/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.handlers.screentouch;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.jnv.betrayal.main.Betrayal;

public class BetrayalInputProcessor {
    private OrthographicCamera camera;
    private Vector3 touch;

    public BetrayalInputProcessor() {
        touch = new Vector3();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Betrayal.WIDTH, Betrayal.HEIGHT);
    }

    // Helpers
    public boolean touchDown(int x, int y, int pointer, int button) {
        //Convert coordinates
        touch.set(x, y, 0);
        camera.unproject(touch);
        BetrayalInput.x = touch.x;
        BetrayalInput.y = touch.y;
        BetrayalInput.down = true;
        return false;
    }
    public boolean touchUp(int x, int y, int pointer, int button) {
        //Convert coordinates
        touch.set(x, y, 0);
        camera.unproject(touch);
        BetrayalInput.x = touch.x;
        BetrayalInput.y = touch.y;
        BetrayalInput.down = false;
        return false;
    }
    public boolean touchDragged(int x, int y, int pointer) {
        //Convert coordinates
        touch.set(x, y, 0);
        camera.unproject(touch);
        BetrayalInput.x = touch.x;
        BetrayalInput.y = touch.y;
        BetrayalInput.down = true;
        return false;
    }
}
