package com.jnv.betrayal.utilities;

import com.jnv.betrayal.main.Betrayal;

//WE"RE GONNA keep this bAby ORGANIZED MOTEHr FKErS
public class TextureLoader {

    public static void loadAll() {
        // About
        Betrayal.res.loadTexture("about/jnv720x1280.png", "splash");
        Betrayal.res.loadTexture("about/start_screen720x1280.png", "start_background");

        // Buttons
        Betrayal.res.loadTexture("button/load_game_button512x144.png", "load game");
        Betrayal.res.loadTexture("button/new_game_button512x144.png", "new game");
        Betrayal.res.loadTexture("button/options_button512x144.png", "options");


        Betrayal.res.loadTexture("button/arrow256x256.png", "arrow");
        Betrayal.res.loadTexture("button/circle_x150x150.png", "circle x");
        //Betrayal.res.loadTexture("button/buy360x144.png", "buy");
        //Betrayal.res.loadTexture("button/cancel360x144.png", "cancel");
        //Betrayal.res.loadTexture("button/color360x144.png", "color");
        //Betrayal.res.loadTexture("button/equip360x144.png", "equip");
        //Betrayal.res.loadTexture("button/sell360x144.png", "sell");

        // Weapons

        Betrayal.res.loadTexture("", "");
        Betrayal.res.loadTexture("", "");
        Betrayal.res.loadTexture("", "");

        // Items
        //Betrayal.res.loadTexture("icon/attack250x250", "attack");
        //Betrayal.res.loadTexture("icon/defense250x250", "defense");
        //Betrayal.res.loadTexture("icon/health250x250", "health");

        //Betrayal.res.loadTexture("icon/heal250x250", "heal");
        //Betrayal.res.loadTexture("icon/damage250x250", "damage");
        //Betrayal.res.loadTexture("icon/block250x250", "block");

        // Icons

        // Monsters

        // Characters
        for (int a = 0; a <= 8; a++) {
            for (int b = 0; b <= 6; b++) {
                //Betrayal.res.loadTexture("character/" + a + b + "000000", a + b + "000000");
            }
        }
        // About
        Betrayal.res.loadTexture("about/jnv720x1280.png", "splash");
        Betrayal.res.loadTexture("about/start_screen720x1280.png", "start-background");

        // Buttons
        Betrayal.res.loadTexture("button/load_game_button512x144.png", "load-game");
        Betrayal.res.loadTexture("button/new_game_button512x144.png", "new-game");
        Betrayal.res.loadTexture("button/options_button512x144.png", "options");
        Betrayal.res.loadTexture("button/instructions512x144.png", "instructions");
        Betrayal.res.loadTexture("button/hall_of_fame512x144.png", "hall-of-fame");


        Betrayal.res.loadTexture("button/arrow256x256.png", "arrow");
        Betrayal.res.loadTexture("button/circle_x150x150.png", "circle x");
        Betrayal.res.loadTexture("button/buy360x144.png", "buy");
        Betrayal.res.loadTexture("button/cancel360x144.png", "cancel");
        Betrayal.res.loadTexture("button/color360x144.png", "color");
        Betrayal.res.loadTexture("button/equip360x144.png", "equip");
        Betrayal.res.loadTexture("button/sell360x144.png", "sell");
        Betrayal.res.loadTexture("button/preview360x144.png", "preview");

        // Weapons

        //armor
        Betrayal.res.loadTexture("armor/black_armor.png", "armor-black");
        Betrayal.res.loadTexture("armor/black_cloak.png", "armor-cloak-black");
        Betrayal.res.loadTexture("armor/black_helmet.png", "helmet-black");
        Betrayal.res.loadTexture("armor/black_hero.png", "armor-hero-black");
        Betrayal.res.loadTexture("armor/blue-armor.png", "armor-blue");
        Betrayal.res.loadTexture("armor/blue_helmet.png", "helmet-blue");
        Betrayal.res.loadTexture("armor/blue-hero.png", "armor-blue-hero");
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
        Betrayal.res.loadTexture("icon/attack250x250", "attack");
        Betrayal.res.loadTexture("icon/defense250x250", "defense");
        Betrayal.res.loadTexture("icon/health250x250", "health");

        Betrayal.res.loadTexture("icon/heal250x250", "heal");
        Betrayal.res.loadTexture("icon/damage250x250", "damage");
        Betrayal.res.loadTexture("icon/block250x250", "block");

        // Items

        // Monsters

        // Characters
        for (int i = 1; i <= 9; i++) {
            //heads
            Betrayal.res.loadTexture("character/head" + i + "a", "head" + i + "1");
            Betrayal.res.loadTexture("character/head" + i + "b", "head" + i + "2");
            Betrayal.res.loadTexture("character/head" + i + "c", "head" + i + "3");
            Betrayal.res.loadTexture("character/head" + i + "d", "head" + i + "4");
            Betrayal.res.loadTexture("character/head" + i + "e", "head" + i + "5");
            Betrayal.res.loadTexture("character/head" + i + "f", "head" + i + "6");
            Betrayal.res.loadTexture("character/head" + i + "g", "head" + i + "7");

        }

        //character headgear
        Betrayal.res.loadTexture("character/headgear1a.png", "char-headgear-normal");
        Betrayal.res.loadTexture("character/headgear1b.png", "char-headgear-red");
        Betrayal.res.loadTexture("character/headgear1c.png", "char-headgear-black");
        Betrayal.res.loadTexture("character/headgear1d.png", "char-headgear-gold");
        Betrayal.res.loadTexture("character/headgear1e.png", "char-headgear-blue");
        Betrayal.res.loadTexture("character/headgear1f.png", "char-headgear-green");
        Betrayal.res.loadTexture("character/headgear1g.png", "char-headgear-purple");

        //character armor
        Betrayal.res.loadTexture("character/armor0.png", "char-armor-peasant" );
        Betrayal.res.loadTexture("character/armor1a.png", "char-armor-grey" );
        Betrayal.res.loadTexture("character/armor1b.png", "char-armor-red" );
        Betrayal.res.loadTexture("character/armor1c.png", "char-armor-black" );
        Betrayal.res.loadTexture("character/armor1d.png", "char-armor-blue" );
        Betrayal.res.loadTexture("character/armor1e.png", "char-armor-green" );
        Betrayal.res.loadTexture("character/armor1f.png", "char-armor-pruple" );
        Betrayal.res.loadTexture("character/armor2a.png", "char-armor-red" );
        Betrayal.res.loadTexture("character/armor2b.png", "char-armor-black" );
        Betrayal.res.loadTexture("character/armor2c.png", "char-armor-gold" );
        Betrayal.res.loadTexture("character/armor2d.png", "char-armor-blue" );
        Betrayal.res.loadTexture("character/armor2e.png", "char-armor-green" );
        Betrayal.res.loadTexture("character/armor2f.png", "char-armor-purple" );
        Betrayal.res.loadTexture("character/cloack1a.png", "char-cloak-brown" );
        Betrayal.res.loadTexture("character/cloack1b.png", "char-cloak-black" );


    }
}
