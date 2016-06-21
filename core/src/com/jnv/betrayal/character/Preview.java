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
import com.jnv.betrayal.character.utils.Gender;
import com.jnv.betrayal.character.utils.PreviewSlot;
import com.jnv.betrayal.character.utils.Trait;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/**
 * Manages a character's preview
 */
public class Preview implements Json.Serializable {

	PreviewHandler previewHandler;
	BetrayalAssetManager res;
	Equips equips;

	/**
	 * Textures for character head, format: head_side_walkAnimation
	 */
	TextureRegion[] currentPreview;
	TextureRegion[] frontLeft, frontStill, frontRight;
	TextureRegion[] rightLeft, rightStill, rightRight;
	TextureRegion[] leftLeft, leftStill, leftRight;
	TextureRegion[] backLeft, backStill, backRight;
	TextureRegion[][] front, right, left, back;

	/**
	 * Holds character trait values
	 */
	Gender gender;
	int rotation = 0;
	int maleHairColor, femaleHairColor, hairColor;

	public Preview(Equips equips, BetrayalAssetManager res) {
		previewHandler = new PreviewHandler(this);
		this.res = res;
		this.equips = equips;

		gender = Gender.MALE;
		maleHairColor = 1;
		femaleHairColor = 1;
		hairColor = 1;

		initializeArrays();
		update();
	}

	void initializeArrays() {
		currentPreview = new TextureRegion[PreviewSlot.SLOTS];
		frontLeft = new TextureRegion[PreviewSlot.SLOTS];
		frontStill = new TextureRegion[PreviewSlot.SLOTS];
		frontRight = new TextureRegion[PreviewSlot.SLOTS];
		rightLeft = new TextureRegion[PreviewSlot.SLOTS];
		rightStill = new TextureRegion[PreviewSlot.SLOTS];
		rightRight = new TextureRegion[PreviewSlot.SLOTS];
		leftLeft = new TextureRegion[PreviewSlot.SLOTS];
		leftStill = new TextureRegion[PreviewSlot.SLOTS];
		leftRight = new TextureRegion[PreviewSlot.SLOTS];
		backLeft = new TextureRegion[PreviewSlot.SLOTS];
		backStill = new TextureRegion[PreviewSlot.SLOTS];
		backRight = new TextureRegion[PreviewSlot.SLOTS];

		front = new TextureRegion[3][PreviewSlot.SLOTS];
		front[0] = frontLeft;
		front[1] = frontStill;
		front[2] = frontRight;
		right = new TextureRegion[3][PreviewSlot.SLOTS];
		right[0] = rightLeft;
		right[1] = rightStill;
		right[2] = rightRight;
		left = new TextureRegion[3][PreviewSlot.SLOTS];
		left[0] = leftLeft;
		left[1] = leftStill;
		left[2] = leftRight;
		back = new TextureRegion[3][PreviewSlot.SLOTS];
		back[0] = backLeft;
		back[1] = backStill;
		back[2] = backRight;
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
		setCurrentPreview(rotation);
		if (currentPreview != null) {
			for (TextureRegion tr : currentPreview) {
				if (tr != null) {
					batch.draw(tr, x, y, width, height);
				}
			}
		}
	}

	private void setCurrentPreview(int rotation) {
		update();
		switch (rotation) {
			case 0:
				currentPreview = frontStill;
				break;
			case 1:
				currentPreview = rightStill;
				break;
			case 2:
				currentPreview = backStill;
				break;
			case 3:
				currentPreview = leftStill;
				break;
			default:
				throw new IllegalStateException("Rotation out of bounds (0-3): " + rotation);
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

	public Group createRotators(float x, float topY, float width, float gap) {
		Texture imageLeftArrow = res.getTexture("arrow-left");
		Texture imageRightArrow = res.getTexture("arrow-right");
		Group previewRotatorsGroup = new Group();

		Image previewRotators_leftArrow = new Image(imageLeftArrow);
		previewRotators_leftArrow.setBounds(x, topY - 60, width, 60);
		previewRotators_leftArrow.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				rotateLeft();
			}
		});
		previewRotatorsGroup.addActor(previewRotators_leftArrow);

		Image rotatorsRightArrow = new Image(imageRightArrow);
		rotatorsRightArrow.setHeight(previewRotators_leftArrow.getHeight());
		rotatorsRightArrow.setWidth(previewRotators_leftArrow.getWidth());
		rotatorsRightArrow.setX(previewRotators_leftArrow.getX()
				+ previewRotators_leftArrow.getWidth() + gap);
		rotatorsRightArrow.setY(previewRotators_leftArrow.getY());
		previewRotatorsGroup.addActor(rotatorsRightArrow);
		rotatorsRightArrow.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				rotateRight();
			}
		});

		return previewRotatorsGroup;
	}

	public void write(Json json) {
		json.writeObjectStart("traits");
		json.writeField(this, "gender", String.class);
		if (gender == Gender.MALE) json.writeField(this, "maleHairColor", Integer.class);
		else json.writeField(this, "femaleHairColor", Integer.class);
		json.writeField(this, "hairColor", Integer.class);
		json.writeObjectEnd();
	}

	public void read(Json json, JsonValue jsonData) {

	}
}
