package com.jnv.betrayal.dungeon.popup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class CardInfo extends Popup {

	private Image okayButton, noButton, background;
	private Label title;
	private String string;

	public CardInfo(Betrayal game, Card card) {
		super(game);
		this.string = "Name: " + card.getName()
				+ "\nHealth: " + Integer.toString(card.getCurrentHealth()) + "/" + Integer.toString(card.getBaseHealth())
				+ "\nAttack: " + Integer.toString(card.getCurrentAttack()) + "/" + Integer.toString(card.getBaseAttack())
				+ "\nDefense: " + Integer.toString(card.getCurrentDefense()) + "/" + Integer.toString(card.getBaseDefense());
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
		background.setBounds(150, 500, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 875);
		popup.addActor(background);
	}

	private void loadTitle() {
		Label.LabelStyle font = FontManager.getFont40();
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
				onConfirm();
				remove();
			}
		});
		popup.addActor(okayButton);

	}

	public void onConfirm() {
	}
}
