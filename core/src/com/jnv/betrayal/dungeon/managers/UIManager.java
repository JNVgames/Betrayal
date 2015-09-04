/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.managers;

import com.jnv.betrayal.dungeon.ui.ActionBar;
import com.jnv.betrayal.dungeon.ui.CurrentAction;
import com.jnv.betrayal.gameobjects.Character;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.gamestates.GameStateManager;

import java.util.List;

public class UIManager {

	private DungeonManager dungeonManager;
	private ActionBar actionBar;
	private CurrentAction currentAction;
	private PlayersManager playersManager;
	private MonsterManager monsterManager;

	public UIManager(List<Character> characters, Monster monster,
					 GameStateManager gsm, DungeonManager dungeonManager) {
		this.dungeonManager = dungeonManager;
		currentAction = new CurrentAction(gsm.getGame().getStage(), this);
		actionBar = new ActionBar(characters.size(), gsm.getGame().getStage(), this);
		playersManager = new PlayersManager(characters, gsm.getGame().getStage());
		monsterManager = new MonsterManager(gsm.getGame().res, gsm.getGame().getStage());
		actionBar.start();
	}

	// Getters
	public DungeonManager getDungeonManager() {
		return dungeonManager;
	}

	public ActionBar getActionBar() {
		return actionBar;
	}

	public CurrentAction getCurrentAction() {
		return currentAction;
	}

	public PlayersManager getPlayersManager() {
		return playersManager;
	}

	public MonsterManager getMonsterManager() {
		return monsterManager;
	}
}
