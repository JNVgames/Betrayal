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

    /** Contains rotation value for character preview
     * front = 0, right side = 1, back = 2, left side = 3 */
    private int rotation = 0;

    /** Holds character trait values */
    private int hair_male, hair_female, hairColor;

    private Gender gender;

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

    private Job job;
    private Equips equips;
    private Inventory inventory;
    private Stats stats;

    /** Creates character with default values */
    public Character() {
        equips = new Equips();
        job = new Job();
        inventory = new Inventory();
        stats = new Stats();

        gender = Gender.MALE;
        hair_male = 1;
        hair_female = 1;
        hairColor = 1;
        job.setJob(Jobs.WARRIOR);
        update();
    }

    public void createCharacter(Jobs job) {
        this.job.setJob(job);
    }
    private void update() {
        updateHeadSprites();
        updateArmorSprites();
    }
    public void saveInfo() {

    }

    // Helpers
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
        TextureRegion[][] armor_split = TextureRegion.split(equips.armor_all, 32, 48);
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
    /** Called when character trait is changed */
    private void spriteChanged() {
        update();
    }

    // Getters
    public String getName() { return name; }
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
    public Job getJobClass() { return job; }
    public Equips getEquipsClass() { return equips; }
    public Inventory getInventoryClass() { return inventory; }
    public Stats getStatsClass() { return stats; }
    public Group toLoadPreview() {
        return null;
    }

    // Setters
    public void setName(String name) { this.name = name; }
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
                spriteChanged();
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
                spriteChanged();
                break;
            case HAIR_COLOR:
                if (hairColor == 1) hairColor = 7;
                else hairColor--;
                spriteChanged();
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
                spriteChanged();
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
                spriteChanged();
                break;
            case HAIR_COLOR:
                if (hairColor == 7) hairColor = 1;
                else hairColor++;
                spriteChanged();
                break;
            case JOB:
                job.setNextJob();
            default:
                break;
        }
    }

    // Classes
    public class Equips {

        private Texture armor_all;

        public Equips() {
            armor_all = Betrayal.res.getTexture("char-armor-peasant");
        }

        public void equipWeapon(Weapon weapon) {

        }
        public void equipHeadArmor(Equip equip) {}
        public void equipBodyArmor() {}
        public void equipShield() {}

        public void unequipWeapon() {}
        public void unequipHeadArmor() {}
        public void unequipBodyArmor() {}
        public void unequipShield() {}

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
            for (Item item : items) {
                if (item.getName().equals(name) && !item.isItemFull()) {
                    // item in list, not full
                    int extra = item.addAmount(amount);
                    if (extra > 0) {
                        if (items.size() < items_max) {
                            // item in list, overflow, list not full
                            items.add(new Item(name, extra));
                        }
                        // item in list, overflow, list full
                    }
                    return;
                }
            }
            // item not in list, list not full
            if (items.size() < items_max) items.add(new Item(name, amount));
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