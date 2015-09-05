/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.character.Rotation;
import com.jnv.betrayal.character.Stats;
import com.jnv.betrayal.gameobjects.Character;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;

public class StatsWindow extends Popup {

	private Image lobbyButton, background;
	private Image [] icons;
	private Label title;
	private Actor mask;
	private Group characterStats;
	private float yRef;
	private Actor charPreview;
	private Character character;

	public StatsWindow(Betrayal game) {
		super(game);
		icons = new Image [3];
		characterStats = new Group();
		character = game.getPlayer().getCurrentCharacter();
		loadButtons();
	}

	private void loadContent() {
		int rotatorIndent = 20;
		loadCharacterStats();
		loadCharacterPreview();

		characterStats.addActor(character.getPreview().createRotators(charPreview.getX() + rotatorIndent,
				charPreview.getY() - 20, (charPreview.getWidth() - (rotatorIndent * 2 + 30)) / 2, 30));
	}

	// Helpers
	private void loadButtons() {
		loadMask();
		loadBackground();
		loadTitle();
		loadContent();
		loadIcons();
		loadApplyButton();
		loadCharacterStats();
		loadReturnToLobbyButton();
		stage.addActor(characterStats);
	}

	private void loadMask() {
		mask = new Actor();
		mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		mask.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				removeStats();
			}
		});
		stage.addActor(mask);
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		stage.addActor(background);
	}

	private void loadTitle() {
		title = new Label("My Stats", FontManager.getFont(40));
		title.setHeight(120);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 200);
		stage.addActor(title);
	}

	private void loadIcons(){
		// Health
		icons[0] = new Image(res.getTexture("health"));
		icons[0].layout();
		icons[0].setBounds(110,Betrayal.HEIGHT-380,40,40);
		stage.addActor(icons[0]);
		// Defense
		icons[1] = new Image(res.getTexture("defense"));
		icons[1].layout();
		icons[1].setBounds(110, Betrayal.HEIGHT-470, 40, 40);
		stage.addActor(icons[1]);
		// Attack
		icons[2] = new Image(res.getTexture("attack"));
		icons[2].layout();
		icons[2].setBounds(110, Betrayal.HEIGHT - 560, 40, 40);
		stage.addActor(icons[2]);
	}

	private void loadCharacterStats() {
		yRef = title.getY();

		characterStatsLabel(characterStats, Stats.Stat.FLOOR, yRef);
		characterStatsLabel(characterStats, Stats.Stat.HEALTH, yRef);
		characterStatsLabel(characterStats, Stats.Stat.DEFENSE, yRef);
		characterStatsLabel(characterStats, Stats.Stat.ATTACK, yRef);
	}

	private void loadApplyButton(){

	}

	private void loadReturnToLobbyButton() {
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 110, 312, 100);
		lobbyButton.addListener(new com.jnv.betrayal.inputprocessors.InputListener(lobbyButton) {
			@Override
			public void doAction() {
				removeStats();
			}
		});
		stage.addActor(lobbyButton);
	}

	private void removeStats() {
		mask.remove();
		title.remove();
		background.remove();
		lobbyButton.remove();
		characterStats.remove();
		for (int i=0; i<3; i++){icons[i].remove();}
	}

	private void characterStatsLabel(Group group, Stats.Stat stat, float yReference) {
		int fontSize = 40;
		Label statsText = new Label("", FontManager.getFont(fontSize));
		statsText.setText(character.getStats().toString(stat));
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
		character.getPreview().setRotation(Rotation.FRONT);
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
		character.getPreview().drawPreview(batch, charPreview.getX(), charPreview.getY(),
				charPreview.getWidth(), charPreview.getHeight());
	}
}
