/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.actions;

import com.jnv.betrayal.dungeon.Player;
import com.jnv.betrayal.main.Betrayal;

import java.util.ArrayList;
import java.util.List;

public class NormalAttack implements Action {

	public final ActionType actionType = ActionType.Attack;
	public final List<Player> targets;

	public NormalAttack() {
		targets = new ArrayList<Player>();
	}

	@Override
	public void event() {

	}

	public void setTargets(List<Player> player) {
		// Assertion that there is only one target, since this is a normal attack
		if (Betrayal.debug && player.size() != 1)
			throw new AssertionError("Normal attack can only target one player");
		targets.addAll(player);
	}
}
