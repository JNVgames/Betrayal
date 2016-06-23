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
		// If player has a cloak, don't render any other sprites
		if (!updateCloakSprites()) {
			updateHeadSprites();
			updateArmorSprites();
			updateLeftHandSprites();
			updateRightHandSprites();
			updateHelmetSprites();

			// Calibrate how the previews are drawn
			// Front and sides
			// Check if hand slots have swords
			if (preview.equips.isLeftHandSlotSword()) {
				toBackOfPreview(PreviewSlot.LEFT_HAND, preview.front);
			} else {
				toFrontOfPreview(PreviewSlot.LEFT_HAND, preview.front);
			}
			if (preview.equips.isRightHandSlotSword()) {
				toBackOfPreview(PreviewSlot.RIGHT_HAND, preview.front);
			} else {
				toFrontOfPreview(PreviewSlot.RIGHT_HAND, preview.front);
			}
			toBackOfPreview(PreviewSlot.BODY, preview.front);
			toBackOfPreview(PreviewSlot.HEAD, preview.front);

			// Back side
			if (!preview.equips.isLeftHandSlotSword()) {
				toFrontOfPreview(PreviewSlot.LEFT_HAND, preview.back);
			}
			if (!preview.equips.isRightHandSlotSword()) {
				toFrontOfPreview(PreviewSlot.RIGHT_HAND, preview.back);
			}
			if (preview.equips.isLeftHandSlotSword()) {
				toFrontOfPreview(PreviewSlot.LEFT_HAND, preview.back);
			}
			if (preview.equips.isRightHandSlotSword()) {
				toFrontOfPreview(PreviewSlot.RIGHT_HAND, preview.back);
			}
			toBackOfPreview(PreviewSlot.BODY, preview.back);
			toBackOfPreview(PreviewSlot.HEAD, preview.back);
		}
	}

	private boolean updateCloakSprites() {
		if (!preview.equips.isCloakSlotEmpty()) {
			TextureRegion[][] cloakTextures =
					TextureRegion.split(preview.equips.getCloakPreview(), 32, 48);
			preview.frontLeft[PreviewSlot.HEAD] = cloakTextures[0][0];
			preview.frontStill[PreviewSlot.HEAD] = cloakTextures[0][1];
			preview.frontRight[PreviewSlot.HEAD] = cloakTextures[0][2];
			preview.rightLeft[PreviewSlot.HEAD] = cloakTextures[1][0];
			preview.rightStill[PreviewSlot.HEAD] = cloakTextures[1][1];
			preview.rightRight[PreviewSlot.HEAD] = cloakTextures[1][2];
			preview.leftLeft[PreviewSlot.HEAD] = cloakTextures[2][0];
			preview.leftStill[PreviewSlot.HEAD] = cloakTextures[2][1];
			preview.leftRight[PreviewSlot.HEAD] = cloakTextures[2][2];
			preview.backLeft[PreviewSlot.HEAD] = cloakTextures[3][0];
			preview.backStill[PreviewSlot.HEAD] = cloakTextures[3][1];
			preview.backRight[PreviewSlot.HEAD] = cloakTextures[3][2];
			return true;
		}
		return false;
	}

	private void updateHelmetSprites() {
		if (!preview.equips.isHeadSlotEmpty() && !preview.isShowingHead) {
			TextureRegion[][] headTextures =
					TextureRegion.split(preview.equips.getHeadArmorPreview(), 32, 48);
			preview.frontLeft[PreviewSlot.HEAD] = headTextures[0][0];
			preview.frontStill[PreviewSlot.HEAD] = headTextures[0][1];
			preview.frontRight[PreviewSlot.HEAD] = headTextures[0][2];
			preview.rightLeft[PreviewSlot.HEAD] = headTextures[1][0];
			preview.rightStill[PreviewSlot.HEAD] = headTextures[1][1];
			preview.rightRight[PreviewSlot.HEAD] = headTextures[1][2];
			preview.leftLeft[PreviewSlot.HEAD] = headTextures[2][0];
			preview.leftStill[PreviewSlot.HEAD] = headTextures[2][1];
			preview.leftRight[PreviewSlot.HEAD] = headTextures[2][2];
			preview.backLeft[PreviewSlot.HEAD] = headTextures[3][0];
			preview.backStill[PreviewSlot.HEAD] = headTextures[3][1];
			preview.backRight[PreviewSlot.HEAD] = headTextures[3][2];
		}
	}

	/**
	 * Update and split the sprite sheet into appropriate sprites
	 */
	private void updateHeadSprites() {
		Texture head_all;
		if (preview.gender == Gender.MALE) {
			head_all = preview.res.getTexture("hair-male-"
					+ preview.maleHair + "-" + preview.hairColor + "-all");
		} else {
			head_all = preview.res.getTexture("hair-female-"
					+ preview.femaleHair + "-" + preview.hairColor + "-all");
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
		TextureRegion[][] armorTextures =
				TextureRegion.split(preview.equips.getBodyArmorPreview(), 32, 48);
		preview.frontLeft[PreviewSlot.BODY] = armorTextures[0][0];
		preview.frontStill[PreviewSlot.BODY] = armorTextures[0][1];
		preview.frontRight[PreviewSlot.BODY] = armorTextures[0][2];
		preview.rightLeft[PreviewSlot.BODY] = armorTextures[1][0];
		preview.rightStill[PreviewSlot.BODY] = armorTextures[1][1];
		preview.rightRight[PreviewSlot.BODY] = armorTextures[1][2];
		preview.leftLeft[PreviewSlot.BODY] = armorTextures[2][0];
		preview.leftStill[PreviewSlot.BODY] = armorTextures[2][1];
		preview.leftRight[PreviewSlot.BODY] = armorTextures[2][2];
		preview.backLeft[PreviewSlot.BODY] = armorTextures[3][0];
		preview.backStill[PreviewSlot.BODY] = armorTextures[3][1];
		preview.backRight[PreviewSlot.BODY] = armorTextures[3][2];
	}

	private void updateLeftHandSprites() {
		if (!preview.equips.isLeftHandSlotEmpty()) {
			TextureRegion[][] weaponTextures =
					TextureRegion.split(preview.equips.getLeftHandPreview(), 32, 48);
			preview.frontLeft[PreviewSlot.LEFT_HAND] = weaponTextures[0][0];
			preview.frontStill[PreviewSlot.LEFT_HAND] = weaponTextures[0][1];
			preview.frontRight[PreviewSlot.LEFT_HAND] = weaponTextures[0][2];
			preview.rightLeft[PreviewSlot.LEFT_HAND] = weaponTextures[1][0];
			preview.rightStill[PreviewSlot.LEFT_HAND] = weaponTextures[1][1];
			preview.rightRight[PreviewSlot.LEFT_HAND] = weaponTextures[1][2];
			preview.leftLeft[PreviewSlot.LEFT_HAND] = weaponTextures[2][0];
			preview.leftStill[PreviewSlot.LEFT_HAND] = weaponTextures[2][1];
			preview.leftRight[PreviewSlot.LEFT_HAND] = weaponTextures[2][2];
			preview.backLeft[PreviewSlot.LEFT_HAND] = weaponTextures[3][0];
			preview.backStill[PreviewSlot.LEFT_HAND] = weaponTextures[3][1];
			preview.backRight[PreviewSlot.LEFT_HAND] = weaponTextures[3][2];
		}
	}

	private void updateRightHandSprites() {
		if (!preview.equips.isRightHandSlotEmpty()) {
			TextureRegion[][] weaponTextures =
					TextureRegion.split(preview.equips.getRightHandPreview(), 32, 48);
			preview.frontLeft[PreviewSlot.RIGHT_HAND] = weaponTextures[0][0];
			preview.frontStill[PreviewSlot.RIGHT_HAND] = weaponTextures[0][1];
			preview.frontRight[PreviewSlot.RIGHT_HAND] = weaponTextures[0][2];
			preview.rightLeft[PreviewSlot.RIGHT_HAND] = weaponTextures[1][0];
			preview.rightStill[PreviewSlot.RIGHT_HAND] = weaponTextures[1][1];
			preview.rightRight[PreviewSlot.RIGHT_HAND] = weaponTextures[1][2];
			preview.leftLeft[PreviewSlot.RIGHT_HAND] = weaponTextures[2][0];
			preview.leftStill[PreviewSlot.RIGHT_HAND] = weaponTextures[2][1];
			preview.leftRight[PreviewSlot.RIGHT_HAND] = weaponTextures[2][2];
			preview.backLeft[PreviewSlot.RIGHT_HAND] = weaponTextures[3][0];
			preview.backStill[PreviewSlot.RIGHT_HAND] = weaponTextures[3][1];
			preview.backRight[PreviewSlot.RIGHT_HAND] = weaponTextures[3][2];
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
			for (int j = 0; j < 7; j++) {
				if (src[i][j] == null) {
					src[i][j] = src[i][index];
					src[i][index] = null;
					break;
				}
			}
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
			for (int j = 20; j > 13; j--) {
				if (src[i][j] == null) {
					src[i][j] = src[i][index];
					src[i][index] = null;
					break;
				}
			}
		}
	}
}
