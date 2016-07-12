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
import com.jnv.betrayal.lobby.LobbyOptions;
import com.jnv.betrayal.lobby.inventory.Inventory;
import com.jnv.betrayal.lobby.shop.Shop;
import com.jnv.betrayal.lobby.social.PartyRoom;
import com.jnv.betrayal.lobby.stats.StatsWindow;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.scene2d.InputListener;

public class Lobby extends GameState {

	private int buttonWidth, buttonHeight, spacing;
	private Texture playButtonTexture;
	private Actor playNowButton;

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
				new PartyRoom(game);
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
		allPlayersBackground.setBounds(Betrayal.WIDTH / 2 + 10, 510, Betrayal.WIDTH / 2 - 20, Betrayal.HEIGHT / 3 + 175);
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

	private void loadPlayNowButton() {
		playButtonTexture = res.getTexture("play-now");
		playNowButton = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(playButtonTexture, playNowButton.getX(), playNowButton.getY(),
						playNowButton.getWidth(), playNowButton.getHeight());
			}
		};
		playNowButton.setWidth(512);
		playNowButton.setBounds((Betrayal.WIDTH - playNowButton.getWidth()) / 2, 20, 512, 144);
		playNowButton.addListener(new InputListener(playNowButton) {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				playButtonTexture = res.getTexture("play-now-pressed");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (x >= playNowButton.getX()
						&& x <= playNowButton.getX() + playNowButton.getWidth()
						&& y >= playNowButton.getY()
						&& y <= playNowButton.getY() + playNowButton.getHeight()) {
					gsm.setState(GameStateManager.State.DUNGEON);
				} else playButtonTexture = res.getTexture("play-now");
			}

			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				playButtonTexture = res.getTexture("play-now");
			}
		});
		stage.addActor(playNowButton);
	}


}
