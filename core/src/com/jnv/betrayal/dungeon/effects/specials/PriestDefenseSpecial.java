package com.jnv.betrayal.dungeon.effects.specials;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PriestDefenseSpecial extends Effect {

	private static final EventType sType = EventType.PRIEST_DEFENSE_SPECIAL;

	public PriestDefenseSpecial(Card src, List<Card> dest) {
		super(sType, 2);
		isHostile = true;
		init(src, dest);
	}

	// JSON Constructor
	public PriestDefenseSpecial(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sType, turns);
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.increaseCurrentDefense(src.getBaseDefense() * 0.25f);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.decreaseCurrentDefense(src.getBaseDefense() * 0.25f);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}
