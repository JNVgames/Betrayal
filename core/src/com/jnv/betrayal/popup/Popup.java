/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public abstract class Popup {

    protected Betrayal game;
    protected BetrayalAssetManager res;
    protected Stage stage;

    protected Popup(Betrayal game) {
        this.game = game;
        res = game.res;
        stage = game.getStage();
    }
}
