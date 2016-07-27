package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Bomb extends Effect {

	private int attack;

	public Bomb(int attack, int turns) {
		super(EventType.BOMB, turns);
		this.attack = attack;
		isHostile = true;
		addToObject();
	}

	// JSON constructor
	public Bomb(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.BOMB, turns);
		try {
			this.attack = data.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		addToObject();
	}

	@Override
	public void startEffect(Card card) {

	}

	@Override
	public void endEffect(Card card) {
		card.takeDamage(attack);
	}

	@Override
	public void consistentEffect(Card card) {

	}

	@Override
	protected void addToObject() {
		JSONObject values = new JSONObject();
		try {
			data.put("attack", attack);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
