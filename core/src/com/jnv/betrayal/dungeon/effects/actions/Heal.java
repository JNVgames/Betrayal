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

public class Heal extends Effect {

	private static final EventType startEventType = EventType.HEAL;
	private int health;

	public Heal(int health) {
		super(startEventType);
		this.health = health;
		isHostile = false;
		description = "Heal\n"
				+ "heal " + health + " health";
	}

	// JSON Constructor
	public Heal(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startEventType, turns);
		try {
			this.health = values.getInt("health");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		description = "Heal\n"
				+ "heal " + health + " health";
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.heal(health);
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("health", health);
	}
}
