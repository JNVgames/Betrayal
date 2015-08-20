/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;

public class Options {
    private Stage stage;

    private Image background, mainMenuButton;
    private Actor mask;
    private Label.LabelStyle labelStyle;
    private Label title;
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
        mask.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeOptions();
            }
        });
        stage.addActor(mask);
    }
    private void loadMainMenuButton() {
        mainMenuButton = new Image(Betrayal.res.getTexture("main-menu"));
        mainMenuButton.layout();
        mainMenuButton.setBounds((Betrayal.WIDTH - mainMenuButton.getWidth()) / 2 + 100, 200, 312, 100);
        mainMenuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeOptions();
            }
        });
        stage.addActor(mainMenuButton);
    }

    private void loadTitle() {
        title = new Label("Options", labelStyle);
        title.setHeight(100);
        title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
        title.setY(Betrayal.HEIGHT - 300);
        stage.addActor(title);
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
                mainMenuButton.remove();
                title.remove();
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
                loadTitle();
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
        mainMenuButton.remove();
        title.remove();
        background.remove();
    }
}
