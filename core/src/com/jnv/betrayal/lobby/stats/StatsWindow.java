/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.stats;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.utils.Rotation;
import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class StatsWindow extends Popup {

	private Image lobbyButton, applyButton, background;
	private Image [] icons, statPlusButtons, statMinusButtons;
	private Label title, availablePoints;
	private Group characterStats;
	private float yRef;
	private Actor charPreview;
	private Character character;

	public StatsWindow(Betrayal game) {
		super(game);
		icons = new Image [3];
		statPlusButtons = new Image [3];
		statMinusButtons = new Image [3];
		characterStats = new Group();
		character = game.getPlayer().getCurrentCharacter();
		loadButtons();
	}

	private void loadContent() {
		int rotatorIndent = 20;
		loadCharacterStats();
		loadCharacterPreview();

		characterStats.addActor(character.preview.createRotators(charPreview.getX() + rotatorIndent,
				charPreview.getY() - 20, (charPreview.getWidth() - (rotatorIndent * 2 + 30)) / 2, 30));
	}

	// Helpers
	private void loadButtons() {
		loadBackground();
		loadTitle();
		loadContent();
		loadIcons();
		loadApplyButton();
		loadStatsAdjustButton();
		loadAvailablePoints();
		loadCharacterStats();
		loadReturnToLobbyButton();
		popup.addActor(characterStats);
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		popup.addActor(background);
	}

	private void loadTitle() {
		title = new Label("My Stats", FontManager.getFont(40));
		title.setHeight(120);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 200);
		popup.addActor(title);
	}

	private void loadIcons(){
		// Health
		icons[0] = new Image(res.getTexture("health"));
		icons[0].layout();
		icons[0].setBounds(110,Betrayal.HEIGHT-380,40,40);
		popup.addActor(icons[0]);
		// Defense
		icons[1] = new Image(res.getTexture("defense"));
		icons[1].layout();
		icons[1].setBounds(110, Betrayal.HEIGHT-470, 40, 40);
		popup.addActor(icons[1]);
		// Attack
		icons[2] = new Image(res.getTexture("attack"));
		icons[2].layout();
		icons[2].setBounds(110, Betrayal.HEIGHT - 560, 40, 40);
		popup.addActor(icons[2]);
	}

	private void loadCharacterStats() {
		yRef = title.getY();

		characterStatsLabel(characterStats, Stat.FLOOR, yRef);
		characterStatsLabel(characterStats, Stat.HEALTH, yRef);
		characterStatsLabel(characterStats, Stat.DEFENSE, yRef);
		characterStatsLabel(characterStats, Stat.ATTACK, yRef);
	}

	private void loadApplyButton(){
		applyButton = new Image(res.getTexture("apply"));
		applyButton.layout();
		applyButton.setBounds(460, 600, 150, 75);
		applyButton.addListener(new InputListener(applyButton) {
			@Override
			public void doAction() {
				new Confirmation(game, "Stats Change Confirmation") {
					public void doSomething() {
						//TODO function to change stats
					}
				};
			}
		});
		popup.addActor(applyButton);
	}

	private void loadReturnToLobbyButton() {
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 110, 312, 100);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadStatsAdjustButton(){
		for(int i = 0; i<3; i++) {
			statPlusButtons[i] = new Image(res.getTexture("plus"));
			statPlusButtons[i].layout();
			statPlusButtons[i].setBounds(420, Betrayal.HEIGHT-380-90*i, 50, 50);
			statPlusButtons[i].addListener(new InputListener(statPlusButtons[i]) {
				@Override
				public void doAction() {
					//TODO: check available points if >0 then allow to press
				}
			});
			popup.addActor(statPlusButtons[i]);

			statMinusButtons[i] = new Image(res.getTexture("minus"));
			statMinusButtons[i].layout();
			statMinusButtons[i].setBounds(520, Betrayal.HEIGHT-380-90*i, 50, 50);
			statMinusButtons[i].addListener(new InputListener(statMinusButtons[i]) {
				@Override
				public void doAction() {

				}
			});
			popup.addActor(statMinusButtons[i]);
		}
	}

	private void loadAvailablePoints(){
		availablePoints = new Label("Available Points:", FontManager.getFont(40));
		availablePoints.setX(Betrayal.WIDTH/2-150);
		availablePoints.setY(Betrayal.HEIGHT - 225);
		popup.addActor(availablePoints);
	}

	private void characterStatsLabel(Group group, Stat stat, float yReference) {
		int fontSize = 40;
		Label statsText = new Label("", FontManager.getFont(fontSize));
		statsText.setText(character.stats.toString(stat));
		statsText.layout();
		statsText.setX(background.getX() + 60);
		statsText.setY(yReference - fontSize - 50);
		yRef = statsText.getY();
		statsText.setWidth(statsText.getPrefWidth());
		statsText.setHeight(fontSize);
		group.addActor(statsText);
	}

	private void loadCharacterPreview() {
		int scale = 8;
		character.preview.setRotation(Rotation.FRONT);
		charPreview = new Actor() {
			public void draw(Batch batch, float parentAlpha) {
				drawPreview(batch);
			}
		};
		charPreview.setBounds(background.getX() + (background.getWidth() - 32 * scale) / 2,
				yRef - 48 * scale - 25,
				32 * scale, 48 * scale);
		characterStats.addActor(charPreview);
	}

	private void drawPreview(Batch batch) {
		character.preview.drawPreview(batch, charPreview.getX(), charPreview.getY(),
				charPreview.getWidth(), charPreview.getHeight());
	}
}
