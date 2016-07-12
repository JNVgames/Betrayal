package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;

public class DefenseUp extends Effect {
	private int defense;

	public DefenseUp(int defense, int turns) {
		super(EventType.BUFF_DEFENSE, turns, EventType.E_BUFF_DEFENSE);
		this.defense = defense;
		isHostile = false;
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
