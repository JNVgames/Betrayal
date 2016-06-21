/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.utils.Trait;
import com.jnv.betrayal.main.Betrayal;
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

	private Actor button_play_now;
	private Dimension reference;
	private Image button_back, field_framePreview;
	private Character character;
	private TextureRegion image_leftArrow, image_rightArrow;
	private Label.LabelStyle font60, font45;

	public CharacterSelection(GameStateManager gsm) {
		super(gsm);

		image_leftArrow = new TextureRegion(res.getTexture("arrow-right"));
		image_leftArrow.flip(true, false);
		image_rightArrow = new TextureRegion(res.getTexture("arrow-right"));
		font60 = FontManager.getFont(60);
		font45 = FontManager.getFont(45);

		character = new Character(gsm.game.getPlayer(), res);

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
		button_back = new Image(image_leftArrow);
		button_back.setHeight(60);
		button_back.setWidth(80);
		button_back.setX(10);
		button_back.setY(Betrayal.HEIGHT - button_back.getHeight() - 10);
		stage.addActor(button_back);

		Actor actor = new Label("Back", font60);
		actor.setX(button_back.getX() + button_back.getWidth() + 10);
		actor.setY(button_back.getY());
		stage.addActor(actor);
		Dimension ref = new Dimension(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());

		actor = new Actor();
		actor.setWidth(button_back.getWidth() + ref.getWidth() + 10);
		actor.setHeight(ref.getHeight());
		actor.setX(button_back.getX());
		actor.setY(button_back.getY());
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
		button_play_now = new Button(skin.getDrawable("play-now"), skin.getDrawable("play-now-pressed"));
		button_play_now.setWidth(512);
		button_play_now.setBounds((Betrayal.WIDTH - button_play_now.getWidth()) / 2, 20, 512, 144);
		button_play_now.addListener(new InputListener(button_play_now) {
			@Override
			public void doAction() {
				player.addCharacter(character);
				player.setCurrentCharacterIndex(player.characters.indexOf(character));
				gsm.setState(GameStateManager.State.LOBBY);
			}
		});
		stage.addActor(button_play_now);
	}

	private void loadImagePreview() {
		loadPreviewFrame();
		loadReference();
		loadPreview();
	}

	private void loadPreviewFrame() {
		field_framePreview = new Image();
		field_framePreview.setWidth(384);
		field_framePreview.setHeight(576);
		field_framePreview.setX(10);
		field_framePreview.setY(button_back.getY() - 71 - field_framePreview.getHeight());
		stage.addActor(field_framePreview);
	}

	private void loadPreviewRotators() {
		int gap = 60, padding = 30;

		Actor actor = new Image(image_leftArrow);
		actor.setHeight(60);
		actor.setWidth((field_framePreview.getWidth() - gap - padding * 2) / 2);
		actor.setX(field_framePreview.getX() + padding);
		actor.setY(field_framePreview.getY()
				- actor.getHeight() - 10);
		actor.addListener(new InputListener(actor) {
			@Override
			public void doAction() {
				character.preview.rotateLeft();
			}
		});
		stage.addActor(actor);
		Dimension ref = new Dimension(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());

		actor = new Image(image_rightArrow);
		actor.setHeight(ref.getHeight());
		actor.setWidth(ref.getWidth());
		actor.setX(ref.getRightX() + gap);
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
		reference.setX(field_framePreview.getRight() + 20);
		reference.setY(field_framePreview.getTop() + 30);
		reference.setWidth(Betrayal.WIDTH - reference.getX() - 20);
		reference.setHeight(60);
	}

	private void loadPreview() {
		Actor field_preview = new Actor() {
			public void draw(Batch batch, float parentAlpha) {
				character.preview.drawPreview(batch, field_framePreview.getX(),
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
		Label jobDescription = new Label("", font45) {
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
		jobDescription.setHeight(field_framePreview.getY() - 80
				- jobDescription.getHeight()
				- (button_play_now.getTop() + 10));
		jobDescription.setWidth(Betrayal.WIDTH - 20);
		jobDescription.setX(field_framePreview.getX());
		jobDescription.setY(field_framePreview.getY() - 80 - jobDescription.getHeight());
		jobDescription.setAlignment(Align.topLeft);
		stage.addActor(jobDescription);
	}

	public void createSelectionField(String label, final Trait trait) {
		// Create dimension reference
		Dimension dimRef = new Dimension();

		// Create label
		Actor actor = new Label(label, font60);
		actor.setBounds(field_framePreview.getRight() + 20, reference.getY() -
				actor.getHeight() - 30, Betrayal.WIDTH - reference.getX() - 20, 60);
		((Label) actor).setAlignment(Align.center);
		stage.addActor(actor);
		dimRef.setBounds(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());

		// Create left arrow
		actor = new Image(image_leftArrow);
		actor.setBounds(dimRef.getX(),
				dimRef.getY() - 10 - dimRef.getHeight(),
				(Betrayal.WIDTH - 160 - field_framePreview.getRight()) / 2,
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
		actor = new Label("", font60) {
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
		actor = new Image(image_rightArrow);
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
