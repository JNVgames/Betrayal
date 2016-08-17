/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.resources.WhiteBoxOutline;
import com.jnv.betrayal.scene2d.Group;

import java.util.ArrayList;
import java.util.List;


public class OpeningInstructions extends UncloseablePopup{

	private Image rightArrow, leftArrow, exitButton, background, content;
	private Label title, pageNum;
	private List<Texture> textures;
	private Label.LabelStyle font40;
	private int currentContent;
	private boolean happenOnce = false;

	public OpeningInstructions(Betrayal game) {
		super(game);
		textures = new ArrayList<Texture>();

		currentContent = 0;
		font40 = FontManager.getFont40();
		loadTextures();
		loadButtons();
		loadXButton();
		exitButton.setVisible(false);
		new OKPopup(game, "Please Read Through\n The Instructions");

	}


	private void loadButtons() {
		loadBackground();
		loadContent();
		loadLeftArrow();
		loadRightArrow();
		setPageNumber();
		loadTitle();
	}

	private Texture getNewTexture(String s){
		Texture texture = res.getTexture(s);
		return texture;
	}

	private void nextContent(){
		currentContent++;
		if (currentContent==textures.size()) currentContent--;
		content.setDrawable(new TextureRegionDrawable(new TextureRegion(textures.get(currentContent))));
		if(currentContent == textures.size()-1 && !happenOnce){
			happenOnce = true;
			new OKPopup(game, "Completed Instructions!\nTap X to exit"){
				@Override
				public void onConfirm() {
					exitButton.setVisible(true);
				}
			};
		}
	}
	private void previousContent(){
		currentContent--;
		if(currentContent<0) currentContent++;
		System.out.println("currentContent = " + currentContent);
		content.setDrawable(new TextureRegionDrawable(new TextureRegion(textures.get(currentContent))));
	}

	private void loadTextures(){
		textures.add(getNewTexture("instruction"));
		textures.add(getNewTexture("gameObjective"));
//		textures.add(getNewTexture("mainMenuNewGame"));
// 		textures.add(getNewTexture("mainMenuLoadGame"));
//		textures.add(getNewTexture("mainMenuHallOfFame"));
//		textures.add(getNewTexture("characterCreation"));
//		textures.add(getNewTexture("createCharacterName"));
//		textures.add(getNewTexture("createCharacterCustomize"));
//		textures.add(getNewTexture("loadGamePage"));
//		textures.add(getNewTexture("loadGameSelect"));
//		textures.add(getNewTexture("lobby"));
//		textures.add(getNewTexture("lobbyShop"));
//		textures.add(getNewTexture("shop"));
//		textures.add(getNewTexture("shopItemTap"));
//		textures.add(getNewTexture("shopTab"));
//		textures.add(getNewTexture("lobbyInventory"));
//		textures.add(getNewTexture("inventory"));
//		textures.add(getNewTexture("inventoryEquip"));
//		textures.add(getNewTexture("inventoryEject"));
//		textures.add(getNewTexture("lobbyStats"));
//		textures.add(getNewTexture("stats"));
//		textures.add(getNewTexture("statsAdjust"));
//		textures.add(getNewTexture("statsApply"));
//		textures.add(getNewTexture("lobbyRoom"));
//		textures.add(getNewTexture("onlineCreateRoom"));
//		textures.add(getNewTexture("createRoomPassword"));
//		textures.add(getNewTexture("onlineRoomJoin"));
//		textures.add(getNewTexture("joinRoomPassword"));
//		textures.add(getNewTexture("onlineLeaveRoom"));
//		textures.add(getNewTexture("lobbyOption"));
//		textures.add(getNewTexture("lobbyCheckTeammateStats"));
//		textures.add(getNewTexture("lobbyReady"));
//		textures.add(getNewTexture("lobbyEnterDungeon"));
//		textures.add(getNewTexture("dungeon"));
//		textures.add(getNewTexture("dungeonAttack"));
//		textures.add(getNewTexture("dungeonDefend"));
//		textures.add(getNewTexture("dungeonFlee"));
//		textures.add(getNewTexture("dungeonItems"));
//		textures.add(getNewTexture("dungeonTapForStats"));
//		textures.add(getNewTexture("dungeonKillMonster"));
//		textures.add(getNewTexture("dungeonMonsterAttackExplanation"));
//		textures.add(getNewTexture("dungeonMonsterSkill"));
//		textures.add(getNewTexture("dungeonEventLog"));
//		textures.add(getNewTexture("dungeonMonsterHealthScale"));
//		textures.add(getNewTexture("dungeonRewardSplit"));
//		textures.add(getNewTexture("dungeonDeathWarning"));

	}

	private void loadTitle() {
		title = new Label("Instructions", font40);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(background.getTop()-title.getPrefHeight()-10);
		popup.addActor(title);

		//add white line under
		com.jnv.betrayal.scene2d.ui.Image whiteLine = new com.jnv.betrayal.scene2d.ui.Image(res.getTexture("white-pixel"));
		whiteLine.setWidth(title.getWidth()+20);
		whiteLine.setHeight(2);
		whiteLine.setX(title.getX()-10);
		whiteLine.setY(title.getY() - whiteLine.getHeight());
		popup.addActor(whiteLine);
	}

	private void loadXButton() {
		exitButton = new Image(res.getTexture("x"));
		exitButton.setWidth(100);
		exitButton.setHeight(100);
		exitButton.setX(background.getX() + background.getWidth() - exitButton.getWidth() - 10);
		exitButton.setY(background.getTop()-exitButton.getHeight() - 10);
		exitButton.addListener(new com.jnv.betrayal.scene2d.InputListener(exitButton) {
			@Override
			public void doAction() {
				game.savedDataHandler.save();
				remove();
			}
		});
		popup.addActor(exitButton);
	}

	private void loadBackground() {
		background = new Image(res.getTexture("confirmation-background"));
		background.layout();
		background.setBounds(100, 50, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 100);
		popup.addActor(background);
	}

	private void loadContent(){
		content = new Image(new TextureRegion(textures.get(0)));
		content.setX(100);
		content.setY(100);
		content.setWidth(520);
		content.setHeight(1080);
		popup.addActor(content);
	}

	private void loadLeftArrow() {
		TextureRegion leftArrow = new TextureRegion(res.getTexture("arrow-right"));
		leftArrow.flip(true, false);
		this.leftArrow = new Image(leftArrow);
		this.leftArrow.setWidth(125);
		this.leftArrow.setHeight(62);
		this.leftArrow.setX(175);
		this.leftArrow.setY(background.getY()+10);
		this.leftArrow.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				previousContent();
				updatePageNumber();
			}
		});
		popup.addActor(this.leftArrow);

		Group group = new Group();
		popup.addActor(group);
		new WhiteBoxOutline(game,group,
				this.leftArrow.getWidth()+6,
				this.leftArrow.getHeight()+6,
				3,
				this.leftArrow.getX()-3,
				this.leftArrow.getY()-3);
	}

	private void loadRightArrow() {
		rightArrow = new Image(res.getTexture("arrow-right"));
		rightArrow.setWidth(125);
		rightArrow.setHeight(62);
		rightArrow.setX(425);
		rightArrow.setY(background.getY()+10);
		rightArrow.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				nextContent();
				updatePageNumber();
			}
		});
		popup.addActor(rightArrow);
		Group group = new Group();
		popup.addActor(group);
		new WhiteBoxOutline(game,group,
				rightArrow.getWidth()+16,
				rightArrow.getHeight()+6,
				3,
				rightArrow.getX()-8,
				rightArrow.getY()-3);

	}


	private void setPageNumber(){
		pageNum = new Label("1/"+textures.size(), FontManager.getFont40());
		pageNum.setX(Betrayal.WIDTH/2 - pageNum.getPrefWidth()/2);
		pageNum.setY(rightArrow.getY() + (rightArrow.getHeight()/2 - pageNum.getPrefHeight()/2));
		popup.addActor(pageNum);
	}

	private void updatePageNumber(){
		int page = currentContent + 1;
		pageNum.setText( page + "/" + textures.size() );
		pageNum.setX(Betrayal.WIDTH/2 - pageNum.getPrefWidth()/2);
	}


}
