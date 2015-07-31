package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;

/**
 * Copyright 2015, JNV Games, All rights reserved.
 */

public class Options {
    private Stage stage;

    private Image background;
    private Actor mask;
    private Label.LabelStyle labelStyle;
    private Label options, mainMenu;
    private int currentContent, totalContent;


    public Options(Betrayal game) {
        stage = game.getStage();
        loadFont();
        currentContent = 0;
        totalContent = 3;
        loadButtons();
    }

    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle(60);
    }

    private void loadButtons() {
        loadMask();
        loadBackground();
        loadContent();
    }
    private void loadMask(){
        mask = new Actor();
        mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
        stage.addActor(mask);
    }
    private void loadMainMenuButton() {
        mainMenu = new Label("Main Menu", labelStyle);
        mainMenu.setX((Betrayal.WIDTH - mainMenu.getWidth()) / 2);
        mainMenu.setY(Betrayal.HEIGHT - mainMenu.getHeight() - 1000);
        mainMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeOptions();
            }
        });
        stage.addActor(mainMenu);
    }

    private void loadOptions() {
        options = new Label("Options", labelStyle);
        options.setHeight(100);
        options.setX((Betrayal.WIDTH - options.getWidth()) / 2);
        options.setY(Betrayal.HEIGHT - 300);
        stage.addActor(options);
    }

    private void loadBackground() {
        background = new Image(Betrayal.res.getTexture("instructions-background"));
        background.layout();
        background.setBounds(150, 150, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 300);
        stage.addActor(background);
    }

    private void removeCurrentContent() {
        switch (currentContent) {
            case 0:
                mainMenu.remove();
                options.remove();
                break;
            case 1:
                break;
            default:
                Gdx.app.log("content", "should not happen");
                break;
        }
    }

    private void loadContent() {
        switch (currentContent) {
            case 0:
                loadMainMenuButton();
                loadOptions();
                break;
            case 1:
                break;
            default:
                Gdx.app.log("content", "should not happen");
                break;
        }
    }

    private void removeOptions() {
        removeCurrentContent();
        mask.remove();
        mainMenu.remove();
        options.remove();
        background.remove();
    }
}
