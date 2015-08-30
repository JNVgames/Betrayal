/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Target {

	private Actor actor;
	private TextureRegion selectedTexture;
	private boolean isSelected = false;
	private String name;

	public Target(Vector2 coords, float width, float height, String name, Stage stage,
				  BetrayalAssetManager res) {
		this(coords.x, coords.y, width, height, name, stage, res);
	}

	public Target(final float x, final float y, final float width, final float height, String name,
				  Stage stage, BetrayalAssetManager res) {
		this.name = name;
		selectedTexture = new TextureRegion(res.getTexture("instructions-background"));
		actor = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				if (isSelected) {
					batch.setColor(Color.WHITE);
					batch.draw(selectedTexture, x, y, width, height);
				}
			}
		};
		actor.setBounds(x, y, width, height);
		stage.addActor(actor);
	}

	// Getters
	public String getName() {
		return name;
	}

	public Actor getActor() {
		return actor;
	}

	public boolean isSelected() {
		return isSelected;
	}

	// Settesr
	public void remove() {
		actor.remove();
	}

	public void select() {
		isSelected = true;
	}

	public void unselect() {
		isSelected = false;
	}
}
