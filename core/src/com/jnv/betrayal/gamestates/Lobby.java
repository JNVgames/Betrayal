/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.lobby.inventory.Inventory;
import com.jnv.betrayal.lobby.shop.Shop;
import com.jnv.betrayal.lobby.social.PartyRoom;
import com.jnv.betrayal.lobby.stats.StatsWindow;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.online.Room;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.popup.LobbyOptions;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Label;

public class Lobby extends GameState {

	private int buttonWidth, buttonHeight, spacing;
	private Image allPlayersBackground, chatBackground, tower;
	private Texture playButtonTexture, readyTexture, greenCircle, redCircle, yellowCircle, unReadyTexture;
	private Texture redT, blueT, purpleT, greenT;
	private Image[] triangles;
	private Actor playNowButton, readyButton, unReadyButton;
	private Group partyMembers, bottom, buttons, middle;
	private Room room;
	private Label roomNum, timeTilEnter, timerValue;
	private Timer timer;
	private Timer.Task task;
	private long savedTime;
	private int delay, timeLeft;
	private int zIndex;

	public Lobby(GameStateManager gsm) {
		super(gsm);
		buttonHeight = 150;
		buttonWidth = 144;
		spacing = 5;
		room = gsm.game.getCurrentCharacter().getRoom();
		room.setLobby(this);
		partyMembers = new Group();
		triangles = new Image[4];
		loadTextures();
		triangles[0] = new Image(new TextureRegion(redT));
		triangles[1] = new Image(new TextureRegion(blueT));
		triangles[2] = new Image(new TextureRegion(greenT));
		triangles[3] = new Image(new TextureRegion(purpleT));
		loadContent();
		refresh();
		timer = new Timer();
		timer.start();
		delay = 5;
		zIndex = 2;
		System.out.println(zIndex);
		task = new Timer.Task() {
			@Override
			public void run() {
				// Do your work
				//getGSM().setState(GameStateManager.State.DUNGEON);
				timerValue.setText("0");
			}
		};
		//enterDungeonCountDown();
	}

	public void enterDungeonCountDown() {
		new OKPopup(game, "Entering Dungeon");
		savedTime = System.currentTimeMillis();
		Timer.schedule(task, delay);
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

	public Betrayal getGame() {
		return gsm.game;
	}

	public GameStateManager getGSM() {
		return gsm;
	}

	// Helpers
	private void loadBackground() {
		Image lobbyBackground = new Image(res.getTexture("instructions-background"));
		lobbyBackground.layout();
		lobbyBackground.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		bottom.addActor(lobbyBackground);
	}

	private void loadContent() {
		bottom = new Group();
		buttons = new Group();
		middle = new Group();
		stage.addActor(bottom);
		stage.addActor(buttons);
		stage.addActor(middle);
		stage.addActor(partyMembers);
		loadBackground();
		loadAllPlayersBackground();
		loadChatBackground();
		loadTower();
		loadShopButton();
		loadSettingsButton();
		loadPartyButton();
		loadRoomLabel();
		loadTimeLeftLabel();
		loadStatsButton();
		loadInventoryButton();
		loadPlayNowButton();
		loadReadyButton();
		loadUnReadyButton();
	}

	private void loadShopButton() {
		Image shopButton = new Image(res.getTexture("lobby-shop"));
		shopButton.layout();
		shopButton.setBounds(0, Betrayal.HEIGHT - buttonHeight - 5, buttonWidth, buttonHeight);
		shopButton.addListener(new InputListener(shopButton, true) {
			@Override
			public void doAction() {
				new Shop(game);
			}
		});
		buttons.addActor(shopButton);
	}

	private void loadInventoryButton() {
		Image inventoryButton = new Image(res.getTexture("lobby-inventory"));
		inventoryButton.layout();
		inventoryButton.setBounds(buttonWidth, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		inventoryButton.addListener(new InputListener(inventoryButton, true) {
			@Override
			public void doAction() {
				new Inventory(game);
			}
		});
		buttons.addActor(inventoryButton);

	}

	private void loadStatsButton() {
		Image statsButton = new Image(res.getTexture("lobby-stats"));
		statsButton.layout();
		statsButton.setBounds(buttonWidth * 2, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		statsButton.addListener(new InputListener(statsButton, true) {
			@Override
			public void doAction() {
				new StatsWindow(game);
			}
		});
		buttons.addActor(statsButton);
	}

	private void loadPartyButton() {
		Image partyButton = new Image(res.getTexture("lobby-party"));
		partyButton.layout();
		partyButton.setBounds(buttonWidth * 3, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		partyButton.addListener(new InputListener(partyButton, true) {
			@Override
			public void doAction() {
				new PartyRoom(game) {
					@Override
					public void remove() {
						super.remove();
						Lobby.this.refresh();
					}
				};
			}
		});
		buttons.addActor(partyButton);
	}

	private void loadTimeLeftLabel() {
		timeTilEnter = new Label("Entering in ", FontManager.getFont60());
		timeTilEnter.setX(chatBackground.getX() + (chatBackground.getWidth() - timeTilEnter.getPrefWidth()) / 2 - 50);
		timeTilEnter.setY(roomNum.getY() - timeTilEnter.getPrefHeight() - 20);
		middle.addActor(timeTilEnter);

		timerValue = new Label("...", FontManager.getFont60()) {
			@Override
			public void act(float delta) {
				super.act(delta);
				if (task.isScheduled()) {
					timerValue.setColor(new Color(195f / 256f, 66f / 256f, 66f / 256f, 1));
					timeLeft = (int) (System.currentTimeMillis() - savedTime);
					timeLeft /= 1000;
					timeLeft = 5 - timeLeft;
					timerValue.setText(Integer.toString(timeLeft));
				} else {
					timerValue.setColor(Color.WHITE);
					timerValue.setText("...");
				}
			}
		};
		timerValue.setX(timeTilEnter.getX() + timeTilEnter.getPrefWidth());
		timerValue.setY(timeTilEnter.getY());
		middle.addActor(timerValue);
	}

	private void loadRoomLabel() {
		roomNum = new Label("Room #:  ", FontManager.getFont60());
		roomNum.setX(chatBackground.getX() + (chatBackground.getWidth() - roomNum.getPrefWidth()) / 2 - 100);
		roomNum.setY(chatBackground.getTop() - roomNum.getPrefHeight() - 20);
		bottom.addActor(roomNum);
	}

	private void loadSettingsButton() {
		Image settingsButton = new Image(res.getTexture("lobby-settings"));
		settingsButton.layout();
		settingsButton.setBounds(buttonWidth * 4, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		settingsButton.addListener(new InputListener(settingsButton, true) {
			@Override
			public void doAction() {
				new LobbyOptions(game);
			}
		});
		buttons.addActor(settingsButton);
	}

	private void loadAllPlayersBackground() {
		allPlayersBackground = new Image(res.getTexture("player-background"));
		allPlayersBackground.layout();
		allPlayersBackground.setBounds(Betrayal.WIDTH / 2 + 10 - 100, 510, Betrayal.WIDTH / 2 - 20 + 100, Betrayal.HEIGHT / 3 + 175);
		bottom.addActor(allPlayersBackground);
	}

	private void loadChatBackground() {
		chatBackground = new Image(res.getTexture("lobby-screen"));
		chatBackground.layout();
		chatBackground.setBounds(10, 175, Betrayal.WIDTH - 20, Betrayal.HEIGHT / 4);
		bottom.addActor(chatBackground);
	}

	private void loadTower() {
		tower = new Image(res.getTexture("lobby-tower"));
		tower.layout();
		tower.setBounds(20, 510, 175, Betrayal.HEIGHT / 3 + 175);
		tower.addListener(new InputListener(tower) {
			@Override
			public void doAction() {
				Lobby.this.refresh();
				game.getCurrentCharacter().stats.advanceFloor2();
			}
		});
		bottom.addActor(tower);
	}

	private void loadTextures() {
		greenCircle = res.getTexture("green-circle");
		redCircle = res.getTexture("red-circle");
		yellowCircle = res.getTexture("yellow-circle");
		readyTexture = res.getTexture("ready");
		playButtonTexture = res.getTexture("play-now");
		unReadyTexture = res.getTexture("unready");
		blueT = res.getTexture("blueT");
		redT = res.getTexture("redT");
		purpleT = res.getTexture("purpleT");
		greenT = res.getTexture("greenT");
	}

	private void setReadyPlayButton() {
		if (room.getRoomID() == -1) {
			playNowButton.setVisible(true);
			readyButton.setVisible(false);
			unReadyButton.setVisible(false);
		} else if (game.getCurrentCharacter().isReady()) {
			playNowButton.setVisible(false);
			readyButton.setVisible(false);
			unReadyButton.setVisible(true);
		} else {
			playNowButton.setVisible(false);
			readyButton.setVisible(true);
			unReadyButton.setVisible(false);
		}
	}

	private void loadPlayNowButton() {
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
					new Confirmation(game, "Enter Dungeon?") {
						@Override
						public void doAction() {
							room.setInDungeon(true);
							gsm.setState(GameStateManager.State.DUNGEON);
						}
					};
				} else playButtonTexture = res.getTexture("play-now");
			}

			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				playButtonTexture = res.getTexture("play-now");
			}
		});
		playNowButton.setVisible(false);
		buttons.addActor(playNowButton);
	}

	private void loadUnReadyButton() {
		unReadyButton = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(unReadyTexture, unReadyButton.getX(), unReadyButton.getY(),
						unReadyButton.getWidth(), unReadyButton.getHeight());
			}
		};
		unReadyButton.setWidth(512);
		unReadyButton.setBounds((Betrayal.WIDTH - unReadyButton.getWidth()) / 2, 20, 512, 144);
		unReadyButton.addListener(new InputListener(unReadyButton, true) {
			@Override
			public void doAction() {
				room.ready(false);
			}
		});
		unReadyButton.setVisible(false);
		buttons.addActor(unReadyButton);
	}

	private void loadReadyButton() {
		readyButton = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(readyTexture, readyButton.getX(), readyButton.getY(),
						readyButton.getWidth(), readyButton.getHeight());
			}
		};
		readyButton.setWidth(512);
		readyButton.setBounds((Betrayal.WIDTH - readyButton.getWidth()) / 2, 20, 512, 144);
		readyButton.addListener(new InputListener(readyButton, true) {
			@Override
			public void doAction() {
				room.ready(true);
			}
		});
		readyButton.setVisible(false);
		buttons.addActor(readyButton);
	}

	public void refresh() {
		partyMembers.clear();
		loadRoomParty();
		setReadyPlayButton();
		setRoomLabel();
	}

	private void loadRoomParty() {
		float width = allPlayersBackground.getImageWidth() - 20;
		float height = (allPlayersBackground.getImageHeight() - 50) / 4;
		float x = allPlayersBackground.getX() + 10;
		float y = allPlayersBackground.getTop() - 10;
		int i = 1;
		for (Character character : room.getCharacters()) {
			addPlayerImage(character, x, (y - (i * height) - (i * 10)), width, height, i);
			loadLevelTriangle(50, 50, i, character.stats.getFloor());
			i++;
		}
	}

	private void setRoomLabel() {
		String ID = "";
		int roomID = game.getCurrentCharacter().getRoom().getRoomID();
		if (roomID >= 0)
			ID += Integer.toString(roomID);
		roomNum.setText("RoomNum: " + ID);
	}

	private void loadLevelTriangle(float width, float height, int counter, int floor) {
		triangles[counter - 1].setX(tower.getX() + tower.getWidth() + counter * 7 - 10);
		triangles[counter - 1].setY(tower.getY() + (floor * tower.getHeight() / 25f) - 20);
		triangles[counter - 1].setZIndex(zIndex);
		triangles[counter - 1].setWidth(width);
		triangles[counter - 1].setHeight(height);
		partyMembers.addActor(triangles[counter - 1]);
	}

	private void addPlayerImage(final Character character, float x, float y, float width, float height, int counter) {
		Group player = new Group();

		//creating the teammate preview
		final float xPos = x - 20;
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
		name.setY(y + ((height - name.getPrefHeight()) / 2) + 20);
		name.setZIndex(zIndex);
		player.addActor(name);

		//create floor Label
		Label floor = new Label("Floor: " + character.stats.getFloor(), FontManager.getFont40());
		floor.setX(x + 20 + (width - floor.getPrefWidth()) / 2);
		floor.setY(name.getY() - floor.getPrefHeight() - 5 + 15);
		floor.setZIndex(zIndex);
		player.addActor(floor);
		switch (counter) {
			case 1:
				floor.setColor(new Color(195f / 256f, 66f / 256f, 66f / 256f, 1)); // red
				break;
			case 2:
				floor.setColor(new Color(46f / 256f, 138f / 256f, 138f / 256f, 1)); // blue
				break;
			case 3:
				floor.setColor(new Color(74f / 256f, 145f / 256f, 38f / 256f, 1));    //green
				break;
			case 4:
				floor.setColor(new Color(179f / 256f, 60f / 256f, 179f / 256f, 1)); //purple
				break;
		}

		//Create Ready Light
		Image ready = new Image();
		if(character.isInDungeon()){
			ready.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowCircle)));
		} else if (character.isReady()) {
			ready.setDrawable(new TextureRegionDrawable(new TextureRegion(greenCircle)));
		} else {
			ready.setDrawable(new TextureRegionDrawable(new TextureRegion(redCircle)));
		}
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
				new OKPopup(420, 380, game, "Stats" +
						"\nHealth: " + character.stats.getTotalHealth() +
						"\nAttack: " + character.stats.getTotalAttack() +
						"\nDefense: " + character.stats.getTotalDefense()
				);
			}
		});
		player.addActor(mask);

		partyMembers.addActor(player);
	}
}
