package com.jnv.betrayal.lobby.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.Stats;
import com.jnv.betrayal.character.utils.Rotation;
import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.resources.WhiteBoxOutline;
import com.jnv.betrayal.scene2d.InputListener;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class StatsWindow extends Popup {

	private Image lobbyButton, applyButton, background;
	private Image[] icons, statPlusButtons, statMinusButtons;
	private Label title, availablePoints, floorLabel, healthLabel, attackLabel, defenseLabel, classLabel;
	private Label equipHealthLabel, equipAttackLabel, equipDefenseLabel, hair;
	private Group characterStats;
	private float yRef;
	private Actor charPreview;
	private Character character;
	private Stats.ApplyPoints applyPoints;

	public StatsWindow(Betrayal game) {
		super(game);
		icons = new Image[3];
		statPlusButtons = new Image[3];
		statMinusButtons = new Image[3];
		characterStats = new Group();
		character = game.getCurrentCharacter();
		applyPoints = character.stats.getApplyPointsObject();
		loadButtons();
		loadHairButton();
		updateStatValues();
	}

	public void loadHairButton() {
		if (!character.equips.isHeadSlotEmpty()) {
			final String showHairText = "Show\nhair";
			final String showHelmetText = "Show\nhelmet";
			hair = new Label(character.preview.isShowingHead() ? showHelmetText : showHairText, FontManager.getFont40());
			hair.setX(120);
			hair.setY(Betrayal.HEIGHT - 800);
			hair.addListener(new InputListener(title) {
				@Override
				public void doAction() {
					character.preview.setShowHead(!character.preview.isShowingHead());
					hair.setText(character.preview.isShowingHead() ? showHelmetText : showHairText);
				}
			});
			Group group = new Group();
			popup.addActor(group);
			new WhiteBoxOutline(game, group, hair.getPrefWidth() + 15, hair.getPrefHeight() + 8, 3, hair.getX() - 7, hair.getY() - 7);
			popup.addActor(hair);
		}
	}

	@Override
	public void remove() {
		super.remove();
		applyPoints.cancel();
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
		title = new Label("My Stats", FontManager.getFont40());
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(background.getTop() - title.getPrefHeight() - 10);
		popup.addActor(title);

		com.jnv.betrayal.scene2d.ui.Image whiteLine = new com.jnv.betrayal.scene2d.ui.Image(res.getTexture("white-pixel"));
		whiteLine.setWidth(title.getWidth()+20);
		whiteLine.setHeight(2);
		whiteLine.setX(title.getX()-10);
		whiteLine.setY(title.getY() - whiteLine.getHeight());
		popup.addActor(whiteLine);
	}

	private void loadIcons() {
		// Health
		icons[0] = new Image(res.getTexture("health"));
		icons[0].layout();
		icons[0].setBounds(110, Betrayal.HEIGHT - 380, 40, 40);
		popup.addActor(icons[0]);
		// Defense
		icons[1] = new Image(res.getTexture("attack"));
		icons[1].layout();
		icons[1].setBounds(110, Betrayal.HEIGHT - 470, 40, 40);
		popup.addActor(icons[1]);
		// Attack
		icons[2] = new Image(res.getTexture("defense"));
		icons[2].layout();
		icons[2].setBounds(110, Betrayal.HEIGHT - 560, 40, 40);
		popup.addActor(icons[2]);
	}

	private void loadCharacterStats() {
		yRef = title.getY();

		// Floor Label
		int fontSize = 40;
		floorLabel = new Label("", FontManager.getFont40());
		character.stats.updateStats();
		equipHealthLabel = new Label(" + " + Integer.toString(character.stats.getEquipHealth()),
				FontManager.getFont40());
		equipAttackLabel = new Label(" + " + Integer.toString(character.stats.getEquipAttack()),
				FontManager.getFont40());
		equipDefenseLabel = new Label(" + " + Integer.toString(character.stats.getEquipDefense()),
				FontManager.getFont40());



		// Get stats from character
		floorLabel.setText(character.stats.toString(Stat.FLOOR));
		floorLabel.setX(background.getX() + 60);
		floorLabel.setY(yRef - fontSize - 50 - 37);
		yRef = floorLabel.getY();
		floorLabel.setWidth(floorLabel.getPrefWidth());
		floorLabel.setHeight(fontSize);
		characterStats.addActor(floorLabel);

		classLabel = new Label("", FontManager.getFont40());
		classLabel.setText("Job: " + character.job.toString());
		classLabel.setX( floorLabel.getX() + floorLabel.getPrefWidth() + 75);
		classLabel.setY(title.getY() - 20 - 50 - 37);
		characterStats.addActor(classLabel);

		// SET HEALTH LABEL INITIALLY
		healthLabel = new Label("", FontManager.getFont40());
		healthLabel.setX(background.getX() + 60);
		healthLabel.setY(yRef - fontSize - 50);
		yRef = healthLabel.getY();
		healthLabel.setWidth(healthLabel.getPrefWidth());
		healthLabel.setHeight(fontSize);
		popup.addActor(healthLabel);

		// SET ATTACK LABEL INITIALLY
		attackLabel = new Label("", FontManager.getFont40());
		attackLabel.setX(background.getX() + 60);
		attackLabel.setY(yRef - fontSize - 50);
		yRef = attackLabel.getY();
		attackLabel.setWidth(attackLabel.getPrefWidth());
		attackLabel.setHeight(fontSize);
		characterStats.addActor(attackLabel);

		// SET DEFENSE LABEL INITIALLY
		defenseLabel = new Label("", FontManager.getFont40());
		defenseLabel.setX(background.getX() + 60);
		defenseLabel.setY(yRef - fontSize - 50);
		yRef = defenseLabel.getY();
		defenseLabel.setWidth(defenseLabel.getPrefWidth());
		defenseLabel.setHeight(fontSize);
		characterStats.addActor(defenseLabel);

		// SET EQUIP HEALTH LABEL
		equipHealthLabel = new Label("", FontManager.getFont40());
		equipHealthLabel.setColor(Color.GREEN);
		equipHealthLabel.setX(healthLabel.getRight());
		equipHealthLabel.setY(healthLabel.getY());
		equipHealthLabel.setWidth(equipHealthLabel.getPrefWidth());
		equipHealthLabel.setHeight(fontSize);
		popup.addActor(equipHealthLabel);

		// SET EQUIP ATTACK LABEL
		equipAttackLabel = new Label("", FontManager.getFont40());
		equipAttackLabel.setColor(Color.GREEN);
		equipAttackLabel.setX(attackLabel.getRight());
		equipAttackLabel.setY(attackLabel.getY());
		equipAttackLabel.setWidth(equipAttackLabel.getPrefWidth());
		equipAttackLabel.setHeight(fontSize);
		characterStats.addActor(equipAttackLabel);

		// SET EQUIP DEFENSE LABEL
		equipDefenseLabel = new Label("", FontManager.getFont40());
		equipDefenseLabel.setColor(Color.GREEN);
		equipDefenseLabel.setX(defenseLabel.getRight());
		equipDefenseLabel.setY(defenseLabel.getY());
		equipDefenseLabel.setWidth(equipDefenseLabel.getPrefWidth());
		equipDefenseLabel.setHeight(fontSize);
		characterStats.addActor(equipDefenseLabel);
	}

	private void loadApplyButton() {
		applyButton = new Image(res.getTexture("apply"));
		applyButton.layout();
		applyButton.setBounds(460, 600, 150, 75);
		applyButton.addListener(new InputListener(applyButton) {
			@Override
			public void doAction() {
				new Confirmation(game, "Are You Sure?") {
					public void doAction() {
						applyPoints.applyPoints();
						character.stats.updateStats();
						character.getRoom().updateServerCharacters();
						game.savedDataHandler.save();
						game.getCurrentCharacter().getRoom().refreshLobby();
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
				applyPoints.cancel();
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadStatsAdjustButton() {
		for (int i = 0; i < 3; i++) {
			final int temp = i;
			statPlusButtons[i] = new Image(res.getTexture("plus"));
			statPlusButtons[i].layout();
			statPlusButtons[i].setBounds(420, Betrayal.HEIGHT - 380 - 90 * i, 50, 50);
			statPlusButtons[i].addListener(new InputListener(statPlusButtons[i]) {
				@Override
				public void doAction() {
					if (applyPoints.hasAvailablePoints()) {
						switch (temp) {
							case 0:
								applyPoints.addHealthPoint();
								break;
							case 1:
								applyPoints.addAttackPoint();
								break;
							case 2:
								applyPoints.addDefensePoint();
								break;
							default:
								throw new AssertionError("STATS WINDOW: not a correct case on +");
						}
						updateStatValues();
					}
				}
			});
			popup.addActor(statPlusButtons[i]);

			statMinusButtons[i] = new Image(res.getTexture("minus"));
			statMinusButtons[i].layout();
			statMinusButtons[i].setBounds(520, Betrayal.HEIGHT - 380 - 90 * i, 50, 50);
			statMinusButtons[i].addListener(new InputListener(statMinusButtons[i]) {
				@Override
				public void doAction() {
					if (applyPoints.hasPointsApplied()) {
						switch (temp) {
							case 0:
								applyPoints.decHealthPoint();
								break;
							case 1:
								applyPoints.decAttackPoint();
								break;
							case 2:
								applyPoints.decDefensePoint();
								break;
							default:
								throw new AssertionError("STATS WINDOW: not a correct case on -");
						}
						updateStatValues();
					}
				}
			});
			popup.addActor(statMinusButtons[i]);
		}
	}

	private void loadAvailablePoints() {
		availablePoints = new Label("Available Points: " + Integer.toString(applyPoints.getAvailablePoints()), FontManager.getFont40());
		availablePoints.setX(Betrayal.WIDTH / 2 - 150);
		availablePoints.setY(Betrayal.HEIGHT - 225);
		popup.addActor(availablePoints);
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

	private void updateStatValues() {
		healthLabel.setText("Health: " + Integer.toString(applyPoints.getHealth()));
		defenseLabel.setText("Defense: " + Integer.toString(applyPoints.getDefense()));
		attackLabel.setText("Attack: " + Integer.toString(applyPoints.getAttack()));
		availablePoints.setText(("Available Points: " + Integer.toString(applyPoints.getAvailablePoints())));
		equipHealthLabel.setText(" + " + Integer.toString(character.stats.getEquipHealth()));
		equipAttackLabel.setText(" + " + Integer.toString(character.stats.getEquipAttack()));
		equipDefenseLabel.setText(" + " + Integer.toString(character.stats.getEquipDefense()));
		updateEquipStatsLabelDimensions();
	}

	private void updateEquipStatsLabelDimensions() {
		healthLabel.setWidth(healthLabel.getPrefWidth());
		attackLabel.setWidth(attackLabel.getPrefWidth());
		defenseLabel.setWidth(defenseLabel.getPrefWidth());

		equipHealthLabel.setX(healthLabel.getRight());
		equipHealthLabel.setY(healthLabel.getY());
		equipHealthLabel.setWidth(equipHealthLabel.getPrefWidth());

		equipAttackLabel.setX(attackLabel.getRight());
		equipAttackLabel.setY(attackLabel.getY());
		equipAttackLabel.setWidth(equipAttackLabel.getPrefWidth());

		equipDefenseLabel.setX(defenseLabel.getRight());
		equipDefenseLabel.setY(defenseLabel.getY());
		equipDefenseLabel.setWidth(equipDefenseLabel.getPrefWidth());
	}
}
