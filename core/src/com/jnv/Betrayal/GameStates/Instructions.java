package com.jnv.Betrayal.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.jnv.Betrayal.Handlers.GameStateManager;
import com.jnv.Betrayal.Handlers.ScreenTouch.BetrayalGestureProcessor;
import com.jnv.Betrayal.Handlers.ScreenTouch.BetrayalInput;
import com.jnv.Betrayal.Main.Betrayal;
import com.jnv.Betrayal.Utilities.SimpleButton;

/**
 * Copyright 2015, JNV Games, All rights reserved.
 */

public class Instructions extends GameState {

        private Texture infoPic1;
        private SimpleButton exitButton;
        private BetrayalGestureProcessor gp;

        public Instructions(GameStateManager gsm) {
            super(gsm);

            cam = new OrthographicCamera();
            cam.setToOrtho(false, Betrayal.WIDTH, Betrayal.HEIGHT);

           // infoPic1 = new Texture(Gdx.files.internal("game_images/info_first_page1280x2880.png"));
            exitButton = new SimpleButton(Betrayal.res.getTexture("x"),
                    620, 1180, 100, 100);
            gp = new BetrayalGestureProcessor();
            GestureDetector gd = new GestureDetector(gp);
            Gdx.input.setInputProcessor(gd);
        }

        public void update(float dt) {
            float newPosX = cam.position.x - gp.getFlingVelocityX() * dt;
            float newPosY = cam.position.y + gp.getFlingVelocityY() * dt;
            handleInput();
            gp.flingDecelerate(dt);
            //cam.position.x -= gp.getFlingVelocityX() * dt;
            if(newPosY < 360 && newPosY > -1800) {
                cam.position.y += gp.getFlingVelocityY() * dt;
                Gdx.app.log("FirstInfoScreen", "y = " + cam.position.y);
            }
            cam.update();
        }
        public void handleInput() {
            if(BetrayalInput.pannedDown || BetrayalInput.pannedUp) {
                if((cam.position.y + BetrayalInput.deltaY) < 360 &&
                        (cam.position.y + BetrayalInput.deltaY) > -1800) {
                    cam.position.set(cam.position.x, cam.position.y + BetrayalInput.deltaY, 0);
                    cam.update();
                }
            }

            if(BetrayalInput.isTapped) {
                if(exitButton.checkIfClicked(BetrayalInput.x, BetrayalInput.y)) {
                    goToMainmenu();
                }
            }

        }
        public void render() {
            Gdx.gl.glClearColor(0, 0, 0, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            sb.setProjectionMatrix(cam.combined);
            sb.begin();
           // sb.draw(infoPic1, 0, -2160, 1280, 2880);
            exitButton.draw(sb);

            sb.end();
        }
        public void dispose() {

        }

        // Helpers

        private void goToMainmenu() { gsm.setState(GameStateManager.State.MENU); }
    }

}
