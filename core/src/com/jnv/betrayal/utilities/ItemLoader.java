/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.utilities;

import com.jnv.betrayal.entities.Weapon;

public class ItemLoader {

    public static void loadAll() {
        for (int i = 1; i <= 6; i++) {
            new Weapon(i, "sword" + i);
        }

        /* VINCENT U BETCH
        for (int i = 1; i <= 6; i++) {
            new Shield(i, "sword" + i);
        }
        for (int i = 1; i <= 6; i++) {
            new Ring(i, "sword" + i);
        }
        for (int i = 1; i <= 6; i++) {
            new Item(i, "sword" + i);
        }
        for (int i = 1; i <= 6; i++) {
            new Weapon(i, "sword" + i);
        }
        for (int i = 1; i <= 6; i++) {
            new Weapon(i, "sword" + i);
        }
        */

    }


}
