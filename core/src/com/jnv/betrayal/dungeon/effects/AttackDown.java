package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;

public class AttackDown extends Effect {
	private int attack;

	public AttackDown(int attack, int turns) {
		super(EventType.DEBUFF_ATTACK, turns, EventType.E_DEBUFF_ATTACK);
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
