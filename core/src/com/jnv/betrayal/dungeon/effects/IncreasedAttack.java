package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class IncreasedAttack extends Effect {
	private int attack;

	public IncreasedAttack(int attack, int turns) {
		super(ActionType.ATTACK, turns);
		isHostile = true;
	}

	@Override
	public void startEffect(Card card) {
		card.takeDamage(attack);
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}
}
