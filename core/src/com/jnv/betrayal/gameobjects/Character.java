/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.character.Equips;
import com.jnv.betrayal.character.Inventory;
import com.jnv.betrayal.character.Job;
import com.jnv.betrayal.character.Preview;
import com.jnv.betrayal.character.Stats;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/**
 * Holds information regarding a game character's traits
 *
 * @author Vincent Wang
 */
public class Character {

	private String name;
	private Preview preview;
	private Job job;
	private Equips equips;
	private Inventory inventory;
	private Stats stats;
	/**
	 * Creates character with default values
	 */
	public Character(BetrayalAssetManager res) {
		equips = new Equips(this, res);
		preview = new Preview(this, res);
		job = new Job();
		inventory = new Inventory(res);
		stats = new Stats();

		job.setJob(Job.WARRIOR);
		update();
	}

	public void createCharacter(int job) {
		this.job.setJob(job);
	}

	private void update() {
		preview.update();
	}

	public void saveInfo() {

	}

	// Getters
	public String getName() {
		return name;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public Preview getPreview() {
		return preview;
	}

	public Job getJob() {
		return job;
	}

	public Equips getEquips() {
		return equips;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Stats getStats() {
		return stats;
	}

	public enum Trait {
		GENDER,
		HAIR_STYLE,
		HAIR_COLOR,
		JOB
	}
}