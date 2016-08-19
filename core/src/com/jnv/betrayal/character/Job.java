package com.jnv.betrayal.character;

import com.jnv.betrayal.character.utils.Jobs;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

/**
 * Manages a character's job. Mainly used in character selection
 */
public class Job {

	private Jobs job;

	public Job() {
		// Default job
		job = Jobs.WARRIOR;
	}

	public Job(Jobs job) {
		this.job = job;
	}

	// Getters
	public Jobs getJob() {
		return job;
	}

	public void setNextJob() {
		job = job.getNextJob();
	}

	public void setPreviousJob() {
		job = job.getPrevJob();
	}

	public void setJob(String job) {
		if (job.equals(Jobs.WARRIOR.toString())) {
			this.job = Jobs.WARRIOR;
		} else if (job.equals(Jobs.KNIGHT.toString())) {
			this.job = Jobs.KNIGHT;
		} else if (job.equals(Jobs.PRIEST.toString())) {
			this.job = Jobs.PRIEST;
		} else if (job.equals(Jobs.THIEF.toString())) {
			this.job = Jobs.THIEF;
		}
	}

	@Override
	public String toString() {
		return job.toString();
	}
}