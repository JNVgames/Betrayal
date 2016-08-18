package com.jnv.betrayal.lobby.shop;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.character.Equips;
import com.jnv.betrayal.character.Preview;
import com.jnv.betrayal.gameobjects.Equip;
import com.jnv.betrayal.gameobjects.Item;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.InputListener;

public class ShopPurchasePopup extends Popup {

	private Image leftArrow;
	private Image rightArrow;
	private Character character;
	private Item item;
	private Preview preview;
	private Shop shop;

	public ShopPurchasePopup(Betrayal game, Item item, Shop shop) {
		super(game);
		character = game.getCurrentCharacter();
		this.item = item;
		this.shop = shop;
		loadButtons();
	}

	private void loadButtons() {
		loadBackground();
		loadReturnToShopButton();
		loadBuyButton();
		loadGoldIcon();
		loadPrice();
		loadDescription();
		loadPreview();
		loadYourMoney();
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("shop-purchase-background"));
		background.layout();
		background.setBounds(150, 200, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 400);
		popup.addActor(background);
	}

	private void loadGoldIcon() {
		Image goldIcon = new Image(res.getTexture("icon-gold"));
		goldIcon.layout();
		goldIcon.setBounds(410, 800, 40, 40);
		popup.addActor(goldIcon);
	}

	private void loadPrice() {
		Label price = new Label("Price: " + Integer.toString(item.getBuyCost()), FontManager.getFont40());
		price.setHeight(50);
		price.setX(365);
		price.setY(850);
		popup.addActor(price);
	}

	private void loadYourMoney() {
		Label userMoney = new Label(Integer.toString(character.inventory.getGold()), FontManager.getFont40());
		userMoney.setHeight(50);
		userMoney.setX(450);
		userMoney.setY(800);
		popup.addActor(userMoney);
	}

	private void loadDescription() {
		Label description = new Label(item.getDescription(), FontManager.getFont40());
		description.setX((Betrayal.WIDTH - description.getWidth()) / 2);
		description.setY(1048 - description.getPrefHeight() + 10);
		popup.addActor(description);
	}

	private void loadBuyButton() {
		Image buyButton = new Image(res.getTexture("buy"));
		buyButton.layout();
		buyButton.setBounds(Betrayal.WIDTH / 2 + 50, 220, 100, 50);
		buyButton.addListener(new InputListener(buyButton) {
			@Override
			public void doAction() {
				new Confirmation(game, "Confirm purchase") {
					@Override
					public void doAction() {
						switch (character.inventory.buyItem(item)) {
							case 0:
								game.savedDataHandler.save();
								new OKPopup(game, "Item Bought");

								shop.setGoldLabelText(Integer.toString(game.getCurrentCharacter().inventory.getGold()));
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

					@Override
					public void remove() {
						super.remove();
						removeThisPopup();
					}
				};
			}
		});
		popup.addActor(buyButton);
	}

	private void loadReturnToShopButton() {
		Image backButton = new Image(res.getTexture("back"));
		backButton.layout();
		backButton.setBounds(Betrayal.WIDTH / 2 - 150, 220, 100, 50);
		backButton.addListener(new InputListener(backButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(backButton);
	}

	private void loadPreview() {
		loadPreviewArrows();

		preview = character.preview;
		preview.setRotation(0);
		if (item instanceof Equip) {
			// Create copy of character's preview with the extra weapon
			Equips equips = new Equips(character.equips, res);
			equips.equip((Equip) item);
			preview = new Preview(character.preview, equips, res);
		}

		final float x = leftArrow.getX();
		final float y = leftArrow.getTop() + 20;
		final float width = rightArrow.getRight() - leftArrow.getX();
		final float height = width * 18 / 12;

		Actor previewImage = new Actor() {
			@Override
			public void draw(Batch sb, float pa) {
				preview.drawPreview(game.getBatch(), x, y, width, height);
			}
		};
		previewImage.setWidth(width);
		previewImage.setHeight(height);
		previewImage.setX(x);
		previewImage.setY(y);
		popup.addActor(previewImage);
	}

	private void loadPreviewArrows() {
		leftArrow = new Image(res.getTexture("arrow-left"));
		leftArrow.layout();
		leftArrow.setBounds(Betrayal.WIDTH / 2 - 150, 300, 100, 50);
		leftArrow.addListener(new InputListener(leftArrow) {
			@Override
			public void doAction() {
				preview.rotateLeft();
			}
		});
		popup.addActor(leftArrow);

		rightArrow = new Image(res.getTexture("arrow-right"));
		rightArrow.layout();
		rightArrow.setBounds(Betrayal.WIDTH / 2 + 50, 300, 100, 50);
		rightArrow.addListener(new InputListener(rightArrow) {
			@Override
			public void doAction() {
				preview.rotateRight();
			}
		});
		popup.addActor(rightArrow);
	}

	private void removeThisPopup() {
		remove();
	}
}
