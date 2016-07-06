package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class AttackUp extends Effect {
	private int attack;

	public AttackUp(int attack) {
		this.attack = attack;
	}

	public AttackUp(Card src, ArrayList<Card> dest, boolean consistent, int attack, int turns) {
		super(src, dest, consistent, turns);
		this.attack = attack;
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
