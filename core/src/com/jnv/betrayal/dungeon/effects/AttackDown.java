package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class AttackDown extends Effect {
	private int attack;

	public AttackDown(int attack) {
		this.attack = attack;
	}

	public AttackDown(Card src, ArrayList<Card> dest,boolean consistent, int attack, int turns) {
		super(src, dest, consistent, turns);
		this.attack = attack;
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
