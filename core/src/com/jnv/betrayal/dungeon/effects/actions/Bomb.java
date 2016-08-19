package com.jnv.betrayal.dungeon.effects.actions;


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

public class Bomb extends Effect {

	private static final EventType sType = EventType.BOMB;
	private int attack;

	public Bomb(int attack, int turns) {
		super(sType, turns);
		this.attack = attack;
		isHostile = true;
	}

	// JSON constructor
	public Bomb(JSONObject values, int turns, Card src, List<Card> dest) {
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
	public void startEffect(Card destCard) {
		src.getField().roundManager.addEventClient(new Bombed(attack, turns, destCard), EventType.NONE);
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("attack", attack);
	}
}
