package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class Flee extends Effect {

	public Flee(Card src) {
		super(EventType.FLEE);
		isHostile = false;
		this.src = src;
		dest = new ArrayList<Card>();
		dest.add(src);
	}

	@Override
	public void startEffect(Card card) {
		card.flee();
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}
}
