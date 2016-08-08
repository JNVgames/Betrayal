/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jnv.betrayal.dungeon.Field;
import com.jnv.betrayal.dungeon.effects.Died;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.popup.CardInfo;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {

	protected Field field;
	protected int baseHealth, baseAttack, baseDefense, currentHealth, currentAttack, currentDefense;
	protected HealthBar healthBar;
	protected BetrayalAssetManager res;
	protected Actor cardImage;
	protected Group group;
	private List<Card> defenders = new ArrayList<Card>();
	private TextureRegion selectedTexture;
	private boolean wasSelected, isSelected, selecting;
	private InputListener selectListener, cardInfoListener;
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
		group.addActor(healthBar);
		healthBar.toFront();
		isTurn = false;
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

	public void increaseCurrentAttack(int value) {
		currentAttack += value;
	}

	public void decreaseCurrentAttack(int value) {
		currentAttack -= value;
	}

	public void increaseCurrentDefense(int value) {
		currentDefense += value;
	}

	public void decreaseCurrentDefense(int value) {
		currentDefense -= value;
	}

	public void heal(int value) {
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
		selecting = true;
	}

	public void endSelectMode() {
		if (!selecting)
			throw new IllegalStateException("Card.beginSelectMode must be called before endSelectMode");
		group.removeListener(selectListener);
		group.addListener(cardInfoListener);
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

	public InputListener createCardInfoListener() {
		try {
			return new InputListener(cardImage) {
				@Override
				public void doAction() {
					new CardInfo(field.game, Card.this);
				}
			};
		} catch (NullPointerException e) {
			System.out.println("Card: Actor cardImage was not created properly");
			Gdx.app.exit();
		}
		return null;
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
					System.out.println("CLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOAK");
				}
			})));
		} else {

			//todo remove this later !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if(card instanceof PlayerCard)
				return;
			Effect effect = new Died(card);
			card.getField().roundManager.addEvent(effect, effect.getStartType());
		}
	}

	public void poison() {
		double newhealth = Math.floor(currentHealth * .9);
		currentHealth = (int) newhealth;
		healthBar.setNewHealthPercent(currentHealth * 100 / baseHealth);
		if (checkIfDied())
			cardDeath(this);
	}

	public void takeTrueDamage(int damage) {
		currentHealth -= damage;
		if (currentHealth < 0)
			currentHealth = 0;
		healthBar.setNewHealthPercent(currentHealth * 100 / baseHealth);
		if (checkIfDied())
			cardDeath(this);
	}

	public void attackTrueDamage(int damage){
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

	public void attack(int damage) {
		if (!defenders.isEmpty()) {
			// has defenders, should split damage among defenders
			for (Card card : defenders) {
				card.takeDamage((int) Math.ceil(damage / defenders.size()));
				System.out.println("DEFENDER TOOK DAMAGE");
			}
		} else {
			//does not have defenders, this card takes the damage
			takeDamage(damage);
			System.out.println("I TOOK DAMAGE");
		}
	}

	public void takeDamage(int damage) {
		currentHealth -= calculateDamageWithDefense(damage, this.getCurrentDefense());
		if (currentHealth < 0)
			currentHealth = 0;
		healthBar.setNewHealthPercent(currentHealth * 100 / baseHealth);
		if (checkIfDied()) {
			cardDeath(this);
		}
	}

	private int calculateDamageWithDefense(int damage, int armor) {
		if (damage <= 0) {
			// If damage is negative, it's a miss
			return 0;
		}
		if (armor < 0) {
			armor = 0;
		}
		int newDamage = damage - armor;
		// If new damage is reduced to less than 0 for
		if (newDamage < 1) {
			newDamage = 1;
		}
		return newDamage;
	}

	public HealthBar getHealthBar() {
		return healthBar;
	}

	// todo REMOVE THIS
	public List<Card> getDefenders() {
		return defenders;
	}

	public void addDefender(PlayerCard defender) {
		defenders.add(defender);
	}

	public boolean isBeingDefended() {
		return defenders.size() > 0;
	}

	public void removeDefender(int id) {
		int counter = 0;
		for (counter = 0; counter < defenders.size(); counter++) {
			if (defenders.get(counter).getID() == id) {
				break;
			}
		}
		defenders.remove(counter);
	}

	public void performEffect(Effect effect) {
		field.roundManager.addEvent(effect, effect.getStartType());
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
				healthBar.addAction(Actions.delay(0.3f, Actions.sizeTo(70 * 2, 8, 0.3f)));
				Runnable r = new Runnable() {
					@Override
					public void run() {
						healthBar.setDrawable(yellowBar);
					}
				};
				healthBar.addAction(Actions.delay(0.6f, Actions.run(r)));
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
				healthBar.addAction(Actions.delay(0.5f, Actions.sizeTo(newHealthPercent * 2, 8, 0.5f)));
			} else if (newHealthPercent > 70 && currentHealthPercentage <= 30) {
				// Red to Green
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
				healthBar.addAction(Actions.delay(1.0f, Actions.sizeTo(newHealthPercent * 2, 8, 1f)));
			}
			currentHealthPercentage = newHealthPercent;
		}
	}

	public void failedToFlee() {
		Card card = this;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				new OKPopup(field.game, "Flee Failed");
				field.turnManager.nextTurn();
			}
		};
		card.getCardImage().addAction(Actions.delay(2f, Actions.run(r)));
	}

	public void flee() {
		Card card = this;
		if (card instanceof PlayerCard && card.getID() == field.game.getCurrentCharacter().getId()) {
			// Flee Successful
			field.removePlayerCard((PlayerCard) card);

			Runnable r = new Runnable() {
				@Override
				public void run() {
					new OKPopup(field.game, "Flee Successful") {
						@Override
						public void onConfirm() {
							field.game.gsm.setState(GameStateManager.State.LOBBY);
						}
					};
				}
			};
			card.getCardImage().addAction(Actions.delay(2f, Actions.run(r)));
		} else if (card instanceof PlayerCard) {
			//Teammate fled
			field.removePlayerCard((PlayerCard) card);
		} else {
			throw new AssertionError("create assertion error thingy. This shouldnt be happening - means not a playercard");
		}
	}

	public void died() {
		if (this instanceof PlayerCard && getID() == field.game.getCurrentCharacter().getId()) {
			// You have died
			field.removePlayerCard((PlayerCard) this);

			Runnable r = new Runnable() {
				@Override
				public void run() {
					new OKPopup(field.game, "You Have Died") {
						@Override
						public void onConfirm() {
							field.game.characters.remove(field.game.getCurrentCharacter());
							field.game.gsm.setState(GameStateManager.State.MENU);
						}
					};
				}
			};
			getCardImage().addAction(Actions.delay(4f, Actions.run(r)));

		} else if (this instanceof PlayerCard) {
			//Teammate died
			field.removePlayerCard((PlayerCard) this);

		} else if (this instanceof MonsterCard) {
			//Monster Card
			field.removeMonsterCard((MonsterCard) this);
			if (field.isMonsterZoneEmpty()) {
				Runnable r = new Runnable() {
					@Override
					public void run() {
						new OKPopup(field.game, "Floor Completed!\nGained " + field.reward + " Gold") {
							@Override
							public void onConfirm() {
								for (Card c : field.getAllPlayerCards()) {
									((PlayerCard) c).levelUpCharacter();
									((PlayerCard) c).getReward();
									System.out.println("field reward" + field.reward);
								}
								field.game.gsm.setState(GameStateManager.State.LOBBY);

							}
						};
					}
				};

				this.getCardImage().addAction(Actions.delay(4f, Actions.run(r)));
			}
		} else {
			throw new AssertionError("create assertion error thingy. This shouldnt be happening - means not mosnter or palyercard");
		}
	}
}
