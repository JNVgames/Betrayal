package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.actions.Action;
import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;

import java.util.List;

public class Run extends Effect {

	private int fleeChance;

	public Run(int fleeChance) {
		super(ActionType.FLEE);
		this.fleeChance = fleeChance;
	}

	@Override
	public void startEffect(Card card) {
		if (PlayerCard.canFlee(fleeChance / 25)) {
			card.getField().actionManager.performAction(new Action(card, ActionType.FLEE));
		}
		else {
			card.getField().actionManager.performAction(new Action(card, ActionType.FAIL_TO_FLEE));
		}
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}
}
