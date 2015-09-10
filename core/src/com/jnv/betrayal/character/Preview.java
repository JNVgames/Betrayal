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
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.utils.Slot;
import com.jnv.betrayal.character.utils.Gender;
import com.jnv.betrayal.character.utils.Trait;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Preview implements Json.Serializable {

	PreviewHandler previewHandler;
	BetrayalAssetManager res;
	Character character;
	/**
	 * Textures for character head, format: head_side_walkAnimation
	 */
	TextureRegion[] front_left, front_still, front_right;
	TextureRegion[] right_left, right_still, right_right;
	TextureRegion[] left_left, left_still, left_right;
	TextureRegion[] back_left, back_still, back_right;
	TextureRegion[][] front, right, left, back;
	/**
	 * Contains rotation value for character preview
	 * front = 0, right side = 1, back = 2, left side = 3
	 */
	int rotation = 0;
	/**
	 * Holds character trait values
	 */
	Gender gender;
	int hair_male, hair_female, hairColor;

	public Preview(Character character, BetrayalAssetManager res) {
		previewHandler = new PreviewHandler(this);
		this.res = res;
		this.character = character;

		gender = Gender.MALE;
		hair_male = 1;
		hair_female = 1;
		hairColor = 1;

		initArrays();
		update();
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
		previewHandler.update();
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
				return Job.getJobInitial(character.job.getJob());
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
						hair_female = 5;
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
					if (hair_female == 5) {
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

	public void write(Json json) {
		json.writeObjectStart("traits");
		json.writeField(this, "gender", String.class);
		if (gender == Gender.MALE) json.writeField(this, "hair_male", Integer.class);
		else json.writeField(this, "hair_female", Integer.class);
		json.writeField(this, "hairColor", Integer.class);
		json.writeObjectEnd();
	}

	public void read(Json json, JsonValue jsonData) {

	}
}
