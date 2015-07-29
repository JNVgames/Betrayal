package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

public abstract class GameState  {

    protected GameStateManager gsm;
    protected Betrayal game;
    protected SpriteBatch sb;
    protected OrthographicCamera cam;
    protected FitViewport fitViewport;
    protected Stage stage;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        sb = game.getBatch();
        cam = game.getCamera();
        fitViewport = game.getFitViewport();
        stage = game.getStage();
        stage.clear();
    }

    public abstract void update(float dt);
    public abstract void handleInput();
    public abstract void render();
    public abstract void dispose();

    public OrthographicCamera getCam() { return cam; }
}
