/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.main.Betrayal;


public class TextureLoader {

    public static void loadAll() {
        // About
        Betrayal.res.loadTexture("about/jnv720x1280.png", "splash");
        Betrayal.res.loadTexture("about/start_screen720x1280.png", "start-background");
        Betrayal.res.loadTexture("about/hall_of_fame720x2000.png", "hall-of-fame-background");
        Betrayal.res.loadTexture("about/instructions_background720x1280.png", "instructions-background");
        Betrayal.res.loadTexture("about/shop_background720x1280.png", "shop-background");
        Betrayal.res.loadTexture("about/game_over720x1280.png", "game-over-background");

        // Buttons
        Betrayal.res.loadTexture("button/load_game_button512x144.png", "load-game");
        Betrayal.res.loadTexture("button/new_game_button512x144.png", "new-game");
        Betrayal.res.loadTexture("button/options_button512x144.png", "options");
        Betrayal.res.loadTexture("button/instructions512x144.png", "instructions");
        Betrayal.res.loadTexture("button/hall_of_fame512x144.png", "hall-of-fame");
        Betrayal.res.loadTexture("button/play_now512x144.png", "play-now");
        Betrayal.res.loadTexture("button/play_now_pressed512x144.png", "play-now-pressed");
        Betrayal.res.loadTexture("button/main_menu512x144.png", "main-menu");
        Betrayal.res.loadTexture("button/back_to_lobby512x144.png", "back-to-lobby");

        Betrayal.res.loadTexture("button/arrow256x256.png", "arrow");
        Betrayal.res.loadTexture("button/circle_x150x150.png", "x");
        Betrayal.res.loadTexture("button/buy360x144.png", "buy");
        Betrayal.res.loadTexture("button/cancel360x144.png", "cancel");
        Betrayal.res.loadTexture("button/color360x144.png", "color");
        Betrayal.res.loadTexture("button/equip360x144.png", "equip");
        Betrayal.res.loadTexture("button/sell360x144.png", "sell");
        Betrayal.res.loadTexture("button/preview360x144.png", "preview");

        Betrayal.res.loadTexture("button/lobby/floor.png", "lobby-floor");
        Betrayal.res.loadTexture("button/lobby/inventory.png", "lobby-inventory");
        Betrayal.res.loadTexture("button/lobby/party.png", "lobby-party");
        Betrayal.res.loadTexture("button/lobby/settings.png", "lobby-settings");
        Betrayal.res.loadTexture("button/lobby/shop.png", "lobby-shop");
        Betrayal.res.loadTexture("button/lobby/stats.png", "lobby-stats");

        Betrayal.res.loadTexture("button/shop/armor.png", "shop-armor");
        Betrayal.res.loadTexture("button/shop/armor_pressed.png", "shop-armor-pressed");
        Betrayal.res.loadTexture("button/shop/extra.png", "shop-extra");
        Betrayal.res.loadTexture("button/shop/Extra_pressed.png", "shop-extra-pressed");
        Betrayal.res.loadTexture("button/shop/item.png", "shop-item");
        Betrayal.res.loadTexture("button/shop/item_pressed.png", "shop-item-pressed");
        Betrayal.res.loadTexture("button/shop/weapons.png", "shop-weapons");
        Betrayal.res.loadTexture("button/shop/weapons_pressed.png", "shop-weapons-pressed");
        Betrayal.res.loadTexture("button/shop/money.png", "shop-money");
        Betrayal.res.loadTexture("button/shop/money_pressed.png", "shop-money-pressed");

        // Icon
        Betrayal.res.loadTexture("icon/attack250x250.png", "attack");
        Betrayal.res.loadTexture("icon/defense250x250.png", "defense");
        Betrayal.res.loadTexture("icon/health250x250.png", "health");
        Betrayal.res.loadTexture("icon/heal250x250.png", "heal");
        Betrayal.res.loadTexture("icon/damage250x250.png", "damage");
        Betrayal.res.loadTexture("icon/block250x250.png", "block");

        // Items
        // Swords Shields Helemt
        //Sword
        Betrayal.res.loadTexture("item/sword1.png", "sword1");
        Betrayal.res.loadTexture("item/sword2.png", "sword2");
        Betrayal.res.loadTexture("item/sword3.png", "sword3");
        Betrayal.res.loadTexture("item/sword4.png", "sword4");
        Betrayal.res.loadTexture("item/sword5.png", "sword5");
        Betrayal.res.loadTexture("item/sword6.png", "sword6");

        for (int i = 0; i < 6; i++) {
            Betrayal.res.loadItem(i + 1, "sword" + (i + 1));
        }

        //Shield
        Betrayal.res.loadTexture("item/shield1.png", "shield1");
        Betrayal.res.loadTexture("item/shield2.png", "shield2");
        Betrayal.res.loadTexture("item/shield3.png", "shield3");
        Betrayal.res.loadTexture("item/shield4.png", "shield4");
        Betrayal.res.loadTexture("item/shield5.png", "shield5");
        Betrayal.res.loadTexture("item/shield6.png", "shield6");

        for (int i = 0; i < 6; i++) {
            Betrayal.res.loadItem(i + 7, "shield" + (i + 1));
        }

        //Headgear
        Betrayal.res.loadTexture("item/helmet1.png", "headgear1");
        Betrayal.res.loadTexture("item/helmet2.png", "headgear2");
        Betrayal.res.loadTexture("item/helmet3.png", "headgear3");
        Betrayal.res.loadTexture("item/helmet4.png", "headgear4");
        Betrayal.res.loadTexture("item/helmet5.png", "headgear5");
        Betrayal.res.loadTexture("item/helmet6.png", "headgear6");


        // Potions
        Betrayal.res.loadTexture("item/potion1.png", "potion1");
        Betrayal.res.loadTexture("item/potion2.png", "potion2");
        Betrayal.res.loadTexture("item/potion3.png", "potion3");
        Betrayal.res.loadTexture("item/potion4.png", "potion4");
        Betrayal.res.loadTexture("item/potion5.png", "potion5");
        Betrayal.res.loadTexture("item/potion6.png", "potion6");
        Betrayal.res.loadTexture("item/potion7.png", "potion7");
        Betrayal.res.loadTexture("item/potion8.png", "potion8");
        Betrayal.res.loadTexture("item/potion9.png", "potion9");
        Betrayal.res.loadTexture("item/potion10.png", "potion10");
        Betrayal.res.loadTexture("item/potion11.png", "potion11");
        Betrayal.res.loadTexture("item/potion12.png", "potion12");

        // Rings
        Betrayal.res.loadTexture("rings/ring1a.png", "ring1-1");
        Betrayal.res.loadTexture("rings/ring1b.png", "ring1-2");
        Betrayal.res.loadTexture("rings/ring1c.png", "ring1-4");
        Betrayal.res.loadTexture("rings/ring1d.png", "ring1-5");
        Betrayal.res.loadTexture("rings/ring1e.png", "ring1-6");
        Betrayal.res.loadTexture("rings/ring1f.png", "ring1-3");
        Betrayal.res.loadTexture("rings/ring2a.png", "ring2-1");
        Betrayal.res.loadTexture("rings/ring2b.png", "ring2-2");
        Betrayal.res.loadTexture("rings/ring2c.png", "ring2-4");
        Betrayal.res.loadTexture("rings/ring2d.png", "ring2-5");
        Betrayal.res.loadTexture("rings/ring2e.png", "ring2-6");
        Betrayal.res.loadTexture("rings/ring2f.png", "ring2-3");
        Betrayal.res.loadTexture("rings/ring3.png", "ring3");
        // Armor
        Betrayal.res.loadTexture("armor/red_armor.png", "armor1-1");
        Betrayal.res.loadTexture("armor/black_armor.png", "armor1-2");
        Betrayal.res.loadTexture("armor/gold_armor.png", "armor1-3");
        Betrayal.res.loadTexture("armor/blue_armor.png", "armor1-4");
        Betrayal.res.loadTexture("armor/green_armor.png", "armor1-5");
        Betrayal.res.loadTexture("armor/purple_armor.png", "armor1-6");

        Betrayal.res.loadTexture("armor/red_hero.png", "armor2-1");
        Betrayal.res.loadTexture("armor/black_hero.png", "armor2-2");
        Betrayal.res.loadTexture("armor/gold_hero.png", "armor2-3");
        Betrayal.res.loadTexture("armor/blue_hero.png", "armor2-4");
        Betrayal.res.loadTexture("armor/green_hero.png", "armor2-5");
        Betrayal.res.loadTexture("armor/purple_hero.png", "armor2-6");

        Betrayal.res.loadTexture("armor/brown_cloak.png", "armor-cloak-brown");
        Betrayal.res.loadTexture("armor/black_cloak.png", "armor-cloak-black");
        // Heads
        /** Format for loading: hair-gender-hairStyle-hairColor-all */
        for (int i = 1; i <= 5; i++) {
            // Male heads
            Betrayal.res.loadTexture("character/head" + i + "a.png", "hair-male-" + i + "-1-all");
            Betrayal.res.loadTexture("character/head" + i + "b.png", "hair-male-" + i + "-2-all");
            Betrayal.res.loadTexture("character/head" + i + "c.png", "hair-male-" + i + "-3-all");
            Betrayal.res.loadTexture("character/head" + i + "d.png", "hair-male-" + i + "-4-all");
            Betrayal.res.loadTexture("character/head" + i + "e.png", "hair-male-" + i + "-5-all");
            Betrayal.res.loadTexture("character/head" + i + "f.png", "hair-male-" + i + "-6-all");
            Betrayal.res.loadTexture("character/head" + i + "g.png", "hair-male-" + i + "-7-all");
        }
        for (int i = 6; i <= 9; i++) {
            // Female heads
            Betrayal.res.loadTexture("character/head" + i + "a.png",
                    "hair-female-" + (i - 5) + "-1-all");
            Betrayal.res.loadTexture("character/head" + i + "b.png",
                    "hair-female-" + (i - 5) + "-2-all");
            Betrayal.res.loadTexture("character/head" + i + "c.png",
                    "hair-female-" + (i - 5) + "-3-all");
            Betrayal.res.loadTexture("character/head" + i + "d.png",
                    "hair-female-" + (i - 5) + "-4-all");
            Betrayal.res.loadTexture("character/head" + i + "e.png",
                    "hair-female-" + (i - 5) + "-5-all");
            Betrayal.res.loadTexture("character/head" + i + "f.png",
                    "hair-female-" + (i - 5) + "-6-all");
            Betrayal.res.loadTexture("character/head" + i + "g.png",
                    "hair-female-" + (i - 5) + "-7-all");
        }

        // Character headgear
        Betrayal.res.loadTexture("character/headgear1a.png", "char-headgear-normal");
        Betrayal.res.loadTexture("character/headgear1b.png", "char-headgear-red");
        Betrayal.res.loadTexture("character/headgear1c.png", "char-headgear-black");
        Betrayal.res.loadTexture("character/headgear1d.png", "char-headgear-gold");
        Betrayal.res.loadTexture("character/headgear1e.png", "char-headgear-blue");
        Betrayal.res.loadTexture("character/headgear1f.png", "char-headgear-green");
        Betrayal.res.loadTexture("character/headgear1g.png", "char-headgear-purple");

        // Character armor
        Betrayal.res.loadTexture("character/armor0.png", "char-armor-peasant");
        Betrayal.res.loadTexture("character/armor1a.png", "char-armor-grey");
        Betrayal.res.loadTexture("character/armor1b.png", "char-armor-red");
        Betrayal.res.loadTexture("character/armor1c.png", "char-armor-black");
        Betrayal.res.loadTexture("character/armor1d.png", "char-armor-blue");
        Betrayal.res.loadTexture("character/armor1e.png", "char-armor-green");
        Betrayal.res.loadTexture("character/armor1f.png", "char-armor-pruple");
        Betrayal.res.loadTexture("character/armor2a.png", "char-armor-red");
        Betrayal.res.loadTexture("character/armor2b.png", "char-armor-black");
        Betrayal.res.loadTexture("character/armor2c.png", "char-armor-gold");
        Betrayal.res.loadTexture("character/armor2d.png", "char-armor-blue");
        Betrayal.res.loadTexture("character/armor2e.png", "char-armor-green");
        Betrayal.res.loadTexture("character/armor2f.png", "char-armor-purple");
        Betrayal.res.loadTexture("character/cloak1a.png", "char-cloak-brown");
        Betrayal.res.loadTexture("character/cloak1b.png", "char-cloak-black");

        // Shields
        Betrayal.res.loadTexture("shields/shield1a.png", "shield-1-red");
        Betrayal.res.loadTexture("shields/shield1b.png", "shield-1-black");
        Betrayal.res.loadTexture("shields/shield1c.png", "shield-1-gold");
        Betrayal.res.loadTexture("shields/shield1d.png", "shield-1-blue");
        Betrayal.res.loadTexture("shields/shield1e.png", "shield-1-green");
        Betrayal.res.loadTexture("shields/shield1f.png", "shield-1-purple");
        Betrayal.res.loadTexture("shields/shield2a.png", "shield-2-red");
        Betrayal.res.loadTexture("shields/shield2b.png", "shield-2-black");
        Betrayal.res.loadTexture("shields/shield2c.png", "shield-2-gold");
        Betrayal.res.loadTexture("shields/shield2d.png", "shield-2-blue");
        Betrayal.res.loadTexture("shields/shield2e.png", "shield-2-green");
        Betrayal.res.loadTexture("shields/shield2f.png", "shield-2-purple");

        // Monster

        // Tier 1
        Betrayal.res.loadTexture("monster/monster1.png", "monster-tier1-0");
        Betrayal.res.loadTexture("monster/monster2.png", "monster-tier1-1");
        Betrayal.res.loadTexture("monster/monster3.png", "monster-tier1-2");
        Betrayal.res.loadTexture("monster/monster4.png", "monster-tier1-3");
        Betrayal.res.loadTexture("monster/monster5.png", "monster-tier1-4");
        Betrayal.res.loadTexture("monster/monster43.png", "monster-tier1-5");
        Betrayal.res.loadTexture("monster/monster44.png", "monster-tier1-6");
        Betrayal.res.loadTexture("monster/monster45.png", "monster-tier1-7");
        Betrayal.res.loadTexture("monster/monster46.png", "monster-tier1-8");
        Betrayal.res.loadTexture("monster/monster47.png", "monster-tier1-9");

        //Tier 2
        Betrayal.res.loadTexture("monster/monster6.png", "monster-tier2-0");
        Betrayal.res.loadTexture("monster/monster7.png", "monster-tier2-1");
        Betrayal.res.loadTexture("monster/monster8.png", "monster-tier2-2");
        Betrayal.res.loadTexture("monster/monster9.png", "monster-tier2-3");
        Betrayal.res.loadTexture("monster/monster11.png", "monster-tier2-4");
        Betrayal.res.loadTexture("monster/monster12.png", "monster-tier2-5");
        Betrayal.res.loadTexture("monster/monster17.png", "monster-tier2-6");
        Betrayal.res.loadTexture("monster/monster18.png", "monster-tier2-7");
        Betrayal.res.loadTexture("monster/monster25.png", "monster-tier2-8");
        Betrayal.res.loadTexture("monster/monster14.png", "monster-tier2-9");

        //Tier 3
        Betrayal.res.loadTexture("monster/monster15.png", "monster-tier3-0");
        Betrayal.res.loadTexture("monster/monster19.png", "monster-tier3-1");
        Betrayal.res.loadTexture("monster/monster26.png", "monster-tier3-2");
        Betrayal.res.loadTexture("monster/monster27.png", "monster-tier3-3");
        Betrayal.res.loadTexture("monster/monster38.png", "monster-tier3-4");
        Betrayal.res.loadTexture("monster/monster24.png", "monster-tier3-5");
        Betrayal.res.loadTexture("monster/monster36.png", "monster-tier3-6");

        //Tier 4
        Betrayal.res.loadTexture("monster/monster10.png", "monster-tier4-0");
        Betrayal.res.loadTexture("monster/monster13.png", "monster-tier4-1");
        Betrayal.res.loadTexture("monster/monster28.png", "monster-tier4-2");
        Betrayal.res.loadTexture("monster/monster29.png", "monster-tier4-3");
        Betrayal.res.loadTexture("monster/monster37.png", "monster-tier4-4");
        //Tier4 mob
        Betrayal.res.loadTexture("monster/monster20.png", "monster-tier4-5");
        Betrayal.res.loadTexture("monster/monster21.png", "monster-tier4-6");
        Betrayal.res.loadTexture("monster/monster22.png", "monster-tier4-7");
        Betrayal.res.loadTexture("monster/monster23.png", "monster-tier4-8");
        //Tier 4 mob
        Betrayal.res.loadTexture("monster/monster30.png", "monster-tier4-9");
        Betrayal.res.loadTexture("monster/monster31.png", "monster-tier4-10");
        Betrayal.res.loadTexture("monster/monster32.png", "monster-tier4-11");
        Betrayal.res.loadTexture("monster/monster33.png", "monster-tier4-12");
        Betrayal.res.loadTexture("monster/monster34.png", "monster-tier4-13");
        Betrayal.res.loadTexture("monster/monster35.png", "monster-tier4-14");

        //Tier 5
        Betrayal.res.loadTexture("monster/monster39.png", "monster-tier5-0");
        Betrayal.res.loadTexture("monster/monster40.png", "monster-tier5-1");
        Betrayal.res.loadTexture("monster/monster41.png", "monster-tier5-2");
        Betrayal.res.loadTexture("monster/monster48.png", "monster-tier5-3");
        Betrayal.res.loadTexture("monster/monster49.png", "monster-tier5-4");
        Betrayal.res.loadTexture("monster/monster50.png", "monster-tier5-5");

        //Other
        Betrayal.res.loadTexture("monster/monster16.png", "monster-tier0-0");
        // Dungeon Background
        for (int i = 1; i <= 5; i++) {
            Betrayal.res.loadTexture("map/dungeon_map" + i + ".png", "map-" + i);
        }

        // Monsters
        for (int i = 1; i <= 50; i++) {
            Betrayal.res.loadTexture("monster/monster" + i + ".png", "monster-" + i);
        }
    }
}
