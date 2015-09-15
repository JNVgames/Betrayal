/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Shield extends Equip implements DualWieldable {

	private int defense;
	private Texture leftPreview, rightPreview;

	public int getDefense() { return defense; }

	public Shield(String name, BetrayalAssetManager res) {
		super(name, res);
	}

	public Shield(int id, String name, BetrayalAssetManager res, int cost_buy, int defense) {
		super(id, name, res, cost_buy);
		this.defense = defense;
		leftPreview = res.getTexture(ItemNaming.toPreview(name, true));
		rightPreview = res.getTexture(ItemNaming.toPreview(name, false));
	}

	public Texture getLeftPreview() {
		return leftPreview;
	}

	public Texture getRightPreview() {
		return rightPreview;
	}
}
