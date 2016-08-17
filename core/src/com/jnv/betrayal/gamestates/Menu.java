/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Instructions;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.popup.OpeningInstructions;
import com.jnv.betrayal.popup.Options;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.resources.SavedDataHandler;
import com.jnv.betrayal.scene2d.InputListener;

public class Menu extends GameState {

	public Menu(GameStateManager gsm) {
		super(gsm);
		gsm.game.savedDataHandler.load();
		loadMenuButtons();
		firstTimeOpenPopUps();
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
	private void loadMenuButtons() {
		loadBackground();
		loadNewGameButton();
		loadLoadGameButton();
		loadInstructionsButton();
		loadHallOfFameButton();
		loadAboutButton();
		//loadOptionsButton();
	}

	private void firstTimeOpenPopUps() {
		if (game.savedDataHandler.retreiveOpenFirstTime()) {
			new OKPopup(game, "Welcome to Betrayal") {
				@Override
				public void onConfirm() {
					new OpeningInstructions(game);
				}
			};
		}
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("start-background")) {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.disableBlending();
				super.draw(batch, parentAlpha);
				batch.enableBlending();
			}
		};
		background.setBounds(0, 0, 720, 1280);
		stage.addActor(background);
	}

	private void loadNewGameButton() {
		final Image button_newGame = new Image(res.getTexture("new-game"));
		button_newGame.layout();
		button_newGame.setBounds((Betrayal.WIDTH - button_newGame.getImageWidth()) / 2,
				800, 512, 144);
		button_newGame.addListener(new InputListener(button_newGame, true) {
			@Override
			public void doAction() {
				if (game.characters.size() < 4)
					gsm.setState(GameStateManager.State.CHARACTER_SELECTION);
				else // if (players.character.size() == 4)
					displayCharactersFullDialog();
			}
		});
		stage.addActor(button_newGame);
	}

	private void loadLoadGameButton() {
		Actor loadGameButton = new Actor() {
			public void draw(Batch batch, float parentAlpha) {
				if (!game.characters.isEmpty()) {
					batch.draw(res.getTexture("load-game"),
							(Betrayal.WIDTH - res.getTexture("load-game").getWidth()) / 2,
							600, 512, 144);
				} else {
					batch.draw(res.getTexture("load-game-grey"),
							(Betrayal.WIDTH - res.getTexture("load-game-grey").getWidth()) / 2,
							600, 512, 144);
				}
			}
		};
		loadGameButton.setBounds((Betrayal.WIDTH - res.getTexture("load-game").getWidth()) / 2,
				600, 512, 144);
		if (game.characters.isEmpty()) loadGameButton.setTouchable(Touchable.disabled);

		loadGameButton.addListener(new InputListener(loadGameButton, true) {
			@Override
			public void doAction() {
				gsm.setState(GameStateManager.State.LOAD_GAME);
			}
		});
		stage.addActor(loadGameButton);
	}

	private void loadInstructionsButton() {
		Image button_instructions = new Image(res.getTexture("instructions"));
		button_instructions.layout();
		button_instructions.setBounds((Betrayal.WIDTH - button_instructions.getImageWidth()) / 2,
				400, 512, 144);
		button_instructions.addListener(new InputListener(button_instructions, true) {
			@Override
			public void doAction() {
				new Instructions(game);
			}
		});
		stage.addActor(button_instructions);
	}

//	private void loadOptionsButton() {
//		Image button_options = new Image(res.getTexture("options"));
//		button_options.layout();
//		button_options.setBounds((Betrayal.WIDTH - button_options.getImageWidth() / 2) / 2,
//				272, 256, 72);
//		button_options.addListener(new InputListener(button_options) {
//			@Override
//			public void doAction() {
//				new Options(game);
//			}
//		});
//		stage.addActor(button_options);
//	}

	private void loadAboutButton() {
		Image aboutButton = new Image(res.getTexture("about-button"));
		aboutButton.layout();
		aboutButton.setBounds((Betrayal.WIDTH - aboutButton.getImageWidth() / 2) / 2,
				272, 256, 72);
		aboutButton.addListener(new InputListener(aboutButton, true) {
			@Override
			public void doAction() {
				new OKPopup(game,"Developers: \nJoseph Phan\nVincent Wang");
				game.savedDataHandler.load();
			}
		});
		stage.addActor(aboutButton);
	}

	private void loadHallOfFameButton() {
		Image button_hallOfFame = new Image(res.getTexture("hall-of-fame"));
		button_hallOfFame.layout();
		button_hallOfFame.setBounds((Betrayal.WIDTH - button_hallOfFame.getImageWidth() / 2) / 2,
				172, 256, 72);
		button_hallOfFame.addListener(new InputListener(button_hallOfFame, true) {
			@Override
			public void doAction() {
				gsm.setState(GameStateManager.State.HALL_OF_FAME);
			}
		});
		stage.addActor(button_hallOfFame);
	}

	private void displayCharactersFullDialog() {
		Group dialog_characterSlotsFull = new Group();
		Label dialog_text = new Label("Character slots are full. " +
				"\nPlease delete a character slot.", FontManager.getFont45());
		dialog_text.setBounds((Betrayal.WIDTH - dialog_text.getPrefWidth()) / 2,
				(Betrayal.HEIGHT - dialog_text.getPrefHeight()) / 2, dialog_text.getPrefWidth(),
				dialog_text.getPrefHeight());
		dialog_text.setAlignment(Align.center);

		Image dialog_background = new Image(res.getTexture("confirmation-background"));
		dialog_background.setBounds((Betrayal.WIDTH - dialog_text.getWidth() - 40) / 2,
				(Betrayal.HEIGHT - dialog_text.getHeight() - 40) / 2, dialog_text.getPrefWidth() + 40,
				dialog_text.getPrefHeight() + 40);

		dialog_characterSlotsFull.addActor(dialog_background);
		dialog_characterSlotsFull.addActor(dialog_text);
		stage.addActor(dialog_characterSlotsFull);
		createMask(dialog_characterSlotsFull);
	}

	/**
	 * Creates a mask for a popup. When the mask is touched,
	 * the mask disappears along with the actor
	 *
	 * @param actor popup to be assigned with mask
	 */
	private void createMask(final Actor actor) {
		final Actor mask = new Actor();
		mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		mask.addListener(new InputListener(mask) {
			@Override
			public void doAction() {
				mask.remove();
				actor.remove();
			}
		});
		stage.addActor(mask);
	}
}
