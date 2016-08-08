package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Attack extends Effect {

	private int attack;

	public Attack(Card src, List<Card> dest) {
		super(EventType.ATTACK);
		isHostile = false;
		attack = src.getCurrentAttack();
		this.src = src;
		this.dest = dest;
	}

	// JSON Constructor
	public Attack(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.ATTACK, turns);
		isHostile = false;
		attack = src.getCurrentAttack();
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
		JSONObject values = new JSONObject();
		try {
			values.put("attack", attack);
			data.put("class", getClass().getCanonicalName());
			data.put("values", values);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fromJSON(JSONObject effect) {

	}
}
