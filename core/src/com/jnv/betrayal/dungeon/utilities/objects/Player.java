/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.utilities.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.character.Rotation;
import com.jnv.betrayal.gameobjects.Character;

public class Player extends Entity {

	private Character character;
	private Actor actor;
	private String username, jobName;

	public Player(int player, Character character, Stage stage) {
		super(stage);
		this.character = character;
		username = character.getName();
		jobName = character.getJob().toString();
		createActor(player);
	}

	public void createActor(int player) {
		final Vector2 coords = com.jnv.betrayal.dungeon.utilities.constants.DungeonCoords.player[player];
		actor = new Actor() {
			public void draw(Batch batch, float parentAlpha) {
				batch.setColor(getColor());
				character.getPreview().drawPreview(batch, Rotation.BACK, this.getX(), this.getY(),
						this.getWidth(), this.getHeight());
			}
		};
		actor.setBounds(coords.x, coords.y, 128, 192);
		stage.addActor(actor);
	}
}
