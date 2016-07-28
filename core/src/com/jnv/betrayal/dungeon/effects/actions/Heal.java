package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Heal extends Effect {

	private int health;

	public Heal(int health) {
		super(EventType.HEAL);
		this.health = health;
		isHostile = false;
		description = "Heal\n"
				+ "heal "+ health + " health";
	}

	// JSON Constructor
	public Heal(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.HEAL);
		try {
			this.health = data.getInt("health");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		description = "Heal\n"
				+ "heal "+ health + " health";
		this.src = src;
		this.dest = dest;
	}

	@Override
	public void startEffect(Card card) {
		card.heal(health);
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
			data.put("health", health);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
