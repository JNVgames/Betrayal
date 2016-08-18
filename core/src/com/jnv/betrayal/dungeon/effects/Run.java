package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.turns.YourTurn;

import org.json.JSONObject;

import java.util.List;

public class Run extends Effect {

	private static final EventType sType = EventType.RUN;
	private int fleeChance;

	public Run(int fleeChance) {
		super(sType);
		this.fleeChance = fleeChance;
		isHostile = false;
	}

	// JSON Constructor
	public Run(JSONObject values, int turns, Card src, List<Card> dest) {
		super(sType, turns);
		isHostile = false;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		if (src.getID() == src.getField().getClientCharacter().getId()) {
			((YourTurn) src.getField().uiManager.getCurrentTurn()).attemptFlee(fleeChance);
		}
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}
