/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.gameobjects.Monster;

public class DungeonMonster extends Entity {

	private Image image;

	public DungeonMonster(Monster monster,
						  Stage stage) {
		super(stage);
		Vector2 coords = new Vector2(DungeonCoords.monster);
		image = new Image(monster.getMonsterTexture());
		image.layout();
		image.setBounds(coords.x, coords.y,
				DungeonCoords.MONSTER_WIDTH, DungeonCoords.MONSTER_HEIGHT);
		image.addAction(Actions.alpha(0));
		image.addAction(Actions.delay(1, Actions.fadeIn(2)));
		stage.addActor(image);
	}
}
