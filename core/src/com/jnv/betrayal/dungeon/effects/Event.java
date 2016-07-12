package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;

import java.util.List;

public class Event {
	private Effect effect;
	private int turnsLeft;

	public Event(Effect effect) {
		this.effect = effect;
		turnsLeft = effect.getTurns();
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

	public Card getSrc() {
		return effect.getSrc();
	}

	public List<Card> getDest() {
		return effect.getDest();
	}
}
