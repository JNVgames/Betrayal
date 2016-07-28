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
	private Socket socket;
	public final Deque<Event> eventHistory;

	public RoundManager() {
		events = new ArrayList<Event>();
		eventHistory = new ArrayDeque<Event>();
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
		for (Event event : events) {
			if (card == event.getSrc()) {
				event.decreaseTurns();
				if (event.turnIsZero()) {
					event.getEffect().doEndEffect();
					eventsToRemove.add(event);
					addEventClient(event.getEffect(), event.getEffect().getEndType());
				}
				//if effect is consistent
				if (event.getEffect().isConsistent()) {
					event.getEffect().doConsistentEffect();
					addEventClient(event.getEffect(), event.getEffect().getConsistentType());
				}
			}
		}
		for (Event event : eventsToRemove) {
			events.remove(event);
		}
	}

	public void addEventClient(Event event) {
		events.add(event);
		event.getEffect().doStartEffect();
		eventHistory.addLast(event);
		AnimationManager.performAnimation(event);
	}

	public Event addEventClient(Effect effect, EventType eventType) {
		Event event = new Event(effect, eventType);
		events.add(event);
		effect.doStartEffect();
		eventHistory.addLast(event);
		AnimationManager.performAnimation(event);
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
			System.out.println("send");
			socket.emit("newEvent", event.toJSON());
		}
	}
}
