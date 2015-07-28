package com.jnv.Betrayal.Utilities;

import com.jnv.Betrayal.Main.Betrayal;


//WE"RE GONNA keep this bAby ORGANIZED MOTEHr FKErS
public class TextureLoader {

    public static void loadAll() {
        //about
        Betrayal.res.loadTexture("about/jnv720x1280.png", "splash");
        Betrayal.res.loadTexture("about/start_screen720x1280.png", "start_background");

        //buttons
        Betrayal.res.loadTexture("button/load_game_button512x144.png", "load");
        Betrayal.res.loadTexture("button/new_game_button512x144.png", "new");
        Betrayal.res.loadTexture("button/options_button512x144.png", "options");

        Betrayal.res.loadTexture("button/buy360x144.png", "buy");
        Betrayal.res.loadTexture("button/cancel360x144.png", "cancel");
        Betrayal.res.loadTexture("button/color360x144.png", "color");
        Betrayal.res.loadTexture("button/equip360x144.png", "equip");
        Betrayal.res.loadTexture("button/sell360x144.png", "sell");

        //weapons

        //items
        Betrayal.res.loadTexture("icon/attack250x250", "attack");
        Betrayal.res.loadTexture("icon/defense250x250", "defense");
        Betrayal.res.loadTexture("icon/health250x250", "health");

        Betrayal.res.loadTexture("icon/heal250x250", "heal");
        Betrayal.res.loadTexture("icon/damage250x250", "damage");
        Betrayal.res.loadTexture("icon/block250x250", "block");
        //icons

        //monsters

        //characters
        for(int a=0; a<=8; a++) {
            for (int b = 0;b<=6; b++) {
                Betrayal.res.loadTexture("character/" + a + b + "000000", a+b+"000000");
            }
        }




    }
}
