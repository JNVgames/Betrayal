package com.jnv.betrayal.dungeon.ActionHandler;


import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.cards.PlayerCard;

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
		this(src, actionType);
		this.dest = dest;
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

	@Override
	public String toString() {
		String event = "";
		event += src.getName();
		event += " " + actionType.toString() + " ";
		if (destExist()) {
			for (int i = 0; i < dest.size(); i++) {
				event += dest.get(i).getName();
				if(i< dest.size()-1)
					event += ", ";
			}
		}
		return event;
	}
}
