/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jnv.betrayal.character.utils.Gender;
import com.jnv.betrayal.character.utils.PreviewSlot;

/**
 * Helper methods for the character's preview class
 */
class PreviewHandler {

	private Preview preview;

	PreviewHandler(Preview preview) {
		this.preview = preview;
	}

	/**
	 * Resets the preview images to the correct images
	 */
	void update() {
		preview.initializeArrays();
		updateHeadSprites();
		updateArmorSprites();
		updateLeftHandSprites();
		updateRightHandSprites();

		// Calibrate how the previews are drawn
		toBackOfPreview(PreviewSlot.LEFT_HAND, preview.front);
		toBackOfPreview(PreviewSlot.RIGHT_HAND, preview.front);
		toFrontOfPreview(PreviewSlot.HEAD, preview.back);
		toFrontOfPreview(PreviewSlot.LEFT_HAND, preview.back);
		toFrontOfPreview(PreviewSlot.RIGHT_HAND, preview.back);
	}

	/**
	 * Update and split the sprite sheet into appropriate sprites
	 */
	private void updateHeadSprites() {
		Texture head_all;
		if (preview.gender == Gender.MALE) {
			head_all = preview.res.getTexture("hair-male-"
					+ preview.maleHairColor + "-" + preview.hairColor + "-all");
		} else {
			head_all = preview.res.getTexture("hair-female-"
					+ preview.femaleHairColor + "-" + preview.hairColor + "-all");
		}
		TextureRegion[][] head_split = TextureRegion.split(head_all, 32, 48);
		preview.frontLeft[PreviewSlot.HEAD] = head_split[0][0];
		preview.frontStill[PreviewSlot.HEAD] = head_split[0][1];
		preview.frontRight[PreviewSlot.HEAD] = head_split[0][2];
		preview.rightLeft[PreviewSlot.HEAD] = head_split[1][0];
		preview.rightStill[PreviewSlot.HEAD] = head_split[1][1];
		preview.rightRight[PreviewSlot.HEAD] = head_split[1][2];
		preview.leftLeft[PreviewSlot.HEAD] = head_split[2][0];
		preview.leftStill[PreviewSlot.HEAD] = head_split[2][1];
		preview.leftRight[PreviewSlot.HEAD] = head_split[2][2];
		preview.backLeft[PreviewSlot.HEAD] = head_split[3][0];
		preview.backStill[PreviewSlot.HEAD] = head_split[3][1];
		preview.backRight[PreviewSlot.HEAD] = head_split[3][2];
	}

	private void updateArmorSprites() {
		TextureRegion[][] armor_split =
				TextureRegion.split(preview.equips.getBodyArmorPreview(), 32, 48);
		preview.frontLeft[PreviewSlot.BODY] = armor_split[0][0];
		preview.frontStill[PreviewSlot.BODY] = armor_split[0][1];
		preview.frontRight[PreviewSlot.BODY] = armor_split[0][2];
		preview.rightLeft[PreviewSlot.BODY] = armor_split[1][0];
		preview.rightStill[PreviewSlot.BODY] = armor_split[1][1];
		preview.rightRight[PreviewSlot.BODY] = armor_split[1][2];
		preview.leftLeft[PreviewSlot.BODY] = armor_split[2][0];
		preview.leftStill[PreviewSlot.BODY] = armor_split[2][1];
		preview.leftRight[PreviewSlot.BODY] = armor_split[2][2];
		preview.backLeft[PreviewSlot.BODY] = armor_split[3][0];
		preview.backStill[PreviewSlot.BODY] = armor_split[3][1];
		preview.backRight[PreviewSlot.BODY] = armor_split[3][2];
	}

	private void updateLeftHandSprites() {
		if (!preview.equips.isLeftHandSlotEmpty()) {
			TextureRegion[][] weapon_split =
					TextureRegion.split(preview.equips.getLeftHandPreview(), 32, 48);
			preview.frontLeft[PreviewSlot.LEFT_HAND] = weapon_split[0][0];
			preview.frontStill[PreviewSlot.LEFT_HAND] = weapon_split[0][1];
			preview.frontRight[PreviewSlot.LEFT_HAND] = weapon_split[0][2];
			preview.rightLeft[PreviewSlot.LEFT_HAND] = weapon_split[1][0];
			preview.rightStill[PreviewSlot.LEFT_HAND] = weapon_split[1][1];
			preview.rightRight[PreviewSlot.LEFT_HAND] = weapon_split[1][2];
			preview.leftLeft[PreviewSlot.LEFT_HAND] = weapon_split[2][0];
			preview.leftStill[PreviewSlot.LEFT_HAND] = weapon_split[2][1];
			preview.leftRight[PreviewSlot.LEFT_HAND] = weapon_split[2][2];
			preview.backLeft[PreviewSlot.LEFT_HAND] = weapon_split[3][0];
			preview.backStill[PreviewSlot.LEFT_HAND] = weapon_split[3][1];
			preview.backRight[PreviewSlot.LEFT_HAND] = weapon_split[3][2];
		}
	}

	private void updateRightHandSprites() {
		if (!preview.equips.isRightHandSlotEmpty()) {
			TextureRegion[][] weapon_split =
					TextureRegion.split(preview.equips.getRightHandPreview(), 32, 48);
			preview.frontLeft[PreviewSlot.RIGHT_HAND] = weapon_split[0][0];
			preview.frontStill[PreviewSlot.RIGHT_HAND] = weapon_split[0][1];
			preview.frontRight[PreviewSlot.RIGHT_HAND] = weapon_split[0][2];
			preview.rightLeft[PreviewSlot.RIGHT_HAND] = weapon_split[1][0];
			preview.rightStill[PreviewSlot.RIGHT_HAND] = weapon_split[1][1];
			preview.rightRight[PreviewSlot.RIGHT_HAND] = weapon_split[1][2];
			preview.leftLeft[PreviewSlot.RIGHT_HAND] = weapon_split[2][0];
			preview.leftStill[PreviewSlot.RIGHT_HAND] = weapon_split[2][1];
			preview.leftRight[PreviewSlot.RIGHT_HAND] = weapon_split[2][2];
			preview.backLeft[PreviewSlot.RIGHT_HAND] = weapon_split[3][0];
			preview.backStill[PreviewSlot.RIGHT_HAND] = weapon_split[3][1];
			preview.backRight[PreviewSlot.RIGHT_HAND] = weapon_split[3][2];
		}
	}

	/**
	 * Sends the specified character preview frame (ex: SWORD, HEAD, SHIELD) to the
	 * back of the character preview
	 *
	 * @param index specified character preview frame
	 * @param src   character preview frame
	 */
	static void toBackOfPreview(final int index, TextureRegion[][] src) {
		for (int i = 0; i < 3; i++) {
			src[i][index - 7] = src[i][index];
			src[i][index] = null;
		}
	}

	/**
	 * Sends the specified character preview frame (ex: SWORD, HEAD, SHIELD) to the
	 * front of the character preview
	 *
	 * @param index specified character preview frame
	 * @param src   character preview frame
	 */
	static void toFrontOfPreview(final int index, TextureRegion[][] src) {
		for (int i = 0; i < 3; i++) {
			src[i][index + 7] = src[i][index];
			src[i][index] = null;
		}
	}
}
