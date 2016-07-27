package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.resources.BetrayalAssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Attack extends Effect {

	private int attack;

	public Attack(Card src, List<Card> dest) {
		super(EventType.ATTACK);
		isHostile = false;
		attack = src.getCurrentAttack();
		this.src = src;
		this.dest = dest;
		addToObject();
	}

	// JSON Constructor
	public Attack(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.ATTACK);
		isHostile = false;
		attack = src.getCurrentAttack();
		this.src = src;
		this.dest = dest;
		addToObject();
	}

	@Override
	public void startEffect(Card card) {
		card.takeDamage(attack);
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
