package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

public class Menu extends GameState {

    public Menu(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Betrayal.WIDTH, Betrayal.HEIGHT);
        Gdx.app.log("Menu", "Menu constructor");

        loadMenuButtons();
    }

    public void update(float dt) {

    }
    public void handleInput() {

    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0); // Set background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(Betrayal.res.getTexture("start_background"), 0, 0, 720, 1280);
        sb.draw(Betrayal.res.getTexture("new"), 104, 800, 512, 144);
        sb.draw(Betrayal.res.getTexture("load"), 104, 600, 512, 144);
        sb.draw(Betrayal.res.getTexture("options"), 104, 400, 512, 144);
        sb.end();
    }
    public void dispose() {

    }

    // Helpers
    private void loadMenuButtons() {
        Image button_new = new Image(Betrayal.res.getTexture("new"));
        button_new.layout();
        button_new.setBounds((Betrayal.WIDTH - button_new.getImageWidth()) / 2,
                800, 512, 144);
        button_new.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.CHARACTER_SELECTION);
            }
        });
        stage.addActor(button_new);
    }

    // Getters
    public OrthographicCamera getCam() {
        return super.getCam();
    }
}
