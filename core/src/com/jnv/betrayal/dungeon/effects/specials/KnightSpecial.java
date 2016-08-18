package com.jnv.betrayal.dungeon.effects.specials;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class KnightSpecial extends Effect {

	private static final EventType sType = EventType.KNIGHT_SPECIAL;
	private float increasedDefense;
	private boolean isBuffed = false, isDebuffed = false;

	public KnightSpecial(Card src, List<Card> dest) {
		super(sType);
		turns = 1;
		isHostile = false;
		init(src, dest);
	}

	// JSON Constructor
	public KnightSpecial(JSONObject data, int turns, Card src, List<Card> dest) {
		super(sType, turns);
		isHostile = false;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.addDefender((PlayerCard) src);
		if (!isBuffed) {
			increasedDefense = src.getBaseDefense() * 0.5f;
			src.increaseCurrentDefense(increasedDefense);
			isBuffed = true;
		}
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.removeDefender(src.getID());
		if (!isDebuffed) {
			src.decreaseCurrentDefense(increasedDefense);
			isDebuffed = true;
		}
	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}
