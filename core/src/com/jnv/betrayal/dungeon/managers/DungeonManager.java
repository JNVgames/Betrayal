/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

import java.util.List;

public class DungeonManager {

	public final GameStateManager gsm;
	public final Betrayal game;
	public final UIManager uiManager;
	private List<Character> characters;

	public DungeonManager(List<Character> characters, Monster monster,
						  GameStateManager gsm) {
		this.gsm = gsm;
		this.game = gsm.getGame();
		gsm.getGame().getStage().addActor(new Image(game.res.getTexture("map-1")));
		this.characters = orderListOfPlayers(characters);
		uiManager = new UIManager(characters, monster, gsm, this);
	}

	public List<Character> orderListOfPlayers(List<Character> characters) {
		for (Character character : characters) {
			if (character == game.getPlayer().getCurrentCharacter()
					&& characters.indexOf(character) != 0) {
				Character tmp = characters.get(0);
				int index = characters.indexOf(character);
				characters.set(0, character);
				characters.set(index, tmp);
			}
		}
		return characters;
	}

}
