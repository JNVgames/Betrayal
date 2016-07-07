package com.jnv.betrayal.dungeon.managers;

import com.jnv.betrayal.dungeon.actions.Action;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Event;

import java.util.ArrayList;
import java.util.List;

public class RoundManager {
	private ArrayList<Event> events;

	public RoundManager() {
		events = new ArrayList<Event>();
	}


	/**
	 * Check for events to go off during each turn
	 * @param card card to check events for
	 * @return the events performed to put into the event log
	 */
	public List<Action> checkEvents(Card card) {
		List<Event> eventsToRemove = new ArrayList<Event>();
		List<Action> actions = new ArrayList<Action>();
		for (Event event : events) {
			if (card == event.getCard()) {
				event.decreaseTurns();
				if (event.turnIsZero()) {
					event.getEffect().doEndEffect();
					eventsToRemove.add(event);
					actions.add(new Action(event.getEffect().getSrc(), event.getEffect().getDest(),
							event.getEffect().getEndType()));
				}
				//if effect is consistent
				if (event.getEffect().isConsistent()) {
					event.getEffect().doConsistentEffect();
					actions.add(new Action(event.getEffect().getSrc(), event.getEffect().getDest(),
							event.getEffect().getConsistentType()));
				}
			}
		}
		for (Event event : eventsToRemove) {
			events.remove(event);
		}
		return actions;
	}

	public void addEvent(Event event) {
		events.add(event);
		event.getEffect().doStartEffect();
	}
}
