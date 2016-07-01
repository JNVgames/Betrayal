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
		for (int i=0; i< events.size(); i++) {
			if (card == events.get(i).getCard()) {
				events.get(i).decreaseTurns();
				if (events.get(i).turnIsZero()) {
					events.get(i).getEffect().doEndEffect();
					events.remove(i);
					i--;				//makes sure you dont skip anything
				}
				//if effect is consistent
				if(events.get(i).getEffect().isConsistent()){
					events.get(i).getEffect().doConsistentEffect();
				}
			}
		}
	}

	public void addEvent(Event event) {
		events.add(event);
		event.getEffect().doStartEffect();
	}

}
