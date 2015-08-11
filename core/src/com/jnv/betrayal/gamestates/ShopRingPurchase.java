package com.jnv.betrayal.gamestates;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.entities.*;
import com.jnv.betrayal.entities.Character;
import com.jnv.betrayal.main.Betrayal;

public class ShopRingPurchase {
    private Stage stage;
    private Image backButton, background, goldIcon, buyButton;
    private Label.LabelStyle labelStyle;
    private Label price, description;
    private Actor mask;
    private Betrayal game;
    private com.jnv.betrayal.entities.Character character;


    public ShopRingPurchase(Betrayal game, Character character) {

        this.game = game;
        this.character = character;
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
        loadContent();
        loadReturnToShopButton();
        loadBuyButton();
        loadGoldIcon();
        loadPreview();
        loadDescription();
        loadPrice();
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
                removeRingShopPurchase();
            }
        });
        stage.addActor(mask);
    }

    private void loadBackground() {
        background = new Image(Betrayal.res.getTexture("shop-purchase-background"));
        background.layout();
        background.setBounds(200, 300, Betrayal.WIDTH - 400, Betrayal.HEIGHT - 600);
        stage.addActor(background);
    }

    private void loadBuyButton() {

    }

    private void loadGoldIcon() {
        backButton = new Image(Betrayal.res.getTexture("gold-icon"));
        backButton.layout();
        backButton.setBounds((Betrayal.WIDTH - backButton.getWidth()) / 2 + 100, 200, 50, 50);
        stage.addActor(backButton);
    }

    private void loadPrice() {
        price = new Label("[xxxx]", labelStyle);
        price.setHeight(100);
        price.setX((Betrayal.WIDTH - price.getWidth()) / 2);
        price.setY(Betrayal.HEIGHT - 250);
        stage.addActor(price);
    }

    private void loadReturnToShopButton() {
        backButton = new Image(Betrayal.res.getTexture("back"));
        backButton.layout();
        backButton.setBounds((Betrayal.WIDTH - backButton.getWidth()) / 2 + 100, 350, 312, 100);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeRingShopPurchase();
            }
        });
        stage.addActor(backButton);
    }

    private void loadContent() {

    }

    private void loadPreview() {
        //show the ring or item
    }
    private void loadDescription(){
        description = new Label("[desription]", labelStyle);
        description.setX((Betrayal.WIDTH - price.getWidth()) / 2);
        description.setY(Betrayal.HEIGHT - 250);
        stage.addActor(description);
    }

    private void removeRingShopPurchase() {
        mask.remove();
        background.remove();
        backButton.remove();
        buyButton.remove();
        goldIcon.remove();

    }
}
