package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FailedToFlee extends Effect {

	private static final EventType startEventType = EventType.FAIL_TO_FLEE;

	public FailedToFlee(Card src) {
		super(startEventType);
		isHostile = false;
		this.src = src;
		dest = new ArrayList<Card>();
		dest.add(src);
	}

	// JSON Constructor
	public FailedToFlee(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startEventType, turns);
		isHostile = false;
		List<Card> destCards = new ArrayList<Card>();
		destCards.add(src);
		init(src, destCards);
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
