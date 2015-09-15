/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Weapon extends Equip implements DualWieldable {

	private int attack;
	private Texture leftPreview, rightPreview;

	public Weapon(int id, String name, BetrayalAssetManager res, int cost_buy, int new_attack) {
		super(id, name, res, cost_buy);
		attack = new_attack;
	}

	public Weapon(String name, BetrayalAssetManager res) {
		super(name, res);
		leftPreview = res.getTexture(ItemNaming.toPreview(name, true));
		rightPreview = res.getTexture(ItemNaming.toPreview(name, false));
	}

	public int  getAttack(){return attack;}

	public Texture getLeftPreview() {
		return leftPreview;
	}

	public Texture getRightPreview() {
		return rightPreview;
	}
}
