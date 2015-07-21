package com.jnv.Betrayal;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by jarnin on 7/20/15.
 */
public class SplashScreen extends GameState {

    final int SPLASH_SCREEN_DELAY = 2;

    protected SplashScreen(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Betrayal.WIDTH, Betrayal.HEIGHT);
        Timer.schedule(new Timer.Task() {

            @Override
            public void run() {
                movetoMenuScreen();
            }

        }, SPLASH_SCREEN_DELAY);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void render() {

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        game.getBatch().draw(Betrayal.res.getTexture("splash"), 0, 0, 720, 1280);
        game.getBatch().end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public OrthographicCamera getCam() {
        return super.getCam();
    }

    private void movetoMenuScreen() {
        gsm.setState(GameStateManager.MENU);
    }
}
