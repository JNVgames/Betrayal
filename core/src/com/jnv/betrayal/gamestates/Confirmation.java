package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;

//TODO; add boolean
//TODO: GET THE @FANGOT BACK TO WORK 8/11
public class Confirmation {
    private Stage stage;
    private Image lobbyButton, yesButton,noButton, background;
    private Label.LabelStyle labelStyle;
    private Label title;
    private Actor mask;
    private Betrayal game;

    public Confirmation(Betrayal game) {
        this.game=game;
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
        loadAnswer();
    }

    private void loadMask() {
        mask = new Actor();
        mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
        stage.addActor(mask);
    }

    private void loadBackground() {
        background = new Image(Betrayal.res.getTexture("confirmation-background"));
        background.layout();
        background.setBounds(150, 500, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 1000);
        stage.addActor(background);
    }

    private void loadTitle() {
        title = new Label("Are You Sure?", labelStyle);
        title.setHeight(100);
        title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
        title.setY(Betrayal.HEIGHT / 2 + 50);
        stage.addActor(title);
    }

    private void loadAnswer() {
        yesButton = new Image(Betrayal.res.getTexture("yes"));
        yesButton.layout();
        yesButton.setBounds(Betrayal.WIDTH/2-175,
                Betrayal.HEIGHT/2-100, 150, 75);
        yesButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeConfirmation();
            }
        });
        stage.addActor(yesButton);

        noButton = new Image(Betrayal.res.getTexture("no"));
        noButton.layout();
        noButton.setBounds(Betrayal.WIDTH/2+25,
                Betrayal.HEIGHT/2-100,150, 75);
        noButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeConfirmation();
            }
        });
        stage.addActor( noButton);
    }

    private void removeConfirmation() {
        mask.remove();
        title.remove();
        background.remove();
        yesButton.remove();
        noButton.remove();
    }
}
