/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.resources;

import com.jnv.betrayal.dungeon.effects.IncreasedAttack;
import com.jnv.betrayal.dungeon.effects.SkipTurn;
import com.jnv.betrayal.dungeon.effects.actions.Bomb;
import com.jnv.betrayal.dungeon.effects.actions.Heal;
import com.jnv.betrayal.dungeon.effects.actions.TrueDamage;
import com.jnv.betrayal.dungeon.effects.buffs.AttackAndDefenseUp;
import com.jnv.betrayal.dungeon.effects.buffs.AttackUp;
import com.jnv.betrayal.dungeon.effects.buffs.DefenseUp;
import com.jnv.betrayal.dungeon.effects.debuffs.AttackAndDefenseDown;
import com.jnv.betrayal.dungeon.effects.debuffs.AttackDown;
import com.jnv.betrayal.dungeon.effects.debuffs.DefenseDown;
import com.jnv.betrayal.dungeon.effects.debuffs.Poison;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.gameobjects.attack.Weapon;
import com.jnv.betrayal.gameobjects.defense.BodyArmor;
import com.jnv.betrayal.gameobjects.defense.Helmet;
import com.jnv.betrayal.gameobjects.defense.Shield;
import com.jnv.betrayal.gameobjects.special.Cloak;
import com.jnv.betrayal.gameobjects.special.Ring;
import com.jnv.betrayal.gameobjects.usables.Potion;
import com.jnv.betrayal.gameobjects.usables.Run;

public class ResourceLoader {

	private BetrayalAssetManager res;

	public ResourceLoader(BetrayalAssetManager res) {
		this.res = res;
		FontManager.loadFonts();
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
		loadItemsAndSkills();
		loadCharacterPreviewsImages();
		loadMonsters();
		loadHealthBar();
		loadDungeonTextures();
		loadInstructions();
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
		res.loadTexture("about/whtie.png", "gray");
		res.loadTexture("about/exc.png", "exc");
		res.loadTexture("about/empty.png", "empty");
		res.loadTexture("about/player-background.png", "player-background");
		res.loadTexture("about/start_screen720x1280.png", "start-background");
		res.loadTexture("about/hall_of_fame720x2000.png", "hall-of-fame-background");
		res.loadTexture("about/instructions_background720x1280.png", "instructions-background");
		res.loadTexture("about/confirmation_background720x1280.png", "confirmation-background");
		res.loadTexture("about/lobby720x1280.png", "lobby-screen");
		res.loadTexture("about/shop_background720x1280.png", "shop-background");
		res.loadTexture("about/shop_purchase_background720x1280.png", "shop-purchase-background");
		res.loadTexture("about/lobby-tower.png", "lobby-tower");
		res.loadTexture("character/character_outline.png", "character-outline");
		res.loadTexture("about/option_background.png", "4option-background");
		res.loadTexture("about/option_background200x213.png", "3option-background");
		res.loadTexture("about/whitepixel.png", "white-pixel");

		int j = 1;
		for(int i = 1; i <=5; i++){
				res.loadTexture("map/tier" + i + "/" + j + ".png", "map" + i + j );
		}
		res.loadTexture("map/tier1/1" + ".png", "map" + "0" + "1" );
	}
	private void loadInstructions(){
		res.loadTexture("instructions/createCharacterCustomize.png","createCharacterCustomize");
		res.loadTexture("instructions/createCharacterName.png","createCharacterName");
		res.loadTexture("instructions/createRoomPassword.png","createRoomPassword");
		res.loadTexture("instructions/inventoryEject.png","inventoryEject");
		res.loadTexture("instructions/inventoryEquip.png","inventoryEquip");
		res.loadTexture("instructions/joinRoomPassword.png","joinRoomPassword");
		res.loadTexture("instructions/LoadGameSelect.png","loadGameSelect");
		res.loadTexture("instructions/lobbyEnterDungeon.png","lobbyEnterDungeon");
		res.loadTexture("instructions/lobbyInventory.png","lobbyInventory");
		res.loadTexture("instructions/lobbyOption.png","lobbyOption");
		res.loadTexture("instructions/lobbyReady.png","lobbyReady");
		res.loadTexture("instructions/lobbyRoom.png","lobbyRoom");
		res.loadTexture("instructions/lobbyShop.png","lobbyShop");
		res.loadTexture("instructions/lobbyStats.png","lobbyStats");
		res.loadTexture("instructions/mainMenuHallOfFame.png","mainMenuHallOfFame");
		res.loadTexture("instructions/mainMenuLoadGame.png","mainMenuLoadGame");
		res.loadTexture("instructions/mainMenuNewGame.png","mainMenuNewGame");
		res.loadTexture("instructions/onlineCreateRoom.png","onlineCreateRoom");
		res.loadTexture("instructions/onlineLeaveRoom.png","onlineLeaveRoom");
		res.loadTexture("instructions/onlineRoomJoin.png","onlineRoomJoin");
		res.loadTexture("instructions/shopItemTap.png","shopItemTap");
		res.loadTexture("instructions/shopTab.png","shopTab");
		res.loadTexture("instructions/statsAdjust.png","statsAdjust");
		res.loadTexture("instructions/statsApply.png","statsApply");
		res.loadTexture("instructions/instructions.png", "instruction");
		res.loadTexture("instructions/characterCreation.png", "characterCreation");
		res.loadTexture("instructions/dungeon.png", "dungeon");
		res.loadTexture("instructions/dungeonAttack.png", "dungeonAttack");
		res.loadTexture("instructions/dungeonDeathWarning.png", "dungeonDeathWarning");
		res.loadTexture("instructions/dungeonDefend.png", "dungeonDefend");
		res.loadTexture("instructions/dungeonEventLog.png", "dungeonEventLog");
		res.loadTexture("instructions/dungeonFlee.png", "dungeonFlee");
		res.loadTexture("instructions/dungeonItems.png", "dungeonItems");
		res.loadTexture("instructions/dungeonKillMonster.png", "dungeonKillMonster");
		res.loadTexture("instructions/dungeonMonsterAttackExplanation.png", "dungeonMonsterAttackExplanation");
		res.loadTexture("instructions/dungeonMonsterSkill.png", "dungeonMonsterSkill");
		res.loadTexture("instructions/dungeonTapForStats.png", "dungeonTapForStats");
		res.loadTexture("instructions/inventory.png", "inventory");
		res.loadTexture("instructions/loadGamePage.png", "loadGamePage");
		res.loadTexture("instructions/lobby.png", "lobby");
		res.loadTexture("instructions/lobbyCheckTeammateStats.png", "lobbyCheckTeammateStats");
		res.loadTexture("instructions/shop.png", "shop");
		res.loadTexture("instructions/stats.png", "stats");
		res.loadTexture("instructions/dungeonMonsterHealthScale.png", "dungeonMonsterHealthScale");
		res.loadTexture("instructions/dungeonRewardSplit.png", "dungeonRewardSplit");
		res.loadTexture("instructions/gameObjective.png", "gameObjective");

	}

	private void loadButtons() {
		res.loadTexture("button/about.png", "about-button");
		res.loadTexture("button/event_log512x144.png", "event-log-button");
		res.loadTexture("button/create_room_button512x144.png", "create-room");
		res.loadTexture("button/leave_room_button512x144.png", "leave-room");
		res.loadTexture("button/join_room_button512x144.png", "join-room");
		res.loadTexture("button/join_room_grey_button512x144.png", "join-room-grey");
		res.loadTexture("button/create_room_grey_button512x144.png", "create-room-grey");
		res.loadTexture("button/leave_room_grey_button512x144.png", "leave-room-grey");
		res.loadTexture("button/load_game_button512x144.png", "load-game");
		res.loadTexture("button/load_game_grey512x144.png", "load-game-grey");
		res.loadTexture("button/new_game_button512x144.png", "new-game");
		res.loadTexture("button/options_button512x144.png", "options");
		res.loadTexture("button/instructions512x144.png", "instructions");
		res.loadTexture("button/hall_of_fame512x144.png", "hall-of-fame");
		res.loadTexture("button/play_now512x144.png", "play-now");
		res.loadTexture("button/play_now_pressed512x144.png", "play-now-pressed");
		res.loadTexture("button/ready512x144.png", "ready");
		res.loadTexture("button/unready512x144.png", "unready");
		res.loadTexture("button/ready_pressed512x144.png", "ready-pressed");
		res.loadTexture("button/main_menu512x144.png", "main-menu");
		res.loadTexture("button/back_to_lobby512x144.png", "back-to-lobby");
		res.loadTexture("button/back512x144.png", "back-button");
		res.loadTexture("button/delete360x144.png", "delete-button");
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
		res.loadTexture("icon/greencircle100x100.png", "green-circle");
		res.loadTexture("icon/redcircle100x100.png", "red-circle");
		res.loadTexture("icon/tellowcircle100x100.png", "yellow-circle");
		res.loadTexture("button/character_preview_border.png", "cpb");
		res.loadTexture("button/cpbBronze.png","cpbBronze");
		res.loadTexture("button/cpbSilver.png","cpbSilver");
		res.loadTexture("button/cpbGold.png","cpbGold");
		res.loadTexture("button/cpbLegend.png","cpbLegend");
		res.loadTexture("button/cpbLegendLegend.png","cpbLegendLegend");
		res.loadTexture("button/cpbWhite.png","cpbWhite");
	}

	private void loadLobbyButtons() {
		res.loadTexture("button/lobby/floor.png", "lobby-floor");
		res.loadTexture("button/lobby/inventory.png", "lobby-inventory");
		res.loadTexture("button/lobby/party.png", "lobby-party");
		res.loadTexture("button/lobby/settings.png", "lobby-settings");
		res.loadTexture("button/lobby/shop.png", "lobby-shop");
		res.loadTexture("button/lobby/stats.png", "lobby-stats");
		res.loadTexture("button/lobby/chatDescription.png", "chatDescription");
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
		res.loadTexture("icon/attack_boost250x250.png", "attack-boost");
		res.loadTexture("icon/defense250x250.png", "defense");
		res.loadTexture("icon/defense_boost250x250.png", "defense-boost");
		res.loadTexture("icon/health250x250.png", "health");
		res.loadTexture("icon/heal250x250.png", "heal");
		res.loadTexture("icon/damage250x250.png", "damage");
		res.loadTexture("icon/block250x250.png", "block");
		res.loadTexture("item/skills/poisonImage.png", "poison");
		res.loadTexture("item/skills/bombImage.png", "bomb");
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
		res.loadTexture("dungeon/monster_select312x312.png", "cross-hair");
		res.loadTexture("dungeon/bluetriangle.png", "blueT");
		res.loadTexture("dungeon/redtriangle.png", "redT");
		res.loadTexture("dungeon/purpletriangle.png", "purpleT");
		res.loadTexture("dungeon/greentriangle.png", "greenT");
		res.loadTexture("dungeon/eventlog-border.png", "eventlog-border");

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

	private void loadItemsAndSkills(){
		res.loadTexture("item/skills/monsterblock.png", "monsterblock");
		res.loadTexture("item/skills/monsterbomb.png", "monsterbomb");
		res.loadTexture("item/skills/monsterdeath.png", "monsterdeath");
		res.loadTexture("item/skills/monsterheal.png", "monsterheal");
		res.loadTexture("item/skills/monstermelee.png", "monstermelee");
		res.loadTexture("item/skills/monsterpoison.png", "monsterpoison");
		res.loadTexture("item/skills/monsterpowerdown.png", "monsterpowerdown");
		res.loadTexture("item/skills/monsterpowerup.png", "monsterpowerup");
		res.loadTexture("item/skills/monstershieldbreak.png", "monstershieldbreak");
		res.loadTexture("item/skills/monsterAttackDown.png", "monsterattackdown");
		res.loadTexture("item/skills/increasedAttack.png", "monsterincreasedattack");
		res.loadTexture("item/skills/run50.png", "run1");
		res.loadTexture("item/skills/run75.png", "run2");
		res.loadTexture("item/skills/run100.png", "run3");
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
		res.loadTexture("character/cloak1b.png", "previewcloak1");
		res.loadTexture("character/cloak1a.png", "previewcloak2");
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
		//Tier 0
		res.loadTexture("monster/monster1.png", "monster-tier0-0");

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
			loadSwordData(color);
			loadShieldData(color);
			loadBodyArmorData(color);
			loadHelmetData(color);
		}
		loadRingData();
		loadPotionData();

		new Cloak.CloakFactory(res)
				.id(139)
				.name("cloak11")
				.costBuy(2500)
				.description("Revives the player with\n10 HP upon death for\none time only.")
				.build();

		loadRunUsables();
	}

	private void loadRunUsables() {
		new Run.RunFactory(res)
				.id(140)
				.name("run1")
				.costBuy(500)
				.description("50% Chance to Flee\n")
				.effect(new com.jnv.betrayal.dungeon.effects.Run(50))
				.build();

		new Run.RunFactory(res)
				.id(141)
				.name("run2")
				.costBuy(1000)
				.description("75% Chance to Flee")
				.effect(new com.jnv.betrayal.dungeon.effects.Run(75))
				.build();

		new Run.RunFactory(res)
				.id(142)
				.name("run3")
				.costBuy(2000)
				.description("100% Chance to Flee")
				.effect(new com.jnv.betrayal.dungeon.effects.Run(100))
				.build();
	}

	private void loadPotionData() {
		new Potion.PotionFactory(res)
				.id(127)
				.name("potion1")
				.costBuy(100)
				.description("Recover 25 health")
				.effect(new Heal(25))
				.build();

		new Potion.PotionFactory(res)
				.id(128)
				.name("potion2")
				.costBuy(500)
				.description("Recover 50 health")
				.effect(new Heal(50))
				.build();

		new Potion.PotionFactory(res)
				.id(129)
				.name("potion3")
				.costBuy(1250)
				.description("Recover 100 health")
				.effect(new Heal(100))
				.build();

		new Potion.PotionFactory(res)
				.id(130)
				.name("potion4")
				.costBuy(200)
				.description("Increase defense by 10\nLasts 3 turns")
				.effect(new DefenseUp(10, 4))
				.build();

		new Potion.PotionFactory(res)
				.id(131)
				.name("potion5")
				.costBuy(750)
				.description("Increase defense by 20\nLasts 3 turns")
				.effect(new DefenseUp(20, 4))
				.build();

		new Potion.PotionFactory(res)
				.id(132)
				.name("potion6")
				.costBuy(1500)
				.description("Increase defense by 30\nLasts 3 turns")
				.effect(new DefenseUp(30, 4))
				.build();

		new Potion.PotionFactory(res)
				.id(133)
				.name("potion7")
				.costBuy(200)
				.description("Increase attack by 10\nLasts 3 turns")
				.effect(new AttackUp(10, 4))
				.build();

		new Potion.PotionFactory(res)
				.id(134)
				.name("potion8")
				.costBuy(750)
				.description("Increase attack by 20\nLasts 3 turns")
				.effect(new AttackUp(20, 4))
				.build();

		new Potion.PotionFactory(res)
				.id(135)
				.name("potion9")
				.costBuy(1500)
				.description("Increase attack by 30\nLasts 3 turns")
				.effect(new AttackUp(30, 4))
				.build();

		new Potion.PotionFactory(res)
				.id(136)
				.name("potion10")
				.costBuy(500)
				.description("Increase attack by 10,\nIncrease def by 10\nLasts 3 turns")
				.effect(new AttackAndDefenseUp(10, 10, 4))
				.build();

		new Potion.PotionFactory(res)
				.id(137)
				.name("potion11")
				.costBuy(1250)
				.description("Increase attack by 20,\nIncrease def by 20\nLasts 3 turns")
				.effect(new AttackAndDefenseUp(20, 20, 4))
				.build();

		new Potion.PotionFactory(res)
				.id(138)
				.name("potion12")
				.costBuy(2500)
				.description("Increase attack by 30,\nIncrease def by 30\nLasts 3 turns")
				.effect(new AttackAndDefenseUp(30, 30, 4))
				.build();
	}

	private void loadRingData() {
		new Ring.RingFactory(res)    //Red Health Ring
				.id(115)
				.name("ring11")
				.costBuy(2000)
				.health(15)
				.attack(0)
				.defense(0)
				.description("+15 health")
				.build();

		new Ring.RingFactory(res)
				.id(116)
				.name("ring21")
				.costBuy(4000)
				.health(30)
				.attack(0)
				.defense(0)
				.description("+30 health")
				.build();

		new Ring.RingFactory(res)
				.id(117)
				.name("ring12")
				.costBuy(2000)
				.health(0)
				.attack(10)
				.defense(0)
				.description("+10 attack")
				.build();

		new Ring.RingFactory(res)
				.id(118)
				.name("ring22")
				.costBuy(4000)
				.health(0)
				.attack(20)
				.defense(0)
				.description("+20 attack")
				.build();

		new Ring.RingFactory(res)
				.id(119)
				.name("ring13")
				.costBuy(2000)
				.health(0)
				.attack(0)
				.defense(10)
				.description("+10 defense")
				.build();

		new Ring.RingFactory(res)
				.id(120)
				.name("ring23")
				.costBuy(2000)
				.health(0)
				.attack(0)
				.defense(20)
				.description("+20 defense")
				.build();

		new Ring.RingFactory(res)
				.id(121)
				.name("ring14")
				.costBuy(2250)
				.health(5)
				.attack(5)
				.defense(0)
				.description("+5 attack, \n+5 health")
				.build();

		new Ring.RingFactory(res)
				.id(122)
				.name("ring24")
				.costBuy(4500)
				.health(10)
				.attack(10)
				.defense(0)
				.description("+10 attack, \n+10 health")
				.build();

		new Ring.RingFactory(res)
				.id(123)
				.name("ring15")
				.costBuy(2250)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+5 defense, \n+5 health")
				.build();

		new Ring.RingFactory(res)
				.id(124)
				.name("ring25")
				.costBuy(4500)
				.health(10)
				.attack(0)
				.defense(10)
				.description("+10 defense, \n+10 health")
				.build();

		new Ring.RingFactory(res)
				.id(125)
				.name("ring16")
				.costBuy(2500)
				.health(0)
				.attack(0)
				.defense(0)
				.description("+10 attack, \n+10 defense, \n+10 health")
				.build();

		new Ring.RingFactory(res)
				.id(126)
				.name("ring26")
				.costBuy(5000)
				.health(20)
				.attack(20)
				.defense(20)
				.description("+20 attack, \n+20 defense, \n+20 health")
				.build();
	}

	private void loadHelmetData(int color) {
		new Helmet.HelmetFactory(res)
				.id(color + 90) // id - 91 to 96
				.name("headgear" + color + "1")
				.costBuy(200)
				.defense(1)
				.description("+1 Defense")
				.build();

		new Helmet.HelmetFactory(res)
				.id(color + 96) // id - 97 to 102
				.name("headgear" + color + "2")
				.costBuy(500)
				.defense(3)
				.description("+3 Defense")
				.build();

		new Helmet.HelmetFactory(res)
				.id(color + 102) // id - 103 to 108
				.name("headgear" + color + "3")
				.costBuy(1200)
				.defense(6)
				.description("+6 Defense")
				.build();

		new Helmet.HelmetFactory(res)
				.id(color + 108) // id - 109 to 114
				.name("headgear" + color + "4")
				.costBuy(2000)
				.defense(10)
				.description("+10 Defense")
				.build();

		new Helmet.HelmetFactory(res)
				.id(color + 114) // id - 115 to 120
				.name("headgear" + color + "5")
				.costBuy(3000)
				.defense(15)
				.description("+15 Defense")
				.build();
	}

	private void loadBodyArmorData(int color) {
		new BodyArmor.ArmorFactory(res)
				.id(color + 60) // id - 61 to 66
				.name("armor" + color + "1")
				.costBuy(200)
				.defense(2)
				.description("+2 Defense")
				.build();

		new BodyArmor.ArmorFactory(res)
				.id(color + 66) // id - 67 to 72
				.name("armor" + color + "2")
				.costBuy(600)
				.defense(6)
				.description("+6 Defense")
				.build();

		new BodyArmor.ArmorFactory(res)
				.id(color + 72) // id - 73 to 78
				.name("armor" + color + "3")
				.costBuy(1500)
				.defense(10)
				.description("+10 Defense")
				.build();

		new BodyArmor.ArmorFactory(res)
				.id(color + 78) // id - 79 to 84
				.name("armor" + color + "4")
				.costBuy(2400)
				.defense(14)
				.description("+14 Defense")
				.build();

		new BodyArmor.ArmorFactory(res)
				.id(color + 84) // id - 85 to 90
				.name("armor" + color + "5s")
				.costBuy(4000)
				.defense(18)
				.description("+18 Defense")
				.build();
	}

	private void loadShieldData(int color) {
		new Shield.ShieldFactory(res)
				.id(color + 30) // id - 31 to 36
				.name("shield" + color + "1")
				.costBuy(200)
				.defense(3)
				.description("+3 Defense")
				.build();

		new Shield.ShieldFactory(res)
				.id(color + 36) // id - 37 to 42
				.name("shield" + color + "2")
				.costBuy(1000)
				.defense(7)
				.description("+7 Defense")
				.build();


		new Shield.ShieldFactory(res)
				.id(color + 42) // id - 43 to 48
				.name("shield" + color + "3")
				.costBuy(2200)
				.defense(12)
				.description("+12 Defense")
				.build();
		new Shield.ShieldFactory(res)
				.id(color + 48) // id - 49 to 54
				.name("shield" + color + "4")
				.costBuy(3500)
				.defense(18)
				.description("+18 Defense")
				.build();

		new Shield.ShieldFactory(res)
				.id(color + 54) // id - 55 to 60
				.name("shield" + color + "5")
				.costBuy(5000)
				.defense(25)
				.description("+25 Defense")
				.build();
	}

	private void loadSwordData(int color) {
		new Weapon.WeaponFactory(res)
				.id(color) // id - 1 to 6
				.name("sword" + color + "1")
				.costBuy(200)
				.attack(3)
				.description("+3 Attack")
				.build();

		new Weapon.WeaponFactory(res)
				.id(color + 6) // id - 7 to 12
				.name("sword" + color + "2")
				.costBuy(1000)
				.attack(7)
				.description("+7 Attack")
				.build();

		new Weapon.WeaponFactory(res)
				.id(color + 12) // id - 3 to 18
				.name("sword" + color + "3")
				.costBuy(2200)
				.attack(12)
				.description("+12 Attack")
				.build();

		new Weapon.WeaponFactory(res)
				.id(color + 18) // id - 19 to 24
				.name("sword" + color + "4")
				.costBuy(3500)
				.attack(18)
				.description("+18 Attack")
				.build();

		new Weapon.WeaponFactory(res)
				.id(color + 24) // id - 25 to 30
				.name("sword" + color + "5")
				.costBuy(5000)
				.attack(25)
				.description("+25 Attack")
				.build();
	}


	private void loadMonsterData() {
		// Tier 0
//		new Monster.MonsterFactory()
//				.assetManager(res)
//				.id(0)
//				.nickname("Skeleton Kelly")
//				.textureName("monster-tier0-0")
//				.skillTextureName("monsterpowerdown")
//				.textureWidth(250).textureHeight(250).x(235).y(740)
//				.health(20).attack(19).defense(2)
//				.numTargets(1).effectCooldown(2)
//				.goldReward(600)
//				.effect(new AttackDown(2,2)).skillTextureName("monsterattackdown")
//				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(0)
				.nickname("Skeleton Kelly")
				.textureName("monster-tier0-0")
				.skillTextureName("monsterpowerdown")
				.textureWidth(250).textureHeight(250).x(235).y(740)
				.health(20).attack(10).defense(2)
				.numTargets(1)
				.effect(new Bomb(20,1)).skillTextureName("monsterbomb").effectCooldown(1)
				.goldReward(600)
				//.effect(new IncreasedAttack(100)).skillTextureName("monsterdeath").effectCooldown(1)
				.build();

		// Tier 1
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(1)
				.nickname("The Green Lantern")
				.textureName("monster-tier1-0").skillTextureName("monsterpowerup")
				.textureWidth(300).textureHeight(300).x(250).y(740)
				.health(58).attack(33).defense(8)
				.goldReward(600)
				.effect(new AttackAndDefenseUp(23, 7, 2))
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(2)
				.nickname("Muriel the Mummy")
				.textureName("monster-tier1-1")
				.textureWidth(300).textureHeight(350).x(210).y(740)
				.health(67).attack(46).defense(0)
				.goldReward(500)
				.effect(new SkipTurn())
				.numTargets(1).effectCooldown(2)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(3)
				.nickname("Fred the Knight")
				.textureName("monster-tier1-2")
				.health(60).attack(35).defense(8)
				.textureWidth(250).textureHeight(300).x(235).y(740)
				.goldReward(600)
				.numTargets(1)
				.effect(new TrueDamage(27)).skillTextureName("monsterdeath").effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(4)
				.nickname("Big Chest")
				.textureName("monster-tier1-3")
				.health(6).attack(33).defense(200)
				.textureWidth(450).textureHeight(400).x(135).y(740)
				// todo high reward
				.goldReward(800)
				.numTargets(1)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(5)
				.nickname("Big Joe")
				.textureName("monster-tier1-4").skillTextureName("monsterdeath")
				.health(60).attack(35).defense(6)
				.textureWidth(300).textureHeight(350).x(210).y(740)
				.goldReward(400)
				.effect(new TrueDamage(30)).effectCooldown(2)		//todo FIX THIX
				.numTargets(1)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(6)
				.nickname("Sir Vincent")
				.textureName("monster-tier1-5").skillTextureName("monsterblock")
				.health(61).attack(37).defense(7)
				.textureWidth(300).textureHeight(350).x(210).y(740)
				.goldReward(400)
				.effect(new DefenseUp(10, 3)).effectCooldown(3)
				.numTargets(1)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(7)
				.nickname("Peeping Fang")
				.textureName("monster-tier1-6")
				.health(58).attack(43).defense(7)
				.textureWidth(300).textureHeight(350).x(210).y(740)
				.goldReward(400)
				.effect(new AttackDown(25,2)).effectCooldown(3).skillTextureName("monsterattackdown")
				.numTargets(1)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(8)
				.nickname("T Squared")
				.textureName("monster-tier1-7").skillTextureName("monsterpowerup")
				.textureWidth(300).textureHeight(350).x(210).y(740)
				.health(57).attack(41).defense(9)
				.effect(new AttackAndDefenseUp(30, 10, 3)).effectCooldown(4)
				.goldReward(400)
				.numTargets(1)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(9)
				.nickname("Jeff the Killer")
				.textureName("monster-tier1-8").skillTextureName("monstermelee")
				.health(58).attack(42).defense(7)
				.textureWidth(300).textureHeight(350).x(210).y(740)
				.goldReward(400)
				.numTargets(1)
				.effect(new AttackUp(50, 2)).effectCooldown(4)
				.build();

		// Tier 1 Mob
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(10)
				.nickname("Skeleton Kelly")
				.textureName("monster-tier1-9")
				.textureWidth(300).textureHeight(350).x(50).y(720)
				.health(20).attack(34).defense(2)
				.goldReward(400)
				.numTargets(1)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(11)
				.nickname("Fred The Knight")
				.textureName("monster-tier1-19")
				.textureWidth(300).textureHeight(350).x(370).y(740)
				.health(54).attack(35).defense(8)
				.goldReward(400)
				.numTargets(1)
				.build();

		// Tier 2
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(12)
				.nickname("Ghost Rider")
				.textureName("monster-tier2-0").skillTextureName("monsterpowerdown")
				.health(69).attack(49).defense(15)
				.textureWidth(400).textureHeight(450).x(160).y(700)
				.goldReward(800)
				.numTargets(4)
				.effect(new AttackAndDefenseDown(20, 20, 2))
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(13)
				.nickname("Moose")
				.textureName("monster-tier2-1")
				.health(77).attack(82).defense(6)
				.textureWidth(350).textureHeight(450).x(185).y(700)
				.goldReward(1100)
				.numTargets(2).effectCooldown(3)
				.effect(new SkipTurn()).effectCooldown(2)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(14)
				.nickname("Samurai Joe")
				.textureName("monster-tier2-2")
				.health(67).attack(46).defense(11)
				.textureWidth(250).textureHeight(300).x(30).y(690)
				.goldReward(850)
				.numTargets(2).effectCooldown(1)
				.effect(new TrueDamage(30)).skillTextureName("monsterdeath")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(15)
				.nickname("David the Tiger")
				.textureName("monster-tier2-3").skillTextureName("monsterpoison")
				.health(65).attack(50).defense(12)
				.textureWidth(500).textureHeight(450).x(110).y(680)
				.goldReward(850)
				.numTargets(1).effectCooldown(3)
				.effect(new Poison(3)).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(16)
				.nickname("Ed Edd and Eddy")
				.textureName("monster-tier2-4").skillTextureName("monstermelee")
				.health(80).attack(30).defense(10)
				.textureWidth(650).textureHeight(550).x(35).y(690)
				.goldReward(800)
				.numTargets(3).effectCooldown(3)
				.effect(new AttackUp(50, 2)).effectCooldown(2)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(17)
				.nickname("Angry Susan")
				.textureName("monster-tier2-5")
				.health(70).attack(53).defense(13)
				.textureWidth(500).textureHeight(500).x(110).y(670)
				.goldReward(1000)
				.numTargets(1).effectCooldown(3)
				.effect(new DefenseDown(40, 2)).effectCooldown(4).skillTextureName("monstershieldbreak")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(18)
				.nickname("Purple Delight")
				.textureName("monster-tier2-6")
				.health(62).attack(58).defense(4)
				.textureWidth(500).textureHeight(500).x(110).y(640)
				.goldReward(900)
				.numTargets(4).effectCooldown(2)
				.effect(new Poison(2)).skillTextureName("monsterpoison")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(19)
				.nickname("Horny Trevor")
				.textureName("monster-tier2-7")
				.health(69).attack(69).defense(8)
				.textureWidth(400).textureHeight(450).x(160).y(650)
				.goldReward(700)
				.numTargets(1).effectCooldown(3)
				.effect(new Heal(69)).skillTextureName("monsterheal")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(20)
				.nickname("Dead Mary")
				.textureName("monster-tier2-8")
				.health(100).attack(47).defense(0)
				.textureWidth(400).textureHeight(500).x(160).y(670)
				.goldReward(900)
				.numTargets(1).effectCooldown(2)
				.effect(new Bomb(60,2)).skillTextureName("monsterbomb")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(21)
				.nickname("It's Your Girlfriend")
				.textureName("monster-tier2-9").skillTextureName("monsterpowerdown")
				.health(80).attack(40).defense(10)
				.textureWidth(400).textureHeight(500).x(160).y(670)
				.goldReward(900)
				.numTargets(2)
				.effect(new IncreasedAttack(69)).skillTextureName("monsterincreasedattack").effectCooldown(2)
				.build();

		// Tier 2 Mob
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(22)
				.nickname("SamuraiPoe")
				.textureName("monster-tier2-12")
				.health(45).attack(53).defense(8)
				.textureWidth(250).textureHeight(300).x(230).y(760)
				.goldReward(600)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(23)
				.nickname("Samurai Low")
				.textureName("monster-tier2-22")
				.health(55).attack(38).defense(13)
				.textureWidth(250).textureHeight(300).x(430).y(690)
				.goldReward(600)
				.numTargets(1).effectCooldown(3)
				.build();

		// Tier 3
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(24)
				.nickname("Pack o' Wolves")
				.textureName("monster-tier3-0")
				.health(120).attack(49).defense(16)
				.textureWidth(350).textureHeight(300).x(340).y(670)
				.goldReward(1700)
				.numTargets(3).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(25)
				.nickname("Hagrid")
				.textureName("monster-tier3-1")
				.health(150).attack(56).defense(23)
				.textureWidth(600).textureHeight(500).x(60).y(640)
				.goldReward(1800)
				.numTargets(2).effectCooldown(3)
				.effect(new Poison(3)).effectCooldown(3).skillTextureName("monsterpoison")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(26)
				.nickname("Tiki To")
				.textureName("monster-tier3-2")
				.health(89).attack(53).defense(17)
				.textureWidth(400).textureHeight(450).x(0).y(690)
				.goldReward(1600)
				.numTargets(1).effectCooldown(3)
				.effect(new DefenseDown(30,2)).skillTextureName("monstershieldbreak")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(27)
				.nickname("Ghost")
				.textureName("monster-tier3-3").skillTextureName("monsterbomb")
				.health(72).attack(56).defense(30)
				.textureWidth(400).textureHeight(450).x(160).y(690)
				.goldReward(1850)
				.numTargets(1).effectCooldown(3)
				.effect(new Bomb(70,3)).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(28)
				.nickname("Chad Livingston")
				.textureName("monster-tier3-4")
				.health(62).attack(46).defense(26)
				.textureWidth(550).textureHeight(500).x(85).y(740)
				.goldReward(1400)
				.effect(new DefenseUp(10,2))	.skillTextureName("monsterblock")
				.numTargets(1).effectCooldown(4)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(29)
				.nickname("Fire Dragon")
				.textureName("monster-tier3-5")
				.health(150).attack(56).defense(23)
				.textureWidth(500).textureHeight(550).x(110).y(665)
				.goldReward(2000)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(30)
				.nickname("Envious Kevin")
				.textureName("monster-tier3-6")
				.health(40).attack(28).defense(10)
				.textureWidth(500).textureHeight(500).x(110).y(670)
				.goldReward(1900)
				.numTargets(1).effectCooldown(3)
				.effect(new Heal(40)).skillTextureName("monsterheal")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(31)
				.nickname("Terrible Terry")
				.textureName("monster-tier3-7")
				.health(80).attack(41).defense(38)
				.textureWidth(500).textureHeight(500).x(110).y(670)
				.goldReward(1800)
				.numTargets(1).effectCooldown(2)
				.effect(new AttackUp(100,2)).skillTextureName("monstermelee")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(32)
				.nickname("No Body Greg")
				.textureName("monster-tier3-9")
				.health(82).attack(63).defense(30)
				.textureWidth(500).textureHeight(500).x(110).y(670)
				.goldReward(2100)
				.numTargets(1)
				.effect(new SkipTurn()).effectCooldown(2)
				.build();

		// Tier 3 Mob 1
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(33)
				.nickname("Ghost Rider")
				.textureName("monster-tier3-8").skillTextureName("monsterpowerdown")
				.health(50).attack(41).defense(13)
				.textureWidth(400).textureHeight(400).x(160).y(750)
				.goldReward(600)
				.numTargets(4).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(34)
				.nickname("Skeleton Kelly")
				.textureName("monster-tier3-18")
				.health(40).attack(51).defense(2)
				.textureWidth(250).textureHeight(300).x(20).y(660)
				.goldReward(600)
				.numTargets(1)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(35)
				.nickname("Fred the Knight")
				.textureName("monster-tier3-28")
				.health(40).attack(44).defense(5)
				.textureWidth(250).textureHeight(300).x(480).y(680)
				.goldReward(600)
				.numTargets(1)
				.effect(new IncreasedAttack(100)).skillTextureName("monsterincreasedattack").effectCooldown(3)
				.build();

		// Tier 3 Mob 2
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(36)
				.nickname("Pack o' Wolves")
				.textureName("monster-tier3-10")
				.health(40).attack(36).defense(7)
				.textureWidth(800).textureHeight(300).x(50).y(710)
				.goldReward(750)
				.numTargets(2)
				.build();



		// Tier 3 Mob 3
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(37)
				.nickname("Tiki Ta")
				.textureName("monster-tier3-12")
				.health(60).attack(31).defense(21)
				.textureWidth(400).textureHeight(450).x(360).y(690)
				.goldReward(900)
				.numTargets(1)
				.build();

		// Tier 4
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(38)
				.nickname("Magic Mike")
				.textureName("monster-tier4-0").skillTextureName("monsterbomb")
				.health(120).attack(31).defense(23)
				.textureWidth(500).textureHeight(475).x(110).y(675)
				.goldReward(3100)
				.numTargets(1).effectCooldown(2)
				.effect(new Bomb(100, 3 ))
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(39)
				.nickname("Ghost Rider")
				.textureName("monster-tier4-1")
				.health(110).attack(77).defense(19)
				.textureWidth(350).textureHeight(400).x(185).y(665)
				.goldReward(2800)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(40)
				.nickname("The Thing")
				.textureName("monster-tier4-2")
				.health(140).attack(20).defense(5)
				.textureWidth(500).textureHeight(475).x(110).y(665)
				.goldReward(3200)
				.effect(new AttackAndDefenseUp(10,5,15)).skillTextureName("monsterpowerup")
				.numTargets(1).effectCooldown(2)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(41)
				.nickname("Wizard of Narwhal")
				.textureName("monster-tier4-3")
				.health(100).attack(80).defense(17)
				.textureWidth(500).textureHeight(475).x(110).y(665)
				.goldReward(3300)
				.numTargets(1).effectCooldown(3)
				.effect(new AttackAndDefenseDown(25,25,3)).skillTextureName("monsterpowerdown")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(42)
				.nickname("Fluffy")
				.textureName("monster-tier4-4")
				.health(150).attack(81).defense(21)
				.textureWidth(500).textureHeight(475).x(110).y(665)
				.goldReward(3700)
				.numTargets(1).effectCooldown(3)
				.effectCooldown(2)
				.effect(new DefenseDown(50,10)).skillTextureName("monstershieldbreak")
				.build();

		// Tier 4 Mob 1
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(43)
				.nickname("Drake")
				.textureName("monster-tier4-5")
				.health(72).attack(49).defense(13)
				.textureWidth(300).textureHeight(350).x(0).y(655)
				.goldReward(1200)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(44)
				.nickname("Drako")
				.textureName("monster-tier4-15")
				.health(63).attack(45).defense(12)
				.textureWidth(350).textureHeight(400).x(185).y(765)
				.goldReward(1200)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(45)
				.nickname("Drakey")
				.textureName("monster-tier4-25")
				.health(57).attack(56).defense(6)
				.textureWidth(300).textureHeight(350).x(420).y(655)
				.goldReward(1200)
				.numTargets(1).effectCooldown(3)
				.build();

		// Tier 4 Mob 2
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(46)
				.nickname("Rock")
				.textureName("monster-tier4-6")
				.health(10).attack(47).defense(400)
				.textureWidth(425).textureHeight(475).x(40).y(675)
				.goldReward(1400)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(47)
				.nickname("Chad Livingston")
				.textureName("monster-tier4-16")
				.health(60).attack(32).defense(40)
				.textureWidth(225).textureHeight(250).x(400).y(740)
				.goldReward(1400)
				.numTargets(1).effectCooldown(3)
				.build();

		// Tier 4 Mob 3
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(48)
				.nickname("Leader")
				.textureName("monster-tier4-7")
				.health(55).attack(45).defense(15)
				.textureWidth(200).textureHeight(250).x(260).y(675)
				.goldReward(500)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(49)
				.nickname("Big Joe")
				.textureName("monster-tier4-17")
				.health(65).attack(55).defense(13)
				.textureWidth(200).textureHeight(250).x(0).y(725)
				.goldReward(500)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(50)
				.nickname("Sir Vincent")
				.textureName("monster-tier4-27")
				.health(55).attack(55).defense(13)
				.textureWidth(400).textureHeight(250).x(520).y(725)
				.goldReward(500)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(51)
				.nickname("Peeping Fang")
				.textureName("monster-tier4-37")
				.health(45).attack(55).defense(13)
				.textureWidth(100).textureHeight(250).x(120).y(815)
				.goldReward(500)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(52)
				.nickname("T Squared")
				.textureName("monster-tier4-47")
				.health(55).attack(55).defense(21)
				.textureWidth(200).textureHeight(250).x(400).y(815)
				.goldReward(500)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(53)
				.nickname("Jeff the Killer")
				.textureName("monster-tier4-57")
				.health(55).attack(45).defense(13)
				.textureWidth(200).textureHeight(250).x(260).y(870)
				.goldReward(500)
				.numTargets(1).effectCooldown(3)
				.build();

		// Tier 5
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(54)
				.nickname("Fattie Triple Chin")
				.textureName("monster-tier5-0")
				.health(200).attack(60).defense(50)
				.textureWidth(600).textureHeight(525).x(60).y(665)
				.goldReward(7500)
				.numTargets(1).effectCooldown(4)
				.effect(new Heal(20)).skillTextureName("monsterheal")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(55)
				.nickname("King of the Mountain, Lioke")
				.textureName("monster-tier5-1")
				.health(185).attack(78).defense(36)
				.textureWidth(450).textureHeight(550).x(135).y(665)
				.goldReward(7500)
				.numTargets(1).effectCooldown(3)
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(56)
				.nickname("Jacob Rivington III")
				.textureName("monster-tier5-2")
				.health(180).attack(72).defense(27)
				.textureWidth(620).textureHeight(500).x(50).y(640)
				.goldReward(7500)
				.numTargets(1).effectCooldown(4)
				.effect(new AttackUp(40,4)).skillTextureName("monstermelee")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(57)
				.nickname("Morenth Bringer of Death")
				.textureName("monster-tier5-3")
				.health(160).attack(10).defense(0)
				.textureWidth(500).textureHeight(500).x(110).y(665)
				.goldReward(7500)
				.numTargets(1).effectCooldown(2)
				.effect(new AttackAndDefenseUp(30,15,15)).skillTextureName("monsterpowerup")
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(58)
				.nickname("Lusloth Protector of Life")
				.textureName("monster-tier5-4")
				.health(200).attack(45).defense(18)
				.textureWidth(500).textureHeight(500).x(110).y(665)
				.goldReward(7500)
				.numTargets(1).effectCooldown(3)
				.effect(new Heal(100))
				.build();
		new Monster.MonsterFactory()
				.assetManager(res)
				.id(59)
				.nickname("Tough Tanuth")
				.textureName("monster-tier5-5")
				.health(170).attack(71).defense(23)
				.textureWidth(500).textureHeight(500).x(110).y(665)
				.goldReward(7500)
				.numTargets(1).effectCooldown(3)
				.effect(new IncreasedAttack(89)).skillTextureName("monsterincreasedattack").effectCooldown(3)
				.build();
	}
}
