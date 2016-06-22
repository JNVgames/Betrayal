/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.resources;

import com.jnv.betrayal.gameobjects.BodyArmor;
import com.jnv.betrayal.gameobjects.Cloak;
import com.jnv.betrayal.gameobjects.Helmet;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.gameobjects.Potion;
import com.jnv.betrayal.gameobjects.Ring;
import com.jnv.betrayal.gameobjects.Shield;
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
		loadHealthBar();
		loadSkills();
		loadDungeonTextures();
	}

	public void loadAllData() {
		loadItemsData();
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
		res.loadTexture("character/character_outline.png", "character-outline");
		res.loadTexture("about/option_background.png", "4option-background");
		res.loadTexture("about/option_background200x213.png", "3option-background");

		// Dungeon backgrounds
		for (int i = 1; i <= 5; i++) {
			res.loadTexture("map/dungeon_map" + i + ".png", "map-" + i);
		}
	}

	private void loadButtons() {
		res.loadTexture("button/event_log512x144.png", "event-log-button");
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
		res.loadTexture("button/ok360x144.png", "ok");
		res.loadTexture("button/apply360x144.png", "apply");
		res.loadTexture("button/plus400x400.png", "plus");
		res.loadTexture("button/minus400x400.png", "minus");
		res.loadTexture("button/Armor360x144.png", "armor-button");
		res.loadTexture("button/shields360x144.png", "shield-button");
		res.loadTexture("button/Swords360x144.png", "sword-button");
		res.loadTexture("button/Headgear360x144.png", "headgear-button");

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
		for (int tier = 1; tier <= 5; tier++) {
			for (int color = 1; color <= 6; color++) {
				res.loadTexture("shop/sword" + tier + color + ".png", "sword" + color + tier);
			}
		}
	}

	private void loadShields() {
		for (int tier = 1; tier <= 5; tier++) {
			for (int color = 1; color <= 6; color++) {
				res.loadTexture("shop/shield" + tier + color + ".png", "shield" + color + tier);
			}
		}
	}

	private void loadHealthBar() {
		res.loadTexture("dungeon/healthbar/greenbar214x17.png", "green-bar");
		res.loadTexture("dungeon/healthbar/red_bar52x17.png", "red-bar");
		res.loadTexture("dungeon/healthbar/yellow_bar102x17.png", "yellow-bar");
		res.loadTexture("dungeon/healthbar/health_bar_background330x40.png", "bar-background");
	}

	private void loadHeadGear() {
		for (int tier = 1; tier <= 5; tier++) {
			for (int color = 1; color <= 6; color++) {
				res.loadTexture("shop/headgear" + tier + color + ".png", "headgear" + color + tier);
			}
		}
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
		res.loadTexture("rings/ring1a.png", "ring11");
		res.loadTexture("rings/ring1b.png", "ring12");
		res.loadTexture("rings/ring1c.png", "ring14");
		res.loadTexture("rings/ring1d.png", "ring15");
		res.loadTexture("rings/ring1e.png", "ring16");
		res.loadTexture("rings/ring1f.png", "ring13");
		res.loadTexture("rings/ring2a.png", "ring21");
		res.loadTexture("rings/ring2b.png", "ring22");
		res.loadTexture("rings/ring2c.png", "ring24");
		res.loadTexture("rings/ring2d.png", "ring25");
		res.loadTexture("rings/ring2e.png", "ring26");
		res.loadTexture("rings/ring2f.png", "ring23");
		res.loadTexture("rings/ring3.png", "ring3");
	}

	private void loadArmor() {
		for (int tier = 1; tier < 5; tier++) {
			for (int color = 1; color <= 6; color++) {
				res.loadTexture("shop/armor" + tier + color + ".png", "armor" + color + tier);
			}
		}

		for (int color = 1; color <= 6; color++) {
			res.loadTexture("shop/armor" + 5 + color + ".png", "armor" + color + 5 + "s");
		}

		res.loadTexture("armor/black_cloak.png", "cloak11");
		res.loadTexture("armor/brown_cloak.png", "cloak21");
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
		for (int i = 6; i <= 10; i++) {
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
		res.loadTexture("character/headgear1b.png", "previewheadgear1");
		res.loadTexture("character/headgear1c.png", "previewheadgear2");
		res.loadTexture("character/headgear1d.png", "previewheadgear3");
		res.loadTexture("character/headgear1e.png", "previewheadgear4");
		res.loadTexture("character/headgear1f.png", "previewheadgear5");
		res.loadTexture("character/headgear1g.png", "previewheadgear6");
	}

	private void loadArmorPreview() {
		res.loadTexture("character/armor0.png", "previewarmor0");
		res.loadTexture("character/armor1a.png", "previewarmor1");
		res.loadTexture("character/armor1b.png", "previewarmor2");
		res.loadTexture("character/armor1c.png", "previewarmor3");
		res.loadTexture("character/armor1d.png", "previewarmor4");
		res.loadTexture("character/armor1e.png", "previewarmor5");
		res.loadTexture("character/armor1f.png", "previewarmor6");
		res.loadTexture("character/armor2a.png", "previewarmor15");
		res.loadTexture("character/armor2b.png", "previewarmor25");
		res.loadTexture("character/armor2c.png", "previewarmor35");
		res.loadTexture("character/armor2d.png", "previewarmor45");
		res.loadTexture("character/armor2e.png", "previewarmor55");
		res.loadTexture("character/armor2f.png", "previewarmor65");
		res.loadTexture("character/cloak1b.png", "previewcloak11");
		res.loadTexture("character/cloak1a.png", "previewcloak21");
	}

	private void loadSwordsAndShieldPreviews() {
		// Character swords
		for (int colors = 1; colors <= 6; colors++) {
			res.loadTexture("character/sword1" + colors + ".png",
					"previewsword" + colors + "left");
			res.loadTexture("character/sword2" + colors + ".png",
					"previewsword" + colors + "right");
		}

		// Shield previews
		for (int colors = 1; colors <= 6; colors++) {
			res.loadTexture("character/shield1" + colors + ".png", "previewshield" + colors + "left");
			res.loadTexture("character/shield2" + colors + ".png", "previewshield" + colors + "right");
		}
	}

	private void loadMonsters() {
		// Tier 1
		res.loadTexture("monster/monster2.png", "monster-tier1-0");
		res.loadTexture("monster/monster3.png", "monster-tier1-1");
		res.loadTexture("monster/monster4.png", "monster-tier1-2");
		res.loadTexture("monster/monster5.png", "monster-tier1-3");
		res.loadTexture("monster/monster43.png", "monster-tier1-4");
		res.loadTexture("monster/monster44.png", "monster-tier1-5");
		res.loadTexture("monster/monster45.png", "monster-tier1-6");
		res.loadTexture("monster/monster46.png", "monster-tier1-7");
		res.loadTexture("monster/monster47.png", "monster-tier1-8");
		// Tier 1 Mob
		res.loadTexture("monster/monster1.png", "monster-tier1-9");
		res.loadTexture("monster/monster4.png", "monster-tier1-19");

		//Tier 2
		res.loadTexture("monster/monster6.png", "monster-tier2-0");
		res.loadTexture("monster/monster7.png", "monster-tier2-1");
		res.loadTexture("monster/monster8.png", "monster-tier2-2");
		res.loadTexture("monster/monster9.png", "monster-tier2-3");
		res.loadTexture("monster/monster11.png", "monster-tier2-4");
		res.loadTexture("monster/monster12.png", "monster-tier2-5");
		res.loadTexture("monster/monster17.png", "monster-tier2-6");
		res.loadTexture("monster/monster18.png", "monster-tier2-7");
		res.loadTexture("monster/monster26.png", "monster-tier2-8");
		res.loadTexture("monster/monster14.png", "monster-tier2-9");
		res.loadTexture("monster/monster8.png", "monster-tier2-12");
		res.loadTexture("monster/monster8.png", "monster-tier2-22");

		//Tier 3
		res.loadTexture("monster/monster15.png", "monster-tier3-0");
		res.loadTexture("monster/monster19.png", "monster-tier3-1");
		res.loadTexture("monster/monster25.png", "monster-tier3-2");
		res.loadTexture("monster/monster27.png", "monster-tier3-3");
		res.loadTexture("monster/monster38.png", "monster-tier3-4");
		res.loadTexture("monster/monster24.png", "monster-tier3-5");
		res.loadTexture("monster/monster52.png", "monster-tier3-6");
		res.loadTexture("monster/monster23.png", "monster-tier3-7");
		res.loadTexture("monster/monster10.png", "monster-tier3-9");
		// mob
		res.loadTexture("monster/monster6.png", "monster-tier3-8");
		res.loadTexture("monster/monster1.png", "monster-tier3-18");
		res.loadTexture("monster/monster4.png", "monster-tier3-28");
		// mob
		res.loadTexture("monster/monster15.png", "monster-tier3-10");
		//mob
		res.loadTexture("monster/monster30.png", "monster-tier3-12");


		//mob

		//Tier 4
		res.loadTexture("monster/monster36.png", "monster-tier4-0");
		res.loadTexture("monster/monster13.png", "monster-tier4-1");
		res.loadTexture("monster/monster28.png", "monster-tier4-2");
		res.loadTexture("monster/monster29.png", "monster-tier4-3");
		res.loadTexture("monster/monster37.png", "monster-tier4-4");
		//Tier4 mob
		res.loadTexture("monster/monster20.png", "monster-tier4-5");
		res.loadTexture("monster/monster21.png", "monster-tier4-15");
		res.loadTexture("monster/monster22.png", "monster-tier4-25");
		//Tier 4 mob
		res.loadTexture("monster/monster10.png", "monster-tier4-6");
		res.loadTexture("monster/monster38.png", "monster-tier4-16");
		//Tier 4 mob
		res.loadTexture("monster/monster42.png", "monster-tier4-7");
		res.loadTexture("monster/monster43.png", "monster-tier4-17");
		res.loadTexture("monster/monster44.png", "monster-tier4-27");
		res.loadTexture("monster/monster45.png", "monster-tier4-37");
		res.loadTexture("monster/monster46.png", "monster-tier4-47");
		res.loadTexture("monster/monster47.png", "monster-tier4-57");

		//Tier 5
		res.loadTexture("monster/monster39.png", "monster-tier5-0");
		res.loadTexture("monster/monster40.png", "monster-tier5-1");
		res.loadTexture("monster/monster41.png", "monster-tier5-2");
		res.loadTexture("monster/monster48.png", "monster-tier5-3");
		res.loadTexture("monster/monster49.png", "monster-tier5-4");
		res.loadTexture("monster/monster50.png", "monster-tier5-5");

		//Other
		res.loadTexture("monster/monster1.png", "monster-tier0-0");
		res.loadTexture("monster/monster16.png", "monster-tier0-1");
		res.loadTexture("monster/monster51.png", "monster-tier0-2");
	}

	private void loadDungeonTextures() {
		res.loadTexture("dungeon/buttongraphics/white_outline360x150.png", "actionBarButtonUp360x150");
		res.loadTexture("dungeon/buttongraphics/white_outline360x300.png", "actionBarButtonUp360x300");
		res.loadTexture("dungeon/buttongraphics/white_outline720x150.png", "actionBarButtonUp720x150");
		res.loadTexture("dungeon/buttongraphics/white_outline720x300.png", "actionBarButtonUp720x300");
		res.loadTexture("dungeon/buttongraphics/grey_outline360x150.png", "actionBarButtonDown360x150");
		res.loadTexture("dungeon/buttongraphics/grey_outline360x300.png", "actionBarButtonDown360x300");
		res.loadTexture("dungeon/buttongraphics/grey_outline720x150.png", "actionBarButtonDown720x150");
		res.loadTexture("dungeon/buttongraphics/grey_outline720x300.png", "actionBarButtonDown720x300");
	}

	private void loadItemsData() {
		for (int color = 1; color <= 6; color++) {

			/*******************Swords********************/
			new Weapon.WeaponFactory(res)
					.id(color) // id - 1 to 6
					.name("sword" + color + "1")
					.costBuy(100)
					.attack(2)
					.description("+2 Attack")
					.build();

			new Weapon.WeaponFactory(res)
					.id(color + 6) // id - 7 to 12
					.name("sword" + color + "2")
					.costBuy(200)
					.attack(5)
					.description("+5 Attack")
					.build();

			new Weapon.WeaponFactory(res)
					.id(color + 12) // id - 3 to 18
					.name("sword" + color + "3")
					.costBuy(400)
					.attack(10)
					.description("+10 Attack")
					.build();

			new Weapon.WeaponFactory(res)
					.id(color + 18) // id - 19 to 24
					.name("sword" + color + "4")
					.costBuy(700)
					.attack(15)
					.description("+15 Attack")
					.build();

			new Weapon.WeaponFactory(res)
					.id(color + 24) // id - 25 to 30
					.name("sword" + color + "5")
					.costBuy(1000)
					.attack(20)
					.description("+20 Attack")
					.build();

			/********************SHIELDS********************/

			new Shield.ShieldFactory(res)
					.id(color + 30) // id - 31 to 36
					.name("shield" + color + "1")
					.costBuy(100)
					.defense(2)
					.description("+2 Defense")
					.build();

			new Shield.ShieldFactory(res)
					.id(color + 36) // id - 37 to 42
					.name("shield" + color + "2")
					.costBuy(200)
					.defense(4)
					.description("+4 Defense")
					.build();

			new Shield.ShieldFactory(res)
					.id(color + 42) // id - 43 to 48
					.name("shield" + color + "3")
					.costBuy(400)
					.defense(6)
					.description("+6 Defense")
					.build();

			new Shield.ShieldFactory(res)
					.id(color + 48) // id - 49 to 54
					.name("shield" + color + "4")
					.costBuy(700)
					.defense(8)
					.description("+8 Defense")
					.build();

			new Shield.ShieldFactory(res)
					.id(color + 54) // id - 55 to 60
					.name("shield" + color + "5")
					.costBuy(1000)
					.defense(10)
					.description("+10 Defense")
					.build();

			/********************ARMOR********************/

			new BodyArmor.ArmorFactory(res)
					.id(color + 60) // id - 61 to 66
					.name("armor" + color + "1")
					.costBuy(100)
					.defense(2)
					.description("+2 Defense")
					.build();

			new BodyArmor.ArmorFactory(res)
					.id(color + 66) // id - 67 to 72
					.name("armor" + color + "2")
					.costBuy(200)
					.defense(4)
					.description("+4 Defense")
					.build();

			new BodyArmor.ArmorFactory(res)
					.id(color + 72) // id - 73 to 78
					.name("armor" + color + "3")
					.costBuy(300)
					.defense(6)
					.description("+6 Defense")
					.build();

			new BodyArmor.ArmorFactory(res)
					.id(color + 78) // id - 79 to 84
					.name("armor" + color + "4")
					.costBuy(400)
					.defense(8)
					.description("+8 Defense")
					.build();

			new BodyArmor.ArmorFactory(res)
					.id(color + 84) // id - 85 to 90
					.name("armor" + color + "5s")
					.costBuy(500)
					.defense(10)
					.description("+10 Defense")
					.build();

			/********************HEADGEAR********************/
			new Helmet.HelmetFactory(res)
					.id(color + 90) // id - 91 to 96
					.name("headgear" + color + "1")
					.costBuy(100)
					.defense(2)
					.description("+2 Defense")
					.build();

			new Helmet.HelmetFactory(res)
					.id(color + 96) // id - 97 to 102
					.name("headgear" + color + "2")
					.costBuy(200)
					.defense(4)
					.description("+4 Defense")
					.build();

			new Helmet.HelmetFactory(res)
					.id(color + 102) // id - 103 to 108
					.name("headgear" + color + "3")
					.costBuy(300)
					.defense(6)
					.description("+6 Defense")
					.build();

			new Helmet.HelmetFactory(res)
					.id(color + 108) // id - 109 to 114
					.name("headgear" + color + "4")
					.costBuy(400)
					.defense(8)
					.description("+8 Defense")
					.build();

			new Helmet.HelmetFactory(res)
					.id(color + 114) // id - 115 to 120
					.name("headgear" + color + "5")
					.costBuy(500)
					.defense(10)
					.description("+10 Defense")
					.build();
		}



		//TODO: FINISH THE FACTORIES

		/********************RINGS********************/
		new Ring.RingFactory(res)	//Red Health Ring
				.id(115)
				.name("ring11")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(116)
				.name("ring21")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(117)
				.name("ring12")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(118)
				.name("ring22")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(119)
				.name("ring13")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(120)
				.name("ring23")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(121)
				.name("ring14")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(122)
				.name("ring24")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(123)
				.name("ring15")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(124)
				.name("ring25")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(125)
				.name("ring16")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Ring.RingFactory(res)
				.id(126)
				.name("ring26")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		/********************POTIONS********************/

		new Potion.PotionFactory(res)
				.id(127)
				.name("potion1")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(128)
				.name("potion2")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(129)
				.name("potion3")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(130)
				.name("potion4")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(131)
				.name("potion5")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(132)
				.name("potion6")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(133)
				.name("potion7")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(134)
				.name("potion8")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(135)
				.name("potion9")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(136)
				.name("potion10")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(137)
				.name("potion11")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Potion.PotionFactory(res)
				.id(138)
				.name("potion12")
				.costBuy(100)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+")
				.build();

		new Cloak.CloakFactory(res)
				.id(139)
				.name("cloak11")
				.costBuy(750)
				.description("If the player takes any damage that would bring their health below 0," +
						"the cloak absorbs any further damage and keeps the player at 10 HP." +
						"The cloak is a one-time use item.")
				.build();
	}

	private void loadSkills() {
		res.loadTexture("icon/skills/axe.png", "skill1");
		res.loadTexture("icon/skills/bomb.png", "skill2");
		res.loadTexture("icon/skills/brokensword.png", "skill3");
		res.loadTexture("icon/skills/chains.png", "skill4");
		res.loadTexture("icon/skills/death.png", "skill5");
		res.loadTexture("icon/skills/defensebreak.png", "skill6");
		res.loadTexture("icon/skills/demon.png", "skill7");
		res.loadTexture("icon/skills/explosion.png", "skill8");
		res.loadTexture("icon/skills/fire.png", "skill9");
		res.loadTexture("icon/skills/fist.png", "skill10");
		res.loadTexture("icon/skills/freeze.png", "skill11");
		res.loadTexture("icon/skills/heal.png", "skill12");
		res.loadTexture("icon/skills/horn.png", "skill13");
		res.loadTexture("icon/skills/hourglass.png", "skill14");
		res.loadTexture("icon/skills/lockon.png", "skill15");
		res.loadTexture("icon/skills/magesymbol.png", "skill16");
		res.loadTexture("icon/skills/magesymbol2.png", "skill17");
		res.loadTexture("icon/skills/poison.png", "skill18");
		res.loadTexture("icon/skills/powerdown.png", "skill19");
		res.loadTexture("icon/skills/scythe.png", "skill20");
		res.loadTexture("icon/skills/shield.png", "skill21");
		res.loadTexture("icon/skills/male.png", "skill22");
		res.loadTexture("icon/skills/female.png", "skill23");
	}

	private void loadMonsterData() {
		//Tier 0
		new Monster(1, "monster-tier0-0", res).setData("Skeleton Kelly","monster-tier0-0", 25, 10, 1, 250, 250, 235, 740, 1, 0, 0,0,0,0,0);

		// Tier1
		new Monster(1, "monster-tier1-0", res).setData("The Green Lantern","monster-tier0-0", 25, 10, 1, 350, 400, 185, 740, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier1-1", res).setData("Muriel the Mummy","monster-tier1-1", 25, 10, 1, 300, 350, 210, 740, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier1-2", res).setData("Knight of the Underworld Fred","monster-tier1-2", 25, 10, 1, 250, 300, 235, 740, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier1-3", res).setData("Big Chest","monster-tier1-3", 25, 10, 1, 450, 400, 135, 740, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier1-4", res).setData("Arthur","monster-tier1-4", 25, 10, 1, 300, 350, 210, 740, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier1-5", res).setData("Sir Cumference","monster-tier1-5", 25, 10, 1, 300, 350, 210, 740, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier1-6", res).setData("Peeping Tom","monster-tier1-6", 25, 10, 1, 300, 350, 210, 740, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier1-7", res).setData("Lil Jimmy","monster-tier1-7", 25, 10, 1, 300, 350, 210, 740, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier1-8", res).setData("Jeff the Killer","monster-tier1-8", 25, 10, 1, 300, 350, 210, 740, 1, 0, 0,0,0,0,0);
		// Tier1 mob1
		new Monster(1, "monster-tier1-9", res).setData("Skeleton Kelly","monster-tier1-9", 25, 10, 1, 300, 350, 50, 720, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier1-19", res).setData("Knight of the Underworld Fred","monster-tier1-19", 25, 10, 1, 300, 350, 370, 740, 1, 0, 0,0,0,0,0);

		// Tier2
		new Monster(1, "monster-tier2-0", res).setData("Ghost Rider","monster-tier2-0", 25, 10, 1, 400, 450, 160, 700, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-1", res).setData("Moose","monster-tier2-1", 25, 10, 1, 350, 450, 185, 700, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-2", res).setData("Samurai Joe","monster-tier2-2", 25, 10, 1, 250, 300, 30, 690, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-3", res).setData("David the Tiger","monster-tier2-3", 25, 10, 1, 500, 450, 110, 680, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-4", res).setData("Ed Edd and Eddy","monster-tier2-4", 25, 10, 1, 650, 550, 35, 690, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-5", res).setData("Angry Susan","monster-tier2-5", 25, 10, 1, 500, 500, 110, 670, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-6", res).setData("Purple Delight","monster-tier2-6", 25, 10, 1, 500, 500, 110, 640, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-7", res).setData("Horny Trevor","monster-tier2-7", 25, 10, 1, 400, 450, 160, 650, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-8", res).setData("Dead Mary","monster-tier2-8", 25, 10, 1, 400, 500, 160, 670, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-9", res).setData("It's Your Girlfriend","monster-tier2-9", 25, 10, 1, 400, 500, 160, 670, 1, 0, 0,0,0,0,0);
		// Tier 2 Mob
		new Monster(1, "monster-tier2-12", res).setData("Samurai Poe","monster-tier2-12", 25, 10, 1, 250, 300, 230, 760, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier2-22", res).setData("Samurai Low","monster-tier2-22", 25, 10, 1, 250, 300, 430, 690, 1, 0, 0,0,0,0,0);


		//Tier 3
		new Monster(1, "monster-tier3-0", res).setData("Pack o' Wolves","monster-tier3-0", 25, 10, 1, 350, 300, 340, 670, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-1", res).setData("Hagrid","monster-tier3-1", 25, 10, 1, 600, 500, 60, 640, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-2", res).setData("Tiki To","monster-tier3-2", 25, 10, 1, 400, 450, 0, 690, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-3", res).setData("Ghost","monster-tier3-3", 25, 10, 1, 400, 450, 160, 690, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-4", res).setData("Chad Livingston","monster-tier3-4", 25, 10, 1, 550, 500, 85, 740, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-5", res).setData("Fire Dragon","monster-tier3-5", 25, 10, 1, 500, 550, 110, 665, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-6", res).setData("Envious Kevin","monster-tier3-6", 25, 10, 1, 500, 500, 110, 670, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-7", res).setData("Terrible Terry","monster-tier3-7", 25, 10, 1, 500, 500, 110, 670, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-9", res).setData("No Body Greg","monster-tier3-9", 25, 10, 1, 500, 500, 110, 670, 1, 0, 0,0,0,0,0);
		//Tier3Mob1
		new Monster(1, "monster-tier3-8", res).setData("Ghost Rider","monster-tier3-8", 25, 10, 1, 400, 400, 160, 750, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-18", res).setData("Skeleton Kelly","monster-tier3-18", 25, 10, 1, 250, 300, 20, 660, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier3-28", res).setData("Knight of the Underworld Fred","monster-tier3-28", 25, 10, 1, 250, 300, 480, 680, 1, 0, 0,0,0,0,0);
		//Tier3mob2
		new Monster(1, "monster-tier3-10", res).setData("Pack o' Wolves","monster-tier3-10", 25, 10, 1, 350, 300, 50, 710, 1, 0, 0,0,0,0,0);
		//Tier 3mob 3
		new Monster(1, "monster-tier3-12", res).setData("Tiki Ta","monster-tier3-12", 25, 10, 1, 400, 450, 360, 690, 1, 0, 0,0,0,0,0);

		// Tier4
		new Monster(1, "monster-tier4-0", res).setData("Magic Mike","monster-tier4-0", 25, 10, 1, 500, 475, 110, 675, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-1", res).setData("Ghost Rider","monster-tier4-1", 25, 10, 1, 350, 400, 185, 665, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-2", res).setData("The Thing","monster-tier4-2", 25, 10, 1, 500, 475, 110, 665, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-3", res).setData("Wizard of Narwhal","monster-tier4-3", 25, 10, 1, 500, 475, 110, 665, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-4", res).setData("Fluffy","monster-tier4-4", 25, 10, 1, 500, 475, 110, 665, 1, 0, 0,0,0,0,0);
		//Tier 4 mob
		new Monster(1, "monster-tier4-5", res).setData("Drake","monster-tier4-5", 25, 10, 1, 300, 350, 0, 655, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-15", res).setData("Drako","monster-tier4-15", 25, 10, 1, 350, 400, 185, 765, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-25", res).setData("Drakey","monster-tier4-25", 25, 10, 1, 300, 350, 420, 655, 1, 0, 0,0,0,0,0);
		//Tier 4 mob
		new Monster(1, "monster-tier4-6", res).setData("Rock","monster-tier4-6", 25, 10, 1, 425, 475, 40, 675, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-16", res).setData("Chad Livingston","monster-tier4-16", 25, 10, 1, 225, 250, 400, 740, 1, 0, 0,0,0,0,0);
		//Tier 4 mob
		new Monster(1, "monster-tier4-7", res).setData("Merlin","monster-tier4-7", 25, 10, 1, 200, 250, 260, 675, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-17", res).setData("Arthur","monster-tier4-17", 25, 10, 1, 200, 250, 0, 725, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-27", res).setData("Sir Cumference","monster-tier4-27", 25, 10, 1, 200, 250, 520, 725, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-37", res).setData("Peeping Tom","monster-tier4-37", 25, 10, 1, 200, 250, 120, 815, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-47", res).setData("Lil Jimmy","monster-tier4-47", 25, 10, 1, 200, 250, 400, 815, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier4-57", res).setData("Jeff the Killer","monster-tier4-57", 25, 10, 1, 200, 250, 260, 870, 1, 0, 0,0,0,0,0);

		//Tier5
		new Monster(1, "monster-tier5-0", res).setData("Fattie Triple Chin","monster-tier5-0", 25, 10, 1, 600, 525, 60, 665, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier5-1", res).setData("King of the Mountain, Lioke","monster-tier5-1", 25, 10, 1, 450, 550, 135, 665, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier5-2", res).setData("Dragon of the Floor, Jacob Rivington III","monster-tier5-2", 25, 10, 1, 620, 500, 50, 640, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier5-3", res).setData("Morenth Bringer of Death","monster-tier5-3", 25, 10, 1, 500, 500, 110, 665, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier5-4", res).setData("Lusloth Protector of Life","monster-tier5-4", 25, 10, 1, 500, 500, 110, 665, 1, 0, 0,0,0,0,0);
		new Monster(1, "monster-tier5-5", res).setData("Tough Tanuth","monster-tier5-5", 25, 10, 1, 500, 500, 110, 665, 1, 0, 0,0,0,0,0);
	}
}
