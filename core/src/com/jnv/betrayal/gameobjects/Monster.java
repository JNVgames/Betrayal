package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Monster {

	private Texture monsterTexture;
	private String textureName, nickname;
	private int id, health, attack, defense, width, height, xPos, yPos, numTargets;
	private int skill1, s1cooldown, skill2, s2cooldown, skill3, s3cooldown;
	private BetrayalAssetManager res;

	public Monster(int id, String name, BetrayalAssetManager res) {
		this.id = id;
		textureName = name;
		this.res = res;
		monsterTexture = res.getTexture(name);
		res.loadMonster(name, this);
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
		skill1 = src.getSkill1();
		skill2 = src.getSkill2();
		skill3 = src.getSkill3();
		s1cooldown = src.getS1CoolDown();
		s2cooldown = src.getS2CoolDown();
		s3cooldown = src.getS3CoolDown();
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

	// Setters
	public void setTextureName(String name) {
		textureName = name;
	}

	public void setAutoattackTimer(int new_normalAattackTimer) {
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

	public int getSkill1() {
		return skill1;
	}

	public void setSkill1(int new_skill1) {
		skill1 = new_skill1;
	}

	public int getSkill2() {
		return skill2;
	}

	public void setSkill2(int new_skill2) {
		skill2 = new_skill2;
	}

	public int getSkill3() {
		return skill3;
	}

	public void setSkill3(int new_skill3) {
		skill3 = new_skill3;
	}

	public int getS1CoolDown() {
		return s1cooldown;
	}

	public void setS1CoolDown(int new_S1CoolDown) {
		s1cooldown = new_S1CoolDown;
	}

	public int getS2CoolDown() {
		return s2cooldown;
	}

	public void setS2CoolDown(int new_S2CoolDown) {
		s2cooldown = new_S2CoolDown;
	}

	public int getS3CoolDown() {
		return s3cooldown;
	}

	public void setS3CoolDown(int new_S3CoolDown) {
		s3cooldown = new_S3CoolDown;
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

	public void setData(String nickname, String name, int newHealth, int newAttack, int newDefense,
						int newWidth, int newHeight, int newXPos, int newYPos,
						int numTargets, int newSkill1, int newS1CoolDown,
						int newSkill2, int newS2CoolDown, int newSkill3, int newS3CoolDown) {
		Monster src = res.getMonster(name);
		src.setNickname(nickname);
		src.setHealth(newHealth);
		src.setAttack(newAttack);
		src.setDefense(newDefense);
		src.setWidth(newWidth);
		src.setHeight((newHeight));
		src.setXPos(newXPos);
		src.setyPos(newYPos);
		src.setAutoattackTimer(numTargets);
		src.setSkill1(newSkill1);
		src.setSkill2(newSkill2);
		src.setSkill3(newSkill3);
		src.setS1CoolDown(newS1CoolDown);
		src.setS2CoolDown(newS2CoolDown);
		src.setS3CoolDown(newS3CoolDown);
	}

	public static class MonsterFactory {

		private String nickname = "stub";
		private String textureName = "stub";
		private int health, attack, defense, textureWidth, textureHeight, x, y, numTargets;
		private int skill1, skill1CD, skill2, skill2CD, skill3, skill3CD;

		public MonsterFactory() {
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

		public MonsterFactory textureHeight(int textureWidth) {
			this.textureWidth = textureWidth;
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


	}
}
