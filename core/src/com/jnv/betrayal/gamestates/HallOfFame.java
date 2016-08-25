package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.utils.Stat;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class HallOfFame extends GameState {

	private ScrollPane scrollPane;
	private VerticalGroup verticalGroup;

	public HallOfFame(GameStateManager gsm) {
		super(gsm);
		res = gsm.game.res;

		loadBackground();

		verticalGroup = new VerticalGroup();
		verticalGroup.layout();
		verticalGroup.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT - 150);
		verticalGroup.align(Align.left);
		scrollPane = new ScrollPane(verticalGroup);
		scrollPane.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT - 150);
		scrollPane.layout();
		scrollPane.setScrollingDisabled(true, false);
		scrollPane.setOverscroll(false, false);
		stage.addActor(scrollPane);

		loadXButton();
		loadFools();
	}

	private void loadFools() {
		List<Character> allFools = new ArrayList<Character>(game.fools);
		Collections.sort(allFools, new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				return o2.stats.getFloor() - o1.stats.getFloor();
			}
		});
		for (Character c : allFools) {
			final Character character = c;
			Group group = new Group();
			group.setBounds(0, 0, Betrayal.WIDTH - 10, 202);

			Image border;
			int tier = (int) Math.ceil(c.stats.getFloor() /5);
			switch(tier){
				case 0:
					border = new Image(res.getTexture("cpbWhite"));
					break;
				case 1:
					border = new Image(res.getTexture("cpbSilver"));
					break;
				case 2:
					border = new Image(res.getTexture("cpbBronze"));
					break;
				case 3:
					border = new Image(res.getTexture("cpbGold"));
					break;
				case 4:
					border = new Image(res.getTexture("cpbLegendLegend"));
					break;
				default:
					border = new Image(res.getTexture("cpbLegend"));
					break;

			}
			border.setX(0);
			border.setY(0);
			group.addActor(border);

			// Preview
			final int previewX = 5, previewWidth = 32 * 4;
			group.addActor(new Actor() {
				@Override
				public void draw(Batch batch, float parentAlpha) {
					character.preview.drawPreview(batch, 0, previewX, 5, previewWidth, 48 * 4);
				}
			});

			Label classPreview = new Label(character.job.toString(), FontManager.getFont50());
			classPreview.setX(previewX + previewWidth + 30);
			classPreview.setY(group.getHeight() - 10 - classPreview.getPrefHeight());
			classPreview.setColor(Color.WHITE);
			group.addActor(classPreview);

			Label name = new Label(character.getName(), FontManager.getFont70());
			name.setX(classPreview.getX());
			name.setY((classPreview.getY() - name.getPrefHeight()) / 2 + 5);
			group.addActor(name);

			Label floorLabel = new Label("FLOOR", FontManager.getFont40());
			floorLabel.setX(Betrayal.WIDTH - 20 - floorLabel.getPrefWidth());
			floorLabel.setY(group.getHeight() - floorLabel.getPrefHeight() - 20);
			floorLabel.setColor(Color.WHITE);
			group.addActor(floorLabel);

			Label floorNum =
					new Label(Integer.toString(character.stats.getStat(Stat.FLOOR)),
							FontManager.getFont100());
			floorNum.setBounds(floorLabel.getX(), 10,
					floorLabel.getWidth(), floorLabel.getY() - 10);
			floorNum.setColor(Color.LIGHT_GRAY);
			floorNum.setAlignment(Align.center);
			group.addActor(floorNum);

			verticalGroup.addActor(group);

			border.addListener(new InputListener(border) {
				@Override
				public void doAction() {
					new OKPopup(Betrayal.WIDTH - 300, Betrayal.HEIGHT - 850, game,
							character.getName()+
									"\nHealth: "+ character.stats.getTotalHealth()  +
							"\nAttack: " + character.stats.getTotalAttack()+
							"\nDefense: " + character.stats.getTotalDefense()+
									"\nNet Worth: " + character.inventory.getNetWorth()
							);

				}
			});
		}
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("hall-of-fame-background"));
		background.setX(0);
		background.setY(0);
		background.setWidth(Betrayal.WIDTH);
		background.setHeight(Betrayal.HEIGHT);
		stage.addActor(background);
	}

	private void loadXButton() {
		Image exitButton = new Image(res.getTexture("x"));
		exitButton.layout();
		exitButton.setBounds(620, 1180, 100, 100);
		exitButton.addListener(new InputListener(exitButton) {
			@Override
			public void doAction() {
				gsm.setState(GameStateManager.State.MENU);
			}
		});
		stage.addActor(exitButton);
	}

	public void update(float dt) {
		stage.act(dt);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	public void dispose() {
	}
}
