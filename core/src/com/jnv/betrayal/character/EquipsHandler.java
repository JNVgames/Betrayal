/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.character.utils.EquipSlot;
import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.Cloak;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.Helmet;
import com.jnv.betrayal.gameobjects.Ring;

/**
 * Helpers for character equips class
 */
class EquipsHandler {

	private Equips equips;

	EquipsHandler(Equips equips) {
		this.equips = equips;
	}

	void equipCloak(Cloak cloak) {
		equips.inventory.removeItem(cloak);
		// Cloak slot is full, swap out current cloak
		if (equips.equips[EquipSlot.CLOAK] != null) {
			equips.inventory.addItem(equips.equips[EquipSlot.CLOAK]);
		}
		equips.equips[EquipSlot.CLOAK] = cloak;
	}

	void equipHand(Equip equip) {
		equips.inventory.removeItem(equip);
		if (equips.equips[EquipSlot.LEFT_HAND] == null) {
			equips.equips[EquipSlot.LEFT_HAND] = equip;
		} else if (equips.equips[EquipSlot.RIGHT_HAND] == null) {
			equips.equips[EquipSlot.RIGHT_HAND] = equip;
		} else {
			equips.inventory.addItem(equips.equips[EquipSlot.RIGHT_HAND]);
			equips.equips[EquipSlot.RIGHT_HAND] = equip;
		}
	}

	void equipHeadArmor(Helmet gear) {
		equips.inventory.removeItem(gear);
		if (!(equips.equips[EquipSlot.HEAD] == null))
			equips.inventory.addItem(equips.equips[EquipSlot.HEAD]);
		equips.equips[EquipSlot.HEAD] = gear;
	}

	void equipBodyArmor(BodyArmor armor) {
		equips.inventory.removeItem(armor);
		if (!(equips.equips[EquipSlot.BODY] == null))
			equips.inventory.addItem(equips.equips[EquipSlot.BODY]);
		equips.equips[EquipSlot.BODY] = armor;
	}

	void equipRing(Ring ring) {
		equips.inventory.removeItem(ring);
		if (equips.equips[EquipSlot.RING1] == null) {
			equips.equips[EquipSlot.RING1] = ring;
		} else if (equips.equips[EquipSlot.RING2] == null) {
			equips.equips[EquipSlot.RING2] = ring;
		} else {
			equips.inventory.addItem(equips.equips[EquipSlot.RING2]);
			equips.equips[EquipSlot.RING2] = ring;
		}
	}

	boolean unequipHeadArmor() {
		if (!(equips.equips[EquipSlot.HEAD] == null)
				&& !equips.inventory.isFull()) {
			equips.inventory.addItem(equips.equips[EquipSlot.HEAD]);
			equips.equips[EquipSlot.HEAD] = null;
			return true;
		}
		return false;
	}

	boolean unequipLeftHand() {
		if (!(equips.equips[EquipSlot.LEFT_HAND] == null)
				&& !equips.inventory.isFull()) {
			equips.inventory.addItem(equips.equips[EquipSlot.LEFT_HAND]);
			equips.equips[EquipSlot.LEFT_HAND] = null;
			return true;
		}
		return false;
	}

	boolean unequipRightHand() {
		if (!(equips.equips[EquipSlot.RIGHT_HAND] == null)
				&& !equips.inventory.isFull()) {
			equips.inventory.addItem(equips.equips[EquipSlot.RIGHT_HAND]);
			equips.equips[EquipSlot.RIGHT_HAND] = null;
			return true;
		}
		return false;
	}

	boolean unequipBodyArmor() {
		if (!(equips.equips[EquipSlot.BODY] == null)
				&& !equips.inventory.isFull()) {
			equips.inventory.addItem(equips.equips[EquipSlot.BODY]);
			equips.equips[EquipSlot.BODY] = null;
			return true;
		}
		return false;
	}

	boolean unequipRing1() {
		if (!(equips.equips[EquipSlot.RING1] == null) && !equips.inventory.isFull()) {
			equips.inventory.addItem(equips.equips[EquipSlot.RING1]);
			equips.equips[EquipSlot.RING1] = null;
			return true;
		}
		return false;
	}

	boolean unequipRing2() {
		if (!(equips.equips[EquipSlot.RING2] == null)
				&& !equips.inventory.isFull()) {
			equips.inventory.addItem(equips.equips[EquipSlot.RING2]);
			equips.equips[EquipSlot.RING2] = null;
			return true;
		}
		return false;
	}
}
