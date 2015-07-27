package com.jnv.Betrayal.GameStates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jnv.Betrayal.Handlers.GameStateManager;
import com.jnv.Betrayal.Main.Betrayal;

public abstract class GameState  {

    protected GameStateManager gsm;
    protected Betrayal game;
    protected SpriteBatch sb;
    protected OrthographicCamera cam;
    protected FitViewport fitViewport;

    protected GameState(GameStateManager gsm) {

        this.gsm = gsm;
        game = gsm.game();
        sb = game.getBatch();
        cam = game.getCamera();
        fitViewport = game.getFitViewport();

    }

    public abstract void update(float dt);
    public abstract void handleInput();
    public abstract void render();
    public abstract void dispose();

    public OrthographicCamera getCam() { return cam; }
}
