package com.jnv.betrayal.popup;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;

public class FriendsList {
    private Stage stage;
    private Image lobbyButton, background, background2;
    private Label.LabelStyle labelStyle;
    private Label title, title2;
    private Actor mask;

    // VINCENTS VARRIES
    private Group inventory;
    private Label button_sort;

    public FriendsList(Betrayal game) {
        stage = game.getStage();
        loadFont();
        loadButtons();
    }

    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle(40);
    }

    public void loadButtons(){
        loadMask();
        loadBackground();
        loadReturnToLobbyButton();
        loadTitle();
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
                removeFriendsList();
            }
        });
        stage.addActor(mask);
    }

    private void loadBackground() {
        background = new Image(Betrayal.res.getTexture("shop-background"));
        background.layout();
        background.setBounds(35, 100, Betrayal.WIDTH/2-45, Betrayal.HEIGHT -250);
        stage.addActor(background);

        background2 = new Image(Betrayal.res.getTexture("shop-background"));
        background2.layout();
        background2.setBounds(Betrayal.WIDTH/2 +10, 100, Betrayal.WIDTH/2-45, Betrayal.HEIGHT - 250);
        stage.addActor(background2);
    }

    private void loadTitle() {
        title = new Label("Party", labelStyle);
        title.setHeight(100);
        title.setX((Betrayal.WIDTH - title.getWidth()) / 4);
        title.setY(Betrayal.HEIGHT - 250);
        stage.addActor(title);

        title2 = new Label("Friends", labelStyle);
        title2.setHeight(100);
        title2.setX((Betrayal.WIDTH - title.getWidth()) / 4 * 3);
        title2.setY(Betrayal.HEIGHT - 250);
        stage.addActor(title2);
    }

    private void loadReturnToLobbyButton() {
        lobbyButton = new Image(Betrayal.res.getTexture("back-to-lobby"));
        lobbyButton.layout();
        lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 390, 110, 180, 60);
        lobbyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeFriendsList();
            }
        });
        stage.addActor(lobbyButton);
    }
    private void removeFriendsList(){
        mask.remove();
        lobbyButton.remove();
        background.remove();
        background2.remove();
        title.remove();
        title2.remove();
    }
}
