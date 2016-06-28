/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {

	public final List<Card> defenders = new ArrayList<Card>();
	protected Field field;
	protected int baseHealth, baseAttack, baseDefense, currentHealth, currentAttack, currentDefense;
	protected HealthBar healthBar;
	protected BetrayalAssetManager res;
	protected Actor cardImage;
	protected Group group;
	private TextureRegion selectedTexture, shieldTexture;
	private boolean wasSelected, isSelected, selecting;
	private InputListener selectListener;
	private boolean isTurn;

	protected Card(Dimension dimension, BetrayalAssetManager res) {
		group = new Group() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				super.draw(batch, parentAlpha);
				if (isSelected && selecting) {
					batch.draw(selectedTexture, getX() + (getWidth() - 100) / 2,
							getY() + (getHeight() - 100) / 2, 100, 100);
				}
			}
		};
		group.setBounds(dimension);
		this.res = res;
		initialize();
	}

	protected Card(float x, float y, float width, float height,
				   BetrayalAssetManager res) {
		this(new Dimension(x, y, width, height), res);
	}

	public Actor getCardImage() {
		return cardImage;
	}

	private void initialize() {
		group.addAction(Actions.alpha(0));
		group.addAction(Actions.delay(1, Actions.fadeIn(2)));
		healthBar = new HealthBar(group.getHeight(), res);
		selectedTexture = new TextureRegion(res.getTexture("cross-hair"));
		shieldTexture = new TextureRegion(res.getTexture("defense"));
		group.addActor(healthBar);
		healthBar.toFront();
		isTurn = false;

	}

	/**
	 * Get the health after being affected by buffs and debuffs
	 * @return final health
	 */
	public int getFinalHealth() {
		return baseHealth + currentHealth;
	}

	public int getFinalAttack() {
		return baseAttack + currentAttack;
	}

	public int getFinalDefense() {
		return baseDefense + currentDefense;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public int getBaseAttack() {
		return baseAttack;
	}

	public int getBaseDefense() {
		return baseDefense;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public void beginSelectMode(int numTargets) {
		if (selecting) {
			throw new IllegalStateException("Card.endSelectMode must be called before beginSelectMode");
		}
		selectListener = createSelectListener(numTargets);
		group.addListener(selectListener);
		selecting = true;
	}

	public void endSelectMode() {
		if (!selecting)
			throw new IllegalStateException("Card.beginSelectMode must be called before endSelectMode");
		group.removeListener(selectListener);
		wasSelected = isSelected;
		selecting = false;
	}

	public void cancelSelectMode() {
		isSelected = wasSelected;
		endSelectMode();
	}

	public void reset() {
		isSelected = false;
		wasSelected = false;
	}

	public void select() {
		isSelected = true;
	}

	public void unselect() {
		isSelected = false;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public InputListener createSelectListener(final int numTargets) {
		try {
			return new InputListener(cardImage) {
				@Override
				public void doAction() {
					// If it's selected, unselect
					if (isSelected) unselect();
					else { // if not selected
						// If limit is one, unselect all and select this one
						if (numTargets == 1) {
							field.unselectAll();
							select();
							// If unselected, check if targets selected is less than the limit
						} else { // numTarget > 1
							if (field.getCardsSelected().size() < numTargets) select();
						}
					}
				}
			};
		} catch (NullPointerException e) {
			System.out.println("Card: Actor cardImage was not created properly");
			Gdx.app.exit();
		}
		return null;
	}

	public Group getGroup() {
		return group;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean turn) {
		isTurn = turn;
	}

	public HealthBar getHealthBar() {
		return healthBar;
	}

	public class HealthBar extends Group {

		private Image healthBarBackground, healthBar;
		private final Drawable greenBar, yellowBar, redBar;
		private int currentHealthPercentage, finalHealthPercentage;	//change current % to final %

		public HealthBar(float height, BetrayalAssetManager res) {
			// Calibrate health bar coordinates
			float x = 50;
			float y = height - 25;

			// Initialize health drawables
			greenBar = new TextureRegionDrawable(new TextureRegion(res.getTexture("green-bar")));
			yellowBar = new TextureRegionDrawable(new TextureRegion(res.getTexture("yellow-bar")));
			redBar = new TextureRegionDrawable(new TextureRegion(res.getTexture("red-bar")));

			// Initialize health bar image actors
			healthBarBackground = new Image(res.getTexture("bar-background"));
			healthBar = new Image(greenBar);

			// Health percentage starts at full
			currentHealthPercentage = 100;
			finalHealthPercentage = 100;
			initialize(x, y);

			// Add actors to the group
			addActor(healthBarBackground);
			addActor(healthBar);
		}
		public void takeDamage(int damage){
			currentHealth-=damage;
			setNewHealthPercent(currentHealth*100/baseHealth);
		}

		public void heal(int healAmount){
			currentHealth+=healAmount;
			setNewHealthPercent(currentHealth*100/baseHealth);
		}

		private void initialize(float x, float y) {
			healthBarBackground.setBounds(x, y, 227, 25);
			//healthBarBackground.addAction(Actions.alpha(0));
			//healthBarBackground.addAction(Actions.delay(1, Actions.fadeIn(2)));
			healthBar.setBounds(x + 10, y + 8, 200, 8);
			//healthBar.addAction(Actions.alpha(0));
			//healthBar.addAction(Actions.delay(1, Actions.fadeIn(2)));
		}

		private void setNewHealthPercent(int newHealthPercent) {
			if (newHealthPercent > 100) newHealthPercent = 100;
			if (newHealthPercent < 0) newHealthPercent = 0;

			System.out.println(newHealthPercent);
			//LOSING HEALTH
			if (newHealthPercent <= 70 && currentHealthPercentage > 70) {
				System.out.println("Green to yellow");
				//Green to Yellow
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(70 * 2, 8, 0.5f)));
				Runnable r = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(yellowBar);
					}
				};
				healthBar.addAction(Actions.delay(1f, Actions.run(r)));
				healthBar.addAction(Actions.delay(1f, Actions.sizeTo(newHealthPercent * 2, 8, 0.5f)));
			} else if (newHealthPercent <= 30 && currentHealthPercentage > 70) {
				//Green to Red
				healthBar.addAction(Actions.delay(0.3f, Actions.sizeTo(70 * 2, 8, 0.3f)));
				Runnable r = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(yellowBar);
					}
				};
				healthBar.addAction(Actions.delay(.6f, Actions.run(r)));
				healthBar.addAction(Actions.delay(0.6f, Actions.sizeTo(30 * 2, 8, 0.3f)));
				Runnable s = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(redBar);
					}
				};
				healthBar.addAction(Actions.delay(0.9f, Actions.run(s)));

				healthBar.addAction(Actions.delay(0.9f, Actions.sizeTo(newHealthPercent * 2, 8, 0.3f)));
			} else if (newHealthPercent <= 30 && currentHealthPercentage <= 70 && currentHealthPercentage > 30) {
				//Yellow to Red
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(30 * 2, 8, 0.5f)));
				Runnable r = new Runnable() {
				@Override
				public void run() {
					healthBar.setDrawable(redBar);
				}
			};
			healthBar.addAction(Actions.delay(1f, Actions.run(r)));
				healthBar.addAction(Actions.delay(1f, Actions.sizeTo(newHealthPercent * 2, 8, 0.5f)));
			} else if (newHealthPercent > 30 && newHealthPercent <= 70 && currentHealthPercentage <= 30) {
				//Red to yellow
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(30 * 2, 8, 0.5f)));
				Runnable r = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(yellowBar);
					}
				};
				healthBar.addAction(Actions.delay(1f, Actions.run(r)));
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(newHealthPercent * 2, 8, 0.5f)));
			} else if (newHealthPercent > 70 && currentHealthPercentage <= 30) {
				//Red to Green
				healthBar.addAction(Actions.delay(0.3f, Actions.sizeTo(30 * 2, 8, 0.3f)));
				Runnable r = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(yellowBar);
					}
				};
				healthBar.addAction(Actions.delay(0.6f, Actions.run(r)));
				healthBar.addAction(Actions.delay(0.6f, Actions.sizeTo(70 * 2, 8, 0.3f)));
				Runnable s = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(greenBar);
					}
				};
				healthBar.addAction(Actions.delay(0.9f, Actions.run(s)));
				healthBar.addAction(Actions.delay(0.9f, Actions.sizeTo(newHealthPercent * 2, 8, 0.3f)));
			} else if (newHealthPercent > 70 && currentHealthPercentage > 30 && currentHealthPercentage < 70) {
				//Yellow to Green
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(70 * 2, 8, 0.5f)));
				Runnable r = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(greenBar);
					}
				};
				healthBar.addAction(Actions.delay(1f, Actions.run(r)));
				healthBar.addAction(Actions.delay(1f, Actions.sizeTo(newHealthPercent * 2, 8, 0.5f)));
			} else {
				//stays in same color range
				healthBar.addAction(Actions.delay(1.0f, Actions.sizeTo(newHealthPercent * 2, 8, 1f)));
			}
			currentHealthPercentage = newHealthPercent;
		}
	}
}
