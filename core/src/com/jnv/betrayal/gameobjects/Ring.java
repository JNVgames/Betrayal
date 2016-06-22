/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Ring extends Equip {

	public Ring(int id, String name, BetrayalAssetManager res, int cost_buy, int health,
				int attack, int defense, String description) {
		super(id, name, res, cost_buy, health, attack, defense, description);
	}

	public Ring(String name, BetrayalAssetManager res) {
		super(name, res);
	}
}
