package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;

public class Heal extends Effect {
	private int health;

	public Heal(int health) {
		super(EventType.HEAL);
		this.health = health;
		isHostile = false;
	}

	@Override
	public void startEffect(Card card) {
		card.heal(health);
	}

	@Override
	public void endEffect(Card card) {

	}

	@Override
	public void consistentEffect(Card card) {

	}
}
