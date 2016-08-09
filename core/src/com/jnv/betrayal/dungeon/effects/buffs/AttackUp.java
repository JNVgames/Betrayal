package com.jnv.betrayal.dungeon.effects.buffs;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AttackUp extends Effect {
	private int attack;

	public AttackUp(int attack, int turns) {
		super(EventType.BUFF_ATTACK, turns, EventType.E_BUFF_ATTACK);
		this.attack = attack;
		isHostile = false;
		description = "Attack Buff\n" + "increase attack by  "+ attack + "\n"
				+ "\nfor" + turns + " turns.";
	}

	// JSON Constructor
	public AttackUp(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.BUFF_ATTACK, turns, EventType.E_BUFF_ATTACK);
		try {
			this.attack = data.getInt("attack");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		description = "Attack Buff\n" + "increase attack by  "+ attack + "\n"
				+ "\nfor" + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.increaseCurrentAttack(attack);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.decreaseCurrentAttack(attack);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject() {
		try {
			data.put("attack", attack);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
