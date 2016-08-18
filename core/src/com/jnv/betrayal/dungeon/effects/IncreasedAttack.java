package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class IncreasedAttack extends Effect {

	private int attack;

	public IncreasedAttack(int attack, int turns) {
		super(EventType.ATTACK, turns);
		this.attack = attack;
		isHostile = true;
	}

	// JSON Constructor
	public IncreasedAttack(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.ATTACK, turns);
		try {
			this.attack = data.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card card) {
		card.attack(attack);
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
			data.getJSONObject("values").put("attack", attack);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}