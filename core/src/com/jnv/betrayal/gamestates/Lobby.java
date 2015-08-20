/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.entities.Character;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

public class Lobby extends GameState {

    private Image lobbyBackground, shopButton, settingsButton,
            statsButton, partyButton, inventoryButton;
    private Label.LabelStyle labelStyle;
    private Label lobby;
    private int buttonWidth, buttonHeight, spacing;

    private Character character;

    // VINCENTS VARIABLES. LOOK AT SECOND NOTE TO SEE WHY ITS HERE
    private Texture image_button_play;
    private Actor button_play_now;

    public Lobby(GameStateManager gsm) {
        super(gsm);

        character = Character.currentCharacter;
        buttonHeight = 150;
        buttonWidth = 144;
        spacing = 5;
        loadContent();
        loadFont();
    }

    public void update(float dt) {
        stage.act(dt);
    }
    public void handleInput() {
    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }
    public void dispose() {
    }

    // Helpers
    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle(40);
    }
    private void loadBackground() {
        lobbyBackground = new Image(Betrayal.res.getTexture("instructions-background"));
        lobbyBackground.layout();
        lobbyBackground.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
        stage.addActor(lobbyBackground);
    }

    private void loadShopButton() {
        shopButton = new Image(Betrayal.res.getTexture("lobby-shop"));
        shopButton.layout();
        shopButton.setBounds(0, Betrayal.HEIGHT - buttonHeight - 5, buttonWidth, buttonHeight);
        shopButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new com.jnv.betrayal.popup.Shop(game);
            }
        });
        stage.addActor(shopButton);
    }
    private void loadInventoryButton() {
        inventoryButton = new Image(Betrayal.res.getTexture("lobby-inventory"));
        inventoryButton.layout();
        inventoryButton.setBounds(buttonWidth, Betrayal.HEIGHT - buttonHeight - spacing,
                buttonWidth, buttonHeight);
        inventoryButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new com.jnv.betrayal.popup.Inventory(game);
            }
        });
        stage.addActor(inventoryButton);
    }
    private void loadStatsButton() {
        statsButton = new Image(Betrayal.res.getTexture("lobby-stats"));
        statsButton.layout();
        statsButton.setBounds(buttonWidth * 2, Betrayal.HEIGHT - buttonHeight - spacing,
                buttonWidth, buttonHeight);
        statsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new com.jnv.betrayal.popup.Stats(game);
            }
        });
        stage.addActor(statsButton);
    }
    private void loadPartyButton() {
        partyButton = new Image(Betrayal.res.getTexture("lobby-party"));
        partyButton.layout();
        partyButton.setBounds(buttonWidth * 3, Betrayal.HEIGHT - buttonHeight - spacing,
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
        settingsButton.setBounds(buttonWidth * 4, Betrayal.HEIGHT - buttonHeight - spacing,
                buttonWidth, buttonHeight);
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new com.jnv.betrayal.popup.LobbyOptions(game);
            }
        });
        stage.addActor(settingsButton);
    }
    private void loadContent() {
        loadBackground();
        loadShopButton();
        loadSettingsButton();
        loadPartyButton();
        loadStatsButton();
        loadInventoryButton();
        //loadLobbyLabel(); ERror>/ FIX later

        // VINECNTS SHIT DELETE LOOK AT BOTTOM TO SEE WHY
        loadPlayNowButton();

    }

    /*
    private void loadLobbyLabel() {
        lobby = new Label("Lobby", labelStyle);
        lobby.setX(0);
        lobby.setY(Betrayal.HEIGHT - buttonHeight-lobby.getHeight());
        stage.addActor(lobby);
    }

   */

    // VINCENTS SHIT. LOADS THE PLAY BUTTON FOR DUNGEON TESTING. YOU CAN DELETE WHENEVER
    private void loadPlayNowButton() {
        image_button_play = Betrayal.res.getTexture("play-now");
        button_play_now = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(image_button_play, button_play_now.getX(), button_play_now.getY(),
                        button_play_now.getWidth(), button_play_now.getHeight());
            }
        };
        button_play_now.setWidth(512);
        button_play_now.setBounds((Betrayal.WIDTH - button_play_now.getWidth()) / 2, 20, 512, 144);
        button_play_now.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                image_button_play = Betrayal.res.getTexture("play-now-pressed");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x >= button_play_now.getX()
                        && x <= button_play_now.getX() + button_play_now.getWidth()
                        && y >= button_play_now.getY()
                        && y <= button_play_now.getY() + button_play_now.getHeight()) {
                    gsm.setState(GameStateManager.State.DUNGEON);
                }
                else image_button_play = Betrayal.res.getTexture("play-now");
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                image_button_play = Betrayal.res.getTexture("play-now");
            }
        });
        stage.addActor(button_play_now);
    }


}
