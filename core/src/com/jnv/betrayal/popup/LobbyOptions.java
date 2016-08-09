/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.online.Room;
import com.jnv.betrayal.popup.Instructions;
import com.jnv.betrayal.popup.Options;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class LobbyOptions extends Popup {

	Image background, lobbyButton, instructions, mainMenuButton;
	Label title;
	int width, height, padding;
	float xCoord;

	public LobbyOptions(Betrayal game) {
		super(game);
		width = 312;
		height = 100;
		padding = 50;
		xCoord = (Betrayal.WIDTH - width) / 2;
		loadButtons();
	}

	private void loadButtons() {
		loadBackground();
		loadTitle();
		loadInstructionsButton();
	//	loadOptionsButton();
		loadReturnToLobbyButton();
		loadReturnToMainMenuButton();
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(175, 350, Betrayal.WIDTH - 350, Betrayal.HEIGHT - 650);
		popup.addActor(background);
	}

	private void loadTitle() {
		title = new Label("Options", FontManager.getFont40());
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(background.getTop()- padding - title.getPrefHeight());
		popup.addActor(title);
	}

	private void loadInstructionsButton() {
		instructions = new Image(res.getTexture("instructions"));
		instructions.setWidth(width);
		instructions.setHeight(height);
		instructions.setX(xCoord);
		instructions.setY(title.getY() - height - padding);
		instructions.addListener(new InputListener(instructions) {
			@Override
			public void doAction() {
				new Instructions(game);
			}
		});
		popup.addActor(instructions);
	}

//	private void loadOptionsButton() {
//		Image options = new Image(res.getTexture("options"));
//		options.layout();
//		options.setBounds((Betrayal.WIDTH - options.getImageWidth()) / 2 + 100,
//				650, 312, 100);
//		options.addListener(new InputListener(options) {
//			@Override
//			public void doAction() {
//				new Options(game);
//			}
//		});
//		popup.addActor(options);
//	}

	private void loadReturnToLobbyButton() {
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.setX(xCoord);
		lobbyButton.setY(instructions.getY() - height - padding);
		lobbyButton.setWidth(width);
		lobbyButton.setHeight(height);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadReturnToMainMenuButton() {
		mainMenuButton = new Image(res.getTexture("main-menu"));
		mainMenuButton.setX(xCoord);
		mainMenuButton.setY(lobbyButton.getY() - height - padding);
		mainMenuButton.setWidth(width);
		mainMenuButton.setHeight(height);
		mainMenuButton.addListener(new InputListener(mainMenuButton) {
			@Override
			public void doAction() {
				Room room = game.getCurrentCharacter().getRoom();
				if (room.getSocket() != null && room.getSocket().connected()) {
					room.getSocket().disconnect();
				}
				remove();
				game.gsm.setState(GameStateManager.State.MENU);
			}
		});
		popup.addActor(mainMenuButton);
	}
}
