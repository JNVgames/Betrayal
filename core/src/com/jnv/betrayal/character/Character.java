/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.utils.Gender;
import com.jnv.betrayal.character.utils.Trait;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/**
 * Holds information regarding a game character's traits
 */
public class Character implements Json.Serializable {

	private int characterID;
	public final Preview preview;
	public final Job job;
	public final Equips equips;
	public final Inventory inventory;
	public final Stats stats;

	/**
	 * Creates a character with default traits
	 */
	public Character(BetrayalAssetManager res) {
		inventory = new Inventory();
		equips = new Equips(inventory, res);
		preview = new Preview(equips, res);
		job = new Job();
		stats = new Stats(equips);
		equips.setStats(stats);

		preview.update();
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

	public int getCharacterID() {
		return characterID;
	}

	public String toJson() {
		Json json = new Json();
		return json.prettyPrint(this);
	}

	// Json methods
	public void write(Json json) {
		json.writeField(this, "characterID", Integer.class);
		json.writeField(job, "job", Job.class);
		preview.write(json);
		equips.write(json);
		inventory.write(json);
		stats.write(json);
	}

	public void read(Json json, JsonValue jsonData) {

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