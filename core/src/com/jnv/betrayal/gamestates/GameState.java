/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.network.Player;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public abstract class GameState {

	protected GameStateManager gsm;
	protected Betrayal game;
	protected BetrayalAssetManager res;
	protected Player player;
	protected Stage stage;

	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.game;
		stage = game.getStage();
		stage.clear();
		res = game.res;
		player = game.getPlayer();
		Gdx.input.setInputProcessor(stage);
	}

	public abstract void update(float dt);

	public abstract void render();

	public abstract void dispose();
}
