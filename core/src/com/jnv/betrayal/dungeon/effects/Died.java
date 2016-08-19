package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Died extends Effect {

	private static final EventType sType = EventType.DIED;

	public Died(Card src) {
		super(sType);
		isHostile = false;
		this.src = src;
		dest = new ArrayList<Card>();
		dest.add(src);
	}

	public Died(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sType, turns);
		isHostile = false;
		List<Card> destCards = new ArrayList<Card>();
		destCards.add(src);
		init(src, destCards);
	}

	@Override
	public void startEffect(Card destCard) {
		src.getStatusIcon().setDrawable(null);
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}
