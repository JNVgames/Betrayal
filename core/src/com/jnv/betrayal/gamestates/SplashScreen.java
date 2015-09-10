/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.network.Player;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.resources.ResourceLoader;

import java.text.DecimalFormat;

public class SplashScreen extends GameState {

	private ResourceLoader loader;
	private Label text_percent;
	private DecimalFormat format;
    private Player player;

	public SplashScreen(GameStateManager gsm) {
		super(gsm);
		loader = game.getResourceLoader();

        player = gsm.getGame().getPlayer();
        player.setPlayerID();
        
		loader.loadLoadingScreen();
		res.finishLoading();
		loader.loadAll();

		format = new DecimalFormat("###");

		loadStage();
	}

	public void update(float dt) {
		stage.act(dt);
		if (res.update()) {
			loader.loadAllData();
			gsm.setState(GameStateManager.State.MENU);
		}
		text_percent.setText("Loading... " + format.format(res.getProgress() * 100) + "%");
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
		text_percent = new Label("Loading... 99%", FontManager.getFont(50));
		text_percent.setBounds((Betrayal.WIDTH - text_percent.getPrefWidth()) / 2, 300,
				text_percent.getPrefWidth(), text_percent.getPrefHeight());
		text_percent.layout();
		stage.addActor(text_percent);
	}
}
