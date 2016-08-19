package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Defend extends Effect {

	private static final EventType startEventType = EventType.DEFEND;

	public Defend(Card src, List<Card> dst) {
		super(startEventType);
		turns = 1;
		isHostile = false;
		init(src, dst);
	}

	// JSON Construction
	public Defend(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startEventType, turns);
		isHostile = false;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.addDefender((PlayerCard) src);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.removeDefender(src.getID());
	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}
