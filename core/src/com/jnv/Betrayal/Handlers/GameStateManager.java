package com.jnv.Betrayal.Handlers;

import com.badlogic.gdx.Gdx;
import com.jnv.Betrayal.GameStates.CharacterSelection;
import com.jnv.Betrayal.GameStates.GameState;
import com.jnv.Betrayal.GameStates.Menu;
import com.jnv.Betrayal.GameStates.SplashScreen;
import com.jnv.Betrayal.Main.Betrayal;

import java.util.Stack;

public class GameStateManager {

    private Betrayal game;
    private Stack<GameState> gameStates;

    public State currentState;

    public enum State {
        SPLASH,
        MENU,
        CHARACTER_SELECTION
    }

    public GameStateManager(Betrayal game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(State.SPLASH);
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }
    public void render () {
        gameStates.peek().render();
    }

    // Helpers
    public void pushState(State state) {
        gameStates.push(getState(state));
    }
    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }

    // Getters
    public Betrayal getGame() { return game; }
    private GameState getState(State state) {
        currentState = state;
        switch (state) {
            case SPLASH:
                return new SplashScreen(this);

            case MENU:
                return new Menu(this);

            case CHARACTER_SELECTION:
                return new CharacterSelection(this);

         //   case INFO:
         //      return new InfoState(this);

            default:
                Gdx.app.log("GameStateManager", "getState() returns null: ERROR");
                return null;
        }
    }
    public GameState state() {
        return gameStates.peek();
    }

    // Setters
    public void setState(State state) {
        popState();
        pushState(state);
    }

}

