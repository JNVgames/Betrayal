/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
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

	private Image lobbyButton, background, weapons, armors, extras, items, money, shields, swords;
	private Image swordButton, armorButton, shieldButton, headGearButton;
	private Image[] potions, ring1, ring2;
	private Image[] sword1,sword2,sword3,sword4,sword5;
	private Image[] shield1,shield2,shield3,shield4,shield5;
	private Image[] headgear1,headgear2,headgear3, headgear4,headgear5;
	private Image[] armor1,armor2,armor3,armor4,armor5;
	private Label title, titleSword1, titleSword2, titleShield1, titleShield2, titleRing1, titleRing2;
	private Label titleHeadgear1, titleHeadgear2, titleArmor1, titleArmor2, titleItems;
	private Label[] titleHeadgear, titleShield, titleArmor, titleSword;
	private int currentContent, buttonHeight, buttonWidth, itemSize;
	private Group currentGroup;

	public Shop(Betrayal game) {
		super(game);
		currentGroup = new Group();
		currentContent = 0;
		buttonHeight = 100;
		buttonWidth = 104;
		itemSize = (Betrayal.WIDTH - 200) / 6;
		potions = new Image[12];
		sword1 = new Image[6];
		sword2 = new Image[6];
		sword3 = new Image[6];
		sword4 = new Image[6];
		sword5 = new Image[6];
		shield1 = new Image[6];
		shield2 = new Image[6];
		shield3 = new Image[6];
		shield4 = new Image[6];
		shield5 = new Image[6];
		headgear1 = new Image[6];
		headgear2 = new Image[6];
		headgear3 = new Image[6];
		headgear4 = new Image[6];
		headgear5 = new Image[6];
		armor1 = new Image[6];
		armor2 = new Image[6];
		armor3 = new Image[6];
		armor4 = new Image[6];
		armor5 = new Image[6];
		ring1 = new Image[6];
		ring2 = new Image[6];
		titleSword = new Label[5];
		titleArmor = new Label[5];
		titleHeadgear = new Label[5];
		titleShield = new Label[5];
		loadButtons();
		loadCurrentActors();
	}
	private void loadCurrentActors(){
		popup.addActor(currentGroup);
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
		lobbyButton.setBounds((Betrayal.WIDTH-312)/2, 110, 312, 100);
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
		weapons.setBounds(100, Betrayal.HEIGHT - buttonHeight - 90, buttonWidth, buttonHeight);
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
		armors.setBounds(100 + buttonWidth, Betrayal.HEIGHT - buttonHeight - 90, buttonWidth, buttonHeight);
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
		extras.setBounds(100 + buttonWidth * 2, Betrayal.HEIGHT - buttonHeight - 90, buttonWidth, buttonHeight);
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
		items.setBounds(100 + buttonWidth * 3, Betrayal.HEIGHT - buttonHeight - 90, buttonWidth, buttonHeight);
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
		money.setBounds(100 + buttonWidth * 4, Betrayal.HEIGHT - buttonHeight - 90, buttonWidth, buttonHeight);
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

	private void loadSwords() {
		for (int i = 1; i <= 6; i++) {
			final String item1 = "sword" + i + "1";
			final String item2 = "sword" + i + "2";
			final String item3 = "sword" + i + "3";
			final String item4 = "sword" + i + "4";
			final String item5 = "sword" + i + "5";
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
			currentGroup.addActor(sword1[i - 1]);

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
			currentGroup.addActor(sword2[i - 1]);

			sword3[i - 1] = new Image(res.getTexture(item3));
			sword3[i - 1].layout();
			sword3[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
			sword3[i - 1].addListener(new InputListener(sword3[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Weapon(item3, res));
				}
			});
			currentGroup.addActor(sword3[i - 1]);

			sword4[i - 1] = new Image(res.getTexture(item4));
			sword4[i - 1].layout();
			sword4[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
			sword4[i - 1].addListener(new InputListener(sword4[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Weapon(item4, res));
				}
			});
			currentGroup.addActor(sword4[i - 1]);

			sword5[i - 1] = new Image(res.getTexture(item5));
			sword5[i - 1].layout();
			sword5[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 9, itemSize, itemSize);
			sword5[i - 1].addListener(new InputListener(sword5[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Weapon(item5, res));
				}
			});
			currentGroup.addActor(sword5[i - 1]);
		}

		shieldButton = new Image(res.getTexture("shield-button"));
		shieldButton.layout();
		shieldButton.setBounds(460,1035,150,60);
		shieldButton.addListener(new InputListener(shieldButton) {
			@Override
			public void doAction() {
				removeCurrentContent();
				loadShields();
				loadShieldTitles();
			}
		});
		currentGroup.addActor(shieldButton);
	}

	private void loadShields(){
		for (int i = 1; i <= 6; i++) {
			final String item1 = "shield1" + i;
			final String item2 = "shield2" + i;
			final String item3 = "shield3" + i;
			final String item4 = "shield4" + i;
			final String item5 = "shield5" + i;

			shield1[i - 1] = new Image(res.getTexture(item1));
			shield1[i - 1].layout();
			shield1[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 1, itemSize, itemSize);
			shield1[i - 1].addListener(new InputListener(shield1[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Shield(item1, res));
				}
			});
			currentGroup.addActor(shield1[i - 1]);


			shield2[i - 1] = new Image(res.getTexture(item2));
			shield2[i - 1].layout();
			shield2[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
			shield2[i - 1].addListener(new InputListener(shield2[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Shield(item2, res));
				}
			});
			currentGroup.addActor(shield2[i - 1]);

			shield3[i - 1] = new Image(res.getTexture(item3));
			shield3[i - 1].layout();
			shield3[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
			shield3[i - 1].addListener(new InputListener(shield3[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Shield(item3, res));
				}
			});
			currentGroup.addActor(shield3[i - 1]);

			shield4[i - 1] = new Image(res.getTexture(item4));
			shield4[i - 1].layout();
			shield4[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
			shield4[i - 1].addListener(new InputListener(shield2[i-1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Shield(item4, res));
				}
			});
			currentGroup.addActor(shield4[i - 1]);

			shield5[i - 1] = new Image(res.getTexture(item5));
			shield5[i - 1].layout();
			shield5[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 9, itemSize, itemSize);
			shield5[i - 1].addListener(new InputListener(shield5[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Shield(item5, res));
				}
			});
			currentGroup.addActor(shield5[i - 1]);
		}
		swordButton = new Image(res.getTexture("sword-button"));
		swordButton.layout();
		swordButton.setBounds(460,1035,150,60);
		swordButton.addListener(new InputListener(swordButton) {
			@Override
			public void doAction() {
				removeCurrentContent();
				loadSwords();
				loadSwordTitles();
			}
		});
		currentGroup.addActor(swordButton);
	}

	private void loadSwordTitles() {
		int spacing = 172;
		for (int i=1; i<=5; i++) {
			titleSword[i-1] = new Label("Sword(tier"+i+")", FontManager.getFont(40));
			titleSword[i-1].setX(110);
			titleSword[i-1].setY(Betrayal.HEIGHT - 250 - (i - 1) * spacing);
			currentGroup.addActor(titleSword[i-1]);
		}
	}
	private void loadShieldTitles(){
		int spacing = 172;
		for (int i=1; i<=5; i++) {
			titleShield[i-1] = new Label("Shield(tier"+i+")", FontManager.getFont(40));
			titleShield[i-1].setX(110);
			titleShield[i-1].setY(Betrayal.HEIGHT - 250 - (i-1)*spacing);
			currentGroup.addActor(titleShield[i-1]);
		}
	}

	private void loadRings() {
		for (int i = 1; i <= 6; i++) {
			final String item1 = "ring1" + i;
			final String item2 = "ring2" + i;
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
			currentGroup.addActor(ring1[i - 1]);

			ring2[i - 1] = new Image(res.getTexture(item2));
			ring2[i - 1].layout();
			ring2[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
			ring2[i - 1].addListener(new InputListener(ring2[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new Ring(item2, res));
				}
			});
			currentGroup.addActor(ring2[i - 1]);
		}


		for (int i = 1; i <= 12; i++) {
			final String item = "potion" + i;
			potions[i - 1] = new Image(res.getTexture("potion" + i));
			potions[i - 1].layout();
			if (i <= 6) {
				potions[i - 1].setBounds(100 + itemSize * (i - 1), Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
				potions[i - 1].addListener(new InputListener(potions[i - 1]) {
					@Override
					public void doAction() {
						new ShopPurchase(game, new BodyArmor(item, res));
					}
				});
				currentGroup.addActor(potions[i - 1]);
			} else {
				potions[i - 1].setBounds(100 + itemSize * (i - 7), Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
				potions[i - 1].addListener(new InputListener(potions[i - 1]) {
					@Override
					public void doAction() {
						new ShopPurchase(game, new BodyArmor(item, res));
					}
				});
				currentGroup.addActor(potions[i - 1]);
			}
		}
	}

	private void loadRingTitle() {
		titleRing1 = new Label("Rings(Tier1)", FontManager.getFont(40));
		titleRing1.setX(110);
		titleRing1.setY(Betrayal.HEIGHT - 250);
		currentGroup.addActor(titleRing1);

		titleRing2 = new Label("Rings(Tier2)", FontManager.getFont(40));
		titleRing2.setX(110);
		titleRing2.setY(Betrayal.HEIGHT - 422);
		currentGroup.addActor(titleRing2);

		titleItems = new Label("Potions", FontManager.getFont(40));
		titleItems.setX(110);
		titleItems.setY(Betrayal.HEIGHT - 250-172*2);
		currentGroup.addActor(titleItems);
	}

	private void loadHeadgear() {
		// Headgear1
		for (int i = 1; i <= 6; i++) {
			final String item1 = "headgear1" + i;
			final String item2 = "headgear2" + i;
			final String item3 = "headgear3" + i;
			final String item4 = "headgear4" + i;
			final String item5 = "headgear5" + i;
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
			currentGroup.addActor(headgear1[i - 1]);

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
			currentGroup.addActor(headgear2[i - 1]);

			headgear3[i - 1] = new Image(res.getTexture(item3));
			headgear3[i - 1].layout();
			headgear3[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
			headgear3[i - 1].addListener(new InputListener(headgear3[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new HeadGear(item3, res));
				}
			});
			currentGroup.addActor(headgear3[i - 1]);

			headgear4[i - 1] = new Image(res.getTexture(item4));
			headgear4[i - 1].layout();
			headgear4[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
			headgear4[i - 1].addListener(new InputListener(headgear4[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new HeadGear(item4, res));
				}
			});
			currentGroup.addActor(headgear4[i - 1]);

			headgear5[i - 1] = new Image(res.getTexture(item5));
			headgear5[i - 1].layout();
			headgear5[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 9, itemSize, itemSize);
			headgear5[i - 1].addListener(new InputListener(headgear5[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new HeadGear(item5, res));
				}
			});
			currentGroup.addActor(headgear5[i - 1]);
		}
		armorButton = new Image(res.getTexture("armor-button"));
		armorButton.layout();
		armorButton.setBounds(460,1035,150,60);
		armorButton.addListener(new InputListener(armorButton) {
			@Override
			public void doAction() {
				removeCurrentContent();
				loadArmor();
				loadArmorTitle();
			}
		});
		currentGroup.addActor(armorButton);
	}
	private void loadArmor(){
		for(int i=1; i<=6; i++) {
			final String item1 = "armor1" + i;
			final String item2 = "armor2" + i;
			final String item3 = "armor3" + i;
			final String item4 = "armor4" + i;
			final String item5 = "armor5" + i;
			armor1[i - 1] = new Image(res.getTexture(item1));
			armor1[i - 1].layout();
			armor1[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 1, itemSize, itemSize);
			armor1[i - 1].addListener(new InputListener(armor1[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new BodyArmor(item1, res));
				}
			});
			currentGroup.addActor(armor1[i - 1]);

			armor2[i - 1] = new Image(res.getTexture(item2));
			armor2[i - 1].layout();
			armor2[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
			armor2[i - 1].addListener(new InputListener(armor2[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new BodyArmor(item2, res));
				}
			});
			currentGroup.addActor(armor2[i - 1]);

			armor3[i - 1] = new Image(res.getTexture(item3));
			armor3[i - 1].layout();
			armor3[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
			armor3[i - 1].addListener(new InputListener(armor3[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new BodyArmor(item3, res));
				}
			});
			currentGroup.addActor(armor3[i - 1]);

			armor4[i - 1] = new Image(res.getTexture(item4));
			armor4[i - 1].layout();
			armor4[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
			armor4[i - 1].addListener(new InputListener(armor4[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new BodyArmor(item4, res));
				}
			});
			currentGroup.addActor(armor4[i - 1]);

			//Armor5
			armor5[i - 1] = new Image(res.getTexture(item5));
			armor5[i - 1].layout();
			armor5[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 9, itemSize, itemSize);
			armor5[i - 1].addListener(new InputListener(armor5[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchase(game, new BodyArmor(item4, res));
				}
			});
			currentGroup.addActor(armor5[i - 1]);
		}
		headGearButton = new Image(res.getTexture("headgear-button"));
		headGearButton.layout();
		headGearButton.setBounds(460,1035,150,60);
		headGearButton.addListener(new InputListener(headGearButton) {
			@Override
			public void doAction() {
				removeCurrentContent();
				loadHeadgear();
				loadHeadgearTitle();
			}
		});
		currentGroup.addActor(headGearButton);
	}

	private void loadHeadgearTitle() {
		int spacing = 172;
		for (int i=1; i<=5; i++) {
			titleHeadgear[i-1] = new Label("Headgear(tier"+i+")", FontManager.getFont(40));
			titleHeadgear[i-1].setX(110);
			titleHeadgear[i-1].setY(Betrayal.HEIGHT - 250 - (i-1)*spacing);
			currentGroup.addActor(titleHeadgear[i-1]);
		}
	}

	private void loadArmorTitle(){
		int spacing = 172;
		for (int i=1; i<=5; i++) {
			titleArmor[i-1] = new Label("Armor(tier"+i+")", FontManager.getFont(40));
			titleArmor[i-1].setX(110);
			titleArmor[i-1].setY(Betrayal.HEIGHT - 250 - (i-1)*spacing);
			currentGroup.addActor(titleArmor[i - 1]);
		}
	}

	private void loadItemsTitle() {
	}

	private void loadItems() {
	}

	private void setContent0() {
		loadSwordTitles();
		loadSwords();
	}

	private void setContent1() {  //armor + headgear
		loadHeadgearTitle();
		loadHeadgear();
	}


	private void setContent2() { // rings
		loadRingTitle();
		loadRings();
	}


	private void setContent3() { // extras
		loadItemsTitle();
		loadItems();
	}


	private void setContent4() { // money
	}

	private void removeCurrentContent() {
		currentGroup.clear();
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
