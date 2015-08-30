/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.events;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.dungeon.objects.DungeonCoords;
import com.jnv.betrayal.dungeon.objects.Target;
import com.jnv.betrayal.dungeon.ui.Action;
import com.jnv.betrayal.dungeon.ui.CurrentAction;
import com.jnv.betrayal.inputprocessors.InputListener;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;

import java.util.ArrayList;
import java.util.List;

public class TargetSelect {

	private CurrentAction currentAction;
	private String action;
	private List<Target> targets;
	private boolean[] isSelected;
	private int targetsSelected = 0;

	public TargetSelect(int numPlayers, String action, Stage stage, CurrentAction currentAction) {
		this.currentAction = currentAction;
		this.action = action;
		BetrayalAssetManager res =
				currentAction.getUiManager().getDungeonManager().getGSM().getGame().res;
		Vector2 coords = DungeonCoords.monster;
		Actor monsterMask = new Actor();
		monsterMask.setBounds(coords.x, coords.y, DungeonCoords.MONSTER_WIDTH,
				DungeonCoords.MONSTER_HEIGHT);
		targets = new ArrayList<Target>();
		targets.add(new Target(DungeonCoords.monster, DungeonCoords.MONSTER_WIDTH,
				DungeonCoords.MONSTER_HEIGHT, Action.TARGET_MONSTER, stage, res));

		for (int i = 0; i < numPlayers; i++) {
			targets.add(new Target(DungeonCoords.player[i], DungeonCoords.PLAYER_WIDTH,
					DungeonCoords.PLAYER_HEIGHT, Action.TARGET_PLAYER[i], stage, res));
		}
		isSelected = new boolean[numPlayers + 1];
	}

	public void configureTargetSelect(int numTargets) {
		if (Betrayal.debug && numTargets > 6) {
			throw new AssertionError("Number of targets out of bounds: " + 6);
		}
		currentAction.setActionText(Action.TARGET_SELECT[numTargets - 1]);
		currentAction.setTargetText("");
		if (numTargets == 1) configSingleTargetSelect();
		if (numTargets > 1) configMultipleTargetSelect(numTargets);
	}

	private void configSingleTargetSelect() {
		for (int i = 0; i < targets.size(); i++) {
			final int index = i;
			targets.get(i).getActor().addListener(new InputListener(targets.get(i).getActor()) {
				@Override
				public void doAction() {
					if (targets.get(index).isSelected()) {
						unselectTarget(targets.get(index));
						targetsSelected--;
					} else {
						if (isAnyTargetSelected()) {
							unselectAll();
							targetsSelected = 0;
						}
						selectTarget(targets.get(index));
						targetsSelected++;
					}
					if (Betrayal.debug &&
							(targetsSelected < 0 || targetsSelected > 1)) {
						if (targetsSelected < 0)
							throw new AssertionError("Targets selected has a value under 0");
						if (targetsSelected > 1)
							throw new AssertionError("Targets selected has a value out of bounds: 1");
					}
				}
			});
		}
	}

	private void configMultipleTargetSelect(final int numTargets) {
		for (int i = 0; i < targets.size(); i++) {
			final int index = i;
			targets.get(i).getActor().addListener(new InputListener(targets.get(i).getActor()) {
				@Override
				public void doAction() {
					if (targets.get(index).isSelected()) {
						unselectTarget(targets.get(index));
						targetsSelected--;
					} else { // if target isn't selected
						if (targetsSelected < numTargets) {
							selectTarget(targets.get(index));
							targetsSelected++;
						}
					}
					if (Betrayal.debug &&
							(targetsSelected < 0 || targetsSelected > numTargets)) {
						if (targetsSelected < 0)
							throw new AssertionError("Targets selected has a value under 0");
						if (targetsSelected > numTargets)
							throw new AssertionError("Targets selected out of bounds: " + numTargets);
					}
				}
			});
		}
	}

	public void remove() {
		for (Target target : targets) target.remove();
	}

	// Helpers
	public boolean isAnyTargetSelected() {
		for (Boolean bool : isSelected) {
			if (bool) {
				return true;
			}
		}
		return false;
	}

	public void selectTarget(Target target) {
		target.select();
		updateSelectedTargets();
	}

	public void unselectTarget(Target target) {
		target.unselect();
		updateSelectedTargets();
	}

	public void unselectAll() {
		for (Target target : targets) {
			target.unselect();
		}
		updateSelectedTargets();
	}

	public void updateSelectedTargets() {
		for (int i = 0; i < targets.size(); i++) {
			isSelected[i] = targets.get(i).isSelected();
		}
	}

	// Getters
	public List<String> getTargetSelected() {
		List<String> listOfTargets = new ArrayList<String>();
		for (Target target : targets) {
			if (target.isSelected()) listOfTargets.add(target.getName());
		}
		return listOfTargets;
	}

	public String getAction() {
		if (getTargetSelected().isEmpty()) return Action.ACTION_DEFAULT;
		else return action;
	}
}
