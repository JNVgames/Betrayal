/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.utils.Stat;

/**
 * Manages a character's stats
 */
public class Stats implements Json.Serializable {

	private int health, defense, attack, floor, availablePoints;

	public Stats() {
		health = 25;
		attack = 5;
		defense = 5;
		floor = 0;
		availablePoints = 0;
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

	public int getAvailablePoints() {
		return availablePoints;
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
	/**
	 * Called when the character levels up
	 */
	public void advanceFloor() {
		floor++;
		availablePoints += 3;
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

	public class ApplyPoints {

		private int tmpHealth, tmpDefense, tmpAttack, tmpAvailablePoints;

		public ApplyPoints() {
			tmpHealth = health;
			tmpAttack = attack;
			tmpDefense = defense;
			tmpAvailablePoints = availablePoints;
		}

		public void addHealthPoint() {
			tmpHealth++;
			tmpAvailablePoints--;
		}

		public void subtractHealthPoint() {
			tmpHealth--;
			tmpAvailablePoints++;
		}

		public void addDefensePoint() {
			tmpDefense++;
			tmpAvailablePoints--;
		}

		public void subtractDefensePoint() {
			tmpDefense--;
			tmpAvailablePoints++;
		}

		public void addAttackPoint() {
			tmpAttack++;
			tmpAvailablePoints--;
		}

		public void subtractAttackPoint() {
			tmpAttack--;
			tmpAvailablePoints++;
		}

		public void applyPoints() {
			health = tmpHealth;
			attack = tmpAttack;
			defense = tmpDefense;
			availablePoints = tmpAvailablePoints;
		}

		/**
		 * Resets all stats and returns points back to available points
		 */
		public void cancel() {
			tmpAvailablePoints += tmpHealth - health;
			tmpAvailablePoints += tmpAttack - attack;
			tmpAvailablePoints += tmpDefense - defense;
			tmpHealth = health;
			tmpAttack = attack;
			tmpDefense = defense;
		}

		// Getters
		public int getTmpHealth() {
			return tmpHealth;
		}

		public int getTmpDefense() {
			return tmpDefense;
		}

		public int getTmpAttack() {
			return tmpAttack;
		}

		public int getTmpAvailablePoints() {
			return tmpAvailablePoints;
		}
	}
}
