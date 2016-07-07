package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class DefenseUp extends Effect {
	private int defense;

	public DefenseUp(int defense, int turns) {
		super(ActionType.BUFF_DEFENSE, turns, ActionType.E_BUFF_DEFENSE);
		this.defense = defense;
	}

	@Override
	public void startEffect(Card card) {
		card.increaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card card) {
		card.decreaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}
