/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jnv.betrayal.entities.*;
import com.jnv.betrayal.entities.Character;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

import java.util.List;

public class LoadGame extends GameState {

    private Image button_back, field_framePreview;
    private TextureRegion image_leftArrow, image_rightArrow;
    private Texture image_button_play;

    public LoadGame(GameStateManager gsm) {
        super(gsm);

        image_leftArrow = new TextureRegion(Betrayal.res.getTexture("arrow"));
        image_leftArrow.flip(true, false);
        image_rightArrow = new TextureRegion(Betrayal.res.getTexture("arrow"));

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

    // Helpers
    private Label.LabelStyle loadFont(int fontSize) {
        return Betrayal.getHurtmoldFontLabelStyle(fontSize);
    }
    private void loadStage() {
        loadBackButton();
        loadSavedSessions();
    }

    private void loadBackButton() {
        Group group_button_back = new Group();

        button_back = new Image(image_leftArrow);
        button_back.setHeight(60);
        button_back.setWidth(80);
        button_back.setX(10);
        button_back.setY(Betrayal.HEIGHT - button_back.getHeight() - 10);
        group_button_back.addActor(button_back);

        Label button_text_back = new Label("Back", loadFont(60));
        button_text_back.setX(button_back.getX() + button_back.getWidth() + 10);
        button_text_back.setY(button_back.getY());
        group_button_back.addActor(button_text_back);

        Actor button_back_clickArea = new Actor();
        button_back_clickArea.setWidth(button_back.getWidth() + button_text_back.getWidth() + 10);
        button_back_clickArea.setHeight(button_text_back.getHeight());
        button_back_clickArea.setX(button_back.getX());
        button_back_clickArea.setY(button_back.getY());
        button_back_clickArea.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateManager.State.MENU);
            }
        });
        group_button_back.addActor(button_back_clickArea);

        stage.addActor(group_button_back);
    }
    private void loadSavedSessions() {
        // TODO @vincent loads kinda slow and code is kinda long
        int counter = 1;

        for (Character c : Character.characters) {
            final Character character = c;
            Group preview = new Group();

            final SnapshotArray<TextureRegion[]> preview_charPics = c.preview.getFullPreview(0);
            final int i = counter;
            Actor preview_charPrev = new Actor() {
                public void draw(Batch sb, float pa) {
                    for (TextureRegion[] tr : preview_charPics) {
                        for (TextureRegion textureRegion : tr) {
                            if (textureRegion != null) {
                                sb.draw(textureRegion, 10,
                                        button_back.getY() - 230 * i + 5, 32 * 4, 48 * 4);
                            }
                        }
                    }
                }
            };
            preview_charPrev.setBounds(10, button_back.getY() - 230 * i + 5, 32 * 4, 48 * 4);
            preview.addActor(preview_charPrev);

            Label preview_name = new Label(c.getName(), loadFont(60));
            preview_name.setX(preview_charPrev.getX() + preview_charPrev.getWidth() + 30);
            preview_name.setY(preview_charPrev.getY() + preview_charPrev.getHeight()
                    - preview_name.getPrefHeight());
            preview_name.setColor(Color.WHITE);
            preview.addActor(preview_name);

            Label preview_class = new Label(c.job.toString(), loadFont(50));
            preview_class.setX(preview_name.getX());
            preview_class.setY(preview_name.getY() - 10 - preview_class.getPrefHeight());
            preview_class.setColor(Color.LIGHT_GRAY);
            preview.addActor(preview_class);

            Label preview_floor = new Label("FLOOR", loadFont(40));
            preview_floor.setX(Betrayal.WIDTH - 10 - preview_floor.getPrefWidth());
            preview_floor.setY(preview_charPrev.getY() + preview_charPrev.getHeight()
                    - preview_floor.getPrefHeight());
            preview_floor.setColor(Color.WHITE);
            preview.addActor(preview_floor);

            Label preview_floorNum =
                    new Label(Integer.toString(c.stats.getStat(Character.Stat.FLOOR)),
                            loadFont(100));
            preview_floorNum.setBounds(preview_floor.getX(), preview_charPrev.getY(),
                    preview_floor.getWidth(), preview_floor.getY() - 10 - preview_charPrev.getY());
            preview_floorNum.setColor(Color.LIGHT_GRAY);
            preview_floorNum.setAlignment(Align.center);
            preview.addActor(preview_floorNum);

            Actor preview_frame = new Actor();
            preview_frame.setBounds(5, button_back.getY() - 230 * i, Betrayal.WIDTH - 10,
                    preview_charPrev.getHeight() + 10);
            preview_frame.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    Character.currentCharacter = character;
                    gsm.setState(GameStateManager.State.LOBBY);
                }
            });
            preview.addActor(preview_frame);

            stage.addActor(preview);

            counter++;
        }
    }

}
