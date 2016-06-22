/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public abstract class Usables extends Item {

    protected Usables(int id, String name, BetrayalAssetManager res, int cost_buy, String description) {
        super(id, name, res, cost_buy, description);
        this.res = res;
    }

    public Usables(String name, BetrayalAssetManager res) {
        super(name, res);
    }
}
