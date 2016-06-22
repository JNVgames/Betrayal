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

	private int baseHealth, baseDefense, baseAttack, floor, availablePoints;
	private int equipHealth, equipAttack, equipDefense;
	private int totalHealth, totalAttack, totalDefense;
	private ApplyPoints applyPoints;
	private Equips equips;

	public Stats(Equips equips) {
		baseHealth = 25;
		baseAttack = 5;
		baseDefense = 5;
		floor = 0;
		availablePoints = 0;
		applyPoints = new ApplyPoints();
		this.equips = equips;
		updateStats();
	}

	/**
	 * Updates equip and total stats
	 */
	public void updateStats() {
		equipHealth = equipAttack = equipDefense = 0;
		for (int i = 0; i < equips.equips.length; i++) {
			if (equips.equips[i] != null) {
				equipHealth += equips.equips[i].getHealth();
				equipAttack += equips.equips[i].getHealth();
				equipDefense += equips.equips[i].getHealth();
			}
		}
		totalHealth = baseHealth + equipHealth;
		totalAttack = baseAttack + equipAttack;
		totalDefense = baseDefense + equipDefense;
	}

	// Getters
	public int getStat(Stat stat) {
		switch (stat) {
			case FLOOR:
				return floor;
			case HEALTH:
				return baseHealth;
			case ATTACK:
				return baseAttack;
			case DEFENSE:
				return baseDefense;
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
				return "Health: " + baseHealth;
			case ATTACK:
				return "Attack: " + baseAttack;
			case DEFENSE:
				return "Defense: " + baseDefense;
			default:
				throw new AssertionError("Stat doesn't exist");
		}
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public int getBaseDefense() {
		return baseDefense;
	}

	public int getBaseAttack() {
		return baseAttack;
	}

	public int getEquipHealth() {
		return equipHealth;
	}

	public int getEquipAttack() {
		return equipAttack;
	}

	public int getEquipDefense() {
		return equipDefense;
	}

	public int getTotalHealth() {
		return totalHealth;
	}

	public int getTotalAttack() {
		return totalAttack;
	}

	public int getTotalDefense() {
		return totalDefense;
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
		json.writeField(this, "baseHealth", Integer.class);
		json.writeField(this, "baseAttack", Integer.class);
		json.writeField(this, "baseDefense", Integer.class);
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

		private int tmpHealth, tmpDefense, tmpAttack, tmpAvailablePoints, tmpPointsApplied;

		/**
		 * Initializes temporary character stats to the provided character
		 */
		public ApplyPoints() {
			updateValues();
		}

		public void addHealthPoint() {
			tmpHealth++;
			tmpAvailablePoints--;
			tmpPointsApplied++;
		}

		public void decHealthPoint() {
			if (tmpHealth > baseHealth && hasPointsApplied()) {
				tmpHealth--;
				tmpAvailablePoints++;
				tmpPointsApplied--;
			}
		}

		public void addDefensePoint() {
			tmpDefense++;
			tmpAvailablePoints--;
			tmpPointsApplied++;
		}

		public void decDefensePoint() {
			if (tmpDefense > baseDefense && hasPointsApplied()) {
				tmpDefense--;
				tmpAvailablePoints++;
				tmpPointsApplied--;
			}
		}

		public void addAttackPoint() {
			tmpAttack++;
			tmpAvailablePoints--;
			tmpPointsApplied++;
		}

		public void decAttackPoint() {
			if (tmpAttack > baseAttack && hasPointsApplied()) {
				tmpAttack--;
				tmpAvailablePoints++;
				tmpPointsApplied--;
			}
		}

		public boolean hasPointsApplied(){
			return (tmpPointsApplied>0);
		}

		public boolean hasAvailablePoints() {
			return (tmpAvailablePoints > 0);
		}
		/**
		 * Sets the changes in stats to the character
		 */
		public void applyPoints() {
			baseHealth = tmpHealth;
			baseAttack = tmpAttack;
			baseDefense = tmpDefense;
			availablePoints = tmpAvailablePoints;
			tmpPointsApplied = 0;
		}

		/**
		 * Resets all stats and returns points back to available points
		 */
		public void cancel() {
			tmpAvailablePoints = availablePoints;
			tmpHealth = baseHealth;
			tmpAttack = baseAttack;
			tmpDefense = baseDefense;
			tmpPointsApplied = 0;
		}

		public void updateValues() {
			tmpHealth = baseHealth;
			tmpAttack = baseAttack;
			tmpDefense = baseDefense;
			tmpAvailablePoints = availablePoints;
			tmpPointsApplied = 0;
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
