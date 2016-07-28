package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Defend extends Effect {

	public Defend(Card src, List<Card> dst) {
		super(EventType.ATTACK);
		isHostile = false;
		this.src = src;
		this.dest = dst;
	}

	// JSON Construction
	public Defend(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.ATTACK);
		isHostile = false;
		this.src = src;
		this.dest = dest;
	}

	@Override
	public void startEffect(Card card) {
		((PlayerCard) src).defendCard(card);
		card.addDefender((PlayerCard) getSrc());
	}

	@Override
	public void endEffect(Card card) {
		card.removeDefender(getSrc());
		((PlayerCard) src).defendCard(null);
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
