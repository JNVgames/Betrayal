/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

public class Dungeon extends GameState {

    private MonsterGenerator mg;
    private Texture monster;

    private Group field_UI;

    private int floor, numPlayers, strongestPlayer;
    private float button_height = 150, button_width = Betrayal.WIDTH / 2;

    private enum Menu {
        MAIN(), ATTACK, ITEMS, EVENT_LOG
    }
    private Menu menu = Menu.MAIN;

    private Label.LabelStyle font;

    public Dungeon(GameStateManager gsm) {
        super(gsm);

        //this.floor = floor;
        //this.numPlayers = numPlayers;

        loadStage();
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

    private void loadStage() {
        field_UI = new Group();
        font = loadFont(70);
        loadTimer();
        loadMainMenu();
        loadPlayers();
    }
    private void changeUI(Menu m) {
        field_UI.clear();
        switch (m) {
            case MAIN:
                loadMainMenu();
                break;
            case ATTACK:
                loadAttackMenu();
                break;
            case ITEMS:
                //loadItemsMenu();
                break;
            case EVENT_LOG:
                //loadEventLog();
                break;
            default:
                break;
        }
    }

    // Helpers
    /** Returns the Hurtmold font with the specified font size
     * @param fontSize font size of hurtmold font
     * @return Hurtmold font with the specified font size */
    private Label.LabelStyle loadFont(int fontSize) {
        return Betrayal.getHurtmoldFontLabelStyle(fontSize);
    }
    private void loadTimer() {
        Label label = new Label("0:25", loadFont(70));
        label.setX((Betrayal.WIDTH - label.getWidth()) / 2);
        label.setY(Betrayal.HEIGHT - label.getHeight() - 20);
        stage.addActor(label);
    }
    private void loadPlayers() {
        Vector2[] playerCoords = new Vector2[5];

    }
    private void loadMainMenu() {
        Label field_UI_eventLog = new Label("Event Log", loadFont(67));
        field_UI_eventLog.setBounds(0, 0, button_width, button_height);
        field_UI_eventLog.setAlignment(Align.center);
        addButtonListener(field_UI_eventLog, Menu.EVENT_LOG);
        field_UI.addActor(field_UI_eventLog);

        Label field_UI_attack = new Label("Attack", font);
        field_UI_attack.setBounds(0, button_height, button_width, button_height);
        field_UI_attack.setAlignment(Align.center);
        addButtonListener(field_UI_attack, Menu.ATTACK);
        field_UI.addActor(field_UI_attack);

        Label field_UI_items = new Label("Items", font);
        field_UI_items.setBounds(button_width, button_height, button_width, button_height);
        field_UI_items.setAlignment(Align.center);
        addButtonListener(field_UI_items, Menu.ITEMS);
        field_UI.addActor(field_UI_items);

        Label tmp = new Label("Lobby (tmp)", loadFont(50));
        tmp.setBounds(button_width, 0, button_width, button_height);
        tmp.setAlignment(Align.center);
        tmp.layout();
        tmp.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < button_width && y > 0 && y < button_height)
                    gsm.setState(GameStateManager.State.LOBBY);
            }
        });
        field_UI.addActor(tmp);

        stage.addActor(field_UI);
    }
    private void loadAttackMenu() {
        Label field_UI_normalAttack = new Label("Normal Attack", loadFont(50));
        field_UI_normalAttack.setBounds(0, button_height, button_width, button_height);
        field_UI_normalAttack.setAlignment(Align.center);
        field_UI.addActor(field_UI_normalAttack);

        Label field_UI_skill = new Label("Skill", font);
        field_UI_skill.setBounds(button_width, button_height, button_width, button_height);
        field_UI_skill.setAlignment(Align.center);
        field_UI.addActor(field_UI_skill);

        Label field_UI_back = new Label("Back", font);
        field_UI_back.setBounds(button_width, 0, button_width, button_height);
        field_UI_back.setAlignment(Align.center);
        addButtonListener(field_UI_back, Menu.MAIN);
        field_UI.addActor(field_UI_back);

        stage.addActor(field_UI);
    }
    private void addButtonListener(Label button, final Menu menu) {
        button.layout();
        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < button_width && y > 0 && y < button_height) changeUI(menu);
            }
        });
    }

    /** Generates a random mob based on the floor the highest player in the party is currently on */
    public class MonsterGenerator {

        public MonsterGenerator() {

        }
    }
}
