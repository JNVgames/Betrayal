/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.online.JsonSerializable;
import com.jnv.betrayal.popup.OKPopup;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Manages a character's stats
 */
public class Stats implements JsonSerializable {

	private int baseHealth, baseDefense, baseAttack, floor, availablePoints;
	private int equipHealth, equipAttack, equipDefense;
	private int totalHealth, totalAttack, totalDefense;
	private ApplyPoints applyPoints;
	private Equips equips;

	public Stats(Equips equips) {
		baseHealth = 50;
		baseAttack = 10;
		baseDefense = 10;
		System.out.println("************************************");
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
				equipAttack += equips.equips[i].getAttack();
				equipDefense += equips.equips[i].getDefense();
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
	public OKPopup advanceFloor(Betrayal game) {
		OKPopup extraPointsNotif;
		if (floor >= 25) return null;
		floor++; //todo add back delete other

		int extraPoints = 0, extraGold = 0;
		switch (floor) {
			case 6:
				extraPoints = 5;
				extraGold = 500;
				break;
			case 11:
				extraPoints = 5;
				extraGold = 1000;
				break;
			case 16:
				extraPoints = 5;
				extraGold = 1500;
				break;
			case 21:
				extraPoints = 5;
				extraGold = 2000;
				break;
			default:
				break;
		}
		availablePoints += 3 + extraPoints;
		game.getCurrentCharacter().inventory.addGold(extraGold);
		if (extraPoints > 0 || extraGold > 0) {
			extraPointsNotif = new OKPopup(Betrayal.WIDTH - 300,
					Betrayal.HEIGHT - 800,
					game,
					"Congrats!\nYou get an extra: \n" + extraPoints + " stat points\nand\n" + extraGold + " gold",
					false
			);
		} else {
			extraPointsNotif = null;
		}
		applyPoints.updateValues();

		// Won the game
		if (floor == 25) {
			game.fools.add(game.getCurrentCharacter());
			game.gsm.setState(GameStateManager.State.HALL_OF_FAME);
			new OKPopup(450, Betrayal.HEIGHT - 1000, game, "You beat the game!\nYou can keep playing but\nyou can no longer level up");
		}
		return extraPointsNotif;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public ApplyPoints getApplyPointsObject() {
		return applyPoints;
	}

	@Override
	public JSONObject toJson() {
		JSONObject data = new JSONObject();
		try {
			data.put("baseHealth", baseHealth);
			data.put("baseAttack", baseAttack);
			data.put("baseDefense", baseDefense);
			data.put("equipHealth", equipHealth);
			data.put("equipAttack", equipAttack);
			data.put("equipDefense", equipDefense);
			data.put("totalHealth", totalHealth);
			data.put("totalAttack", totalAttack);
			data.put("totalDefense", totalDefense);
			data.put("floor", floor);
			data.put("availablePoints", availablePoints);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void fromJson(JSONObject data) {
		try {
			baseHealth = data.getInt("baseHealth");
			baseAttack = data.getInt("baseAttack");
			baseDefense = data.getInt("baseDefense");
			equipHealth = data.getInt("equipHealth");
			equipAttack = data.getInt("equipAttack");
			equipDefense = data.getInt("equipDefense");
			totalHealth = data.getInt("totalHealth");
			totalAttack = data.getInt("totalAttack");
			totalDefense = data.getInt("totalDefense");
			floor = data.getInt("floor");
			availablePoints = data.getInt("availablePoints");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		applyPoints.updateValues();
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
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

		public boolean hasPointsApplied() {
			return (tmpPointsApplied > 0);
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
		 *
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
