package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;

public class DefenseDown extends Effect {
	private int defense;

	public DefenseDown(int defense, int turns) {
		super(EventType.DEBUFF_DEFENSE, turns, EventType.E_DEBUFF_DEFENSE);
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
