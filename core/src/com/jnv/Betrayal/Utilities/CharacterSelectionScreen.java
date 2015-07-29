package com.jnv.betrayal.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jnv.betrayal.main.Betrayal;

public class CharacterSelectionScreen {

    private static Stage stage;

    public static void loadStage(Stage s) {
        stage = s;
    }

    public static void createScreen() {
        // Create enter username text
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/HURTMOLD.ttf"));
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter ftfp =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        ftfp.size = 60;
        labelStyle.font = generator.generateFont(ftfp);
        Label field_usernameLabel = new Label("Name: ", labelStyle);
        field_usernameLabel.setHeight(80);
        field_usernameLabel.setX(10);
        field_usernameLabel.setY(Betrayal.HEIGHT - field_usernameLabel.getHeight() * 2);
        stage.addActor(field_usernameLabel);

        // Create enter username text input field
        TextField.TextFieldStyle tfs = new TextField.TextFieldStyle();
        tfs.font = generator.generateFont(ftfp);
        tfs.messageFont = tfs.font;
        tfs.fontColor = Color.LIGHT_GRAY;
        tfs.messageFontColor = Color.GRAY;
        TextField field_usernameEnter = new TextField("", tfs);
        field_usernameEnter.setMessageText("Enter name here");
        field_usernameEnter.setBounds(10 + field_usernameLabel.getWidth() + 10, field_usernameLabel.getY(),
                Betrayal.WIDTH - 10 - field_usernameLabel.getWidth(), field_usernameLabel.getHeight());
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

    }
}
