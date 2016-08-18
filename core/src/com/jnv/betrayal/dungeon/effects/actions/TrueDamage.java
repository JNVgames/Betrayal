package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TrueDamage extends Effect {

	private static final EventType startEventType = EventType.TRUE_DAMAGE;
	private int damage;

	public TrueDamage(int damage) {
		super(startEventType, 1);
		this.damage = damage;
		isHostile = true;
		description = "True Damage\n" + "deal " + damage + "\n"
				+ "\n ignoring defense";
	}

	// JSON Constructor
	public TrueDamage(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startEventType, turns);
		try {
			this.damage = values.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		description = "True Damage\n" + "deal " + damage + "\n"
				+ "\n ignoring defense";
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
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("attack", damage);
	}
}
