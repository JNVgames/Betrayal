/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.utils.Stat;

public class Stats implements Json.Serializable {

	private int health, defense, attack, floor;

	public Stats() {
		health = 25;
		defense = 5;
		attack = 5;
		floor = 0;
	}

	// Getters
	public int getStat(Stat stat) {
		switch (stat) {
			case FLOOR:
				return floor;
			case HEALTH:
				return health;
			case ATTACK:
				return attack;
			case DEFENSE:
				return defense;
			default:
				throw new AssertionError("Stat doesn't exist");
		}
	}

	public String toString(Stat stat) {
		switch (stat) {
			case FLOOR:
				return "Floor: " + floor;
			case HEALTH:
				return "Health: " + health;
			case ATTACK:
				return "Attack: " + attack;
			case DEFENSE:
				return "Defense: " + defense;
			default:
				throw new AssertionError("Stat doesn't exist");
		}
	}

	// Setters
	public void advanceFloor() {
		floor++;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void write(Json json) {
		json.writeObjectStart("stats");
		json.writeField(this, "health", Integer.class);
		json.writeField(this, "defense", Integer.class);
		json.writeField(this, "attack", Integer.class);
		json.writeField(this, "floor", Integer.class);
		json.writeObjectEnd();
	}

	public void read(Json json, JsonValue jsonData) {

	}
}
