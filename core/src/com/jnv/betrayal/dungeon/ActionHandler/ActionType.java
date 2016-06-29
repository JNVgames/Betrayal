package com.jnv.betrayal.dungeon.ActionHandler;

public enum ActionType {
	ATTACK("attacked"),
	DEFEND("defended"),
	ITEM("used an item"),
	FLEE("fleed"),
	DIED("died");

	private String action;

	ActionType(String action) {
		this.action = action;
	}

	public String toString() {
		return action;
	}
}
