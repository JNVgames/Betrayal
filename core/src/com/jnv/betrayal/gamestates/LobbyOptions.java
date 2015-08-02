package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

/**
 * Copyright 2015, JNV Games, All rights reserved.
 */

public class LobbyOptions {
    private Stage stage;
    private Image lobbyButton, mainMenuButton, background;
    private Label.LabelStyle labelStyle;
    private Label title;
    private Actor mask;
    private Betrayal game;


    public LobbyOptions(Betrayal game) {
        this.game=game;
        stage = game.getStage();
        loadFont();
        loadButtons();
    }

    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle(40);
    }

    private void loadButtons() {
        loadMask();
        loadBackground();
        loadTitle();
        loadContent();
        loadReturnToLobbyButton();
        loadReturnToMainMenuButton();
    }

    private void loadMask() {
        mask = new Actor();
        mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
        stage.addActor(mask);
    }

    private void loadBackground() {
        background = new Image(Betrayal.res.getTexture("shop-background"));
        background.layout();
        background.setBounds(150, 150, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 300);
        stage.addActor(background);
    }

    private void loadTitle() {
        title = new Label("Options", labelStyle);
        title.setHeight(100);
        title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
        title.setY(Betrayal.HEIGHT - 250);
        stage.addActor(title);
    }

    private void loadReturnToLobbyButton() {
        lobbyButton = new Image(Betrayal.res.getTexture("back-to-lobby"));
        lobbyButton.layout();
        lobbyButton.setBounds((Betrayal.WIDTH-lobbyButton.getWidth())/2+100, 350, 312, 100);
        lobbyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeLobbyOptons();
            }
        });
        stage.addActor(lobbyButton);
    }
    private void loadReturnToMainMenuButton() {
        mainMenuButton = new Image(Betrayal.res.getTexture("main-menu"));
        mainMenuButton.layout();
        mainMenuButton.setBounds((Betrayal.WIDTH-mainMenuButton.getWidth())/2+100, 200, 312, 100);
        mainMenuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeLobbyOptons();
                game.gsm.setState(GameStateManager.State.MENU);
            }
        });
        stage.addActor( mainMenuButton);
    }

    private void loadContent() {

    }

    private void removeLobbyOptons() {
        mask.remove();
        title.remove();
        background.remove();
        lobbyButton.remove();
        mainMenuButton.remove();
    }
}
