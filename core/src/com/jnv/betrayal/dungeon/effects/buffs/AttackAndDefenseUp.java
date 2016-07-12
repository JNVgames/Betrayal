package com.jnv.betrayal.dungeon.effects.buffs;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

public class AttackAndDefenseUp extends Effect {
	private int attack;
	private int defense;

	public AttackAndDefenseUp(int attack, int defense, int turns) {
		super(EventType.BUFF_ATTACK_DEFENSE, turns, EventType.E_BUFF_ATTACK_DEFENSE);
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
