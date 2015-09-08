/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.inputprocessors.InputListener;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.FriendsList;
import com.jnv.betrayal.popup.Inventory;
import com.jnv.betrayal.popup.LobbyOptions;
import com.jnv.betrayal.popup.Shop;
import com.jnv.betrayal.popup.StatsWindow;

public class Lobby extends GameState {

	private int buttonWidth, buttonHeight, spacing;
	private Texture image_button_play;
	private Actor button_play_now;

	public Lobby(GameStateManager gsm) {
		super(gsm);
		buttonHeight = 150;
		buttonWidth = 144;
		spacing = 5;
		loadContent();
	}

	public void update(float dt) {
		stage.act(dt);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	public void dispose() {
	}

	// Helpers
	private void loadBackground() {
		Image lobbyBackground = new Image(res.getTexture("instructions-background"));
		lobbyBackground.layout();
		lobbyBackground.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		stage.addActor(lobbyBackground);
	}

	private void loadShopButton() {
		Image shopButton = new Image(res.getTexture("lobby-shop"));
		shopButton.layout();
		shopButton.setBounds(0, Betrayal.HEIGHT - buttonHeight - 5, buttonWidth, buttonHeight);
		shopButton.addListener(new InputListener(shopButton) {
			@Override
			public void doAction() {
				new Shop(game);
			}
		});
		stage.addActor(shopButton);
	}

	private void loadInventoryButton() {
		Image inventoryButton = new Image(res.getTexture("lobby-inventory"));
		inventoryButton.layout();
		inventoryButton.setBounds(buttonWidth, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		inventoryButton.addListener(new InputListener(inventoryButton) {
			@Override
			public void doAction() {
				new Inventory(game);
			}
		});
		stage.addActor(inventoryButton);
	}

	private void loadStatsButton() {
		Image statsButton = new Image(res.getTexture("lobby-stats"));
		statsButton.layout();
		statsButton.setBounds(buttonWidth * 2, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		statsButton.addListener(new InputListener(statsButton) {
			@Override
			public void doAction() {
				new StatsWindow(game);
			}
		});
		stage.addActor(statsButton);
	}

	private void loadPartyButton() {
		Image partyButton = new Image(res.getTexture("lobby-party"));
		partyButton.layout();
		partyButton.setBounds(buttonWidth * 3, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		partyButton.addListener(new InputListener(partyButton) {
			@Override
			public void doAction() {
				new FriendsList(game);
			}
		});
		stage.addActor(partyButton);
	}

	private void loadSettingsButton() {
		Image settingsButton = new Image(res.getTexture("lobby-settings"));
		settingsButton.layout();
		settingsButton.setBounds(buttonWidth * 4, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		settingsButton.addListener(new InputListener(settingsButton) {
			@Override
			public void doAction() {
				new LobbyOptions(game);
			}
		});
		stage.addActor(settingsButton);
	}

	private void loadAllPlayersBackground() {
		Image allPlayersBackground = new Image(res.getTexture("lobby-screen"));
		allPlayersBackground.layout();
		allPlayersBackground.setBounds(Betrayal.WIDTH /2+10, 510, Betrayal.WIDTH /2 - 20, Betrayal.HEIGHT / 3 + 175);
		stage.addActor(allPlayersBackground);
	}

	private void loadChatBackground() {
		Image chatBackground = new Image(res.getTexture("lobby-screen"));
		chatBackground.layout();
		chatBackground.setBounds(10, 175, Betrayal.WIDTH - 20, Betrayal.HEIGHT / 4);
		stage.addActor(chatBackground);
	}

	private void loadTower() {
		Image tower = new Image(res.getTexture("lobby-tower"));
		tower.layout();
		tower.setBounds(20, 510, 175, Betrayal.HEIGHT / 3 + 175);
		stage.addActor(tower);
	}

	private void loadPartyLevels() {
		//TODO: Finish this
	}

	private void loadContent() {
		loadBackground();
		loadAllPlayersBackground();
		loadChatBackground();
		loadTower();
		loadPartyLevels();
		loadShopButton();
		loadSettingsButton();
		loadPartyButton();
		loadStatsButton();
		loadInventoryButton();

		//loadLobbyLabel(); ERror>/ FIX later

		// VINECNTS SHIT DELETE LOOK AT BOTTOM TO SEE WHY
		loadPlayNowButton();

	}

    /*
	private void loadLobbyLabel() {
        lobby = new Label("Lobby", labelStyle);
        lobby.setX(0);
        lobby.setY(Betrayal.HEIGHT - buttonHeight-lobby.getHeight());
        stage.addActor(lobby);
    }

   */

	// VINCENTS SHIT. LOADS THE PLAY BUTTON FOR DUNGEON TESTING. YOU CAN DELETE WHENEVER
	private void loadPlayNowButton() {
		image_button_play = res.getTexture("play-now");
		button_play_now = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(image_button_play, button_play_now.getX(), button_play_now.getY(),
						button_play_now.getWidth(), button_play_now.getHeight());
			}
		};
		button_play_now.setWidth(512);
		button_play_now.setBounds((Betrayal.WIDTH - button_play_now.getWidth()) / 2, 20, 512, 144);
		button_play_now.addListener(new InputListener(button_play_now) {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				image_button_play = res.getTexture("play-now-pressed");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (x >= button_play_now.getX()
						&& x <= button_play_now.getX() + button_play_now.getWidth()
						&& y >= button_play_now.getY()
						&& y <= button_play_now.getY() + button_play_now.getHeight()) {
					gsm.setState(GameStateManager.State.DUNGEON);
				} else image_button_play = res.getTexture("play-now");
			}

			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				image_button_play = res.getTexture("play-now");
			}
		});
		stage.addActor(button_play_now);
	}


}
