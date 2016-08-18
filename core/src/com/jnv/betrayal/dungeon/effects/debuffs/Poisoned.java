package com.jnv.betrayal.dungeon.effects.debuffs;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Poisoned extends Effect {

	private static final String DESCRIPTION = "Poison\n"
			+ "Take damage equivalent\nto 10% of target's\ncurrent health for ";
	private static final EventType sEventType = EventType.NONE;
	private static final EventType cEventType = EventType.C_POISON;
	private static final EventType eEventType = EventType.E_POISON;

	public Poisoned(int turns, Card src) {
		super(sEventType, turns, true, cEventType, eEventType);
		isHostile = true;
		description = DESCRIPTION + turns + " turns.";
		List<Card> dest = new ArrayList<Card>();
		dest.add(src);
		init(src, dest);
	}

	// JSON Constructor
	public Poisoned(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sEventType, turns, true, cEventType, eEventType);
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
}
