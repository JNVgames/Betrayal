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

public class AttackUp extends Effect {

	private static final EventType startEventType = EventType.BUFF_ATTACK;
	private static final EventType endEventType = EventType.E_BUFF_ATTACK;
	private int attack;

	public AttackUp(int attack, int turns) {
		super(startEventType, turns, endEventType);
		this.attack = attack;
		isHostile = false;
		description = "Attack Buff\n" + "increase attack by  " + attack + "\n"
				+ "\nfor" + turns + " turns.";
	}

	// JSON Constructor
	public AttackUp(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startEventType, turns, endEventType);
		try {
			this.attack = values.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		description = "Attack Buff\n" + "increase attack by  " + attack + "\n"
				+ "\nfor" + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.increaseCurrentAttack(attack);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.decreaseCurrentAttack(attack);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("attack", attack);
	}
}
