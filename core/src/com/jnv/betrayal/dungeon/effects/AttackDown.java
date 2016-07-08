package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class AttackDown extends Effect {
	private int attack;

	public AttackDown(int attack, int turns) {
		super(ActionType.DEBUFF_ATTACK, turns, ActionType.E_DEBUFF_ATTACK);
		this.attack = attack;
		isHostile = true;
	}

	@Override
	public void startEffect(Card card) {
		card.decreaseCurrentAttack(attack);
	}

	@Override
	public void endEffect(Card card) {
		card.increaseCurrentAttack(attack);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}
