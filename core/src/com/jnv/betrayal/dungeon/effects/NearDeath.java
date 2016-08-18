package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class NearDeath extends Effect {

	private static final EventType sType = EventType.ATTACK;
	private int attack;

	public NearDeath(int attack, int turns) {
		super(sType, turns);
		this.attack = attack;
		isHostile = true;
	}

	// JSON Construction
	public NearDeath(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sType, turns);
		try {
			this.attack = values.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.attackTrueDamage(destCard.getCurrentHealth() - 1);
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("attack", attack);
	}
}
