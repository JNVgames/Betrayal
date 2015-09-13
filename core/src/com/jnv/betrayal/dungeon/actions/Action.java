/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.actions;

import com.jnv.betrayal.dungeon.Entity;
import com.jnv.betrayal.dungeon.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface that provides methods for a dungeon action
 */
public interface Action {

	List<Entity> targets = new ArrayList<Entity>();

	/**
	 * Different types of actions
	 */
	enum ActionType {
		Attack, Defend, Buff, Skill
	}

	/**
	 * Specifies everything that happens when this action is executed
	 */
	void event();

	/**
	 * Specifies targets for this action
	 * @param player targets
	 */
	void setTargets(List<Player> player);

	/**
	 * Performs the event on all the targets
	 */
	void fire();
}
