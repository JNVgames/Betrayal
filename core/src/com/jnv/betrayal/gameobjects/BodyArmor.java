/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class BodyArmor extends Equip implements Previewable {

	private Texture preview;
	private int defense;

	public BodyArmor(String name, BetrayalAssetManager res) {
		super(name, res);
	}

	public BodyArmor(int id, String name, BetrayalAssetManager res, int cost_buy, int defense) {
		super(id, name, res, cost_buy);
		this.defense = defense;
		preview = res.getTexture(ItemNaming.toPreview(name));
	}

	public int getDefense() { return defense;}

	public Texture getPreview() {
		return preview;
	}
}
