/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jnv.betrayal.dungeon.Field;
import com.jnv.betrayal.dungeon.animations.CardAnimation;
import com.jnv.betrayal.dungeon.effects.Died;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.popup.CardInfo;
import com.jnv.betrayal.dungeon.utils.DungeonCoords;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;
import com.jnv.betrayal.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {

	protected Field field;
	protected int baseHealth, baseAttack, baseDefense, currentHealth, currentAttack, currentDefense;
	protected HealthBar healthBar;
	protected BetrayalAssetManager res;
	protected Actor cardImage;
	protected Label cardName;
	protected Group group;
	private List<Card> defenders = new ArrayList<Card>();
	private TextureRegion selectedTexture;
	private boolean isSelected, selecting;
	private InputListener selectListener, cardInfoListener;
	private Image statusIcon;

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
		group.setBounds(dimension.getX(), dimension.getY(), dimension.getWidth(), dimension.getHeight());
		this.res = res;
		initialize();
	}

	protected Card(float x, float y, float width, float height,
				   BetrayalAssetManager res) {
		this(new Dimension(x, y, width, height), res);
	}

	private void initialize() {
		group.addAction(Actions.alpha(0));
		group.addAction(Actions.delay(1, Actions.fadeIn(2)));
		healthBar = new HealthBar(group.getHeight(), res);
		selectedTexture = new TextureRegion(res.getTexture("cross-hair"));
		group.addActor(healthBar);
		healthBar.toFront();

		statusIcon = new Image();
		statusIcon.setBounds(DungeonCoords.PLAYER_WIDTH,
				(DungeonCoords.PLAYER_HEIGHT - healthBar.getBackgroundHeight()) / 2 - 50, 100, 100);
		group.addActor(statusIcon);
	}

	public Label getCardName() {
		return cardName;
	}

	public Actor getCardImage() {
		return cardImage;
	}

	public Image getStatusIcon() {
		return statusIcon;
	}

	protected void initializeCardListener() {
		cardInfoListener = new InputListener(cardImage) {
			@Override
			public void doAction() {
				new CardInfo(field.game, Card.this);
			}
		};
		group.addListener(cardInfoListener);
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

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getCurrentAttack() {
		return currentAttack;
	}

	public int getCurrentDefense() {
		return currentDefense;
	}

	public void increaseCurrentAttack(float value) {
		currentAttack += value;
	}

	public void decreaseCurrentAttack(float value) {
		currentAttack -= value;
	}

	public void increaseCurrentDefense(float value) {
		currentDefense += value;
	}

	public void decreaseCurrentDefense(float value) {
		currentDefense -= value;
	}

	public void heal(float value) {
		currentHealth += value;
		if (currentHealth > baseHealth) {
			currentHealth = baseHealth;
		}
		healthBar.setNewHealthPercent(currentHealth * 100 / baseHealth);
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Field getField() {
		return field;
	}

	public void beginSelectMode(int numTargets) {
		if (selecting) {
			throw new IllegalStateException("Card.endSelectMode must be called before beginSelectMode");
		}
		selectListener = createSelectListener(numTargets);
		group.addListener(selectListener);
		group.removeListener(cardInfoListener);
		isSelected = false;
		selecting = true;
	}

	public void endSelectMode() {
		if (selecting) {
			group.removeListener(selectListener);
			group.addListener(cardInfoListener);
			isSelected = false;
			selecting = false;
		}
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
					if (isSelected) {
						unselect();
					} else {
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

	public boolean checkIfDied() {
		return currentHealth == 0;
	}

	//check if character is you, switch to death screen, else fade out correct card
	public void cardDeath(final Card card) {
		// Check if card is a playercard. if it is, check for cloak. if cloak exists, burn the cloak
		//  and set hp to
		if (card instanceof PlayerCard && !((PlayerCard) card).hasCloak()) {
			cardImage.addAction(Actions.delay(1.5f, Actions.run(new Runnable() {
				@Override
				public void run() {
					((PlayerCard) card).removeCloak();
					heal(10);
				}
			})));
		} else if ((card instanceof MonsterCard)) {
			Effect effect = new Died(card);
			CardAnimation cardAnimation = field.animationManager.getCardAnimation();

			field.monsterZone.remove(card);
			cardAnimation.fadeOut(card);

			card.died();
			card.getField().roundManager.addEventClient(effect, effect.getStartType());
		} else {
			Effect effect = new Died(card);
			CardAnimation cardAnimation = field.animationManager.getCardAnimation();
			field.playerZone.remove(card);
			cardAnimation.fadeOut(card);

			Runnable r = new Runnable() {
				@Override
				public void run() {
					card.died();
				}
			};
			getCardImage().addAction(Actions.delay(4f, Actions.run(r)));
			card.getField().roundManager.addEventClient(effect, effect.getStartType());

			System.out.println("Current card: " + field.getCurrentCard().getName());

			if (field.getCurrentCard() == card) {
				field.nextTurn();
			}
			if (field.playerZone.size() == 0) {
				field.dungeonEnded();
			}
			if (card.getID() != field.getClientCharacter().getId()) {
				int moneygained = 0;
				if (field.playerZone.size() == 0) {
					moneygained = ((PlayerCard) this).getNetWorth();
				} else {
					moneygained = ((PlayerCard) this).getNetWorth() / field.playerZone.size();

				}
				field.getClientCharacter().inventory.addGold(moneygained);
				new OKPopup(field.game, this.getName() + " died\n You found " + moneygained);
			}

		}
		card.getCardName().setVisible(false);
	}

	public void poison() {
		double newhealth = Math.floor(currentHealth - baseHealth * .2);
		currentHealth = (int) newhealth;
		healthBar.setNewHealthPercent(currentHealth * 100 / baseHealth);
		if (checkIfDied())
			cardDeath(this);
	}

	public void attackTrueDamage(float damage) {
		if (!defenders.isEmpty()) {
			// has defenders, should split damage among defenders
			for (Card card : defenders) {
				card.takeTrueDamage((int) Math.ceil(damage / defenders.size()));
				System.out.println("DEFENDER TOOK DAMAGE");
			}
		} else {
			//does not have defenders, this card takes the damage
			takeTrueDamage(damage);
			System.out.println("I TOOK DAMAGE");
		}
	}

	public void attack(float damage) {
		if (!defenders.isEmpty()) {
			// has defenders, should split damage among defenders
			for (Card card : defenders) {
				card.takeDamage((int) Math.ceil(damage / defenders.size()));
			}
		} else {
			//does not have defenders, this card takes the damage
			takeDamage(damage);
		}
	}

	private void takeTrueDamage(float damage) {
		currentHealth -= damage;
		if (currentHealth < 0)
			currentHealth = 0;
		healthBar.setNewHealthPercent(currentHealth * 100 / baseHealth);
		if (checkIfDied())
			cardDeath(this);
	}

	private void takeDamage(float damage) {
		currentHealth -= calculateDamageWithDefense(damage, this.getCurrentDefense());
		if (currentHealth < 0)
			currentHealth = 0;
		healthBar.setNewHealthPercent(currentHealth * 100 / baseHealth);
		if (checkIfDied()) {
			cardDeath(this);
		}
	}

	public void died() {
		if (this instanceof PlayerCard && getID() == field.game.getCurrentCharacter().getId()) {
			// You have died
			System.out.println("YOURSELF");

			field.removePlayerCard((PlayerCard) this);
			field.game.addFool(field.game.getCurrentCharacter());
			field.game.savedDataHandler.save();
			new OKPopup(field.game, "You Have Died") {
				@Override
				public void onConfirm() {
					field.game.gsm.setState(GameStateManager.State.MENU);
				}
			};

			if (field.getClientCharacter().getRoom().getSocket() != null
					&& field.getClientCharacter().getRoom().getSocket().connected()) {
				field.getClientCharacter().getRoom().getSocket().disconnect();
			}

		} else if (this instanceof PlayerCard) {
			System.out.println("TEAMMATE");
			//Teammate died
			//field.removePlayerCard((PlayerCard) this);


		} else if (this instanceof MonsterCard) {
			System.out.println("MONSTER");
			//Monster Card
			endSelectMode();
			field.removeMonsterCard((MonsterCard) this);
			if (field.isMonsterZoneEmpty()) {
				//last hit bonus
				field.dungeonEnded();
				int i = field.playerZone.indexOf(field.getCurrentCard());
				i--;
				if (i < 0) i = field.playerZone.size() - 1;
				if (field.playerZone.get(i).getID() == field.getClientCharacter().getId()) {
					//you got the last hit bonus
					int bonusReward = 150;
					field.getClientCharacter().inventory.addGold(bonusReward);
					new OKPopup(field.game, "Last Hit Bonus!\nYou got " + bonusReward + " gold");
				}
				final OKPopup specialFloorPopup = field.getClientCharacter().stats.advanceFloor(field.game);
				field.game.savedDataHandler.save();
				Runnable r = new Runnable() {
					@Override
					public void run() {
						field.reward /= field.playerZone.size();
						new OKPopup(field.game, "Floor Completed!\nGained " + field.reward + " Gold") {
							@Override
							public void onConfirm() {
								field.getClientCharacter().inventory.addGold(field.reward);        //gets your reward
								field.game.gsm.setState(GameStateManager.State.LOBBY);
								if (specialFloorPopup != null) specialFloorPopup.show();
								game.getCurrentCharacter().getRoom().updateServerCharacters();
								field.getClientCharacter().getRoom().refreshLobby();
							}
						};
						System.out.println("floor comp");
					}
				};
				System.out.println("cleared");
				this.getCardImage().addAction(Actions.delay(4f, Actions.run(r)));
			}
		} else {
			throw new AssertionError("create assertion error thingy. This shouldnt be happening - means not mosnter or palyercard");
		}
	}

	private float calculateDamageWithDefense(float damage, int armor) {
		if (damage <= 0) {
			// If damage is negative, it's a miss
			return 0;
		}
		if (armor < 0) {
			armor = 0;
		}
		float newDamage = damage - armor;
		// If new damage is reduced to less than 0 for
		if (newDamage < 1) {
			newDamage = 1;
		}
		return newDamage;
	}

	public HealthBar getHealthBar() {
		return healthBar;
	}

	public void addDefender(PlayerCard defender) {
		defenders.add(defender);
	}

	public boolean isBeingDefended() {
		return defenders.size() > 0;
	}

	public int getDefendersSize() {
		return defenders.size();
	}

	public Card getDefender(int index) {
		return defenders.get(index);
	}

	public void removeDefender(int id) {
		for (int counter = 0; counter < defenders.size(); counter++) {
			if (defenders.get(counter).getID() == id) {
				defenders.remove(counter);
				return;
			}
		}
	}

	public abstract String getName();

	public abstract int getID();

	public class HealthBar extends Group {

		private final Drawable greenBar, yellowBar, redBar;
		private Image healthBarBackground, healthBar;
		private int currentHealthPercentage;    //change current % to final %

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
			initialize(x, y);

			// Add actors to the group
			addActor(healthBarBackground);
			addActor(healthBar);
		}

		private void initialize(float x, float y) {
			healthBarBackground.setBounds(x, y, 227, 25);
			healthBar.setBounds(x + 10, y + 8, 200, 8);

		}

		public float getBackgroundHeight() {
			return healthBarBackground.getHeight();
		}

		private void setNewHealthPercent(int newHealthPercent) {
			if (newHealthPercent > 100) newHealthPercent = 100;
			if (newHealthPercent < 0) newHealthPercent = 0;

			// LOSING HEALTH
			if (newHealthPercent <= 70 && newHealthPercent > 30 && currentHealthPercentage > 70) {
				// Green to Yellow
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
				// Green to Red
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(70 * 2, 8, 0.33f)));
				Runnable r = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(yellowBar);
					}
				};
				healthBar.addAction(Actions.delay(0.5f + 0.33f, Actions.run(r)));
				healthBar.addAction(Actions.delay(0.5f + 0.33f, Actions.sizeTo(30 * 2, 8, 0.33f)));
				Runnable s = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(redBar);
					}
				};
				healthBar.addAction(Actions.delay(0.5f + 0.66f, Actions.run(s)));

				healthBar.addAction(Actions.delay(0.5f + 0.66f, Actions.sizeTo(newHealthPercent * 2, 8, 0.33f)));
			} else if (newHealthPercent <= 30 && currentHealthPercentage <= 70 && currentHealthPercentage > 30) {
				// Yellow to Red
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
				// Red to yellow
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(30 * 2, 8, 0.5f)));
				Runnable r = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(yellowBar);
					}
				};
				healthBar.addAction(Actions.delay(1f, Actions.run(r)));
				healthBar.addAction(Actions.delay(1f, Actions.sizeTo(newHealthPercent * 2, 8, 0.5f)));
			} else if (newHealthPercent > 70 && currentHealthPercentage <= 30) {
				// Red to Green
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(30 * 2, 8, 0.33f)));
				Runnable r = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(yellowBar);
					}
				};
				healthBar.addAction(Actions.delay(0.5f + 0.33f, Actions.run(r)));
				healthBar.addAction(Actions.delay(0.5f + 0.33f, Actions.sizeTo(70 * 2, 8, 0.33f)));
				Runnable s = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(greenBar);
					}
				};
				healthBar.addAction(Actions.delay(0.5f + 0.66f, Actions.run(s)));
				healthBar.addAction(Actions.delay(0.5f + 0.66f, Actions.sizeTo(newHealthPercent * 2, 8, 0.33f)));
			} else if (newHealthPercent > 70 && currentHealthPercentage > 30 && currentHealthPercentage < 70) {
				// Yellow to Green
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
				// stays in same color range
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(newHealthPercent * 2, 8, 1)));
			}
			currentHealthPercentage = newHealthPercent;
		}
	}
}
