/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.renamedthings;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.Dimension;

public abstract class Card extends Actor {

	protected int health, attack, defense;
	protected Texture texture;
	protected HealthBar healthBar;
	protected BetrayalAssetManager res;

	protected Card(Dimension dimension, BetrayalAssetManager res) {
		setBounds(dimension);
		this.res = res;
		initialize();
	}

	protected Card(float x, float y, float width, float height,
				   BetrayalAssetManager res) {
		this(new Dimension(x, y, width, height), res);
	}

	private void initialize() {
		healthBar = new HealthBar(getX(), getY(), res);
	}

}
