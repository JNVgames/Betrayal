package com.jnv.betrayal.lobby.shop;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class ShopRingPurchase extends Popup {

	private Image backButton, background, goldIcon, buyButton;
	private Label price, description;

	public ShopRingPurchase(Betrayal game) {
		super(game);
		loadButtons();
	}

	private void loadButtons() {
		loadBackground();
		loadDescription();
		loadReturnToShopButton();
		loadBuyButton();
		loadGoldIcon();
		loadPrice();
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-purchase-background"));
		background.layout();
		background.setBounds(150, 400, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 800);
		popup.addActor(background);
	}

	private void loadGoldIcon() {
		goldIcon = new Image(res.getTexture("icon-gold"));
		goldIcon.layout();
		goldIcon.setBounds(410, 500, 40, 40);
		popup.addActor(goldIcon);
	}

	private void loadPrice() {
		price = new Label("$$$$$", FontManager.getFont(40));
		price.setHeight(50);
		price.setX(450);
		price.setY(500);
		popup.addActor(price);
	}

	private void loadDescription() {
		description = new Label("+5 Attack", FontManager.getFont(40));
		description.setHeight(50);
		description.setX(300);
		description.setY(700);
		popup.addActor(description);
	}

	private void loadBuyButton() {
		buyButton = new Image(res.getTexture("buy"));
		buyButton.layout();
		buyButton.setBounds(Betrayal.WIDTH / 2 + 50, 420, 100, 50);
		buyButton.addListener(new InputListener(buyButton) {
			@Override
			public void doAction() {
				new com.jnv.betrayal.popup.Confirmation(game, "Buy");
			}
		});
		popup.addActor(buyButton);
	}

	private void loadReturnToShopButton() {
		backButton = new Image(res.getTexture("back"));
		backButton.layout();
		backButton.setBounds(Betrayal.WIDTH / 2 - 150, 420, 100, 50);
		backButton.addListener(new InputListener(backButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(backButton);
	}
}
