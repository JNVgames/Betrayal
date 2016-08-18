package com.jnv.betrayal.dungeon.managers;

import com.jnv.betrayal.dungeon.Field;
import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.Event;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import io.socket.client.Socket;

public class RoundManager {

	private ArrayList<Event> events;
	private AnimationManager animation;
	private Socket socket;
	public final Deque<Event> eventHistory;

	public RoundManager(AnimationManager animation) {
		events = new ArrayList<Event>();
		eventHistory = new ArrayDeque<Event>();
		this.animation = animation;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Check for events to go off during each turn
	 * @param card card to check events for
	 */
	public void checkEvents(Card card) {
		List<Event> eventsToRemove = new ArrayList<Event>();
		System.out.println("-----------ROUND MANAGER: " + card.getName() + "------------");
		System.out.println("events before = " + events);
		for (Event event : events) {
			final Event tmp = event;

			//checks if the src is still alive

				if(!checkSrcAlive(event.getSrc())){
					System.out.println("END EFFECT: bECAUSe SRC IS DEAD" + event);
					Event tmpEvent = new Event(event.getEffect(), event.getEffect().getEndType());
					// Perform the animation
					animation.queueEventAnimation(tmpEvent, new Runnable() {
						@Override
						public void run() {
							tmp.getEffect().doEndEffect();
						}
					});
					// Add the end effect to event history
					eventHistory.addLast(tmpEvent);
				}


			//check if you put in that event
			if (card == event.getSrc() ) {
				System.out.println("DECREASE TURNS FOR EVENT: " + event);
				event.decreaseTurns();
				if (event.effectEnded()) {
					System.out.println("END EFFECT: " + event);
					Event tmpEvent = new Event(event.getEffect(), event.getEffect().getEndType());
					// Perform the animation
					animation.queueEventAnimation(tmpEvent, new Runnable() {
						@Override
						public void run() {
							tmp.getEffect().doEndEffect();
						}
					});
					// Add the end effect to event history
					eventHistory.addLast(tmpEvent);
				}
				// If effect is consistent
				// 	and it didn't get its turns decreased for the first time
				// 	(Basically a check that startEffect() and consistentEffect() weren't called on the same turn)
				if (event.getEffect().isConsistent() && event.getTurnsLeft() != (event.getEffect().getTurns())) {
					System.out.println("CONSISTENT EFFECT: " + event);
					Event tmpEvent = new Event(event.getEffect(), event.getEffect().getConsistentType());
					animation.queueEventAnimation(tmpEvent, new Runnable() {
						@Override
						public void run() {
							tmp.getEffect().doConsistentEffect();
						}
					});
					eventHistory.addLast(tmpEvent);
				}
			}
			// Flush out all ended effects
			if (event.effectEnded()) {
				eventsToRemove.add(event);
				System.out.println("Flushing event: " + event);
			}
		}
		for (Event event : eventsToRemove) {
			events.remove(event);
		}
		animation.animate();
	}

	public boolean checkSrcAlive(Card card){
		Field field = card.getField();
		for(Card c : field.getAllCards()){
			if (c.getID() == card.getID())
				return true;
		}
		return false;
	}

	public void addEventClient(final Event event) {
		System.out.println("RECEIVED EVENT: " + event.toString());
		events.add(event);
		eventHistory.addLast(event);
		animation.queueEventAnimation(event, new Runnable() {
			@Override
			public void run() {
				event.getEffect().doStartEffect();
			}
		});
	}

	public Event addEventClient(final Effect effect, EventType eventType) {
		Event event = new Event(effect, eventType);
		System.out.println("RECEIVED EVENT: " + event.toString());
		events.add(event);
		eventHistory.addLast(event);
		animation.queueEventAnimation(event, new Runnable() {
			@Override
			public void run() {
				effect.doStartEffect();
			}
		});
		return event;
	}

	public void addEvent(Event event) {
		addEventClient(event);
		emitEvent(event);
	}

	public void addEvent(Effect effect, EventType eventType) {
		emitEvent(addEventClient(effect, eventType));
	}

	private void emitEvent(Event event) {
		if (socket != null && socket.connected()) {
			socket.emit("newEvent", event.toJSON());
		}
	}
}
