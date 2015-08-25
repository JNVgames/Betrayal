package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;

import java.util.HashMap;

public class Monster {

    private BetrayalAssetManager res;

    protected Texture monsterTexture;
    protected String MonsterName;
    protected int id, health, attack, defense;

    public Monster(int id, String name, BetrayalAssetManager res) {
        this.id = id;
        MonsterName = name;
        this.res = res;
        monsterTexture = res.getTexture(name);
        res.loadMonster(name, this);
    }
    public Monster(String name) {
        Monster src = res.getMonster(name);
        id = src.getID();
        MonsterName = name;
        health = src.getHealth();
        attack = src.getAttack();
        defense = src.getDefense();
        monsterTexture = src.getMonsterTexture();
    }

    // Getters
    public int getID() { return id; }
    public String getName() { return MonsterName; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public Texture getMonsterTexture() {
        return monsterTexture;
    }

    // Setters
    public void setName(String name) { MonsterName = name; }
    public void setData(String name, int newHealth, int newAttack, int newDefense) {
        Monster src = res.getMonster(name);
        src.setHealth(newHealth);
        src.setAttack(newAttack);
        src.setDefense(newDefense);
    }
    public void setHealth(int new_health) { health = new_health;}
    public void setAttack(int new_attack) { attack = new_attack; }
    public void setDefense(int new_defense) { defense = new_defense; }

    /*
    public void loadMonsters() {
        // Tier1
        new Monster(1,"monster-tier1-0", res);
        Monster.setData("monster-tier1-0", 25, 10, 1);
        new Monster(1,"monster-tier1-1");
        Monster.setData("monster-tier1-1", 25, 10, 1);
        new Monster(1,"monster-tier1-2");
        Monster.setData("monster-tier1-2", 25, 10, 1);
        new Monster(1,"monster-tier1-3");
        Monster.setData("monster-tier1-3", 25, 10, 1);
        new Monster(1,"monster-tier1-4");
        Monster.setData("monster-tier1-4", 25, 10, 1);
        new Monster(1,"monster-tier1-5");
        Monster.setData("monster-tier1-5", 25, 10, 1);
        new Monster(1,"monster-tier1-6");
        Monster.setData("monster-tier1-6", 25, 10, 1);
        new Monster(1,"monster-tier1-7");
        Monster.setData("monster-tier1-7", 25, 10, 1);
        new Monster(1,"monster-tier1-8");
        Monster.setData("monster-tier1-8", 25, 10, 1);
        new Monster(1,"monster-tier1-9");
        Monster.setData("monster-tier1-9", 25, 10, 1);
        new Monster(1,"monster-tier1-10");
        Monster.setData("monster-tier1-10", 25, 10, 1);
        new Monster(1,"monster-tier1-11");
        Monster.setData("monster-tier1-11", 25, 10, 1);
        new Monster(1,"monster-tier1-11");

        // Tier 2
    }
    */
}
