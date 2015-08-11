/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.entities.Character;
import com.jnv.betrayal.main.Betrayal;

public class Stats {
    private Stage stage;
    private Image lobbyButton, background;
    private Label.LabelStyle labelStyle;
    private Label title;
    private Actor mask;

    // VINCENTS VARIABLE
    private Group characterStats;

    public Stats(Betrayal game) {
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

    private void loadContent() {

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
    private void loadCharacterStats() {
        characterStats = new Group();
        float yReference = title.getY();

        yReference = characterStatsLabel(characterStats, Character.Stat.FLOOR, yReference).getY();
        yReference = characterStatsLabel(characterStats, Character.Stat.HEALTH, yReference).getY();
        yReference = characterStatsLabel(characterStats, Character.Stat.DEFENSE, yReference).getY();
        characterStatsLabel(characterStats, Character.Stat.ATTACK, yReference).getY();
        stage.addActor(characterStats);
    }
    private Label characterStatsLabel(Group group, Character.Stat stat, float yReference) {
        int fontSize = 40;
        Label statsText = new Label("", Betrayal.getHurtmoldFontLabelStyle(fontSize));
        statsText.setText(Character.currentCharacter.stats.toString(stat));
        statsText.layout();
        statsText.setX(background.getX() + 30);
        statsText.setY(yReference - fontSize - 60);
        statsText.setWidth(statsText.getPrefWidth());
        statsText.setHeight(fontSize);
        group.addActor(statsText);
        return statsText;
    }
}
