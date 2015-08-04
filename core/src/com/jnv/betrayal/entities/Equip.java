/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

public class Equip extends Item {

    final int maxAmount = 1;

    public Equip(int id, String name) {
        super(id, name);
    }

    public Equip(String name, int amount) {
        super(name, amount);
    }
}
