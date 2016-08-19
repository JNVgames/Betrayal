package com.jnv.betrayal.dungeon.effects.buffs;


import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class DefenseUp extends Effect {

	private static final EventType startEventType = EventType.BUFF_DEFENSE;
	private static final EventType endEventType = EventType.E_BUFF_DEFENSE;
	private int defense;

	public DefenseUp(int defense, int turns) {
		super(startEventType, turns, endEventType);
		this.defense = defense;
		isHostile = false;
		description = "Defense Buff\n"  +
				"increase defense by " + defense + "\nfor" + turns + " turns.";
	}

	// JSON Constructor
	public DefenseUp(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startEventType, turns, endEventType);
		try {
			this.defense = values.getInt("defense");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		description = "Defense Buff\n"  +
				"increase defense by " + defense + "\nfor" + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.increaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.decreaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("defense", defense);
	}
}
