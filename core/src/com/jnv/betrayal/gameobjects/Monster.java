package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Monster {

	protected Texture monsterTexture;
	protected String MonsterName;
	protected int id, health, attack, defense;
	private BetrayalAssetManager res;

	public Monster(int id, String name, BetrayalAssetManager res) {
		this.id = id;
		MonsterName = name;
		this.res = res;
		monsterTexture = res.getTexture(name);
		res.loadMonster(name, this);
	}

	public Monster(String name, BetrayalAssetManager res) {
		Monster src = res.getMonster(name);
		id = src.getID();
		MonsterName = name;
		health = src.getHealth();
		attack = src.getAttack();
		defense = src.getDefense();
		monsterTexture = src.getMonsterTexture();
	}

	// Getters
	public int getID() {
		return id;
	}

	public String getName() {
		return MonsterName;
	}

	// Setters
	public void setName(String name) {
		MonsterName = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int new_health) {
		health = new_health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int new_attack) {
		attack = new_attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int new_defense) {
		defense = new_defense;
	}

	public Texture getMonsterTexture() {
		return monsterTexture;
	}

	public void setData(String name, int newHealth, int newAttack, int newDefense) {
		Monster src = res.getMonster(name);
		src.setHealth(newHealth);
		src.setAttack(newAttack);
		src.setDefense(newDefense);
	}
}
