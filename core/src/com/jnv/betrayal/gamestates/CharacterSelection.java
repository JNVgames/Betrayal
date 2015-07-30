package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

public class CharacterSelection extends GameState {

    public CharacterSelection(GameStateManager gsm) {
        super(gsm);
        Gdx.app.log("log", "CharacterSelection constructor");

        loadActors();
    }

    public void update(float dt) {
        stage.act(dt);
    }
    public void handleInput() {

    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0); // Set background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }
    public void dispose() {

    }

    // Helpers
    private void loadActors() {
        loadUsernameActors();
    }
    private void loadUsernameActors() {
        // Username "Name:" text
        FreeTypeFontGenerator generator = Betrayal.getHurtmoldFontGenerator();
        FreeTypeFontGenerator.FreeTypeFontParameter fontDetails =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontDetails.size = 60;
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = generator.generateFont(fontDetails);
        Label field_usernameLabel = new Label("Name: ", labelStyle);
        field_usernameLabel.setHeight(80);
        field_usernameLabel.setX(10);
        field_usernameLabel.setY(Betrayal.HEIGHT - field_usernameLabel.getHeight() * 2);
        stage.addActor(field_usernameLabel);

        // Username input text field
        TextField.TextFieldStyle tfs = new TextField.TextFieldStyle();
        tfs.font = generator.generateFont(fontDetails);
        tfs.messageFont = tfs.font;
        tfs.fontColor = Color.LIGHT_GRAY;
        tfs.messageFontColor = Color.GRAY;
        TextField field_usernameEnter = new TextField("", tfs);
        field_usernameEnter.setMessageText("Enter name here");
        field_usernameEnter.setBounds(10 + field_usernameLabel.getWidth() + 10, field_usernameLabel.getY(),
                Betrayal.WIDTH - 20 - field_usernameLabel.getWidth(), field_usernameLabel.getHeight());
        field_usernameEnter.setMaxLength(16);
        stage.addActor(field_usernameEnter);

        // Removes keyboard focus if tap isn't on a TextField
        stage.getRoot().addCaptureListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!(event.getTarget() instanceof TextField)) {
                    stage.setKeyboardFocus(null);
                    Gdx.input.setOnscreenKeyboardVisible(false);
                }
                return false;
            }
        });

        // Create image preview box
        float scale = 0.8f;
        Image image = new Image();
        image.setBounds(10, Betrayal.HEIGHT - 30 - field_usernameLabel.getHeight() * 3 - 720 * scale,
                480 * scale, 720 * scale);
        stage.addActor(image);

    }
}
