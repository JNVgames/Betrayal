/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.dungeon.managers.UIManager;
import com.jnv.betrayal.main.Betrayal;

import java.util.List;

public class CurrentAction {

	private UIManager uiManager;
	private Label actionText, targetText;
	private String action, target;

	public CurrentAction(Stage stage, UIManager uiManager) {
		this.uiManager = uiManager;
		action = Action.ACTION_DEFAULT;
		target = Action.TARGET_DEFAULT;

		targetText = new Label(Action.TARGET_DEFAULT, Betrayal.getFont(45));
		targetText.setBounds(Panel.top.x, Panel.top.y,
				Betrayal.WIDTH, targetText.getPrefHeight());
		targetText.layout();
		targetText.setAlignment(Align.center);
		stage.addActor(targetText);

		actionText = new Label(Action.ACTION_DEFAULT, Betrayal.getFont(45));
		actionText.setBounds(targetText.getX(), targetText.getY() + targetText.getHeight(),
				targetText.getWidth(), targetText.getHeight());
		actionText.layout();
		actionText.setAlignment(Align.center);
		stage.addActor(actionText);
	}

	public void updateText() {
		actionText.setText(action);
		targetText.setText(target);
	}

	private void updateLabelBounds() {
		targetText.setBounds(targetText.getX(), targetText.getY(), targetText.getWidth(),
				targetText.getPrefHeight());
		actionText.setBounds(targetText.getX(), targetText.getY() + targetText.getHeight(),
				targetText.getWidth(), actionText.getPrefHeight());
	}

	// Getters
	public UIManager getUiManager() {
		return uiManager;
	}

	// Setters
	public void setCurrentActionText(String actionText, List<String> targets) {
		setActionText(actionText);
		setTargetText(targets);
	}

	public void setActionText(String text) {
		action = text;
		actionText.setText(action);
	}

	public void setTargetText(String text) {
		target = text;
		targetText.setText(target);
	}

	public void setTargetText(List<String> targets) {
		String text = "";
		if (targets.size() <= 3) {
			for (int i = 0; i < targets.size(); i++) {
				text += targets.get(i);
				if (i != targets.size() - 1) text += ", ";
			}
		} else { // targets.size() > 3
			for (int i = 0; i < 3; i++) {
				text += targets.get(i);
				if (i != targets.size() - 1) text += ", ";
			}
			text += "\n";
			for (int i = 3; i < targets.size(); i++) {
				text += targets.get(i);
				if (i != targets.size() - 1) text += ", ";
			}
		}
		if (!text.equals("")) targetText.setText(text);
		else targetText.setText(Action.TARGET_DEFAULT);
		target = targetText.getText().toString();
		updateLabelBounds();
	}
}
