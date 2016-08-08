package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TrueDamage extends Effect {
	private int damage;


	public TrueDamage(int damage) {
		super(EventType.BUFF_ATTACK, 1, EventType.E_BUFF_ATTACK);
		this.damage = damage;
		isHostile = false;
		description = "True Damage\n" + "deal  "+ damage + "\n"
				+ "\n ignoring defense";
	}

	// JSON Constructor
	public TrueDamage(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.BUFF_ATTACK, turns, EventType.E_BUFF_ATTACK);
		try {
			this.damage = data.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		description = "Attack Buff\n" + "increase attack by  "+ damage + "\n"
				+ "\nfor" + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card card) {
		card.attackTrueDamage(damage);
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
			data.put("attack", damage);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
