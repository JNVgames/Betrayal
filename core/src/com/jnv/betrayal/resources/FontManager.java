package com.jnv.betrayal.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class FontManager {

	private static Label.LabelStyle labelStyle35, labelStyle45, labelStyle40, labelStyle50;
	private static Label.LabelStyle labelStyle60, labelStyle70, labelStyle80, labelStyle100, labelStyle120;

	public static void loadFonts() {
		// Font size 35
		labelStyle35 = new Label.LabelStyle();
		labelStyle35.font = new BitmapFont(Gdx.files.internal("fonts/Rajdhani-Regular.fnt"));
		labelStyle35.font.getData().scale(-0.55f);

		// Font size 45
		labelStyle45 = new Label.LabelStyle();
		labelStyle45.font = new BitmapFont(Gdx.files.internal("fonts/Rajdhani-Regular.fnt"));
		labelStyle45.font.getData().scale(-0.45f);

		// Font size 40
		labelStyle40 = new Label.LabelStyle();
		labelStyle40.font = new BitmapFont(Gdx.files.internal("fonts/Rajdhani-Regular.fnt"));
		labelStyle40.font.getData().scale(-0.5f);

		// Font size 60
		labelStyle60 = new Label.LabelStyle();
		labelStyle60.font = new BitmapFont(Gdx.files.internal("fonts/Rajdhani-Regular.fnt"));
		labelStyle60.font.getData().scale(-0.3f);

		// Font size 70
		labelStyle70 = new Label.LabelStyle();
		labelStyle70.font = new BitmapFont(Gdx.files.internal("fonts/Rajdhani-Regular.fnt"));
		labelStyle70.font.getData().scale(-0.15f);

		// Font size 50
		labelStyle50 = new Label.LabelStyle();
		labelStyle50.font = new BitmapFont(Gdx.files.internal("fonts/Rajdhani-Regular.fnt"));
		labelStyle50.font.getData().scale(-0.4f);

		// Font size 80
		labelStyle80 = new Label.LabelStyle();
		labelStyle80.font = new BitmapFont(Gdx.files.internal("fonts/Rajdhani-Regular.fnt"));
		labelStyle80.font.getData().scale(-0.05f);

		// Font size 100
		labelStyle100 = new Label.LabelStyle();
		labelStyle100.font = new BitmapFont(Gdx.files.internal("fonts/Rajdhani-Regular.fnt"));

		// Font size 120
		labelStyle120 = new Label.LabelStyle();
		labelStyle120.font = new BitmapFont(Gdx.files.internal("fonts/Rajdhani-Regular.fnt"));
	}

	public static Label.LabelStyle getFont35() {
		return labelStyle35;
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
