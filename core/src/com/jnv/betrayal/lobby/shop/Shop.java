/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.Equips;
import com.jnv.betrayal.character.Preview;
import com.jnv.betrayal.gameobjects.defense.BodyArmor;
import com.jnv.betrayal.gameobjects.special.Cloak;
import com.jnv.betrayal.gameobjects.defense.Helmet;
import com.jnv.betrayal.gameobjects.usables.Potion;
import com.jnv.betrayal.gameobjects.special.Ring;
import com.jnv.betrayal.gameobjects.defense.Shield;
import com.jnv.betrayal.gameobjects.attack.Weapon;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.InputListener;

import java.awt.Font;

public class Shop extends Popup {

	private Image leftArrow, rightArrow, cloakBuyButton;
	private Image[] potions, ring1, ring2, run;
	private Image[] sword1, sword2, sword3, sword4, sword5;
	private Image[] shield1, shield2, shield3, shield4, shield5;
	private Image[] headgear1, headgear2, headgear3, headgear4, headgear5;
	private Image[] armor1, armor2, armor3, armor4, armor5;
	private Label[] titleHeadgear, titleShield, titleArmor, titleSword;
	private Label cloakPrice, comingSoon, runTitle;
	private int currentContent, buttonHeight, buttonWidth, itemSize;
	private Group currentGroup;
	private Character character;
	private Preview preview;

	public Shop(Betrayal game) {
		super(game);
		character = game.getCurrentCharacter();
		preview = game.getCurrentCharacter().preview;
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
		run = new Image[3];
		loadButtons();
		loadCurrentActors();
	}

	private void loadCurrentActors() {
		popup.addActor(currentGroup);
	}

	private void loadButtons() {
		loadBackground();
		loadWeaponsButton();
		loadArmorButton();
		loadExtrasButton();
		loadItemsButton();
		loadMoneyButton();
		loadContent();
		loadReturnToLobbyButton();
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		popup.addActor(background);
	}

	private void loadReturnToLobbyButton() {
		Image lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - 312) / 2, 110, 312, 100);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadWeaponsButton() {
		Image weapons = new Image(res.getTexture("shop-weapons"));
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
		Image armors = new Image(res.getTexture("shop-armor"));
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
		Image extras = new Image(res.getTexture("shop-extra"));
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
		Image items = new Image(res.getTexture("shop-item"));
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
		Image money = new Image(res.getTexture("shop-money"));
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
					new ShopPurchasePopup(game, sword);
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
					new ShopPurchasePopup(game, new Weapon(item2, res));
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
					new ShopPurchasePopup(game, new Weapon(item3, res));
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
					new ShopPurchasePopup(game, new Weapon(item4, res));
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
					new ShopPurchasePopup(game, new Weapon(item5, res));
				}
			});
			currentGroup.addActor(sword5[i - 1]);
		}

		Image shieldButton = new Image(res.getTexture("shield-button"));
		shieldButton.layout();
		shieldButton.setBounds(460, 1035, 150, 60);
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

	private void loadShields() {
		for (int i = 1; i <= 6; i++) {
			final String item1 = "shield" + i + "1";
			final String item2 = "shield" + i + "2";
			final String item3 = "shield" + i + "3";
			final String item4 = "shield" + i + "4";
			final String item5 = "shield" + i + "5";

			shield1[i - 1] = new Image(res.getTexture(item1));
			shield1[i - 1].layout();
			shield1[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 1, itemSize, itemSize);
			shield1[i - 1].addListener(new InputListener(shield1[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new Shield(item1, res));
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
					new ShopPurchasePopup(game, new Shield(item2, res));
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
					new ShopPurchasePopup(game, new Shield(item3, res));
				}
			});
			currentGroup.addActor(shield3[i - 1]);

			shield4[i - 1] = new Image(res.getTexture(item4));
			shield4[i - 1].layout();
			shield4[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
			shield4[i - 1].addListener(new InputListener(shield2[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new Shield(item4, res));
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
					new ShopPurchasePopup(game, new Shield(item5, res));
				}
			});
			currentGroup.addActor(shield5[i - 1]);
		}
		Image swordButton = new Image(res.getTexture("sword-button"));
		swordButton.layout();
		swordButton.setBounds(460, 1035, 150, 60);
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
		for (int i = 1; i <= 5; i++) {
			titleSword[i - 1] = new Label("Sword (Tier " + i + ")", FontManager.getFont(40));
			titleSword[i - 1].setX(110);
			titleSword[i - 1].setY(Betrayal.HEIGHT - 250 - (i - 1) * spacing);
			currentGroup.addActor(titleSword[i - 1]);
		}
	}

	private void loadShieldTitles() {
		int spacing = 172;
		for (int i = 1; i <= 5; i++) {
			titleShield[i - 1] = new Label("Shield (Tier" + i + ")", FontManager.getFont(40));
			titleShield[i - 1].setX(110);
			titleShield[i - 1].setY(Betrayal.HEIGHT - 250 - (i - 1) * spacing);
			currentGroup.addActor(titleShield[i - 1]);
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
					new ShopPurchasePopup(game, new Ring(item1, res));
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
					new ShopPurchasePopup(game, new Ring(item2, res));
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
						new ShopPurchasePopup(game, new Potion(item, res));
					}
				});
				currentGroup.addActor(potions[i - 1]);
			} else {
				potions[i - 1].setBounds(100 + itemSize * (i - 7), Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
				potions[i - 1].addListener(new InputListener(potions[i - 1]) {
					@Override
					public void doAction() {
						new ShopPurchasePopup(game, new Potion(item, res));
					}
				});
				currentGroup.addActor(potions[i - 1]);
			}
		}

		//TODO TRY THIS LATER


		for (int i=1; i<4; i++){
			final String item = "Run" + i;
			run[i - 1] = new Image(res.getTexture("run"+i));
			run[i - 1].layout();;
			run[i - 1].setBounds(100 + itemSize * (i - 1), Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 9, itemSize, itemSize);
			run[i - 1].addListener(new InputListener(run[i - 1]) {
				@Override
				public void doAction() {
					//todo Created Run Item,
					//new ShopPurchasePopup(game, new Run(item, res));
				}
			});
			currentGroup.addActor(run[i - 1]);
		}

		runTitle = new Label("Run", FontManager.getFont(40));
		runTitle.setX(110);
		runTitle.setY(run[0].getTop()+10);
		currentGroup.addActor(runTitle);
	}

	private void loadRingTitle() {
		Label titleRing1 = new Label("Rings (Tier 1)", FontManager.getFont(40));
		titleRing1.setX(110);
		titleRing1.setY(Betrayal.HEIGHT - 250);
		currentGroup.addActor(titleRing1);

		Label titleRing2 = new Label("Rings (Tier 2)", FontManager.getFont(40));
		titleRing2.setX(110);
		titleRing2.setY(Betrayal.HEIGHT - 422);
		currentGroup.addActor(titleRing2);

		Label titleItems = new Label("Potions", FontManager.getFont(40));
		titleItems.setX(110);
		titleItems.setY(Betrayal.HEIGHT - 250 - 172 * 2);
		currentGroup.addActor(titleItems);
	}

	private void loadHeadgear() {
		// Headgear1
		for (int i = 1; i <= 6; i++) {
			final String tier1 = "headgear" + i + "1";
			final String tier2 = "headgear" + i + "2";
			final String tier3 = "headgear" + i + "3";
			final String tier4 = "headgear" + i + "4";
			final String tier5 = "headgear" + i + "5";
			headgear1[i - 1] = new Image(res.getTexture(tier1));
			headgear1[i - 1].layout();
			headgear1[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize, itemSize, itemSize);
			headgear1[i - 1].addListener(new InputListener(headgear1[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new Helmet(tier1, res));
				}
			});
			currentGroup.addActor(headgear1[i - 1]);

			// Headgear 2
			headgear2[i - 1] = new Image(res.getTexture(tier2));
			headgear2[i - 1].layout();
			headgear2[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
			headgear2[i - 1].addListener(new InputListener(headgear2[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new Helmet(tier2, res));
				}
			});
			currentGroup.addActor(headgear2[i - 1]);

			headgear3[i - 1] = new Image(res.getTexture(tier3));
			headgear3[i - 1].layout();
			headgear3[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
			headgear3[i - 1].addListener(new InputListener(headgear3[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new Helmet(tier3, res));
				}
			});
			currentGroup.addActor(headgear3[i - 1]);

			headgear4[i - 1] = new Image(res.getTexture(tier4));
			headgear4[i - 1].layout();
			headgear4[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
			headgear4[i - 1].addListener(new InputListener(headgear4[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new Helmet(tier4, res));
				}
			});
			currentGroup.addActor(headgear4[i - 1]);

			headgear5[i - 1] = new Image(res.getTexture(tier5));
			headgear5[i - 1].layout();
			headgear5[i - 1].setBounds(100 + itemSize * (i - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 9, itemSize, itemSize);
			headgear5[i - 1].addListener(new InputListener(headgear5[i - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new Helmet(tier5, res));
				}
			});
			currentGroup.addActor(headgear5[i - 1]);
		}
		Image armorButton = new Image(res.getTexture("armor-button"));
		armorButton.layout();
		armorButton.setBounds(460, 1035, 150, 60);
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

	private void loadArmor() {
		for (int color = 1; color <= 6; color++) {
			final String tier1String = "armor" + color + "1";
			final String tier2String = "armor" + color + "2";
			final String tier3String = "armor" + color + "3";
			final String tier4String = "armor" + color + "4";
			final String tier5String = "armor" + color + "5s";

			armor1[color - 1] = new Image(res.getTexture(tier1String));
			armor1[color - 1].layout();
			armor1[color - 1].setBounds(100 + itemSize * (color - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 1, itemSize, itemSize);
			armor1[color - 1].addListener(new InputListener(armor1[color - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new BodyArmor(tier1String, res));
				}
			});
			currentGroup.addActor(armor1[color - 1]);

			armor2[color - 1] = new Image(res.getTexture(tier2String));
			armor2[color - 1].layout();
			armor2[color - 1].setBounds(100 + itemSize * (color - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 3, itemSize, itemSize);
			armor2[color - 1].addListener(new InputListener(armor2[color - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new BodyArmor(tier2String, res));
				}
			});
			currentGroup.addActor(armor2[color - 1]);

			armor3[color - 1] = new Image(res.getTexture(tier3String));
			armor3[color - 1].layout();
			armor3[color - 1].setBounds(100 + itemSize * (color - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 5, itemSize, itemSize);
			armor3[color - 1].addListener(new InputListener(armor3[color - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new BodyArmor(tier3String, res));
				}
			});
			currentGroup.addActor(armor3[color - 1]);

			armor4[color - 1] = new Image(res.getTexture(tier4String));
			armor4[color - 1].layout();
			armor4[color - 1].setBounds(100 + itemSize * (color - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 7, itemSize, itemSize);
			armor4[color - 1].addListener(new InputListener(armor4[color - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new BodyArmor(tier4String, res));
				}
			});
			currentGroup.addActor(armor4[color - 1]);

			//Armor5
			armor5[color - 1] = new Image(res.getTexture(tier5String));
			armor5[color - 1].layout();
			armor5[color - 1].setBounds(100 + itemSize * (color - 1),
					Betrayal.HEIGHT - buttonHeight - 150 - itemSize * 9, itemSize, itemSize);
			armor5[color - 1].addListener(new InputListener(armor5[color - 1]) {
				@Override
				public void doAction() {
					new ShopPurchasePopup(game, new BodyArmor(tier5String, res));
				}
			});
			currentGroup.addActor(armor5[color - 1]);
		}
		Image headGearButton = new Image(res.getTexture("headgear-button"));
		headGearButton.layout();
		headGearButton.setBounds(460, 1035, 150, 60);
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
		for (int i = 1; i <= 5; i++) {
			titleHeadgear[i - 1] = new Label("Helmet (Tier " + i + ")", FontManager.getFont(40));
			titleHeadgear[i - 1].setX(110);
			titleHeadgear[i - 1].setY(Betrayal.HEIGHT - 250 - (i - 1) * spacing);
			currentGroup.addActor(titleHeadgear[i - 1]);
		}
	}

	private void loadArmorTitle() {
		int spacing = 172;
		for (int i = 1; i <= 5; i++) {
			titleArmor[i - 1] = new Label("Armor (Tier " + i + ")", FontManager.getFont(40));
			titleArmor[i - 1].setX(110);
			titleArmor[i - 1].setY(Betrayal.HEIGHT - 250 - (i - 1) * spacing);
			currentGroup.addActor(titleArmor[i - 1]);
		}
	}

	private void loadItemsTitle() {
	}

	private void loadItems() {
		comingSoon = new Label("Coming Soon", FontManager.getFont(40));
		comingSoon.setX(Betrayal.WIDTH/2 - comingSoon.getPrefWidth()/2);
		comingSoon.setY(Betrayal.HEIGHT);
		currentGroup.addActor(comingSoon);
	}

	private void loadSwordAndShieldPage() {
		loadSwordTitles();
		loadSwords();
	}

	private void loadArmorPage() {  //armor + headgear
		loadHeadgearTitle();
		loadHeadgear();
	}

	private void loadExtrasPage() { // rings
		loadRingTitle();
		loadRings();
	}

	private void loadItemsPage() { // extras
		loadItemsTitle();
		loadItems();
	}

	private void loadMoneyPage() { // money
		loadPreviewArrows();
		Actor previewImage = loadMoneyPagePreview();
		Image cloakIcon = loadCloakIcon(previewImage);
		loadCloakDescription(cloakIcon);
		loadCloakButtons();
	}

	private void loadCloakDescription(Image cloakIcon) {
		Label description = new Label("Cloak\n" + new Cloak("cloak11", res).getDescription(), FontManager.getFont(40));
		description.setX(cloakIcon.getRight() + 30);
		description.setY(cloakIcon.getY() - 55);
		description.setWidth(600);
		description.setHeight(90);
		currentGroup.addActor(description);
	}

	private Image loadCloakIcon(Actor previewImage) {
		Image cloakIcon = new Image(res.getTexture("cloak11"));
		cloakIcon.setX(popup.getX() + 120);
		cloakIcon.setY(previewImage.getY() - 200);
		cloakIcon.setWidth(itemSize);
		cloakIcon.setHeight(itemSize);
		currentGroup.addActor(cloakIcon);

		return cloakIcon;
	}

	private Actor loadMoneyPagePreview() {
		preview = character.preview;
		// Create copy of character's preview with the extra weapon
		Equips equips = new Equips(character.equips, res);
		equips.equip(new Cloak("cloak11", res));
		preview = new Preview(character.preview, equips, res);

		final float x = leftArrow.getX() + 50;
		final float y = leftArrow.getTop() + 20;
		final float width = rightArrow.getRight() - leftArrow.getX() - 100;
		final float height = width * 18 / 12;

		Actor previewImage = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				preview.drawPreview(batch, x, y, width, height);
			}
		};
		previewImage.setWidth(width);
		previewImage.setHeight(height);
		previewImage.setX(x);
		previewImage.setY(y);
		currentGroup.addActor(previewImage);

		return previewImage;
	}

	private void loadPreviewArrows() {
		float y = 700, width = 100, height = 50;
		leftArrow = new Image(res.getTexture("arrow-left"));
		leftArrow.layout();
		leftArrow.setBounds(Betrayal.WIDTH / 2 - 150, y, width, height);
		leftArrow.addListener(new InputListener(leftArrow) {
			@Override
			public void doAction() {
				preview.rotateLeft();
			}
		});
		currentGroup.addActor(leftArrow);

		rightArrow = new Image(res.getTexture("arrow-right"));
		rightArrow.layout();
		rightArrow.setBounds(Betrayal.WIDTH / 2 + 50, y, width, height);
		rightArrow.addListener(new InputListener(rightArrow) {
			@Override
			public void doAction() {
				preview.rotateRight();
			}
		});
		currentGroup.addActor(rightArrow);
	}

	private void loadCloakButtons() {
		cloakBuyButton = new Image(res.getTexture("buy"));
		cloakBuyButton.layout();
		cloakBuyButton.setBounds(Betrayal.WIDTH / 2 - 75, 250, 150, 75);
		cloakBuyButton.addListener(new InputListener(cloakBuyButton) {
			@Override
			public void doAction() {
				new Confirmation(game, "Confirm purchase") {
					@Override
					public void doAction() {
						switch (character.inventory.buyItem(new Cloak("cloak11", res))) {
							case 0:
								new OKPopup(game, "Item Bought");
								break;
							case 1:
								new OKPopup(game, "Not Enough Gold");
								break;
							case 2:
								new OKPopup(game, "Inventory Full");
								break;
							default:
								throw new AssertionError("Shop Transaction Error: buyItem() returned weird number");
						}
					}
				};
			}
		});

		currentGroup.addActor(cloakBuyButton);
		cloakPrice = new Label("Price: " + Integer.toString(new Cloak("cloak11", res).getBuyCost()), FontManager.getFont((60)));
		cloakPrice.setX((leftArrow.getX() + 40));
		cloakPrice.setY(cloakBuyButton.getY() + 110);
		currentGroup.addActor(cloakPrice);

	}


	private void removeCurrentContent() {
		currentGroup.clear();
	}

	private void loadContent() {
		switch (currentContent) {
			case 0:
				loadSwordAndShieldPage();
				break;
			case 1:
				loadArmorPage();
				break;
			case 2:
				loadExtrasPage();
				break;
			case 3:
				loadItemsPage();
				break;
			case 4:
				loadMoneyPage();
				break;
			default:
				Gdx.app.log("content", "should not happen");
				break;
		}
	}
}
