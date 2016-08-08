/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.Field;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.InputListener;

import java.util.Random;

public class MonsterManager {

	private Field field;
	private BetrayalAssetManager res;

	public MonsterManager(int highestFloor, BetrayalAssetManager res, Field field) {
		this.field = field;
		this.res = res;
		int monsterID = generateMonster(highestFloor);
		addDungeonMonster(highestFloor, monsterID);
	}

	private void addDungeonMonster(int tier, int monsterID) {
		// Configure amount of monsters in dungeon. For example, mobs will have more than one monster
		int numMonsters = 1;

		if ((tier == 1 && monsterID == 9)
				|| (tier == 3 && monsterID == 0)
				|| (tier == 3 && monsterID == 2)
				|| (tier == 4 && monsterID == 6)) {
			numMonsters = 2;
		} else if ((tier == 2 && monsterID == 2)
				|| (tier == 3 && monsterID == 8)
				|| (tier == 4 && monsterID == 5)) {
			numMonsters = 3;
		} else if ((tier == 4 && monsterID == 7)) {
			numMonsters = 6;
		}
		for (int i = 0; i < numMonsters; i++) {
			final Monster monster = new Monster("monster-tier" + tier + "-" + monsterID, res);
			MonsterCard monsterCard = new MonsterCard(monster.getX(), monster.getY(),
					monster.getWidth(), monster.getHeight(), monster, res);
			field.addCard(monsterCard);
			if (monster.getSkillTexture() != null) {
				Image image = new Image(monster.getSkillTexture());
				int side = 100;
				image.setBounds(Betrayal.WIDTH - side - 10, Betrayal.HEIGHT - side - 10, side, side);
				field.addActor(image);
				image.addListener(new InputListener(image) {
					@Override
					public void doAction() {
						new OKPopup(500, 400, field.game, monster.getEffect().getDescription());
					}
				});
			}
			monsterID += 10;
			field.reward += monster.getGoldReward();
		}
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
	}
}
