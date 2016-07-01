package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class Bomb extends Effect {
	private int value;

	public Bomb() {
	}

	public Bomb(Card src, ArrayList<Card> dest, boolean consistent, int value) {
		super(src, dest, consistent);
		this.value = value;
	}

	@Override
	public void startEffect(Card card) {

	}

	@Override
	public void endEffect(Card card) {
		card.takeDamage(value);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}
