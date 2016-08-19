package com.jnv.betrayal.scene2d;

import com.badlogic.gdx.math.Vector2;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

**
 * Holds x, y, width, and height values. Used mainly for actor positioning. Can be used for Betrayal Actor setBounds()
 * for convenience
 */
public class Dimension {

	private float x, y, width, height;
	private boolean isCenterX, isCenterY;

	public Dimension() {

	}

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
		setBounds(x, y, width, height, isCenterX, isCenterY);
	}

	// Getters
	public Vector2 getCoords() {
		return new Vector2(getX(), getY());
	}

	public float getX() {
		if (isCenterX) return x - width / 2;
		else return x;
	}

	public void setX(float x) {
		this.x = x;
		isCenterX = false;
	}

	public float getRight() {
		return getX() + width;
	}

	public float getTop() {
		return getY() + getHeight();
	}

	public float getY() {
		if (isCenterY) return y - height / 2;
		else return y;
	}

	public void setY(float y) {
		this.y = y;
		isCenterY = false;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	// Setters
	public void setBounds(float x, float y, float width, float height) {
		setBounds(x, y, width, height, false, false);
	}

	public void setBounds(float x, float y, float width, float height, boolean isCenterX,
						  boolean isCenterY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isCenterX = isCenterX;
		this.isCenterY = isCenterY;
	}

	public void setCenterX(float x) {
		this.x = x;
		isCenterX = true;
	}

	public void setCenterY(float y) {
		this.y = y;
		isCenterY = true;
	}

	@Override
	public String toString() {
		return "Dimension{" +
				"x=" + x +
				", y=" + y +
				", width=" + width +
				", height=" + height +
				'}';
	}
}
