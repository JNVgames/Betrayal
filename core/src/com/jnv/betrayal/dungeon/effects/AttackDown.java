package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class AttackDown extends Effect {
	private int value;

	public AttackDown() {
	}

	public AttackDown(Card src, ArrayList<Card> dest,boolean consistent, int value) {
		super(src, dest, consistent);
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

	@Override
	public void consistentEffect(Card card) {

	}
}