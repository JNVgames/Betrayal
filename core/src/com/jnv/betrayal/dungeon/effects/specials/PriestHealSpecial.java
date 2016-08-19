package com.jnv.betrayal.dungeon.effects.specials;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PriestHealSpecial extends Effect {

	private static final EventType sType = EventType.PRIEST_HEAL_SPECIAL;

	public PriestHealSpecial(Card src, List<Card> dest) {
		super(sType);
		isHostile = true;
		init(src, dest);
	}

	// JSON Constructor
	public PriestHealSpecial(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sType, turns);
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.heal(src.getBaseHealth() * 0.5f);
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}
