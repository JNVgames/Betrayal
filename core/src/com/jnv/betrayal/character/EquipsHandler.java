/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.character.utils.Slot;
import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.HeadGear;
import com.jnv.betrayal.gameobjects.Ring;
import com.jnv.betrayal.gameobjects.Shield;
import com.jnv.betrayal.gameobjects.Weapon;

/**
 * Helpers for character equips class
 */
class EquipsHandler {

	private Equips equips;

	EquipsHandler(Equips equips) {
		this.equips = equips;
	}

	void equipWeapon(Weapon weapon) {
		equips.character.inventory.removeItem(weapon);
		if (equips.equips[Slot.WEAPON1] == null) {
			equips.equips[Slot.WEAPON1] = weapon;
		} else if (equips.equips[Slot.WEAPON2] == null) {
			equips.equips[Slot.WEAPON2] = weapon;
		} else {
			equips.character.inventory.addItem(equips.equips[Slot.WEAPON2]);
			equips.equips[Slot.WEAPON2] = weapon;
		}
	}

	void equipHeadArmor(HeadGear gear) {
		equips.character.inventory.removeItem(gear);
		if (!(equips.equips[Slot.HEAD] == null))
			equips.character.inventory.addItem(equips.equips[Slot.HEAD]);
		equips.equips[Slot.HEAD] = gear;
	}

	void equipBodyArmor(BodyArmor armor) {
		equips.character.inventory.removeItem(armor);
		if (!(equips.equips[Slot.BODY] == null))
			equips.character.inventory.addItem(equips.equips[Slot.BODY]);
		equips.equips[Slot.BODY] = armor;
	}

	void equipShield(Shield shield) {
		equips.character.inventory.removeItem(shield);
		if (!(equips.equips[Slot.SHIELD] == null))
			equips.character.inventory.addItem(equips.equips[Slot.SHIELD]);
		equips.equips[Slot.SHIELD] = shield;
	}

	void equipRing(Ring ring) {
		equips.character.inventory.removeItem(ring);
		if (equips.equips[Slot.RING1] == null) {
			equips.equips[Slot.RING1] = ring;
		} else if (equips.equips[Slot.RING2] == null) {
			equips.equips[Slot.RING2] = ring;
		} else {
			equips.character.inventory.addItem(equips.equips[Slot.RING2]);
			equips.equips[Slot.RING2] = ring;
		}
	}

	boolean unequipHeadArmor() {
		if (!(equips.equips[Slot.HEAD] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[Slot.HEAD]);
			equips.equips[Slot.HEAD] = null;
			return true;
		}
		return false;
	}

	boolean unequipWeapon1() {
		if (!(equips.equips[Slot.WEAPON1] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[Slot.WEAPON1]);
			equips.equips[Slot.WEAPON1] = null;
			return true;
		}
		return false;
	}

	boolean unequipWeapon2() {
		if (!(equips.equips[Slot.WEAPON2] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[Slot.WEAPON2]);
			equips.equips[Slot.WEAPON2] = null;
			return true;
		}
		return false;
	}

	boolean unequipBodyArmor() {
		if (!(equips.equips[Slot.BODY] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[Slot.BODY]);
			equips.equips[Slot.BODY] = null;
			return true;
		}
		return false;
	}

	boolean unequipShield() {
		if (!(equips.equips[Slot.SHIELD] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[Slot.SHIELD]);
			equips.equips[Slot.SHIELD] = null;
			return true;
		}
		return false;
	}

	boolean unequipRing1() {
		if (!(equips.equips[Slot.RING1] == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[Slot.RING1]);
			equips.equips[Slot.RING1] = null;
			return true;
		}
		return false;
	}

	boolean unequipRing2() {
		if (!(equips.equips[Slot.RING2] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[Slot.RING2]);
			equips.equips[Slot.RING2] = null;
			return true;
		}
		return false;
	}
}
