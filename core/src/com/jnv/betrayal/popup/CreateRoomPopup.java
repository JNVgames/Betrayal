package com.jnv.betrayal.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class CreateRoomPopup extends Popup {

	private Image yesButton;
	private Image background;
	private Label titleLabel;
	private TextField password;
	private String title;
	private static final int INPUT_WIDTH = 500;
	private InputListener keyboardListener;

	public CreateRoomPopup(Betrayal game, String title) {
		super(game);
		this.title = title;
		loadButtons();
	}

	private void loadButtons() {
		loadBackground();
		loadAnswer();
		loadTitle();
		loadInputFields();
	}

	private void loadInputFields() {
		TextField.TextFieldStyle tfs = new TextField.TextFieldStyle();
		tfs.font = FontManager.getFont60().font;
		tfs.messageFont = tfs.font;
		tfs.fontColor = Color.LIGHT_GRAY;
		tfs.messageFontColor = Color.GRAY;

		password = new TextField("", tfs);
		password.setMessageText("Enter Password Here");
		password.setBounds((Betrayal.WIDTH - INPUT_WIDTH) / 2,
				titleLabel.getY() - 50 - password.getPrefHeight(),
				INPUT_WIDTH, password.getPrefHeight());
		password.setMaxLength(8);
		popup.addActor(password);

		// Removes keyboard focus if tap isn't on a TextField
		keyboardListener = new InputListener(password) {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (!(event.getTarget() instanceof TextField)) {
					game.getStage().setKeyboardFocus(null);
					Gdx.input.setOnscreenKeyboardVisible(false);
				}
				return false;
			}
		};
		game.getStage().getRoot().addCaptureListener(keyboardListener);

		//add white line under
		com.jnv.betrayal.scene2d.ui.Image whiteLine = new com.jnv.betrayal.scene2d.ui.Image(res.getTexture("white-pixel"));
		whiteLine.setWidth(500);
		whiteLine.setHeight(3);
		whiteLine.setX(password.getX());
		whiteLine.setY(password.getY() - whiteLine.getHeight());
		popup.addActor(whiteLine);

	}

	private void loadBackground() {
		background = new Image(res.getTexture("confirmation-background"));
		background.layout();
		background.setBounds(100, 450, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 900);
		popup.addActor(background);
	}

	private void loadTitle() {
		titleLabel = new Label(title, FontManager.getFont50());
		titleLabel.layout();
		titleLabel.setBounds((Betrayal.WIDTH - titleLabel.getPrefWidth()) / 2,
				background.getTop() - 10 - titleLabel.getPrefHeight(),
				titleLabel.getPrefWidth(), titleLabel.getPrefHeight());
		popup.addActor(titleLabel);
	}

	private void loadAnswer() {
		yesButton = new Image(res.getTexture("yes"));
		yesButton.layout();
		yesButton.setBounds(Betrayal.WIDTH / 2 - 175,
				Betrayal.HEIGHT / 2 - 150, 150, 75);
		yesButton.addListener(new InputListener(yesButton) {
			@Override
			public void doAction() {
				CreateRoomPopup.this.doAction();
				remove();
			}
		});
		popup.addActor(yesButton);

		Image noButton = new Image(res.getTexture("no"));
		noButton.layout();
		noButton.setBounds(Betrayal.WIDTH / 2 + 25,
				Betrayal.HEIGHT / 2 - 150, 150, 75);
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

	@Override
	public void remove() {
		super.remove();
		game.getStage().removeCaptureListener(keyboardListener);
	}

	public String getPasswordString() {
		return password.getText();
	}
}
