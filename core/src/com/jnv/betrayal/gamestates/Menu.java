/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.entities.*;
import com.jnv.betrayal.entities.Character;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

public class Menu extends GameState {

    private Group group_dialog_warning;

    public Menu(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Betrayal.WIDTH, Betrayal.HEIGHT);
        loadMenuButtons();
    }

    public void update(float dt) {
        stage.act(dt);
    }
    public void handleInput() {

    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
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
        loadInstructionsButton();
        loadHallOfFameButton();
        loadOptionsButton();
    }
    private void loadNewGameButton() {
        Image button_newGame = new Image(Betrayal.res.getTexture("new-game"));
        button_newGame.layout();
        button_newGame.setBounds((Betrayal.WIDTH - button_newGame.getImageWidth()) / 2,
                800, 512, 144);
        button_newGame.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.CHARACTER_SELECTION);
            }
        });
        stage.addActor(button_newGame);
    }
    private void loadLoadGameButton() {
        Actor button_loadGame = new Actor() {
            public void draw(Batch batch, float parentAlpha) {
                if (!Character.characters.isEmpty()) {
                    batch.draw(Betrayal.res.getTexture("load-game"),
                            (Betrayal.WIDTH - Betrayal.res.getTexture("load-game").getWidth()) / 2,
                            600, 512, 144);
                } else {
                    batch.draw(Betrayal.res.getTexture("load-game-grey"),
                            (Betrayal.WIDTH - Betrayal.res.getTexture("load-game-grey").getWidth()) / 2,
                            600, 512, 144);
                }
            }
        };
        button_loadGame.setBounds((Betrayal.WIDTH - Betrayal.res.getTexture("load-game").getWidth()) / 2,
                600, 512, 144);
        if (Character.characters.isEmpty()) button_loadGame.setTouchable(Touchable.disabled);

        button_loadGame.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    gsm.setState(GameStateManager.State.LOAD_GAME);
                }
        });
        stage.addActor(button_loadGame);
    }
    private void loadInstructionsButton() {
        Image button_instructions = new Image(Betrayal.res.getTexture("instructions"));
        button_instructions.layout();
        button_instructions.setBounds((Betrayal.WIDTH - button_instructions.getImageWidth()) / 2,
                400, 512, 144);
        button_instructions.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new Instructions(game);
            }
        });
        stage.addActor(button_instructions);
    }
    private void loadOptionsButton() {
        Image button_options = new Image(Betrayal.res.getTexture("options"));
        button_options.layout();
        button_options.setBounds((Betrayal.WIDTH - button_options.getImageWidth()/2) / 2,
                272, 256, 72);
        button_options.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new Options(game);
            }
        });
        stage.addActor(button_options);
    }
    private void loadHallOfFameButton() {
        Image button_hallOfFame = new Image(Betrayal.res.getTexture("hall-of-fame"));
        button_hallOfFame.layout();
        button_hallOfFame.setBounds((Betrayal.WIDTH - button_hallOfFame.getImageWidth()/2) / 2,
                172, 256, 72);
        button_hallOfFame.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.HALL_OF_FAME);
            }
        });
        stage.addActor(button_hallOfFame);
    }

}
