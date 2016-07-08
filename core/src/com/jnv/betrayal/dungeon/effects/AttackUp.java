package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class AttackUp extends Effect {
	private int attack;

	public AttackUp(int attack, int turns) {
		super(ActionType.BUFF_ATTACK, turns, ActionType.E_BUFF_ATTACK);
		this.attack = attack;
		isHostile = false;
	}

	@Override
	public void startEffect(Card card) {
		card.increaseCurrentAttack(attack);
	}

	@Override
	public void endEffect(Card card) {
		card.decreaseCurrentAttack(attack);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}
