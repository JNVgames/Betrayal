package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class Heal extends Effect {
	private int value;

	public Heal() {
	}

	public Heal(Card src, ArrayList<Card> dest, boolean consistent, int value) {
		super(src, dest, consistent);
		this.value = value;
	}

	@Override
	public void startEffect(Card card) {
		card.heal(value);
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}
}
