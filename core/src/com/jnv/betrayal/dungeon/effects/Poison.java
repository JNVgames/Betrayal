package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class Poison extends Effect {
	private int value;

	public Poison() {
	}

	public Poison(Card src, ArrayList<Card> dest, boolean consistent, int value, int turns) {
		super(src, dest, consistent, turns);
		this.value = value;
	}

	@Override
	public void startEffect(Card card) {
		card.poison();
	}

	@Override
	public void endEffect(Card card) {	}

	@Override
	public void consistentEffect(Card card) {
		card.poison();
	}
}
