/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Weapon extends Equip {

	private int attack;

	public Weapon(int id, String name, BetrayalAssetManager res, int cost_buy, int new_attack)
	{
		super(id, name, res, cost_buy);
		attack = new_attack;
	}

	public Weapon(String name, BetrayalAssetManager res) {
		super(name, res);
	}

	public int  getAttack(){return attack;}

}
