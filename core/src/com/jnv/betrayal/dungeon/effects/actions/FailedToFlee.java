package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import java.util.ArrayList;

public class FailedToFlee extends Effect {

	public FailedToFlee(Card src) {
		super(EventType.FAIL_TO_FLEE);
		isHostile = false;
		this.src = src;
		dest = new ArrayList<Card>();
		dest.add(src);
	}

	@Override
	public void startEffect(Card card) {
		card.failedToFlee();
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}
}