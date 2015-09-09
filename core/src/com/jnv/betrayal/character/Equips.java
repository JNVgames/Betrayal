/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.character.utils.*;
import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.HeadGear;
import com.jnv.betrayal.gameobjects.Ring;
import com.jnv.betrayal.gameobjects.Shield;
import com.jnv.betrayal.gameobjects.Weapon;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Equips {

	private EquipsHandler equipsHandler;
	Character character;
	BetrayalAssetManager res;

	// todo @vincent add getPreview() for equip class
	HeadGear slot_armor_head;
	BodyArmor slot_armor_body;
	Shield slot_shield;
	Ring slot_ring_1, slot_ring_2;
	Weapon slot_weapon;

	public Equips(Character character, BetrayalAssetManager res) {
		equipsHandler = new EquipsHandler(this);
		this.character = character;
		this.res = res;
	}

	// Getters
	public boolean isHeadSlotEmpty() {
		return slot_armor_head == null;
	}

	public boolean isBodySlotEmpty() {
		return slot_armor_body == null;
	}

	public boolean isShieldSlotEmpty() {
		return slot_shield == null;
	}

	public boolean isWeaponSlotEmpty() {
		return slot_ring_1 == null;
	}

	public boolean isRingSlot1Empty() {
		return slot_ring_1 == null;
	}

	public boolean isRingSlot2Empty() {
		return slot_ring_2 == null;
	}

	public Texture getHeadArmorPreview() {
		return slot_armor_head.getPreview();
	}

	public Texture getBodyArmorPreview() {
		if (slot_armor_body == null) return res.getTexture("char-armor-peasant");
		else return slot_armor_body.getPreview();
	}

	public Texture getShieldPreview() {
		return slot_shield.getPreview();
	}

	public Texture getWeaponPreview() {
		return slot_weapon.getPreview();
	}

	public Texture getRing1Preview() {
		return slot_ring_1.getItemIcon();
	}

	public Texture getRing2Preview() {
		return slot_ring_2.getItemIcon();
	}

	// Setters
	public void equip(Equip equip) {
		if (equip instanceof Weapon) equipsHandler.equipWeapon((Weapon) equip);
		else if (equip instanceof HeadGear) equipsHandler.equipHeadArmor((HeadGear) equip);
		else if (equip instanceof BodyArmor) equipsHandler.equipBodyArmor((BodyArmor) equip);
		else if (equip instanceof Shield) equipsHandler.equipShield((Shield) equip);
		else if (equip instanceof Ring) equipsHandler.equipRing((Ring) equip);
	}

	/**
	 * List of functions that unequip equips from the character. If inventory is full and
	 * a unequip action is attempted, the unequip will fail and return false.
	 *
	 * @param slot slot to be unequipped
	 * @return true if unequip was successful, false if not
	 */
	public boolean unequip(int slot) {
		switch (slot) {
			case Slot.HEAD:
				return equipsHandler.unequipHeadArmor();
			case Slot.BODY:
				return equipsHandler.unequipBodyArmor();
			case Slot.SHIELD:
				return equipsHandler.unequipShield();
			case Slot.WEAPON:
				return equipsHandler.unequipWeapon();
			case Slot.RING1:
				return equipsHandler.unequipRing1();
			case Slot.RING2:
				return equipsHandler.unequipRing2();
			default:
				throw new AssertionError("Unequip slot does not exist");
		}
	}

}