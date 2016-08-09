package com.jnv.betrayal.dungeon.effects.specials;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONObject;

import java.util.List;

public class PriestHealSpecial extends Effect {

	public PriestHealSpecial(Card src, List<Card> dest) {
		super(EventType.PRIEST_HEAL_SPECIAL);
		isHostile = true;
		init(src, dest);
	}

	// JSON Constructor
	public PriestHealSpecial(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.PRIEST_HEAL_SPECIAL, turns);
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.heal(src.getBaseHealth() * 0.1f);
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject() {

	}
}
