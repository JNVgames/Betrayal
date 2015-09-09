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
		if (!(equips.ring1 == null))
			equips.character.inventory.addItem(equips.weapon);
		equips.weapon = weapon;
		equips.character.preview.update();
	}

	void equipHeadArmor(HeadGear gear) {
		equips.character.inventory.removeItem(gear);
		if (!(equips.headArmor == null))
			equips.character.inventory.addItem(equips.headArmor);
		equips.headArmor = gear;
	}

	void equipBodyArmor(BodyArmor armor) {
		equips.character.inventory.removeItem(armor);
		if (!(equips.bodyArmor == null))
			equips.character.inventory.addItem(equips.bodyArmor);
		equips.bodyArmor = armor;
	}

	void equipShield(Shield shield) {
		equips.character.inventory.removeItem(shield);
		if (!(equips.shield == null))
			equips.character.inventory.addItem(equips.shield);
		equips.shield = shield;
	}

	void equipRing(Ring ring) {
		equips.character.inventory.removeItem(ring);
		if (equips.ring1 == null) {
			equips.ring1 = ring;
		} else if (equips.ring2 == null) {
			equips.ring2 = ring;
		} else {
			equips.character.inventory.addItem(equips.ring2);
			equips.ring2 = ring;
		}
	}

	boolean unequipHeadArmor() {
		if (!(equips.headArmor == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.headArmor);
			equips.headArmor = null;
			return true;
		}
		return false;
	}

	boolean unequipWeapon() {
		if (!(equips.weapon == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.weapon);
			equips.weapon = null;
			return true;
		}
		return false;
	}

	boolean unequipBodyArmor() {
		if (!(equips.bodyArmor == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.bodyArmor);
			equips.bodyArmor = null;
			return true;
		}
		return false;
	}

	boolean unequipShield() {
		if (!(equips.shield == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.shield);
			equips.shield = null;
			return true;
		}
		return false;
	}

	boolean unequipRing1() {
		if (!(equips.ring1 == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.ring1);
			equips.ring1 = null;
			return true;
		}
		return false;
	}

	boolean unequipRing2() {
		if (!(equips.ring2 == null) && !equips.character.inventory.isFull()) {
			equips.character.inventory.addItem(equips.ring2);
			equips.ring2 = null;
			return true;
		}
		return false;
	}
}
