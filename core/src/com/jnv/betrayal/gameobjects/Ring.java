/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Ring extends Equip {

	public Ring(int id, String name, BetrayalAssetManager res, int cost_buy, String description) {
		super(id, name, res, cost_buy, description);
	}

	public Ring(String name, BetrayalAssetManager res) {
		super(name, res);
	}
}
