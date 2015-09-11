/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.HeadGear;
import com.jnv.betrayal.gameobjects.Ring;
import com.jnv.betrayal.gameobjects.Shield;
import com.jnv.betrayal.gameobjects.Weapon;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class Shop extends Popup {

	private Image lobbyButton, background, weapons, armors, extras, items, money;
	private Image[] potions, sword1, sword2, shield1, shield2, headgear1, headgear2, armor1, armor2, ring1, ring2;
	private Label title, titleSword1, titleSword2, titleShield1, titleShield2, titleRing1, titleRing2;
	private Label titleHeadgear1, titleHeadgear2, titleArmor1, titleArmor2, titleItems;
	private int currentContent, buttonHeight, buttonWidth, itemSize;

	public Shop(Betrayal game) {
		super(game);
		currentContent = 0;
		buttonHeight = 100;
		buttonWidth = 104;
		itemSize = (Betrayal.WIDTH - 200) / 6;
		potions = new Image[12];
		sword1 = new Image[6];
		sword2 = new Image[6];
		shield1 = new Image[6];
		shield2 = new Image[6];
		headgear1 = new Image[6];
		headgear2 = new Image[6];
		armor1 = new Image[6];
		armor2 = new Image[6];
		ring1 = new Image[6];
		ring2 = new Image[6];
		loadButtons();
	}

	private void loadButtons() {
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

	private void loadBackground() {
		background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		popup.addActor(background);
	}

	private void loadTitle() {
		title = new Label("Shop", FontManager.getFont(40));
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 200);
		popup.addActor(title);
	}

	private void loadReturnToLobbyButton() {
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 110, 312, 100);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadWeaponsButton() {
		weapons = new Image(res.getTexture("shop-weapons"));
		weapons.layout();
		weapons.setBounds(100, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
		weapons.addListener(new InputListener(weapons) {
			@Override
			public void doAction() {
				removeCurrentContent();
				currentContent = 0;
				loadContent();
			}
		});
		popup.addActor(weapons);
	}

	private void loadArmorButton() {
		armors = new Image(res.getTexture("shop-armor"));
		armors.layout();
		armors.setBounds(100 + buttonWidth, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
		armors.addListener(new InputListener(armors) {
			@Override
			public void doAction() {
				removeCurrentContent();
				currentContent = 1;
				loadContent();
			}
		});
		popup.addActor(armors);
	}

	private void loadExtrasButton() {
		extras = new Image(res.getTexture("shop-extra"));
		extras.layout();
		extras.setBounds(100 + buttonWidth * 2, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
		extras.addListener(new InputListener(extras) {
			@Override
			public void doAction() {
				removeCurrentContent();
				currentContent = 2;
				loadContent();
			}
		});
		popup.addActor(extras);
	}

	private void loadItemsButton() {
		items = new Image(res.getTexture("shop-item"));
		items.layout();
		items.setBounds(100 + buttonWidth * 3, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
		items.addListener(new InputListener(items) {
			@Override
			public void doAction() {
				removeCurrentContent();
				currentContent = 3;
				loadContent();
			}
		});
		popup.addActor(items);
	}

	private void loadMoneyButton() {
		money = new Image(res.getTexture("shop-money"));
		money.layout();
		money.setBounds(100 + buttonWidth * 4, Betrayal.HEIGHT - buttonHeight - 105, buttonWidth, buttonHeight);
		money.addListener(new InputListener(money) {
			@Override
			public void doAction() {
				removeCurrentContent();
				currentContent = 4;
				loadContent();
			}
		});
		popup.addActor(money);
	}

	private void loadWeaponsAndShields() {
		for (int i = 1; i <= 6; i++) {
			final String item1 = "sword1" + i;
			final String item2 = "sword2" + i;
			final String item3 = "shield1" + i;
			final String item4 = "shield2" + i;
			sword1[i - 1] = new Image(res.getTexture(item1));
			sword1[i - 1].layout();
			sword1[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
			sword1[i - 1].addListener(new InputListener(sword1[i - 1]) {
				@Override
				public void doAction() {
					Weapon sword = new Weapon(item1, res);
					new ShopPurchase(game, sword);


				}
			});
			popup.addActor(sword1[i - 1]);

			sword2[i - 1] = new Image(res.getTexture(item2));
			sword2[i - 1].layout();
			sword2[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
			sword2[i - 1].addListener(new InputListener(sword2[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Weapon(item2, res));
				}
			});
			popup.addActor(sword2[i - 1]);

			shield1[i - 1] = new Image(res.getTexture(item3));
			shield1[i - 1].layout();
			shield1[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
			shield1[i - 1].addListener(new InputListener(shield1[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Shield(item3, res));
				}
			});
			popup.addActor(shield1[i - 1]);

			shield2[i - 1] = new Image(res.getTexture(item4));
			shield2[i - 1].layout();
			shield2[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
			shield2[i - 1].addListener(new InputListener(shield2[i-1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Shield(item4, res));
				}
			});
			popup.addActor(shield2[i - 1]);

		}
	}

	private void loadWeaponAndShieldsTitles() {
		titleSword1 = new Label("Swords(Tier 1)", FontManager.getFont(40));
		titleSword1.setX(110);
		titleSword1.setY(Betrayal.HEIGHT - 250);
		popup.addActor(titleSword1);

		titleSword2 = new Label("Swords(Tier 2)", FontManager.getFont(40));
		titleSword2.setX(110);
		titleSword2.setY(Betrayal.HEIGHT - 422);
		popup.addActor(titleSword2);

		titleShield1 = new Label("Shields(Tier 1)", FontManager.getFont(40));
		titleShield1.setX(110);
		titleShield1.setY(Betrayal.HEIGHT - 594);
		popup.addActor(titleShield1);

		titleShield2 = new Label("Shields(Tier 2)", FontManager.getFont(40));
		titleShield2.setX(110);
		titleShield2.setY(Betrayal.HEIGHT - 766);
		popup.addActor(titleShield2);
	}

	private void loadRings() {
		for (int i = 1; i <= 6; i++) {
			final String item1 = "ring1-" + i;
			final String item2 = "ring2-" + i;
			ring1[i - 1] = new Image(res.getTexture(item1));
			ring1[i - 1].layout();
			ring1[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
			ring1[i - 1].addListener(new InputListener(ring1[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Ring(item1, res));
				}
			});
			popup.addActor(ring1[i - 1]);

			ring2[i - 1] = new Image(res.getTexture(item2));
			ring2[i - 1].layout();
			ring2[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
			ring2[i - 1].addListener(new InputListener(ring2[i-1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Ring(item2, res));
				}
			});
			popup.addActor(ring2[i - 1]);

		}
	}

	private void loadRingTitle() {
		titleRing1 = new Label("Rings(Tier 1)", FontManager.getFont(40));
		titleRing1.setX(110);
		titleRing1.setY(Betrayal.HEIGHT - 250);
		popup.addActor(titleRing1);

		titleRing2 = new Label("Rings(Tier 2)", FontManager.getFont(40));
		titleRing2.setX(110);
		titleRing2.setY(Betrayal.HEIGHT - 422);
		popup.addActor(titleRing2);
	}

	private void loadArmor() {
		// Headgear1
		for (int i = 1; i <= 6; i++) {
			final String item1 = "headgear1" + i;
			final String item2 = "headgear2" + i;
			final String item3 = "armor1-" + i;
			final String item4 = "armor2-" + i;
			headgear1[i - 1] = new Image(res.getTexture(item1));
			headgear1[i - 1].layout();
			headgear1[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
			headgear1[i - 1].addListener(new InputListener(headgear1[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new HeadGear(item1, res));
				}
			});
			popup.addActor(headgear1[i - 1]);

			// Headgear 2
			headgear2[i - 1] = new Image(res.getTexture(item2));
			headgear2[i - 1].layout();
			headgear2[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
			headgear2[i - 1].addListener(new InputListener(headgear2[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new HeadGear(item1, res));
				}
			});
			popup.addActor(headgear2[i - 1]);

			//Armor1
			armor1[i - 1] = new Image(res.getTexture(item3));
			armor1[i - 1].layout();
			armor1[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
			armor1[i - 1].addListener(new InputListener(armor1[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new BodyArmor(item3, res));
				}
			});
			popup.addActor(armor1[i - 1]);

			//Armor2
			armor2[i - 1] = new Image(res.getTexture(item4));
			armor2[i - 1].layout();
			armor2[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
			armor2[i - 1].addListener(new InputListener(armor2[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new BodyArmor(item4, res));
				}
			});
			popup.addActor(armor2[i - 1]);
		}
	}

	private void loadArmorTitle() {
		titleHeadgear1 = new Label("Headgear(tier1)", FontManager.getFont(40));
		titleHeadgear1.setX(110);
		titleHeadgear1.setY(Betrayal.HEIGHT - 250);
		popup.addActor(titleHeadgear1);

		titleHeadgear2 = new Label("Headgear(tier2)", FontManager.getFont(40));
		titleHeadgear2.setX(110);
		titleHeadgear2.setY(Betrayal.HEIGHT - 422);
		popup.addActor(titleHeadgear2);

		titleArmor1 = new Label("Armor(tier 1)", FontManager.getFont(40));
		titleArmor1.setX(110);
		titleArmor1.setY(Betrayal.HEIGHT - 594);
		popup.addActor(titleArmor1);

		titleArmor2 = new Label("Armor(tier 2)", FontManager.getFont(40));
		titleArmor2.setX(110);
		titleArmor2.setY(Betrayal.HEIGHT - 766);
		popup.addActor(titleArmor2);
	}

	private void loadItemsTitle() {
		titleItems = new Label("Items", FontManager.getFont(40));
		titleItems.setX(110);
		titleItems.setY(Betrayal.HEIGHT - 250);
		popup.addActor(titleItems);
	}

	private void loadItems() {
		for (int i = 1; i <= 12; i++) {
			final String item = "potion" + i;
			potions[i - 1] = new Image(res.getTexture("potion" + i));
			potions[i - 1].layout();
			if (i <= 6) {
				potions[i - 1].setBounds(100 + itemSize * (i - 1), Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
				potions[i - 1].addListener(new InputListener(potions[i - 1]) {
					@Override
					public void doAction() {
						new ShopPurchase(game, new BodyArmor(item, res));
					}
				});
				popup.addActor(potions[i - 1]);
			} else {
				potions[i - 1].setBounds(100 + itemSize * (i - 7), Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
				potions[i - 1].addListener(new InputListener(potions[i - 1]) {
					@Override
					public void doAction() {
						new ShopPurchase(game, new BodyArmor(item, res));
					}
				});
				popup.addActor(potions[i - 1]);
			}
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
		titleHeadgear1.remove();
		titleHeadgear2.remove();
		titleArmor1.remove();
		titleArmor2.remove();
		for (int i = 0; i < 6; i++) {
			headgear1[i].remove();
			headgear2[i].remove();
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
}