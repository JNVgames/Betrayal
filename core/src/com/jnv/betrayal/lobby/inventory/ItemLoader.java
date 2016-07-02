/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.Item;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;

class ItemLoader {

	private InventoryLoadable inventory;
	private Group inventoryItems, popup;
	private Image background;
	private Character character;

	ItemLoader(InventoryLoadable inventory) {
		this.inventory = inventory;
		popup = inventory.getPopup();
		character = inventory.getCharacter();
		background = inventory.getBackground();
	}

	void loadInventory() {
		inventoryItems = new Group();
		Item[][] items = character.inventory.getItems(6, 5, inventory instanceof Inventory);
		Actor[][] itemsDisplay = new Actor[items.length][items[0].length];

		float padding = 10;
		float sideLength = (background.getWidth() - (items[0].length + 1) * padding) / items[0].length;
		float startingX = background.getX(), startingY = inventory.getTitleActor().getY() - 30;

		for (int row = 0; row < itemsDisplay.length; row++) {
			for (int col = 0; col < itemsDisplay[row].length; col++) {
				if (items[row][col] != null) {
					itemsDisplay[row][col] = loadInventoryBox(
							startingX + (col + 1) * padding + col * sideLength,
							startingY - row * padding - row * sideLength,
							sideLength, items[row][col]
					);
					inventoryItems.addActor(itemsDisplay[row][col]);
					final Dimension itemDimens = new Dimension(
							itemsDisplay[row][col].getX(),
							itemsDisplay[row][col].getY(),
							itemsDisplay[row][col].getWidth(),
							itemsDisplay[row][col].getHeight()
					);
					final Item item = items[row][col];
					itemsDisplay[row][col].addListener(new InputListener(itemsDisplay[row][col]) {
						@Override
						public void doAction() {
							new ItemOptions(inventory, item, itemDimens, inventory.getGame());
						}
					});
				}
			}
		}
		popup.addActor(inventoryItems);
	}

	private Actor loadInventoryBox(float x, float topY, final float sideLength, final Item item) {
		final Texture itemImage = item.getItemIcon();
		Actor invBox = new Actor() {
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(itemImage, this.getX(), this.getY(),
						sideLength, sideLength);
			}
		};
		invBox.setBounds(x, topY - sideLength, sideLength, sideLength);
		return invBox;
	}

	void refresh() {
		inventoryItems.clear();
		loadInventory();
	}
}
