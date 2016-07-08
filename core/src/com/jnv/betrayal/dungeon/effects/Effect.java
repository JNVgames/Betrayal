package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.List;

public abstract class Effect {
	private Card src;
	private List<Card> dest;
	private boolean consistent;
	private int turns;
	private final ActionType startType, consistentType, endType;
	protected boolean isHostile;

	protected Effect(ActionType startType) {
		this(startType, false, null, 0, null);
	}

	protected Effect(ActionType startType, int turns) {
		this(startType, false, null, turns, null);
	}

	protected Effect(ActionType startType, int turns, ActionType endType) {
		this(startType, false, null, turns, endType);
	}

	protected Effect(ActionType startType, int turns, boolean consistent, ActionType consistentType,
					 ActionType endType) {
		this(startType, consistent, consistentType, turns, endType);
	}

	protected Effect(ActionType startType, boolean consistent, ActionType consistentType, int turns,
					 ActionType endType) {
		this.startType = startType;
		this.consistentType = consistentType;
		this.endType = endType;
		this.consistent = consistent;
		this.turns = turns;
	}

	public boolean isHostile() {
		return isHostile;
	}

	public int getTurns() {
		return turns;
	}

	public ActionType getStartType() {
		return startType;
	}

	public ActionType getConsistentType() {
		return consistentType;
	}

	public ActionType getEndType() {
		return endType;
	}

	public Card getSrc() {
		return src;
	}

	public List<Card> getDest() {
		return dest;
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
