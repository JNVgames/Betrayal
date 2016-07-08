package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Monster {

	private Texture monsterTexture;
	private String textureName, nickname;
	private int id, health, attack, defense, width, height, xPos, yPos, numTargets;
	private Effect effect;

	public Monster(int id, BetrayalAssetManager res, String textureName, String nickname,
				   int health, int attack, int defense, int width, int height, int xPos, int yPos,
				   int numTargets, Effect effect) {
		this.textureName = textureName;
		this.nickname = nickname;
		this.id = id;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.numTargets = numTargets;
		this.effect = effect;
		monsterTexture = res.getTexture(textureName);
		res.loadMonster(textureName, this);
	}

	public Monster(String name, BetrayalAssetManager res) {
		Monster src = res.getMonster(name);
		id = src.getID();
		textureName = name;
		health = src.getHealth();
		attack = src.getAttack();
		defense = src.getDefense();
		width = src.getWidth();
		height = src.getHeight();
		xPos = src.getxPos();
		yPos = src.getyPos();
		numTargets = src.getNumTargets();
		this.effect = src.getEffect();
		nickname = src.getNickname();
		monsterTexture = src.getMonsterTexture();
	}

	// Getters
	public int getID() {
		return id;
	}

	public String getName() {
		return textureName;
	}

	public Effect getEffect() {
		return effect;
	}

	// Setters
	public void setAutoAttackTimer(int new_normalAattackTimer) {
		numTargets = new_normalAattackTimer;
	}

	public void setXPos(int new_xPos) {
		xPos = new_xPos;
	}

	// Getters
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

	public int getNumTargets() {
		return numTargets;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int new_Width) {
		width = new_Width;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int new_Height) {
		height = new_Height;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int new_yPos) {
		yPos = new_yPos;
	}

	public Texture getMonsterTexture() {
		return monsterTexture;
	}

	public static class MonsterFactory {

		private String nickname = "stub";
		private String textureName = "stub";
		private BetrayalAssetManager res;
		private int id, health, attack, defense, textureWidth, textureHeight, x, y, numTargets;
		private Effect effect;

		public MonsterFactory() {
		}

		public MonsterFactory assetManager(BetrayalAssetManager res) {
			this.res = res;
			return this;
		}

		public MonsterFactory id(int id) {
			this.id = id;
			return this;
		}

		public MonsterFactory nickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public MonsterFactory textureName(String textureName) {
			this.textureName = textureName;
			return this;
		}

		public MonsterFactory health(int health) {
			this.health = health;
			return this;
		}

		public MonsterFactory attack(int attack) {
			this.attack = attack;
			return this;
		}

		public MonsterFactory defense(int defense) {
			this.defense = defense;
			return this;
		}

		public MonsterFactory textureWidth(int textureWidth) {
			this.textureWidth = textureWidth;
			return this;
		}

		public MonsterFactory textureHeight(int textureHeight) {
			this.textureHeight = textureHeight;
			return this;
		}

		public MonsterFactory x(int x) {
			this.x = x;
			return this;
		}

		public MonsterFactory y(int y) {
			this.y = y;
			return this;
		}

		public MonsterFactory numTargets(int numTargets) {
			this.numTargets = numTargets;
			return this;
		}

		public MonsterFactory effect(Effect effect) {
			this.effect = effect;
			return this;
		}

		public Monster build() {
			return new Monster(id, res, textureName, nickname, health, attack, defense, textureWidth,
					textureHeight, x, y, numTargets, effect);
		}
	}
}
