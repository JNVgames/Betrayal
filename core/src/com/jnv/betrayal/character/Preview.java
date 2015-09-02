/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Preview {

	public enum Gender {
		MALE, FEMALE
	}

	private BetrayalAssetManager res;
	private com.jnv.betrayal.gameobjects.Character character;
	private Gender gender;
	/**
	 * Textures for character head, format: head_side_walkAnimation
	 */
	private TextureRegion[] front_left, front_still, front_right;
	private TextureRegion[] right_left, right_still, right_right;
	private TextureRegion[] left_left, left_still, left_right;
	private TextureRegion[] back_left, back_still, back_right;
	private TextureRegion[][] front, right, left, back;
	/**
	 * Contains rotation value for character preview
	 * front = 0, right side = 1, back = 2, left side = 3
	 */
	private int rotation = 0;
	/**
	 * Holds character trait values
	 */
	private int hair_male, hair_female, hairColor;

	public Preview(com.jnv.betrayal.gameobjects.Character character, BetrayalAssetManager res) {
		this.res = res;
		this.character = character;

		gender = Gender.MALE;
		hair_male = 1;
		hair_female = 1;
		hairColor = 1;

		initArrays();
		this.update();
	}

	private void initArrays() {
		front_left = new TextureRegion[Slot.SLOTS];
		front_still = new TextureRegion[Slot.SLOTS];
		front_right = new TextureRegion[Slot.SLOTS];
		right_left = new TextureRegion[Slot.SLOTS];
		right_still = new TextureRegion[Slot.SLOTS];
		right_right = new TextureRegion[Slot.SLOTS];
		left_left = new TextureRegion[Slot.SLOTS];
		left_still = new TextureRegion[Slot.SLOTS];
		left_right = new TextureRegion[Slot.SLOTS];
		back_left = new TextureRegion[Slot.SLOTS];
		back_still = new TextureRegion[Slot.SLOTS];
		back_right = new TextureRegion[Slot.SLOTS];

		front = new TextureRegion[3][Slot.SLOTS];
		front[0] = front_left;
		front[1] = front_still;
		front[2] = front_right;
		right = new TextureRegion[3][Slot.SLOTS];
		right[0] = right_left;
		right[1] = right_still;
		right[2] = right_right;
		left = new TextureRegion[3][Slot.SLOTS];
		left[0] = left_left;
		left[1] = left_still;
		left[2] = left_right;
		back = new TextureRegion[3][Slot.SLOTS];
		back[0] = back_left;
		back[1] = back_still;
		back[2] = back_right;
	}

	public void update() {
		updateHeadSprites();
		updateArmorSprites();
		updateShieldSprites();
		updateWeaponSprites();

		toBackOfPreview(Slot.WEAPON, front);
		toFrontOfPreview(Slot.WEAPON, back);
		toFrontOfPreview(Slot.HEAD, back);
	}

	/**
	 * Update and split the sprite sheet into appropriate sprites
	 */
	private void updateHeadSprites() {
		Texture head_all;
		if (gender == Gender.MALE) {
			head_all = res.getTexture("hair-male-"
					+ hair_male + "-" + hairColor + "-all");
		} else {
			head_all = res.getTexture("hair-female-"
					+ hair_female + "-" + hairColor + "-all");
		}
		TextureRegion[][] head_split = TextureRegion.split(head_all, 32, 48);
		front_left[Slot.HEAD] = head_split[0][0];
		front_still[Slot.HEAD] = head_split[0][1];
		front_right[Slot.HEAD] = head_split[0][2];
		right_left[Slot.HEAD] = head_split[1][0];
		right_still[Slot.HEAD] = head_split[1][1];
		right_right[Slot.HEAD] = head_split[1][2];
		left_left[Slot.HEAD] = head_split[2][0];
		left_still[Slot.HEAD] = head_split[2][1];
		left_right[Slot.HEAD] = head_split[2][2];
		back_left[Slot.HEAD] = head_split[3][0];
		back_still[Slot.HEAD] = head_split[3][1];
		back_right[Slot.HEAD] = head_split[3][2];
	}

	private void updateArmorSprites() {
		TextureRegion[][] armor_split =
				TextureRegion.split(character.getEquips().getBodyArmorPreview(), 32, 48);
		front_left[Slot.BODY] = armor_split[0][0];
		front_still[Slot.BODY] = armor_split[0][1];
		front_right[Slot.BODY] = armor_split[0][2];
		right_left[Slot.BODY] = armor_split[1][0];
		right_still[Slot.BODY] = armor_split[1][1];
		right_right[Slot.BODY] = armor_split[1][2];
		left_left[Slot.BODY] = armor_split[2][0];
		left_still[Slot.BODY] = armor_split[2][1];
		left_right[Slot.BODY] = armor_split[2][2];
		back_left[Slot.BODY] = armor_split[3][0];
		back_still[Slot.BODY] = armor_split[3][1];
		back_right[Slot.BODY] = armor_split[3][2];
	}

	private void updateShieldSprites() {
		if (!character.getEquips().isShieldSlotEmpty()) {
			TextureRegion[][] shield_split =
					TextureRegion.split(character.getEquips().getShieldPreview(), 32, 48);
		}
	}

	private void updateWeaponSprites() {
		if (!character.getEquips().isWeaponSlotEmpty()) {
			TextureRegion[][] weapon_split =
					TextureRegion.split(character.getEquips().getWeaponPreview(), 32, 48);
			front_left[Slot.WEAPON] = weapon_split[0][0];
			front_still[Slot.WEAPON] = weapon_split[0][1];
			front_right[Slot.WEAPON] = weapon_split[0][2];
			right_left[Slot.WEAPON] = weapon_split[1][0];
			right_still[Slot.WEAPON] = weapon_split[1][1];
			right_right[Slot.WEAPON] = weapon_split[1][2];
			left_left[Slot.WEAPON] = weapon_split[2][0];
			left_still[Slot.WEAPON] = weapon_split[2][1];
			left_right[Slot.WEAPON] = weapon_split[2][2];
			back_left[Slot.WEAPON] = weapon_split[3][0];
			back_still[Slot.WEAPON] = weapon_split[3][1];
			back_right[Slot.WEAPON] = weapon_split[3][2];
		}
	}

	// Getters
	public void drawPreview(Batch batch, float x, float y, float width, float height) {
		drawPreview(batch, rotation, x, y, width, height);
	}

	public void drawPreview(Batch batch, int rotation, float x, float y, float width, float height) {
		this.rotation = rotation;
		TextureRegion[] preview = getFullPreview();
		if (preview != null) {
			for (TextureRegion tr : preview) {
				if (tr != null) {
					batch.draw(tr, x, y, width, height);
				}
			}
		}
	}

	private TextureRegion[] getFullPreview() {
		update();
		switch (rotation) {
			case 0:
				return front_still;
			case 1:
				return right_still;
			case 2:
				return back_still;
			case 3:
				return left_still;
			default:
				return null;
		}
	}

	private TextureRegion[] getFullPreview(int rotation) {
		this.rotation = rotation;
		return getFullPreview();
	}

	public String getTrait(com.jnv.betrayal.gameobjects.Character.Trait trait) {
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
				return Job.getJobInitial(character.getJob().getJob());
			default:
				return null;
		}
	}

	/**
	 * Functions for rotating character preview image
	 */
	public void rotateLeft() {
		if (rotation == 0) rotation = 3;
		else rotation--;
	}

	// Setters

	public void rotateRight() {
		if (rotation == 3) rotation = 0;
		else rotation++;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public void setPreviousTrait(com.jnv.betrayal.gameobjects.Character.Trait trait) {
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
				character.getJob().setPreviousJob();
			default:
				break;
		}
	}

	public void setNextTrait(com.jnv.betrayal.gameobjects.Character.Trait trait) {
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
				character.getJob().setNextJob();
			default:
				break;
		}
	}

	/**
	 * Sends the specified character preview frame (ex: SWORD, HEAD, SHIELD) to the
	 * back of the character preview
	 *
	 * @param index specified character preview frame
	 * @param src   character preview frame
	 */
	private void toBackOfPreview(int index, TextureRegion[][] src) {
		int counter;
		for (int i = 0; i < 3; i++) {
			counter = index;
			TextureRegion tmp = src[i][index];
			while (counter > 0) {
				src[i][counter] = src[i][--counter];
			}
			src[i][0] = tmp;
		}
	}

	private void toFrontOfPreview(int index, TextureRegion[][] src) {
		int counter;
		for (int i = 0; i < 3; i++) {
			counter = index;
			TextureRegion tmp = src[i][index];
			while (counter < src[i].length - 1) {
				src[i][counter] = src[i][++counter];
			}
			src[i][src.length - 1] = tmp;
		}
	}

	// Static convenience methods
	public Group createRotators(float x, float topY, float width, float gap) {
		Texture image_leftArrow = res.getTexture("arrow-left");
		Texture image_rightArrow = res.getTexture("arrow-right");
		Group group_previewRotators = new Group();

		Image previewRotators_leftArrow = new Image(image_leftArrow);
		previewRotators_leftArrow.setBounds(x, topY - 60, width, 60);
		previewRotators_leftArrow.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				rotateLeft();
			}
		});
		group_previewRotators.addActor(previewRotators_leftArrow);

		Image previewRotators_rightArrow = new Image(image_rightArrow);
		previewRotators_rightArrow.setHeight(previewRotators_leftArrow.getHeight());
		previewRotators_rightArrow.setWidth(previewRotators_leftArrow.getWidth());
		previewRotators_rightArrow.setX(previewRotators_leftArrow.getX()
				+ previewRotators_leftArrow.getWidth() + gap);
		previewRotators_rightArrow.setY(previewRotators_leftArrow.getY());
		group_previewRotators.addActor(previewRotators_rightArrow);
		previewRotators_rightArrow.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				rotateRight();
			}
		});

		return group_previewRotators;
	}
}
