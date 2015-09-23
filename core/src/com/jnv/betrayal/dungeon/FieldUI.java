/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.actions.Action;
import com.jnv.betrayal.dungeon.actions.NormalAttack;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.dungeon.utils.State;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

import java.util.Stack;

class FieldUI extends Group {

	private Field field;
	private BetrayalAssetManager res;
	private Pool<Label> panelPool;
	private Pool<Button> buttonPool;
	private Stack<State> menu;
	private int numPlayers;
	private Action currentAction;
	public final GameStateManager gsm;

	public FieldUI(Field field) {
		this.field = field;
		res = field.res;
		gsm = field.gsm;
		menu = new Stack<State>();
		setupPools();
	}

	public void start() {
		draw(State.MAIN);
	}

	private void draw(State state) {
		// Clear action bar
		for (Actor actor : getChildren()) {
			if (actor instanceof Label) panelPool.free((Label) actor);
			if (actor instanceof Button) buttonPool.free((Button) actor);
		}
		clear();
		switch (state) {
			case BACK:
				menu.pop();
				draw(menu.peek());
				break;
			case MAIN:
				drawMainMenu();
				pushMenuState(State.MAIN);
				break;
			case ATTACK:
				//drawAttackMenu();
				//pushMenuState(State.ATTACK);
				break;
			case ITEMS:
				//drawItemsMenu();
				break;
			case EVENT_LOG:
				//drawEventLogMenu();
				break;
			case TARGET_SELECT:
				throw new AssertionError("Use draw(int targets) for target selection");
			default:
				break;
		}
	}

	/**
	 * Use this method for target selection
	 *
	 * @param action used for targets
	 */
	private void draw(Action action) {
		// If currentAction is null, set the action to current action
		if (currentAction == null) currentAction = action;
		// If not null, unselect all
		else if (!currentAction.equals(action)) { // if (currentAction != null)
			field.unselectAll();
		}

		// Clear action bar
		for (Actor actor : getChildren()) {
			if (actor instanceof Label) panelPool.free((Label) actor);
			System.out.println("buttonPool" + buttonPool.getFree());
			if (actor instanceof Button) buttonPool.free((Button) actor);
			System.out.println("buttonPool" + buttonPool.getFree());
		}
		clear();

		field.beginSelectMode(action.getTargetLimit());
		drawTargetSelect();
		pushMenuState(State.TARGET_SELECT);
	}

	// Helpers
	private void pushMenuState(State state) {
		if (menu.isEmpty() || menu.peek() != state) menu.push(state);
	}

	private void setupPools() {
		buttonPool = new Pool<Button>() {
			public Button obtain() {
				Button tmp = super.obtain();
				ClickListener clickListener = tmp.getClickListener();
				// Remove all listeners except for the one needed for button to work
				for (EventListener listener : tmp.getListeners())
					if (listener != clickListener) tmp.removeListener(listener);
				return tmp;
			}

			protected Button newObject() {
				return new Button();
			}
		};
		panelPool = new Pool<Label>() {
			public Label obtain() {
				Label tmp = super.obtain();
				tmp.clearListeners();
				return tmp;
			}

			protected Label newObject() {
				return new Label(null, new Label.LabelStyle(FontManager.getFont(70)));
			}
		};
	}

	private void drawMainMenu() {
		createPanel("Items", 70, Panel.bottomLeft, new Runnable() {
			public void run() {
				draw(State.EVENT_LOG);
			}
		});
		createPanel("Attack", 70, Panel.topLeft, new Runnable() {
			public void run() {
				draw(new NormalAttack());
			}
		});
		createPanel("Skills", 70, Panel.topRight, new Runnable() {
			public void run() {
				draw(State.SKILLS);
			}
		});
		createPanel("Flee", 70, Panel.bottomRight, new Runnable() {
			@Override
			public void run() {
				new Confirmation(gsm.game, "Are you sure you want to flee?" + "\n20% Chance") {
					@Override
					public void doSomething() {
						gsm.setState(GameStateManager.State.LOBBY);
					}
				};
			}
		});
	}

	private void drawTargetSelect() {
		//final TargetSelect targetSelect =
		//		new TargetSelect(numPlayers, Action.ACTION_NORMAL_ATTACK, stage);
		//targetSelect.configureTargetSelect(targets);

		createPanel("Done", 70, Panel.top, new Runnable() {
			@Override
			public void run() {
				field.endSelectMode();
				if (field.getCardsSelected() == 0) currentAction = null;
				draw(State.BACK);
			}
		});
		createPanel("Cancel", 70, Panel.bottom, new Runnable() {
			@Override
			public void run() {
				field.cancelSelectMode();
				if (field.getCardsSelected() == 0) currentAction = null;
				draw(State.BACK);
			}
		});
	}

	/**
	 * Function to make creating 4-button action bars easier
	 *
	 * @param panelText text you want your button to say
	 * @param fontSize  text size
	 */
	private void createPanel(String panelText, int fontSize, Dimension dimension,
							 final Runnable action) {
		Label panel = panelPool.obtain();
		panel.setText(panelText);
		panel.setStyle(FontManager.getFont(fontSize));
		panel.setBounds(dimension);
		panel.setAlignment(Align.center);
		panel.layout();
		Button border = buttonPool.obtain();
		border.setStyle(new com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle(
				new TextureRegionDrawable(new TextureRegion(
						res.getTexture("actionBarButtonUp" + (int) dimension.getWidth() + "x"
								+ (int) dimension.getHeight()))),
				new TextureRegionDrawable(new TextureRegion(
						res.getTexture("actionBarButtonDown" + (int) dimension.getWidth() + "x"
								+ (int) dimension.getHeight()))), null));
		border.setBounds(dimension);
		border.addListener(new InputListener(border) {
			@Override
			public void doAction() {
				action.run();
			}
		});
		addActor(panel);
		addActor(border);
	}
}
