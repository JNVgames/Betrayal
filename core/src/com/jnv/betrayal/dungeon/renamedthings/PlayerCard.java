/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.renamedthings;

import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Dimension;

public class PlayerCard extends Card {

	protected PlayerCard(Dimension dimension, BetrayalAssetManager res) {
		super(dimension, res);
	}

	protected PlayerCard(float x, float y, float width, float height, BetrayalAssetManager res) {
		super(x, y, width, height, res);
	}
}
