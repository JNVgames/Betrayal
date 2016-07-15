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
	public final BetrayalAssetManager res;
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

		this.res = res;
		inventory = new Inventory();
		equips = new Equips(inventory, res);
		preview = new Preview(equips, res);
		job = new Job();
		stats = new Stats(equips);
		equips.setStats(stats);

		preview.update();
	}

	private static int generateRandomID() {
		Random random = new Random();
		return random.nextInt(89999999) + 10000000;
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

	// Json methods
	@Override
	public JSONObject toJson() {
		JSONObject data = new JSONObject();
		try {
			data.put("id", id);
			data.put("job", job.toString());
			data.put("preview", preview.toJson());
			data.put("equips", equips.toJson());
			data.put("stats", stats.toJson());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void fromJson(JSONObject data) {
		try {
			id = data.getInt("id");
			job.setJob(data.getString("job"));
			preview.fromJson(data.getJSONObject("preview"));
			equips.fromJson(data.getJSONObject("equips"));
			stats.fromJson(data.getJSONObject("stats"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}