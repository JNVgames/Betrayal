package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

public class DefenseDown extends Effect {
	private int defense;

	public DefenseDown(int defense, int turns) {
		super(ActionType.DEBUFF_DEFENSE, turns, ActionType.E_DEBUFF_DEFENSE);
		this.defense = defense;
		isHostile = true;
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
