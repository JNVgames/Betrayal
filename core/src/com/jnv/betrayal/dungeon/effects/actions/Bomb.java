package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.effects.EventType;
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
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {

	}

	@Override
	public void endEffect(Card destCard) {
		destCard.takeDamage(attack);
	}

	@Override
	public void consistentEffect(Card destCard) {

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
