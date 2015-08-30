/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.events.TargetSelect;
import com.jnv.betrayal.dungeon.managers.DungeonManager;
import com.jnv.betrayal.dungeon.managers.UIManager;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.inputprocessors.InputListener;
import com.jnv.betrayal.main.Betrayal;

import java.util.Stack;

public class ActionBar {

	private UIManager uiManager;
	private GameStateManager gsm;
	private Stage stage;
	private Group actionBar;
	private Pool<Label> panelPool;
	private Stack<State> menu;
	private int numPlayers;

	public ActionBar(int numPlayers, Stage stage, UIManager uiManager) {
		this.uiManager = uiManager;
		gsm = uiManager.getDungeonManager().getGSM();
		this.numPlayers = numPlayers;
		this.stage = stage;
		actionBar = new Group();
		menu = new Stack<State>();
		stage.addActor(actionBar);
		setupLabelPool();
	}

	public void start() {
		draw(State.MAIN);
	}

	private void draw(State state) {
		for (Actor actor : actionBar.getChildren()) {
			if (actor instanceof Label) panelPool.free((Label) actor);
		}
		actionBar.clear();
		uiManager.getCurrentAction().updateText();
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

	private void setupLabelPool() {
		panelPool = new Pool<Label>() {
			public Label obtain() {
				Label tmp = super.obtain();
				tmp.clearListeners();
				return tmp;
			}

			protected Label newObject() {
				return new Label(null, new Label.LabelStyle(Betrayal.getFont(70)));
			}
		};
	}

	private void drawMainMenu() {
		createPanel("Event Log", 67, Panel.bottomLeft, State.EVENT_LOG, actionBar);
		createPanel("Attack", 70, Panel.topLeft, State.ATTACK, actionBar);
		createPanel("Items", 70, Panel.topRight, State.ITEMS, actionBar);

		Label tmp = panelPool.obtain();
		tmp.setText("Lobby (tmp)");
		tmp.setStyle(Betrayal.getFont(50));
		tmp.setBounds(Panel.BUTTON_WIDTH, 0, Panel.BUTTON_WIDTH, Panel.BUTTON_HEIGHT);
		tmp.setAlignment(Align.center);
		tmp.layout();
		tmp.addListener(new com.badlogic.gdx.scenes.scene2d.InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (x > 0 && x < Panel.BUTTON_WIDTH && y > 0 && y < Panel.BUTTON_HEIGHT)
					gsm.setState(GameStateManager.State.LOBBY);
			}
		});
		actionBar.addActor(tmp);
	}

	private void drawAttackMenu() {
		createPanel("Normal Attack", 50, Panel.topLeft, State.TARGET_SELECT, actionBar);
		createPanel("Skill", 70, Panel.topRight, null, actionBar);
		createPanel("Back", 70, Panel.bottomRight, State.BACK, actionBar);
	}

	private void drawTargetSelect() {
		final TargetSelect targetSelect =
				new TargetSelect(numPlayers, Action.ACTION_NORMAL_ATTACK, stage,
						uiManager.getCurrentAction());
		targetSelect.configureTargetSelect(4);

		Label doneButton = panelPool.obtain();
		doneButton.setText("Done");
		doneButton.setStyle(Betrayal.getFont(50));
		doneButton.setBounds(Panel.topLeft.x, Panel.topLeft.y,
				Betrayal.WIDTH, Panel.BUTTON_HEIGHT);
		doneButton.layout();
		doneButton.setAlignment(Align.center);
		doneButton.addListener(new InputListener(doneButton) {
			public void doAction() {
				uiManager.getCurrentAction().setCurrentActionText(targetSelect.getAction(),
						targetSelect.getTargetSelected());
				targetSelect.remove();
				draw(State.BACK);
			}
		});
		actionBar.addActor(doneButton);

		Label cancelButton = panelPool.obtain();
		cancelButton.setText("Cancel");
		cancelButton.setStyle(Betrayal.getFont(50));
		cancelButton.setBounds(Panel.bottomLeft.x, Panel.bottomLeft.y,
				Betrayal.WIDTH, Panel.BUTTON_HEIGHT);
		cancelButton.layout();
		cancelButton.setAlignment(Align.center);
		cancelButton.addListener(new InputListener(cancelButton) {
			@Override
			public void doAction() {
				targetSelect.remove();
				draw(State.BACK);
			}
		});
		actionBar.addActor(cancelButton);

		menu.push(State.TARGET_SELECT);
	}

	/**
	 * Function to make creating 4-button action bars easier
	 *
	 * @param panelText   text you want your button to say
	 * @param fontSize    text size
	 * @param position    where you want your button to be
	 * @param panelAction what you want your button to do
	 * @param group       add your button to a group
	 */
	private void createPanel(String panelText, int fontSize, Vector2 position,
							 final State panelAction, Group group) {
		Label panel = panelPool.obtain();
		panel.setText(panelText);
		panel.setStyle(Betrayal.getFont(fontSize));
		panel.setBounds(position.x, position.y, Panel.BUTTON_WIDTH, Panel.BUTTON_HEIGHT);
		panel.setAlignment(Align.center);
		panel.layout();
		panel.addListener(new InputListener(panel) {
			@Override
			public void doAction() {
				draw(panelAction);
			}
		});
		group.addActor(panel);
	}

	public enum State {
		BACK,
		MAIN,
		ATTACK,
		ITEMS,
		EVENT_LOG,
		TARGET_SELECT,
		MULTIPLE_TARGET_SELECT
	}
}
