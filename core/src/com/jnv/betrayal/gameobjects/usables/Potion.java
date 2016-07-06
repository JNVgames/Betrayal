/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects.usables;

import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.gameobjects.Usables;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Potion extends Usables {

	public Potion(int id, String name, BetrayalAssetManager res, int buyCost,
				  String description, Effect effect) {
		super(id, name, res, buyCost, description, effect);
	}

	public Potion(String name, BetrayalAssetManager res) {
		super(name, res);
	}

	public static class PotionFactory {
		private int id = -1;
		private String name = "";
		private int costBuy = 0;
		private BetrayalAssetManager res;
		private String description;
		private Effect effect;

		public PotionFactory(BetrayalAssetManager res) {
			this.res = res;
		}

		public PotionFactory id(int id) {
			this.id = id;
			return this;
		}

		public PotionFactory name(String name) {
			this.name = name;
			return this;
		}

		public PotionFactory costBuy(int costBuy) {
			this.costBuy = costBuy;
			return this;
		}

		public PotionFactory description(String description) {
			this.description = description;
			return this;
		}

		public PotionFactory effect(Effect effect) {
			this.effect = effect;
			return this;
		}

		public Potion build() {
			return new Potion(id, name, res, costBuy, description, effect);
		}
	}

}
