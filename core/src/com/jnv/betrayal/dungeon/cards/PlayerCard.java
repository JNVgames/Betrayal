/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.Job;
import com.jnv.betrayal.character.Preview;
import com.jnv.betrayal.character.utils.Jobs;
import com.jnv.betrayal.character.utils.Rotation;
import com.jnv.betrayal.dungeon.utils.DungeonCoords;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.Dimension;

import java.util.Random;

public class PlayerCard extends Card {

	private Preview preview;
	private Character character;
	private Jobs characterJob;

	public PlayerCard(Vector2 coords, Character character,
					  BetrayalAssetManager res) {
		this(coords.x, coords.y, character, res);
	}

	public PlayerCard(float x, float y, Character character,
					  BetrayalAssetManager res) {
		super(new Dimension(x, y, DungeonCoords.PLAYER_WIDTH, DungeonCoords.PLAYER_HEIGHT), res);
		this.character = character;
		baseHealth = currentHealth = character.stats.getTotalHealth();
		baseAttack = currentAttack = character.stats.getTotalAttack();
		baseDefense = currentDefense = character.stats.getTotalDefense();
		characterJob = character.job.getJob();
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
		initializeCardListener();
	}

	public String getName() {
		return character.getName();
	}

	public void levelUpCharacter() {
		character.stats.advanceFloor();
	}

	public void getReward(){
		character.inventory.addGold(field.reward);
	}

	public boolean hasCloak() {
		return character.equips.isCloakSlotEmpty();
	}

	public void removeCloak() {
		character.equips.removeCloak();
	}

	public void multiplyHealth(){
		baseHealth = currentHealth = (int) (Math.ceil(currentHealth * 1.25));
	}

	public void multiplyAttack(){
		baseAttack = currentAttack = (int)(Math.ceil(currentAttack * 1.25));
	}

	public void multiplyDefense(){
		baseDefense = currentDefense = (int)(Math.ceil(currentDefense * 1.25));
	}

	public Jobs getJob(){
		return characterJob;
	}

	public void flee() {
		// Flee Successful
		field.removePlayerCard(this);

		Runnable r = new Runnable() {
			@Override
			public void run() {
				new OKPopup(field.game, "Flee Successful") {
					@Override
					public void onConfirm() {
						field.getClientCharacter().getRoom().updateServerCharacters();
						field.game.gsm.setState(GameStateManager.State.LOBBY);
					}
				};
			}
		};
		getCardImage().addAction(Actions.delay(2f, Actions.run(r)));
	}

	@Override
	public int getID() {
		return character.getId();
	}

	public static boolean canFlee(int x){
		Random random = new Random();
		int rand = random.nextInt(4) + 1;
		return x >= rand;
	}

	@Override
	public String toString() {
		return "PlayerCard{" +
				"name=" + character.getName() +
				'}';
	}
}
