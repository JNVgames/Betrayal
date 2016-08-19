package com.jnv.betrayal.dungeon.utils;

import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.scene2d.Dimension;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public final class Panel {

	public static final float BUTTON_HEIGHT = 150;
	public static final float BUTTON_WIDTH = Betrayal.WIDTH / 2;

	public static final Dimension topLeft = new Dimension(0, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
	public static final Dimension topRight = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
	public static final Dimension bottomLeft = new Dimension(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
	public static final Dimension bottomRight = new Dimension(BUTTON_WIDTH, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
	public static final Dimension top = new Dimension(0, BUTTON_HEIGHT, BUTTON_WIDTH * 2, BUTTON_HEIGHT);
	public static final Dimension bottom = new Dimension(0, 0, BUTTON_WIDTH * 2, BUTTON_HEIGHT);
	public static final Dimension full = new Dimension(0, 0, BUTTON_WIDTH * 2, BUTTON_HEIGHT * 2);
}
