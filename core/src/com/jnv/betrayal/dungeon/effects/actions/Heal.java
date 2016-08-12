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
		super(EventType.HEAL, turns);
		try {
			this.health = data.getInt("health");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		description = "Heal\n"
				+ "heal "+ health + " health";
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.heal(health);
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject() {
		try {
			data.getJSONObject("values").put("health", health);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
