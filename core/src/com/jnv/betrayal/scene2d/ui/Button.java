package com.jnv.betrayal.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.jnv.betrayal.scene2d.Dimension;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Button extends com.badlogic.gdx.scenes.scene2d.ui.Button {

	public Button(Skin skin) {
		super(skin);
	}

	public Button(Skin skin, String styleName) {
		super(skin, styleName);
	}

	public Button(Actor child, Skin skin, String styleName) {
		super(child, skin, styleName);
	}

	public Button(Actor child, ButtonStyle style) {
		super(child, style);
	}

	public Button(ButtonStyle style) {
		super(style);
	}

	public Button() {
		super();
	}

	public Button(Drawable up) {
		super(up);
	}

	public Button(Drawable up, Drawable down) {
		super(up, down);
	}

	public Button(Drawable up, Drawable down, Drawable checked) {
		super(up, down, checked);
	}

	public Button(Actor child, Skin skin) {
		super(child, skin);
	}

	public void setBounds(Dimension dimension) {
		setBounds(dimension.getX(), dimension.getY(), dimension.getWidth(), dimension.getHeight());
	}
}
