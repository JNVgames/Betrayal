/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.dungeon.ui.Players;
import com.jnv.betrayal.gameobjects.Character;

import java.util.List;

public class PlayersManager {

	private Players players;

	public PlayersManager(List<Character> characters, Stage stage) {
		players = new Players(characters, stage);
	}
}
