/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.utils.*;
import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.HeadGear;
import com.jnv.betrayal.gameobjects.Ring;
import com.jnv.betrayal.gameobjects.Shield;
import com.jnv.betrayal.gameobjects.Weapon;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Equips implements Json.Serializable {

	private EquipsHandler equipsHandler;
	Character character;
	BetrayalAssetManager res;

	// todo @vincent add getPreview() for equip class
	HeadGear headArmor;
	BodyArmor bodyArmor;
	Shield shield;
	Ring ring1, ring2;
	Weapon weapon;

	public Equips(Character character, BetrayalAssetManager res) {
		equipsHandler = new EquipsHandler(this);
		this.character = character;
		this.res = res;
	}

	// Getters
	public boolean isHeadSlotEmpty() {
		return headArmor == null;
	}

	public boolean isBodySlotEmpty() {
		return bodyArmor == null;
	}

	public boolean isShieldSlotEmpty() {
		return shield == null;
	}

	public boolean isWeaponSlotEmpty() {
		return weapon == null;
	}

	public boolean isRingSlot1Empty() {
		return ring1 == null;
	}

	public boolean isRingSlot2Empty() {
		return ring2 == null;
	}

	public Texture getHeadArmorPreview() {
		return headArmor.getPreview();
	}

	public Texture getBodyArmorPreview() {
		if (bodyArmor == null) return res.getTexture("char-armor-peasant");
		else return bodyArmor.getPreview();
	}

	public Texture getShieldPreview() {
		return shield.getPreview();
	}

	public Texture getWeaponPreview() {
		return weapon.getPreview();
	}

	public Texture getRing1Preview() {
		return ring1.getItemIcon();
	}

	public Texture getRing2Preview() {
		return ring2.getItemIcon();
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

	public void write(Json json) {
		json.writeObjectStart("equips");
		json.writeField(this, "headArmor", String.class);
		json.writeField(this, "bodyArmor", String.class);
		json.writeField(this, "shield", String.class);
		json.writeField(this, "ring1", String.class);
		json.writeField(this, "ring2", String.class);
		json.writeField(this, "weapon", String.class);
		json.writeObjectEnd();
	}

	public void read(Json json, JsonValue jsonData) {

	}
}