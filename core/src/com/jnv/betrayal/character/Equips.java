/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Equips {

    private Character character;
    private BetrayalAssetManager res;

    // todo @vincent add getPreview() for equip class
    private com.jnv.betrayal.item.HeadGear slot_armor_head;
    private com.jnv.betrayal.item.BodyArmor slot_armor_body;
    private com.jnv.betrayal.item.Shield slot_shield;
    private com.jnv.betrayal.item.Ring slot_ring_1, slot_ring_2;
    private com.jnv.betrayal.item.Weapon slot_weapon;

    public Equips(Character character, BetrayalAssetManager res) {
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
    public void equip(com.jnv.betrayal.item.Equip equip) {
        if (equip instanceof com.jnv.betrayal.item.Weapon) equipWeapon((com.jnv.betrayal.item.Weapon) equip);
        else if (equip instanceof com.jnv.betrayal.item.HeadGear) equipHeadArmor((com.jnv.betrayal.item.HeadGear) equip);
        else if (equip instanceof com.jnv.betrayal.item.BodyArmor) equipBodyArmor((com.jnv.betrayal.item.BodyArmor) equip);
        else if (equip instanceof com.jnv.betrayal.item.Shield) equipShield((com.jnv.betrayal.item.Shield) equip);
        else if (equip instanceof com.jnv.betrayal.item.Ring) equipRing((com.jnv.betrayal.item.Ring) equip);
    }
    public void equipWeapon(com.jnv.betrayal.item.Weapon weapon) {
        character.inventory.removeItem(weapon);
        if (!isWeaponSlotEmpty()) character.inventory.addItem(slot_weapon);
        slot_weapon = weapon;
        character.preview.update();
    }
    public void equipHeadArmor(com.jnv.betrayal.item.HeadGear gear) {
        character.inventory.removeItem(gear);
        if (!isHeadSlotEmpty()) character.inventory.addItem(slot_armor_head);
        slot_armor_head = gear;
    }
    public void equipBodyArmor(com.jnv.betrayal.item.BodyArmor armor) {
        character.inventory.removeItem(armor);
        if (!isBodySlotEmpty()) character.inventory.addItem(slot_armor_body);
        slot_armor_body = armor;
    }
    public void equipShield(com.jnv.betrayal.item.Shield shield) {
        character.inventory.removeItem(shield);
        if (!isShieldSlotEmpty()) character.inventory.addItem(slot_shield);
        slot_shield = shield;
    }
    public void equipRing(com.jnv.betrayal.item.Ring ring) {
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