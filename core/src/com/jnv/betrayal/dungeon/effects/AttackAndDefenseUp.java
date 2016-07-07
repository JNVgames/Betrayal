package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class AttackAndDefenseUp extends Effect {
	private int attack;
	private int defense;

	public AttackAndDefenseUp(int attack, int defense) {
		this.attack = attack;
		this.defense = defense;
	}

	public AttackAndDefenseUp(Card src, ArrayList<Card> dest, boolean consistent, int attack, int defense, int turns) {
		super(src, dest, consistent, turns);
		this.attack = attack;
		this.defense = defense;
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
