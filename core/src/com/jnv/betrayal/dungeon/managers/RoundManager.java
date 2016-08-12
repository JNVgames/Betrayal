package com.jnv.betrayal.dungeon.managers;

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
			if (card == event.getSrc()) {
				System.out.println("DECREASE TURNS FOR EVENT: " + event);
				event.decreaseTurns();
				if (event.effectEnded()) {
					Event tmpEvent = new Event(event.getEffect(), event.getEffect().getEndType());
					// Perform the event when it ends
					event.getEffect().doEndEffect();
					// Perform the animation
					animation.performAnimation(tmpEvent);
					// Add the end effect to event history
					eventHistory.addLast(tmpEvent);
				}
				// If effect is consistent
				if (event.getEffect().isConsistent()) {
					Event tmpEvent = new Event(event.getEffect(), event.getEffect().getConsistentType());
					event.getEffect().doConsistentEffect();
					animation.performAnimation(tmpEvent);
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
	}

	public void addEventClient(Event event) {
		System.out.println("addEventClient(1p): New event received: " + event.getEventType());
		events.add(event);
		event.getEffect().doStartEffect();
		eventHistory.addLast(event);
		animation.performAnimation(event);
	}

	public Event addEventClient(Effect effect, EventType eventType) {
		System.out.println("addEventClient(2p): New event received: " + eventType);
		Event event = new Event(effect, eventType);
		events.add(event);
		effect.doStartEffect();
		eventHistory.addLast(event);
		animation.performAnimation(event);
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
