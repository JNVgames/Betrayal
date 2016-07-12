/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.utils.EquipSlot;
import com.jnv.betrayal.gameobjects.DualWieldable;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.Previewable;
import com.jnv.betrayal.gameobjects.attack.Weapon;
import com.jnv.betrayal.gameobjects.defense.BodyArmor;
import com.jnv.betrayal.gameobjects.defense.Helmet;
import com.jnv.betrayal.gameobjects.defense.Shield;
import com.jnv.betrayal.gameobjects.special.Cloak;
import com.jnv.betrayal.gameobjects.special.Ring;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/**
 * Manages the character's equips
 */
public class Equips implements Json.Serializable {

	public final Equip[] equips;
	Inventory inventory;
	Stats stats;
	BetrayalAssetManager res;
	private EquipsHandler equipsHandler;

	public Equips(Inventory inventory, BetrayalAssetManager res) {
		equipsHandler = new EquipsHandler(this);
		this.inventory = inventory;
		this.res = res;
		equips = new Equip[EquipSlot.SLOTS];
		this.stats = stats;
	}

	// Copy Constructor
	public Equips(Equips equips, BetrayalAssetManager res) {
		equipsHandler = new EquipsHandler(this);
		this.inventory = new Inventory();
		this.res = res;
		this.equips = equips.equips.clone();
	}

	public static Class determineEquipType(Equip equip) {
		if (equip instanceof Weapon) return Weapon.class;
		else if (equip instanceof Helmet) return Helmet.class;
		else if (equip instanceof BodyArmor) return BodyArmor.class;
		else if (equip instanceof Shield) return Shield.class;
		else if (equip instanceof Ring) return Ring.class;
		throw new AssertionError("Class type is not an equip");
	}

	// Getters
	public boolean isLeftHandSlotSword() {
		return equips[EquipSlot.LEFT_HAND] instanceof Weapon;
	}

	public boolean isRightHandSlotSword() {
		return equips[EquipSlot.RIGHT_HAND] instanceof Weapon;
	}

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

	public Texture getCloakPreview() {
		return ((Previewable) equips[EquipSlot.CLOAK]).getPreview();
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
		else if (equip instanceof Helmet) equipsHandler.equipHeadArmor((Helmet) equip);
		else if (equip instanceof BodyArmor) equipsHandler.equipBodyArmor((BodyArmor) equip);
		else if (equip instanceof Ring) equipsHandler.equipRing((Ring) equip);
		else if (equip instanceof Cloak) equipsHandler.equipCloak((Cloak) equip);
		if (stats != null) stats.updateStats();
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	/**
	 * List of functions that unequip equips from the character. If inventory is full and
	 * a unequip action is attempted, the unequip will fail and return false.
	 *
	 * @param slot slot to be unequipped
	 * @return true if unequip was successful, false if not
	 */
	public boolean unequip(int slot) {
		boolean isUnequipped = false;
		switch (slot) {
			case EquipSlot.HEAD:
				isUnequipped = equipsHandler.unequipHeadArmor();
				break;
			case EquipSlot.BODY:
				isUnequipped = equipsHandler.unequipBodyArmor();
				break;
			case EquipSlot.LEFT_HAND:
				isUnequipped = equipsHandler.unequipLeftHand();
				break;
			case EquipSlot.RIGHT_HAND:
				isUnequipped = equipsHandler.unequipRightHand();
				break;
			case EquipSlot.RING1:
				isUnequipped = equipsHandler.unequipRing1();
				break;
			case EquipSlot.RING2:
				isUnequipped = equipsHandler.unequipRing2();
				break;
			case EquipSlot.CLOAK:
				isUnequipped = equipsHandler.unequipCloak();
				break;
			default:
				throw new AssertionError("Unequip slot does not exist");
		}
		stats.updateStats();
		return isUnequipped;
	}

	public void write(Json json) {
		json.writeField(this, "equips", Equip[].class);
	}

	public void read(Json json, JsonValue jsonData) {

	}
}