package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import java.util.List;

public class Attack extends Effect {
	private int attack;

	public Attack(Card src, List<Card> dest) {
		super(EventType.ATTACK);
		isHostile = false;
		attack = src.getCurrentAttack();
		this.src = src;
		this.dest = dest;
	}

	@Override
	public void startEffect(Card card) {
		card.takeDamage(attack);
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}
}
