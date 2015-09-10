/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.inventory;

import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.Item;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

class ItemOptions extends Popup {

	private Inventory inventory;
	private Item item;
	public final Dimension itemBoxDimen;
	private boolean isEquippable, isEquipped;
	private final int unequipSlot;

	ItemOptions(Inventory inventory, Item item, Dimension itemBoxDimen, Betrayal game) {
		this(inventory, item, itemBoxDimen, game, -1);
	}

	ItemOptions(Inventory inventory, Item equip, Dimension itemBoxDimen, Betrayal game,
				int unequipSlot) {
		super(game);
		this.inventory = inventory;
		this.item = equip;
		this.itemBoxDimen = itemBoxDimen;
		isEquippable = item.isEquippable();
		this.isEquipped = unequipSlot != -1;
		this.unequipSlot = unequipSlot;
		showItemOptions();
	}

	private void showItemOptions() {
		List<Dimension> dimensions = new ArrayList<Dimension>();

		// Options values
		int numOptions;
		float optionWidth = 200;
		float optionHeight = 71;

		// If item is equippable, you have the option to Equip, Check item info, Sell, or Cancel
		if (isEquippable) {
			numOptions = 4;
		} else numOptions = 3;

		for (int i = 0; i < numOptions; i++) {
			// If there's enough space to the right of the item, draw labels
			if (itemBoxDimen.getRightX() + optionWidth < Betrayal.WIDTH)
				dimensions.add(new Dimension(itemBoxDimen.getRightX() + 20,
						itemBoxDimen.getTopY() - optionHeight * (i + 1), optionWidth, optionHeight));
			// Else draw to the left of the item
			else
				dimensions.add(new Dimension(itemBoxDimen.getX() - 20 - optionWidth,
						itemBoxDimen.getTopY() - optionHeight * (i + 1), optionWidth, optionHeight));
		}
		// Draw out options
		setOptions(dimensions);
	}

	private void setOptions(List<Dimension> dimensions) {
		int counter = 0;
		// If item is equipped, show unequip option
		if (isEquipped) {
			popup.addActor(createOptionLabel(dimensions.get(counter++), Option.Unequip));
		} else if (isEquippable) {
			popup.addActor(createOptionLabel(dimensions.get(counter++), Option.Equip));
		}
		popup.addActor(createOptionLabel(dimensions.get(counter++), Option.Info));
		if (!isEquipped) popup.addActor(createOptionLabel(dimensions.get(counter++), Option.Sell));
		popup.addActor(createOptionLabel(dimensions.get(counter), Option.Cancel));
	}

	private Label createOptionLabel(Dimension dimens, Option option) {
		Label label = new Label(option.toString(), FontManager.getFont(60));
		label.setBounds(dimens);
		switch (option) {
			case Equip:
				label.addListener(new InputListener(label) {
					@Override
					public void doAction() {
						game.getPlayer().getCurrentCharacter().equips.equip((Equip) item);
						inventory.refresh();
						remove();
					}
				});
				break;
			case Unequip:
				label.addListener(new InputListener(label) {
					@Override
					public void doAction() {
						if (Betrayal.debug && unequipSlot != -1)
							game.getPlayer().getCurrentCharacter().equips.unequip(unequipSlot);
						inventory.refresh();
						remove();
					}
				});
			case Info:
				label.addListener(new InputListener(label) {
					// stub - show info
				});
				break;
			case Sell:
				label.addListener(new InputListener(label) {
					// stub - add gold to inventory
				});
				break;
			case Cancel:
				label.addListener(new InputListener(label) {
					@Override
					public void doAction() {
						remove();
					}
				});
				break;
			default:
				throw new AssertionError("ItemOptions.java: Option doesn't exist");
		}
		return label;
	}
}
