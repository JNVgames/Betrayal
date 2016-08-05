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
					// Perform the event when it ends
					event.getEffect().doEndEffect();
					// Add the end effect to event history
					eventHistory.addLast(new Event(event.getEffect(), event.getEffect().getEndType()));
				}
				// If effect is consistent
				if (event.getEffect().isConsistent()) {
					event.getEffect().doConsistentEffect();
					eventHistory.addLast(new Event(event.getEffect(), event.getEffect().getConsistentType()));
				}
			}
			// Flush out all ended effects
			if (event.effectEnded()) {
				eventsToRemove.add(event);
			}
		}
		for (Event event : eventsToRemove) {
			events.remove(event);
		}
//		System.out.println("events after = " + events);
//		System.out.println("DEFENDERS FOR PLAYER " + card.getName() + ": " + card.getDefenders());
	}

	public void addEventClient(Event event) {
		events.add(event);
		event.getEffect().doStartEffect();
		eventHistory.addLast(event);
		animation.performAnimation(event);
	}

	public Event addEventClient(Effect effect, EventType eventType) {
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
