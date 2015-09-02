/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.gameobjects.Character;
import com.jnv.betrayal.gameobjects.Item;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;

public class Inventory extends Popup {

	private Image lobbyButton, background;
	private Label title;
	private Actor mask;
	private Group inventory;
	private Label button_sort;
	private Character character;
	private Image [] inventorySpots, characterOutline;
	private Label [] charOutDescription;

	public Inventory(Betrayal game) {
		super(game);
		inventorySpots = new Image[20];
		characterOutline = new Image[8];
		charOutDescription = new Label [7];
		character = game.getPlayer().getCurrentCharacter();
		loadButtons();
	}

	private void loadButtons() {
		loadMask();
		loadBackground();
		loadTitle();
		loadContent();
		loadInventorySpots();
		loadEquipSpots();
		loadReturnToLobbyButton();
		loadInventory();
		loadSortButton();
	}

	private void loadMask() {
		mask = new Actor();
		mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		mask.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				removeInventory();
			}
		});
		stage.addActor(mask);
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		stage.addActor(background);
	}

	private void loadTitle() {
		title = new Label("Inventory", FontManager.getFont(40));
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 200);
		stage.addActor(title);
	}

	private void loadReturnToLobbyButton() {
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 110, 312, 100);
		lobbyButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				removeInventory();
			}
		});
		stage.addActor(lobbyButton);
	}

	private void loadContent() {

	}
	private void loadInventorySpots(){

		int padding = 10, itemSize = 92;
		float startingX = background.getX()+ itemSize + padding, startingY = title.getY() - 30-92;

		for (int i = 0; i <20; i++) {
			inventorySpots[i] = new Image(res.getTexture("shop-purchase-background"));
			inventorySpots[i].layout();

			if (i < 5) {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 1) + padding * i,
						startingY, itemSize, itemSize);
			} else if (i>=5 && i<10) {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 6) + padding * (i-5),
						startingY-itemSize-padding, itemSize, itemSize);
			} else if (i>=10 && i<15) {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 11) + padding * (i-10),
						startingY-(itemSize+padding)*2, itemSize, itemSize);
			} else {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 16) + padding * (i-15),
						startingY-(itemSize+padding)*3, itemSize, itemSize);
			}
			stage.addActor(inventorySpots[i]);
		}
	}

	private void loadEquipSpots(){
		int itemSize = 92;
		// character outline
		characterOutline[7] = new Image(res.getTexture("character-outline"));
		characterOutline[7].layout();
		characterOutline[7].setBounds(137, 225, 240, 400);
		for (int i=0;i<7; i++){
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

		for (int i=0;i<8; i++){
			stage.addActor(characterOutline[i]);
		}

		for (int i = 0; i<7; i++){
			stage.addActor(charOutDescription[i]);
		}



	}
	private void removeInventory() {
		mask.remove();
		title.remove();
		background.remove();
		lobbyButton.remove();
		for (int i = 0; i<20; i++){
			inventorySpots[i].remove();
		}
		for (int i = 0; i<8; i++){
			characterOutline[i].remove();
		}
		for (int i = 0; i<7; i++){
			charOutDescription[i].remove();
		}
		inventory.remove();
		button_sort.remove();
	}

	private void loadInventory() {
		inventory = new Group();
		Item[][] items = character.getInventory().getItems(6, 5);
		Actor[][] itemsDisplay = new Actor[items.length][items[0].length];

		float padding = 10;
		float sideLength = (background.getWidth() - (items[0].length + 1) * padding) / items[0].length;
		float startingX = background.getX(), startingY = title.getY() - 30;

		for (int row = 0; row < itemsDisplay.length; row++) {
			for (int col = 0; col < itemsDisplay[row].length; col++) {
				if (items[row][col] != null) {
					itemsDisplay[row][col] = loadInventoryBox(
							startingX + (col + 1) * padding + col * sideLength,
							startingY - row * padding - row * sideLength,
							sideLength, items[row][col]
					);
					inventory.addActor(itemsDisplay[row][col]);
				}
			}
		}
		stage.addActor(inventory);
	}

	private void loadSortButton() {
		button_sort = new Label("Sort", FontManager.getFont(40));
		button_sort.setBounds(background.getX() + background.getWidth() - 30 - button_sort.getPrefWidth(),
				lobbyButton.getY() + lobbyButton.getHeight() + 842, button_sort.getPrefWidth(),
				button_sort.getPrefHeight());
		button_sort.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				character.getInventory().sortItems();
				inventory.remove();
				loadInventory();
			}
		});
		stage.addActor(button_sort);
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
}
