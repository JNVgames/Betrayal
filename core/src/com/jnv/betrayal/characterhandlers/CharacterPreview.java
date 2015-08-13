/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.characterhandlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jnv.betrayal.entities.*;
import com.jnv.betrayal.entities.Character;
import com.jnv.betrayal.main.Betrayal;

public class CharacterPreview {

    private com.jnv.betrayal.entities.Character character;

    public enum Gender {
        MALE, FEMALE
    }
    private Gender gender;

    /** Textures for character head, format: head_side_walkAnimation */
    private TextureRegion[] front_left, front_still, front_right;
    private TextureRegion[] right_left, right_still, right_right;
    private TextureRegion[] left_left, left_still, left_right;
    private TextureRegion[] back_left, back_still, back_right;

    public final static int SLOTS = 6;
    public final static int HEAD = 0;
    public final static int BODY = 1;
    public final static int SHIELD = 2;
    public final static int WEAPON = 3;
    public final static int RING1 = 4;
    public final static int RING2 = 5;

    /** Contains rotation value for character preview
     * front = 0, right side = 1, back = 2, left side = 3 */
    private int rotation = 0;

    /** Holds character trait values */
    private int hair_male, hair_female, hairColor;

    public CharacterPreview(Character character) {
        this.character = character;

        gender = Gender.MALE;
        hair_male = 1;
        hair_female = 1;
        hairColor = 1;

        initArrays();
        this.update();
    }

    private void initArrays() {
        front_left = new TextureRegion[SLOTS];
        front_still = new TextureRegion[SLOTS];
        front_right = new TextureRegion[SLOTS];
        right_left = new TextureRegion[SLOTS];
        right_still = new TextureRegion[SLOTS];
        right_right = new TextureRegion[SLOTS];
        left_left = new TextureRegion[SLOTS];
        left_still = new TextureRegion[SLOTS];
        left_right = new TextureRegion[SLOTS];
        back_left = new TextureRegion[SLOTS];
        back_still = new TextureRegion[SLOTS];
        back_right = new TextureRegion[SLOTS];
    }
    public void update() {
        updateHeadSprites();
        updateArmorSprites();
        updateShieldSprites();
        updateWeaponSprites();

        toBackOfPreview(WEAPON, front_still);
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
        front_left[HEAD] = head_split[0][0];
        front_still[HEAD] = head_split[0][1];
        front_right[HEAD] = head_split[0][2];
        right_left[HEAD] = head_split[1][0];
        right_still[HEAD] = head_split[1][1];
        right_right[HEAD] = head_split[1][2];
        left_left[HEAD] = head_split[2][0];
        left_still[HEAD] = head_split[2][1];
        left_right[HEAD] = head_split[2][2];
        back_left[HEAD] = head_split[3][0];
        back_still[HEAD] = head_split[3][1];
        back_right[HEAD] = head_split[3][2];
    }
    private void updateArmorSprites() {
        TextureRegion[][] armor_split =
                TextureRegion.split(character.equips.getBodyArmorPreview(), 32, 48);
        front_left[BODY] = armor_split[0][0];
        front_still[BODY] = armor_split[0][1];
        front_right[BODY] = armor_split[0][2];
        right_left[BODY] = armor_split[1][0];
        right_still[BODY] = armor_split[1][1];
        right_right[BODY] = armor_split[1][2];
        left_left[BODY] = armor_split[2][0];
        left_still[BODY] = armor_split[2][1];
        left_right[BODY] = armor_split[2][2];
        back_left[BODY] = armor_split[3][0];
        back_still[BODY] = armor_split[3][1];
        back_right[BODY] = armor_split[3][2];
    }
    private void updateShieldSprites() {
        if (!character.equips.isShieldSlotEmpty()) {
            TextureRegion[][] shield_split =
                    TextureRegion.split(character.equips.getShieldPreview(), 32, 48);
        }
    }
    private void updateWeaponSprites() {
        if (!character.equips.isWeaponSlotEmpty()) {
            TextureRegion[][] weapon_split =
                    TextureRegion.split(character.equips.getWeaponPreview(), 32, 48);
            front_left[WEAPON] = weapon_split[0][0];
            front_still[WEAPON] = weapon_split[0][1];
            front_right[WEAPON] = weapon_split[0][2];
            right_left[WEAPON] = weapon_split[1][0];
            right_still[WEAPON] = weapon_split[1][1];
            right_right[WEAPON] = weapon_split[1][2];
            left_left[WEAPON] = weapon_split[2][0];
            left_still[WEAPON] = weapon_split[2][1];
            left_right[WEAPON] = weapon_split[2][2];
            back_left[WEAPON] = weapon_split[3][0];
            back_still[WEAPON] = weapon_split[3][1];
            back_right[WEAPON] = weapon_split[3][2];
        }
    }

    // Getters
    public SnapshotArray<TextureRegion[]> getFullPreview() {
        SnapshotArray<TextureRegion[]> preview = new SnapshotArray<TextureRegion[]>();
        switch (rotation) {
            case 0:
                preview.add(front_still);
                break;
            case 1:
                preview.add(right_still);
                break;
            case 2:
                preview.add(back_still);
                break;
            case 3:
                preview.add(left_still);
                break;
            default:
                break;
        }
        return preview;
    }
    public SnapshotArray<TextureRegion[]> getFullPreview(int rotation) {
        this.rotation = rotation;
        return getFullPreview();
    }
    public String getTrait(Character.Trait trait) {
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
                return character.getJob(character.job.getJob());
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
    public void setPreviousTrait(Character.Trait trait) {
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
                character.job.setPreviousJob();
            default:
                break;
        }
    }
    public void setNextTrait(Character.Trait trait) {
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
                character.job.setNextJob();
            default:
                break;
        }
    }
    /** Sends the specified character preview frame (ex: SWORD, HEAD, SHIELD) to the
     * back of the character preview
     * @param index specified character preview frame
     * @param src character preview frame */
    private void toBackOfPreview(int index, TextureRegion[] src) {
        TextureRegion tmp = src[index];
        while (index > 0) {
            src[index] = src[--index];
        }
        src[0] = tmp;
    }

}
