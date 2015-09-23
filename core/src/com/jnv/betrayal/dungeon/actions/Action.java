/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.actions;

import com.jnv.betrayal.dungeon.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface that provides methods for a dungeon action
 */
public abstract class Action {

	List<Card> targets = new ArrayList<Card>();

	/**
	 * Different types of actions
	 */
	enum ActionType {
		Attack, Defend, Buff, Skill
	}

	public boolean equals(Action obj) {
		return this.toString().equals(obj.toString());
	}

	public abstract String toString();

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
	public abstract void fire();

	/**
	 * @return number of targets that can be selected. -1 is returned if there is no limit
	 */
	public abstract int getTargetLimit();
}
