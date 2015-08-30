/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.resources;

import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.gameobjects.Weapon;

public class ResourceLoader {

	private BetrayalAssetManager res;

	public ResourceLoader(BetrayalAssetManager res) {
		this.res = res;
	}

	public void loadLoadingScreen() {
		res.loadTexture("about/jnv720x1280.png", "splash");
	}

	public void loadAll() {
		loadBackgrounds();
		loadButtons();
		loadLobbyButtons();
		loadShopButtons();
		loadCombatIcons();
		loadItems();
		loadCharacterPreviewsImages();
		loadMonsters();
	}

	public void loadAllData() {
		loadWeaponData();
		loadMonsterData();
	}

	private void loadItems() {
		loadSwordTier1();
		loadShields();
		loadHeadGear();
		loadPotions();
		loadRings();
		loadArmor();
	}

	private void loadBackgrounds() {
		res.loadTexture("about/start_screen720x1280.png", "start-background");
		res.loadTexture("about/hall_of_fame720x2000.png", "hall-of-fame-background");
		res.loadTexture("about/instructions_background720x1280.png", "instructions-background");
		res.loadTexture("about/confirmation_background720x1280.png", "confirmation-background");
		res.loadTexture("about/lobby720x1280.png", "lobby-screen");
		res.loadTexture("about/shop_background720x1280.png", "shop-background");
		res.loadTexture("about/shop_purchase_background720x1280.png", "shop-purchase-background");
		res.loadTexture("about/game_over720x1280.png", "game-over-background");
		res.loadTexture("about/tower.png", "tower");
		res.loadTexture("about/lobby-tower.png", "lobby-tower");

		// Dungeon backgrounds
		for (int i = 1; i <= 5; i++) {
			res.loadTexture("map/dungeon_map" + i + ".png", "map-" + i);
		}
	}

	private void loadButtons() {
		res.loadTexture("button/load_game_button512x144.png", "load-game");
		res.loadTexture("button/load_game_grey512x144.png", "load-game-grey");
		res.loadTexture("button/new_game_button512x144.png", "new-game");
		res.loadTexture("button/options_button512x144.png", "options");
		res.loadTexture("button/instructions512x144.png", "instructions");
		res.loadTexture("button/hall_of_fame512x144.png", "hall-of-fame");
		res.loadTexture("button/play_now512x144.png", "play-now");
		res.loadTexture("button/play_now_pressed512x144.png", "play-now-pressed");
		res.loadTexture("button/main_menu512x144.png", "main-menu");
		res.loadTexture("button/back_to_lobby512x144.png", "back-to-lobby");
		res.loadTexture("button/arrow256x256.png", "arrow-right");
		res.loadTexture("button/arrow_left256x256.png", "arrow-left");
		res.loadTexture("button/circle_x150x150.png", "x");
		res.loadTexture("button/buy360x144.png", "buy");
		res.loadTexture("button/back360x144.png", "back");
		res.loadTexture("button/cancel360x144.png", "cancel");
		res.loadTexture("button/color360x144.png", "color");
		res.loadTexture("button/equip360x144.png", "equip");
		res.loadTexture("button/sell360x144.png", "sell");
		res.loadTexture("button/preview360x144.png", "preview");
		res.loadTexture("button/yes360x144.png", "yes");
		res.loadTexture("button/no360x144.png", "no");
	}

	private void loadLobbyButtons() {
		res.loadTexture("button/lobby/floor.png", "lobby-floor");
		res.loadTexture("button/lobby/inventory.png", "lobby-inventory");
		res.loadTexture("button/lobby/party.png", "lobby-party");
		res.loadTexture("button/lobby/settings.png", "lobby-settings");
		res.loadTexture("button/lobby/shop.png", "lobby-shop");
		res.loadTexture("button/lobby/stats.png", "lobby-stats");
	}

	private void loadShopButtons() {
		res.loadTexture("button/shop/armor.png", "shop-armor");
		res.loadTexture("button/shop/armor_pressed.png", "shop-armor-pressed");
		res.loadTexture("button/shop/extra.png", "shop-extra");
		res.loadTexture("button/shop/Extra_pressed.png", "shop-extra-pressed");
		res.loadTexture("button/shop/item.png", "shop-item");
		res.loadTexture("button/shop/item_pressed.png", "shop-item-pressed");
		res.loadTexture("button/shop/weapons.png", "shop-weapons");
		res.loadTexture("button/shop/weapons_pressed.png", "shop-weapons-pressed");
		res.loadTexture("button/shop/money.png", "shop-money");
		res.loadTexture("button/shop/money_pressed.png", "shop-money-pressed");
	}

	private void loadCombatIcons() {
		res.loadTexture("icon/icon_gold34x32.png", "icon-gold");
		res.loadTexture("icon/attack250x250.png", "attack");
		res.loadTexture("icon/defense250x250.png", "defense");
		res.loadTexture("icon/health250x250.png", "health");
		res.loadTexture("icon/heal250x250.png", "heal");
		res.loadTexture("icon/damage250x250.png", "damage");
		res.loadTexture("icon/block250x250.png", "block");
	}

	private void loadSwordTier1() {
		res.loadTexture("item/sword1.png", "sword11");
		res.loadTexture("item/sword2.png", "sword12");
		res.loadTexture("item/sword3.png", "sword13");
		res.loadTexture("item/sword4.png", "sword14");
		res.loadTexture("item/sword5.png", "sword15");
		res.loadTexture("item/sword6.png", "sword16");
		res.loadTexture("item/sword1.png", "sword21");
		res.loadTexture("item/sword2.png", "sword22");
		res.loadTexture("item/sword3.png", "sword23");
		res.loadTexture("item/sword4.png", "sword24");
		res.loadTexture("item/sword5.png", "sword25");
		res.loadTexture("item/sword6.png", "sword26");
	}

	private void loadShields() {
		res.loadTexture("item/shield1.png", "shield1");
		res.loadTexture("item/shield2.png", "shield2");
		res.loadTexture("item/shield3.png", "shield3");
		res.loadTexture("item/shield4.png", "shield4");
		res.loadTexture("item/shield5.png", "shield5");
		res.loadTexture("item/shield6.png", "shield6");
	}

	private void loadHeadGear() {
		res.loadTexture("item/helmet1.png", "headgear1");
		res.loadTexture("item/helmet2.png", "headgear2");
		res.loadTexture("item/helmet3.png", "headgear3");
		res.loadTexture("item/helmet4.png", "headgear4");
		res.loadTexture("item/helmet5.png", "headgear5");
		res.loadTexture("item/helmet6.png", "headgear6");
	}

	private void loadPotions() {
		res.loadTexture("item/potion1.png", "potion1");
		res.loadTexture("item/potion2.png", "potion2");
		res.loadTexture("item/potion3.png", "potion3");
		res.loadTexture("item/potion4.png", "potion4");
		res.loadTexture("item/potion5.png", "potion5");
		res.loadTexture("item/potion6.png", "potion6");
		res.loadTexture("item/potion7.png", "potion7");
		res.loadTexture("item/potion8.png", "potion8");
		res.loadTexture("item/potion9.png", "potion9");
		res.loadTexture("item/potion10.png", "potion10");
		res.loadTexture("item/potion11.png", "potion11");
		res.loadTexture("item/potion12.png", "potion12");
	}

	private void loadRings() {
		res.loadTexture("rings/ring1a.png", "ring1-1");
		res.loadTexture("rings/ring1b.png", "ring1-2");
		res.loadTexture("rings/ring1c.png", "ring1-4");
		res.loadTexture("rings/ring1d.png", "ring1-5");
		res.loadTexture("rings/ring1e.png", "ring1-6");
		res.loadTexture("rings/ring1f.png", "ring1-3");
		res.loadTexture("rings/ring2a.png", "ring2-1");
		res.loadTexture("rings/ring2b.png", "ring2-2");
		res.loadTexture("rings/ring2c.png", "ring2-4");
		res.loadTexture("rings/ring2d.png", "ring2-5");
		res.loadTexture("rings/ring2e.png", "ring2-6");
		res.loadTexture("rings/ring2f.png", "ring2-3");
		res.loadTexture("rings/ring3.png", "ring3");
	}

	private void loadArmor() {
		res.loadTexture("armor/red_armor.png", "armor1-1");
		res.loadTexture("armor/black_armor.png", "armor1-2");
		res.loadTexture("armor/gold_armor.png", "armor1-3");
		res.loadTexture("armor/blue_armor.png", "armor1-4");
		res.loadTexture("armor/green_armor.png", "armor1-5");
		res.loadTexture("armor/purple_armor.png", "armor1-6");

		res.loadTexture("armor/red_hero.png", "armor2-1");
		res.loadTexture("armor/black_hero.png", "armor2-2");
		res.loadTexture("armor/gold_hero.png", "armor2-3");
		res.loadTexture("armor/blue_hero.png", "armor2-4");
		res.loadTexture("armor/green_hero.png", "armor2-5");
		res.loadTexture("armor/purple_hero.png", "armor2-6");

		res.loadTexture("armor/brown_cloak.png", "armor-cloak-brown");
		res.loadTexture("armor/black_cloak.png", "armor-cloak-black");
	}

	private void loadCharacterPreviewsImages() {
		loadHeads();
		loadHeadGearPreview();
		loadArmorPreview();
		loadSwordsAndShieldPreviews();
	}

	private void loadHeads() {
		/** Format for loading: hair-gender-hairStyle-hairColor-all */
		for (int i = 1; i <= 5; i++) {
			// Male heads
			res.loadTexture("character/head" + i + "a.png", "hair-male-" + i + "-1-all");
			res.loadTexture("character/head" + i + "b.png", "hair-male-" + i + "-2-all");
			res.loadTexture("character/head" + i + "c.png", "hair-male-" + i + "-3-all");
			res.loadTexture("character/head" + i + "d.png", "hair-male-" + i + "-4-all");
			res.loadTexture("character/head" + i + "e.png", "hair-male-" + i + "-5-all");
			res.loadTexture("character/head" + i + "f.png", "hair-male-" + i + "-6-all");
			res.loadTexture("character/head" + i + "g.png", "hair-male-" + i + "-7-all");
		}
		for (int i = 6; i <= 9; i++) {
			// Female heads
			res.loadTexture("character/head" + i + "a.png",
					"hair-female-" + (i - 5) + "-1-all");
			res.loadTexture("character/head" + i + "b.png",
					"hair-female-" + (i - 5) + "-2-all");
			res.loadTexture("character/head" + i + "c.png",
					"hair-female-" + (i - 5) + "-3-all");
			res.loadTexture("character/head" + i + "d.png",
					"hair-female-" + (i - 5) + "-4-all");
			res.loadTexture("character/head" + i + "e.png",
					"hair-female-" + (i - 5) + "-5-all");
			res.loadTexture("character/head" + i + "f.png",
					"hair-female-" + (i - 5) + "-6-all");
			res.loadTexture("character/head" + i + "g.png",
					"hair-female-" + (i - 5) + "-7-all");
		}
	}

	private void loadHeadGearPreview() {
		res.loadTexture("character/headgear1a.png", "char-headgear-normal");
		res.loadTexture("character/headgear1b.png", "char-headgear-red");
		res.loadTexture("character/headgear1c.png", "char-headgear-black");
		res.loadTexture("character/headgear1d.png", "char-headgear-gold");
		res.loadTexture("character/headgear1e.png", "char-headgear-blue");
		res.loadTexture("character/headgear1f.png", "char-headgear-green");
		res.loadTexture("character/headgear1g.png", "char-headgear-purple");
	}

	private void loadArmorPreview() {
		res.loadTexture("character/armor0.png", "char-armor-peasant");
		res.loadTexture("character/armor1a.png", "char-armor-grey");
		res.loadTexture("character/armor1b.png", "char-armor-red");
		res.loadTexture("character/armor1c.png", "char-armor-black");
		res.loadTexture("character/armor1d.png", "char-armor-blue");
		res.loadTexture("character/armor1e.png", "char-armor-green");
		res.loadTexture("character/armor1f.png", "char-armor-pruple");
		res.loadTexture("character/armor2a.png", "char-armor-red");
		res.loadTexture("character/armor2b.png", "char-armor-black");
		res.loadTexture("character/armor2c.png", "char-armor-gold");
		res.loadTexture("character/armor2d.png", "char-armor-blue");
		res.loadTexture("character/armor2e.png", "char-armor-green");
		res.loadTexture("character/armor2f.png", "char-armor-purple");
		res.loadTexture("character/cloak1a.png", "char-cloak-brown");
		res.loadTexture("character/cloak1b.png", "char-cloak-black");
	}

	private void loadSwordsAndShieldPreviews() {
		// Character swords
		for (int tiers = 1; tiers <= 2; tiers++) {
			for (int colors = 1; colors <= 6; colors++) {
				res.loadTexture("character/sword" + tiers + colors + ".png",
						"char-sword" + tiers + colors);
			}
		}

		// Shields
		res.loadTexture("shields/shield1a.png", "shield-1-red");
		res.loadTexture("shields/shield1b.png", "shield-1-black");
		res.loadTexture("shields/shield1c.png", "shield-1-gold");
		res.loadTexture("shields/shield1d.png", "shield-1-blue");
		res.loadTexture("shields/shield1e.png", "shield-1-green");
		res.loadTexture("shields/shield1f.png", "shield-1-purple");
		res.loadTexture("shields/shield2a.png", "shield-2-red");
		res.loadTexture("shields/shield2b.png", "shield-2-black");
		res.loadTexture("shields/shield2c.png", "shield-2-gold");
		res.loadTexture("shields/shield2d.png", "shield-2-blue");
		res.loadTexture("shields/shield2e.png", "shield-2-green");
		res.loadTexture("shields/shield2f.png", "shield-2-purple");
	}

	private void loadMonsters() {
		// Tier 1
		res.loadTexture("monster/monster1.png", "monster-tier1-0");
		res.loadTexture("monster/monster2.png", "monster-tier1-1");
		res.loadTexture("monster/monster3.png", "monster-tier1-2");
		res.loadTexture("monster/monster4.png", "monster-tier1-3");
		res.loadTexture("monster/monster5.png", "monster-tier1-4");
		res.loadTexture("monster/monster43.png", "monster-tier1-5");
		res.loadTexture("monster/monster44.png", "monster-tier1-6");
		res.loadTexture("monster/monster45.png", "monster-tier1-7");
		res.loadTexture("monster/monster46.png", "monster-tier1-8");
		res.loadTexture("monster/monster47.png", "monster-tier1-9");

		//Tier 2
		res.loadTexture("monster/monster6.png", "monster-tier2-0");
		res.loadTexture("monster/monster7.png", "monster-tier2-1");
		res.loadTexture("monster/monster8.png", "monster-tier2-2");
		res.loadTexture("monster/monster9.png", "monster-tier2-3");
		res.loadTexture("monster/monster11.png", "monster-tier2-4");
		res.loadTexture("monster/monster12.png", "monster-tier2-5");
		res.loadTexture("monster/monster17.png", "monster-tier2-6");
		res.loadTexture("monster/monster18.png", "monster-tier2-7");
		res.loadTexture("monster/monster25.png", "monster-tier2-8");
		res.loadTexture("monster/monster14.png", "monster-tier2-9");

		//Tier 3
		res.loadTexture("monster/monster15.png", "monster-tier3-0");
		res.loadTexture("monster/monster19.png", "monster-tier3-1");
		res.loadTexture("monster/monster26.png", "monster-tier3-2");
		res.loadTexture("monster/monster27.png", "monster-tier3-3");
		res.loadTexture("monster/monster38.png", "monster-tier3-4");
		res.loadTexture("monster/monster24.png", "monster-tier3-5");
		res.loadTexture("monster/monster36.png", "monster-tier3-6");

		//Tier 4
		res.loadTexture("monster/monster10.png", "monster-tier4-0");
		res.loadTexture("monster/monster13.png", "monster-tier4-1");
		res.loadTexture("monster/monster28.png", "monster-tier4-2");
		res.loadTexture("monster/monster29.png", "monster-tier4-3");
		res.loadTexture("monster/monster37.png", "monster-tier4-4");
		//Tier4 mob
		res.loadTexture("monster/monster20.png", "monster-tier4-5");
		res.loadTexture("monster/monster21.png", "monster-tier4-6");
		res.loadTexture("monster/monster22.png", "monster-tier4-7");
		res.loadTexture("monster/monster23.png", "monster-tier4-8");
		//Tier 4 mob
		res.loadTexture("monster/monster30.png", "monster-tier4-9");
		res.loadTexture("monster/monster31.png", "monster-tier4-10");
		res.loadTexture("monster/monster32.png", "monster-tier4-11");
		res.loadTexture("monster/monster33.png", "monster-tier4-12");
		res.loadTexture("monster/monster34.png", "monster-tier4-13");
		res.loadTexture("monster/monster35.png", "monster-tier4-14");

		//Tier 5
		res.loadTexture("monster/monster39.png", "monster-tier5-0");
		res.loadTexture("monster/monster40.png", "monster-tier5-1");
		res.loadTexture("monster/monster41.png", "monster-tier5-2");
		res.loadTexture("monster/monster48.png", "monster-tier5-3");
		res.loadTexture("monster/monster49.png", "monster-tier5-4");
		res.loadTexture("monster/monster50.png", "monster-tier5-5");

		//Other
		res.loadTexture("monster/monster16.png", "monster-tier0-0");
	}

	private void loadWeaponData() {
		for (int i = 1; i <= 6; i++) {
			new Weapon(i, "sword1" + i, res);
		}
		/*

        for (int i = 1; i <= 6; i++) {
            new Shield(i, "sword" + i);
        }
        for (int i = 1; i <= 6; i++) {
            new Ring(i, "sword" + i);
        }
        for (int i = 1; i <= 6; i++) {
            new Item(i, "sword" + i);
        }

        for (int i = 1; i <= 6; i++) {
            new Weapon(i + 6, "sword2" + i);
        }

        new BodyArmor(0, "char-armor-peasant");
        */
	}

	private void loadMonsterData() {
		// Tier1
		new Monster(1, "monster-tier1-0", res).setData("monster-tier1-0", 25, 10, 1);
		new Monster(1, "monster-tier1-1", res).setData("monster-tier1-1", 25, 10, 1);
		new Monster(1, "monster-tier1-2", res).setData("monster-tier1-2", 25, 10, 1);
		new Monster(1, "monster-tier1-3", res).setData("monster-tier1-3", 25, 10, 1);
		new Monster(1, "monster-tier1-4", res).setData("monster-tier1-4", 25, 10, 1);
		new Monster(1, "monster-tier1-5", res).setData("monster-tier1-5", 25, 10, 1);
		new Monster(1, "monster-tier1-6", res).setData("monster-tier1-6", 25, 10, 1);
		new Monster(1, "monster-tier1-7", res).setData("monster-tier1-7", 25, 10, 1);
		new Monster(1, "monster-tier1-8", res).setData("monster-tier1-8", 25, 10, 1);
		new Monster(1, "monster-tier1-9", res).setData("monster-tier1-9", 25, 10, 1);

		// Tier 2
	}
}
