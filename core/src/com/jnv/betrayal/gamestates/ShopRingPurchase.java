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

    public ShopRingPurchase(Betrayal game) {

        this.game = game;
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
        loadDescription();
        loadReturnToShopButton();
        loadBuyButton();
        loadGoldIcon();
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
                removeShopPurchase();
            }
        });
        stage.addActor(mask);
    }

    private void loadBackground() {
        background = new Image(Betrayal.res.getTexture("shop-purchase-background"));
        background.layout();
        background.setBounds(150, 400, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 800);
        stage.addActor(background);
    }

    private void loadGoldIcon() {
        goldIcon = new Image(Betrayal.res.getTexture("icon-gold"));
        goldIcon.layout();
        goldIcon.setBounds(410, 500, 40, 40);
        stage.addActor(goldIcon);
    }

    private void loadPrice() {
        price = new Label("$$$$$", labelStyle);
        price.setHeight(50);
        price.setX(450);
        price.setY(500);
        stage.addActor(price);
    }

    private void loadDescription() {
        description = new Label("+5 Attack", labelStyle);
        description.setHeight(50);
        description.setX(300);
        description.setY(700);
        stage.addActor(description);
    }

    private void loadBuyButton() {
        buyButton = new Image(Betrayal.res.getTexture("buy"));
        buyButton.layout();
        buyButton.setBounds(Betrayal.WIDTH / 2 + 50, 420, 100, 50);
        buyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //CharacterSelection.getCharacter().getInventoryClass().addItem(item);
                new Confirmation(game);
            }
        });
        stage.addActor(buyButton);
    }
    private void loadReturnToShopButton() {
        backButton = new Image(Betrayal.res.getTexture("back"));
        backButton.layout();
        backButton.setBounds(Betrayal.WIDTH / 2 - 150, 420, 100, 50);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeShopPurchase();
            }
        });
        stage.addActor(backButton);
    }

    private void removeShopPurchase() {
        mask.remove();
        background.remove();
        backButton.remove();
        buyButton.remove();
        goldIcon.remove();
        price.remove();
        description.remove();
    }
}
