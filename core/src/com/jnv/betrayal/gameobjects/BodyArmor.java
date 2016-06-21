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
		preview = res.getTexture(ItemNaming.toPreview(name));
	}

	public BodyArmor(int id, String name, BetrayalAssetManager res, int cost_buy, int defense) {
		super(id, name, res, cost_buy);
		this.defense = defense;
	}

	public int getDefense() { return defense;}

	public Texture getPreview() {
		return preview;
	}

	public static class ArmorFactory {
		private int id = -1;
		private String name = "";
		private int costBuy = -1;
		private int defense = -1;
		private BetrayalAssetManager res;

		public ArmorFactory(BetrayalAssetManager res) {
			this.res = res;
		}

		public ArmorFactory id(int id){
			this.id = id;
			return this;
		}
		public ArmorFactory name(String name) {
			this.name = name;
			return this;
		}

		public ArmorFactory costBuy(int costBuy) {
			this.costBuy = costBuy;
			return this;
		}

		public ArmorFactory defense(int defense) {
			this.defense = defense;
			return this;
		}

		public BodyArmor build() {
			return new BodyArmor(id, name, res, costBuy, defense);
		}

	}

}
