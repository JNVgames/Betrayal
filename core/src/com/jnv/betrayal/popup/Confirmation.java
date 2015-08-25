package com.jnv.betrayal.popup;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;

//TODO; add boolean
//TODO: GET THE @FANGOT BACK TO WORK 8/11
public class Confirmation extends Popup {

    private Image yesButton, noButton, background;
    private Label title;
    private Actor mask;
    private String string;

    public Confirmation(Betrayal game, String string) {
        super(game);
        this.string = string;
        loadButtons();
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
        background = new Image(res.getTexture("confirmation-background"));
        background.layout();
        background.setBounds(150, 500, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 1000);
        stage.addActor(background);
    }

    private void loadTitle() {
        title = new Label(string, Betrayal.getFont(40));
        title.setHeight(100);
        title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
        title.setY(Betrayal.HEIGHT / 2 + 50);
        stage.addActor(title);
    }

    private void loadAnswer() {
        yesButton = new Image(res.getTexture("yes"));
        yesButton.layout();
        yesButton.setBounds(Betrayal.WIDTH / 2 - 175,
                Betrayal.HEIGHT / 2 - 100, 150, 75);
        yesButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                doSomething();
                removeConfirmation();
            }
        });
        stage.addActor(yesButton);

        noButton = new Image(res.getTexture("no"));
        noButton.layout();
        noButton.setBounds(Betrayal.WIDTH / 2 + 25,
                Betrayal.HEIGHT / 2 - 100, 150, 75);
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

    public void doSomething() {
    }
}
