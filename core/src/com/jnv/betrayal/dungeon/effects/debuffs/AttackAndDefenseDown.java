package com.jnv.betrayal.dungeon.effects.debuffs;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AttackAndDefenseDown extends Effect {
	private int attack;
	private int defense;

	public AttackAndDefenseDown(int attack, int defense, int turns) {
		super(EventType.BUFF_ATTACK_DEFENSE, turns, EventType.E_DEBUFF_ATTACK_DEFENSE);
		this.attack = attack;
		this.defense = defense;
		isHostile = true;
		description = "Attack and Defense Debuff\nLowers your attack by " + attack + "\n"
				+ "and defense by " + defense + "\n" + "for " + turns + " turns.";
		this.src = src;
		this.dest = dest;
	}

	// JSON Constructor
	public AttackAndDefenseDown(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.BUFF_ATTACK_DEFENSE, turns, EventType.E_DEBUFF_ATTACK_DEFENSE);
		try {
			this.attack = data.getInt("attack");
			this.defense = data.getInt("defense");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		description = "Attack and Defense Debuff\nLowers your attack by " + attack + "\n"
				+ "and defense by " + defense + "\n" + "for " + turns + " turns.";
		init(src, dest);

	}

	@Override
	public void startEffect(Card card) {
		card.decreaseCurrentAttack(attack);
		card.decreaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card card) {
		card.increaseCurrentAttack(attack);
		card.increaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card card) {

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
