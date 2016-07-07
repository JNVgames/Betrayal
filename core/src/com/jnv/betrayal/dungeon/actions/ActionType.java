package com.jnv.betrayal.dungeon.actions;

public enum ActionType {
	ATTACK("attacked"),
	DEFEND("defended"),
	ITEM("used an item"),
	FLEE("fled"),
	FAIL_TO_FLEE("failed to flee"),
	DIED("died");

	private String action;

	ActionType(String action) {
		this.action = action;
	}

	public String toString() {
		return action;
	}
}
