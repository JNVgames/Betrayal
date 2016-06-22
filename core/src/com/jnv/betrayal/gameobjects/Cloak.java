package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Cloak extends Equip {

	public Cloak(int id, String name, BetrayalAssetManager res, int cost_buy,
				 int health, int attack, int defense, String description) {
		super(id, name, res, cost_buy, health, attack, defense, description);
	}

	public Cloak(String name, BetrayalAssetManager res) {
		super(name, res);
	}
}
