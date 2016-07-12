/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

public class MonsterCard extends Card {

	private List<EventType> allActions;
	private int actionIndex;
	private String name;
	private int numAttackTargets;
	private Effect effect;
	public int effectCounter = 1;

	public MonsterCard(final float x, final float y, final float width, final float height,
					   final Monster monster, BetrayalAssetManager res) {
		super(x, y, width, height, res);
		name = monster.getNickname();
		baseHealth = currentHealth = monster.getHealth();
		baseAttack = currentAttack = monster.getAttack();
		baseDefense = currentDefense = monster.getDefense();
		numAttackTargets = monster.getNumTargets();
		this.effect = monster.getEffect();

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
		allActions = new ArrayList<EventType>();
		allActions.add(EventType.ATTACK);
		initializeCardListener();
	}

	public int getNumAttackTargets() {
		return numAttackTargets;
	}

	public boolean hasEffect(){
		return effect != null;
	}

	public Effect getEffect() {
		return effect;
	}

	public EventType getActionType() {
		return allActions.get(actionIndex);
	}

	public void nextActionIndex() {
		this.actionIndex = (actionIndex + 1) % allActions.size();
	}

	public String getName() {
		return name;
	}

	@Override
	public int getID() {
		return 0;
	}
}
