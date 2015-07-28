package com.jnv.Betrayal.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.Betrayal.Handlers.GameStateManager;
import com.jnv.Betrayal.Main.Betrayal;
import com.jnv.Betrayal.Utilities.CharacterSelectionScreen;

public class CharacterSelection extends GameState {

    public CharacterSelection(GameStateManager gsm) {
        super(gsm);
        Gdx.app.log("log", "CharacterSelection constructor");

        CharacterSelectionScreen.loadStage(stage);
        CharacterSelectionScreen.createScreen();
    }

    public void update(float dt) {
        stage.act(dt);
    }
    public void handleInput() {

    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0); // Set background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }
    public void dispose() {

    }
}
