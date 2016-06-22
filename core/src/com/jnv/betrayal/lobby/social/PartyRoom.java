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

public class PartyRoom extends Popup {

	public final Image partyBackground, lobbyButton, createRoom, leaveRoom;
	public final Label partyTitle;

	public PartyRoom(Betrayal game) {
		super(game);
		createRoom = new Image(res.getTexture("create-room"));
		leaveRoom = new Image(res.getTexture("leave-room"));
		partyBackground = new Image(res.getTexture("shop-background"));
		partyTitle = new Label("", FontManager.getFont(60));			//VINCENT DO NOT DELETE THIS. USING IT IN RELATION
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		loadStage();
	}

	public void loadStage() {
		loadBackground();
		loadReturnToLobbyButton();
		loadTitle();
		loadRoomButtons();
	}

	private void loadBackground() {
		partyBackground.layout();
		partyBackground.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		popup.addActor(partyBackground);

	}

	private void loadTitle() {
		partyTitle.layout();
		partyTitle.setBounds(new Dimension(partyBackground.getX() + partyBackground.getWidth() / 2,
				partyBackground.getTop() - 20 - partyTitle.getPrefHeight(), partyTitle.getPrefWidth(),
				partyTitle.getPrefHeight(), true, false));
		popup.addActor(partyTitle);


	}

	private void loadReturnToLobbyButton() {
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 110, 312, 100);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadRoomButtons(){
		createRoom.layout();
		createRoom.setBounds(142 , partyTitle.getY()-20, 200, 65);
		createRoom.addListener(new InputListener(createRoom) {
			@Override
			public void doAction() {

			}
		});
		popup.addActor(createRoom);

		leaveRoom.layout();
		leaveRoom.setBounds(372 , partyTitle.getY()-20, 200, 65);
		leaveRoom.addListener(new InputListener(leaveRoom) {
			@Override
			public void doAction() {

			}
		});
		popup.addActor(leaveRoom);
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
		scrollPane.setScrollingDisabled(true, false);
		verticalGroup.align(Align.topLeft);
		verticalGroup.pad(0, 20, 0, 20);
		popup.addActor(scrollPane);
	}
}
