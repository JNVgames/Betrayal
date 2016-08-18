package com.jnv.betrayal.dungeon.effects.specials;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ThiefSpecial extends Effect {

	private static final EventType sType = EventType.THIEF_SPECIAL;

	public ThiefSpecial(Card src, List<Card> dest) {
		super(sType);
		isHostile = true;
		init(src, dest);
	}

	// JSON Constructor
	public ThiefSpecial(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sType, turns);
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.attackTrueDamage(src.getCurrentAttack() * 0.5f);
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}
