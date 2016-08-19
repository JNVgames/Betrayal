package com.jnv.betrayal.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jnv.betrayal.scene2d.Dimension;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class ScrollPane extends com.badlogic.gdx.scenes.scene2d.ui.ScrollPane {

	public ScrollPane(Actor widget) {
		super(widget);
	}

	public ScrollPane(Actor widget, Skin skin) {
		super(widget, skin);
	}

	public ScrollPane(Actor widget, Skin skin, String styleName) {
		super(widget, skin, styleName);
	}

	public ScrollPane(Actor widget, ScrollPaneStyle style) {
		super(widget, style);
	}

	public void setBounds(Dimension dimension) {
		setBounds(dimension.getX(), dimension.getY(), dimension.getWidth(), dimension.getHeight());
	}

	public Dimension getDimensions() {
		return new Dimension(getX(), getY(), getWidth(), getHeight());
	}
}
