package com.jnv.betrayal.dungeon.effects.buffs;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

public class AttackUp extends Effect {
	private int attack;

	public AttackUp(int attack, int turns) {
		super(EventType.BUFF_ATTACK, turns, EventType.E_BUFF_ATTACK);
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
