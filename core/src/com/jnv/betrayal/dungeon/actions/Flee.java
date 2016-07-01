package com.jnv.betrayal.dungeon.actions;

import com.jnv.betrayal.dungeon.cards.Card;

import java.util.List;
import java.util.Random;

public class Flee extends Action {

	private int chance;

	/**
	 * An action with a chance to flee
	 *
	 * @param chance an integer that indicates the chance in percentage format (1-100)
	 */
	public Flee(int chance) {
		type = ActionType.ITEM;
		this.chance = chance;
		targets.add(parentCard);
	}

	public int getTargetLimit() {
		return 0;
	}
	
	public void begin() {
		Random random = new Random();
		float rng = random.nextInt(100) + 1;
		if (rng <= chance) ; // todo implement leave dungeon function
	}

	public void end() {
		// Do nothing
	}

	public void setTargets(List<Card> player) {
		// Do nothing since this is a self-targeted item
	}

	public String toString() {
		return null;
	}

	public void event() {

	}
}
