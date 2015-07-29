package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.utilities.CharacterSelectionScreen;

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
