/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.main.Betrayal;

public class Weapon extends Equip {

    private Texture weapon_preview;

    public Weapon(int id, String name) {
        super(id, name);
        weapon_preview = Betrayal.res.getTexture("char-" + name);
    }

    public Weapon(String name) {
        super(name);
        Weapon src = (Weapon) allItems.get(name);
        weapon_preview = src.getPreview();
    }

    // Getters
    public Texture getPreview() {
        return weapon_preview;
    }
}
