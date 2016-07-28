package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.actions.FailedToFlee;
import com.jnv.betrayal.dungeon.effects.actions.Flee;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Run extends Effect {

	private int fleeChance;

	public Run(int fleeChance) {
		super(EventType.FLEE);
		this.fleeChance = fleeChance;
		isHostile = false;
	}

	// JSON Constructor
	public Run(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.FLEE);
		try {
			this.fleeChance = data.getInt("fleeChance");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		this.src = src;
		this.dest = dest;
	}

	@Override
	public void startEffect(Card card) {
		if (PlayerCard.canFlee(fleeChance / 25)) {
			Effect flee = new Flee(card);
			card.getField().roundManager.addEvent(flee, flee.startType);
		}
		else {
			Effect failToFlee = new FailedToFlee(card);
			card.getField().roundManager.addEvent(failToFlee, failToFlee.startType);
		}
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}

	@Override
	protected void addToObject() {

	}
}
