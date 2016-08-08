package com.jnv.betrayal.dungeon.effects.debuffs;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AttackDown extends Effect {
	private int attack;

	public AttackDown(int attack, int turns) {
		super(EventType.DEBUFF_ATTACK, turns, EventType.E_DEBUFF_ATTACK);
		this.attack = attack;
		isHostile = true;
		description = "Attack Debuff\nLowers your attack by " + attack + "\n"
				+ "for " + turns + " turns.";
	}

	// JSON Constructor
	public AttackDown(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.DEBUFF_ATTACK, turns, EventType.E_DEBUFF_ATTACK);
		try {
			this.attack = data.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		description = "Attack Debuff\nLowers your attack by " + attack + "\n"
				+ "for " + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card card) {
		card.decreaseCurrentAttack(attack);
	}

	@Override
	public void endEffect(Card card) {
		card.increaseCurrentAttack(attack);
	}

	@Override
	public void consistentEffect(Card card) {

	}

	@Override
	protected void addToObject() {
		JSONObject values = new JSONObject();
		try {
			values.put("attack", attack);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
			data.put("values", values);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
