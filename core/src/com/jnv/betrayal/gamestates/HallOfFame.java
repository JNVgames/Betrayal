/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;

public class HallOfFame extends GameState {

	private ScrollPane scrollPane;
	private VerticalGroup verticalGroup;

	public HallOfFame(GameStateManager gsm) {
		super(gsm);
		res = gsm.game.res;

		loadBackground();

		verticalGroup = new VerticalGroup();
		verticalGroup.layout();
		verticalGroup.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT - 150);
		verticalGroup.align(Align.left);
		scrollPane = new ScrollPane(verticalGroup);
		scrollPane.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT - 150);
		scrollPane.layout();
		scrollPane.setScrollingDisabled(true, false);
		scrollPane.setOverscroll(false, false);
		stage.addActor(scrollPane);

		loadXButton();
		loadFools();
	}

	private void loadFools() {
		for (Character c : game.fools) {
			final Character character = c;
			Group group = new Group();
			group.setBounds(0, 0, Betrayal.WIDTH - 10, 202);

			Image border = new Image(res.getTexture("cpb"));
			border.setX(0);
			border.setY(0);
			group.addActor(border);

			// Preview
			final int previewX = 5, previewWidth = 32 * 4;
			group.addActor(new Actor() {
				@Override
				public void draw(Batch batch, float parentAlpha) {
					character.preview.drawPreview(batch, 0, previewX, 5, previewWidth, 48 * 4);
				}
			});

			Label classPreview = new Label(character.job.toString(), FontManager.getFont50());
			classPreview.setX(previewX + previewWidth + 30);
			classPreview.setY(group.getHeight() - 10 - classPreview.getPrefHeight());
			classPreview.setColor(Color.WHITE);
			group.addActor(classPreview);

			Label name = new Label(character.getName(), FontManager.getFont70());
			name.setX(classPreview.getX());
			name.setY((classPreview.getY() - name.getPrefHeight()) / 2 + 5);
			group.addActor(name);

			Label floorLabel = new Label("FLOOR", FontManager.getFont40());
			floorLabel.setX(Betrayal.WIDTH - 20 - floorLabel.getPrefWidth());
			floorLabel.setY(group.getHeight() - floorLabel.getPrefHeight() - 20);
			floorLabel.setColor(Color.WHITE);
			group.addActor(floorLabel);

			Label floorNum =
					new Label(Integer.toString(character.stats.getStat(Stat.FLOOR)),
							FontManager.getFont100());
			floorNum.setBounds(floorLabel.getX(), 10,
					floorLabel.getWidth(), floorLabel.getY() - 10);
			floorNum.setColor(Color.LIGHT_GRAY);
			floorNum.setAlignment(Align.center);
			group.addActor(floorNum);

			verticalGroup.addActor(group);
		}
	}

	private void loadBackground() {
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

	public void dispose() {
	}
}
