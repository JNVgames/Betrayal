/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.inventory;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;

class EquipLoader {

	private Inventory inventory;
	private Character character;
	private Group equips, popup;

	EquipLoader(Inventory inventory) {
		this.inventory = inventory;
		character = inventory.character;
		equips = new Group();
		popup = inventory.popup;
	}

	void loadEquips() {
		final Equip[] allEquips = character.equips.equips;
		// Check each equip slot to see if it's empty
		for (int i = 0; i < Slot.SLOTS; i++) {
			final int index = i;
			// If the slot is not empty, display the item icon
			if (allEquips[i] != null) {
				Image equipIcon = new Image(allEquips[i].getItemIcon());
				equipIcon.setBounds(Slot.ALL_DIMENS[i]);
				equipIcon.addListener(new InputListener(equipIcon) {
					@Override
					public void doAction() {
						new ItemOptions(inventory, allEquips[index], Slot.ALL_DIMENS[index],
								inventory.game, index);
					}
				});
				equips.addActor(equipIcon);
			}
		}
		popup.addActor(equips);
	}

	void refresh() {
		equips.clear();
		loadEquips();
	}
}
