/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;

public class BetrayalLabel extends Label{

    public BetrayalLabel(CharSequence text, LabelStyle style) {
        super(text, style);
    }
    public BetrayalLabel(CharSequence text, int fontSize) {
        this(text, Betrayal.getFont(fontSize));
    }
}
