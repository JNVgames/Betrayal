/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.entities.Item;
import com.jnv.betrayal.entities.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Resources {

    private HashMap<String, Texture> textures;
    private HashMap<String, Item> items;
    private HashMap<String, Monster> monsters;

    public Resources() {
        textures = new HashMap<String, Texture>();
        items = new HashMap<String, Item>();
        monsters = new HashMap<String, Monster>();
    }

    public void loadTexture(String path, String key) {
        Texture t = new Texture(Gdx.files.internal(path));
        textures.put(key, t);
    }
    public void loadItem(String name, Item item) {
        items.put(name, item);
    }
    public void loadMonster(String name, Monster monster) {
        monsters.put(name, monster);
    }

    // Getters
    public Texture getTexture(String key) {
        return textures.get(key);
    }
    public Item getItem(String name) {
        return items.get(name);
    }
    public Monster getMonster(String name) {
        return monsters.get(name);
    }

    // Setters
    public void removeAll() {
        List<String> keys = new ArrayList<String>(textures.keySet());
        for (String key : keys) {
            textures.remove(key);
        }
        keys = new ArrayList<String>(items.keySet());
        for (String key : keys) {
            items.remove(key);
        }
        keys = new ArrayList<String>(monsters.keySet());
        for (String key : keys) {
            monsters.remove(key);
        }
    }

}
