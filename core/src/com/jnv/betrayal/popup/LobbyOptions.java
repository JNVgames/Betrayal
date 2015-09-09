/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;

public class LobbyOptions extends Popup {

	private Image lobbyButton, mainMenuButton, background, instructions, options;
	private Label title;
	private Actor mask;

	public LobbyOptions(Betrayal game) {
		super(game);
		loadButtons();
	}

	private void loadButtons() {
		loadMask();
		loadBackground();
		loadTitle();
		loadContent();
		loadInstructionsButton();
		loadOptionsButton();
		loadReturnToLobbyButton();
		loadReturnToMainMenuButton();
	}

	private void loadMask() {
		mask = new Actor();
		mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		mask.addListener(new com.jnv.betrayal.scene2d.InputListener(mask) {
			@Override
			public void doAction() {
				removeLobbyOptons();
			}
		});
		stage.addActor(mask);
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(175, 300, Betrayal.WIDTH - 350, Betrayal.HEIGHT - 550);
		stage.addActor(background);
	}

	private void loadTitle() {
		title = new Label("Options", FontManager.getFont(40));
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 350);
		stage.addActor(title);
	}

	private void loadInstructionsButton() {
		instructions = new Image(res.getTexture("instructions"));
		instructions.layout();
		instructions.setBounds((Betrayal.WIDTH - instructions.getImageWidth()) / 2 + 100,
				800, 312, 100);
		instructions.addListener(new com.jnv.betrayal.scene2d.InputListener(instructions) {
			@Override
			public void doAction() {
				new Instructions(game);
			}
		});
		stage.addActor(instructions);
	}

	private void loadOptionsButton() {
		options = new Image(res.getTexture("options"));
		options.layout();
		options.setBounds((Betrayal.WIDTH - options.getImageWidth()) / 2 + 100,
				650, 312, 100);
		options.addListener(new com.jnv.betrayal.scene2d.InputListener(options) {
			@Override
			public void doAction() {
				new Options(game);
			}
		});
		stage.addActor(options);
	}

	private void loadReturnToLobbyButton() {
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 350, 312, 100);
		lobbyButton.addListener(new com.jnv.betrayal.scene2d.InputListener(lobbyButton) {
			@Override
			public void doAction() {
				removeLobbyOptons();
			}
		});
		stage.addActor(lobbyButton);
	}

	private void loadReturnToMainMenuButton() {
		mainMenuButton = new Image(res.getTexture("main-menu"));
		mainMenuButton.layout();
		mainMenuButton.setBounds((Betrayal.WIDTH - mainMenuButton.getWidth()) / 2 + 100, 500, 312, 100);
		mainMenuButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				removeLobbyOptons();
				game.gsm.setState(GameStateManager.State.MENU);
			}
		});
		stage.addActor(mainMenuButton);
	}

	private void loadContent() {

	}

	private void removeLobbyOptons() {
		mask.remove();
		title.remove();
		background.remove();
		lobbyButton.remove();
		mainMenuButton.remove();
		options.remove();
		instructions.remove();
	}
}
