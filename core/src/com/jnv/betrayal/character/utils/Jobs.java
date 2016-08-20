package com.jnv.betrayal.character.utils;
/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

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

	public static Jobs getNextJob(Jobs job) {
		int newSerial = (job.serial + 1) % 4;
		switch (newSerial) {
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

	public static Jobs getPrevJob(Jobs job) {
		int newSerial = (job.serial + 3) % 4;
		switch (newSerial) {
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