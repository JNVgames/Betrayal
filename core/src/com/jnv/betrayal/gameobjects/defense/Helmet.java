package com.jnv.betrayal.gameobjects.defense;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.ItemNaming;
import com.jnv.betrayal.gameobjects.Previewable;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Helmet extends Equip implements Previewable {

	private Texture preview;

	public Helmet(int id, String name, BetrayalAssetManager res, int buyCost, int health,
				  int attack, int defense, String description) {
		super(id, name, res, buyCost, health, attack, defense, description);
		this.defense = defense;
		this.description = description;
	}

	public Helmet(String name, BetrayalAssetManager res) {
		super(name, res);
		preview = res.getTexture(ItemNaming.toPreview(name));
	}

	public Texture getPreview() {
		return preview;
	}

	public static class HelmetFactory {
		private int id = -1;
		private String name = "";
		private int costBuy = -1;
		private int defense = -1;
		private BetrayalAssetManager res;
		private String description;

		public HelmetFactory(BetrayalAssetManager res) {
			this.res = res;
		}

		public HelmetFactory id(int id) {
			this.id = id;
			return this;
		}

		public HelmetFactory name(String name) {
			this.name = name;
			return this;
		}

		public HelmetFactory costBuy(int costBuy) {
			this.costBuy = costBuy;
			return this;
		}

		public HelmetFactory defense(int defense) {
			this.defense = defense;
			return this;
		}

		public HelmetFactory description(String description) {
			this.description = description;
			return this;
		}

		public Helmet build() {
			return new Helmet(id, name, res, costBuy, 0, 0, defense, description);
		}
	}
}
