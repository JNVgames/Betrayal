package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

public class AttackAndDefenseDown extends Effect {
	private int attack;
	private int defense;

	public AttackAndDefenseDown(int attack, int defense, int turns) {
		super(ActionType.BUFF_ATTACK_DEFENSE, turns, ActionType.E_DEBUFF_ATTACK_DEFENSE);
		this.attack = attack;
		this.defense = defense;
		isHostile = true;
	}

	@Override
	public void startEffect(Card card) {
		card.decreaseCurrentAttack(attack);
		card.decreaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card card) {
		card.increaseCurrentAttack(attack);
		card.increaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}
