/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class DungeonMonster {

	private MonsterCard[] monsterCards;
	private Monster[] monster;

	public DungeonMonster(BetrayalAssetManager res, int tier, int monsterID, com.jnv.betrayal.dungeon.mechanics.Field field) {
		int numMonsters = 1;
		//tier = 4;
		//monsterID = 7;
		if ((tier == 1 && monsterID == 9) || (tier == 3 && monsterID == 0) || (tier == 3 && monsterID == 2) || (tier == 4 && monsterID == 6)) {
			numMonsters = 2;
		} else if ((tier == 2 && monsterID == 2) || (tier == 3 && monsterID == 8) || (tier == 4 && monsterID == 5)) {
			numMonsters = 3;
		} else if ((tier == 4 && monsterID == 7)) {
			numMonsters = 6;
		}
		monsterCards = new MonsterCard[6];
		monster = new Monster[6];
		for (int i = 0; i < numMonsters; i++) {
			monster[i] = new Monster("monster-tier" + tier + "-" + monsterID, res);
			// todo simplify this code
			monsterCards[i] = new MonsterCard(monster[i].getxPos(), monster[i].getyPos(),
					monster[i].getWidth(), monster[i].getHeight(), monster[i], res);
			field.addCard(monsterCards[i]);
			monsterID += 10;
		}
	}
}
