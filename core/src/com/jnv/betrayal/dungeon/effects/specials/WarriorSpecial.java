package com.jnv.betrayal.dungeon.effects.specials;


import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WarriorSpecial extends Effect {

	public WarriorSpecial(Card src, List<Card> dest) {
		super(EventType.WARRIOR_SPECIAL);
		isHostile = true;
		init(src, dest);
	}

	// JSON Constructor
	public WarriorSpecial(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.WARRIOR_SPECIAL, turns);
		isHostile = true;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.takeDamage(src.getCurrentAttack() * 1.5f);
	}

	@Override
	public void endEffect(Card destCard) {

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
