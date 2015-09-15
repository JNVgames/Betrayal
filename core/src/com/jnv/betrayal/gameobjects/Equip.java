/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public abstract class Equip extends Item {

	protected Equip(int id, String name, BetrayalAssetManager res, int cost_buy) {
		super(id, name, res, cost_buy);
		isEquippable = true;
	}

	public Equip(String name, BetrayalAssetManager res) {
		super(name, res);
	}

}
