/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.dungeon.Player;
import com.jnv.betrayal.character.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Draws players in the dungeon
 *
 * @author Vincent Wang
 */
public class Players {

	private Stage stage;
	private List<Player> players;

	public Players(List<Character> characters, Stage stage) {
		this.stage = stage;
		players = new ArrayList<Player>();
		createPlayers(characters);
	}

	private void createPlayers(List<Character> characters) {
		for (int i = 0; i < characters.size(); i++) {
			players.add(new Player(i, characters.get(i), stage));
		}
	}
}
