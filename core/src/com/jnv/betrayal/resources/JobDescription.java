/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.resources;

public class JobDescription {
	private static final String warriorDescription = "Warrior:" + "\n (Passive) +25% Attack" +
			"\n (Team Passive) +10% Attack" + "\n (Ability) 150% strike";
	private static final String knightDescription = "Knight:" + "\n (Passive) +25% Defense" +
			"\n (Team Passive) +10% Defense" + "\n (Ability) Grants Shield";
	private static final String priestDescription = "Priest:" + "\n (Passive) +25% Health" +
			"\n (Team Passive) +10% Health" + "\n (Ability) Buffs";
	private static final String thiefDescription = "Thief:" + "\n (Passive) +50% Gold drop rate" +
			"\n (*TP) +25% Gold drop rate" + "\n (Ability) 25% Chance to Steal " + "\n *TP is Team Passive";

	public static String getWarriorDescription() {
		return warriorDescription;
	}

	public static String getKnightDescription() {
		return knightDescription;
	}

	public static String getPriestDescription() {
		return priestDescription;
	}

	public static String getThiefDescription() {
		return thiefDescription;
	}
}
