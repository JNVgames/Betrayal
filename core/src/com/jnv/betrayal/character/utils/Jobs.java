/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character.utils;
//
//public final class Jobs {
//	public static final int WARRIOR = 0;
//	public static final int THIEF = 1;
//	public static final int KNIGHT = 2;
//	public static final int PRIEST = 3;
//}

public enum Jobs {
	WARRIOR(0, "W", "Warrior"),
	THIEF(1, "T", "Thief"),
	KNIGHT(2, "K", "Knight"),
	PRIEST(3, "P", "Priest");

	private int serial;
	private String initial, name;

	Jobs(int serial, String initial, String name) {
		this.serial = serial;
		this.initial = initial;
		this.name = name;
	}

	public int getSerial() {
		return serial;
	}

	public String getInitial() {
		return initial;
	}

	public String toString() {
		return name;
	}

	public Jobs getNextJob() {
		serial = (serial + 1) % 4;
		switch (serial) {
			case 0:
				return WARRIOR;
			case 1:
				return THIEF;
			case 2:
				return KNIGHT;
			case 3:
				return PRIEST;
			default:
				throw new AssertionError("Jobs Enum: getNextJob() miscalculation");
		}
	}

	public Jobs getPrevJob() {
		serial = (serial + 3) % 4;
		switch (serial) {
			case 0:
				return WARRIOR;
			case 1:
				return THIEF;
			case 2:
				return KNIGHT;
			case 3:
				return PRIEST;
			default:
				throw new AssertionError("Jobs Enum: getNextJob() miscalculation");
		}
	}
}