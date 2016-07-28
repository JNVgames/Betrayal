package com.jnv.betrayal.dungeon.effects.debuffs;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Poison extends Effect {
	private int damage;

	public Poison(int damage, int turns) {
		super(EventType.POISON, turns, true, EventType.C_POISON, EventType.E_POISON);
		isHostile = true;
		description = "Poison\n"
				+ "take "+ damage + "\n" + "for " + turns + " turns.";
	}

	// JSON Constructor
	public Poison(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.POISON, turns, true, EventType.C_POISON, EventType.E_POISON);
		isHostile = true;
		description = "Poison\n"
				+ "take "+ damage + "\n" + "for " + turns + " turns.";
		this.src = src;
		this.dest = dest;
	}

	@Override
	public void startEffect(Card card) {
		card.poison();
	}

	@Override
	public void endEffect(Card card) {
	}

	@Override
	public void consistentEffect(Card card) {
		card.poison();
	}
	@Override
	protected void addToObject() {
		try {
			data.put("damage", damage);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
