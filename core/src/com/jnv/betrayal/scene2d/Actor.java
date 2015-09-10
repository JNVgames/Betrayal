/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.scene2d;

public class Actor extends com.badlogic.gdx.scenes.scene2d.Actor {

	public Actor() {
		super();
	}

	public void setBounds(Dimension dimension) {
		setBounds(dimension.getX(), dimension.getY(), dimension.getWidth(),
				dimension.getHeight());
	}
}
