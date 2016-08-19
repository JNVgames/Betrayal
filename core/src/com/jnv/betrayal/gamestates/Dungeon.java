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

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

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
		field.setBackgroundForField("map" + tier + "1");
		if (game.getCurrentCharacter().getRoom().getRoomID() > 0) {
			int MID = game.getCurrentCharacter().getRoom().getMonsterID();
			int MT = game.getCurrentCharacter().getRoom().getMonsterTier();
			new MonsterManager(MT, res, field, MID);
		} else {
			new MonsterManager(tier, res, field);
		}

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
