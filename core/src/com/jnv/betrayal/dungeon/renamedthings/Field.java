/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.renamedthings;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

public class Field extends Group {

	public final SnapshotArray<PlayerCard> playerZone;
	public final SnapshotArray<MonsterCard> monsterZone;

	public Field() {
		playerZone = new SnapshotArray<PlayerCard>();
		monsterZone = new SnapshotArray<MonsterCard>();
	}

	public void addCard(Card card) {
		addActor(card);
		if (card instanceof PlayerCard) playerZone.add((PlayerCard) card);
		else if (card instanceof MonsterCard) monsterZone.add((MonsterCard) card);
		else throw new AssertionError("Card is neither a PlayerCard or MonsterCard");
	}
}
