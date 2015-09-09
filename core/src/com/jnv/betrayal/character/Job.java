/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.character.utils.Jobs;

public class Job {

	private int job;

	public Job() {
	}

	public Job(int job) {
		this.job = job;
	}

	// Static convenience methods
	public static String getJobInitial(int job) {
		switch (job) {
			case Jobs.WARRIOR:
				return "W";
			case Jobs.KNIGHT:
				return "K";
			case Jobs.PRIEST:
				return "P";
			case Jobs.THIEF:
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
			case Jobs.WARRIOR:
				return "Warrior";
			case Jobs.KNIGHT:
				return "Knight";
			case Jobs.PRIEST:
				return "Priest";
			case Jobs.THIEF:
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