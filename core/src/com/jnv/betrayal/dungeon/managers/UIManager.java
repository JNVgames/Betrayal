/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.dungeon.ui.ActionBar;
import com.jnv.betrayal.dungeon.ui.CurrentAction;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.ui.Image;

import java.util.List;

public class UIManager {

	public final DungeonManager dungeonManager;
	public final ActionBar actionBar;
	public final CurrentAction currentAction;
	public final PlayersManager playersManager;
	public final MonsterManager monsterManager;
	public final BetrayalAssetManager res;
	public final Stage stage;

	public UIManager(List<Character> characters, Monster monster,
					 GameStateManager gsm, DungeonManager dungeonManager) {
		this.dungeonManager = dungeonManager;
		res = gsm.getGame().res;
		stage = gsm.getGame().getStage();
		currentAction = new CurrentAction(gsm.getGame().getStage(), this);
		actionBar = new ActionBar(characters.size(), gsm.getGame().getStage(), this);
		playersManager = new PlayersManager(characters, gsm.getGame().getStage());
		monsterManager = new MonsterManager(gsm.getGame().res, gsm.getGame().getStage());
		loadStage();
		actionBar.start();
	}

	private void loadStage() {
		loadEventLogButton();
	}

	private void loadEventLogButton() {
		float scale = 0.5f;
		Image eventLogButton = new Image(res.getTexture("event-log-button"));
		eventLogButton.layout();
		eventLogButton.setBounds(20, Betrayal.HEIGHT - 30 - 144 * scale, 512 * scale, 144  * scale);
		stage.addActor(eventLogButton);
	}
}
