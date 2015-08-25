/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public abstract class Equip extends Item {

    private BetrayalAssetManager res;
    private Texture weapon_preview;

    protected Equip(int id, String name, BetrayalAssetManager res) {
        super(id, name, res);
        this.res = res;
        weapon_preview = res.getTexture("char-" + name);
    }

    public Equip(String name) {
        super(name);
        Equip src = (Equip) res.getItem(name);
        weapon_preview = src.getPreview();
    }

    // Getters
    public Texture getPreview() {
        return weapon_preview;
    }
}
