/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.main.Betrayal;

public class HallOfFame extends GameState {

	private ScrollPane scrollPane;
	private Table table;

	public HallOfFame(GameStateManager gsm) {
		super(gsm);
		res = gsm.getGame().res;

		table = new Table();
		table.add(new Image(res.getTexture("hall-of-fame-background")));
		table.layout();
		table.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		scrollPane = new ScrollPane(table);
		scrollPane.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		scrollPane.layout();
		scrollPane.setZIndex(0);
		scrollPane.setScrollingDisabled(true, false);
		scrollPane.setOverscroll(false, false);
		stage.addActor(scrollPane);

		loadXButton();
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
