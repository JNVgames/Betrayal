package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class AttackDown extends Effect {
	private int value;

	public AttackDown() {
	}

	public AttackDown(Card src, ArrayList<Card> dest, int value) {
		super(src, dest);
		this.value = value;
	}

	@Override
	public void startEffect(Card card) {
		card.decreaseCurrentAttack(value);
	}

	@Override
	public void endEffect(Card card) {
		card.increaseCurrentAttack(value);
	}
}
