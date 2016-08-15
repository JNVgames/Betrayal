package com.jnv.betrayal.dungeon.effects;

public enum EventType {
	NONE(),
	ATTACK("attacked"),
	DEFEND("defended"),
	FLEE("fled", false),
	FAIL_TO_FLEE("failed to flee", false),
	DIED("died", false),

	// Class special attacks
	WARRIOR_SPECIAL("critically smacked"),
	PRIEST_ATTACK_SPECIAL("reluctantly buffed (atk)"),
	PRIEST_DEFENSE_SPECIAL("reluctantly buffed (def)"),
	PRIEST_HEAL_SPECIAL("reluctantly healed"),
	THIEF_SPECIAL("sinisterly backstabbed"),
	KNIGHT_SPECIAL("bro-tected"),

	// Item effects
	HEAL("healed", false),
	BOMB("bombed"),
	POISON("poisoned"),
	BUFF_ATTACK("buffed (atk)"),
	BUFF_DEFENSE("buffed (def)"),
	BUFF_ATTACK_DEFENSE("buffed (atk, def)"),
	DEBUFF_ATTACK("debuffed (atk)"),
	DEBUFF_DEFENSE("debuffed (def)"),
	DEBUFF_ATTACK_DEFENSE("debuffed (atk, def)"),
	RUN("used a run item", false),
	SKIP_TURN(),

	// Consistent effects
	C_POISON("was poisoned", false, true, false),

	// End effects
	E_POISON("is no longer poisoned", false),
	E_BUFF_ATTACK("lost their buff (atk)", false),
	E_BUFF_DEFENSE("lost their buff (def)", false),
	E_BUFF_ATTACK_DEFENSE("lost their buff (atk, def)", false),
	E_DEBUFF_ATTACK("is no longer debuffed (atk)", false),
	E_DEBUFF_DEFENSE("is no longer debuffed (def)", false),
	E_DEBUFF_ATTACK_DEFENSE("is no longer debuffed (atk, def)", false),
	E_BOMB("Bomb exploded on", true, false, false);

	private String action;
	private boolean showSrc, showDest, showInEventLog, showDestFirst;

	EventType() {
		showInEventLog = false;
	}

	EventType(String action) {
		this(action, true, false, true);
	}

	EventType(String action, boolean showDest) {
		this(action, showDest, false, true);
	}

	EventType(String action, boolean showDest, boolean showDestFirst, boolean showSrc) {
		this.action = action;
		this.showDest = showDest;
		this.showDestFirst = showDestFirst;
		this.showSrc = showSrc;
		showInEventLog = true;
	}

	public String getActionString() {
		return action;
	}

	public boolean showDestination() {
		return showDest;
	}

	public boolean showInEventLog() {
		return showInEventLog;
	}

	public boolean showDestFirst() {
		return showDestFirst;
	}

	public boolean showSrc() {
		return showSrc;
	}
}
