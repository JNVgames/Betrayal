/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.utils.Slot;
import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.HeadGear;
import com.jnv.betrayal.gameobjects.Ring;
import com.jnv.betrayal.gameobjects.Shield;
import com.jnv.betrayal.gameobjects.Weapon;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/**
 * Manages the character's equips
 */
public class Equips implements Json.Serializable {

	private EquipsHandler equipsHandler;
	Character character;
	BetrayalAssetManager res;

	// todo @vincent add getPreview() for equip class
	public final Equip[] equips;

	public Equips(Character character, BetrayalAssetManager res) {
		equipsHandler = new EquipsHandler(this);
		this.character = character;
		this.res = res;
		equips = new Equip[Slot.SLOTS];
	}

	// Getters
	public boolean isHeadSlotEmpty() {
		return equips[Slot.HEAD] == null;
	}

	public boolean isBodySlotEmpty() {
		return equips[Slot.BODY] == null;
	}

	public boolean isShieldSlotEmpty() {
		return equips[Slot.SHIELD] == null;
	}

	public boolean isWeapon1SlotEmpty() {
		return equips[Slot.WEAPON1] == null;
	}

	public boolean isWeapon2SlotEmpty() {
		return equips[Slot.WEAPON2] == null;
	}

	public boolean isRingSlot1Empty() {
		return equips[Slot.RING1] == null;
	}

	public boolean isRingSlot2Empty() {
		return equips[Slot.RING2] == null;
	}

	public boolean isCloakSlotEmpty() {
		return equips[Slot.CLOAK] == null;
	}

	public Texture getHeadArmorPreview() {
		return equips[Slot.HEAD].getPreview();
	}

	public Texture getBodyArmorPreview() {
		if (equips[Slot.BODY] == null) return res.getTexture("char-armor-peasant");
		else return equips[Slot.BODY].getPreview();
	}

	public Texture getShieldPreview() {
		return equips[Slot.SHIELD].getPreview();
	}

	public Texture getWeapon1Preview() {
		return equips[Slot.WEAPON1].getPreview();
	}

	public Texture getWeapon2Preview() {
		return equips[Slot.WEAPON2].getPreview();
	}

	public Texture getRing1Preview() {
		return equips[Slot.RING1].getItemIcon();
	}

	public Texture getRing2Preview() {
		return equips[Slot.RING2].getItemIcon();
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
			case Slot.WEAPON1:
				return equipsHandler.unequipWeapon1();
			case Slot.WEAPON2:
				return equipsHandler.unequipWeapon2();
			case Slot.RING1:
				return equipsHandler.unequipRing1();
			case Slot.RING2:
				return equipsHandler.unequipRing2();
			default:
				throw new AssertionError("Unequip slot does not exist");
		}
	}

	public void write(Json json) {
		json.writeField(this, "equips", Equip[].class);
	}

	public void read(Json json, JsonValue jsonData) {

	}

	public static Class determineEquipType(Equip equip) {
		if (equip instanceof Weapon) return Weapon.class;
		else if (equip instanceof HeadGear) return HeadGear.class;
		else if (equip instanceof BodyArmor) return BodyArmor.class;
		else if (equip instanceof Shield) return Shield.class;
		else if (equip instanceof Ring) return Ring.class;
		throw new AssertionError("Class type is not an equip");
	}
}