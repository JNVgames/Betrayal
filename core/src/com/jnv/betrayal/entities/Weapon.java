/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Weapon extends Equip {

    public Weapon(int id, String name, BetrayalAssetManager res) {
        super(id, name, res);
    }

    public Weapon(String name) {
        super(name);
    }

}
