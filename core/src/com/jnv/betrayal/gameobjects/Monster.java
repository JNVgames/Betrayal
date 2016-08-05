package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Monster {

	private Texture monsterTexture, skillTexture;
	private String textureName, nickname;
	private int id, health, attack, defense, width, height, xPos, yPos, numTargets, goldReward, effectCD;
	private Effect effect;

	public Monster(int id, BetrayalAssetManager res, String textureName, String nickname,
				   int health, int attack, int defense, int width, int height, int xPos, int yPos,
				   int numTargets, int effectCD, int goldReward, Effect effect, String skillTextureName) {
		this.textureName = textureName;
		this.nickname = nickname;
		this.id = id;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.width = width;
		this.goldReward = goldReward;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.numTargets = numTargets;
		this.effectCD = effectCD;
		this.effect = effect;
		monsterTexture = res.getTexture(textureName);
		if (skillTextureName != null) skillTexture = res.getTexture(skillTextureName);
		res.loadMonster(textureName, this);
	}

	public Monster(String name, BetrayalAssetManager res) {
		Monster src = res.getMonster(name);
		id = src.getID();
		textureName = name;
		health = src.health;
		attack = src.attack;
		defense = src.defense;
		width = src.width;
		height = src.height;
		xPos = src.xPos;
		yPos = src.yPos;
		goldReward = src.goldReward;
		numTargets = src.numTargets;
		this.effect = src.effect;
		effectCD = src.effectCD;
		nickname = src.nickname;
		monsterTexture = src.monsterTexture;
		skillTexture = src.skillTexture;
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

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public int getGoldReward() {
		return goldReward;
	}

	public int getDefense() {
		return defense;
	}

	public int getNumTargets() {
		return numTargets;
	}

	public int getWidth() {
		return width;
	}

	public String getNickname() {
		return nickname;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public Texture getMonsterTexture() {
		return monsterTexture;
	}

	public Texture getSkillTexture() {
		return skillTexture;
	}

	public int getEffectCD() {
		return effectCD;
	}

	public static class MonsterFactory {

		private String nickname = "stub";
		private String textureName = "stub";
		private String skillTextureName;
		private BetrayalAssetManager res;
		private int id, health, attack, defense, textureWidth, textureHeight, x, y, numTargets, effectCD;
		private Effect effect;
		private int goldReward = 100;

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

		public MonsterFactory skillTextureName(String textureName) {
			skillTextureName = textureName;
			return this;
		}

		public MonsterFactory goldReward(int gold) {
			goldReward = gold;
			return this;
		}

		public MonsterFactory effectCooldown(int turns) {
			effectCD = turns;
			return this;
		}

		public Monster build() {
			return new Monster(id, res, textureName, nickname, health, attack, defense, textureWidth,
					textureHeight, x, y, numTargets, effectCD, goldReward, effect, skillTextureName);
		}
	}
}
