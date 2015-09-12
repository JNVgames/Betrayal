/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.MonsterInfo;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class DungeonMonster extends Entity {

	private Image[] image;
	private Monster[] monster;
	private BetrayalAssetManager res;

	public DungeonMonster(BetrayalAssetManager res, int tier, int monsterID, Stage stage) {
		super(stage);
		int numMonsters = 1;
		//tier = 4;
		//monsterID = 7;
		if ((tier == 1 && monsterID == 9) || (tier == 3 && monsterID == 0) || (tier == 3 && monsterID == 2) || (tier ==4 && monsterID ==6)) {
			numMonsters = 2;
		}else if ((tier==2 && monsterID ==2) || (tier ==3 && monsterID ==8) || (tier == 4&&monsterID==5)){
			numMonsters=3;
		}else if ((tier ==4 && monsterID ==7)){
			numMonsters=6;
		}
		image = new Image[6];
		monster = new Monster[6];
		for (int i = 0; i < numMonsters; i++) {
						monster[i] = new Monster("monster-tier" + tier + "-" + monsterID, res);
						image[i] = new Image(monster[i].getMonsterTexture());
						image[i].layout();
						image[i].setBounds(monster[i].getxPos(), monster[i].getyPos(),
								monster[i].getWidth(), monster[i].getHeight());
		/*	image[i].addListener(new InputListener(image[i]) {
				@Override
				new MonsterInfo(Betrayal.game,monster[i], 10) //CHANGE 10 to current health??
			});
			*/
			image[i].addAction(Actions.alpha(0));
			image[i].addAction(Actions.delay(1, Actions.fadeIn(2)));
			stage.addActor(image[i]);
			monsterID+=10;
			HealthBar(res, monster[i].getxPos(), monster[i].getyPos() + monster[i].getHeight(), stage);
		}
	}

	public void HealthBar(BetrayalAssetManager res, int xPos, int yPos, Stage stage){

		Image healthBarBackground, healthBar;
		xPos+=50;
		yPos-=25;
		healthBarBackground = new Image(res.getTexture("bar-background"));
		healthBarBackground.layout();
		healthBarBackground.setBounds(xPos, yPos, 227, 25);
		healthBarBackground.addAction(Actions.alpha(0));
		healthBarBackground.addAction(Actions.delay(1, Actions.fadeIn(2)));
		stage.addActor(healthBarBackground);

		healthBar= new Image(res.getTexture("green-bar"));
		healthBar.layout();
		healthBar.setBounds(xPos+10, yPos+8, 200, 8);
		healthBar.addAction(Actions.alpha(0));
		healthBar.addAction(Actions.delay(1, Actions.fadeIn(2)));
		stage.addActor(healthBar);
/*
		nameText = new Label(name, FontManager.getFont(17));
		nameText.setX(xPos+5);
		nameText.setY(yPos + 4);
		stage.addActor(nameText);
		*/
	}
}
