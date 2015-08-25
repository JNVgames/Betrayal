/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.handlers.screentouch.BetrayalGestureProcessor;
import com.jnv.betrayal.handlers.screentouch.BetrayalInput;
import com.jnv.betrayal.main.Betrayal;

public class HallOfFame extends GameState {

    private Texture hallOfFameBackground;
    private BetrayalGestureProcessor gp;

    public HallOfFame(GameStateManager gsm) {
        super(gsm);
        res = gsm.getGame().res;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Betrayal.WIDTH, Betrayal.HEIGHT);

        hallOfFameBackground = res.getTexture("hall-of-fame-background");

        InputMultiplexer im = new InputMultiplexer();
        gp = new BetrayalGestureProcessor();
        im.addProcessor(stage);
        im.addProcessor(new GestureDetector(gp));
        Gdx.input.setInputProcessor(im);

        loadXButton();
    }

    private void loadXButton() {
        Image exitButton = new Image(res.getTexture("x"));
        exitButton.layout();
        exitButton.setBounds(620, 1180, 100, 100);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.MENU);
            }
        });
        stage.addActor(exitButton);
    }

    public void update(float dt) {
        stage.act(dt);

        float newPosX = cam.position.x - gp.getFlingVelocityX() * dt;
        float newPosY = cam.position.y + gp.getFlingVelocityY() * dt;
        handleInput();
        gp.flingDecelerate(dt);
        if (newPosY < 640 && newPosY > -80) {
            cam.position.y += gp.getFlingVelocityY() * dt;
        }
        cam.update();
    }

    public void handleInput() {
        if (BetrayalInput.pannedDown || BetrayalInput.pannedUp) {
            if ((cam.position.y + BetrayalInput.deltaY) < 640 &&
                    (cam.position.y + BetrayalInput.deltaY) > -80) {
                cam.position.set(cam.position.x, cam.position.y + BetrayalInput.deltaY, 0);
                cam.update();
            }
        }
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(hallOfFameBackground, 0, -720, 720, 2000);
        sb.end();

        stage.draw();
    }

    public void dispose() {
    }
}
