/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {

	public final List<Card> defenders = new ArrayList<Card>();
	protected Field field;
	protected int baseHealth, baseAttack, baseDefense, deltaHealth, deltaAttack, deltaDefense;
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
		return baseHealth + deltaHealth;
	}

	public int getFinalAttack() {
		return baseAttack + deltaAttack;
	}

	public int getFinalDefense() {
		return baseDefense + deltaDefense;
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
}
