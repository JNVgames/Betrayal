package com.jnv.betrayal.dungeon.actions;

public enum EventType {
	ATTACK("attacked"),
	DEFEND("defended"),
	FLEE("fled"),
	FAIL_TO_FLEE("failed to flee"),
	DIED("died"),

	// Item effects
	HEAL("healed"),
	BOMB("bombed"),
	POISON("poisoned"),
	BUFF_ATTACK("buffed (atk)"),
	BUFF_DEFENSE("buffed (def)"),
	BUFF_ATTACK_DEFENSE("buffed (atk, def)"),
	DEBUFF_ATTACK("debuffed (atk)"),
	DEBUFF_DEFENSE("debuffed (def)"),
	DEBUFF_ATTACK_DEFENSE("debuffed (atk, def)"),
	//SKIP_TURN("skipped")

	// Consistent effects
	C_POISON("dealt poison dmg to"),

	// End effects
	E_POISON("was freed from the poison of"),
	E_BUFF_ATTACK("lost their buff (atk) from"),
	E_BUFF_DEFENSE("lost their buff (def) from"),
	E_BUFF_ATTACK_DEFENSE("lost their buff (atk, def) from"),
	E_DEBUFF_ATTACK("regained stats (atk) from debuff of"),
	E_DEBUFF_DEFENSE("regained stats (def) from debuff of"),
	E_DEBUFF_ATTACK_DEFENSE("regained stats (atk, def) from debuff of");

	private String action, consistentAction;

	EventType(String action) {
		this.action = action;
	}

	public String toString() {
		return action;
	}
}
