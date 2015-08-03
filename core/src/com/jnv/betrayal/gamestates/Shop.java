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
    private Image sword1, sword2, sword3, sword4, sword5, sword6;
    private Image shield1, shield2, shield3, shield4, shield5, shield6;
    private Image ring1a, ring1b, ring1c, ring1d, ring1e, ring1f;
    private Image ring2a, ring2b, ring2c, ring2d, ring2e, ring2f;
    private Image headgear1, headgear2, headgear3, headgear4, headgear5, headgear6;
    private Image armor1a, armor1b, armor1c, armor1d, armor1e, armor1f;
    private Image armor2a, armor2b, armor2c, armor2d, armor2e, armor2f;
    private Image potion1, potion2, potion3, potion4, potion5, potion6;
    private Image potion7, potion8, potion9, potion10, potion11, potion12;
    private Label.LabelStyle labelStyle;
    private Label title, titleWeapons, titleShields, titleRing1, titleRing2;
    private Label titleHeadgear, titleArmor1, titleArmor2, titleItems;
    private int currentContent, buttonHeight, buttonWidth, itemSize;
    private Actor mask;


    public Shop(Betrayal game) {
        stage = game.getStage();
        loadFont();
        currentContent = 0;
        buttonHeight = 100;
        buttonWidth = 104;
        itemSize = (Betrayal.WIDTH - 200) / 6;
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
        mask.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                removeShop();
            }
        });
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
        lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 110, 312, 100);
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
        armors.setBounds(100 + buttonWidth, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
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
        extras.setBounds(100 + buttonWidth * 2, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
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
        items.setBounds(100 + buttonWidth * 3, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
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
        money.setBounds(100 + buttonWidth * 4, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
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

    private void loadWeapons() {
        //sword 1
        sword1 = new Image(Betrayal.res.getTexture("sword1"));
        sword1.layout();
        sword1.setBounds(100, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        sword1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(sword1);

        //sword2
        sword2 = new Image(Betrayal.res.getTexture("sword2"));
        sword2.layout();
        sword2.setBounds(100 + itemSize, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        sword2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(sword2);

        //sword3
        sword3 = new Image(Betrayal.res.getTexture("sword3"));
        sword3.layout();
        sword3.setBounds(100 + itemSize * 2, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        sword3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(sword3);

        //sword4
        sword4 = new Image(Betrayal.res.getTexture("sword4"));
        sword4.layout();
        sword4.setBounds(100 + itemSize * 3, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        sword4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(sword4);

        //sword5
        sword5 = new Image(Betrayal.res.getTexture("sword5"));
        sword5.layout();
        sword5.setBounds(100 + itemSize * 4, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        sword5.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(sword5);

        //sword6
        sword6 = new Image(Betrayal.res.getTexture("sword6"));
        sword6.layout();
        sword6.setBounds(100 + itemSize * 5, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        sword6.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(sword6);

    }

    private void loadShields() {

        // Shield1
        shield1 = new Image(Betrayal.res.getTexture("shield1"));
        shield1.layout();
        shield1.setBounds(100, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        shield1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(shield1);

        // Shield2
        shield2 = new Image(Betrayal.res.getTexture("shield2"));
        shield2.layout();
        shield2.setBounds(100 + itemSize, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        shield2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(shield2);

        // Shield3
        shield3 = new Image(Betrayal.res.getTexture("shield3"));
        shield3.layout();
        shield3.setBounds(100 + itemSize * 2, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        shield3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(shield3);

        //Shield4
        shield4 = new Image(Betrayal.res.getTexture("shield4"));
        shield4.layout();
        shield4.setBounds(100 + itemSize * 3, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        shield4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(shield4);

        //Shield5
        shield5 = new Image(Betrayal.res.getTexture("shield5"));
        shield5.layout();
        shield5.setBounds(100 + itemSize * 4, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        shield5.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(shield5);

        //Shield6
        shield6 = new Image(Betrayal.res.getTexture("shield6"));
        shield6.layout();
        shield6.setBounds(100 + itemSize * 5, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        shield6.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(shield6);
    }

    private void loadWeaponAndShieldsTitles() {
        titleWeapons = new Label("Weapons", labelStyle);
        titleWeapons.setX(110);
        titleWeapons.setY(Betrayal.HEIGHT - 250);
        stage.addActor(titleWeapons);

        titleShields = new Label("Shields", labelStyle);
        titleShields.setX(110);
        titleShields.setY(Betrayal.HEIGHT - 422);
        stage.addActor(titleShields);
    }

    private void loadRings() {

        // Ring1a
        ring1a = new Image(Betrayal.res.getTexture("ring-1-red"));
        ring1a.layout();
        ring1a.setBounds(100, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        ring1a.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring1a);

        // Ring1b
        ring1b = new Image(Betrayal.res.getTexture("ring-1-black"));
        ring1b.layout();
        ring1b.setBounds(100 + itemSize, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        ring1b.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring1b);

        // Ring1c
        ring1c = new Image(Betrayal.res.getTexture("ring-1-gold"));
        ring1c.layout();
        ring1c.setBounds(100 + itemSize * 2, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        ring1c.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring1c);

        // Ring1d
        ring1d = new Image(Betrayal.res.getTexture("ring-1-blue"));
        ring1d.layout();
        ring1d.setBounds(100 + itemSize * 3, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        ring1d.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring1d);

        // Ring1e
        ring1e = new Image(Betrayal.res.getTexture("ring-1-green"));
        ring1e.layout();
        ring1e.setBounds(100 + itemSize * 4, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        ring1e.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring1e);

        // Ring1e
        ring1f = new Image(Betrayal.res.getTexture("ring-1-purple"));
        ring1f.layout();
        ring1f.setBounds(100 + itemSize * 5, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        ring1f.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring1f);

        // Ring2a
        ring2a = new Image(Betrayal.res.getTexture("ring-2-red"));
        ring2a.layout();
        ring2a.setBounds(100, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        ring2a.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring2a);

        // Ring2b
        ring2b = new Image(Betrayal.res.getTexture("ring-2-black"));
        ring2b.layout();
        ring2b.setBounds(100 + itemSize, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        ring2b.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring2b);

        // Ring2c
        ring2c = new Image(Betrayal.res.getTexture("ring-2-gold"));
        ring2c.layout();
        ring2c.setBounds(100 + itemSize * 2, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        ring2c.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring2c);

        // Ring2d
        ring2d = new Image(Betrayal.res.getTexture("ring-2-blue"));
        ring2d.layout();
        ring2d.setBounds(100 + itemSize * 3, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        ring2d.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring2d);

        // Ring2e
        ring2e = new Image(Betrayal.res.getTexture("ring-2-green"));
        ring2e.layout();
        ring2e.setBounds(100 + itemSize * 4, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        ring2e.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring2e);

        // Ring2f
        ring2f = new Image(Betrayal.res.getTexture("ring-2-purple"));
        ring2f.layout();
        ring2f.setBounds(100 + itemSize * 5, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        ring2f.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(ring2f);

    }

    private void loadRingTitle() {
        titleRing1 = new Label("Rings(Tier 1)", labelStyle);
        titleRing1.setX(110);
        titleRing1.setY(Betrayal.HEIGHT - 250);
        stage.addActor(titleRing1);

        titleRing2 = new Label("Rings(Tier 2)", labelStyle);
        titleRing2.setX(110);
        titleRing2.setY(Betrayal.HEIGHT - 422);
        stage.addActor(titleRing2);
    }

    private void loadArmor() {
        // Headgear1
        headgear1 = new Image(Betrayal.res.getTexture("helmet-red"));
        headgear1.layout();
        headgear1.setBounds(100, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        headgear1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(headgear1);

        // Headgear2
        headgear2 = new Image(Betrayal.res.getTexture("helmet-black"));
        headgear2.layout();
        headgear2.setBounds(100 + itemSize, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        headgear2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(headgear2);

        //headgear3
        headgear3 = new Image(Betrayal.res.getTexture("helmet-gold"));
        headgear3.layout();
        headgear3.setBounds(100 + itemSize * 2, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        headgear3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(headgear3);

        //headgear4
        headgear4 = new Image(Betrayal.res.getTexture("helmet-blue"));
        headgear4.layout();
        headgear4.setBounds(100 + itemSize * 3, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        headgear4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(headgear4);

        //headgear5
        headgear5 = new Image(Betrayal.res.getTexture("helmet-green"));
        headgear5.layout();
        headgear5.setBounds(100 + itemSize * 4, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        headgear5.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(headgear5);

        //sword6
        headgear6 = new Image(Betrayal.res.getTexture("helmet-purple"));
        headgear6.layout();
        headgear6.setBounds(100 + itemSize * 5, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        headgear6.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(headgear6);

        // armor1a
        armor1a = new Image(Betrayal.res.getTexture("armor-red"));
        armor1a.layout();
        armor1a.setBounds(100, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        armor1a.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor1a);

        // armor1b
        armor1b = new Image(Betrayal.res.getTexture("armor-black"));
        armor1b.layout();
        armor1b.setBounds(100 + itemSize, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        armor1b.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor1b);

        // armor1c
        armor1c = new Image(Betrayal.res.getTexture("armor-gold"));
        armor1c.layout();
        armor1c.setBounds(100 + itemSize * 2, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        armor1c.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor1c);

        // armor1d
        armor1d = new Image(Betrayal.res.getTexture("armor-blue"));
        armor1d.layout();
        armor1d.setBounds(100 + itemSize * 3, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        armor1d.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor1d);

        // armor1e
        armor1e = new Image(Betrayal.res.getTexture("armor-green"));
        armor1e.layout();
        armor1e.setBounds(100 + itemSize * 4, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        armor1e.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor1e);

        // armor1f
        armor1f = new Image(Betrayal.res.getTexture("armor-purple"));
        armor1f.layout();
        armor1f.setBounds(100 + itemSize * 5, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        armor1f.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor1f);

        // armor2a
        armor2a = new Image(Betrayal.res.getTexture("armor-hero-red"));
        armor2a.layout();
        armor2a.setBounds(100, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
        armor2a.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor2a);

        // armor2b
        armor2b = new Image(Betrayal.res.getTexture("armor-hero-black"));
        armor2b.layout();
        armor2b.setBounds(100 + itemSize, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
        armor2b.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor2b);

        // armor2c
        armor2c = new Image(Betrayal.res.getTexture("armor-hero-gold"));
        armor2c.layout();
        armor2c.setBounds(100 + itemSize * 2, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
        armor2c.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor2c);

        // armor2d
        armor2d = new Image(Betrayal.res.getTexture("armor-hero-blue"));
        armor2d.layout();
        armor2d.setBounds(100 + itemSize * 3, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
        armor2d.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor2d);

        // armor2e
        armor2e = new Image(Betrayal.res.getTexture("armor-hero-green"));
        armor2e.layout();
        armor2e.setBounds(100 + itemSize * 4, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
        armor2e.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor2e);

        // Ring2f
        armor2f = new Image(Betrayal.res.getTexture("armor-hero-purple"));
        armor2f.layout();
        armor2f.setBounds(100 + itemSize * 5, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
        armor2f.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(armor2f);
    }

    private void loadArmorTitle() {
        titleHeadgear = new Label("Headgear", labelStyle);
        titleHeadgear.setX(110);
        titleHeadgear.setY(Betrayal.HEIGHT - 250);
        stage.addActor(titleHeadgear);

        titleArmor1 = new Label("Armor(tier 1)", labelStyle);
        titleArmor1.setX(110);
        titleArmor1.setY(Betrayal.HEIGHT - 422);
        stage.addActor(titleArmor1);

        titleArmor2 = new Label("Armor(tier 2)", labelStyle);
        titleArmor2.setX(110);
        titleArmor2.setY(Betrayal.HEIGHT - 594);
        stage.addActor(titleArmor2);
    }

    private void loadItemsTitle(){
        titleItems = new Label("Items", labelStyle);
        titleItems.setX(110);
        titleItems.setY(Betrayal.HEIGHT - 250);
        stage.addActor(titleItems);
    }

    private void loadItems(){
        // potion1
        potion1 = new Image(Betrayal.res.getTexture("potion1"));
        potion1.layout();
        potion1.setBounds(100, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        potion1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion1);

        // potion1
        potion2 = new Image(Betrayal.res.getTexture("potion2"));
        potion2.layout();
        potion2.setBounds(100 + itemSize, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        potion2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion2);

        // potion3
        potion3 = new Image(Betrayal.res.getTexture("potion3"));
        potion3.layout();
        potion3.setBounds(100 + itemSize * 2, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        potion3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion3);

        // potion4
        potion4 = new Image(Betrayal.res.getTexture("potion4"));
        potion4.layout();
        potion4.setBounds(100 + itemSize * 3, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        potion4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion4);

        // potion5
        potion5 = new Image(Betrayal.res.getTexture("potion5"));
        potion5.layout();
        potion5.setBounds(100 + itemSize * 4, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        potion5.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion5);

        // potion6
        potion6 = new Image(Betrayal.res.getTexture("potion6"));
        potion6.layout();
        potion6.setBounds(100 + itemSize * 5, Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
        potion6.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion6);

        // potion7
        potion7 = new Image(Betrayal.res.getTexture("potion7"));
        potion7.layout();
        potion7.setBounds(100, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        potion7.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion7);

        // potion8
        potion8 = new Image(Betrayal.res.getTexture("potion8"));
        potion8.layout();
        potion8.setBounds(100 + itemSize, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        potion8.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion8);

        // potion8
        potion9 = new Image(Betrayal.res.getTexture("potion9"));
        potion9.layout();
        potion9.setBounds(100 + itemSize * 2, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        potion9.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion9);

        // potion10
        potion10 = new Image(Betrayal.res.getTexture("potion10"));
        potion10.layout();
        potion10.setBounds(100 + itemSize * 3, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        potion10.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion10);

        // potion11
        potion11 = new Image(Betrayal.res.getTexture("potion11"));
        potion11.layout();
        potion11.setBounds(100 + itemSize * 4, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        potion11.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion11);

        // potion12
        potion12 = new Image(Betrayal.res.getTexture("potion12"));
        potion12.layout();
        potion12.setBounds(100 + itemSize * 5, Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
        potion12.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        stage.addActor(potion12);

    }

    private void setContent0() {
        loadWeapons();
        loadShields();
        loadWeaponAndShieldsTitles();
    }

    private void removeContent0() {
        titleShields.remove();
        titleWeapons.remove();
        sword1.remove();
        sword2.remove();
        sword3.remove();
        sword4.remove();
        sword5.remove();
        sword6.remove();
        shield1.remove();
        shield2.remove();
        shield3.remove();
        shield4.remove();
        shield5.remove();
        shield6.remove();
    }

    private void setContent1() {  //armor + headgear
        loadArmorTitle();
        loadArmor();
    }

    private void removeContent1() {
        titleHeadgear.remove();
        titleArmor1.remove();
        titleArmor2.remove();
        headgear1.remove();
        headgear2.remove();
        headgear3.remove();
        headgear4.remove();
        headgear5.remove();
        headgear6.remove();
        armor1a.remove();
        armor1b.remove();
        armor1c.remove();
        armor1d.remove();
        armor1e.remove();
        armor1f.remove();
        armor2a.remove();
        armor2b.remove();
        armor2c.remove();
        armor2d.remove();
        armor2e.remove();
        armor2f.remove();
    }

    private void setContent2() { // rings
        loadRingTitle();
        loadRings();
    }

    private void removeContent2() {
        titleRing1.remove();
        titleRing2.remove();
        ring1a.remove();
        ring1b.remove();
        ring1c.remove();
        ring1d.remove();
        ring1e.remove();
        ring1f.remove();
        ring2a.remove();
        ring2b.remove();
        ring2c.remove();
        ring2d.remove();
        ring2e.remove();
        ring2f.remove();

    }

    private void setContent3() { // extras
        loadItemsTitle();
        loadItems();
    }

    private void removeContent3() {
        titleItems.remove();
        potion1.remove();
        potion2.remove();
        potion3.remove();
        potion4.remove();
        potion5.remove();
        potion6.remove();
        potion7.remove();
        potion8.remove();
        potion9.remove();
        potion10.remove();
        potion11.remove();
        potion12.remove();
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
