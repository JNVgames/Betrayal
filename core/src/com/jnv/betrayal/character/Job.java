/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.character.utils.Jobs;

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
}