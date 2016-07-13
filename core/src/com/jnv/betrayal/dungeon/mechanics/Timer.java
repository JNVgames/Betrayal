/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.mechanics;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.resources.FontManager;

public class Timer {

	private Label timerDisplay;
	private java.util.Timer timer;

	public Timer() {
		timerDisplay = new Label("----", FontManager.getFont70());
		timer = new java.util.Timer();
	}
}
