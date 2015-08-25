/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Timer;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.ResourceLoader;

public class SplashScreen extends GameState {
    private BetrayalAssetManager res;
    private float time;
    private BitmapFont font;

    public SplashScreen(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Betrayal.WIDTH, Betrayal.HEIGHT);
        res = gsm.getGame().res;
        ResourceLoader resourceLoader = new ResourceLoader(res);
        resourceLoader.loadLoadingScreen();
        res.finishLoading();
        resourceLoader.loadAll();
        font = new BitmapFont();
    }

    public void update(float dt) {
        time += dt;
        if (res.update()) {
            gsm.setState(GameStateManager.State.MENU);
        }
    }
    public void handleInput() {

    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0); // Set background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        game.getBatch().draw(res.getTexture("splash"), 0, 0, 720, 1280);
        font.draw(sb, Float.toString(res.getProgress()), 100, 100);
        game.getBatch().end();
    }
    public void dispose() {

    }

    // Helpers
    private void movetoMenuScreen() {
        gsm.setState(GameStateManager.State.MENU);
    }

    // Getters
    public OrthographicCamera getCam() {
        return super.getCam();
    }
}
