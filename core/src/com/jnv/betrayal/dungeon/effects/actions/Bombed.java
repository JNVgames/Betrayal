package com.jnv.betrayal.dungeon.effects.actions;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Bombed extends Effect {

	private static final EventType sType = EventType.NONE;
	private static final EventType eType = EventType.E_BOMB;
	private int attack;

	public Bombed(int attack, int turns, Card src) {
		super(sType, turns, eType);
		this.attack = attack;
		isHostile = true;
		List<Card> dest = new ArrayList<Card>();
		dest.add(src);
		init(src, dest);
	}

	// JSON constructor
	public Bombed(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sType, turns, eType);
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {

	}

	@Override
	public void endEffect(Card destCard) {
		destCard.attack(attack);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}
