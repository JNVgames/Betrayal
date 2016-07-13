package com.jnv.betrayal.lobby.inventory;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;
import com.jnv.betrayal.scene2d.ui.Label;

public class DungeonInventory extends Popup implements InventoryLoadable {

	private Field field;
	private Image lobbyButton;
	private Image[] inventorySpots, characterOutline;
	private Label[] charOutDescription;
	private ItemLoader itemLoader;
	private Card card;
	Label title;
	Image background;
	Character character;
	Betrayal game;

	public DungeonInventory(Betrayal game, Card card) {
		super(game);
		this.card = card;
		this.game = game;
		inventorySpots = new Image[20];
		characterOutline = new Image[8];
		charOutDescription = new Label[7];
		character = game.getCurrentCharacter();
		loadButtons();
		itemLoader = new ItemLoader(this);
		itemLoader.loadInventory();
	}

	private void loadButtons() {
		loadBackground();
		loadTitle();
		loadInventorySpots();
		loadReturnToLobbyButton();
		loadSortButton();
		loadHelp();
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(100, 400, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 500);
		popup.addActor(background);
	}

	private void loadTitle() {
		title = new Label("Inventory", FontManager.getFont40());
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 2);
		title.setY(Betrayal.HEIGHT - 200);
		popup.addActor(title);

	}

	private void loadHelp(){
		Label help = new Label( "Tap Item to use",FontManager.getFont40());
		help.setX((Betrayal.WIDTH - help.getPrefWidth())/2);
		help.setY(lobbyButton.getTop()+70);
		popup.addActor(help);
	}


	private void loadReturnToLobbyButton() {
		lobbyButton = new Image(res.getTexture("back-button"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - 312) / 2 , background.getY()+10, 312, 100);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadInventorySpots() {

		int padding = 10, itemSize = 92;
		float startingX = background.getX() + itemSize + padding, startingY = title.getY() - 30 - 92;

		for (int i = 0; i < 20; i++) {
			inventorySpots[i] = new Image(res.getTexture("shop-purchase-background"));
			inventorySpots[i].layout();

			if (i < 5) {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 1) + padding * i,
						startingY, itemSize, itemSize);
			} else if (i >= 5 && i < 10) {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 6) + padding * (i - 5),
						startingY - itemSize - padding, itemSize, itemSize);
			} else if (i >= 10 && i < 15) {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 11) + padding * (i - 10),
						startingY - (itemSize + padding) * 2, itemSize, itemSize);
			} else {
				inventorySpots[i].setBounds(startingX + itemSize * (i - 16) + padding * (i - 15),
						startingY - (itemSize + padding) * 3, itemSize, itemSize);
			}
			popup.addActor(inventorySpots[i]);
		}
	}

	private void loadSortButton() {
		Label sortButton = new Label("Sort", FontManager.getFont40());
		sortButton.setBounds(background.getX() + background.getWidth() - 30 - sortButton.getPrefWidth(),
				lobbyButton.getY() + lobbyButton.getHeight() + 842, sortButton.getPrefWidth(),
				sortButton.getPrefHeight());
		sortButton.addListener(new InputListener(sortButton) {
			@Override
			public void doAction() {
				character.inventory.sortItems();
				refresh();
			}
		});
		popup.addActor(sortButton);
	}

	@Override
	public Label getTitleActor() {
		return title;
	}

	public void refresh() {
		itemLoader.refresh();
	}

	@Override
	public Character getCharacter() {
		return character;
	}

	@Override
	public Image getBackground() {
		return background;
	}

	@Override
	public Group getPopup() {
		return popup;
	}

	@Override
	public Betrayal getGame() {
		return game;
	}

	public Card getCard() {
		return card;
	}
}
