package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class Poison extends Effect {
	private int damage;

	public Poison(int damage, int turns) {
		super(ActionType.POISON, turns, true, ActionType.C_POISON, ActionType.E_POISON);
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
