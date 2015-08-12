/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.utilities;

import com.jnv.betrayal.entities.BodyArmor;
import com.jnv.betrayal.entities.Item;
import com.jnv.betrayal.entities.Ring;
import com.jnv.betrayal.entities.Shield;
import com.jnv.betrayal.entities.Weapon;

public class ItemLoader {

    public static void loadAll() {
        for (int i = 1; i <= 6; i++) {
            new Weapon(i, "sword1" + i);
        }

        /*
        for (int i = 1; i <= 6; i++) {
            new Shield(i, "sword" + i);
        }
        for (int i = 1; i <= 6; i++) {
            new Ring(i, "sword" + i);
        }
        for (int i = 1; i <= 6; i++) {
            new Item(i, "sword" + i);
        }
        */
        for (int i = 1; i <= 6; i++) {
            new Weapon(i + 6, "sword2" + i);
        }

        new BodyArmor(0, "char-armor-peasant");

    }

}
