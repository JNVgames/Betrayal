package com.jnv.Betrayal.GameStates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Timer;
import com.jnv.Betrayal.Handlers.GameStateManager;

public class SplashScreen extends GameState {

    final int SPLASH_SCREEN_DELAY = 2;

    public SplashScreen(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, com.jnv.Betrayal.Main.Betrayal.WIDTH, com.jnv.Betrayal.Main.Betrayal.HEIGHT);
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

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        game.getBatch().draw(com.jnv.Betrayal.Main.Betrayal.res.getTexture("splash"), 0, 0, 720, 1280);
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
