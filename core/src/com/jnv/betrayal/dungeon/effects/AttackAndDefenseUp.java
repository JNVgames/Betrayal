package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class AttackAndDefenseUp extends Effect {
	private int attack;
	private int defense;

	public AttackAndDefenseUp(int attack, int defense, int turns) {
		super(ActionType.BUFF_ATTACK_DEFENSE, turns, ActionType.E_BUFF_ATTACK_DEFENSE);
		this.attack = attack;
		this.defense = defense;
		isHostile = false;
	}

	@Override
	public void startEffect(Card card) {
		card.increaseCurrentAttack(attack);
		card.increaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card card) {
		card.decreaseCurrentAttack(attack);
		card.decreaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}
