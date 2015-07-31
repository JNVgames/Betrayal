package com.jnv.betrayal.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jnv.betrayal.gamestates.CharacterSelection;
import com.jnv.betrayal.main.Betrayal;

import java.io.File;
import java.nio.file.attribute.FileOwnerAttributeView;

/**
 * Holds information regarding a game character's traits
 * @author Vincent Wang
 */
public class CharacterInfo {

    /** Contains rotation value for character preview
     * front = 0, right side = 1, back = 2, left side = 3 */
    private int rotation = 0;

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

    public void saveInfo() {
        File characterInfo = new File("");
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
    }

    // Getters
    public TextureRegion getHeadPreview() {
        switch (rotation) {
            case 0:
                return head_front_still;
            case 1:
                return head_right_still;
            case 2:
                return head_back_still;
            case 3:
                return head_left_still;
            default:
                return null;
        }
    }
    public TextureRegion getArmorPreview() {
        switch (rotation) {
            case 0:
                return armor_front_still;
            case 1:
                return armor_right_still;
            case 2:
                return armor_back_still;
            case 3:
                return armor_left_still;
            default:
                return null;
        }
    }
    public String getTrait(CharacterSelection.Trait trait) {
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
            default:
                return null;
        }
    }

    // Setters
    public void rotateLeft() {
        if (rotation == 0) rotation = 3;
        else rotation--;
    }
    public void rotateRight() {
        rotation++;
        rotation &= 3;
    }
    public void setPreviousTrait(CharacterSelection.Trait trait) {
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
            default:
                break;
        }
    }
    public void setNextTrait(CharacterSelection.Trait trait) {
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
            default:
                break;
        }
    }

}
