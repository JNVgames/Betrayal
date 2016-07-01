/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects.usables;

import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.gameobjects.Usables;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Potion extends Usables {

	private Effect effect;
	private int attack, defense, health;

	public Potion(int id, String name, BetrayalAssetManager res, int cost_buy, int health, int attack, int defense, String description) {
		super(id, name, res, cost_buy, description);
		this.health = health;
		this.attack = attack;
		this.defense = defense;
	}

	public Potion(String name, BetrayalAssetManager res) {
		super(name, res);
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public static class PotionFactory {
		private int id = -1;
		private String name = "";
		private int costBuy = 0;
		private int defense = 0;
		private int attack = 0;
		private int health = 0;
		private BetrayalAssetManager res;
		private String description;

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

		public PotionFactory defense(int defense) {
			this.defense = defense;
			return this;
		}

		public PotionFactory health(int health) {
			this.health = health;
			return this;
		}

		public PotionFactory attack(int attack) {
			this.attack = attack;
			return this;
		}

		public PotionFactory description(String description) {
			this.description = description;
			return this;
		}

		public Potion build() {
			return new Potion(id, name, res, costBuy, health, attack, defense, description);
		}
	}

}
