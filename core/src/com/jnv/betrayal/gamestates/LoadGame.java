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
	private Image button_back;
	private TextureRegion image_leftArrow;

	private boolean mode_delete = false;

	public LoadGame(GameStateManager gsm) {
		super(gsm);
		image_leftArrow = new TextureRegion(res.getTexture("arrow-left"));

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
		Group group_button_back = new Group();

		button_back = new Image(image_leftArrow);
		button_back.setHeight(60);
		button_back.setWidth(80);
		button_back.setX(10);
		button_back.setY(Betrayal.HEIGHT - button_back.getHeight() - 10);
		group_button_back.addActor(button_back);

		Label button_text_back = new Label("Back", FontManager.getFont60());
		button_text_back.setX(button_back.getX() + button_back.getWidth() + 10);
		button_text_back.setY(button_back.getY());
		group_button_back.addActor(button_text_back);

		Actor button_back_clickArea = new Actor();
		button_back_clickArea.setWidth(button_back.getWidth() + button_text_back.getWidth() + 10);
		button_back_clickArea.setHeight(button_text_back.getHeight());
		button_back_clickArea.setX(button_back.getX());
		button_back_clickArea.setY(button_back.getY());
		button_back_clickArea.addListener(new InputListener(button_back_clickArea) {
			@Override
			public void doAction() {
				gsm.setState(GameStateManager.State.MENU);
			}
		});
		group_button_back.addActor(button_back_clickArea);

		stage.addActor(group_button_back);
	}

	private void loadSavedSessions() {
		// TODO @vincent loads kinda slow and code is kinda long
		int counter = 1, scale = 4;

		for (Character c : game.characters) {
			final Character character = c;
			Group preview = new Group();

			final Actor preview_frame = new Actor();
			preview_frame.setBounds(5, button_back.getY() - 230 * counter, Betrayal.WIDTH - 10,
					48 * scale + 10);
			preview.addActor(preview_frame);

			final int i = counter;
			Actor preview_charPrev = new Actor() {
				public void draw(Batch sb, float pa) {
					character.preview.drawPreview(sb, 0, preview_frame.getX() + 5,
							preview_frame.getY() + 5, 32 * 4, 48 * 4);
				}
			};
			preview_charPrev.setBounds(10, button_back.getY() - 230 * i + 5, 32 * scale, 48 * scale);
			preview.addActor(preview_charPrev);

			Label classPreview = new Label(c.job.toString(), FontManager.getFont50());
			classPreview.setX(preview_charPrev.getX() + preview_charPrev.getWidth() + 30);
			classPreview.setY(preview_charPrev.getY() + preview_charPrev.getHeight()
					- classPreview.getPrefHeight());
			classPreview.setColor(Color.WHITE);
			preview.addActor(classPreview);

			Label preview_floor = new Label("FLOOR", FontManager.getFont40());
			preview_floor.setX(Betrayal.WIDTH - 10 - preview_floor.getPrefWidth());
			preview_floor.setY(preview_charPrev.getY() + preview_charPrev.getHeight()
					- preview_floor.getPrefHeight());
			preview_floor.setColor(Color.WHITE);
			preview.addActor(preview_floor);

			Label floorNumLabel =
					new Label(Integer.toString(c.stats.getStat(Stat.FLOOR)),
							FontManager.getFont100());
			floorNumLabel.setBounds(preview_floor.getX(), preview_charPrev.getY(),
					preview_floor.getWidth(), preview_floor.getY() - 10 - preview_charPrev.getY());
			floorNumLabel.setColor(Color.LIGHT_GRAY);
			floorNumLabel.setAlignment(Align.center);
			preview.addActor(floorNumLabel);

			addPreviewListener(preview, preview_frame, c);

			stage.addActor(preview);

			savedSessions[counter - 1] = preview;
			counter++;
		}
	}

	private void loadDeleteButton() {
		final Label buttonDelete = new Label("Delete tmp", FontManager.getFont60());
		buttonDelete.setBounds(Betrayal.WIDTH - 30 - buttonDelete.getPrefWidth(),
				50, buttonDelete.getPrefWidth(), buttonDelete.getPrefHeight());
		buttonDelete.layout();
		buttonDelete.addListener(new InputListener(buttonDelete) {
			@Override
			public void doAction() {
				mode_delete = true;
				buttonDelete.remove();
				loadCancelButton();
			}
		});
		stage.addActor(buttonDelete);
	}

	private void loadCancelButton() {
		final Label button_cancel = new Label("Cancel tmp", FontManager.getFont60());
		button_cancel.setBounds(Betrayal.WIDTH - 30 - button_cancel.getPrefWidth(),
				50, button_cancel.getPrefWidth(), button_cancel.getPrefHeight());
		button_cancel.layout();
		button_cancel.addListener(new InputListener(button_cancel) {
			@Override
			public void doAction() {
				mode_delete = false;
				button_cancel.remove();
				loadDeleteButton();
			}
		});
		stage.addActor(button_cancel);
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
					if (!mode_delete) { // Delete mode is off
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
