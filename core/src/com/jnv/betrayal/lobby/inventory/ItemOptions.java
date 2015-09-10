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

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

class ItemOptions extends Popup {

	private Item item;
	public final Dimension itemBoxDimen;
	private boolean isEquippable;

	public ItemOptions(Item item, Dimension itemBoxDimen, Betrayal game) {
		super(game);
		this.item = item;
		this.itemBoxDimen = itemBoxDimen;
		isEquippable = item.isEquippable();
		showItemOptions();
	}

	private void showItemOptions() {
		List<Dimension> dimensions = new ArrayList<Dimension>();

		// Options values
		float optionWidth = 200;
		float optionHeight = 71;

		// If there's enough space to the right of the item, draw labels
		if (itemBoxDimen.getRightX() + optionWidth < Betrayal.WIDTH) {
			// If item is equippable, you have the option to Equip, Check item info, Sell, or Cancel
			if (isEquippable) {
				for (int i = 0; i < 4; i++) {
					dimensions.add(new Dimension(itemBoxDimen.getRightX() + 20,
							itemBoxDimen.getTopY() - optionHeight * (i + 1), optionWidth, optionHeight));
				}
			}
		}
		// If there's not enough space, find the new dimensions and draw labels


		// Draw out options
		setOptions(dimensions);
	}

	private void setOptions(List<Dimension> dimensions) {
		int counter = 0;
		if (isEquippable) {
			popup.addActor(createEquipButton(dimensions.get(counter++), Option.EQUIP));
		}
		Label info = new Label("Info", FontManager.getFont(60));
		info.setBounds(dimensions.get(counter++));
		info.addListener(new InputListener(info) {
			@Override
			public void doAction() {
				// stub - show item information
			}
		});
		popup.addActor(info);
		Label sell = new Label("Sell", FontManager.getFont(60));
		sell.setBounds(dimensions.get(counter++));
		sell.addListener(new InputListener(sell) {
			@Override
			public void doAction() {
				// stub - sell from inventory
			}
		});
		popup.addActor(sell);
		Label cancel = new Label("Cancel", FontManager.getFont(60));
		cancel.setBounds(dimensions.get(counter));
		cancel.addListener(new InputListener(cancel) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(cancel);
	}

	private Label createEquipButton(Dimension dimens, Option option) {
		Label label = new Label("", FontManager.getFont(60));
		label.setBounds(dimens);
		switch (option) {
			case EQUIP:
				label.setText("Equip");
				label.addListener(new InputListener(label) {
					@Override
					public void doAction() {
						game.getPlayer().getCurrentCharacter().equips.equip((Equip) item);
						remove();
					}
				});
				break;
			case INFO:
				label.setText("Info");
				label.addListener(new InputListener(label) {
					// stub - show info
				});
				break;
			case SELL:
				label.setText("Sell");
				label.addListener(new InputListener(label));
		}
		return label;
	}
}
