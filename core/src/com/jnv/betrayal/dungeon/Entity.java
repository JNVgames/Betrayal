/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.dungeon.actions.Action;

public abstract class Entity {

	protected Stage stage;
	protected int hp;
	protected boolean poisoned, frozen;

	protected Entity(Stage stage) {
		this.stage = stage;
		poisoned = false;
		frozen = false;
	}

	//public abstract void handleEvent(Action action);
}
