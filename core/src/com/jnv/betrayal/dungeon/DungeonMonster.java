/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class DungeonMonster extends Entity {

	private Image[] image;
	private Monster[] monster;
	private BetrayalAssetManager res;

	public DungeonMonster(BetrayalAssetManager res, int tier, int monsterID, Stage stage) {
		super(stage);
		int numMonsters = 1;
		//tier = 1;
		//monsterID = 9;
		if(tier==1&&monsterID==9) {
			numMonsters = 2;
		}
		image = new Image[5];
		monster = new Monster[5];
		for (int i = 0; i < numMonsters; i++) {
						monster[i] = new Monster("monster-tier" + tier + "-" + monsterID, res);
						image[i] = new Image(monster[i].getMonsterTexture());
						image[i].layout();
						image[i].setBounds(	monster[i].getxPos(), monster[i].getyPos(),
								monster[i].getWidth(), monster[i].getHeight());
			image[i].addAction(Actions.alpha(0));
			image[i].addAction(Actions.delay(1, Actions.fadeIn(2)));
			stage.addActor(image[i]);
		}
	}
}
