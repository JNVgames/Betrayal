/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby;

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

	public LobbyOptions(Betrayal game) {
		super(game);
		loadButtons();
	}

	private void loadButtons() {
		loadBackground();
		loadTitle();
		loadInstructionsButton();
		loadOptionsButton();
		loadReturnToLobbyButton();
		loadReturnToMainMenuButton();
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(175, 300, Betrayal.WIDTH - 350, Betrayal.HEIGHT - 550);
		popup.addActor(background);
	}

	private void loadTitle() {
		Label title = new Label("Options", FontManager.getFont40());
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 350);
		popup.addActor(title);
	}

	private void loadInstructionsButton() {
		Image instructions = new Image(res.getTexture("instructions"));
		instructions.layout();
		instructions.setBounds((Betrayal.WIDTH - instructions.getImageWidth()) / 2 + 100,
				800, 312, 100);
		instructions.addListener(new InputListener(instructions) {
			@Override
			public void doAction() {
				new Instructions(game);
			}
		});
		popup.addActor(instructions);
	}

	private void loadOptionsButton() {
		Image options = new Image(res.getTexture("options"));
		options.layout();
		options.setBounds((Betrayal.WIDTH - options.getImageWidth()) / 2 + 100,
				650, 312, 100);
		options.addListener(new InputListener(options) {
			@Override
			public void doAction() {
				new Options(game);
			}
		});
		popup.addActor(options);
	}

	private void loadReturnToLobbyButton() {
		Image lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 350, 312, 100);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadReturnToMainMenuButton() {
		Image mainMenuButton = new Image(res.getTexture("main-menu"));
		mainMenuButton.layout();
		mainMenuButton.setBounds((Betrayal.WIDTH - mainMenuButton.getWidth()) / 2 + 100, 500, 312, 100);
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
