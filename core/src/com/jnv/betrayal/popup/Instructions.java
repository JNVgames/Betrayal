/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;


public class Instructions extends Popup {

	private Image image_rightArrow, image_leftArrow, exitButton, background;
	private Label title, content0, content1, content2, content3, content4;
	private Actor mask;
	private int currentContent, totalContent;

	public Instructions(Betrayal game) {
		super(game);
		currentContent = 0;
		totalContent = 5;
		loadButtons();
	}

	private void loadButtons() {
		loadMask();
		loadBackground();
		loadLeftArrow();
		loadRightArrow();
		loadContent();
		loadXButton();
		loadTitle();
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
				removeInstructions();
			}
		});
		stage.addActor(mask);
	}

	private void loadTitle() {
		title = new Label("Instructions", Betrayal.getFont(40));
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 200);
		stage.addActor(title);
	}

	private void loadXButton() {
		exitButton = new Image(res.getTexture("x"));
		exitButton.layout();
		exitButton.setBounds(520, 1080, 100, 100);
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				removeInstructions();
			}
		});
		stage.addActor(exitButton);
	}

	private void loadBackground() {
		background = new Image(res.getTexture("instructions-background"));
		background.layout();
		background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		stage.addActor(background);
	}

	private void loadLeftArrow() {
		TextureRegion leftArrow = new TextureRegion(res.getTexture("arrow-right"));
		leftArrow.flip(true, false);
		image_leftArrow = new Image(leftArrow);


		image_leftArrow.layout();
		image_leftArrow.setBounds(210, 150, 125, 62);
		image_leftArrow.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				removeCurrentContent();
				currentContent--;
				if (currentContent == -1) {
					currentContent = totalContent - 1;
				}
				loadContent();
			}
		});
		stage.addActor(image_leftArrow);
	}

	private void loadRightArrow() {
		image_rightArrow = new Image(res.getTexture("arrow-right"));
		image_rightArrow.layout();
		image_rightArrow.setBounds(385, 150, 125, 62);
		image_rightArrow.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				removeCurrentContent();
				currentContent++;
				currentContent = currentContent % totalContent;
				loadContent();
			}
		});
		stage.addActor(image_rightArrow);
	}

	private void setContent0() {
		content0 = new Label(" The goal of this turn" +
				"\n based game is to clear " +
				"\n 50 dungeon floors. ", Betrayal.getFont(40));
		content0.setX(100);
		content0.setY(Betrayal.HEIGHT - content0.getHeight() - 250);
		stage.addActor(content0);
	}

	private void setContent1() {
		content1 = new Label(" Party System:" +
				"\n create teams of size 2-4 " +
				"\n and challenge a dungeon" +
				"\n floor. Be careful the " +
				"\n monsters aren't the only " +
				"\n things to worry about", Betrayal.getFont(40));
		content1.setX(100);
		content1.setY(Betrayal.HEIGHT - content1.getHeight() - 250);
		stage.addActor(content1);
	}

	private void setContent2() {
		content2 = new Label(" Dungeons:" +
				"\n hard as ballz", Betrayal.getFont(40));
		content2.setX(100);
		content2.setY(Betrayal.HEIGHT - content2.getHeight() - 250);
		stage.addActor(content2);
	}

	private void setContent3() {
		content3 = new Label(" Game Over: " +
				"\n Don't Die. You'll have " +
				"\n to start a new game. " +
				"\n If you kill a person " +
				"\n you get to take two " +
				"\n of their items. " +
				"\n *note* all their gold counts" +
				"\n as one item.", Betrayal.getFont(40));
		content3.setX(100);
		content3.setY(Betrayal.HEIGHT - content3.getHeight() - 250);
		stage.addActor(content3);
	}

	private void setContent4() {
		content4 = new Label(" Shop:" +
				"\n Spend all your money!", Betrayal.getFont(40));
		content4.setX(100);
		content4.setY(Betrayal.HEIGHT - content4.getHeight() - 250);
		stage.addActor(content4);
	}

	private void removeCurrentContent() {
		switch (currentContent) {
			case 0:
				content0.remove();
				break;
			case 1:
				content1.remove();
				break;
			case 2:
				content2.remove();
				break;
			case 3:
				content3.remove();
				break;
			case 4:
				content4.remove();
				break;
			default:
				Gdx.app.log("content", "should not happen");
				break;
		}
	}

	private void loadContent() {
		switch (currentContent) {
			case 0:
				setContent0();
				break;
			case 1:
				setContent1();
				break;
			case 2:
				setContent2();
				break;
			case 3:
				setContent3();
				break;
			case 4:
				setContent4();
				break;
			default:
				Gdx.app.log("content", "should not happen");
				break;
		}
	}

	private void removeInstructions() {
		mask.remove();
		removeCurrentContent();
		image_leftArrow.remove();
		image_rightArrow.remove();
		title.remove();
		background.remove();
		exitButton.remove();
	}
}
