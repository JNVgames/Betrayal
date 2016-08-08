package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SkipTurn extends Effect {

    public SkipTurn() {
        super(EventType.NONE);
    }

	// JSON Constructor
	public SkipTurn(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.NONE, turns);
		isHostile = false;
		this.turns = turns;
		init(src, dest);
	}

    @Override
    public void startEffect(Card card) {

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
