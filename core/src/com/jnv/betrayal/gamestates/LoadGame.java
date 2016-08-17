/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class LoadGame extends GameState {

	private Group[] savedSessions;
	private Image backButton, deleteButton;
	private InputListener deleteListener, cancelListener;
	private TextureRegion leftArrowImage;
	private Label hintText;
	private static final String PLAY_TEXT = "Click a character to play!";
	private static final String DELETE_TEXT = "Click a character to delete.";
	private boolean deleteMode = false;

	public LoadGame(GameStateManager gsm) {
		super(gsm);
		leftArrowImage = new TextureRegion(res.getTexture("arrow-left"));

		savedSessions = new Group[game.characters.size()];

		createBackground();
		loadStage();
	}

	public void createBackground(){
		Image image = new Image(res.getTexture("map51"));
		image.layout();
		image.setX(0);
		image.setY(0);
		image.setWidth(Betrayal.WIDTH);
		image.setHeight(Betrayal.HEIGHT);
		stage.addActor(image);
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

	private void loadStage() {
		loadBackButton();
		loadHintText();
		loadSavedSessions();
		initDeleteButton();
		setLoadMode();
	}

	// Helpers
	private void loadHintText() {
		hintText = new Label(PLAY_TEXT, FontManager.getFont50());
		hintText.setBounds((Betrayal.WIDTH - hintText.getPrefWidth()) / 2,
				backButton.getY() - 75 - hintText.getPrefHeight(),
				hintText.getPrefWidth(), hintText.getPrefHeight());
		stage.addActor(hintText);
	}

	private void loadBackButton() {
		Group backButtonGroup = new Group();

		backButton = new Image(leftArrowImage);
		backButton.setHeight(60);
		backButton.setWidth(80);
		backButton.setX(10);
		backButton.setY(Betrayal.HEIGHT - backButton.getHeight() - 10);
		backButtonGroup.addActor(backButton);

		Label backButtonText = new Label("Back", FontManager.getFont60());
		backButtonText.setX(backButton.getX() + backButton.getWidth() + 10);
		backButtonText.setY(backButton.getY());
		backButtonGroup.addActor(backButtonText);

		Actor backButtonClickArea = new Actor();
		backButtonClickArea.setWidth(backButton.getWidth() + backButtonText.getWidth() + 10);
		backButtonClickArea.setHeight(backButtonText.getHeight());
		backButtonClickArea.setX(backButton.getX());
		backButtonClickArea.setY(backButton.getY());
		backButtonClickArea.addListener(new InputListener(backButtonClickArea) {
			@Override
			public void doAction() {
				gsm.setState(GameStateManager.State.MENU);
			}
		});
		backButtonGroup.addActor(backButtonClickArea);

		stage.addActor(backButtonGroup);
	}

	private void loadSavedSessions() {
		int counter = 1;
		final int scale = 4;

		for (Character c : game.characters) {
			c.getRoom().setRoomID(-1);
			final Character character = c;
			Group preview = new Group();

			Image border = new Image(res.getTexture("cpb"));
			border.setY(hintText.getY() - 230 * counter);
			preview.addActor(border);

			final Actor previewFrame = new Actor();
			previewFrame.setBounds(5, hintText.getY() - 230 * counter, Betrayal.WIDTH - 10,
					48 * scale + 10);
			preview.addActor(previewFrame);

			final int i = counter;
			Actor characterPreview = new Actor() {
				public void draw(Batch sb, float pa) {
					character.preview.drawPreview(sb, 0, previewFrame.getX() + 5,
							previewFrame.getY() + 5, 32 * scale, 48 * scale);
				}
			};
			characterPreview.setBounds(10, hintText.getY() - 230 * i + 5, 32 * scale, 48 * scale);
			preview.addActor(characterPreview);

			Label classPreview = new Label(c.job.toString(), FontManager.getFont50());
			classPreview.setX(characterPreview.getX() + characterPreview.getWidth() + 30);
			classPreview.setY(characterPreview.getTop() - classPreview.getPrefHeight());
			classPreview.setColor(Color.WHITE);
			preview.addActor(classPreview);

			Label name = new Label(c.getName(), FontManager.getFont70());
			name.setX(characterPreview.getX() + characterPreview.getWidth() + 30);
			name.setY((characterPreview.getY() + classPreview.getY() - name.getPrefHeight()) / 2 + 5);
			preview.addActor(name);

			Label floorLabel = new Label("FLOOR", FontManager.getFont40());
			floorLabel.setX(Betrayal.WIDTH - 20 - floorLabel.getPrefWidth());
			floorLabel.setY(characterPreview.getY() + characterPreview.getHeight()
					- floorLabel.getPrefHeight() - 10);
			floorLabel.setColor(Color.WHITE);
			preview.addActor(floorLabel);

			Label floorNumLabel =
					new Label(Integer.toString(c.stats.getStat(Stat.FLOOR)),
							FontManager.getFont100());
			floorNumLabel.setBounds(floorLabel.getX(), characterPreview.getY() + 5,
					floorLabel.getWidth(), floorLabel.getY() - 10 - characterPreview.getY());
			floorNumLabel.setColor(Color.LIGHT_GRAY);
			floorNumLabel.setAlignment(Align.center);
			preview.addActor(floorNumLabel);

			previewFrame.addListener(new InputListener(preview, true));

			previewFrame.addListener(new InputListener(previewFrame) {
				@Override
				public void doAction() {
					if (!deleteMode) { // Delete mode is off
						game.setCurrentCharacter(character);
						System.out.println(game.getCurrentCharacter().stats.getBaseAttack() + "++++++++++++++++++++++++++++++++++++++++++++");
						gsm.setState(GameStateManager.State.LOBBY);
					} else { // Delete mode is on
						new Confirmation(game, "Are you sure?") {
							@Override
							public void doAction() {
								removeSavedSessions();
								game.addFool(character);
								game.characters.remove(character);
								loadSavedSessions();
								setLoadMode();
								game.savedDataHandler.save();
							}
						};
					}
				}
			});

			preview.setY(-120);

			stage.addActor(preview);

			savedSessions[counter - 1] = preview;
			counter++;
		}
	}

	private void setLoadMode() {
		deleteButton.setDrawable(new TextureRegionDrawable(new TextureRegion(res.getTexture("delete-button"))));
		deleteButton.clearListeners();
		deleteButton.addListener(deleteListener);
		deleteMode = false;
		hintText.setText(PLAY_TEXT);
		centerHintText();
	}

	private void setDeleteMode() {
		deleteButton.setDrawable(new TextureRegionDrawable(new TextureRegion(res.getTexture("cancel"))));
		deleteButton.clearListeners();
		deleteButton.addListener(cancelListener);
		deleteMode = true;
		hintText.setText(DELETE_TEXT);
		centerHintText();
	}

	private void initDeleteButton() {
		float scale = 0.6f;
		deleteButton = new Image(res.getTexture("delete-button"));
		deleteButton.setBounds(Betrayal.WIDTH - deleteButton.getPrefWidth() * scale - 10,
				Betrayal.HEIGHT - deleteButton.getPrefHeight() * scale - 10,
				deleteButton.getPrefWidth() * scale, deleteButton.getPrefHeight() * scale);
		stage.addActor(deleteButton);
		deleteListener = new InputListener(deleteButton) {
			@Override
			public void doAction() {
				setDeleteMode();
			}
		};
		cancelListener = new InputListener(deleteButton) {
			@Override
			public void doAction() {
				setLoadMode();
			}
		};
	}

	private void removeSavedSessions() {
		for (Group group : savedSessions) {
			group.remove();
		}
	}

	private void centerHintText() {
		hintText.setX((Betrayal.WIDTH - hintText.getPrefWidth()) / 2);
	}
}
