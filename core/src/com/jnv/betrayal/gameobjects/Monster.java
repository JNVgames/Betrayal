package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.Dimensions;

public class Monster {

    protected Texture monsterTexture;
    protected String MonsterName;
    protected int id, health, attack, defense, width, height, xPos, yPos, normalAttackTimer;
    protected int skill1, s1cooldown, skill2, s2cooldown, skill3, s3cooldown;
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
        width = src.getWidth();
        height = src.getHeight();
        xPos = src.getxPos();
        yPos = src.getyPos();
        normalAttackTimer = src.getNormalAttackTimer();
        skill1 = src.getSkill1();
        skill2 = src.getSkill2();
        skill3 = src.getSkill3();
        s1cooldown = src.getS1CoolDown();
        s2cooldown = src.getS2CoolDown();
        s3cooldown = src.getS3CoolDown();
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

    public void setAutoattackTimer(int new_normalAattackTimer) {
        normalAttackTimer = new_normalAattackTimer;
    }

    public void setSkill1(int new_skill1) {
        skill1 = new_skill1;
    }

    public void setSkill2(int new_skill2) {
        skill2 = new_skill2;
    }

    public void setSkill3(int new_skill3) {
        skill3 = new_skill3;
    }

    public void setS1CoolDown(int new_S1CoolDown) {
        s1cooldown = new_S1CoolDown;
    }

    public void setS2CoolDown(int new_S2CoolDown) {
        s2cooldown = new_S2CoolDown;
    }

    public void setS3CoolDown(int new_S3CoolDown) {
        s3cooldown = new_S3CoolDown;
    }

    public void setWidth(int new_Width) {
        width = new_Width;
    }

    public void setHeight(int new_Height) {
        height = new_Height;
    }

    public void setXPos(int new_xPos) {
        xPos = new_xPos;
    }

    public void setyPos(int new_yPos) {
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

    public int getNormalAttackTimer() {
        return normalAttackTimer;
    }

    public int getSkill1() {
        return skill1;
    }

    public int getSkill2() {
        return skill2;
    }

    public int getSkill3() {
        return skill3;
    }

    public int getS1CoolDown() {
        return s1cooldown;
    }

    public int getS2CoolDown() {
        return s2cooldown;
    }

    public int getS3CoolDown() {
        return s3cooldown;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Texture getMonsterTexture() {
        return monsterTexture;
    }

    public void setData(String name, int newHealth, int newAttack, int newDefense,
                        int newWidth, int newHeight, int newXPos, int newYPos,
                        int newNormalAttackTimer, int newSkill1, int newS1CoolDown,
                        int newSkill2, int newS2CoolDown, int newSkill3, int newS3CoolDown) {
        Monster src = res.getMonster(name);
        src.setHealth(newHealth);
        src.setAttack(newAttack);
        src.setDefense(newDefense);
        src.setWidth(newWidth);
        src.setHeight((newHeight));
        src.setXPos(newXPos);
        src.setyPos(newYPos);
        src.setAutoattackTimer(newNormalAttackTimer);
        src.setSkill1(newSkill1);
        src.setSkill2(newSkill2);
        src.setSkill3(newSkill3);
        src.setS1CoolDown(newS1CoolDown);
        src.setS2CoolDown(newS2CoolDown);
        src.setS3CoolDown(newS3CoolDown);
    }
}
