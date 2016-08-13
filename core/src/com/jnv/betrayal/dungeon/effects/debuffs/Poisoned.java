package com.jnv.betrayal.dungeon.effects.debuffs;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Poisoned extends Effect {

	private static final String DESCRIPTION = "Poison\n"
			+ "Take damage equivalent\nto 10% of target's\ncurrent health for ";

	public Poisoned(int turns, Card src) {
		super(EventType.NONE, turns, true, EventType.C_POISON, EventType.E_POISON);
		isHostile = true;
		description = DESCRIPTION + turns + " turns.";
		List<Card> dest = new ArrayList<Card>();
		dest.add(src);
		init(src, dest);
	}

	// JSON Constructor
	public Poisoned(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.NONE, turns, true, EventType.C_POISON, EventType.E_POISON);
		isHostile = true;
		description = DESCRIPTION + turns + " turns.";
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
	}

	@Override
	public void endEffect(Card destCard) {
	}

	@Override
	public void consistentEffect(Card destCard) {
		destCard.poison();
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
