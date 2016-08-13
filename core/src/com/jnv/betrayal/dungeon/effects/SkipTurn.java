package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SkipTurn extends Effect {

    public SkipTurn() {
        super(EventType.SKIP_TURN);
    }

	// JSON Constructor
	public SkipTurn(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.SKIP_TURN, turns);
		isHostile = false;
		this.turns = turns;
		init(src, dest);
	}

    @Override
    public void startEffect(Card destCard) {

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
