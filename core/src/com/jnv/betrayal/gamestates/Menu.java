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
        stage.act(dt);
    }
    public void handleInput() {

    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0); // Set background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(Betrayal.res.getTexture("start-background"), 0, 0, 720, 1280);
        sb.end();

        stage.draw();
    }
    public void dispose() {

    }

    // Helpers
    private void loadMenuButtons() {
        loadNewGameButton();
        loadLoadGameButton();
        loadOptionsButton();
    }
    private void loadNewGameButton() {
        Image button_newGame = new Image(Betrayal.res.getTexture("new-game"));
        button_newGame.layout();
        button_newGame.setBounds((Betrayal.WIDTH - button_newGame.getImageWidth()) / 2,
                800, 512, 144);
        button_newGame.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.CHARACTER_SELECTION);
            }
        });
        stage.addActor(button_newGame);
    }
    private void loadLoadGameButton() {
        Image button_loadGame = new Image(Betrayal.res.getTexture("load-game"));
        button_loadGame.layout();
        button_loadGame.setBounds((Betrayal.WIDTH - button_loadGame.getImageWidth()) / 2,
                600, 512, 144);
        button_loadGame.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.MENU);
            }
        });
        stage.addActor(button_loadGame);
    }
    private void loadOptionsButton() {
        Image button_options = new Image(Betrayal.res.getTexture("instructions"));
        button_options.layout();
        button_options.setBounds((Betrayal.WIDTH - button_options.getImageWidth()) / 2,
                400, 512, 144);
        button_options.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.MENU);
            }
        });
        stage.addActor(button_options);
    }

    // Getters
    public OrthographicCamera getCam() {
        return super.getCam();
    }
}
