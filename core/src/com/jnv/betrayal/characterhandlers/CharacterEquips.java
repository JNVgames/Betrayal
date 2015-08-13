/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.characterhandlers;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.entities.*;
import com.jnv.betrayal.entities.Character;
import com.jnv.betrayal.main.Betrayal;

public class CharacterEquips {

    private com.jnv.betrayal.entities.Character character;

    // todo @vincent add getPreview() for equip class
    private HeadGear slot_armor_head;
    private BodyArmor slot_armor_body;
    private Shield slot_shield;
    private Ring slot_ring_1, slot_ring_2;
    private Weapon slot_weapon;

    public CharacterEquips(Character character) {
        this.character = character;
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
        if (isBodySlotEmpty()) return Betrayal.res.getTexture("char-armor-peasant");
        else return slot_armor_body.getPreview();
    }
    public Texture getShieldPreview() {
        return slot_shield.getPreview();
    }
    public Texture getWeaponPreview() {
        return slot_weapon.getPreview();
    }
    public Texture getRing1Preview() {
        return slot_ring_1.getItemImage();
    }
    public Texture getRing2Preview() {
        return slot_ring_2.getItemImage();
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
        character.inventory.removeItem(weapon);
        if (!isWeaponSlotEmpty()) character.inventory.addItem(slot_weapon);
        slot_weapon = weapon;
        character.preview.update();
    }
    public void equipHeadArmor(HeadGear gear) {
        character.inventory.removeItem(gear);
        if (!isHeadSlotEmpty()) character.inventory.addItem(slot_armor_head);
        slot_armor_head = gear;
    }
    public void equipBodyArmor(BodyArmor armor) {
        character.inventory.removeItem(armor);
        if (!isBodySlotEmpty()) character.inventory.addItem(slot_armor_body);
        slot_armor_body = armor;
    }
    public void equipShield(Shield shield) {
        character.inventory.removeItem(shield);
        if (!isShieldSlotEmpty()) character.inventory.addItem(slot_shield);
        slot_shield = shield;
    }
    public void equipRing(Ring ring) {
        character.inventory.removeItem(ring);
        if (isRingSlot1Empty()) {
            slot_ring_1 = ring;
        } else if (isRingSlot2Empty()) {
            slot_ring_2 = ring;
        } else {
            character.inventory.addItem(slot_ring_2);
            slot_ring_2 = ring;
        }
    }

    /** List of functions that unequip equips from the character. If inventory is full and
     * a unequip action is attempted, the unequip will fail and return false.
     * @return true if unequip was successful, false if not */
    public boolean unequipWeapon() {
        if (!isWeaponSlotEmpty() && !character.inventory.isFull()) {
            character.inventory.addItem(slot_weapon);
            slot_weapon = null;
            return true;
        }
        return false;
    }
    public boolean unequipHeadArmor() {
        if (!isHeadSlotEmpty() && !character.inventory.isFull()) {
            character.inventory.addItem(slot_armor_head);
            slot_armor_head = null;
            return true;
        }
        return false;
    }
    public boolean unequipBodyArmor() {
        if (!isBodySlotEmpty() && !character.inventory.isFull()) {
            character.inventory.addItem(slot_armor_body);
            slot_armor_body = null;
            return true;
        }
        return false;
    }
    public boolean unequipShield() {
        if (!isShieldSlotEmpty() && !character.inventory.isFull()) {
            character.inventory.addItem(slot_shield);
            slot_shield = null;
            return true;
        }
        return false;
    }
    public boolean unequipRing1() {
        if (!isRingSlot1Empty() && !character.inventory.isFull()) {
            character.inventory.addItem(slot_ring_1);
            slot_ring_1 = null;
            return true;
        }
        return false;
    }
    public boolean unequipRing2() {
        if (!isRingSlot2Empty() && !character.inventory.isFull()) {
            character.inventory.addItem(slot_ring_2);
            slot_ring_2 = null;
            return true;
        }
        return false;
    }

}