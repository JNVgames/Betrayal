package com.jnv.betrayal.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class FontManager {

	private static FreeTypeFontGenerator generator =
			new FreeTypeFontGenerator(Gdx.files.internal("fonts/Rajdhani-Regular.ttf"));

	private static FreeTypeFontGenerator.FreeTypeFontParameter fontDetails =
			new FreeTypeFontGenerator.FreeTypeFontParameter();

	private static Label.LabelStyle labelStyle45, labelStyle40, labelStyle50;
	private static Label.LabelStyle labelStyle60, labelStyle70, labelStyle80, labelStyle100, labelStyle120;

	public static void loadFonts() {
		fontDetails.size = 45;
		labelStyle45 = new Label.LabelStyle();
		labelStyle45.font = generator.generateFont(fontDetails);

		fontDetails.size = 40;
		labelStyle40 = new Label.LabelStyle();
		labelStyle40.font = generator.generateFont(fontDetails);

		fontDetails.size = 60;
		labelStyle60 = new Label.LabelStyle();
		labelStyle60.font = generator.generateFont(fontDetails);

		fontDetails.size = 70;
		labelStyle70 = new Label.LabelStyle();
		labelStyle70.font = generator.generateFont(fontDetails);

		fontDetails.size = 50;
		labelStyle50 = new Label.LabelStyle();
		labelStyle50.font = generator.generateFont(fontDetails);

		fontDetails.size = 80;
		labelStyle80 = new Label.LabelStyle();
		labelStyle80.font = generator.generateFont(fontDetails);

		fontDetails.size = 100;
		labelStyle100 = new Label.LabelStyle();
		labelStyle100.font = generator.generateFont(fontDetails);

		fontDetails.size = 120;
		labelStyle120 = new Label.LabelStyle();
		labelStyle120.font = generator.generateFont(fontDetails);
	}

	public static Label.LabelStyle getFont45() {
		return labelStyle45;
	}

	public static Label.LabelStyle getFont40() {
		return labelStyle40;
	}

	public static Label.LabelStyle getFont50() {
		return labelStyle50;
	}

	public static Label.LabelStyle getFont60() {
		return labelStyle60;
	}

	public static Label.LabelStyle getFont70() {
		return labelStyle70;
	}

	public static Label.LabelStyle getFont80() {
		return labelStyle80;
	}

	public static Label.LabelStyle getFont100() {
		return labelStyle100;
	}

	public static Label.LabelStyle getFont120() {
		return labelStyle120;
	}
}
