/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.actions;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.main.Betrayal;

import java.util.List;

public class NormalAttack extends Action {

	public NormalAttack() {

	}

	public String toString() {
		return "Normal Attack";
	}

	public void event() {

	}

	public void setTargets(List<Card> player) {
		// Assertion that there is only one target, since this is a normal attack
		if (Betrayal.debug && player.size() != 1)
			throw new AssertionError("Normal attack can only target one player");
		targets.addAll(player);
	}

	public void begin() {

	}

	public void end() {

	}

	public int getTargetLimit() {
		return 1;
	}
}
