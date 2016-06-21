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
	private ApplyPoints applyPoints;

	public Stats() {
		health = 25;
		attack = 5;
		defense = 5;
		floor = 0;
		availablePoints = 0;
		applyPoints = new ApplyPoints();
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
		availablePoints += 5;
		applyPoints.updateValues();
	}

	public int getFloor() {
		return floor;
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

	public ApplyPoints getApplyPointsObject() {
		return applyPoints;
	}

	/**
	 * Provides methods for applying points to character stats.
	 */
	public class ApplyPoints {

		private int tmpHealth, tmpDefense, tmpAttack, tmpAvailablePoints;

		/**
		 * Initializes temporary character stats to the provided character
		 */
		public ApplyPoints() {
			updateValues();
		}

		public void addHealthPoint() {
			tmpHealth++;
			tmpAvailablePoints--;
		}

		public void decHealthPoint() {
			if (tmpHealth > health) {
				tmpHealth--;
				tmpAvailablePoints++;
			}
		}

		public void addDefensePoint() {
			tmpDefense++;
			tmpAvailablePoints--;
		}

		public void decDefensePoint() {
			if (tmpDefense > defense) {
				tmpDefense--;
				tmpAvailablePoints++;
			}
		}

		public void addAttackPoint() {
			tmpAttack++;
			tmpAvailablePoints--;
		}

		public void decAttackPoint() {
			if (tmpAttack > attack) {
				tmpAttack--;
				tmpAvailablePoints++;
			}
		}

		public boolean hasAvailablePoints() {
			return (tmpAvailablePoints > 0);
		}
		/**
		 * Sets the changes in stats to the character
		 */
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
			tmpAvailablePoints = availablePoints;
			tmpHealth = health;
			tmpAttack = attack;
			tmpDefense = defense;
		}

		public void updateValues() {
			tmpHealth = health;
			tmpAttack = attack;
			tmpDefense = defense;
			tmpAvailablePoints = availablePoints;
		}

		// Getters

		/**
		 * Use these methods to constantly get the correct stats values for display
		 * @return returns requested stats
		 */
		public int getHealth() {
			return tmpHealth;
		}

		public int getDefense() {
			return tmpDefense;
		}

		public int getAttack() {
			return tmpAttack;
		}

		public int getAvailablePoints() {
			return tmpAvailablePoints;
		}
	}
}
