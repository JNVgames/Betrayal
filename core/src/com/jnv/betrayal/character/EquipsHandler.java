/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.HeadGear;
import com.jnv.betrayal.gameobjects.Ring;
import com.jnv.betrayal.gameobjects.Shield;
import com.jnv.betrayal.gameobjects.Weapon;

class EquipsHandler {

	private Equips equips;

	EquipsHandler(Equips equips) {
		this.equips = equips;
	}

	void equipWeapon(Weapon weapon) {
		equips.character.inventory.removeItem(weapon);
		if (!(equips.slot_ring_1 == null))
			equips.character.inventory.addItem(equips.slot_weapon);
		equips.slot_weapon = weapon;
		equips.character.preview.update();
	}

	void equipHeadArmor(HeadGear gear) {
		equips.character.inventory.removeItem(gear);
		if (!(equips.slot_armor_head == null))
			equips.character.inventory.addItem(equips.slot_armor_head);
		equips.slot_armor_head = gear;
	}

	void equipBodyArmor(BodyArmor armor) {
		equips.character.inventory.removeItem(armor);
		if (!(equips.slot_armor_body == null))
			equips.character.inventory.addItem(equips.slot_armor_body);
		equips.slot_armor_body = armor;
	}

	void equipShield(Shield shield) {
		equips.character.inventory.removeItem(shield);
		if (!(equips.slot_shield == null))
			equips.character.inventory.addItem(equips.slot_shield);
		equips.slot_shield = shield;
	}

	void equipRing(Ring ring) {
		equips.character.inventory.removeItem(ring);
		if (equips.slot_ring_1 == null) {
			equips.slot_ring_1 = ring;
		} else if (equips.slot_ring_2 == null) {
			equips.slot_ring_2 = ring;
		} else {
			equips.character.inventory.addItem(equips.slot_ring_2);
			equips.slot_ring_2 = ring;
		}
	}

	boolean unequipHeadArmor() {
		if (!(equips.slot_armor_head == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.slot_armor_head);
			equips.slot_armor_head = null;
			return true;
		}
		return false;
	}

	boolean unequipWeapon() {
		if (!(equips.slot_weapon == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.slot_weapon);
			equips.slot_weapon = null;
			return true;
		}
		return false;
	}

	boolean unequipBodyArmor() {
		if (!(equips.slot_armor_body == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.slot_armor_body);
			equips.slot_armor_body = null;
			return true;
		}
		return false;
	}

	boolean unequipShield() {
		if (!(equips.slot_shield == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.slot_shield);
			equips.slot_shield = null;
			return true;
		}
		return false;
	}

	boolean unequipRing1() {
		if (!(equips.slot_ring_1 == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.slot_ring_1);
			equips.slot_ring_1 = null;
			return true;
		}
		return false;
	}

	boolean unequipRing2() {
		if (!(equips.slot_ring_2 == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.slot_ring_2);
			equips.slot_ring_2 = null;
			return true;
		}
		return false;
	}
}
