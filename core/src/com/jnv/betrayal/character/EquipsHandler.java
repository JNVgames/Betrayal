/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.character.utils.EquipSlot;
import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.HeadGear;
import com.jnv.betrayal.gameobjects.Ring;

/**
 * Helpers for character equips class
 */
class EquipsHandler {

	private Equips equips;

	EquipsHandler(Equips equips) {
		this.equips = equips;
	}

	void equipHand(Equip equip) {
		equips.character.inventory.removeItem(equip);
		if (equips.equips[EquipSlot.LEFT_HAND] == null) {
			equips.equips[EquipSlot.LEFT_HAND] = equip;
		} else if (equips.equips[EquipSlot.RIGHT_HAND] == null) {
			equips.equips[EquipSlot.RIGHT_HAND] = equip;
		} else {
			equips.character.inventory.addItem(equips.equips[EquipSlot.RIGHT_HAND]);
			equips.equips[EquipSlot.RIGHT_HAND] = equip;
		}
	}

	void equipHeadArmor(HeadGear gear) {
		equips.character.inventory.removeItem(gear);
		if (!(equips.equips[EquipSlot.HEAD] == null))
			equips.character.inventory.addItem(equips.equips[EquipSlot.HEAD]);
		equips.equips[EquipSlot.HEAD] = gear;
	}

	void equipBodyArmor(BodyArmor armor) {
		equips.character.inventory.removeItem(armor);
		if (!(equips.equips[EquipSlot.BODY] == null))
			equips.character.inventory.addItem(equips.equips[EquipSlot.BODY]);
		equips.equips[EquipSlot.BODY] = armor;
	}

	void equipRing(Ring ring) {
		equips.character.inventory.removeItem(ring);
		if (equips.equips[EquipSlot.RING1] == null) {
			equips.equips[EquipSlot.RING1] = ring;
		} else if (equips.equips[EquipSlot.RING2] == null) {
			equips.equips[EquipSlot.RING2] = ring;
		} else {
			equips.character.inventory.addItem(equips.equips[EquipSlot.RING2]);
			equips.equips[EquipSlot.RING2] = ring;
		}
	}

	boolean unequipHeadArmor() {
		if (!(equips.equips[EquipSlot.HEAD] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[EquipSlot.HEAD]);
			equips.equips[EquipSlot.HEAD] = null;
			return true;
		}
		return false;
	}

	boolean unequipLeftHand() {
		if (!(equips.equips[EquipSlot.LEFT_HAND] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[EquipSlot.LEFT_HAND]);
			equips.equips[EquipSlot.LEFT_HAND] = null;
			return true;
		}
		return false;
	}

	boolean unequipRightHand() {
		if (!(equips.equips[EquipSlot.RIGHT_HAND] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[EquipSlot.RIGHT_HAND]);
			equips.equips[EquipSlot.RIGHT_HAND] = null;
			return true;
		}
		return false;
	}

	boolean unequipBodyArmor() {
		if (!(equips.equips[EquipSlot.BODY] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[EquipSlot.BODY]);
			equips.equips[EquipSlot.BODY] = null;
			return true;
		}
		return false;
	}

	boolean unequipRing1() {
		if (!(equips.equips[EquipSlot.RING1] == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[EquipSlot.RING1]);
			equips.equips[EquipSlot.RING1] = null;
			return true;
		}
		return false;
	}

	boolean unequipRing2() {
		if (!(equips.equips[EquipSlot.RING2] == null)
				&& !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.equips[EquipSlot.RING2]);
			equips.equips[EquipSlot.RING2] = null;
			return true;
		}
		return false;
	}
}
