/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.actions;

import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface that provides methods for a dungeon action
 */
public abstract class Action {

	protected int type = -1;
	protected Card parentCard;
	protected List<Card> targets = new ArrayList<Card>();

	public boolean equals(Action obj) {
		return this.toString().equals(obj.toString());
	}

	public abstract String toString();

	public void setCard(Card card) {
		parentCard = card;
	}

	/**
	 * Specifies everything that happens when this action is executed
	 */
	public abstract void event();

	/**
	 * Specifies targets for this action
	 * @param player targets
	 */
	public abstract void setTargets(List<Card> player);

	/**
	 * Performs the event on all the targets
	 */
	public abstract void begin();

	/**
	 * Removes buff effects from the character. Used mainly for buffs and debuffs when the
	 * duration ends.
	 */
	public abstract void end();

	/**
	 * @return number of targets that can be selected. -1 is returned if there is no limit
	 */
	public abstract int getTargetLimit();

	public int getType() {
		if (type == -1)
			throw new AssertionError("ActionType has not been set");
		return type;
	}
}
