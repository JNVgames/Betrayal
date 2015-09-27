/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.Preview;
import com.jnv.betrayal.character.utils.Rotation;
import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.dungeon.utils.DungeonCoords;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.Dimension;

public class PlayerCard extends Card {

	private Preview preview;

	public PlayerCard(Vector2 coords, Character character,
					  BetrayalAssetManager res) {
		this(coords.x, coords.y, character, res);
	}

	public PlayerCard(float x, float y, Character character,
						 BetrayalAssetManager res) {
		super(new Dimension(x, y, DungeonCoords.PLAYER_WIDTH, DungeonCoords.PLAYER_HEIGHT), res);
		baseHealth = character.stats.getStat(Stat.HEALTH);
		baseAttack = character.stats.getStat(Stat.ATTACK);
		baseDefense = character.stats.getStat(Stat.DEFENSE);
		preview = character.preview;
		cardImage = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
				preview.drawPreview(batch, Rotation.BACK, getX(), getY(), getWidth(), getHeight());
			}
		};
		cardImage.setBounds(0, 0, group.getWidth(), group.getHeight());
		group.addActor(cardImage);
		cardImage.toBack();
	}
}
