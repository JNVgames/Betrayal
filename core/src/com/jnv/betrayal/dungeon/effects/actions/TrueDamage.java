package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TrueDamage extends Effect {

	private int damage;
	private String description = "True Damage\n" + "deal %d" + "\n"
			+ "\n ignoring defense";

	public TrueDamage(int damage) {
		super(EventType.BUFF_ATTACK, 1, EventType.E_BUFF_ATTACK);
		this.damage = damage;
		isHostile = true;
		description = String.format(description, damage);
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
		description = String.format(description, damage);
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.attackTrueDamage(damage);
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
			data.put("attack", damage);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
