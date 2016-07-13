package com.jnv.betrayal.dungeon.managers;

import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.Event;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RoundManager {

	private ArrayList<Event> events;
	public final Deque<Event> eventHistory;

	public RoundManager() {
		events = new ArrayList<Event>();
		eventHistory = new ArrayDeque<Event>();
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
					addEvent(event.getEffect(), event.getEffect().getEndType());
				}
				//if effect is consistent
				if (event.getEffect().isConsistent()) {
					event.getEffect().doConsistentEffect();
					addEvent(event.getEffect(), event.getEffect().getConsistentType());
				}
			}
		}
		for (Event event : eventsToRemove) {
			events.remove(event);
		}
	}

	public void addEvent(Event event) {
		events.add(event);
		event.getEffect().doStartEffect();
		eventHistory.addLast(event);
		AnimationManager.performAnimation(event);
	}

	public void addEvent(Effect effect, EventType eventType) {
		Event event = new Event(effect, eventType);
		events.add(event);
		effect.doStartEffect();
		eventHistory.addLast(event);
		AnimationManager.performAnimation(event);
	}
}
