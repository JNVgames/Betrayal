/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;
import com.jnv.betrayal.entities.Item;
import com.jnv.betrayal.entities.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BetrayalAssetManager extends AssetManager {

    private ObjectMap<String, String> resourceNames = new ObjectMap<String, String>();
    private ObjectMap<String, Item> items = new ObjectMap<String, Item>();
    private ObjectMap<String, Monster> monsters = new ObjectMap<String, Monster>();

    public BetrayalAssetManager() {
        super();
    }

    // Asset Manager functions
    public void loadTexture(String fileName, String key) {
        resourceNames.put(key, fileName);
        load(fileName, Texture.class);
    }
    public Texture getTexture(String name) {
        return get(resourceNames.get(name), Texture.class);
    }
    public synchronized void dispose() {
        super.dispose();

        resourceNames.clear();

        ObjectMap.Keys<String> keys = items.keys();
        for (String key : keys) {
            items.remove(key);
        }
        keys = monsters.keys();
        for (String key : keys) {
            monsters.remove(key);
        }
    }

    // Resource functions
    public void loadItem(String name, Item item) {
        items.put(name, item);
    }
    public void loadMonster(String name, Monster monster) {
        monsters.put(name, monster);
    }

    // Resource getters
    public Item getItem(String name) {
        return items.get(name);
    }
    public Monster getMonster(String name) {
        return monsters.get(name);
    }


}
