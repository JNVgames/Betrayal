/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.renamedthings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.ui.Image;

/**
 * Health bar for cards
 */
public class HealthBar extends Group {

	private Image healthBarBackground, healthBar;
	private final Drawable greenBar, yellowBar, redBar;
	private int currentHealthPercentage, finalHealthPercentage;

	public HealthBar(float topX, float leftY, BetrayalAssetManager res) {
		// Calibrate health bar coordinates
		float x = topX + 50;
		float y = leftY - 25;

		// Initialize health drawables
		greenBar = new TextureRegionDrawable(new TextureRegion(res.getTexture("green-bar")));
		yellowBar = new TextureRegionDrawable(new TextureRegion(res.getTexture("yellow-bar")));
		redBar = new TextureRegionDrawable(new TextureRegion(res.getTexture("red-bar")));

		// Initialize health bar image actors
		healthBarBackground = new Image(res.getTexture("bar"));
		healthBar = new Image(greenBar);

		// Health percentage starts at full
		currentHealthPercentage = 100;
		initialize(x, y);

		// Add actors to the group
		addActor(healthBarBackground);
		addActor(healthBar);
	}

	private void initialize(float x, float y) {
		healthBarBackground.setBounds(x, y, 227, 25);
		healthBarBackground.addAction(Actions.alpha(0));
		healthBarBackground.addAction(Actions.delay(1, Actions.fadeIn(2)));
		healthBar.setBounds(x + 10, y + 8, 200, 8);
		healthBar.addAction(Actions.alpha(0));
		healthBar.addAction(Actions.delay(1, Actions.fadeIn(2)));
	}

	public void setNewHealthPercent(int newHealthPercent) {
		//if (newHealthPercent > 100) newHealthPercent = 100;
		//if (newHealthPercent < 0) newHealthPercent = 0;
		if (newHealthPercent > 100 || newHealthPercent < 0)
			throw new AssertionError("New health percent is out of bounds (0-100): " + newHealthPercent);
		finalHealthPercentage = newHealthPercent;
	}

	public void act(float delta) {
		update();
		super.act(delta);
	}

	public void draw(Batch batch, float parentAlpha) {
		// Make sure the right color is used for health bar
		if (currentHealthPercentage < 25 && healthBar.getDrawable() != redBar) {
			healthBar.setDrawable(redBar);
		} else if (currentHealthPercentage >= 25 && currentHealthPercentage < 50
				&& healthBar.getDrawable() != yellowBar) {
			healthBar.setDrawable(yellowBar);
		} else if (currentHealthPercentage >= 50 && healthBar.getDrawable() != greenBar) {
			healthBar.setDrawable(greenBar);
		}
		super.draw(batch, parentAlpha);
	}

	public void update() {
		if (currentHealthPercentage > 100 || currentHealthPercentage < 0)
			throw new AssertionError("Current health percent is out of bounds (0-100): " + currentHealthPercentage);
		// If current HP is less than final HP
		if (currentHealthPercentage < finalHealthPercentage) {
			if (currentHealthPercentage < 25) {
				healthBar.setWidth((currentHealthPercentage++) * 2); //increase width by 2 pixels a time cuz full health is 200
			} else if (currentHealthPercentage >= 25 && currentHealthPercentage < 50) {
				healthBar.setWidth((currentHealthPercentage++) * 2);
			} else if (currentHealthPercentage >= 50) {
				healthBar.setWidth((currentHealthPercentage++) * 2);
			}
		}
		// If current HP is greater than final HP
		else if (currentHealthPercentage > finalHealthPercentage) {
			if (currentHealthPercentage < 25) {
				healthBar.setWidth((currentHealthPercentage--) * 2); //increase width by 2 pixels a time cuz full health is 200
			} else if (currentHealthPercentage >= 25 && currentHealthPercentage < 50) {
				healthBar.setWidth((currentHealthPercentage--) * 2);
			} else if (currentHealthPercentage >= 50) {
				healthBar.setWidth((currentHealthPercentage--) * 2);
			}
		}
		// Make sure health percent isn't out of bounds
		if (currentHealthPercentage > 100) currentHealthPercentage = 100;
		if (currentHealthPercentage < 0) currentHealthPercentage = 0;
	}
}

/*	Joey's code for reference
    public HealthBarManager(BetrayalAssetManager res, Stage stage, int xPos, int yPos) {
        this.stage = stage;
        this.xPos = xPos+50;
        this.yPos = yPos-25;
        health_bar_background = new Texture(res.get Texture("bar"));
        health_bar_background.setWidth = 227;
        health_bar_background.setHeight = 25;
        green_bar = new Texture(res.get Texture("green-bar"));
        .setHeight = 8;
        .setWidth = currentHealthPercentage;
        yellow_bar = new Texture(res.getTexture("green-bar"));
        .setHeight = 8;
        .setWidth = currentHealthPercentage;
        red_bar = new Texture(res.getTexture("green-bar"));
        .setHeight = 8;
        .setWidth = currentHealthPercentage;
        currentHealthPercentage = 100;
    }

    @Override
    public void render(float delta) {
        health_bar_background.draw(spritebatch, xPos, yPos);
        if (currentHealthPercentage<25){
            red_bar.draw(spritebatch, xPos+10, yPos+8 );
        }else if(currentHealthPercentage >=25 && currentHealthPercentage<50 ){
            yellow_bar.draw(spritebatch, xPos+10, yPos+8 );
        } else if (currentHealthPercentage >=50){
            green_bar.draw(spritebatch, xPos + 10, yPos + 8);
        }

        update(100, 0);
    }

    public void create(){ //creates health bar at full health initially + background
        /*
        health_bar_background.layout();
        health_bar_background.setBounds(xPos, yPos, 227, 25);
        health_bar_background.addAction(Actions.alpha(0));
        health_bar_background.addAction(Actions.delay(1, Actions.fadeIn(2)));
        stage.addActor(health_bar_background);

        green_bar.layout();
        green_bar.setBounds(xPos+10, yPos+8, 200, 8);
        green_bar.addAction(Actions.alpha(0));
        green_bar.addAction(Actions.delay(1, Actions.fadeIn(2)));
        stage.addActor(green_bar);
	}
    private void update(float currentHealthPercentage, float finalHealthPercentage){
        if(currentHealthPercentage < finalHealthPercentage){
            if (currentHealthPercentage<25){
                green_bar.setWidth((currentHealthPercentage+1)*2; //increase width by 2 pixels a time cuz full health is 200
            }else if(currentHealthPercentage >=25 && currentHealthPercentage<50 ){
                yellow_bar.setWidth((currentHealthPercentage+1)*2;
            }else if(currentHealthPercentage >=50){
                green_bar.setWidth((currentHealthPercentage+1)*2;
            }
        }else if(currentHealthPercentage > finalHealthPercentage){
            if (currentHealthPercentage<25){
                green_bar.setWidth((currentHealthPercentage-1)*2; //increase width by 2 pixels a time cuz full health is 200
            }else if(currentHealthPercentage >=25 && currentHealthPercentage<50 ){
                yellow_bar.setWidth((currentHealthPercentage-1)*2;
            }else if(currentHealthPercentage >=50){
                green_bar.setWidth((currentHealthPercentage-1)*2;
            }
        }
    }
}
*/
