package com.jnv.betrayal.gameobjects.special;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.ItemNaming;
import com.jnv.betrayal.gameobjects.Previewable;
import com.jnv.betrayal.resources.BetrayalAssetManager;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Cloak extends Equip implements Previewable {

	private Texture preview;

	public Cloak(int id, String textureName, BetrayalAssetManager res, int cost_buy,
				 int health, int attack, int defense, String description) {
		super(id, textureName, res, cost_buy, health, attack, defense, description);
	}

	public Cloak(String textureName, BetrayalAssetManager res) {
		super(textureName, res);
		preview = res.getTexture(ItemNaming.toPreview(textureName));
	}

	@Override
	public Texture getPreview() {
		return preview;
	}

	public static class CloakFactory {

		private int id = -1;
		private String name = "";
		private int costBuy = -1;
		private BetrayalAssetManager res;
		private String description;

		public CloakFactory(BetrayalAssetManager res) {
			this.res = res;
		}

		public CloakFactory id(int id) {
			this.id = id;
			return this;
		}

		public CloakFactory name(String name) {
			this.name = name;
			return this;
		}

		public CloakFactory costBuy(int costBuy) {
			this.costBuy = costBuy;
			return this;
		}

		public CloakFactory description(String description) {
			this.description = description;
			return this;
		}

		public Cloak build() {
			return new Cloak(id, name, res, costBuy, 0, 0, 0, description);
		}
	}
}
