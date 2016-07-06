package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Effect {
	private Card src;
	private List<Card> dest;
	private boolean consistent;
	private int turns;

	protected Effect() {
	}

	protected Effect(Card src, List<Card> dest, boolean consistent, int turns) {
		this.src = src;
		this.dest = dest;
		this.consistent = consistent;
		this.turns = turns;
	}

	public int getTurns() {
		return turns;
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

	public void setSrc(Card src) {
		this.src = src;
	}

	public void setDest(List<Card> dest) {
		this.dest = dest;
	}

	public abstract void startEffect(Card card);

	public abstract void endEffect(Card card);

	public abstract void consistentEffect(Card card);
}
