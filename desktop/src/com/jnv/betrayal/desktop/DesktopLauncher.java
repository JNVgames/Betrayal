/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jnv.betrayal.main.Betrayal;

public class DesktopLauncher {
    public static void main (String[] arg) {
        float scale = 0.4f;
        System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) Math.floor(720 * scale) + 20;
        config.height = (int) Math.floor(1280 * scale);
        config.useGL30 = false;
        new LwjglApplication(new Betrayal(), config);
    }
}
