/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jnv.betrayal.main.Betrayal;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Holds information regarding a game character's traits
 * @author Vincent Wang
 */
public class Character {

    /* If array this was private, a getter would get this array and other classes would
     * still be able to edit it. Either way, this array will function as a public array */
    public static List<Character> characters = new ArrayList<Character>();
    public static Character currentCharacter;

    public enum Trait {
        GENDER,
        HAIR_STYLE,
        HAIR_COLOR,
        JOB
    }
    private enum Gender {
        MALE, FEMALE
    }
    public enum Jobs {
        WARRIOR, THIEF, KNIGHT, PRIEST
    }
    public enum Stat {
        FLOOR, HEALTH, DEFENSE, ATTACK
    }

    private String name;

    private Gender gender;

    public Preview preview;
    public Job job;
    public Equips equips;
    public Inventory inventory;
    public Stats stats;

    /** Creates character with default values */
    public Character() {
        equips = new Equips();
        preview = new Preview();
        job = new Job();
        inventory = new Inventory();
        stats = new Stats();

        job.setJob(Jobs.WARRIOR);
        update();
    }

    public void createCharacter(Jobs job) {
        this.job.setJob(job);
    }
    private void update() {
        preview.updateHeadSprites();
        preview.updateArmorSprites();
    }
    public void saveInfo() {

    }

    // Getters
    public String getName() { return name; }
    private String getJob(Jobs job) {
        switch (job) {
            case WARRIOR:
                return "W";
            case KNIGHT:
                return "K";
            case PRIEST:
                return "P";
            case THIEF:
                return "T";
            default:
                return null;
        }
    }

    // Setters
    public void setName(String name) { this.name = name; }

    // Classes
    public class Preview {

        /** Textures for character head, format: head_side_walkAnimation */
        private TextureRegion head_front_left, head_front_still, head_front_right;
        private TextureRegion head_right_left, head_right_still, head_right_right;
        private TextureRegion head_left_left, head_left_still, head_left_right;
        private TextureRegion head_back_left, head_back_still, head_back_right;
        private TextureRegion armor_front_left, armor_front_still, armor_front_right;
        private TextureRegion armor_right_left, armor_right_still, armor_right_right;
        private TextureRegion armor_left_left, armor_left_still, armor_left_right;
        private TextureRegion armor_back_left, armor_back_still, armor_back_right;

        private TextureRegion headgear_front_still, headgear_right_still, headgear_left_still, headgear_back_still;
        private TextureRegion shield_front_still, shield_right_still, shield_left_still, shield_back_still;
        private TextureRegion sword_front_still, sword_right_still, sword_left_still, sword_back_still;

        /** Contains rotation value for character preview
         * front = 0, right side = 1, back = 2, left side = 3 */
        private int rotation = 0;

        /** Holds character trait values */
        private int hair_male, hair_female, hairColor;

        public Preview() {
            gender = Gender.MALE;
            hair_male = 1;
            hair_female = 1;
            hairColor = 1;

            updateHeadSprites();
            updateArmorSprites();
        }

        /** Update and split the sprite sheet into appropriate sprites */
        private void updateHeadSprites() {
            Texture head_all;
            if (gender == Gender.MALE) {
                head_all = Betrayal.res.getTexture("hair-male-"
                        + hair_male + "-" + hairColor + "-all");
            } else {
                head_all = Betrayal.res.getTexture("hair-female-"
                        + hair_female + "-" + hairColor + "-all");
            }
            TextureRegion[][] head_split = TextureRegion.split(head_all, 32, 48);
            head_front_left = head_split[0][0];
            head_front_still = head_split[0][1];
            head_front_right = head_split[0][2];
            head_right_left = head_split[1][0];
            head_right_still = head_split[1][1];
            head_right_right = head_split[1][2];
            head_left_left = head_split[2][0];
            head_left_still = head_split[2][1];
            head_left_right = head_split[2][2];
            head_back_left = head_split[3][0];
            head_back_still = head_split[3][1];
            head_back_right = head_split[3][2];
        }
        private void updateArmorSprites() {
            TextureRegion[][] armor_split = TextureRegion.split(equips.getBodyArmorPrev(), 32, 48);
            armor_front_left = armor_split[0][0];
            armor_front_still = armor_split[0][1];
            armor_front_right = armor_split[0][2];
            armor_right_left = armor_split[1][0];
            armor_right_still = armor_split[1][1];
            armor_right_right = armor_split[1][2];
            armor_left_left = armor_split[2][0];
            armor_left_still = armor_split[2][1];
            armor_left_right = armor_split[2][2];
            armor_back_left = armor_split[3][0];
            armor_back_still = armor_split[3][1];
            armor_back_right = armor_split[3][2];
        }
        private void updateShieldSprites() {
            //Texture shield_all = Betrayal.res.getTexture()
        }

        // Getters
        public SnapshotArray<TextureRegion> getFullPreview() {
            SnapshotArray<TextureRegion> preview = new SnapshotArray<TextureRegion>();
            switch (rotation) {
                case 0:
                    preview.add(armor_front_still);
                    //preview.add(sword_front_still);
                    preview.add(head_front_still);
                    //preview.add(shield_front_still);
                    break;
                case 1:
                    preview.add(armor_right_still);
                    //preview.add(sword_right_still);
                    preview.add(head_right_still);
                    //preview.add(shield_right_still);
                    break;
                case 2:
                    preview.add(armor_back_still);
                    //preview.add(sword_front_still);
                    preview.add(head_back_still);
                    //preview.add(shield_front_still);
                    break;
                case 3:
                    preview.add(armor_left_still);
                    //preview.add(sword_front_still);
                    preview.add(head_left_still);
                    //preview.add(shield_front_still);
                    break;
                default:
                    break;
            }
            return preview;
        }
        public SnapshotArray<TextureRegion> getFullPreview(int rotation) {
            this.rotation = rotation;
            return getFullPreview();
        }
        public String getTrait(Trait trait) {
            switch (trait) {
                case GENDER:
                    if (gender == Gender.MALE) return "M";
                    else return "F";
                case HAIR_STYLE:
                    if (gender == Gender.MALE) return Integer.toString(hair_male);
                    else return Integer.toString(hair_female);
                case HAIR_COLOR:
                    if (gender == Gender.MALE) return Integer.toString(hairColor);
                    else return Integer.toString(hairColor);
                case JOB:
                    return getJob(job.getJob());
                default:
                    return null;
            }
        }

        // Setters
        /** Functions for rotating character preview image */
        public void rotateLeft() {
            if (rotation == 0) rotation = 3;
            else rotation--;
        }
        public void rotateRight() {
            rotation++;
            rotation &= 3;
        }
        public void setPreviousTrait(Trait trait) {
            switch (trait) {
                case GENDER:
                    if (gender == Gender.MALE) gender = Gender.FEMALE;
                    else gender = Gender.MALE;
                    update();
                    break;
                case HAIR_STYLE:
                    if (gender == Gender.MALE) {
                        if (hair_male == 1) {
                            hair_male = 5;
                        } else hair_male--;
                    } else {
                        if (hair_female == 1) {
                            hair_female = 4;
                        } else hair_female--;
                    }
                    update();
                    break;
                case HAIR_COLOR:
                    if (hairColor == 1) hairColor = 7;
                    else hairColor--;
                    update();
                    break;
                case JOB:
                    job.setPreviousJob();
                default:
                    break;
            }
        }
        public void setNextTrait(Trait trait) {
            switch (trait) {
                case GENDER:
                    if (gender == Gender.MALE) gender = Gender.FEMALE;
                    else gender = Gender.MALE;
                    update();
                    break;
                case HAIR_STYLE:
                    if (gender == Gender.MALE) {
                        if (hair_male == 5) {
                            hair_male = 1;
                        } else hair_male++;
                    } else {
                        if (hair_female == 4) {
                            hair_female = 1;
                        } else hair_female++;
                    }
                    update();
                    break;
                case HAIR_COLOR:
                    if (hairColor == 7) hairColor = 1;
                    else hairColor++;
                    update();
                    break;
                case JOB:
                    job.setNextJob();
                default:
                    break;
            }
        }

    }
    public class Equips {

        private Item slot_armor_head, slot_armor_body, slot_shield, slot_weapon, slot_ring_1, slot_ring_2;

        public Equips() {
            slot_armor_body = new BodyArmor("char-armor-peasant");
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
        public Texture getBodyArmorPrev() {
            return slot_armor_body.getItemImage();
        }

        // Setters
        public void equipWeapon(Weapon weapon) {
            inventory.removeItem(weapon);
            if (!isWeaponSlotEmpty()) inventory.addItem(slot_weapon);
            slot_weapon = weapon;
        }
        public void equipHeadArmor(HeadGear gear) {
            inventory.removeItem(gear);
            if (!isHeadSlotEmpty()) inventory.addItem(slot_armor_head);
            slot_armor_head = gear;
        }
        public void equipBodyArmor(BodyArmor armor) {
            inventory.removeItem(armor);
            if (!isBodySlotEmpty()) inventory.addItem(slot_armor_body);
            slot_armor_body = armor;
        }
        public void equipShield(Shield shield) {
            inventory.removeItem(shield);
            if (!isShieldSlotEmpty()) inventory.addItem(slot_shield);
            slot_shield = shield;
        }
        public void equipRing(Ring ring) {
            inventory.removeItem(ring);
            if (isRingSlot1Empty()) {
                slot_ring_1 = ring;
            } else if (isRingSlot2Empty()) {
                slot_ring_2 = ring;
            } else {
                inventory.addItem(slot_ring_2);
                slot_ring_2 = ring;
            }
        }

        /** List of functions that unequip equips from the character. If inventory is full and
         * a unequip action is attempted, the unequip will fail and return false.
         * @return true if unequip was successful, false if not */
        public boolean unequipWeapon() {
            if (!isWeaponSlotEmpty() && !inventory.isFull()) {
                inventory.addItem(slot_weapon);
                slot_weapon = null;
                return true;
            }
            return false;
        }
        public boolean unequipHeadArmor() {
            if (!isHeadSlotEmpty() && !inventory.isFull()) {
                inventory.addItem(slot_armor_head);
                slot_armor_head = null;
                return true;
            }
            return false;
        }
        public boolean unequipBodyArmor() {
            if (!isBodySlotEmpty() && !inventory.isFull()) {
                inventory.addItem(slot_armor_body);
                slot_armor_body = null;
                return true;
            }
            return false;
        }
        public boolean unequipShield() {
            if (!isShieldSlotEmpty() && !inventory.isFull()) {
                inventory.addItem(slot_shield);
                slot_shield = null;
                return true;
            }
            return false;
        }
        public boolean unequipRing1() {
            if (!isRingSlot1Empty() && !inventory.isFull()) {
                inventory.addItem(slot_ring_1);
                slot_ring_1 = null;
                return true;
            }
            return false;
        }
        public boolean unequipRing2() {
            if (!isRingSlot2Empty() && !inventory.isFull()) {
                inventory.addItem(slot_ring_2);
                slot_ring_2 = null;
                return true;
            }
            return false;
        }

    }
    public class Inventory {

        private int gold, items_max;
        private List<Item> items;

        public Inventory() {
            gold = 0;
            items_max = 30;

            items = new ArrayList<Item>();
        }

        // Getters
        public int getGold() { return gold; }
        public Item[][] getItems(int rows, int cols) {
            Item[][] itemArray = new Item[rows][cols];
            int counter = 0;
            for (int row = 0; row < itemArray.length; row++) {
                for (int col = 0; col < itemArray[row].length; col++) {
                    if (counter < items.size()) itemArray[row][col] = items.get(counter++);
                }
            }
            return itemArray;
        }
        public boolean isFull() { return items.size() == items_max; }

        // Setters
        /** Adds an item to inventory and return true, if inventory is filled,
         * do nothing and return false.
         * @param itemName name of item to be added */
        public void addItem(String itemName) {
            addItem(itemName, 1);
        }
        /** Adds the specific amount of items to inventory and return true.
         * If inventory is filled, do nothing and return false.
         * @param name name of item to be added
         * @param amount how many items to be added */
        public void addItem(String name, int amount) {
            for (int i = 0; i < amount; i++) if (items.size() < items_max) items.add(new Item(name));
        }
        public void addItem(Item item) {
            addItem(item.getName(), 1);
        }
        public void addItem(Item item, int amount) {
            addItem(item.getName(), amount);
        }
        public void removeItem(Item item) {
            items.remove(item);
        }
        /** Sorts the inventory */
        public void sortItems() {
            if (items != null) {
                items.sort(new Item.ItemComparator());
            }
        }
        /** Adds specified amount of gold to inventory
         * @param amount amount of gold to be added */
        public void addGold(int amount) {
            gold += amount;
        }

    }
    public class Stats {

        private int health, defense, attack, floor;

        public Stats() {
            health = 25;
            defense = 5;
            attack = 5;
            floor = 1;
        }

        // Getters
        public int getStat(Stat stat) {
            switch (stat) {
                case FLOOR:
                    return floor;
                case HEALTH:
                    return health;
                case ATTACK:
                    return attack;
                case DEFENSE:
                    return defense;
                default:
                    return -1;
            }
        }
        public String toString(Stat stat) {
            switch (stat) {
                case FLOOR:
                    return "Floor: " + floor;
                case HEALTH:
                    return "Health: " + health;
                case ATTACK:
                    return "Attack: " + attack;
                case DEFENSE:
                    return "Defense: " + defense;
                default:
                    return null;
            }
        }

        // Setters
        public void advanceFloor() { floor++; }
        public void setFloor(int floor) { this.floor = floor; }
    }
    public class Job {

        private Jobs job;

        public Job() {}
        public Job(Jobs job) {
            this.job = job;
        }

        // Getters
        public Jobs getJob() { return job; }
        public String toString() {
            switch (job) {
                case WARRIOR:
                    return "Warrior";
                case KNIGHT:
                    return "Knight";
                case PRIEST:
                    return "Priest";
                case THIEF:
                    return "Thief";
                default:
                    return null;
            }
        }

        // Setters
        public void setJob(Jobs job) {
            this.job = job;
        }
        public void setPreviousJob() {
            switch (job) {
                case WARRIOR:
                    this.job = Jobs.THIEF;
                    break;
                case KNIGHT:
                    this.job = Jobs.WARRIOR;
                    break;
                case PRIEST:
                    this.job = Jobs.KNIGHT;
                    break;
                case THIEF:
                    this.job = Jobs.PRIEST;
                    break;
                default:
                    break;
            }
        }
        public void setNextJob() {
            switch (job) {
                case WARRIOR:
                    this.job = Jobs.KNIGHT;
                    break;
                case KNIGHT:
                    this.job = Jobs.PRIEST;
                    break;
                case PRIEST:
                    this.job = Jobs.THIEF;
                    break;
                case THIEF:
                    this.job = Jobs.WARRIOR;
                    break;
                default:
                    break;
            }
        }
    }
}