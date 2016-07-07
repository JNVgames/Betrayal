package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class Bomb extends Effect {
	private int attack;

	public Bomb(int attack, int turns) {
		super(ActionType.BOMB, turns);
		this.attack = attack;
	}

	@Override
	public void startEffect(Card card) {

	}

	@Override
	public void endEffect(Card card) {
		card.takeDamage(attack);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}
