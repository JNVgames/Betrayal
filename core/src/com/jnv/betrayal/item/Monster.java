package com.jnv.betrayal.item;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

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
}
