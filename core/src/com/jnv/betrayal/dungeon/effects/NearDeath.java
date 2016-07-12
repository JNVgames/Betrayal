package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;

public class NearDeath extends Effect {
	private int attack;

	public NearDeath(int attack, int turns) {
		super(EventType.ATTACK, turns);
		isHostile = true;
	}

	@Override
	public void startEffect(Card card) {
		card.takeTrueDamage(card.getCurrentHealth()-1);
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}
}
