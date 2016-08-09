package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Flee extends Effect {

	public Flee(Card src) {
		super(EventType.FLEE);
		isHostile = false;
		this.src = src;
		dest = new ArrayList<Card>();
		dest.add(src);
	}

	// JSON Constructor
	public Flee(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.FLEE, turns);
		isHostile = false;
		List<Card> destCards = new ArrayList<Card>();
		destCards.add(src);
		init(src, destCards);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.flee();
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
