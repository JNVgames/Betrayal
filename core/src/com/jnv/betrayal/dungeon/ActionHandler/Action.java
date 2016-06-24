package com.jnv.betrayal.dungeon.ActionHandler;


import com.jnv.betrayal.dungeon.cards.Card;

public abstract class Action {
	private Card src;
	private Card dest;
	private ActionType actionType;

	public Action(Card src, ActionType actionType) {
		this.src = src;
		this.actionType = actionType;
	}

	public Action(Card src, Card dest, ActionType actionType) {
		this.src = src;
		this.dest = dest;
		this.actionType = actionType;
	}

	public Card getSrc() {
		return src;
	}

	public Card getDest() {
		return dest;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public boolean destExist() {
		return dest == null;
	}
}
