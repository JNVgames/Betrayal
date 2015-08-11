/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.entities.*;
import com.jnv.betrayal.entities.Character;
import com.jnv.betrayal.main.Betrayal;

public class Inventory {

    private Stage stage;
    private Image lobbyButton, background;
    private Label.LabelStyle labelStyle;
    private Label title;
    private Actor mask;

    // VINCENTS VARRIES
    private Group inventory;
    private Label button_sort;

    public Inventory(Betrayal game) {
        stage = game.getStage();
        loadFont();
        loadButtons();
    }

    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle(40);
    }

    private void loadButtons() {
        loadMask();
        loadBackground();
        loadTitle();
        loadContent();
        loadReturnToLobbyButton();

        // VINCENTS
        loadInventory();
        loadSortButton();
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
                removeInventory();
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
        title = new Label("Inventory", labelStyle);
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
                removeInventory();
            }
        });
        stage.addActor(lobbyButton);
    }

    private void loadContent() {

    }

    private void removeInventory() {
        mask.remove();
        title.remove();
        background.remove();
        lobbyButton.remove();

        // VINCENTS
        inventory.remove();
        button_sort.remove();
    }

    // VINCENTS STUFF
    private void loadInventory() {
        inventory = new Group();
        Item[][] items = Character.currentCharacter.getInventoryClass().getItems(6, 5);
        Actor[][] itemsDisplay = new Actor[items.length][items[0].length];

        float padding = 10;
        float sideLength = (background.getWidth() - (items[0].length + 1) * padding) / items[0].length;
        float startingX = background.getX(), startingY = title.getY() - 30;

        for (int row = 0; row < itemsDisplay.length; row++) {
            for (int col = 0; col < itemsDisplay[row].length; col++) {
                if (items[row][col] != null) {
                    itemsDisplay[row][col] = loadInventoryBox(
                            startingX + (col + 1) * padding + col * sideLength,
                            startingY - row * padding - row * sideLength,
                            sideLength, items[row][col]
                    );
                    inventory.addActor(itemsDisplay[row][col]);
                }
            }
        }
        stage.addActor(inventory);
    }
    private void loadSortButton() {
        button_sort = new Label("Sort", Betrayal.getHurtmoldFontLabelStyle(40));
        button_sort.setBounds(background.getX() + background.getWidth() - 30 - button_sort.getPrefWidth(),
                lobbyButton.getY() + lobbyButton.getHeight() + 30, button_sort.getPrefWidth(),
                button_sort.getPrefHeight());
        button_sort.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Character.currentCharacter.getInventoryClass().sortItems();
                inventory.remove();
                loadInventory();
            }
        });
        stage.addActor(button_sort);
    }
    private Actor loadInventoryBox(float x, float topY, final float sideLength, final Item item) {
        final TextureRegion itemImage = item.getItemImage();
        Actor invBox = new Actor() {
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(itemImage, this.getX(), this.getY(),
                        sideLength, sideLength);
            }
        };
        invBox.setBounds(x, topY - sideLength, sideLength, sideLength);
        return invBox;
    }
}
