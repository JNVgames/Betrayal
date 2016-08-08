/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.utils.Trait;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.resources.JobDescription;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Image;

/**
 * Sets up the character creation screen with scene2d
 *
 * @author Vincent Wang
 */
public class CharacterSelection extends GameState {

	private Actor playNowButton;
	private Dimension reference;
	private Image backButton, previewFrame;
	private Character character;
	private TextureRegion leftArrowImage, rightArrowImage;
	private TextField fieldUsernameEnter;

	public CharacterSelection(GameStateManager gsm) {
		super(gsm);

		leftArrowImage = new TextureRegion(res.getTexture("arrow-right"));
		leftArrowImage.flip(true, false);
		rightArrowImage = new TextureRegion(res.getTexture("arrow-right"));

		character = new Character(res);

		loadStage();
	}

	public void update(float dt) {
		stage.act(dt);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	public void dispose() {

	}

	// Helpers

	/**
	 * Calls the appropriate functions to create the character selection screen
	 */
	private void loadStage() {
		loadBackground();
		loadBackButton();
		loadPlayNowButton();
		loadImagePreview();
		loadPreviewRotators();
		loadUsernameField();

		createSelectionField("Gender", Trait.GENDER);
		createSelectionField("Hair Style", Trait.HAIR_STYLE);
		createSelectionField("Hair Color", Trait.HAIR_COLOR);
		createSelectionField("Class", Trait.JOB);

		loadJobDescription();
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("instructions-background")) {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.disableBlending();
				super.draw(batch, parentAlpha);
				batch.enableBlending();
			}
		};
		background.layout();
		background.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		stage.addActor(background);
	}

	private void loadBackButton() {
		backButton = new Image(leftArrowImage);
		backButton.setHeight(60);
		backButton.setWidth(80);
		backButton.setX(10);
		backButton.setY(Betrayal.HEIGHT - backButton.getHeight() - 10);
		stage.addActor(backButton);

		Actor actor = new Label("Back", FontManager.getFont60());
		actor.setX(backButton.getX() + backButton.getWidth() + 10);
		actor.setY(backButton.getY());
		stage.addActor(actor);
		Dimension ref = new Dimension(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());

		actor = new Actor();
		actor.setWidth(backButton.getWidth() + ref.getWidth() + 10);
		actor.setHeight(ref.getHeight());
		actor.setX(backButton.getX());
		actor.setY(backButton.getY());
		actor.addListener(new InputListener(actor) {
			@Override
			public void doAction() {
				character = null;
				gsm.setState(GameStateManager.State.MENU);
			}
		});
		stage.addActor(actor);
	}

	private void loadPlayNowButton() {
		Skin skin = new Skin();
		skin.add("play-now", res.getTexture("play-now"));
		skin.add("play-now-pressed", res.getTexture("play-now-pressed"));
		playNowButton = new Button(skin.getDrawable("play-now"), skin.getDrawable("play-now-pressed"));
		playNowButton.setWidth(512);
		playNowButton.setBounds((Betrayal.WIDTH - playNowButton.getWidth()) / 2, 20, 512, 144);
		playNowButton.addListener(new InputListener(playNowButton) {
			@Override
			public void doAction() {
				if (fieldUsernameEnter.getText().isEmpty() && !Betrayal.DEBUG) {
					// Prompt user to enter a name
					new OKPopup(game, "Please enter\na username.");
				} else if (isNameAllSpaces(fieldUsernameEnter.getText()) && !Betrayal.DEBUG) {
					new OKPopup(game, "Add some letters\nto your name, please.");
				} else {
					character.setName(fieldUsernameEnter.getText());
					game.characters.add(character);
					game.setCurrentCharacter(character);
					character.instantializeRoom();
					gsm.setState(GameStateManager.State.LOBBY);
				}
			}
		});
		stage.addActor(playNowButton);
	}

	private boolean isNameAllSpaces(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) != ' ') {
				return false;
			}
		}
		return true;
	}

	private void loadUsernameField() {
		// Username "Name:" text
		Label usernameTextField = new Label("Name: ", FontManager.getFont60());
		usernameTextField.setX(10);
		usernameTextField.setY(backButton.getY() - backButton.getHeight() - 10);
		stage.addActor(usernameTextField);

		// Username input text field
		TextField.TextFieldStyle tfs = new TextField.TextFieldStyle();
		tfs.font = FontManager.getFont60().font;
		tfs.messageFont = tfs.font;
		tfs.fontColor = Color.LIGHT_GRAY;
		tfs.messageFontColor = Color.GRAY;
		fieldUsernameEnter = new TextField("", tfs);
		fieldUsernameEnter.setMessageText("Enter name here");
		fieldUsernameEnter.setBounds(10 + usernameTextField.getWidth() + 10, usernameTextField.getY(),
				Betrayal.WIDTH - 20 - usernameTextField.getWidth(), usernameTextField.getHeight());
		fieldUsernameEnter.setMaxLength(12);
		stage.addActor(fieldUsernameEnter);

		// Removes keyboard focus if tap isn't on a TextField
		stage.getRoot().addCaptureListener(new InputListener(fieldUsernameEnter) {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (!(event.getTarget() instanceof TextField)) {
					stage.setKeyboardFocus(null);
					Gdx.input.setOnscreenKeyboardVisible(false);
				}
				return false;
			}
		});
	}

	private void loadImagePreview() {
		loadPreviewFrame();
		loadReference();
		loadPreview();
	}

	private void loadPreviewFrame() {
		previewFrame = new Image();
		previewFrame.setWidth(384);
		previewFrame.setHeight(576);
		previewFrame.setX(10);
		previewFrame.setY(backButton.getY() - 71 - previewFrame.getHeight());
		stage.addActor(previewFrame);
	}

	private void loadPreviewRotators() {
		int gap = 60, padding = 30;

		Actor actor = new Image(leftArrowImage);
		actor.setHeight(60);
		actor.setWidth((previewFrame.getWidth() - gap - padding * 2) / 2);
		actor.setX(previewFrame.getX() + padding);
		actor.setY(previewFrame.getY()
				- actor.getHeight() - 10);
		actor.addListener(new InputListener(actor) {
			@Override
			public void doAction() {
				character.preview.rotateLeft();
			}
		});
		stage.addActor(actor);
		Dimension ref = new Dimension(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());

		actor = new Image(rightArrowImage);
		actor.setHeight(ref.getHeight());
		actor.setWidth(ref.getWidth());
		actor.setX(ref.getRight() + gap);
		actor.setY(ref.getY());
		stage.addActor(actor);
		actor.addListener(new InputListener(actor) {
			@Override
			public void doAction() {
				character.preview.rotateRight();
			}
		});
	}

	private void loadReference() {
		reference = new Dimension();
		reference.setX(previewFrame.getRight() + 20);
		reference.setY(previewFrame.getTop() + 30);
		reference.setWidth(Betrayal.WIDTH - reference.getX() - 20);
		reference.setHeight(60);
	}

	private void loadPreview() {
		Actor previewField = new Actor() {
			public void draw(Batch batch, float parentAlpha) {
				character.preview.drawPreview(batch, previewFrame.getX(),
						previewFrame.getY(), previewFrame.getWidth(),
						previewFrame.getHeight());
			}
		};
		previewField.setWidth(384);
		previewField.setHeight(576);
		previewField.setX(previewFrame.getX());
		previewField.setY(previewFrame.getY());
		stage.addActor(previewField);
	}

	private void loadJobDescription() {
		Label jobDescription = new Label("", FontManager.getFont45()) {
			@Override
			public void act(float delta) {
				super.act(delta);
				switch (character.job.getJob()) {
					case WARRIOR:
						setText(JobDescription.getWarriorDescription());
						break;
					case KNIGHT:
						setText(JobDescription.getKnightDescription());
						break;
					case PRIEST:
						setText(JobDescription.getPriestDescription());
						break;
					case THIEF:
						setText(JobDescription.getThiefDescription());
						break;
					default:
						break;
				}
			}
		};
		jobDescription.setHeight(previewFrame.getY() - 80
				- jobDescription.getHeight()
				- (playNowButton.getTop() + 10));
		jobDescription.setWidth(Betrayal.WIDTH - 20);
		jobDescription.setX(previewFrame.getX());
		jobDescription.setY(previewFrame.getY() - 80 - jobDescription.getHeight());
		jobDescription.setAlignment(Align.topLeft);
		stage.addActor(jobDescription);
	}

	public void createSelectionField(String label, final Trait trait) {
		// Create dimension reference
		Dimension dimRef = new Dimension();

		// Create label
		Actor actor = new Label(label, FontManager.getFont60());
		actor.setBounds(previewFrame.getRight() + 20, reference.getY() -
				actor.getHeight() - 30, Betrayal.WIDTH - reference.getX() - 20, 60);
		((Label) actor).setAlignment(Align.center);
		stage.addActor(actor);
		dimRef.setBounds(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());

		// Create left arrow
		actor = new Image(leftArrowImage);
		actor.setBounds(dimRef.getX(),
				dimRef.getY() - 10 - dimRef.getHeight(),
				(Betrayal.WIDTH - 160 - previewFrame.getRight()) / 2,
				reference.getHeight());
		reference.setBounds(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
		actor.addListener(new InputListener(actor) {
			@Override
			public void doAction() {
				setPreviousTrait(trait);
			}
		});
		stage.addActor(actor);
		dimRef.setBounds(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());

		// Create serial number
		actor = new Label("", FontManager.getFont60()) {
			@Override
			public void act(float delta) {
				super.act(delta);
				if (!getText().toString().equals(character.getTrait(trait)))
					setText(character.getTrait(trait));
			}
		};
		actor.setBounds(dimRef.getX()
						+ dimRef.getWidth() + 30, dimRef.getY(),
				60, reference.getHeight());
		((Label) actor).setAlignment(Align.center);
		stage.addActor(actor);
		dimRef.setBounds(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());

		// Create right arrow
		actor = new Image(rightArrowImage);
		actor.setBounds(dimRef.getX() +
						dimRef.getWidth() + 30, reference.getY(),
				reference.getWidth(), reference.getHeight());
		actor.addListener(new InputListener(actor) {
			@Override
			public void doAction() {
				setNextTrait(trait);
			}
		});
		stage.addActor(actor);
	}

	private void setPreviousTrait(Trait trait) {
		character.setPreviousTrait(trait);
	}

	private void setNextTrait(Trait trait) {
		character.setNextTrait(trait);
	}
}
