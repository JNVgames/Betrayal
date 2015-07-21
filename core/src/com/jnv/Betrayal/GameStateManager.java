package com.jnv.Betrayal;

import com.badlogic.gdx.Gdx;

import java.util.Stack;

/**
 * Created by jarnin on 7/20/15.
 */
public class GameStateManager {
    private Betrayal game;
    private Stack<GameState> gameStates;
    public int currentState;

    public static final int SPLASH = 0;
    public static final int MENU = 1;           // these numbers indicate what state

    public GameStateManager(Betrayal game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(SPLASH);
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }

    public Betrayal game() { return game; }

    private GameState getState(int state) {
        currentState = state;
        switch(state) {

            case SPLASH:
                return new SplashScreen(this);

            case MENU:
                return new MenuState(this);


            default:
                Gdx.app.log("GameStateManager", "getState() returns null: ERROR");
                return null;
        }
    }

    public GameState state() {
        return gameStates.peek();
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public void render () {
        gameStates.peek().render();
    }

}

