package com.jnv.betrayal.scene2d;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

/**
 * My own version of actor
 */
public class Actor extends com.badlogic.gdx.scenes.scene2d.Actor {

	public Actor() {
		super();
	}

	public void act(float delta) {
		super.act(delta);
	}

	public void setBounds(Dimension dimension) {
		setBounds(dimension.getX(), dimension.getY(), dimension.getWidth(),
				dimension.getHeight());
	}

	public Dimension getDimensions() {
		return new Dimension(getX(), getY(), getWidth(), getHeight());
	}
}
