package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Died extends Effect {

	public Died(Card src) {
		super(EventType.DIED);
		isHostile = false;
		this.src = src;
		dest = new ArrayList<Card>();
		dest.add(src);
	}

	public Died(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.DIED, turns);
		isHostile = false;
		List<Card> destCards = new ArrayList<Card>();
		destCards.add(src);
		init(src, destCards);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.died();
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject() {
		try {
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
