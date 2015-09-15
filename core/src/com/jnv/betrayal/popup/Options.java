/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class Options extends Popup {

	private Image background, exitButton;
	private Label title;
	private int currentContent, totalContent;

	public Options(Betrayal game) {
		super(game);
		currentContent = 0;
		totalContent = 3;
		loadButtons();
	}

	private void loadButtons() {
		loadBackground();
		loadContent();
	}

	private void loadMainMenuButton() {
		exitButton = new Image(res.getTexture("x"));
		exitButton.layout();
		exitButton.setBounds(Betrayal.WIDTH - 250, Betrayal.HEIGHT - 250, 100, 100);
		exitButton.addListener(new InputListener(exitButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(exitButton);
	}

	private void loadTitle() {
		title = new Label("Options", FontManager.getFont(60));
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 300);
		popup.addActor(title);
	}

	private void loadBackground() {
		background = new Image(res.getTexture("instructions-background"));
		background.layout();
		background.setBounds(150, 150, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 300);
		popup.addActor(background);
	}

	private void removeCurrentContent() {
		switch (currentContent) {
			case 0:
				exitButton.remove();
				title.remove();
				break;
			case 1:
				break;
			default:
				Gdx.app.log("content", "should not happen");
				break;
		}
	}

	private void loadContent() {
		switch (currentContent) {
			case 0:
				loadMainMenuButton();
				loadTitle();
				break;
			case 1:
				break;
			default:
				Gdx.app.log("content", "should not happen");
				break;
		}
	}
}
