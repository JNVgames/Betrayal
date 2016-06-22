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
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.InputListener;

public class ShopPurchasePopup extends Popup {

	private Image backButton, background, goldIcon, buyButton, leftArrow, rightArrow;
	private Label description;
	private int currentSide;
	private Character character;
	private Item item;
	private Preview preview;

	public ShopPurchasePopup(Betrayal game, Item item) {
		super(game);
		character = game.getPlayer().getCurrentCharacter();
		this.item = item;
		currentSide = 0;
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
		background = new Image(res.getTexture("shop-purchase-background"));
		background.layout();
		background.setBounds(150, 200, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 400);
		popup.addActor(background);
	}

	private void loadGoldIcon() {
		goldIcon = new Image(res.getTexture("icon-gold"));
		goldIcon.layout();
		goldIcon.setBounds(410, 850, 40, 40);
		popup.addActor(goldIcon);
	}

	private void loadPrice(){
		Label price = new Label("Price: "+ Integer.toString(item.getBuyCost()), FontManager.getFont(40));
		price.setHeight(50);
		price.setX(365);
		price.setY(900);
		popup.addActor(price);
	}
	private void loadYourMoney() {
		Label userMoney = new Label(Integer.toString(character.inventory.getGold()), FontManager.getFont(40));
		userMoney.setHeight(50);
		userMoney.setX(450);
		userMoney.setY(850);
		popup.addActor(userMoney);
	}

	private void loadDescription() {
		description = new Label("", FontManager.getFont(40));
		description.setHeight(50);
		description.setX(250);
		description.setY(900);
		popup.addActor(description);
	}

	private void loadBuyButton() {
		buyButton = new Image(res.getTexture("buy"));
		buyButton.layout();
		buyButton.setBounds(Betrayal.WIDTH / 2 + 50, 220, 100, 50);
		buyButton.addListener(new InputListener(buyButton) {
			@Override
			public void doAction() {
				new Confirmation(game, "Confirm purchase") {
					@Override
					public void doAction() {
						character.inventory.addItem(item);
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
		backButton = new Image(res.getTexture("back"));
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
