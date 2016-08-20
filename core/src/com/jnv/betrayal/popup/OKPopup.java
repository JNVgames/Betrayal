package com.jnv.betrayal.popup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class OKPopup extends Popup {

	private int x = 150, y = 500, width = Betrayal.WIDTH - 300, height = Betrayal.HEIGHT - 1000;
	private Image okayButton, background;
	private String text;
	private boolean isVisible = true;

	public OKPopup(Betrayal game, String text) {
		super(game);
		this.text = text;
		init();
	}

	public OKPopup(int width, int height, Betrayal game, String text) {
		this(width, height, game, text, true);
	}

	public OKPopup(int width, int height, Betrayal game, String text, boolean show) {
		super(game);
		this.text = text;
		this.width = width;
		this.height = height;
		isVisible = show;
		x = (Betrayal.WIDTH - width) / 2;
		y = (Betrayal.HEIGHT - height) / 2;
		if (show) {
			init();
		}
	}

	private void init() {
		loadButtons();
		setMaskAction(new Runnable() {
			@Override
			public void run() {
				onConfirm();
			}
		});
	}

	public void show() {
		if (!isVisible) {
			loadButtons();
			isVisible = true;
		}
	}

	private void loadButtons() {
		loadBackground();
		loadAnswer();
		loadText();
	}

	private void loadBackground() {
		background = new Image(res.getTexture("confirmation-background"));
		background.layout();
		background.setBounds(x, y, width, height);
		popup.addActor(background);
	}

	private void loadText() {
		Label.LabelStyle font = FontManager.getFont40();
		font.fontColor = Color.WHITE;
		Label title = new Label(text, font);
		title.layout();
		title.setBounds((Betrayal.WIDTH - background.getWidth()) / 2,
				(background.getTop() + okayButton.getTop() - title.getPrefHeight()) / 2, background.getWidth(),
				title.getPrefHeight());
		title.setAlignment(Align.center);
		popup.addActor(title);
	}

	private void loadAnswer() {
		okayButton = new Image(res.getTexture("ok"));
		okayButton.layout();
		okayButton.setBounds(Betrayal.WIDTH / 2 - 75,
				background.getY() + 30, 150, 75);
		okayButton.addListener(new InputListener(okayButton) {
			@Override
			public void doAction() {
				onConfirm();
				remove();
			}
		});
		popup.addActor(okayButton);

	}

	public void onConfirm() {
	}
}
