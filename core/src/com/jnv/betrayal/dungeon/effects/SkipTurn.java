package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONObject;

import java.util.List;

public class SkipTurn extends Effect {

	private static final EventType sType = EventType.SKIP_TURN;

    public SkipTurn() {
        super(sType);
    }

	// JSON Constructor
	public SkipTurn(JSONObject data, int turns, Card src, List<Card> dest) {
		super(sType, turns);
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
}
