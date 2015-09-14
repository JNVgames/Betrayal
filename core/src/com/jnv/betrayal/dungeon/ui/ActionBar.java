/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.events.TargetSelect;
import com.jnv.betrayal.dungeon.managers.UIManager;
import com.jnv.betrayal.dungeon.utils.Action;
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

public class ActionBar {

	private UIManager uiManager;
	private GameStateManager gsm;
	private BetrayalAssetManager res;
	private Stage stage;
	private Group actionBar;
	private Pool<Label> panelPool;
	private Pool<Button> buttonPool;
	private Stack<State> menu;
	private int numPlayers;

	public ActionBar(int numPlayers, Stage stage, UIManager uiManager) {
		this.uiManager = uiManager;
		gsm = uiManager.dungeonManager.gsm;
		this.numPlayers = numPlayers;
		this.stage = stage;
		res = uiManager.dungeonManager.gsm.getGame().res;
		actionBar = new Group();
		menu = new Stack<State>();
		stage.addActor(actionBar);
		setupPools();
	}

	public void start() {
		draw(State.MAIN);
	}

	private void draw(State state) {
		for (Actor actor : actionBar.getChildren()) {
			if (actor instanceof Label) panelPool.free((Label) actor);
			System.out.println("buttonPool" + buttonPool.getFree());
			if (actor instanceof Button) buttonPool.free((Button) actor);
			System.out.println("buttonPool" + buttonPool.getFree());
		}
		actionBar.clear();
		uiManager.currentAction.updateText();
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
				drawAttackMenu();
				pushMenuState(State.ATTACK);
				break;
			case ITEMS:
				//drawItemsMenu();
				break;
			case EVENT_LOG:
				//drawEventLogMenu();
				break;
			case TARGET_SELECT:
				drawTargetSelect();
				pushMenuState(State.TARGET_SELECT);
				break;
			default:
				break;
		}
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
		createPanel("Items", 70, Panel.bottomLeft, actionBar, new Runnable() {
			public void run() {
				draw(State.EVENT_LOG);
			}
		});
		createPanel("Attack", 70, Panel.topLeft, actionBar, new Runnable() {
			public void run() {
				draw(State.ATTACK);
			}
		});
		createPanel("Skills", 70, Panel.topRight, actionBar, new Runnable() {
			public void run() {
				draw(State.SKILLS);
			}
		});
		createPanel("Flee", 70, Panel.bottomRight, actionBar, new Runnable() {
			@Override
			public void run() {
				new Confirmation(gsm.getGame(), "Are you sure you want to flee?" + "\n20% Chance") {
					@Override
					public void doSomething() {
						gsm.setState(GameStateManager.State.LOBBY);
					}
				};
			}
		});
	}

	private void drawAttackMenu() {
		createPanel("Normal Attack", 50, Panel.topLeft, actionBar, new Runnable() {
			public void run() {
				draw(State.TARGET_SELECT);
			}
		});
		createPanel("Skill", 70, Panel.topRight, actionBar, null);
		createPanel("Back", 70, Panel.bottomRight, actionBar, new Runnable() {
			public void run() {
				draw(State.BACK);
			}
		});
	}

	private void drawTargetSelect() {
		final TargetSelect targetSelect =
				new TargetSelect(numPlayers, Action.ACTION_NORMAL_ATTACK, stage,
						uiManager.currentAction);
		targetSelect.configureTargetSelect(4);

		createPanel("Done", 70, Panel.top, actionBar, new Runnable() {
			@Override
			public void run() {
				uiManager.currentAction.setCurrentActionText(targetSelect.getAction(),
						targetSelect.getTargetSelected());
				targetSelect.remove();
				draw(State.BACK);
			}
		});
		createPanel("Cancel", 70, Panel.bottom, actionBar, new Runnable() {
			@Override
			public void run() {
				targetSelect.remove();
				draw(State.BACK);
				uiManager.currentAction.updateText();
			}
		});
	}

	/**
	 * Function to make creating 4-button action bars easier
	 *
	 * @param panelText   text you want your button to say
	 * @param fontSize    text size
	 * @param group       add your button to a group
	 */
	private void createPanel(String panelText, int fontSize, Dimension dimension,
							 Group group, final Runnable action) {
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
		group.addActor(panel);
		group.addActor(border);
	}
}
