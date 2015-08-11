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

/**
 * Created by jphan on 8/11/2015.
 */
public class ShopPurchase {
    private Stage stage;
    private Image backButton, background, goldIcon, buyButton, leftArrow, rightArrow, field_framePreview;
    private TextureRegion image_leftArrow, image_rightArrow;
    private Label.LabelStyle labelStyle;
    private Label price;
    private Actor mask;
    private Betrayal game;
    private Character character;
    private int currentSide;

    public ShopPurchase(Betrayal game) {
        image_leftArrow = new TextureRegion(Betrayal.res.getTexture("arrow"));
        image_leftArrow.flip(true, false);
        image_rightArrow = new TextureRegion(Betrayal.res.getTexture("arrow"));

        this.game = game;
        character = Character.currentCharacter;
        stage = game.getStage();
        currentSide = 0;
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
        loadPrice();
        loadArrows();
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
        background.setBounds(200, 200, Betrayal.WIDTH - 400, Betrayal.HEIGHT - 400);
        stage.addActor(background);
    }

    private void loadBuyButton() {
        backButton = new Image(Betrayal.res.getTexture("buy"));
        backButton.layout();
        backButton.setBounds((Betrayal.WIDTH - backButton.getWidth()) / 2 + 100, 350, 312, 100);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //CharacterSelection.getCharacter().getInventoryClass().addItem(item);
            }
        });
        stage.addActor(backButton);
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
                removeShopPurchase();
            }
        });
        stage.addActor(backButton);
    }

    private void loadContent() {

    }

    private void loadPreview() {
        /*
        Actor field_preview = new Actor() {
            public void draw(Batch sb, float parentAlpha) {
                for (TextureRegion preview : character.getFullPreview()) {
                    sb.draw(preview, field_framePreview.getX(),
                            field_framePreview.getY(), field_framePreview.getWidth(),
                            field_framePreview.getHeight());
                }
            }
        };
        field_preview.setWidth(384);
        field_preview.setHeight(576);
        field_preview.setX(field_framePreview.getX());
        field_preview.setY(field_framePreview.getY());
        stage.addActor(field_preview);
        */
    }

    private void loadArrows() {
        leftArrow = new Image(Betrayal.res.getTexture("back"));
        leftArrow.layout();
        leftArrow.setBounds((Betrayal.WIDTH -leftArrow.getWidth()) / 2 + 100, 350, 312, 100);
        leftArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                currentSide--;
                if (currentSide<0){currentSide=3;}
            }
        });
        stage.addActor(leftArrow);

        rightArrow = new Image(Betrayal.res.getTexture("back"));
        rightArrow.layout();
        rightArrow.setBounds((Betrayal.WIDTH - backButton.getWidth()) / 2 + 100, 350, 312, 100);
        rightArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                currentSide= (currentSide+1)%4;
            }
        });
        stage.addActor(rightArrow);
    }

    private void removeShopPurchase() {
        mask.remove();
        background.remove();
        backButton.remove();
        buyButton.remove();
        goldIcon.remove();

    }
}
