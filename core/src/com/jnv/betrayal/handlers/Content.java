/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.entities.Item;

import java.util.HashMap;

public class Content {

    private HashMap<String, Texture> textures;
    private HashMap<String, Item> items;

    public Content() {
        textures = new HashMap<String, Texture>();
        items = new HashMap<String, Item>();
    }

    public void loadTexture(String path, String key) {
        Texture t = new Texture(Gdx.files.internal(path));
        textures.put(key, t);
    }
    public void loadItem(int id, String key) {
        Item i = new Item(id, key);
        items.put(key, i);
    }

    // Getters
    public Texture getTexture(String key) {
        return textures.get(key);
    }
    public Item getItem(String key) {
        return items.get(key);
    }

}
