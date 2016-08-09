package com.jnv.betrayal.dungeon.effects.specials;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONObject;

import java.util.List;

public class KnightSpecial extends Effect {

	public KnightSpecial(Card src, List<Card> dest) {
		super(EventType.KNIGHT_SPECIAL);
		turns = 1;
		isHostile = false;
		init(src, dest);
	}

	// JSON Constructor
	public KnightSpecial(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.KNIGHT_SPECIAL, turns);
		isHostile = false;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.addDefender((PlayerCard) src);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.removeDefender(src.getID());
	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject() {

	}
}
