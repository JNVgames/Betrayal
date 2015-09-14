/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.network.Player;
import com.jnv.betrayal.dungeon.managers.DungeonManager;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;

public class Dungeon extends GameState {

	private DungeonManager dungeonManager;
	private Monster monster;

	private int floor, numPlayers, strongestPlayer;

	private Player player;

	public Dungeon(GameStateManager gsm) {
		super(gsm);
		player = gsm.getGame().getPlayer();
		//this.floor = floor;
		//this.numPlayers = numPlayers;

		// todo player.getCharacters() should be other real life characters
		dungeonManager = new DungeonManager(player.characters, monster, gsm);
		loadStage();
	}

	public void update(float dt) {
		stage.act(dt);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	public void dispose() {

	}

	private void loadStage() {
		loadTimer();
		//loadMonster();
	}

	// Helpers
	private void loadTimer() {
		Label label = new Label("0:25", FontManager.getFont(70));
		label.setX((Betrayal.WIDTH - label.getWidth()) / 2);
		label.setY(Betrayal.HEIGHT - label.getHeight() - 20);
		stage.addActor(label);
	}
}
