package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;

/**
 * Copyright 2015, JNV Games, All rights reserved.
 */

public class Shop {
    private Stage stage;
    private Image lobbyButton, background, weapons, armors, extras, items, money;
    private Label.LabelStyle labelStyle;
    private Label title;
    private Menu menu;
    private int currentContent, rowSpacing, columnSpacing, buttonHeight, buttonWidth;
    private Actor mask;


    public Shop(Betrayal game) {
        stage = game.getStage();
        loadFont();
        currentContent = 0;
        buttonHeight = 100;
        buttonWidth = 104;
        rowSpacing = 100;
        columnSpacing = 100;
        loadButtons();
    }

    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle(40);
    }

    private void loadButtons() {
        loadMask();
        loadBackground();
        loadTitle();
        loadWeaponsButton();
        loadArmorButton();
        loadExtrasButton();
        loadItemsButton();
        loadMoneyButton();
        loadContent();
        loadReturnToLobbyButton();
    }

    private void loadMask() {
        mask = new Actor();
        mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
        stage.addActor(mask);
    }

    private void loadBackground() {
        background = new Image(Betrayal.res.getTexture("shop-background"));
        background.layout();
        background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
        stage.addActor(background);
    }

    private void loadTitle() {
        title = new Label("Shop", labelStyle);
        title.setHeight(100);
        title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
        title.setY(Betrayal.HEIGHT - 200);
        stage.addActor(title);
    }

    private void loadReturnToLobbyButton() {
        lobbyButton = new Image(Betrayal.res.getTexture("back-to-lobby"));
        lobbyButton.layout();
        lobbyButton.setBounds((Betrayal.WIDTH-lobbyButton.getWidth())/2+100, 110, 312, 100);
        lobbyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeShop();
            }
        });
        stage.addActor(lobbyButton);
    }

    private void loadWeaponsButton() {
        weapons = new Image(Betrayal.res.getTexture("shop-weapons"));
        weapons.layout();
        weapons.setBounds(100, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
        weapons.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeCurrentContent();
                currentContent = 0;
                loadContent();
            }
        });
        stage.addActor(weapons);
    }

    private void loadArmorButton() {
        armors = new Image(Betrayal.res.getTexture("shop-armor"));
        armors.layout();
        armors.setBounds(100+buttonWidth, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
        armors.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeCurrentContent();
                currentContent = 1;
                loadContent();
            }
        });
        stage.addActor(armors);
    }

    private void loadExtrasButton() {
        extras = new Image(Betrayal.res.getTexture("shop-extra"));
        extras.layout();
        extras.setBounds(100+buttonWidth*2, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
        extras.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeCurrentContent();
                currentContent = 2;
                loadContent();
            }
        });
        stage.addActor(extras);
    }

    private void loadItemsButton() {
        items = new Image(Betrayal.res.getTexture("shop-item"));
        items.layout();
        items.setBounds(100+buttonWidth*3, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
        items.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeCurrentContent();
                currentContent = 3;
                loadContent();
            }
        });
        stage.addActor(items);
    }

    private void loadMoneyButton() {
        money = new Image(Betrayal.res.getTexture("shop-money"));
        money.layout();
        money.setBounds(100+buttonWidth*4, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
        money.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeCurrentContent();
                currentContent = 4;
                loadContent();
            }
        });
        stage.addActor(money);
    }

    private void setContent0() {
    }

    private void removeContent0() {
    }

    private void setContent1() {  //weapons + shields
    }

    private void removeContent1() {
    }

    private void setContent2() { //armor + headgear
    }

    private void removeContent2() {
    }

    private void setContent3() { // rings + cloak
    }

    private void removeContent3() {
    }
    private void setContent4() { // money
    }

    private void removeContent4() {
    }

    private void removeCurrentContent() {
        switch (currentContent) {
            case 0:
                removeContent0(); //weapons
                break;
            case 1:
                removeContent1(); //armor
                break;
            case 2:
                removeContent2(); //extras
                break;
            case 3:
                removeContent3();//items
                break;
            case 4:
                removeContent4();//money
                break;
            default:
                Gdx.app.log("content", "should not happen");
                break;
        }
    }

    private void loadContent() {
        switch (currentContent) {
            case 0:
                setContent0();//weapons + shields
                break;
            case 1:
                setContent1();//armor
                break;
            case 2:
                setContent2();//extras
                break;
            case 3:
                setContent3();//items
                break;
            case 4:
                setContent4();//money
                break;
            default:
                Gdx.app.log("content", "should not happen");
                break;
        }
    }

    private void removeShop() {
        removeCurrentContent();
        mask.remove();
        title.remove();
        background.remove();
        lobbyButton.remove();
        weapons.remove();
        armors.remove();
        extras.remove();
        items.remove();
        money.remove();
    }
}
