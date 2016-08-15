package com.jnv.betrayal.dungeon.effects.specials;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class KnightSpecial extends Effect {

	private float increasedDefense;

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
		increasedDefense = src.getBaseDefense() * 1.5f;
		src.increaseCurrentDefense(increasedDefense);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.removeDefender(src.getID());
		src.decreaseCurrentDefense(increasedDefense);
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
