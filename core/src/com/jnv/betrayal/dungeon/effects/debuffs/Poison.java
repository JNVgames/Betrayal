package com.jnv.betrayal.dungeon.effects.debuffs;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

public class Poison extends Effect {
	private int damage;

	public Poison(int damage, int turns) {
		super(EventType.POISON, turns, true, EventType.C_POISON, EventType.E_POISON);
		isHostile = true;
	}

	@Override
	public void startEffect(Card card) {
		card.poison();
	}

	@Override
	public void endEffect(Card card) {
	}

	@Override
	public void consistentEffect(Card card) {
		card.poison();
	}
}
