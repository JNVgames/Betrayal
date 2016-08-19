package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class IncreasedAttack extends Effect {

	private static final EventType sType = EventType.ATTACK;
	private int attack;

	public IncreasedAttack(int attack) {
		super(sType);
		this.attack = attack;
		isHostile = true;
	}

	// JSON Constructor
	public IncreasedAttack(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sType, turns);
		try {
			this.attack = values.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card card) {
		card.attack(attack);
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}

	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("attack", attack);
	}
}