package com.jnv.betrayal.dungeon.effects.specials;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PriestAttackSpecial extends Effect {

	public PriestAttackSpecial(Card src, List<Card> dest) {
		super(EventType.PRIEST_ATTACK_SPECIAL, 2);
		isHostile = true;
		init(src, dest);
	}

	// JSON Constructor
	public PriestAttackSpecial(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.PRIEST_ATTACK_SPECIAL, turns);
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.increaseCurrentAttack(src.getBaseAttack() * 0.25f);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.decreaseCurrentAttack(src.getBaseAttack() * 0.25f);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject() {
		try {
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
