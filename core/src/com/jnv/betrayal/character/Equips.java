/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.utils.EquipSlot;
import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.DualWieldable;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.HeadGear;
import com.jnv.betrayal.gameobjects.Previewable;
import com.jnv.betrayal.gameobjects.Ring;
import com.jnv.betrayal.gameobjects.Shield;
import com.jnv.betrayal.gameobjects.Weapon;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/**
 * Manages the character's equips
 */
public class Equips implements Json.Serializable {

	private EquipsHandler equipsHandler;
	Inventory inventory;
	BetrayalAssetManager res;

	public final Equip[] equips;

	public Equips(Inventory inventory, BetrayalAssetManager res) {
		equipsHandler = new EquipsHandler(this);
		this.inventory = inventory;
		this.res = res;
		equips = new Equip[EquipSlot.SLOTS];
	}

	// Getters
	public boolean isHeadSlotEmpty() {
		return equips[EquipSlot.HEAD] == null;
	}

	public boolean isBodySlotEmpty() {
		return equips[EquipSlot.BODY] == null;
	}

	public boolean isLeftHandSlotEmpty() {
		return equips[EquipSlot.LEFT_HAND] == null;
	}

	public boolean isRightHandSlotEmpty() {
		return equips[EquipSlot.RIGHT_HAND] == null;
	}

	public boolean isRingSlot1Empty() {
		return equips[EquipSlot.RING1] == null;
	}

	public boolean isRingSlot2Empty() {
		return equips[EquipSlot.RING2] == null;
	}

	public boolean isCloakSlotEmpty() {
		return equips[EquipSlot.CLOAK] == null;
	}

	public Texture getHeadArmorPreview() {
		return ((Previewable) equips[EquipSlot.HEAD]).getPreview();
	}

	public Texture getBodyArmorPreview() {
		if (equips[EquipSlot.BODY] == null) return res.getTexture("previewarmor0");
		else return ((Previewable) equips[EquipSlot.BODY]).getPreview();
	}

	public Texture getLeftHandPreview() {
		return ((DualWieldable) equips[EquipSlot.LEFT_HAND]).getLeftPreview();
	}

	public Texture getRightHandPreview() {
		return ((DualWieldable) equips[EquipSlot.RIGHT_HAND]).getRightPreview();
	}

	// Setters
	public void equip(Equip equip) {
		if (equip instanceof DualWieldable) equipsHandler.equipHand(equip);
		else if (equip instanceof HeadGear) equipsHandler.equipHeadArmor((HeadGear) equip);
		else if (equip instanceof BodyArmor) equipsHandler.equipBodyArmor((BodyArmor) equip);
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
			case EquipSlot.HEAD:
				return equipsHandler.unequipHeadArmor();
			case EquipSlot.BODY:
				return equipsHandler.unequipBodyArmor();
			case EquipSlot.LEFT_HAND:
				return equipsHandler.unequipLeftHand();
			case EquipSlot.RIGHT_HAND:
				return equipsHandler.unequipRightHand();
			case EquipSlot.RING1:
				return equipsHandler.unequipRing1();
			case EquipSlot.RING2:
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