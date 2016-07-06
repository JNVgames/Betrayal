package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class DefenseDown extends Effect {
	private int defense;

	public DefenseDown(int defense) {
		this.defense = defense;
	}

	public DefenseDown(Card src, ArrayList<Card> dest, boolean consistent, int defense, int turns) {
		super(src, dest, consistent, turns);
		this.defense = defense;
	}

	@Override
	public void startEffect(Card card) {
		card.decreaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card card) {
		card.increaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}
