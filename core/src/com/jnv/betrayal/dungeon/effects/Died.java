package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;

public class Died extends Effect {

	public Died(Card src) {
		super(EventType.ATTACK);
		isHostile = false;
		this.src = src;
		this.dest.add(src);
	}

	@Override
	public void startEffect(Card card) {
		card.died();
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}
}
