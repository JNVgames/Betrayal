package com.jnv.betrayal.dungeon.managers;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.turns.Event;

import java.util.ArrayList;

public class RoundManager {
	private ArrayList<Event> events;

	public RoundManager() {
		events = new ArrayList<Event>();
	}

	public void checkEvents(Card card) {
		for (Event event : events) {
			if (card == event.getCard()) {
				event.decreaseTurns();
				if (event.turnIsZero()) {
					event.getEffect().endEffect(card);
				}
			}
		}
	}

	public void addEvent(Event event) {
		events.add(event);
		event.getEffect().doStartEffect();
	}

}
