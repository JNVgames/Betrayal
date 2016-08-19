package com.jnv.betrayal.dungeon.effects.specials;

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

public class PriestAttackSpecial extends Effect {

	private static final EventType sType = EventType.PRIEST_ATTACK_SPECIAL;

	public PriestAttackSpecial(Card src, List<Card> dest) {
		super(sType, 2);
		isHostile = true;
		init(src, dest);
	}

	// JSON Constructor
	public PriestAttackSpecial(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sType, turns);
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.increaseCurrentAttack(src.getBaseAttack() * 0.5f);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.decreaseCurrentAttack(src.getBaseAttack() * 0.5f);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}
