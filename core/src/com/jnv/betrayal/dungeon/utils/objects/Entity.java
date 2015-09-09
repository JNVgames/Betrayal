/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.utils.objects;

import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Entity {

	protected Stage stage;
	protected int hp;
	protected boolean poisoned, frozen;

	protected Entity(Stage stage) {
		this.stage = stage;
		poisoned = false;
		frozen = false;
	}

	public void poison(int turns, int damage) {
		poisoned = true;
	}

	public void freeze(int turns, int damage) {
		frozen = true;
	}
}
