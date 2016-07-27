package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.actions.EventType;
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
		addToObject();
	}

	// JSON Constructor
	public Flee(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.FLEE);
		isHostile = false;
		this.src = src;
		dest = new ArrayList<Card>();
		dest.add(src);
		addToObject();
	}

	@Override
	public void startEffect(Card card) {
		card.flee();
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

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
