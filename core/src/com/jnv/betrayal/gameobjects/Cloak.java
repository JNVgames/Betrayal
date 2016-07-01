package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Cloak extends Equip implements Previewable {

	private Texture preview;

	public Cloak(int id, String name, BetrayalAssetManager res, int cost_buy,
				 int health, int attack, int defense, String description) {
		super(id, name, res, cost_buy, health, attack, defense, description);
	}

	public Cloak(String name, BetrayalAssetManager res) {
		super(name, res);
		preview = res.getTexture(ItemNaming.toPreview(name));
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
