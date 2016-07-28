package com.jnv.betrayal.dungeon.effects.buffs;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AttackAndDefenseUp extends Effect {
	private int attack;
	private int defense;

	public AttackAndDefenseUp(int attack, int defense, int turns) {
		super(EventType.BUFF_ATTACK_DEFENSE, turns, EventType.E_BUFF_ATTACK_DEFENSE);
		this.attack = attack;
		this.defense = defense;
		isHostile = false;
		description = "Attack Defense Buff\n" + "increase attack by  " + attack + "\n" +
				"increase defense by " + defense + "\nfor" + turns + " turns.";
	}

	// JSON Constructor
	public AttackAndDefenseUp(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.BUFF_ATTACK_DEFENSE, turns, EventType.E_BUFF_ATTACK_DEFENSE);
		try {
			this.attack = data.getInt("attack");
			this.defense = data.getInt("defense");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		description = "Attack Defense Buff\n" + "increase attack by  " + attack + "\n" +
				"increase defense by " + defense + "\nfor" + turns + " turns.";
		this.src = src;
		this.dest = dest;
	}


	@Override
	public void startEffect(Card card) {
		card.increaseCurrentAttack(attack);
		card.increaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card card) {
		card.decreaseCurrentAttack(attack);
		card.decreaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card card) {

	}

	@Override
	protected void addToObject() {
		try {
			data.put("attack", attack);
			data.put("defense", defense);
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
