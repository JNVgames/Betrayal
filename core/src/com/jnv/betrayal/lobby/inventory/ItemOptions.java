/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.Item;
import com.jnv.betrayal.gameobjects.Usables;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

class ItemOptions extends Popup {

	public final Dimension itemBoxDimen;
	private final int unequipSlot;
	private InventoryLoadable inventory;
	private Image background;
	private Item item;
	private boolean isEquippable, isEquipped;

	ItemOptions(InventoryLoadable inventory, Item item, Dimension itemBoxDimen, Betrayal game) {
		this(inventory, item, itemBoxDimen, game, -1);
	}

	ItemOptions(InventoryLoadable inventory, Item equip, Dimension itemBoxDimen, Betrayal game,
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
		if (isEquippable && !isEquipped) {
			numOptions = 4;
			background = new Image(res.getTexture("4option-background"));
		} else {
			numOptions = 3;
			background = new Image(res.getTexture("3option-background"));
		}
		background.layout();

		for (int i = 0; i < numOptions; i++) {
			// If there's enough space to the right of the item, draw labels
			if (itemBoxDimen.getRight() + optionWidth < Betrayal.WIDTH)
				dimensions.add(new Dimension(itemBoxDimen.getRight(),
						itemBoxDimen.getTop() - optionHeight * (i + 1), optionWidth, optionHeight));
				// Else draw to the left of the item
			else
				dimensions.add(new Dimension(itemBoxDimen.getX() - 20 - optionWidth,
						itemBoxDimen.getTop() - optionHeight * (i + 1), optionWidth, optionHeight));
		}
		float x = dimensions.get(0).getX();
		float y = dimensions.get(numOptions - 1).getY();
		float width = dimensions.get(0).getWidth();
		float height = dimensions.get(0).getHeight() * numOptions;
		background.setBounds(x, y, width, height);
		popup.addActor(background);

		// Draw out options
		if (inventory instanceof DungeonInventory) {
			setDungeonOptions(dimensions);
		} else {
			setOptions(dimensions);
		}
	}

	private void setDungeonOptions(List<Dimension> dimensions) {
		int counter = 0;
		// Add options
		popup.addActor(createDungeonOptions(dimensions.get(counter++), Option.Use));
		popup.addActor(createDungeonOptions(dimensions.get(counter++), Option.Info));
		popup.addActor(createDungeonOptions(dimensions.get(counter), Option.Cancel));
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

	private Label createDungeonOptions(Dimension dimens, Option option) {
		Label label = new Label(option.toString(), FontManager.getFont60());
		label.setBounds(dimens);
		switch (option) {
			case Use:
				label.addListener(new InputListener(label) {
					@Override
					public void doAction() {
						inventory.getCharacter().inventory.removeItem(item);

						Effect effect = ((Usables) item).getEffect();
						Card src = ((DungeonInventory) inventory).getCard();
						List<Card> dest = new ArrayList<Card>();

						effect.setSrc(src);
						dest.add(src);
						effect.setDest(dest);
						src.performEffect(effect);
						remove();
						((DungeonInventory) inventory).remove();
						src.getField().turnManager.nextTurn();
					}
				});
				break;
			case Info:
				label.addListener(new InputListener(label) {
					@Override
					public void doAction() {
						new OKPopup(game, item.getDescription());
					}
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

	private Label createOptionLabel(Dimension dimens, Option option) {
		Label label = new Label(option.toString(), FontManager.getFont60());
		label.setBounds(dimens);
		switch (option) {
			case Equip:
				label.addListener(new InputListener(label) {
					@Override
					public void doAction() {
						game.getCurrentCharacter().equips.equip((Equip) item);
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
							game.getCurrentCharacter().equips.unequip(unequipSlot);
						inventory.refresh();
						remove();
					}
				});
				break;
			case Info:
				label.addListener(new InputListener(label) {
					@Override
					public void doAction() {
						new OKPopup(game, item.getDescription());
					}
				});
				break;
			case Sell:
				label.addListener(new InputListener(label) {
					@Override
					public void doAction() {
						new Confirmation(game, "Are you sure?") {
							@Override
							public void doAction() {
								inventory.getCharacter().inventory.sellItem(item);
								removeThisPopup();
								inventory.refresh();
							}
						};
					}
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

	private void removeThisPopup() {
		remove();
	}
}
