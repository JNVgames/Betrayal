/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.resources;

public class JobDescription {
	private static final String warriorDescription = "Warrior:"  +
			"\n (Team Passive) +25% Attack" + "\n (Ability) Strike a target with \nan extra 50% damage";
	private static final String knightDescription = "Knight:" +
			"\n (Team Passive) +25% Defense" + "\n (Ability) Defends two targets \n and increase Defense"
			+ "\n lasts 2 turns";
	private static final String priestDescription = "Priest:" +
			"\n (Team Passive) +25% Health" + "\n (Ability) Heal/ Buff Atk/Buff Def of \ntarget *effect (x.25 of your own stat)"
			+"\n(lasts 1 turn)";
	private static final String thiefDescription = "Thief:" +	"\n (Team Passive) +50% Gold Reward" +
			"\n (Ability) True Damage Strike \n(50% your current attack)" ;

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
