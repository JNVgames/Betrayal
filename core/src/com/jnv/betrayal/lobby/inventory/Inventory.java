/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.inventory;

import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;
import com.jnv.betrayal.scene2d.ui.Label;

public class Inventory extends Popup {

	private Image lobbyButton, goldIcon;
	private Image[] inventorySpots, characterOutline;
	private Label userGold;
	private Label[] charOutDescription;
	private ItemLoader itemLoader;
	private EquipLoader equipLoader;
	Label title;
	Image background;
	Character character;
	Betrayal game;

	public Inventory(Betrayal game) {
		super(game);
		this.game = game;
		inventorySpots = new Image[20];
		characterOutline = new Image[8];
		charOutDescription = new Label[7];
		character = game.getCurrentCharacter();
		loadButtons();
		itemLoader = new ItemLoader(this);
		itemLoader.loadInventory();
		loadGoldIcon();
		equipLoader = new EquipLoader(this);
		equipLoader.loadEquips();
	}

	private void loadButtons() {
		loadBackground();
		loadTitle();
		loadInventorySpots();
		loadEquipSpots();
		loadReturnToLobbyButton();
		loadSortButton();
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		popup.addActor(background);
	}

	private void loadTitle() {
		title = new Label("Inventory", FontManager.getFont(40));
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 200);
		popup.addActor(title);
	}

	private void loadGoldIcon() {
		goldIcon = new Image(res.getTexture("icon-gold"));
		goldIcon.layout();
		float x = inventorySpots[0].getX();
		float y = inventorySpots[0].getTop();
		goldIcon.setBounds(x, y + 10, 40, 40);
		popup.addActor(goldIcon);

		userGold = new Label(Integer.toString(character.inventory.getGold()),FontManager.getFont(40));
		userGold.setX(x + goldIcon.getWidth() + 10);
		userGold.setY(y + 7);
		popup.addActor(userGold);
	}



	private void loadReturnToLobbyButton() {
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 110, 312, 100);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadInventorySpots() {

		int padding = 10, itemSize = 92;
		float startingX = background.getX()+ itemSize + padding, startingY = title.getY() - 30-92;

		for (int i = 0; i < 20; i++) {
			inventorySpots[i] = new Image(res.getTexture("shop-purchase-background"));
			inventorySpots[i].layout();

			if (i < 5) {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 1) + padding * i,
						startingY, itemSize, itemSize);
			} else if (i >= 5 && i < 10) {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 6) + padding * (i - 5),
						startingY - itemSize - padding, itemSize, itemSize);
			} else if (i >= 10 && i < 15) {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 11) + padding * (i - 10),
						startingY - (itemSize + padding) * 2, itemSize, itemSize);
			} else {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 16) + padding * (i - 15),
						startingY - (itemSize + padding) * 3, itemSize, itemSize);
			}
			popup.addActor(inventorySpots[i]);
		}
	}

	private void loadEquipSpots() {
		int itemSize = 92;
		// character outline
		characterOutline[7] = new Image(res.getTexture("character-outline"));
		characterOutline[7].layout();
		characterOutline[7].setBounds(137, 225, 240, 400);
		for (int i = 0; i < 7; i++) {
			characterOutline[i] = new Image(res.getTexture("instructions-background"));
			characterOutline[i].layout();
		}
		// head
		characterOutline[0].setBounds(212, 525, itemSize, itemSize);
		// body armor
		characterOutline[1].setBounds(212, 425, itemSize, itemSize);
		// left hand
		characterOutline[2].setBounds(112, 325, itemSize, itemSize);
		// right hand
		characterOutline[3].setBounds(312, 325, itemSize, itemSize);
		// ring1
		characterOutline[4].setBounds(518, 325, itemSize, itemSize);
		// ring2
		characterOutline[5].setBounds(518, 225, itemSize, itemSize);
		// cloak
		characterOutline[6].setBounds(518, 525, itemSize, itemSize);

		// Labels
		// head
		charOutDescription[0] = new Label("Head", FontManager.getFont(40));
		charOutDescription[0].setBounds(112, 525, itemSize, itemSize);
		// body armor
		charOutDescription[1] = new Label("Body", FontManager.getFont(40));
		charOutDescription[1].setBounds(112, 425, itemSize, itemSize);
		//left hand
		charOutDescription[2] = new Label("Hand", FontManager.getFont(40));
		charOutDescription[2].setBounds(112, 265, itemSize, itemSize);
		// right hand
		charOutDescription[3] = new Label("Hand", FontManager.getFont(40));
		charOutDescription[3].setBounds(312, 265, itemSize, itemSize);
		// ring1
		charOutDescription[4] = new Label("Ring", FontManager.getFont(40));
		charOutDescription[4].setBounds(428, 325, itemSize, itemSize);
		// ring2
		charOutDescription[5] = new Label("Ring", FontManager.getFont(40));
		charOutDescription[5].setBounds(428, 225, itemSize, itemSize);
		// cloak
		charOutDescription[6] = new Label("Cloak", FontManager.getFont(40));
		charOutDescription[6].setBounds(413, 525, itemSize, itemSize);

		for (int i = 0; i < 8; i++) {
			popup.addActor(characterOutline[i]);
		}

		for (int i = 0; i < 7; i++) {
			popup.addActor(charOutDescription[i]);
		}
	}

	private void loadSortButton() {
		Label button_sort = new Label("Sort", FontManager.getFont(40));
		button_sort.setBounds(background.getX() + background.getWidth() - 30 - button_sort.getPrefWidth(),
				lobbyButton.getY() + lobbyButton.getHeight() + 842, button_sort.getPrefWidth(),
				button_sort.getPrefHeight());
		button_sort.addListener(new InputListener(button_sort) {
			@Override
			public void doAction() {
				character.inventory.sortItems();
				refresh();
			}
		});
		popup.addActor(button_sort);
	}

	void refresh() {
		userGold.setText(Integer.toString(character.inventory.getGold()));
		itemLoader.refresh();
		equipLoader.refresh();

	}
}
