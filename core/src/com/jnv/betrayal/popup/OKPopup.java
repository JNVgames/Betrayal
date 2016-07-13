package com.jnv.betrayal.popup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class OKPopup extends Popup {

	private int x = 150, y = 500, width = Betrayal.WIDTH - 300, height = Betrayal.HEIGHT - 1000;
	private Image okayButton, background;
	private String string;

	public OKPopup(Betrayal game, String string) {
		super(game);
		this.string = string;
		loadButtons();
		setMaskAction(new Runnable() {
			@Override
			public void run() {
				onConfirm();
			}
		});
	}

	public OKPopup(int width, int height, Betrayal game, String string) {
		super(game);
		this.string = string;
		this.width = width;
		this.height = height;
		x = (Betrayal.WIDTH - width) / 2;
		y = (Betrayal.HEIGHT - height) / 2;
		loadButtons();
		setMaskAction(new Runnable() {
			@Override
			public void run() {
				onConfirm();
			}
		});
	}

	private void loadButtons() {
		loadBackground();
		loadAnswer();
		loadTitle();
	}

	private void loadBackground() {
		background = new Image(res.getTexture("confirmation-background"));
		background.layout();
		background.setBounds(x, y, width, height);
		popup.addActor(background);
	}

	private void loadTitle() {
		Label.LabelStyle font = FontManager.getFont40();
		font.fontColor = Color.WHITE;
		Label title = new Label(string, font);
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
