/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.dungeon.cards.MonsterManager;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.dungeon.utils.DungeonCoords;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;

public class Dungeon extends GameState {

	private Field field;
	private Monster monster;

	private int floor, numPlayers, strongestPlayer;

	public Dungeon(GameStateManager gsm) {
		super(gsm);
		//this.floor = floor;
		//this.numPlayers = numPlayers;

		// todo player.getCharacters() should be other real life characters
		//dungeonManager = new DungeonManager(player.characters, monster, gsm);
		//loadStage();
		field = new Field(gsm);

		// Add all player and monster cards to the field
		int playerNum = 0;
		for (Character character : game.characters) {
			field.addCard(new PlayerCard(DungeonCoords.player[playerNum], character, res));
			playerNum++;
		}
		MonsterManager monsterManager = new MonsterManager(1, res, field);
		field.turnManager.draw();
		stage.addActor(field);
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
