/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.scene2d;

import com.badlogic.gdx.math.Vector2;

public class Dimension {

	private float x, y, width, height;
	private boolean isCenterX, isCenterY;

	public Dimension(Vector2 coords, float width, float height) {
		this(coords.x, coords.y, width, height, false, false);
	}

	public Dimension(float x, float y, float width, float height) {
		this(x, y, width, height, false, false);
	}

	public Dimension(Vector2 coords, float width, float height,
					 boolean isCenterX, boolean isCenterY) {
		this(coords.x, coords.y, width, height, isCenterX, isCenterY);
	}

	public Dimension(float x, float y, float width, float height,
					 boolean isCenterX, boolean isCenterY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isCenterX = isCenterX;
		this.isCenterY = isCenterY;
	}

	// Getters
	public Vector2 getCoords() {
		return new Vector2(getX(), getY());
	}

	public float getX() {
		if (isCenterX) return x - width / 2;
		else return x;
	}

	public float getRightX() {
		return getX() + width;
	}

	public float getTopY() {
		return getY() + getHeight();
	}

	public float getY() {
		if (isCenterY) return y - height / 2;
		else return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}
