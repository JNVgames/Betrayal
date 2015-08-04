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
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

public class LoadGame extends GameState {

    private Label.LabelStyle labelStyle;

    private Actor field_headPreview, field_armorPreview, button_play_now;
    private Label field_username, label_jobDescription;
    private Image button_back, field_framePreview;
    private TextureRegion image_leftArrow, image_rightArrow;
    private Texture image_button_play;

    public LoadGame(GameStateManager gsm) {
        super(gsm);

        image_leftArrow = new TextureRegion(Betrayal.res.getTexture("arrow"));
        image_leftArrow.flip(true, false);
        image_rightArrow = new TextureRegion(Betrayal.res.getTexture("arrow"));

        loadFont();
        loadStage();
    }

    public void update(float dt) {
        CharacterSelection.updateJobDescription(label_jobDescription);
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

    public static boolean previouslyPlayed() {
        return !(CharacterSelection.getCharacter() == null);
    }

    // Helpers
    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle(60);
    }
    private void loadStage() {
        loadBackButton();
        loadPlayNowButton();
        loadUsername();
        loadImagePreview();
        loadPreviewRotators();
        loadJobDescription();
    }
    private void loadUsername() {
        // Username "Name:" text
        field_username = new Label("Name: " + CharacterSelection.getCharacter().getName(), labelStyle);
        field_username.setX(10);
        field_username.setY(button_back.getY() - button_back.getHeight() - 10);
        stage.addActor(field_username);
    }

    private void loadImagePreview() {
        loadPreviewFrame();
        loadHeadPreview();
        loadArmorPreview();
    }
    private void loadPreviewFrame() {
        field_framePreview = new Image();
        field_framePreview.setWidth(384);
        field_framePreview.setHeight(576);
        field_framePreview.setX(10);
        field_framePreview.setY(field_username.getY() - field_username.getHeight()
                - field_framePreview.getHeight());
        stage.addActor(field_framePreview);
    }
    private void loadHeadPreview() {
        field_headPreview = new Actor() {
            public void draw(Batch batch, float parentAlpha) {
                sb.draw(CharacterSelection.getCharacter().getArmorPreview(), field_armorPreview.getX(),
                        field_armorPreview.getY(), field_armorPreview.getWidth(),
                        field_armorPreview.getHeight());
            }
        };
        field_headPreview.setWidth(384);
        field_headPreview.setHeight(576);
        field_headPreview.setX(field_framePreview.getX());
        field_headPreview.setY(field_framePreview.getY());
        stage.addActor(field_headPreview);
    }
    private void loadArmorPreview() {
        field_armorPreview = new Actor() {
            public void draw(Batch batch, float parentAlpha) {
                sb.draw(CharacterSelection.getCharacter().getHeadPreview(), field_headPreview.getX(),
                        field_headPreview.getY(), field_headPreview.getWidth(),
                        field_headPreview.getHeight());
            }
        };
        field_armorPreview.setWidth(384);
        field_armorPreview.setHeight(576);
        field_armorPreview.setX(field_framePreview.getX());
        field_armorPreview.setY(field_framePreview.getY());
        stage.addActor(field_armorPreview);
    }
    private void loadPreviewRotators() {
        int gap = 60, padding = 30;
        Group group_previewRotators = new Group();

        Image previewRotators_leftArrow = new Image(image_leftArrow);
        previewRotators_leftArrow.setHeight(60);
        previewRotators_leftArrow.setWidth((field_framePreview.getWidth() - gap - padding * 2) / 2);
        previewRotators_leftArrow.setX(field_framePreview.getX() + padding);
        previewRotators_leftArrow.setY(field_framePreview.getY()
                - previewRotators_leftArrow.getHeight() - 10);
        previewRotators_leftArrow.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                CharacterSelection.getCharacter().rotateLeft();
            }
        });
        group_previewRotators.addActor(previewRotators_leftArrow);

        Image previewRotators_rightArrow = new Image(image_rightArrow);
        previewRotators_rightArrow.setHeight(previewRotators_leftArrow.getHeight());
        previewRotators_rightArrow.setWidth(previewRotators_leftArrow.getWidth());
        previewRotators_rightArrow.setX(previewRotators_leftArrow.getX()
                + previewRotators_leftArrow.getWidth() + gap);
        previewRotators_rightArrow.setY(previewRotators_leftArrow.getY());
        group_previewRotators.addActor(previewRotators_rightArrow);
        previewRotators_rightArrow.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                CharacterSelection.getCharacter().rotateRight();
            }
        });
        stage.addActor(group_previewRotators);
    }
    private void loadBackButton() {
        Group group_button_back = new Group();

        button_back = new Image(image_leftArrow);
        button_back.setHeight(60);
        button_back.setWidth(80);
        button_back.setX(10);
        button_back.setY(Betrayal.HEIGHT - button_back.getHeight() - 10);
        group_button_back.addActor(button_back);

        Label button_text_back = new Label("Back", labelStyle);
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
    private void loadJobDescription() {
        Label.LabelStyle font_jobDescription = Betrayal.getHurtmoldFontLabelStyle(30);
        label_jobDescription = new Label("", font_jobDescription);
        label_jobDescription.setHeight(field_framePreview.getY() - 80
                - label_jobDescription.getHeight()
                - (button_play_now.getY() + button_play_now.getHeight() + 10));
        label_jobDescription.setWidth(Betrayal.WIDTH - 20);
        label_jobDescription.setX(field_framePreview.getX());
        label_jobDescription.setY(field_framePreview.getY() - 80 - label_jobDescription.getHeight());
        label_jobDescription.setAlignment(Align.topLeft);
        stage.addActor(label_jobDescription);
    }
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
                    gsm.setState(GameStateManager.State.LOBBY);
                } else image_button_play = Betrayal.res.getTexture("play-now");
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                image_button_play = Betrayal.res.getTexture("play-now");
            }
        });
        stage.addActor(button_play_now);
    }

}
