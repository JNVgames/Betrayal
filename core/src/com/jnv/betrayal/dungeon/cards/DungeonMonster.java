/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.InputListener;

public class DungeonMonster {

	public DungeonMonster(final BetrayalAssetManager res, int tier, int monsterID, final com.jnv.betrayal.dungeon.mechanics.Field field) {
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
		for (int i = 0; i < numMonsters; i++) {
			final Monster monster = new Monster("monster-tier" + tier + "-" + monsterID, res);
			MonsterCard monsterCard = new MonsterCard(monster.getxPos(), monster.getyPos(),
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
			System.out.println("wtghfghfghfhgfh" + monster.getGoldReward());
		}
	}
}
