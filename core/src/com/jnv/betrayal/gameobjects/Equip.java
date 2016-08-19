package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public abstract class Equip extends Item {

	protected int attack, defense, health;

	protected Equip(int id, String name, BetrayalAssetManager res, int cost_buy, int health, int attack, int defense, String description) {
		super(id, name, res, cost_buy, description);
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		isEquippable = true;
	}

	public Equip(String name, BetrayalAssetManager res) {
		super(name, res);
		Item src = res.getItem(name);
		health = ((Equip) src).getHealth();
		attack = ((Equip) src).getAttack();
		defense = ((Equip) src).getDefense();
	}

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}
}
