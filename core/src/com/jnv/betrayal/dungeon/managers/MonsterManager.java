/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.dungeon.objects.DungeonMonster;
import com.jnv.betrayal.gameobjects.Monster;

public class MonsterManager {

	private Image image;
	private DungeonMonster monster;

	public MonsterManager(Monster monster,
						  Stage stage) {
		this.monster = new DungeonMonster(monster, stage);
	}
}
