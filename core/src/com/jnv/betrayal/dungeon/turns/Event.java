package com.jnv.betrayal.dungeon.turns;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

public class Event {
	private Card card;
	private int turnsLeft;
	private Effect effect;

	public Event(Card card, int turnsLeft, Effect effect) {
		this.card = card;
		this.turnsLeft = turnsLeft;
		this.effect = effect;

		throw new AssertionError("Turnsleft can't be initialized to 0. make it 1 for instant effects");
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
