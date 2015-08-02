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

public class Lobby extends GameState {

    private Image lobbyBackground, shopButton, settingsButton,statsButton,partyButton;
    private Label.LabelStyle labelStyle;
    private Label lobby, content0, content1, content2, content3, content4;
    private Menu menu;
    private int currentContent, totalContent;

    public Lobby(GameStateManager gsm) {
        super(gsm);
        loadContent();
    }

    private void loadContent(){
            loadBackground();
            loadShopButton();
            loadSettingsButton();
            loadPartyButton();
            loadStatsButton();
            loadLobbyLabel();
    }

    private void loadBackground() {
        lobbyBackground = new Image(Betrayal.res.getTexture("instructions-background"));
        lobbyBackground.layout();
        lobbyBackground.setBounds(0,0, Betrayal.WIDTH, Betrayal.HEIGHT);
        stage.addActor(lobbyBackground);
    }
    private void loadLobbyLabel() {
        lobby = new Label("Lobby", labelStyle);
        lobby.setHeight(100);
        lobby.setX((Betrayal.WIDTH - lobby.getWidth()) / 2);
        lobby.setY(Betrayal.HEIGHT - 200);
        stage.addActor(lobby);
    }

    private void loadSettingsButton() {
        Image exitButton = new Image(Betrayal.res.getTexture("x"));
        exitButton.layout();
        exitButton.setBounds(620, 1180, 100, 100);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.MENU);
            }
        });
        stage.addActor(exitButton);
    }

    private void loadShopButton() {
        settingsButton = new Image(Betrayal.res.getTexture("x"));
        settingsButton.layout();
        settingsButton.setBounds(620, 1180, 100, 100);
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        stage.addActor(settingsButton);
    }

    private void loadStatsButton() {
        Image exitButton = new Image(Betrayal.res.getTexture("x"));
        exitButton.layout();
        exitButton.setBounds(620, 1180, 100, 100);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.MENU);
            }
        });
        stage.addActor(exitButton);
    }

    private void loadPartyButton() {
        Image exitButton = new Image(Betrayal.res.getTexture("x"));
        exitButton.layout();
        exitButton.setBounds(620, 1180, 100, 100);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.MENU);
            }
        });
        stage.addActor(exitButton);
    }
    public void update(float dt) {
        stage.act(dt);

    }

    public void handleInput() {
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.end();

        stage.draw();
    }

    public void dispose() {
    }

}
