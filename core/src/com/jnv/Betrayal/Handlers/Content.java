package com.jnv.Betrayal.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class Content {

    private HashMap<String, Texture> textures;

    public Content() {
        textures = new HashMap<String, Texture>();
    }

    public void loadTexture(String path, String key) {
        Texture t = new Texture(Gdx.files.internal(path));
        textures.put(key, t);
    }

    public Texture getTexture(String key) {
        return textures.get(key);
    }


}
