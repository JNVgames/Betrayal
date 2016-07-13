package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.List;

public abstract class Effect {

	protected Card src;
	protected List<Card> dest;
	protected boolean consistent;
	protected int turns = 1;
	protected final EventType startType, consistentType, endType;
	protected boolean isHostile;
	protected String description;

	protected Effect(EventType startType) {
		this(startType, false, null, 0, null);
	}

	protected Effect(EventType startType, int turns) {
		this(startType, false, null, turns, null);
	}

	protected Effect(EventType startType, int turns, EventType endType) {
		this(startType, false, null, turns, endType);
	}

	protected Effect(EventType startType, int turns, boolean consistent, EventType consistentType,
					 EventType endType) {
		this(startType, consistent, consistentType, turns, endType);
	}

	protected Effect(EventType startType, boolean consistent, EventType consistentType, int turns,
					 EventType endType) {
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

	public EventType getStartType() {
		return startType;
	}

	public EventType getConsistentType() {
		return consistentType;
	}

	public EventType getEndType() {
		return endType;
	}

	public Card getSrc() {
		return src;
	}

	public List<Card> getDest() {
		return dest;
	}

	public String getDescription() {
		return description;
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
