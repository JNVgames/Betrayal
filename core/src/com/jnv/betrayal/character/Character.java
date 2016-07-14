/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.utils.Json;
import com.jnv.betrayal.character.utils.Gender;
import com.jnv.betrayal.character.utils.Trait;
import com.jnv.betrayal.online.JsonSerializable;
import com.jnv.betrayal.online.Room;
import com.jnv.betrayal.resources.BetrayalAssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Holds information regarding a game character's traits
 */
public class Character implements JsonSerializable {

	private Room room;
	public final Preview preview;
	public final Job job;
	public final Equips equips;
	public final Inventory inventory;
	public final Stats stats;
	private int id;
	private String name;

	/**
	 * Creates a character with default traits
	 */
	public Character(BetrayalAssetManager res) {
		id = generateRandomID();

		inventory = new Inventory();
		equips = new Equips(inventory, res);
		preview = new Preview(equips, res);
		job = new Job();
		stats = new Stats(equips);
		equips.setStats(stats);

		preview.update();
	}

	// TODO REMOVE THIS WE WILL NOT BE USING THIS WE WILL USE ACCOUNT ID
	private static int generateRandomID() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	public String getTrait(Trait trait) {
		switch (trait) {
			case GENDER:
				return preview.gender.getInitial();
			case HAIR_STYLE:
				if (preview.gender == Gender.MALE) return Integer.toString(preview.maleHair);
				else return Integer.toString(preview.femaleHair);
			case HAIR_COLOR:
				if (preview.gender == Gender.MALE) return Integer.toString(preview.hairColor);
				else return Integer.toString(preview.hairColor);
			case JOB:
				return job.getJob().getInitial();
			default:
				return null;
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toJson() {
		Json json = new Json();
		return json.prettyPrint(this);
	}

	// Json methods
	public void write(JSONObject json) {
		try {
			json.put("id", id);
			json.put("job", job.toString());

			JSONObject previewJson = new JSONObject();
			preview.write(previewJson);
			json.put("preview", previewJson);

			JSONObject equipsJson = new JSONObject();
			equips.write(equipsJson);
			json.put("equips", equipsJson);

			JSONObject invJson = new JSONObject();
			inventory.write(invJson);
			json.put("inventory", invJson);

			JSONObject statsJson = new JSONObject();
			stats.write(statsJson);
			json.put("stats", statsJson);
		} catch (JSONException e) {
			System.out.println(e);
		}
	}

	public void read(JSONObject json) {

	}

	public void setNextTrait(Trait trait) {
		switch (trait) {
			case GENDER:
				preview.gender = preview.gender.getOtherGender();
				preview.update();
				break;
			case HAIR_STYLE:
				if (preview.gender == Gender.MALE) {
					preview.maleHair = preview.maleHair % 5 + 1;
				} else {
					preview.femaleHair = preview.femaleHair % 5 + 1;
				}
				preview.update();
				break;
			case HAIR_COLOR:
				preview.hairColor = preview.hairColor % 7 + 1;
				preview.update();
				break;
			case JOB:
				job.setNextJob();
			default:
				break;
		}
	}

	public void setPreviousTrait(Trait trait) {
		switch (trait) {
			case GENDER:
				preview.gender = preview.gender.getOtherGender();
				preview.update();
				break;
			case HAIR_STYLE:
				if (preview.gender == Gender.MALE) {
					preview.maleHair = (preview.maleHair + 3) % 5 + 1;
				} else {
					preview.femaleHair = (preview.femaleHair + 3) % 5 + 1;
				}
				preview.update();
				break;
			case HAIR_COLOR:
				if (preview.hairColor == 1) preview.hairColor = 7;
				else preview.hairColor--;
				preview.update();
				break;
			case JOB:
				job.setPreviousJob();
			default:
				break;
		}
	}
}