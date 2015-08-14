package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.main.Betrayal;

import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by jphan on 8/14/2015.
 */
public class Monster {


    protected static HashMap<String, Monster> allMonsters = new HashMap<String, Monster>();

    protected Texture itemImage;
    protected String MonsterName;
    protected int id, health, attack, defense;

    public Monster(int id, String name) {
        this.id = id;
        MonsterName = name;
        itemImage = Betrayal.res.getTexture(name);
        allMonsters.put(name, this);
    }
    public Monster(String name) {
        Monster src = allMonsters.get(name);
        id = src.getID();
        MonsterName = name;
        health = src.getHealth();
        attack = src.getAttack();
        defense = src.getDefense();
        itemImage = src.getItemImage();
    }

    // Getters
    public int getID() { return id; }
    public String getName() { return MonsterName; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public Texture getItemImage() {
        return itemImage;
    }

    // Setters
    public void setName(String name) { MonsterName = name; }
    public static void setData(String name, int newHealth, int newAttack, int newDefense) {
        Monster src = allMonsters.get(name);
        src.setHealth(newHealth);
        src.setAttack(newAttack);
        src.setDefense(newDefense);
    }
    public void setHealth(int new_health) { health = new_health;}
    public void setAttack(int new_attack) { attack = new_attack; }
    public void setDefense(int new_defense) { defense=new_defense; }

    public static void loadMonsters (){
        // Tier1
        Monster monster = new Monster(1,"monster-tier1-0");
            monster.setData("monster-tier1-0",25,10,1);
        monster = new Monster(1,"monster-tier1-1");
            monster.setData("monster-tier-1",25,10,1);
        monster = new Monster(1,"monster-tier1-2");
            monster.setData("monster-tier-1-2",25,10,1);
        monster = new Monster(1,"monster-tier1-3");
            monster.setData("monster-tier-1-3",25,10,1);
        monster = new Monster(1,"monster-tier1-4");
            monster.setData("monster-tier-1-4",25,10,1);
        monster = new Monster(1,"monster-tier1-5");
            monster.setData("monster-tier-1-5",25,10,1);
        monster = new Monster(1,"monster-tier1-6");
            monster.setData("monster-tier-1-6",25,10,1);
        monster = new Monster(1,"monster-tier1-7");
            monster.setData("monster-tier-1-7",25,10,1);
        monster = new Monster(1,"monster-tier1-8");
            monster.setData("monster-tier-1-8",25,10,1);
        monster = new Monster(1,"monster-tier1-9");
            monster.setData("monster-tier-1-9",25,10,1);
        monster = new Monster(1,"monster-tier1-10");
            monster.setData("monster-tier-1-10",25,10,1);
        monster = new Monster(1,"monster-tier1-11");
            monster.setData("monster-tier-1-11",25,10,1);
        monster = new Monster(1,"monster-tier1-11");

        // Tier 2
    }
}
