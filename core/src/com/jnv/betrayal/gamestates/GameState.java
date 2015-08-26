/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jnv.betrayal.Network.Player;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public abstract class GameState  {

    protected GameStateManager gsm;
    protected Betrayal game;
    protected SpriteBatch sb;
    protected OrthographicCamera cam;
    protected StretchViewport stretchViewport;
    protected BetrayalAssetManager res;
    protected Player player;
    protected Stage stage;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        sb = game.getBatch();
        cam = game.getCamera();
        stretchViewport = game.getStretchViewport();
        stage = game.getStage();
        res = game.res;
        stage.clear();
        player = game.player;
    }

    public abstract void update(float dt);
    public abstract void handleInput();
    public abstract void render();
    public abstract void dispose();

    public OrthographicCamera getCam() { return cam; }
}
