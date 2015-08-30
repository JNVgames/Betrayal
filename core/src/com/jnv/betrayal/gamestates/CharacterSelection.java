/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Job;
import com.jnv.betrayal.gameobjects.Character;
import com.jnv.betrayal.inputprocessors.InputListener;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.JobDescription;

/**
 * Sets up the character creation screen with scene2d
 *
 * @author Vincent Wang
 */
public class CharacterSelection extends GameState {

	private Actor reference, button_play_now;
	private Image button_back, field_framePreview;
	private Label field_usernameLabel, label_jobDescription;
	private TextField field_usernameEnter;
	private SelectionField gender, hairStyle, hairColor, job;
	private Character character;
	private TextureRegion image_leftArrow, image_rightArrow;
	private Texture image_button_play;

	public CharacterSelection(GameStateManager gsm) {
		super(gsm);

		image_leftArrow = new TextureRegion(res.getTexture("arrow-right"));
		image_leftArrow.flip(true, false);
		image_rightArrow = new TextureRegion(res.getTexture("arrow-right"));

		character = new Character(res);

		loadStage();
	}

	public void update(float dt) {
		updateTraits();
		updateJobDescription(label_jobDescription);
		stage.act(dt);
	}

	public void handleInput() {

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
		loadUsernameField();
		loadImagePreview();
		loadPreviewRotators();

		gender = new SelectionField("Gender", Character.Trait.GENDER);
		hairStyle = new SelectionField("Hair Style", Character.Trait.HAIR_STYLE);
		hairColor = new SelectionField("Hair Color", Character.Trait.HAIR_COLOR);
		job = new SelectionField("Class", Character.Trait.JOB);

		gender.addToStage();
		hairStyle.addToStage();
		hairColor.addToStage();
		job.addToStage();

		loadJobDescription();
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("instructions-background"));
		background.layout();
		background.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		stage.addActor(background);
	}

	private void loadBackButton() {
		Group group_button_back = new Group();

		button_back = new Image(image_leftArrow);
		button_back.setHeight(60);
		button_back.setWidth(80);
		button_back.setX(10);
		button_back.setY(Betrayal.HEIGHT - button_back.getHeight() - 10);
		group_button_back.addActor(button_back);

		Label button_text_back = new Label("Back", Betrayal.getFont(60));
		button_text_back.setX(button_back.getX() + button_back.getWidth() + 10);
		button_text_back.setY(button_back.getY());
		group_button_back.addActor(button_text_back);

		Actor button_back_clickArea = new Actor();
		button_back_clickArea.setWidth(button_back.getWidth() + button_text_back.getWidth() + 10);
		button_back_clickArea.setHeight(button_text_back.getHeight());
		button_back_clickArea.setX(button_back.getX());
		button_back_clickArea.setY(button_back.getY());
		button_back_clickArea.addListener(new InputListener(button_back_clickArea) {
			@Override
			public void doAction() {
				character = null;
				gsm.setState(GameStateManager.State.MENU);
			}
		});
		group_button_back.addActor(button_back_clickArea);

		stage.addActor(group_button_back);
	}

	private void loadPlayNowButton() {
		// if layout() is used, the inputlistener x, y field becomes relative to the actor position
		image_button_play = res.getTexture("play-now");
		button_play_now = new Actor() {
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(image_button_play, button_play_now.getX(), button_play_now.getY(),
						button_play_now.getWidth(), button_play_now.getHeight());
			}
		};
		button_play_now.setWidth(512);
		button_play_now.setBounds((Betrayal.WIDTH - button_play_now.getWidth()) / 2, 20, 512, 144);
		button_play_now.addListener(new InputListener(button_play_now) {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				image_button_play = res.getTexture("play-now-pressed");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (x >= button_play_now.getX()
						&& x <= button_play_now.getX() + button_play_now.getWidth()
						&& y >= button_play_now.getY()
						&& y <= button_play_now.getY() + button_play_now.getHeight()) {
					character.setName(field_usernameEnter.getText());
					player.addCharacter(character);
					gsm.setState(GameStateManager.State.LOBBY);
				} else image_button_play = res.getTexture("play-now");
			}

			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				image_button_play = res.getTexture("play-now");
			}
		});
		stage.addActor(button_play_now);
	}

	private void loadUsernameField() {
		// Username "Name:" text
		field_usernameLabel = new Label("Name: ", Betrayal.getFont(60));
		field_usernameLabel.setX(10);
		field_usernameLabel.setY(button_back.getY() - button_back.getHeight() - 10);
		stage.addActor(field_usernameLabel);

		// Username input text field
		TextField.TextFieldStyle tfs = new TextField.TextFieldStyle();
		tfs.font = Betrayal.getFont(60).font;
		tfs.messageFont = tfs.font;
		tfs.fontColor = Color.LIGHT_GRAY;
		tfs.messageFontColor = Color.GRAY;
		field_usernameEnter = new TextField("", tfs);
		field_usernameEnter.setMessageText("Enter name here");
		field_usernameEnter.setBounds(10 + field_usernameLabel.getWidth() + 10, field_usernameLabel.getY(),
				Betrayal.WIDTH - 20 - field_usernameLabel.getWidth(), field_usernameLabel.getHeight());
		field_usernameEnter.setMaxLength(10);
		stage.addActor(field_usernameEnter);

		// Removes keyboard focus if tap isn't on a TextField
		stage.getRoot().addCaptureListener(new InputListener(field_usernameEnter) {
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
		loadReferenceActor();
		loadPreview();
	}

	private void loadPreviewFrame() {
		field_framePreview = new Image();
		field_framePreview.setWidth(384);
		field_framePreview.setHeight(576);
		field_framePreview.setX(10);
		field_framePreview.setY(field_usernameLabel.getY() - field_usernameLabel.getHeight()
				- field_framePreview.getHeight());
		stage.addActor(field_framePreview);
	}

	private void loadPreviewRotators() {
		int gap = 60, padding = 30;
		Group group_previewRotators = new Group();

		Image previewRotators_leftArrow = new Image(image_leftArrow);
		previewRotators_leftArrow.setHeight(60);
		previewRotators_leftArrow.setWidth((field_framePreview.getWidth() - gap - padding * 2) / 2);
		previewRotators_leftArrow.setX(field_framePreview.getX() + padding);
		previewRotators_leftArrow.setY(field_framePreview.getY()
				- previewRotators_leftArrow.getHeight() - 10);
		previewRotators_leftArrow.addListener(new InputListener(previewRotators_leftArrow) {
			@Override
			public void doAction() {
				character.getPreview().rotateLeft();
			}
		});
		group_previewRotators.addActor(previewRotators_leftArrow);

		Image previewRotators_rightArrow = new Image(image_rightArrow);
		previewRotators_rightArrow.setHeight(previewRotators_leftArrow.getHeight());
		previewRotators_rightArrow.setWidth(previewRotators_leftArrow.getWidth());
		previewRotators_rightArrow.setX(previewRotators_leftArrow.getX()
				+ previewRotators_leftArrow.getWidth() + gap);
		previewRotators_rightArrow.setY(previewRotators_leftArrow.getY());
		group_previewRotators.addActor(previewRotators_rightArrow);
		previewRotators_rightArrow.addListener(new InputListener(previewRotators_rightArrow) {
			@Override
			public void doAction() {
				character.getPreview().rotateRight();
			}
		});
		stage.addActor(group_previewRotators);
	}

	private void loadReferenceActor() {
		reference = new Actor();
		reference.setX(field_framePreview.getX() + field_framePreview.getWidth() + 20);
		reference.setY(field_framePreview.getY() + field_framePreview.getHeight() + 30);
		reference.setWidth(Betrayal.WIDTH - reference.getX() - 20);
		reference.setHeight(60);
	}

	private void loadPreview() {
		Actor field_preview = new Actor() {
			public void draw(Batch batch, float parentAlpha) {
				character.getPreview().drawPreview(batch, field_framePreview.getX(),
						field_framePreview.getY(), field_framePreview.getWidth(),
						field_framePreview.getHeight());
			}
		};
		field_preview.setWidth(384);
		field_preview.setHeight(576);
		field_preview.setX(field_framePreview.getX());
		field_preview.setY(field_framePreview.getY());
		stage.addActor(field_preview);
	}

	private void loadJobDescription() {
		label_jobDescription = new Label("", Betrayal.getFont(45));
		label_jobDescription.setHeight(field_framePreview.getY() - 80
				- label_jobDescription.getHeight()
				- (button_play_now.getY() + button_play_now.getHeight() + 10));
		label_jobDescription.setWidth(Betrayal.WIDTH - 20);
		label_jobDescription.setX(field_framePreview.getX());
		label_jobDescription.setY(field_framePreview.getY() - 80 - label_jobDescription.getHeight());
		label_jobDescription.setAlignment(Align.topLeft);
		stage.addActor(label_jobDescription);
	}

	private void updateTraits() {
		gender.update();
		hairStyle.update();
		hairColor.update();
		job.update();
	}

	public void updateJobDescription(Label label) {
		switch (character.getJob().getJob()) {
			case Job.WARRIOR:
				label.setText(JobDescription.getWarriorDescription());
				break;
			case Job.KNIGHT:
				label.setText(JobDescription.getKnightDescription());
				break;
			case Job.PRIEST:
				label.setText(JobDescription.getPriestDescription());
				break;
			case Job.THIEF:
				label.setText(JobDescription.getThiefDescription());
				break;
			default:
				break;
		}
	}

	// Classes
	private class SelectionField {

		private Character.Trait trait;

		private Group group_selectionField;
		private Label field_selection_label, field_selection_serialNumber;
		private Image field_selection_leftArrow, field_selection_rightArrow;

		/**
		 * Creates selection field based off the position of the last selection field created
		 *
		 * @param label name of character trait to be edited
		 * @param t     specifies character trait to be edited
		 */
		public SelectionField(String label, Character.Trait t) {
			this.trait = t;
			group_selectionField = new Group();

			// Create label
			field_selection_label = new Label(label, Betrayal.getFont(60));
			field_selection_label.setWidth(Betrayal.WIDTH - reference.getX() - 20);
			field_selection_label.setHeight(60);
			field_selection_label.setX(field_framePreview.getX()
					+ field_framePreview.getWidth() + 20);
			field_selection_label.setY(reference.getY() -
					field_selection_label.getHeight() - 30);
			field_selection_label.setAlignment(Align.center);
			group_selectionField.addActor(field_selection_label);

			// Create left arrow
			field_selection_leftArrow = new Image(image_leftArrow);
			field_selection_leftArrow.setWidth((Betrayal.WIDTH - 160
					- field_framePreview.getWidth() -
					field_framePreview.getX()) / 2);
			field_selection_leftArrow.setHeight(field_selection_label.getHeight());
			field_selection_leftArrow.setX(field_selection_label.getX());
			field_selection_leftArrow.setY(field_selection_label.getY() - 10
					- field_selection_leftArrow.getHeight());
			reference = field_selection_leftArrow;
			field_selection_leftArrow.addListener(new InputListener(field_selection_leftArrow) {
				@Override
				public void doAction() {
					setPreviousTrait(trait);
				}
			});
			group_selectionField.addActor(field_selection_leftArrow);

			// Create serial number
			field_selection_serialNumber = new Label("", Betrayal.getFont(60));
			field_selection_serialNumber.setBounds(field_selection_leftArrow.getX()
							+ field_selection_leftArrow.getWidth() + 30, field_selection_leftArrow.getY(),
					60, field_selection_leftArrow.getHeight());
			field_selection_serialNumber.setAlignment(Align.center);
			group_selectionField.addActor(field_selection_serialNumber);

			// Create right arrow
			field_selection_rightArrow = new Image(image_rightArrow);
			field_selection_rightArrow.setBounds(field_selection_serialNumber.getX() +
							field_selection_serialNumber.getWidth() + 30,
					field_selection_serialNumber.getY(),
					field_selection_leftArrow.getWidth(), field_selection_leftArrow.getHeight());
			field_selection_rightArrow.addListener(new InputListener(field_selection_leftArrow) {
				@Override
				public void doAction() {
					setNextTrait(trait);
				}
			});
			group_selectionField.addActor(field_selection_rightArrow);
		}

		public void update() {
			field_selection_serialNumber.setText(character.getPreview().getTrait(trait));
		}

		// Helpers
		public void addToStage() {
			stage.addActor(group_selectionField);
		}

		private void setPreviousTrait(Character.Trait trait) {
			character.getPreview().setPreviousTrait(trait);
		}

		private void setNextTrait(Character.Trait trait) {
			character.getPreview().setNextTrait(trait);
		}
	}
}
