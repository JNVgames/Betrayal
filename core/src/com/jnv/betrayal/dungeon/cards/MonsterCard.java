/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.dungeon.ActionHandler.Action;
import com.jnv.betrayal.dungeon.ActionHandler.ActionType;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.Dimension;

import java.util.ArrayList;
import java.util.List;

public class MonsterCard extends Card {

	private List<ActionType> allActions;
	private int actionIndex;

	public MonsterCard(Dimension dimension, Monster monster, BetrayalAssetManager res) {
		this(dimension.getX(), dimension.getY(), dimension.getWidth(), dimension.getHeight(),
				monster, res);
	}

	public MonsterCard(final float x, final float y, final float width, final float height,
					   final Monster monster, BetrayalAssetManager res) {
		super(x, y, width, height, res);
		baseHealth = currentHealth = monster.getHealth();
		baseAttack = currentAttack = monster.getAttack();
		baseDefense = currentDefense = monster.getDefense();
		cardImage = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
				batch.draw(monster.getMonsterTexture(), getX(), getY(), width, height);
			}
		};
		cardImage.setBounds(0, 0, width, height);
		group.addActor(cardImage);
		cardImage.toBack();
		allActions = new ArrayList<ActionType>();
		allActions.add(ActionType.ATTACK);
	}

	public ActionType getActionType() {
		return allActions.get(actionIndex);
	}

	public void nextActionIndex() {
		this.actionIndex = (actionIndex + 1) % allActions.size();
	}
}
