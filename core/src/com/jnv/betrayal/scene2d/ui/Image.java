/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.scene2d.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;
import com.jnv.betrayal.scene2d.Dimension;

public class Image extends com.badlogic.gdx.scenes.scene2d.ui.Image {

	public Image() {
		super();
	}

	public Image(Texture texture) {
		super(texture);
	}

	public Image(NinePatch patch) {
		super(patch);
	}

	public Image(TextureRegion region) {
		super(region);
	}

	public Image(Skin skin, String drawableName) {
		super(skin, drawableName);
	}

	public Image(Drawable drawable) {
		super(drawable);
	}

	public Image(Drawable drawable, Scaling scaling) {
		super(drawable, scaling);
	}

	public Image(Drawable drawable, Scaling scaling, int align) {
		super(drawable, scaling, align);
	}

	public void setBounds(Dimension dim) {
		setBounds(dim.getX(), dim.getY(), dim.getWidth(), dim.getHeight());
	}

	public Dimension getDimensions() {
		return new Dimension(getX(), getY(), getWidth(), getHeight());
	}
}
