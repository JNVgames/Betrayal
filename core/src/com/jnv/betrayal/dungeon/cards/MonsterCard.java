package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class MonsterCard extends Card {

	private List<EventType> allActions;
	private int actionIndex;
	private String name;
	private int numAttackTargets;
	private Effect effect;
	private Texture skillTexture;
	public int effectCounter = 1;
	private Monster monster;

	public MonsterCard(final float x, final float y, final float width, final float height,
					   Monster monster, BetrayalAssetManager res) {
		super(x, y, width, height, res);
		this.monster = monster;
		name = monster.getNickname();
		baseHealth = currentHealth = monster.getHealth();
		baseAttack = currentAttack = monster.getAttack();
		baseDefense = currentDefense = monster.getDefense();
		numAttackTargets = monster.getNumTargets();
		this.effect = monster.getEffect();
		this.skillTexture = monster.getSkillTexture();

		cardImage = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
				batch.draw(MonsterCard.this.monster.getMonsterTexture(), getX(), getY(), width, height);
			}
		};
		cardImage.setBounds(0, 0, width, height);
		group.addActor(cardImage);
		cardImage.toBack();
		allActions = new ArrayList<EventType>();
		allActions.add(EventType.ATTACK);
		initializeCardListener();
		cardName = new Label(name, FontManager.getFont40());
		cardName.setX(50);
		cardName.setY(group.getHeight() - 10);
		group.addActor(cardName);
	}

	public int getNumAttackTargets() {
		return numAttackTargets;
	}

	public boolean hasEffect() {
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

	public Texture getSkillTexture() {
		return skillTexture;
	}

	public void multiplyHealth(int mFactor) {
		baseHealth = currentHealth = mFactor * currentHealth;
	}

	public Monster getMonster() {
		return monster;
	}

	@Override
	public int getID() {
		return monster.getID();
	}

	@Override
	public String toString() {
		return "MonsterCard{" +
				"name='" + name + '\'' +
				'}';
	}
}
