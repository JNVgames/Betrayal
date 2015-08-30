/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.Network.Player;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public abstract class GameState {

	protected GameStateManager gsm;
	protected Betrayal game;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected BetrayalAssetManager res;
	protected Player player;
	protected Stage stage;

	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.getGame();
		stage = game.getStage();
		stage.clear();
		sb = game.getBatch();
		cam = game.getCamera();
		res = game.res;
		player = game.getPlayer();
	}

	public abstract void update(float dt);

	public abstract void handleInput();

	public abstract void render();

	public abstract void dispose();
}
