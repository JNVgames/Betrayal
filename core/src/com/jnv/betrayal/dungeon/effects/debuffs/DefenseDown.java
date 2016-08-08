package com.jnv.betrayal.dungeon.effects.debuffs;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DefenseDown extends Effect {
	private int defense;

	public DefenseDown(int defense, int turns) {
		super(EventType.DEBUFF_DEFENSE, turns, EventType.E_DEBUFF_DEFENSE);
		this.defense = defense;
		isHostile = true;
		description = "Defense Debuff\nLowers your defense by "
				+ defense + "\n" + "for " + turns + " turns.";
	}

	// JSON Constructor
	public DefenseDown(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.DEBUFF_DEFENSE, turns, EventType.E_DEBUFF_DEFENSE);
		try {
			this.defense = data.getInt("defense");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		description = "Defense Debuff\nLowers your defense by "
				+ defense + "\n" + "for " + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card card) {
		card.decreaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card card) {
		card.increaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card card) {

	}
	@Override
	protected void addToObject() {
		try {
			data.put("defense", defense);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
