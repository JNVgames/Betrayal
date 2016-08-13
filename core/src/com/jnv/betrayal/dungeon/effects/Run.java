package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.actions.FailedToFlee;
import com.jnv.betrayal.dungeon.effects.actions.Flee;
import com.jnv.betrayal.dungeon.turns.YourTurn;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Run extends Effect {

	private int fleeChance;

	public Run(int fleeChance) {
		super(EventType.RUN);
		this.fleeChance = fleeChance;
		isHostile = false;
	}

	// JSON Constructor
	public Run(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.RUN, turns);
		isHostile = false;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		if (src.getID() == src.getField().getClientCharacter().getId()) {
			((YourTurn) src.getField().turnManager.getCurrentTurn()).attemptFlee(fleeChance);
		}
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
