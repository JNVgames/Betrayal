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


	public static class HeadGearFactory {
		private int id = -1;
		private String name = "";
		private int costBuy = -1;
		private int defense = -1;
		private BetrayalAssetManager res;

		public HeadGearFactory(BetrayalAssetManager res) {
			this.res = res;
		}

		public HeadGearFactory id(int id){
			this.id = id;
			return this;
		}
		public HeadGearFactory name(String name) {
			this.name = name;
			return this;
		}

		public HeadGearFactory costBuy(int costBuy) {
			this.costBuy = costBuy;
			return this;
		}

		public HeadGearFactory defense(int defense) {
			this.defense = defense;
			return this;
		}

		public HeadGear build() {
			return new HeadGear(id, name, res, costBuy, defense);
		}


	}
}
