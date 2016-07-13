package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.actions.FailedToFlee;
import com.jnv.betrayal.dungeon.effects.actions.Flee;

public class Run extends Effect {

	private int fleeChance;

	public Run(int fleeChance) {
		super(EventType.FLEE);
		this.fleeChance = fleeChance;
		isHostile = false;
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
}
