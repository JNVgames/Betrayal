/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

public class Job {

	public static final int WARRIOR = 0;
	public static final int THIEF = 1;
	public static final int KNIGHT = 2;
	public static final int PRIEST = 3;

	private int job;

	public Job() {
	}

	public Job(int job) {
		this.job = job;
	}

	// Static convenience methods
	public static String getJobInitial(int job) {
		switch (job) {
			case Job.WARRIOR:
				return "W";
			case Job.KNIGHT:
				return "K";
			case Job.PRIEST:
				return "P";
			case Job.THIEF:
				return "T";
			default:
				return null;
		}
	}

	// Getters
	public int getJob() {
		return job;
	}

	// Setters
	public void setJob(int job) {
		this.job = job;
	}

	public String toString() {
		switch (job) {
			case WARRIOR:
				return "Warrior";
			case KNIGHT:
				return "Knight";
			case PRIEST:
				return "Priest";
			case THIEF:
				return "Thief";
			default:
				return null;
		}
	}

	public void setNextJob() {
		if (job == 0) job = 3;
		else job--;
	}

	public void setPreviousJob() {
		if (job == 3) job = 0;
		else job++;
	}
}