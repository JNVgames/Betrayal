/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class FontManager {

	private static FreeTypeFontGenerator generator =
			new FreeTypeFontGenerator(Gdx.files.internal("fonts/Rajdhani-SemiBold.ttf"));;

	public static Label.LabelStyle getFont(int fontSize) {
		FreeTypeFontGenerator.FreeTypeFontParameter fontDetails =
				new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontDetails.size = fontSize;
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = generator.generateFont(fontDetails);
		return labelStyle;
	}
}
