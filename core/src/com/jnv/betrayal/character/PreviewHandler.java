/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jnv.betrayal.character.utils.Gender;
import com.jnv.betrayal.character.utils.Slot;

class PreviewHandler {

	private Preview preview;

	PreviewHandler(Preview preview) {
		this.preview = preview;
	}

	void update() {
		updateHeadSprites();
		updateArmorSprites();
		updateShieldSprites();
		updateWeaponSprites();

		toBackOfPreview(Slot.WEAPON, preview.front);
		toFrontOfPreview(Slot.WEAPON, preview.back);
		toFrontOfPreview(Slot.HEAD, preview.back);
	}

	/**
	 * Update and split the sprite sheet into appropriate sprites
	 */
	private void updateHeadSprites() {
		Texture head_all;
		if (preview.gender == Gender.MALE) {
			head_all = preview.res.getTexture("hair-male-"
					+ preview.hair_male + "-" + preview.hairColor + "-all");
		} else {
			head_all = preview.res.getTexture("hair-female-"
					+ preview.hair_female + "-" + preview.hairColor + "-all");
		}
		TextureRegion[][] head_split = TextureRegion.split(head_all, 32, 48);
		preview.front_left[Slot.HEAD] = head_split[0][0];
		preview.front_still[Slot.HEAD] = head_split[0][1];
		preview.front_right[Slot.HEAD] = head_split[0][2];
		preview.right_left[Slot.HEAD] = head_split[1][0];
		preview.right_still[Slot.HEAD] = head_split[1][1];
		preview.right_right[Slot.HEAD] = head_split[1][2];
		preview.left_left[Slot.HEAD] = head_split[2][0];
		preview.left_still[Slot.HEAD] = head_split[2][1];
		preview.left_right[Slot.HEAD] = head_split[2][2];
		preview.back_left[Slot.HEAD] = head_split[3][0];
		preview.back_still[Slot.HEAD] = head_split[3][1];
		preview.back_right[Slot.HEAD] = head_split[3][2];
	}

	private void updateArmorSprites() {
		TextureRegion[][] armor_split =
				TextureRegion.split(preview.character.getEquips().getBodyArmorPreview(), 32, 48);
		preview.front_left[Slot.BODY] = armor_split[0][0];
		preview.front_still[Slot.BODY] = armor_split[0][1];
		preview.front_right[Slot.BODY] = armor_split[0][2];
		preview.right_left[Slot.BODY] = armor_split[1][0];
		preview.right_still[Slot.BODY] = armor_split[1][1];
		preview.right_right[Slot.BODY] = armor_split[1][2];
		preview.left_left[Slot.BODY] = armor_split[2][0];
		preview.left_still[Slot.BODY] = armor_split[2][1];
		preview.left_right[Slot.BODY] = armor_split[2][2];
		preview.back_left[Slot.BODY] = armor_split[3][0];
		preview.back_still[Slot.BODY] = armor_split[3][1];
		preview.back_right[Slot.BODY] = armor_split[3][2];
	}

	private void updateShieldSprites() {
		if (!preview.character.getEquips().isShieldSlotEmpty()) {
			TextureRegion[][] shield_split =
					TextureRegion.split(preview.character.getEquips().getShieldPreview(), 32, 48);
		}
	}

	private void updateWeaponSprites() {
		if (!preview.character.getEquips().isWeaponSlotEmpty()) {
			TextureRegion[][] weapon_split =
					TextureRegion.split(preview.character.getEquips().getWeaponPreview(), 32, 48);
			preview.front_left[Slot.WEAPON] = weapon_split[0][0];
			preview.front_still[Slot.WEAPON] = weapon_split[0][1];
			preview.front_right[Slot.WEAPON] = weapon_split[0][2];
			preview.right_left[Slot.WEAPON] = weapon_split[1][0];
			preview.right_still[Slot.WEAPON] = weapon_split[1][1];
			preview.right_right[Slot.WEAPON] = weapon_split[1][2];
			preview.left_left[Slot.WEAPON] = weapon_split[2][0];
			preview.left_still[Slot.WEAPON] = weapon_split[2][1];
			preview.left_right[Slot.WEAPON] = weapon_split[2][2];
			preview.back_left[Slot.WEAPON] = weapon_split[3][0];
			preview.back_still[Slot.WEAPON] = weapon_split[3][1];
			preview.back_right[Slot.WEAPON] = weapon_split[3][2];
		}
	}

	/**
	 * Sends the specified character preview frame (ex: SWORD, HEAD, SHIELD) to the
	 * back of the character preview
	 *
	 * @param index specified character preview frame
	 * @param src   character preview frame
	 */
	static void toBackOfPreview(int index, TextureRegion[][] src) {
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

	static void toFrontOfPreview(int index, TextureRegion[][] src) {
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
}
