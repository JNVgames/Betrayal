/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Shield extends Equip {

	public Shield(int id, String name, BetrayalAssetManager res) {
		super(id, name, res);
	}

	public Shield(String name, BetrayalAssetManager res) {
		super(name, res);
	}
}
