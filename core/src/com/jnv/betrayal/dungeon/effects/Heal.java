package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class Heal extends Effect {
	private int health;

	public Heal(int health) {
		super(ActionType.HEAL);
		this.health = health;
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
