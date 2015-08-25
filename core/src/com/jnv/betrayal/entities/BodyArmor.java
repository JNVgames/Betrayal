/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class BodyArmor extends Equip {

    public BodyArmor(int id, String name, BetrayalAssetManager res) {
        super(id, name, res);
    }
    public BodyArmor(String name) {
        super(name);
    }

}
