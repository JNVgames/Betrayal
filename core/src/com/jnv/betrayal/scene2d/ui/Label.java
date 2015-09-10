/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jnv.betrayal.scene2d.Dimension;

public class Label extends com.badlogic.gdx.scenes.scene2d.ui.Label {

	public Label(CharSequence text, Skin skin, String styleName) {
		super(text, skin, styleName);
	}

	public Label(CharSequence text, Skin skin, String fontName, Color color) {
		super(text, skin, fontName, color);
	}

	public Label(CharSequence text, Skin skin, String fontName, String colorName) {
		super(text, skin, fontName, colorName);
	}

	public Label(CharSequence text, LabelStyle style) {
		super(text, style);
	}

	public Label(CharSequence text, Skin skin) {
		super(text, skin);
	}

	public void setBounds(Dimension dimension) {
		setBounds(dimension.getX(), dimension.getY(), dimension.getWidth(),
				dimension.getHeight());
	}
}
