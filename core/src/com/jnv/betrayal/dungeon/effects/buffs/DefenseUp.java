package com.jnv.betrayal.dungeon.effects.buffs;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DefenseUp extends Effect {
	private int defense;

	public DefenseUp(int defense, int turns) {
		super(EventType.BUFF_DEFENSE, turns, EventType.E_BUFF_DEFENSE);
		this.defense = defense;
		isHostile = false;
		description = "Defense Buff\n"  +
				"increase defense by " + defense + "\nfor" + turns + " turns.";
		addToObject();
	}

	// JSON Constructor
	public DefenseUp(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.BUFF_DEFENSE, turns, EventType.E_BUFF_DEFENSE);
		try {
			this.defense = data.getInt("defense");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		description = "Defense Buff\n"  +
				"increase defense by " + defense + "\nfor" + turns + " turns.";
		addToObject();
	}

	@Override
	public void startEffect(Card card) {
		card.increaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card card) {
		card.decreaseCurrentDefense(defense);
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
