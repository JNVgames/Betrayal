package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public abstract class Effect {
	private Card src;
	private ArrayList<Card> dest;

	protected Effect() {
	}

	protected Effect(Card src, ArrayList<Card> dest) {
		this.src = src;
		this.dest = dest;
	}

	public void doStartEffect() {
		for (Card card : dest) {
			startEffect(card);
		}
	}

	public abstract void startEffect(Card card);

	public abstract void endEffect(Card card);
}
