package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class DefenseUp extends Effect {
	private int value;

	public DefenseUp() {
	}

	public DefenseUp(Card src, ArrayList<Card> dest, boolean consistent, int value) {
		super(src, dest, consistent);
		this.value = value;
	}

	@Override
	public void startEffect(Card card) {
		card.increaseCurrentDefense(value);
	}

	@Override
	public void endEffect(Card card) {
		card.decreaseCurrentDefense(value);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}
