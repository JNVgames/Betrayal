/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class HeadGear extends Equip {

	public HeadGear(int id, String name, BetrayalAssetManager res) {
		super(id, name, res);
	}

	public HeadGear(String name, BetrayalAssetManager res) {
		super(name, res);
	}
}
