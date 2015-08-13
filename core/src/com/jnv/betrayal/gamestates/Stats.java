/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.characterhandlers.CharacterPreview;
import com.jnv.betrayal.entities.Character;
import com.jnv.betrayal.characterhandlers.CharacterStats;
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

    public Stats(Betrayal game) {
        stage = game.getStage();
        characterStats = new Group();
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

    // TODO @JOEYPHAN PLS MERGE MAH CODE
    // VINCENTS STUFF. DISPLAYS THE CHARACTERS STATS ON THE STATS PAGE
    private void loadContent() {
        int rotatorIndent = 20;
        loadCharacterStats();
        loadCharacterPreview();
        characterStats.addActor(CharacterPreview.createRotators(charPreview.getX() + rotatorIndent,
                charPreview.getY() - 20, (charPreview.getWidth() - rotatorIndent * 2 + 30) / 2, 30));
        stage.addActor(characterStats);
    }
    private void loadCharacterStats() {
        yRef = title.getY();

        characterStatsLabel(characterStats, CharacterStats.Stat.FLOOR, yRef);
        characterStatsLabel(characterStats, CharacterStats.Stat.HEALTH, yRef);
        characterStatsLabel(characterStats, CharacterStats.Stat.DEFENSE, yRef);
        characterStatsLabel(characterStats, CharacterStats.Stat.ATTACK, yRef);
    }
    private void characterStatsLabel(Group group, CharacterStats.Stat stat, float yReference) {
        int fontSize = 40;
        Label statsText = new Label("", Betrayal.getHurtmoldFontLabelStyle(fontSize));
        statsText.setText(Character.currentCharacter.stats.toString(stat));
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
        Character.currentCharacter.preview.setRotation(0);
        charPreview = new Actor() {
            public void draw(Batch batch, float parentAlpha) {
                drawPreview(batch);
            }
        };
        charPreview.setBounds(background.getX() + (background.getWidth() - 32 * scale) / 2,
                yRef - 48 * scale - 30,
                32 * scale, 48 * scale);
        characterStats.addActor(charPreview);
    }
    private void drawPreview(Batch batch) {
        for (TextureRegion[] tr : Character.currentCharacter.preview.getFullPreview()) {
            for (int layers = 0; layers < CharacterPreview.SLOTS; layers++) {
                if (tr[layers] != null) {
                    batch.draw(tr[layers], charPreview.getX(), charPreview.getY(),
                            charPreview.getWidth(), charPreview.getHeight());
                }
            }
        }
    }
}
