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

public class JoinRoomPopup extends Popup {

	private Image yesButton;
	private Image background;
	private Label titleLabel;
	private TextField roomID, password;
	private String title;
	private static final int INPUT_WIDTH = 500;
	private InputListener keyboardListener;

	public JoinRoomPopup(Betrayal game, String title) {
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
		tfs.font = FontManager.getFont50().font;
		tfs.messageFont = tfs.font;
		tfs.fontColor = Color.LIGHT_GRAY;
		tfs.messageFontColor = Color.GRAY;

		roomID = new TextField("", tfs);
		roomID.setMessageText("Enter Room ID Here");
		roomID.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
		roomID.setBounds((Betrayal.WIDTH - INPUT_WIDTH) / 2,
				titleLabel.getY() - 20 - roomID.getPrefHeight(),
				INPUT_WIDTH, roomID.getPrefHeight());
		roomID.setMaxLength(8);
		popup.addActor(roomID);

		//add white line under
		Image whiteLinee = new com.jnv.betrayal.scene2d.ui.Image(res.getTexture("white-pixel"));
		whiteLinee.setWidth(500);
		whiteLinee.setHeight(3);
		whiteLinee.setX(roomID.getX());
		whiteLinee.setY(roomID.getY() - whiteLinee.getHeight());
		popup.addActor(whiteLinee);

		password = new TextField("", tfs);
		password.setMessageText("Enter Password Here");
		password.setBounds(roomID.getX(), roomID.getY() - 5 - roomID.getHeight(),
				INPUT_WIDTH, roomID.getHeight());
		password.setMaxLength(8);
		popup.addActor(password);

		// Removes keyboard focus if tap isn't on a TextField
		keyboardListener = new InputListener(roomID) {
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
		Image whiteLine = new Image(res.getTexture("white-pixel"));
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
				JoinRoomPopup.this.doAction();
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

	public boolean isRoomIDFieldEmpty() {
		return roomID.getText().isEmpty();
	}

	public int getRoomID() {
		return Integer.parseInt(roomID.getText());
	}
}
