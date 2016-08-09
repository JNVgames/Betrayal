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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class LoadGame extends GameState {

	private Group[] savedSessions;
	private Image backButton;
	private TextureRegion leftArrowImage;

	private boolean deleteMode = false;

	public LoadGame(GameStateManager gsm) {
		super(gsm);
		leftArrowImage = new TextureRegion(res.getTexture("arrow-left"));

		savedSessions = new Group[game.characters.size()];

		loadStage();

		//if (Betrayal.debug) System.out.println(gsm.game.getPlayer().toJson());
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
		loadSavedSessions();
		loadDeleteButton();
	}

	// Helpers
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
		int counter = 1, scale = 4;

		for (Character c : game.characters) {
			final Character character = c;
			Group preview = new Group();

			final Actor previewFrame = new Actor();
			previewFrame.setBounds(5, backButton.getY() - 230 * counter, Betrayal.WIDTH - 10,
					48 * scale + 10);
			preview.addActor(previewFrame);

			final int i = counter;
			Actor characterPreview = new Actor() {
				public void draw(Batch sb, float pa) {
					character.preview.drawPreview(sb, 0, previewFrame.getX() + 5,
							previewFrame.getY() + 5, 32 * 4, 48 * 4);
				}
			};
			characterPreview.setBounds(10, backButton.getY() - 230 * i + 5, 32 * scale, 48 * scale);
			preview.addActor(characterPreview);

			Label classPreview = new Label(c.job.toString(), FontManager.getFont50());
			classPreview.setX(characterPreview.getX() + characterPreview.getWidth() + 30);
			classPreview.setY(characterPreview.getY() + characterPreview.getHeight()
					- classPreview.getPrefHeight());
			classPreview.setColor(Color.WHITE);
			preview.addActor(classPreview);

			Label floorLabel = new Label("FLOOR", FontManager.getFont40());
			floorLabel.setX(Betrayal.WIDTH - 10 - floorLabel.getPrefWidth());
			floorLabel.setY(characterPreview.getY() + characterPreview.getHeight()
					- floorLabel.getPrefHeight());
			floorLabel.setColor(Color.WHITE);
			preview.addActor(floorLabel);

			Label floorNumLabel =
					new Label(Integer.toString(c.stats.getStat(Stat.FLOOR)),
							FontManager.getFont100());
			floorNumLabel.setBounds(floorLabel.getX(), characterPreview.getY(),
					floorLabel.getWidth(), floorLabel.getY() - 10 - characterPreview.getY());
			floorNumLabel.setColor(Color.LIGHT_GRAY);
			floorNumLabel.setAlignment(Align.center);
			preview.addActor(floorNumLabel);

			addPreviewListener(preview, previewFrame, c);

			stage.addActor(preview);

			savedSessions[counter - 1] = preview;
			counter++;
		}
	}

	private void loadDeleteButton() {
		float scale = 0.8f;
		final Image buttonDelete = new Image(res.getTexture("delete-button"));
		buttonDelete.setBounds(Betrayal.WIDTH - 30 - buttonDelete.getPrefWidth() * scale,
				50, buttonDelete.getPrefWidth() * scale, buttonDelete.getPrefHeight() * scale);
		buttonDelete.layout();
		buttonDelete.addListener(new InputListener(buttonDelete) {
			@Override
			public void doAction() {
				deleteMode = true;
				buttonDelete.remove();
				loadCancelButton();
			}
		});
		stage.addActor(buttonDelete);
	}

	private void loadCancelButton() {
		float scale = 0.8f;
		final Image cancelButton = new Image(res.getTexture("cancel"));
		cancelButton.setBounds(Betrayal.WIDTH - 30 - cancelButton.getPrefWidth() * scale,
				50, cancelButton.getPrefWidth() * scale, cancelButton.getPrefHeight() * scale);
		cancelButton.layout();
		cancelButton.addListener(new InputListener(cancelButton) {
			@Override
			public void doAction() {
				deleteMode = false;
				cancelButton.remove();
				loadDeleteButton();
			}
		});
		stage.addActor(cancelButton);
	}

	private void removeSavedSessions() {
		for (Group group : savedSessions) {
			group.remove();
		}
	}

	private void addPreviewListener(final Group preview,
									final Actor frame, final Character character) {
		preview.addListener(new InputListener(frame, true) {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (x >= frame.getX() && x <= frame.getX() + frame.getWidth()
						&& y >= frame.getY() && y <= frame.getY() + frame.getHeight()) {
					if (!deleteMode) { // Delete mode is off
						game.setCurrentCharacter(character);
						gsm.setState(GameStateManager.State.LOBBY);
					} else { // Delete mode is on
						removeSavedSessions();
						game.characters.remove(character);
						loadSavedSessions();
					}
				}
			}
		});
	}

}
