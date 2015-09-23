/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Group extends com.badlogic.gdx.scenes.scene2d.Group {

	public Group() {
		super();
	}

	public void act(float delta) {
		super.act(delta);
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	public void setBounds(Dimension dimension) {
		super.setBounds(dimension.getX(), dimension.getY(),
				dimension.getWidth(), dimension.getHeight());
	}

	public Dimension getDimensions() {
		return new Dimension(getX(), getY(), getWidth(), getHeight());
	}

}
