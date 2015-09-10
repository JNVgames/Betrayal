/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.dungeon.DungeonMonster;
import com.jnv.betrayal.resources.BetrayalAssetManager;

import java.util.Random;

public class MonsterManager {

	private Image image;
	private DungeonMonster monster;
	private BetrayalAssetManager res;

	public MonsterManager(BetrayalAssetManager res, Stage stage) {
		this.res = res;
		int tier = 10;  //CHANGE accord to floor level
		tier = generateMonster(tier); // TODO: take this out later. fully randomizes
		int monsterID = generateMonster(tier);
		this.monster = new DungeonMonster(res, tier, monsterID, stage);
	}

	/**
	 * Generates a random mob based on the floor the highest player in the party is currently on
	 *
	 * @param tier tier
	 * @return monster for the dungeon
	 */
	public int generateMonster(int tier) { // MonsterGenerator(int floor)
		Random randomNumberGenerator = new Random();
		int x;
		switch (tier) {
			case 0:
				x = 0;
				break;
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
				x = randomNumberGenerator.nextInt(8);
				break;
			case 5:
				x = randomNumberGenerator.nextInt(6);
				break;
			default:
				x = randomNumberGenerator.nextInt(5);
				break;
		}
		return x;
		//return new Monster("monster-tier1-" + x, res);
	}
}
