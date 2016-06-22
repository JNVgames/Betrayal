package com.jnv.betrayal.popup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class Confirmation extends Popup {

	private Image yesButton, noButton, background;
	private Label title;
	private String string;

	public Confirmation(Betrayal game, String string) {
		super(game);
		this.string = string;
		loadButtons();
	}

	private void loadButtons() {
		loadBackground();
		loadAnswer();
		loadTitle();
	}

	private void loadBackground() {
		background = new Image(res.getTexture("confirmation-background"));
		background.layout();
		background.setBounds(150, 500, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 1000);
		popup.addActor(background);
	}

	private void loadTitle() {
		Label.LabelStyle font = FontManager.getFont(40);
		font.fontColor = Color.BLACK;
		title = new Label(string, font);
		title.layout();
		title.setBounds((Betrayal.WIDTH - background.getWidth()) / 2,
				yesButton.getY() + yesButton.getHeight() + 20, background.getWidth(),
				title.getPrefHeight());
		title.setAlignment(Align.center);
		popup.addActor(title);
	}

	private void loadAnswer() {
		yesButton = new Image(res.getTexture("yes"));
		yesButton.layout();
		yesButton.setBounds(Betrayal.WIDTH / 2 - 175,
				Betrayal.HEIGHT / 2 - 100, 150, 75);
		yesButton.addListener(new InputListener(yesButton) {
			@Override
			public void doAction() {
				Confirmation.this.doAction();
				remove();
			}
		});
		popup.addActor(yesButton);

		noButton = new Image(res.getTexture("no"));
		noButton.layout();
		noButton.setBounds(Betrayal.WIDTH / 2 + 25,
				Betrayal.HEIGHT / 2 - 100, 150, 75);
		noButton.addListener(new InputListener(noButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(noButton);
	}

	public void doAction() {
	}
}
