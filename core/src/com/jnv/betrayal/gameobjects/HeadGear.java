/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class HeadGear extends Equip implements Previewable {

	private int defense;
	private Texture preview;

	public HeadGear(int id, String name, BetrayalAssetManager res,int cost_buy, int defense) {
		super(id, name, res, cost_buy);
		this.defense = defense;
	}

	public HeadGear(String name, BetrayalAssetManager res) {
		super(name, res);
		preview = res.getTexture(ItemNaming.toPreview(name));
	}

	public int getDefense() { return defense; }

	public Texture getPreview() {
		return preview;
	}
}
