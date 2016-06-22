package com.jnv.betrayal.popup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class OKPopup extends Popup {

	private Image okayButton, noButton, background;
	private Label title;
	private String string;

	public OKPopup(Betrayal game, String string) {
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
		font.fontColor = Color.WHITE;
		title = new Label(string, font);
		title.layout();
		title.setBounds((Betrayal.WIDTH - background.getWidth()) / 2,
				okayButton.getY() + okayButton.getHeight() + 50, background.getWidth(),
				title.getPrefHeight());
		title.setAlignment(Align.center);
		popup.addActor(title);
	}

	private void loadAnswer() {
		okayButton = new Image(res.getTexture("ok"));
		okayButton.layout();
		okayButton.setBounds(Betrayal.WIDTH / 2 - 75,
				Betrayal.HEIGHT / 2 - 100, 150, 75);
		okayButton.addListener(new InputListener(okayButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(okayButton);

	}

	public void doAction() {
	}
}
