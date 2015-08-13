/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.main.Betrayal;

public abstract class Equip extends Item {

    private Texture weapon_preview;

    protected Equip(int id, String name) {
        super(id, name);
        weapon_preview = Betrayal.res.getTexture("char-" + name);
    }

    public Equip(String name) {
        super(name);
        Equip src = (Equip) allItems.get(name);
        weapon_preview = src.getPreview();
    }

    // Getters
    public Texture getPreview() {
        return weapon_preview;
    }
}
