package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.resources.BetrayalAssetManager;

import javafx.stage.Stage;

public class HealthBarManager /*implements Screen*/ {
    /*
    private Stage stage;
    private Image healthBarBackground, greenBar, yellowBar, redBar;
    private BetrayalAssetManager res;
    private float currentHealthPercentage, finalHealthPercentage ;
    private Texture health_bar_background, green_bar, yellow_bar, red_bar;
    private int xPos, yPos;

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
    public void show() {

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
        */
    }
/*
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
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
*/
