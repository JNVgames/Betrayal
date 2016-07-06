package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;

public class Event {
	private Card card;
	private Effect effect;
	private int turnsLeft;

	public Event(Card card, Effect effect) {
		this.card = card;
		this.effect = effect;
		turnsLeft = effect.getTurns();
	}

	public Card getCard() {
		return card;
	}

	public boolean turnIsZero() {
		return turnsLeft == 0;
	}

	public void decreaseTurns() {
		turnsLeft--;
	}

	public Effect getEffect() {
		return effect;
	}

}
