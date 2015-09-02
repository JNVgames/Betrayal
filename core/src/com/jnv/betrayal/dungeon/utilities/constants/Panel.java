/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.utilities.constants;

import com.badlogic.gdx.math.Vector2;
import com.jnv.betrayal.main.Betrayal;

public final class Panel {

	public static final float BUTTON_HEIGHT = 150;
	public static final float BUTTON_WIDTH = Betrayal.WIDTH / 2;

	public static final Vector2 topLeft = new Vector2(0, BUTTON_HEIGHT);
	public static final Vector2 topRight = new Vector2(BUTTON_WIDTH, BUTTON_HEIGHT);
	public static final Vector2 bottomLeft = new Vector2(0, 0);
	public static final Vector2 bottomRight = new Vector2(BUTTON_WIDTH, 0);
	public static final Vector2 top = new Vector2(0, BUTTON_HEIGHT * 2);
}
