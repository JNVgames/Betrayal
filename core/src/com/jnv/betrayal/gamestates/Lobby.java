/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.character.*;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.lobby.LobbyOptions;
import com.jnv.betrayal.lobby.inventory.Inventory;
import com.jnv.betrayal.lobby.shop.Shop;
import com.jnv.betrayal.lobby.social.PartyRoom;
import com.jnv.betrayal.lobby.stats.StatsWindow;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.online.Room;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Label;

import java.awt.Font;
import java.util.EventListener;

public class Lobby extends GameState {

	private int buttonWidth, buttonHeight, spacing;
	private Image allPlayersBackground;
	private Texture playButtonTexture;
	private Actor playNowButton;
	private Group partyMembers;
	private Room room;

	public Lobby(GameStateManager gsm) {
		super(gsm);
		buttonHeight = 150;
		buttonWidth = 144;
		spacing = 5;
		room = gsm.game.getCurrentCharacter().getRoom();
		System.out.println("awsraftsd" + room);
		partyMembers= new Group();
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
		allPlayersBackground = new Image(res.getTexture("player-background"));
		allPlayersBackground.layout();
		allPlayersBackground.setBounds(Betrayal.WIDTH / 2 + 10 - 100, 510, Betrayal.WIDTH / 2 - 20 + 100, Betrayal.HEIGHT / 3 + 175);
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
		tower.addListener(new InputListener(tower) {
			@Override
			public void doAction() {
				Lobby.this.refresh();
			}
		});
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

	public void refresh() {
		partyMembers.clear();
		loadRoomParty();
	}

	private void loadRoomParty(){
		float width = allPlayersBackground.getImageWidth()-20;
		float height = (allPlayersBackground.getImageHeight()-50)/4;
		float x = allPlayersBackground.getX() + 10;
		float y = allPlayersBackground.getTop() - 10;
		int i = 1;
		for(Character character : room.getCharacters()){
			addPlayerImage(character, x, (y - (i * height) - (i * 10)), width, height);
			i++;
		}
		stage.addActor(partyMembers);
	}


	private void addPlayerImage(final Character character, float x, float y,float width, float height){
		Group player = new Group();

		//creating the teammate preview
		final float xPos = x -20;
		final float yPos = y + (height - 72) / 2 - 115;
		final float previewWidth = 48 * 3;
		final float previewHeight = 72 * 3;

		Actor preview = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				character.preview.drawHeadPreview(batch, xPos, yPos, previewWidth, previewHeight);
			}
		};
		preview.setWidth(previewWidth);
		preview.setHeight(previewHeight);
		preview.setX(xPos);
		preview.setY(yPos);
		preview.setTouchable(Touchable.disabled);
		player.addActor(preview);

		//create name label
		Label name = new Label(character.getName(), FontManager.getFont40());
		name.setX(x + 20 + (width - name.getPrefWidth()) / 2);
		name.setY(y + ((height - name.getPrefHeight()) / 2)+20);
		player.addActor(name);

		//create floor Label
		Label floor = new Label("Floor: "+ character.stats.getFloor(), FontManager.getFont40());
		floor.setX(x + 20 + (width - floor.getPrefWidth()) / 2);
		floor.setY(name.getY() - floor.getPrefHeight() - 5 + 15);
		player.addActor(floor);

		//Create Ready Light
		Image ready = new Image(res.getTexture("green-circle"));	//todo check if ready or not
		ready.setWidth(20);
		ready.setHeight(20);
		ready.setX(x + width - 30);
		ready.setY(y + (height + 20) / 2 - 25);
		player.addActor(ready);

		//used so player is clickable
		Image mask = new Image();
		mask.setWidth(width);
		mask.setHeight(height);
		mask.setX(x);
		mask.setY(y);
		mask.addListener(new InputListener(mask) {
			@Override
			public void doAction() {
				new OKPopup(game, "STUB");
				//new OKPopup(character.stats.getTotalAttack());
			}
		});

		partyMembers.addActor(player);

	}

}
