package com.jnv.betrayal.dungeon.effects.debuffs;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

public class AttackAndDefenseDown extends Effect {
	private int attack;
	private int defense;

	public AttackAndDefenseDown(int attack, int defense, int turns) {
		super(EventType.BUFF_ATTACK_DEFENSE, turns, EventType.E_DEBUFF_ATTACK_DEFENSE);
		this.attack = attack;
		this.defense = defense;
		isHostile = true;
		description = "Attack and Defense Debuff\nLowers your attack by " + attack + "\n"
				+ "and defense by " + defense + "\n" + "for " + turns + " turns.";
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
