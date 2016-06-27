package com.jnv.betrayal.dungeon.ActionHandler;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.List;

public class Action {
	private Card src;
	private List<Card> dest;
	private ActionType actionType;

	public Action(Card src, ActionType actionType) {
		this.src = src;
		this.actionType = actionType;
	}

	public Action(Card src, List<Card> dest, ActionType actionType) {
		this.src = src;
		this.dest = dest;
		this.actionType = actionType;
	}

	public Card getSrc() {
		return src;
	}

	public List<Card> getDest() {
		return dest;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public boolean destExist() {
		return dest == null || dest.size() != 0;
	}
}
