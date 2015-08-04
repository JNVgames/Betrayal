package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.handlers.GameStateManager;

public class Dungeon extends GameState {

    private MonsterGenerator mg;
    private Texture monster;

    private int floor, numPlayers;

    public Dungeon(GameStateManager gsm, int floor, int numPlayers) {
        super(gsm);

        this.floor = floor;
        this.numPlayers = numPlayers;
    }

    public void update(float dt) {

    }
    public void handleInput() {

    }
    public void render() {

    }
    public void dispose() {

    }

    /** Generates a random mob based on the floor the highest player in the party is currently on */
    public class MonsterGenerator {

        public MonsterGenerator() {

        }
    }
}
