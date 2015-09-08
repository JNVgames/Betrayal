package com.jnv.betrayal.popup;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.gameobjects.Character;
import com.jnv.betrayal.gameobjects.Item;
import com.jnv.betrayal.gameobjects.Weapon;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;

public class ShopPurchase extends Popup {

	private Image backButton, background, goldIcon, buyButton, leftArrow, rightArrow, item;
	private Label price, description;
	private Actor mask;
	private int currentSide;
	private Character character;
	private Item currentItem;

	public ShopPurchase(Betrayal game, Item item) {
		super(game);
		currentSide = 0;
		character = game.getPlayer().getCurrentCharacter();
		loadButtons();
	}

	private void loadButtons() {
		loadMask();
		loadBackground();
		loadContent();
		loadReturnToShopButton();
		loadBuyButton();
		loadGoldIcon();
		loadDescription();
		loadPreview();
		loadPrice();
		loadArrows();
	}

	private void loadMask() {
		mask = new Actor();
		mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		mask.addListener(new com.jnv.betrayal.inputprocessors.InputListener(mask) {
			@Override
			public void doAction() {
				removeShopPurchase();
			}
		});
		stage.addActor(mask);
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-purchase-background"));
		background.layout();
		background.setBounds(150, 200, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 400);
		stage.addActor(background);
	}

	private void loadGoldIcon() {
		goldIcon = new Image(res.getTexture("icon-gold"));
		goldIcon.layout();
		goldIcon.setBounds(410, 850, 40, 40);
		stage.addActor(goldIcon);
	}

	private void loadPrice() {
		price = new Label("xxxxx", FontManager.getFont(40));
		price.setHeight(50);
		price.setX(450);
		price.setY(850);
		stage.addActor(price);
	}

	private void loadDescription() {
		description = new Label("+5 Attack", FontManager.getFont(40));
		description.setHeight(50);
		description.setX(250);
		description.setY(900);
		stage.addActor(description);
	}

	private void loadBuyButton() {
		buyButton = new Image(res.getTexture("buy"));
		buyButton.layout();
		buyButton.setBounds(Betrayal.WIDTH / 2 + 50, 220, 100, 50);
		buyButton.addListener(new com.jnv.betrayal.inputprocessors.InputListener(buyButton) {
			@Override
			public void doAction() {
				new Confirmation(game, "Confirm purchase") {
					public void doSomething() {
						character.getInventory().addItem(new Weapon("sword11", res));
					}
				};
			}
		});
		stage.addActor(buyButton);
	}

	private void loadReturnToShopButton() {
		backButton = new Image(res.getTexture("back"));
		backButton.layout();
		backButton.setBounds(Betrayal.WIDTH / 2 - 150, 220, 100, 50);
		backButton.addListener(new com.jnv.betrayal.inputprocessors.InputListener(backButton) {
			@Override
			public void doAction() {
				removeShopPurchase();
			}
		});
		stage.addActor(backButton);
	}

	private void loadContent() {

	}

	private void loadPreview() {
		/*
		Actor field_preview = new Actor() {
            public void draw(Batch sb, float parentAlpha) {
                for (TextureRegion preview : character.getFullPreview()) {
                    sb.draw(preview, field_framePreview.getX(),
                            field_framePreview.getY(), field_framePreview.getWidth(),
                            field_framePreview.getHeight());
                }
            }
        };
        field_preview.setWidth(384);
        field_preview.setHeight(576);
        field_preview.setX(field_framePreview.getX());
        field_preview.setY(field_framePreview.getY());
        stage.addActor(field_preview);
        */
	}

	private void loadArrows() {
		leftArrow = new Image(res.getTexture("arrow-left"));
		leftArrow.layout();
		leftArrow.setBounds(Betrayal.WIDTH / 2 - 150, 300, 100, 50);
		leftArrow.addListener(new com.jnv.betrayal.inputprocessors.InputListener(leftArrow) {
			@Override
			public void doAction() {
				currentSide--;
				if (currentSide < 0) {
					currentSide = 3;
				}
			}
		});
		stage.addActor(leftArrow);

		rightArrow = new Image(res.getTexture("arrow-right"));
		rightArrow.layout();
		rightArrow.setBounds(Betrayal.WIDTH / 2 + 50, 300, 100, 50);
		rightArrow.addListener(new com.jnv.betrayal.inputprocessors.InputListener(rightArrow) {
			@Override
			public void doAction() {
				currentSide = (currentSide + 1) % 4;
			}
		});
		stage.addActor(rightArrow);
	}

	private void removeShopPurchase() {
		rightArrow.remove();
		leftArrow.remove();
		mask.remove();
		background.remove();
		backButton.remove();
		buyButton.remove();
		goldIcon.remove();
		price.remove();
		description.remove();

	}
}
