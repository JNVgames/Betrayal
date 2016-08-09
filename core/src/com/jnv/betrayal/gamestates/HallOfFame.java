/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class HallOfFame extends GameState {

	private ScrollPane scrollPane;
	private Table table;
	private Group deadCharacterPreview;

	public HallOfFame(GameStateManager gsm) {
		super(gsm);
		res = gsm.game.res;

		loadBackground();

		deadCharacterPreview = new Group();
		table = new Table();
		table.layout();
		table.setBounds(100, 0, Betrayal.WIDTH, Betrayal.HEIGHT-250);
		scrollPane = new ScrollPane(table);
		scrollPane.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		scrollPane.layout();
		scrollPane.setZIndex(0);
		scrollPane.setScrollingDisabled(true, false);
		scrollPane.setOverscroll(false, false);
		stage.addActor(scrollPane);
		loadXButton();

	}

	private void loadBackground(){
		Image border = new Image(res.getTexture("hall-of-fame-background"));
		border.setX(0);
		border.setY(0);
		border.setWidth(Betrayal.WIDTH);
		border.setHeight(Betrayal.HEIGHT);
		stage.addActor(border);
	}

	private void loadXButton() {
		Image exitButton = new Image(res.getTexture("x"));
		exitButton.layout();
		exitButton.setBounds(620, 1180, 100, 100);
		exitButton.addListener(new InputListener(exitButton) {
			@Override
			public void doAction() {
				gsm.setState(GameStateManager.State.MENU);
			}
		});
		stage.addActor(exitButton);
	}

	public void update(float dt) {
		stage.act(dt);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}


	private void loadSavedSessions() {
		int counter = 1;
		final int scale = 4;

		for (Character c : game.characters) {
			final Character character = c;
			Group preview = new Group();

			Image border = new Image(res.getTexture("cpb"));
			border.setY(Betrayal.HEIGHT - 230 * counter);
			preview.addActor(border);

			final Actor previewFrame = new Actor();
			previewFrame.setBounds(5, Betrayal.HEIGHT  - 230 * counter, Betrayal.WIDTH - 10,
					48 * scale + 10);
			preview.addActor(previewFrame);

			final int i = counter;
			Actor characterPreview = new Actor() {
				public void draw(Batch sb, float pa) {
					character.preview.drawPreview(sb, 0, previewFrame.getX() + 5,
							previewFrame.getY() + 5, 32 * scale, 48 * scale);
				}
			};
			characterPreview.setBounds(10, Betrayal.HEIGHT  - 230 * i + 5, 32 * scale, 48 * scale);
			preview.addActor(characterPreview);

			Label classPreview = new Label(c.job.toString(), FontManager.getFont50());
			classPreview.setX(characterPreview.getX() + characterPreview.getWidth() + 30);
			classPreview.setY(characterPreview.getTop() - classPreview.getPrefHeight());
			classPreview.setColor(Color.WHITE);
			preview.addActor(classPreview);

			Label name = new Label(c.getName(), FontManager.getFont70());
			name.setX(characterPreview.getX() + characterPreview.getWidth() + 30);
			name.setY((characterPreview.getY() + classPreview.getY() - name.getPrefHeight()) / 2);
			preview.addActor(name);

			Label floorLabel = new Label("FLOOR", FontManager.getFont40());
			floorLabel.setX(Betrayal.WIDTH - 20 - floorLabel.getPrefWidth());
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


			preview.setY(-120);

			stage.addActor(preview);
			counter++;
		}
	}


	public void dispose() {
	}
}
