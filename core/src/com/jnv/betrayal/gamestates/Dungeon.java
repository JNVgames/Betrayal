/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.dungeon.Field;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.managers.MonsterManager;
import com.jnv.betrayal.dungeon.utils.DungeonCoords;

import java.util.ArrayList;
import java.util.List;

public class Dungeon extends GameState {

	private Field field;

	public Dungeon(GameStateManager gsm) {
		super(gsm);
		field = new Field(gsm);
		float highestPartyMemberFloor = 0;
		if (game.getCurrentCharacter().getRoom().getRoomID() < 0) {
			// If not in online room, add only yourself
			ArrayList<Character> tmp = new ArrayList<Character>();
			tmp.add(game.getCurrentCharacter());
			addCardsToStage(tmp);
			highestPartyMemberFloor = game.getCurrentCharacter().stats.getFloor();
		} else {
			// If in online room, get all characters in the room
			addCardsToStage(game.getCurrentCharacter().getRoom().getCharacters());
			for (Character character : game.getCurrentCharacter().getRoom().getCharacters()) {
				if (character.stats.getFloor() > highestPartyMemberFloor)
					highestPartyMemberFloor = character.stats.getFloor();
			}
		}
		int tier = (int) Math.ceil(highestPartyMemberFloor / 5);
		System.out.println("highestPartyMemberFloor = " + highestPartyMemberFloor);
		System.out.println("tier = " + tier);
		field.setBackgroundForField("map" + tier + "1");
		if (game.getCurrentCharacter().getRoom().getRoomID() > 0) {
			int MID = game.getCurrentCharacter().getRoom().getMonsterID();
			int MT = game.getCurrentCharacter().getRoom().getMonsterTier();
			MonsterManager monsterManager = new MonsterManager(MT, res, field, MID);
		} else {
			MonsterManager monsterManager = new MonsterManager(tier, res, field);
		}

		System.out.println("All cards: " + field.getAllCards());
		field.adjustPlayerCardStatsBasedOnJobs();
		field.adjustMonsterHealth();        // Adjusts MonsterHealth according to number of players
		stage.addActor(field);

		// Start first turn
		field.setup();
		field.uiManager.drawUI();
		field.roundManager.checkEvents(field.getCurrentCard());
	}

	public void update(float dt) {
		stage.act(dt);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	public void dispose() {

	}

	private void addCardsToStage(List<Character> characters) {
		int playerNum = 0;
		for (Character character : characters) {
			field.addCard(new PlayerCard(DungeonCoords.player[characters.size() - 1][playerNum], character, res));
			playerNum++;
		}
	}
}
