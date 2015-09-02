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
import com.jnv.betrayal.Network.Player;
import com.jnv.betrayal.dungeon.managers.DungeonManager;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;

import java.util.Random;

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

		monster = generateMonster(1);
		// todo player.getCharacters() should be other real life characters
		dungeonManager = new DungeonManager(player.getCharacters(), monster, gsm);
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
		loadMonster();
	}

	// Helpers
	private void loadTimer() {
		Label label = new Label("0:25", FontManager.getFont(70));
		label.setX((Betrayal.WIDTH - label.getWidth()) / 2);
		label.setY(Betrayal.HEIGHT - label.getHeight() - 20);
		stage.addActor(label);
	}

	private void loadMonster() {
		Image image_monster = new Image(monster.getMonsterTexture());
		float width = 300, height = 300;
		Vector2 center = new Vector2(Betrayal.WIDTH / 2, Betrayal.HEIGHT - 300);

		image_monster.layout();
		image_monster.setBounds(center.x - width / 2, center.y - height / 2, 300, 300);
		image_monster.addAction(Actions.alpha(0));
		image_monster.addAction(Actions.delay(1, Actions.fadeIn(2)));
		stage.addActor(image_monster);
	}

	/**
	 * Generates a random mob based on the floor the highest player in the party is currently on
	 *
	 * @param tier tier
	 * @return monster for the dungeon
	 */
	public Monster generateMonster(int tier) { // MonsterGenerator(int floor)
		// todo @joey change tier parameter to floor
		Random randomNumberGenerator = new Random();
		int x;
		switch (tier) {
			case 1:
				x = randomNumberGenerator.nextInt(10);
				break;
			case 2:
				x = randomNumberGenerator.nextInt(10);
				break;
			case 3:
				x = randomNumberGenerator.nextInt(10);
				break;
			case 4:
				x = randomNumberGenerator.nextInt(10);
				break;
			case 5:
				x = randomNumberGenerator.nextInt(10);
				break;
			default:
				x = randomNumberGenerator.nextInt(10);
				break;
		}
		return new Monster("monster-tier1-" + x, res);
	}
}
