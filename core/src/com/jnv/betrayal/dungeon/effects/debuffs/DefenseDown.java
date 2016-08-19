package com.jnv.betrayal.dungeon.effects.debuffs;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class DefenseDown extends Effect {

	private static final EventType startType = EventType.DEBUFF_DEFENSE;
	private static final EventType endType = EventType.E_DEBUFF_DEFENSE;
	private int defense;

	public DefenseDown(int defense, int turns) {
		super(startType, turns, endType);
		this.defense = defense;
		isHostile = true;
		description = "Defense Debuff\nLowers your defense by "
				+ defense + "\n" + "for " + turns + " turns.";
	}

	// JSON Constructor
	public DefenseDown(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startType, turns, endType);
		try {
			this.defense = values.getInt("defense");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		description = "Defense Debuff\nLowers your defense by "
				+ defense + "\n" + "for " + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.decreaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.increaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}
	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("defense", defense);
	}
}
