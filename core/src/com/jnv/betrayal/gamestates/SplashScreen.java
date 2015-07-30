package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Timer;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

public class SplashScreen extends GameState {

    final int SPLASH_SCREEN_DELAY = 2;

    public SplashScreen(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Betrayal.WIDTH, Betrayal.HEIGHT);
        Timer.schedule(new Timer.Task() {

            @Override
            public void run() {
                movetoMenuScreen();
            }

        }, SPLASH_SCREEN_DELAY);
    }

    public void update(float dt) {

    }
    public void handleInput() {

    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0); // Set background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        game.getBatch().draw(Betrayal.res.getTexture("splash"), 0, 0, 720, 1280);
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
