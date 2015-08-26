/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.item;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Weapon extends Equip {

    public Weapon(int id, String name, BetrayalAssetManager res) {
        super(id, name, res);
    }

    public Weapon(String name, BetrayalAssetManager res) {
        super(name, res);
    }

}
