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

public class AttackDown extends Effect {

	private static final EventType startType = EventType.DEBUFF_ATTACK;
	private static final EventType endType = EventType.E_DEBUFF_ATTACK;
	private int attack;

	public AttackDown(int attack, int turns) {
		super(startType, turns, endType);
		this.attack = attack;
		isHostile = true;
		description = "Attack Debuff\nLowers your attack by " + attack + "\n"
				+ "for " + turns + " turns.";
	}

	// JSON Constructor
	public AttackDown(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startType, turns, endType);
		try {
			this.attack = values.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		description = "Attack Debuff\nLowers your attack by " + attack + "\n"
				+ "for " + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.decreaseCurrentAttack(attack);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.increaseCurrentAttack(attack);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("attack", attack);
	}
}
