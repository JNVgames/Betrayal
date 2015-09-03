/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Potions extends Usables {

    private int attack;

    public void setAttack(int attack){this.attack = attack;	}

    public int  getAttack(){return attack;}
    public Potions(int id, String name, BetrayalAssetManager res) {
        super(id, name, res);
    }

    public Potions(String name, BetrayalAssetManager res) {
        super(name, res);
    }

}
