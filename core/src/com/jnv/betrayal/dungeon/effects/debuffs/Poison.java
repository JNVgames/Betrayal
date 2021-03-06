package com.jnv.betrayal.dungeon.effects.debuffs;


import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONObject;

import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Poison extends Effect {

	private static final String DESCRIPTION = "Poison\n"
			+ "Deal true damage \nequal to 20% of target's\ntotal health for ";
	private static final EventType startType = EventType.POISON;

	public Poison(int turns) {
		this(turns, null, null);
	}

	public Poison(int turns, Card src, List<Card> dest) {
		super(startType, turns);
		isHostile = true;
		description = DESCRIPTION + turns + " turns.";
		init(src, dest);
	}

	// JSON Constructor
	public Poison(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startType, turns);
		isHostile = true;
		description = DESCRIPTION + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		src.getField().roundManager.addEventClient(new Poisoned(turns, destCard), EventType.NONE);
	}

	@Override
	public void endEffect(Card destCard) {
	}

	@Override
	public void consistentEffect(Card destCard) {
	}
}
