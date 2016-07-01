/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects.special;

import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Ring extends Equip {

	public Ring(int id, String name, BetrayalAssetManager res, int cost_buy, int health,
				int attack, int defense, String description) {
		super(id, name, res, cost_buy, health, attack, defense, description);
	}

	public Ring(String name, BetrayalAssetManager res) {
		super(name, res);
	}
	
	public static class RingFactory {
		private int id = -1;
		private String name = "";
		private int costBuy = 0;
		private int defense = 0;
		private int attack = 0;
		private int health = 0;
		private BetrayalAssetManager res;
		private String description;

		public RingFactory(BetrayalAssetManager res) {
			this.res = res;
		}

		public RingFactory id(int id) {
			this.id = id;
			return this;
		}

		public RingFactory name(String name) {
			this.name = name;
			return this;
		}

		public RingFactory costBuy(int costBuy) {
			this.costBuy = costBuy;
			return this;
		}

		public RingFactory defense(int defense) {
			this.defense = defense;
			return this;
		}

		public RingFactory health(int health) {
			this.health = health;
			return this;
		}

		public RingFactory attack(int attack) {
			this.attack = attack;
			return this;
		}

		public RingFactory description(String description) {
			this.description = description;
			return this;
		}

		public Ring build() {
			return new Ring(id, name, res, costBuy, health, attack, defense, description);
		}
	}
}
