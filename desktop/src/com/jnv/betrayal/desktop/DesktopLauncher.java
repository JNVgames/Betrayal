/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jnv.betrayal.main.Betrayal;

public class DesktopLauncher {
    public static void main (String[] arg) {
        System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 720;
        config.height = 1280;
        config.useGL30 = false;
        new LwjglApplication(new Betrayal(), config);
    }
}
