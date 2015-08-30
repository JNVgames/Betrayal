/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.HeadGear;
import com.jnv.betrayal.gameobjects.Ring;
import com.jnv.betrayal.gameobjects.Shield;
import com.jnv.betrayal.gameobjects.Weapon;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Equips {

	private com.jnv.betrayal.gameobjects.Character character;
	private BetrayalAssetManager res;

	// todo @vincent add getPreview() for equip class
	private HeadGear slot_armor_head;
	private BodyArmor slot_armor_body;
	private Shield slot_shield;
	private Ring slot_ring_1, slot_ring_2;
	private Weapon slot_weapon;

	public Equips(com.jnv.betrayal.gameobjects.Character character, BetrayalAssetManager res) {
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
		return slot_weapon == null;
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
		if (isBodySlotEmpty()) return res.getTexture("char-armor-peasant");
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
		if (equip instanceof Weapon) equipWeapon((Weapon) equip);
		else if (equip instanceof HeadGear) equipHeadArmor((HeadGear) equip);
		else if (equip instanceof BodyArmor) equipBodyArmor((BodyArmor) equip);
		else if (equip instanceof Shield) equipShield((Shield) equip);
		else if (equip instanceof Ring) equipRing((Ring) equip);
	}

	public void equipWeapon(Weapon weapon) {
		character.getInventory().removeItem(weapon);
		if (!isWeaponSlotEmpty()) character.getInventory().addItem(slot_weapon);
		slot_weapon = weapon;
		character.getPreview().update();
	}

	public void equipHeadArmor(HeadGear gear) {
		character.getInventory().removeItem(gear);
		if (!isHeadSlotEmpty()) character.getInventory().addItem(slot_armor_head);
		slot_armor_head = gear;
	}

	public void equipBodyArmor(BodyArmor armor) {
		character.getInventory().removeItem(armor);
		if (!isBodySlotEmpty()) character.getInventory().addItem(slot_armor_body);
		slot_armor_body = armor;
	}

	public void equipShield(Shield shield) {
		character.getInventory().removeItem(shield);
		if (!isShieldSlotEmpty()) character.getInventory().addItem(slot_shield);
		slot_shield = shield;
	}

	public void equipRing(Ring ring) {
		character.getInventory().removeItem(ring);
		if (isRingSlot1Empty()) {
			slot_ring_1 = ring;
		} else if (isRingSlot2Empty()) {
			slot_ring_2 = ring;
		} else {
			character.getInventory().addItem(slot_ring_2);
			slot_ring_2 = ring;
		}
	}

	/**
	 * List of functions that unequip equips from the character. If inventory is full and
	 * a unequip action is attempted, the unequip will fail and return false.
	 *
	 * @return true if unequip was successful, false if not
	 */
	public boolean unequipWeapon() {
		if (!isWeaponSlotEmpty() && !character.getInventory().isFull()) {
			character.getInventory().addItem(slot_weapon);
			slot_weapon = null;
			return true;
		}
		return false;
	}

	public boolean unequipHeadArmor() {
		if (!isHeadSlotEmpty() && !character.getInventory().isFull()) {
			character.getInventory().addItem(slot_armor_head);
			slot_armor_head = null;
			return true;
		}
		return false;
	}

	public boolean unequipBodyArmor() {
		if (!isBodySlotEmpty() && !character.getInventory().isFull()) {
			character.getInventory().addItem(slot_armor_body);
			slot_armor_body = null;
			return true;
		}
		return false;
	}

	public boolean unequipShield() {
		if (!isShieldSlotEmpty() && !character.getInventory().isFull()) {
			character.getInventory().addItem(slot_shield);
			slot_shield = null;
			return true;
		}
		return false;
	}

	public boolean unequipRing1() {
		if (!isRingSlot1Empty() && !character.getInventory().isFull()) {
			character.getInventory().addItem(slot_ring_1);
			slot_ring_1 = null;
			return true;
		}
		return false;
	}

	public boolean unequipRing2() {
		if (!isRingSlot2Empty() && !character.getInventory().isFull()) {
			character.getInventory().addItem(slot_ring_2);
			slot_ring_2 = null;
			return true;
		}
		return false;
	}

}