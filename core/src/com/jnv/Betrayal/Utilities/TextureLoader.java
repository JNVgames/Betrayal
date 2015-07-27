package com.jnv.Betrayal.Utilities;

import com.jnv.Betrayal.Main.Betrayal;

public class TextureLoader {

    public static void loadAll() {
        Betrayal.res.loadTexture("about/jnv720x1280.png", "splash");
        Betrayal.res.loadTexture("about/start_screen720x1280.png", "start_background");
        Betrayal.res.loadTexture("button/load_game_button512x144.png", "load");
        Betrayal.res.loadTexture("button/new_game_button512x144.png", "new");
        Betrayal.res.loadTexture("button/options_button512x144.png", "options");
    }
}
