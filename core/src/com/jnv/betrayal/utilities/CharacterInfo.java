package com.jnv.betrayal.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jnv.betrayal.main.Betrayal;

/**
 * Holds information regarding a game character's traits
 * @author Vincent Wang
 */
public class CharacterInfo {

    /** Holds character trait values */
    private int hair_male, hair_female, hairColor, skinTone;

    /** Holds gender trait values, male or female */
    private enum Gender {
        MALE, FEMALE
    }
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

    /** Creates character with default values */
    public CharacterInfo() {
        gender = Gender.MALE;
        hair_male = 1;
        hair_female = 1;
        hairColor = 1;
        skinTone = 1;
        updateHeadSprites();
        updateArmorSprites();
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
        Texture armor_all = Betrayal.res.getTexture("char-armor-peasant");
        TextureRegion[][] armor_split = TextureRegion.split(armor_all, 32, 48);
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
    /** Called when character trait is changed */
    private void spriteChanged() {
        updateHeadSprites();
        updateArmorSprites(); // TODO change for different armors
    }

    // Getters
    public TextureRegion getHeadPreview() {
        return head_front_still; // stub
    }
    public TextureRegion getArmorPreview() {
        return armor_front_still;
    }
    public String getGender() {
        if (gender == Gender.MALE) return "M";
        else return "F";
    }

    // Setters
    /** Called when switching genders */
    public void setGender() {
        if (gender == Gender.MALE) gender = Gender.FEMALE;
        else gender = Gender.MALE;
        spriteChanged();
    }
    /** Set hairstyle to the next choice */
    public void setNextHairStyle() {
        if (gender == Gender.MALE) {
            if (hair_male == 5) {
                hair_male = 1;
            } else hair_male++;
        } else {
            if (hair_female == 5) {
                hair_female = 1;
            } else hair_female++;
        }
        spriteChanged();
    }
    /** Set hairstyle to the previous choice */
    public void setPreviousHairStyle() {
        if (gender == Gender.MALE) {
            if (hair_male == 1) {
                hair_male = 5;
            } else hair_male--;
        } else {
            if (hair_female == 1) {
                hair_female = 5;
            } else hair_female--;
        }
        spriteChanged();
    }
    public void setNextHairColor() {
        if (hairColor == 4) hairColor = 1;
        else hairColor++;
        spriteChanged();
    }
    public void setPreviousHairColor() {
        if (hairColor == 1) hairColor = 4;
        else hairColor--;
        spriteChanged();
    }

}
