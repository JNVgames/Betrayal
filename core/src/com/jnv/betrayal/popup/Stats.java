/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.Network.Player;
import com.jnv.betrayal.character.Preview;
import com.jnv.betrayal.main.Betrayal;

public class Stats {

    private Stage stage;
    private Image lobbyButton, background;
    private Label.LabelStyle labelStyle;
    private Label title;
    private Actor mask;

    // VINCENTS VARIABLE
    private Group characterStats;
    private float yRef;
    private Actor charPreview;
    private Player player;

    public Stats(Betrayal game) {
        stage = game.getStage();
        characterStats = new Group();
        loadFont();
        loadButtons();
        player = game.player;
    }

    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle(40);
    }
    private void loadContent() {
        int rotatorIndent = 20;
        loadCharacterStats();
        loadCharacterPreview();
        Preview preview = new Preview(player.currentCharacter);
        characterStats.addActor(preview.createRotators(charPreview.getX() + rotatorIndent,
                charPreview.getY() - 20, (charPreview.getWidth() - (rotatorIndent * 2 + 30)) / 2, 30));
        stage.addActor(characterStats);
    }

    // Helpers
    private void loadButtons() {
        loadMask();
        loadBackground();
        loadTitle();
        loadContent();
        loadCharacterStats();
        loadReturnToLobbyButton();
    }
    private void loadMask() {
        mask = new Actor();
        mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
        mask.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeStats();
            }
        });
        stage.addActor(mask);
    }
    private void loadBackground() {
        background = new Image(Betrayal.res.getTexture("shop-background"));
        background.layout();
        background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
        stage.addActor(background);
    }
    private void loadTitle() {
        title = new Label(" My Stats", labelStyle);
        title.setHeight(100);
        title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
        title.setY(Betrayal.HEIGHT - 200);
        stage.addActor(title);
    }
    private void loadCharacterStats() {
        yRef = title.getY();

        characterStatsLabel(characterStats, com.jnv.betrayal.character.Stats.Stat.FLOOR, yRef);
        characterStatsLabel(characterStats, com.jnv.betrayal.character.Stats.Stat.HEALTH, yRef);
        characterStatsLabel(characterStats, com.jnv.betrayal.character.Stats.Stat.DEFENSE, yRef);
        characterStatsLabel(characterStats, com.jnv.betrayal.character.Stats.Stat.ATTACK, yRef);
    }
    private void loadReturnToLobbyButton() {
        lobbyButton = new Image(Betrayal.res.getTexture("back-to-lobby"));
        lobbyButton.layout();
        lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 110, 312, 100);
        lobbyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeStats();
            }
        });
        stage.addActor(lobbyButton);
    }
    private void removeStats() {
        mask.remove();
        title.remove();
        background.remove();
        lobbyButton.remove();

        // vincents variable
        characterStats.remove();
    }
    private void characterStatsLabel(Group group, com.jnv.betrayal.character.Stats.Stat stat, float yReference) {
        int fontSize = 40;
        Label statsText = new Label("", Betrayal.getHurtmoldFontLabelStyle(fontSize));
        statsText.setText(player.currentCharacter.stats.toString(stat));
        statsText.layout();
        statsText.setX(background.getX() + 30);
        statsText.setY(yReference - fontSize - 30);
        yRef = statsText.getY();
        statsText.setWidth(statsText.getPrefWidth());
        statsText.setHeight(fontSize);
        group.addActor(statsText);
    }
    private void loadCharacterPreview() {
        int scale = 8;
        player.currentCharacter.preview.setRotation(0);
        charPreview = new Actor() {
            public void draw(Batch batch, float parentAlpha) {
                drawPreview(batch);
            }
        };
        charPreview.setBounds(background.getX() + (background.getWidth() - 32 * scale) / 2,
                yRef - 48 * scale - 50,
                32 * scale, 48 * scale);
        characterStats.addActor(charPreview);
    }
    private void drawPreview(Batch batch) {
        player.currentCharacter.preview.drawPreview(batch, charPreview.getX(), charPreview.getY(),
                charPreview.getWidth(), charPreview.getHeight());
    }
}
