/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Weapon extends Equip {

	private int attack;

	public void setAttack(int attack){this.attack = attack;	}

	public int  getAttack(){return attack;}
	public Weapon(int id, String name, BetrayalAssetManager res) {
		super(id, name, res);
	}

	public Weapon(String name, BetrayalAssetManager res) {
		super(name, res);
	}

}
