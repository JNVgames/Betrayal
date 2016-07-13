/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.resources.ResourceLoader;

import java.text.DecimalFormat;

public class SplashScreen extends GameState {

	private ResourceLoader loader;
	private Label percentText;
	private DecimalFormat format;

	public SplashScreen(GameStateManager gsm) {
		super(gsm);
		loader = game.getResourceLoader();

		loader.loadLoadingScreen();
		res.finishLoading();
		loader.loadAll();
		if (Betrayal.debug) res.finishLoading();

		format = new DecimalFormat("###");

		loadStage();
	}

	public void update(float dt) {
		stage.act(dt);
		if (res.update()) {
			loader.loadAllData();
			gsm.setState(GameStateManager.State.MENU);
		}
		percentText.setText("Loading... " + format.format(res.getProgress() * 100) + "%");
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	public void dispose() {

	}

	// Helpers
	private void loadStage() {
		loadBackground();
		loadPercentText();
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("splash"));
		background.setBounds(0, 0, 720, 1280);
		background.layout();
		stage.addActor(background);
	}

	private void loadPercentText() {
		percentText = new Label("Loading... 99%", FontManager.getFont50());
		percentText.setBounds((Betrayal.WIDTH - percentText.getPrefWidth()) / 2, 300,
				percentText.getPrefWidth(), percentText.getPrefHeight());
		percentText.layout();
		stage.addActor(percentText);
	}
}
