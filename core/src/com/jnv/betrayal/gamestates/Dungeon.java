/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

public class Dungeon extends GameState {

    private MonsterGenerator mg;
    private Texture monster;

    private int floor, numPlayers;

    public Dungeon(GameStateManager gsm) {
        super(gsm);

        //this.floor = floor;
        //this.numPlayers = numPlayers;

        loadStage();
    }

    public void update(float dt) {
        stage.act(dt);
    }
    public void handleInput() {

    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }
    public void dispose() {

    }

    // Helpers
    private Label.LabelStyle loadFont(int fontSize) {
        return Betrayal.getHurtmoldFontLabelStyle(fontSize);
    }
    private void loadStage() {
        loadTimer();
        loadActionBar();
    }
    private void loadTimer() {
        Label label = new Label("0:25", loadFont(70));
        label.setX((Betrayal.WIDTH - label.getWidth()) / 2);
        label.setY(Betrayal.HEIGHT - label.getHeight() - 20);
        stage.addActor(label);
    }
    private void loadActionBar() {

    }

    /** Generates a random mob based on the floor the highest player in the party is currently on */
    public class MonsterGenerator {

        public MonsterGenerator() {

        }
    }
}
