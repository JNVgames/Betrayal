/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.utils.Json;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/**
 * Holds information regarding a game character's traits
 *
 * @author Vincent Wang
 */
public class Character {

	private String name;
	public final Preview preview;
	public final Job job;
	public final Equips equips;
	public final Inventory inventory;
	public final Stats stats;

	/**
	 * Creates a character with default traits
	 */
	public Character(BetrayalAssetManager res) {
		equips = new Equips(this, res);
		preview = new Preview(this, res);
		job = new Job();
		inventory = new Inventory(res);
		stats = new Stats();

		job.setJob(Job.WARRIOR);
		update();
		//toJson();
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

	public String toJson() {
		Json json = new Json();
		return json.toJson(this);
	}
}