/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public abstract class Usables extends Item {

	protected Effect effect;

	protected Usables(int id, String name, BetrayalAssetManager res, int buyCost, String description,
					  Effect effect) {
		super(id, name, res, buyCost, description);
		this.res = res;
		this.effect = effect;
	}

	public Usables(String name, BetrayalAssetManager res) {
		super(name, res);
		Item src = res.getItem(name);
		this.effect = ((Usables) src).effect;
	}

	public Effect getEffect() {
		return effect;
	}
}
