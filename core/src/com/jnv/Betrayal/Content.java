package com.jnv.Betrayal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by jarnin on 7/20/15.
 */
public class Content {

    private HashMap<String, Texture> textures;

    public Content() {
        textures = new HashMap<String, Texture>();
    }

    public void loadTexture(String path) {
        int slashIndex = path.lastIndexOf("/");
        String key;
        if (slashIndex == -1) {
            key = path.substring(0, path.lastIndexOf("."));
        } else {
            key = path.substring(slashIndex + 1, path.lastIndexOf("."));
        }
        loadTexture(path, key);
    }

    public void loadTexture(String path, String key) {
        Texture t = new Texture(Gdx.files.internal(path));
        textures.put(key, t);
    }

    public Texture getTexture(String key) {
        return textures.get(key);
    }

    public void removeTexture(String key) {
        Texture t = textures.get(key);
        if (t != null) {
            textures.remove(key);
            t.dispose();
        }
    }

    public static void loadAll() {
        Betrayal.res.loadTexture("about/jnv720x1280.png", "splash");
        Betrayal.res.loadTexture("about/start_screen720x1280.png", "start_background");
        Betrayal.res.loadTexture("button/load_game_button512x144.png", "load");
        Betrayal.res.loadTexture("button/new_game_button512x144.png", "new");
        Betrayal.res.loadTexture("button/options_button512x144.png", "options");
    }
}
