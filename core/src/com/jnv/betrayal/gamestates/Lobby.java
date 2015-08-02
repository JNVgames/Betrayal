package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.handlers.screentouch.BetrayalGestureProcessor;
import com.jnv.betrayal.handlers.screentouch.BetrayalInput;
import com.jnv.betrayal.main.Betrayal;
/**
 * Copyright 2015, JNV Games, All rights reserved.
 */
public class Lobby extends GameState {

    private Image lobbyBackground, shopButton, settingsButton,statsButton,partyButton, inventoryButton;
    private Label.LabelStyle labelStyle;
    private Label lobby;
    private int buttonWidth,buttonHeight,spacing;

    public Lobby(GameStateManager gsm) {
        super(gsm);
        buttonHeight=150;
        buttonWidth=144;
        spacing=5;
        loadContent();
        loadFont();
    }

    private void loadContent(){
            loadBackground();
            loadShopButton();
            loadSettingsButton();
            loadPartyButton();
            loadStatsButton();
            loadInventoryButton();
     //       loadLobbyLabel(); ERror>/ FIX later

    }

    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle(40);
    }

    private void loadBackground() {
        lobbyBackground = new Image(Betrayal.res.getTexture("instructions-background"));
        lobbyBackground.layout();
        lobbyBackground.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
        stage.addActor(lobbyBackground);
    }

    /*
    private void loadLobbyLabel() {
        lobby = new Label("Lobby", labelStyle);
        lobby.setX(0);
        lobby.setY(Betrayal.HEIGHT - buttonHeight-lobby.getHeight());
        stage.addActor(lobby);
    }

   */

    private void loadShopButton() {
        shopButton = new Image(Betrayal.res.getTexture("lobby-shop"));
        shopButton.layout();
        shopButton.setBounds(0, Betrayal.HEIGHT-buttonHeight-5, buttonWidth, buttonHeight);
        shopButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
               new Shop(game);
            }
        });
        stage.addActor(shopButton);
    }
    private void loadInventoryButton() {
        inventoryButton = new Image(Betrayal.res.getTexture("lobby-inventory"));
        inventoryButton.layout();
        inventoryButton.setBounds(buttonWidth, Betrayal.HEIGHT-buttonHeight-spacing,
                buttonWidth, buttonHeight);
        inventoryButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new Inventory(game);
            }
        });
        stage.addActor(inventoryButton);
    }
    private void loadStatsButton() {
        statsButton = new Image(Betrayal.res.getTexture("lobby-stats"));
        statsButton.layout();
        statsButton.setBounds(buttonWidth*2, Betrayal.HEIGHT-buttonHeight-spacing,
                buttonWidth, buttonHeight);
        statsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new Stats(game);
            }
        });
        stage.addActor(statsButton);
    }

    private void loadPartyButton() {
        partyButton = new Image(Betrayal.res.getTexture("lobby-party"));
        partyButton.layout();
        partyButton.setBounds(buttonWidth*3, Betrayal.HEIGHT-buttonHeight-spacing,
                buttonWidth, buttonHeight);
        partyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(partyButton);
    }

    private void loadSettingsButton() {
        settingsButton = new Image(Betrayal.res.getTexture("lobby-settings"));
        settingsButton.layout();
        settingsButton.setBounds(buttonWidth*4,Betrayal.HEIGHT-buttonHeight-spacing,
                buttonWidth, buttonHeight);
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new LobbyOptions(game);
            }
        });
        stage.addActor(settingsButton);
    }
    public void update(float dt) {
        stage.act(dt);

    }

    public void handleInput() {
    }

    public void render() {
        sb.begin();
        sb.end();

        stage.draw();
    }

    public void dispose() {
    }

}
