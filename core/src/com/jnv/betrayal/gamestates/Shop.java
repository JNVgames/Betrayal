/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
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

public class Shop {
    private Stage stage;
    private Image lobbyButton, background, weapons, armors, extras, items, money;
    private Image[] potions, sword1, sword2, shield1, shield2, headgear, armor1, armor2, ring1, ring2;
    private Label.LabelStyle labelStyle;
    private Label title, titleSword1, titleSword2, titleShield1, titleShield2, titleRing1, titleRing2;
    private Label titleHeadgear, titleArmor1, titleArmor2, titleItems;
    private int currentContent, buttonHeight, buttonWidth, itemSize;
    private Actor mask;
    private Betrayal game;


    public Shop(Betrayal game) {
        this.game=game;
        stage = game.getStage();
        loadFont();
        currentContent = 0;
        buttonHeight = 100;
        buttonWidth = 104;
        itemSize = (Betrayal.WIDTH - 200) / 6;
        potions = new Image[12];
        sword1 = new Image[6];
        sword2 = new Image[6];
        shield1 = new Image[6];
        shield2 = new Image[6];
        headgear = new Image[6];
        armor1 = new Image[6];
        armor2 = new Image[6];
        ring1 = new Image[6];
        ring2 = new Image[6];
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

    private void loadWeaponsAndShields() {
        for (int i = 1; i <= 6; i++) {
            final String item = "sword1" + i;
            sword1[i - 1] = new Image(Betrayal.res.getTexture(item));
            sword1[i - 1].layout();
            sword1[i - 1].setBounds(100 + itemSize * (i - 1),
                    Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
            sword1[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    new ShopPurchase(game);
                }
            });
            stage.addActor(sword1[i - 1]);

            sword2[i - 1] = new Image(Betrayal.res.getTexture("sword2" + i));
            sword2[i - 1].layout();
            sword2[i - 1].setBounds(100 + itemSize * (i - 1),
                    Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
            sword2[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
            stage.addActor(sword2[i - 1]);

            shield1[i - 1] = new Image(Betrayal.res.getTexture("shield" + i));
            shield1[i - 1].layout();
            shield1[i - 1].setBounds(100 + itemSize * (i - 1),
                    Betrayal.HEIGHT - buttonHeight - 150 - itemSize*5, itemSize, itemSize);
            shield1[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
            stage.addActor(shield1[i - 1]);

            shield2[i - 1] = new Image(Betrayal.res.getTexture("shield" + i));
            shield2[i - 1].layout();
            shield2[i - 1].setBounds(100 + itemSize * (i - 1),
                    Betrayal.HEIGHT - buttonHeight - 150 - itemSize*7, itemSize, itemSize);
            shield2[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
            stage.addActor(shield2[i - 1]);

        }
    }

    private void loadWeaponAndShieldsTitles() {
        titleSword1 = new Label("Swords(Tier 1)", labelStyle);
        titleSword1.setX(110);
        titleSword1.setY(Betrayal.HEIGHT - 250);
        stage.addActor(titleSword1);

        titleSword2 = new Label("Swords(Tier 2)", labelStyle);
        titleSword2.setX(110);
        titleSword2.setY(Betrayal.HEIGHT - 422);
        stage.addActor( titleSword2);

        titleShield1 = new Label("Shields(Tier 1)", labelStyle);
        titleShield1.setX(110);
        titleShield1.setY(Betrayal.HEIGHT - 594);
        stage.addActor(  titleShield1);

        titleShield2 = new Label("Shields(Tier 2)", labelStyle);
        titleShield2.setX(110);
        titleShield2.setY(Betrayal.HEIGHT - 766);
        stage.addActor(  titleShield2);
    }

    private void loadRings() {
        for (int i = 1; i <= 6; i++) {
            ring1[i - 1] = new Image(Betrayal.res.getTexture("ring1-" + i));
            ring1[i - 1].layout();
            ring1[i - 1].setBounds(100 + itemSize * (i - 1),
                    Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
            ring1[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
            stage.addActor(ring1[i - 1]);

            ring2[i - 1] = new Image(Betrayal.res.getTexture("ring2-" + i));
            ring2[i - 1].layout();
            ring2[i - 1].setBounds(100 + itemSize * (i - 1),
                    Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
            ring2[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
            stage.addActor(ring2[i - 1]);

        }
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
        // Headgear
        for (int i = 1; i <= 6; i++) {

            headgear[i - 1] = new Image(Betrayal.res.getTexture("headgear" + i));
            headgear[i - 1].layout();
            headgear[i - 1].setBounds(100 + itemSize * (i - 1),
                    Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
            headgear[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
            stage.addActor(headgear[i - 1]);
        }

        //Armor1
        for (int i = 1; i <= 6; i++) {

            armor1[i - 1] = new Image(Betrayal.res.getTexture("armor1-" + i));
            armor1[i - 1].layout();
            armor1[i - 1].setBounds(100 + itemSize * (i - 1),
                    Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
            armor1[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
            stage.addActor(armor1[i - 1]);
        }

        //Armor2
        for (int i = 1; i <= 6; i++) {

            armor2[i - 1] = new Image(Betrayal.res.getTexture("armor2-" + i));
            armor2[i - 1].layout();
            armor2[i - 1].setBounds(100 + itemSize * (i - 1),
                    Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
            armor2[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
            stage.addActor(armor2[i - 1]);
        }

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

    private void loadItemsTitle() {
        titleItems = new Label("Items", labelStyle);
        titleItems.setX(110);
        titleItems.setY(Betrayal.HEIGHT - 250);
        stage.addActor(titleItems);
    }

    private void loadItems() {
        for (int i = 1; i <= 6; i++) {
            potions[i - 1] = new Image(Betrayal.res.getTexture("potion" + i));
            potions[i - 1].layout();
            potions[i - 1].setBounds(100 + itemSize * (i - 1), Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
            potions[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    new ShopRingPurchase(game);
                }
            });
            stage.addActor(potions[i - 1]);
        }
        for (int i = 7; i <= 12; i++) {
            potions[i - 1] = new Image(Betrayal.res.getTexture("potion" + i));
            potions[i - 1].layout();
            potions[i - 1].setBounds(100 + itemSize * (i - 7), Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
            potions[i - 1].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
            stage.addActor(potions[i - 1]);
        }

    }

    private void setContent0() {
        loadWeaponsAndShields();
        loadWeaponAndShieldsTitles();
    }

    private void removeContent0() {
        titleShield1.remove();
        titleShield2.remove();
        titleSword1.remove();
        titleSword2.remove();
        for (int i = 0; i < 6; i++) {
            sword1[i].remove();
            sword2[i].remove();
            shield1[i].remove();
            shield2[i].remove();
        }

    }

    private void setContent1() {  //armor + headgear
        loadArmorTitle();
        loadArmor();
    }

    private void removeContent1() {
        titleHeadgear.remove();
        titleArmor1.remove();
        titleArmor2.remove();
        for (int i = 0; i < 6; i++) {
            headgear[i].remove();
            armor1[i].remove();
            armor2[i].remove();
        }
    }

    private void setContent2() { // rings
        loadRingTitle();
        loadRings();
    }

    private void removeContent2() {
        titleRing1.remove();
        titleRing2.remove();
        for (int i = 0; i < 6; i++) {
            ring1[i].remove();
            ring2[i].remove();
        }

    }

    private void setContent3() { // extras
        loadItemsTitle();
        loadItems();
    }

    private void removeContent3() {
        titleItems.remove();
        for (int i = 0; i < 12; i++) {
            potions[i].remove();
        }

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
