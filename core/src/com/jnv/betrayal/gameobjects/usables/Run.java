package com.jnv.betrayal.gameobjects.usables;

import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.gameobjects.Usables;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Run extends Usables {

	private int probablilty;

	public Run(int id, String name, BetrayalAssetManager res, int buyCost, String description, Effect effect) {
		super(id, name, res, buyCost, description, effect);

	}

	public Run(String name, BetrayalAssetManager res) {
		super(name, res);
	}

	public static class RunFactory {
		private int id = -1;
		private String name = "";
		private int costBuy = 0;

		private BetrayalAssetManager res;
		private String description;
		private Effect effect;

		public RunFactory(BetrayalAssetManager res) {
			this.res = res;
		}

		public RunFactory id(int id) {
			this.id = id;
			return this;
		}

		public RunFactory name(String name) {
			this.name = name;
			return this;
		}

		public RunFactory costBuy(int costBuy) {
			this.costBuy = costBuy;
			return this;
		}


		public RunFactory description(String description) {
			this.description = description;
			return this;
		}

		public RunFactory effect(Effect effect) {
			this.effect = effect;
			return this;
		}

		public Run build() {
			return new Run(id, name, res, costBuy, description, effect);
		}
	}

}
