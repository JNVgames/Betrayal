package com.jnv.betrayal.lobby.social;

import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;
import com.jnv.betrayal.scene2d.ui.Label;
import com.jnv.betrayal.scene2d.ui.ScrollPane;

import java.util.List;

public class FriendsList extends Popup {

	public final Image partyBackground, friendsBackground, lobbyButton;
	public final Label partyTitle, friendsTitle;

	public FriendsList(Betrayal game) {
		super(game);
		partyBackground = new Image(res.getTexture("shop-background"));
		friendsBackground = new Image(res.getTexture("shop-background"));
		partyTitle = new Label("Party", FontManager.getFont(60));
		friendsTitle = new Label("Friends", FontManager.getFont(60));
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		loadStage();
	}

	public void loadStage() {
		loadBackground();
		loadReturnToLobbyButton();
		loadTitle();
	}

	private void loadBackground() {
		partyBackground.layout();
		partyBackground.setBounds(35, 100, Betrayal.WIDTH / 2 - 45, Betrayal.HEIGHT - 250);
		popup.addActor(partyBackground);

		friendsBackground.layout();
		friendsBackground.setBounds(Betrayal.WIDTH / 2 + 10, 100, Betrayal.WIDTH / 2 - 45,
				Betrayal.HEIGHT - 250);
		popup.addActor(friendsBackground);
	}

	private void loadTitle() {
		partyTitle.layout();
		partyTitle.setBounds(new Dimension(partyBackground.getX() + partyBackground.getWidth() / 2,
				partyBackground.getTop() - 20 - partyTitle.getPrefHeight(), partyTitle.getPrefWidth(),
				partyTitle.getPrefHeight(), true, false));
		popup.addActor(partyTitle);

		friendsTitle.layout();
		friendsTitle.setBounds(new Dimension(friendsBackground.getX() + friendsBackground.getWidth() / 2,
				partyTitle.getY(), friendsTitle.getPrefWidth(),
				friendsTitle.getPrefHeight(), true, false));
		popup.addActor(friendsTitle);
	}

	private void loadReturnToLobbyButton() {
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 390, 110, 180, 60);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	/**
	 * Given a list of usernames, this method prints out every name in a column.
	 * @param listOfFriends list of usernames
	 */
	private void loadFriendsList(List<String> listOfFriends) {
		VerticalGroup verticalGroup = new VerticalGroup();
		for (String name : listOfFriends) {
			Label label = new Label(name, FontManager.getFont(50));
			verticalGroup.addActor(label);
		}
		ScrollPane scrollPane = new ScrollPane(verticalGroup);
		scrollPane.setBounds(friendsBackground.getX(), lobbyButton.getTop() + 20,
				friendsBackground.getWidth(), friendsTitle.getY() - lobbyButton.getTop() - 30);
		scrollPane.setScrollingDisabled(true, false);
		verticalGroup.align(Align.topLeft);
		verticalGroup.pad(0, 20, 0, 20);
		popup.addActor(scrollPane);
	}
}
