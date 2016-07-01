package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public abstract class Effect {
	private Card src;
	private ArrayList<Card> dest;
	private boolean consistent;

	protected Effect() {
	}

	protected Effect(Card src, ArrayList<Card> dest, boolean consistent) {
		this.src = src;
		this.dest = dest;
		this.consistent = consistent;
	}

	public void doStartEffect() {
		for (Card card : dest) {
			startEffect(card);
		}
	}

	public void doEndEffect() {
		for (Card card : dest) {
			endEffect(card);
		}
	}

	public void doConsistentEffect() {
		for (Card card : dest) {
			consistentEffect(card);
		}
	}

	public boolean isConsistent() {
		return consistent;
	}

	public void setConsistent(boolean consistent) {
		this.consistent = consistent;
	}

	public abstract void startEffect(Card card);

	public abstract void endEffect(Card card);

	public abstract void consistentEffect(Card card);
}
