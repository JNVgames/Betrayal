package com.jnv.betrayal.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.main.Betrayal;

//WE"RE GONNA keep this bAby ORGANIZED MOTEHr FKErS
public class TextureLoader {

    public static void loadAll() {
        // About
        Betrayal.res.loadTexture("about/jnv720x1280.png", "splash");
        Betrayal.res.loadTexture("about/start_screen720x1280.png", "start-background");
        Betrayal.res.loadTexture("about/hall_of_fame720x2000.png", "hall-of-fame-background");
        Betrayal.res.loadTexture("about/instructions_background720x1280.png", "instructions-background");

        // Buttons
        Betrayal.res.loadTexture("button/load_game_button512x144.png", "load-game");
        Betrayal.res.loadTexture("button/new_game_button512x144.png", "new-game");
        Betrayal.res.loadTexture("button/options_button512x144.png", "options");
        Betrayal.res.loadTexture("button/instructions512x144.png", "instructions");
        Betrayal.res.loadTexture("button/hall_of_fame512x144.png", "hall-of-fame");



        Betrayal.res.loadTexture("button/arrow256x256.png", "arrow");
        Betrayal.res.loadTexture("button/circle_x150x150.png", "x");
        Betrayal.res.loadTexture("button/buy360x144.png", "buy");
        Betrayal.res.loadTexture("button/cancel360x144.png", "cancel");
        Betrayal.res.loadTexture("button/color360x144.png", "color");
        Betrayal.res.loadTexture("button/equip360x144.png", "equip");
        Betrayal.res.loadTexture("button/sell360x144.png", "sell");
        Betrayal.res.loadTexture("button/preview360x144.png", "preview");

        // Weapons

        // Armor
        Betrayal.res.loadTexture("armor/black_armor.png", "armor-black");
        Betrayal.res.loadTexture("armor/black_cloak.png", "armor-cloak-black");
        Betrayal.res.loadTexture("armor/black_helmet.png", "helmet-black");
        Betrayal.res.loadTexture("armor/black_hero.png", "armor-hero-black");
        Betrayal.res.loadTexture("armor/blue_armor.png", "armor-blue");
        Betrayal.res.loadTexture("armor/blue_helmet.png", "helmet-blue");
        Betrayal.res.loadTexture("armor/blue_hero.png", "armor-blue-hero");
        Betrayal.res.loadTexture("armor/brown_cloak.png", "armor-cloak-brown");
        Betrayal.res.loadTexture("armor/gold_helmet.png", "helmet-gold");
        Betrayal.res.loadTexture("armor/gold_hero.png", "armor-hero-gold");
        Betrayal.res.loadTexture("armor/green_armor.png", "armor-green");
        Betrayal.res.loadTexture("armor/green_helmet.png", "helmet-green");
        Betrayal.res.loadTexture("armor/green_hero.png", "armor-hero-green");
        Betrayal.res.loadTexture("armor/normal_armor.png", "armor-normal");
        Betrayal.res.loadTexture("armor/normal_helmet.png", "helmet-normal");
        Betrayal.res.loadTexture("armor/purple_armor.png", "armor-purple");
        Betrayal.res.loadTexture("armor/purple_helmet.png", "helmet-purple");
        Betrayal.res.loadTexture("armor/purple_hero.png", "armor-hero-purple");
        Betrayal.res.loadTexture("armor/red_armor.png", "armor-red");
        Betrayal.res.loadTexture("armor/red_helmet.png", "helmet-red");
        Betrayal.res.loadTexture("armor/red_hero.png", "armor-hero-red");
        Betrayal.res.loadTexture("armor/silver_armor.png", "armor-silver");

        // Icon
        Betrayal.res.loadTexture("icon/attack250x250.png", "attack");
        Betrayal.res.loadTexture("icon/defense250x250.png", "defense");
        Betrayal.res.loadTexture("icon/health250x250.png", "health");
        Betrayal.res.loadTexture("icon/heal250x250.png", "heal");
        Betrayal.res.loadTexture("icon/damage250x250.png", "damage");
        Betrayal.res.loadTexture("icon/block250x250.png", "block");

        // Items

        // Monsters

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

        // Rings
        Betrayal.res.loadTexture("rings/ring1a.png", "ring-1-red");
        Betrayal.res.loadTexture("rings/ring1b.png", "ring-1-black");
        Betrayal.res.loadTexture("rings/ring1c.png", "ring-1-blue");
        Betrayal.res.loadTexture("rings/ring1d.png", "ring-1-green");
        Betrayal.res.loadTexture("rings/ring1e.png", "ring-1-purple");
        Betrayal.res.loadTexture("rings/ring2a.png", "ring-2-red");
        Betrayal.res.loadTexture("rings/ring2b.png", "ring-2-black");
        Betrayal.res.loadTexture("rings/ring2c.png", "ring-2-blue");
        Betrayal.res.loadTexture("rings/ring2d.png", "ring-2-green");
        Betrayal.res.loadTexture("rings/ring2e.png", "ring-2-purple");
        Betrayal.res.loadTexture("rings/ring3.png", "ring-3");

        // Dungeon Background
        for (int i = 1; i <= 5; i++) {
            Betrayal.res.loadTexture("map/dungeon_map" + i + ".png", "map" + i);
        }
    }
}
