package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.Dimensions;

public class Monster {

	protected Texture monsterTexture;
	protected String MonsterName;
	protected int id, health, attack, defense, width, height, xPos, yPos, normalAttackTimer, skill1, skill2, skill3;
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

	public void setHealth(int new_health) {
		health = new_health;
	}

	public void setDefense(int new_defense) {
		defense = new_defense;
	}

	public void setAttack(int new_attack) {
		attack = new_attack;
	}

	public void setAutoattackTimer(int new_normalAattackTimer){
		new_normalAattackTimer = normalAttackTimer;
	}
	public void setSkill1(int new_skill1){
		skill1 = new_skill1;
	}

	public void setSkill2(int new_skill2){
		skill2 = new_skill2;
	}
	public void setWidth(int new_Width) {
		width = new_Width;
	}
	public void setHeight(int new_Height){
		height = new_Height;
	}
	public void setXPos(int new_xPos){
		xPos = new_xPos;
	}
	public void setyPos(int new_yPos){
		yPos = new_yPos;
	}

	// Getters
	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getSkill1(){
		return skill1;
	}

	public int getSkill2(){
		return skill2;
	}

	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public int getxPos() {return xPos;}
	public int getyPos() {return yPos;}

	public Texture getMonsterTexture() {
		return monsterTexture;
	}

	public void setData(String name, int newHealth, int newAttack, int newDefense, int newWidth, int newHeight, int newXPos, int newYPos) {
		Monster src = res.getMonster(name);
		src.setHealth(newHealth);
		src.setAttack(newAttack);
		src.setDefense(newDefense);
		src.setWidth(newWidth);
		src.setHeight((newHeight));
		src.setXPos(newXPos);
		src.setyPos(newYPos);
	}
}
