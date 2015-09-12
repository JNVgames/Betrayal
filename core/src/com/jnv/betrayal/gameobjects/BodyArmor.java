/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class BodyArmor extends Equip {
	private int defense;

	public int getDefense() { return defense;}
	public BodyArmor(int id, String name, BetrayalAssetManager res, int cost_buy, int defense) {
		super(id, name, res, cost_buy);
		this.defense = defense;
	}

	public BodyArmor(String name, BetrayalAssetManager res) {
		super(name, res);
	}

}
