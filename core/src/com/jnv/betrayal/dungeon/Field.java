/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.Event;
import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.managers.AnimationManager;
import com.jnv.betrayal.dungeon.managers.RoundManager;
import com.jnv.betrayal.dungeon.managers.TurnManager;
import com.jnv.betrayal.dungeon.popup.EventLog;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Field extends Group {

	public final RoundManager roundManager;
	public final TurnManager turnManager;
	public final AnimationManager animationMgr;
	public final GameStateManager gsm;
	public final BetrayalAssetManager res;
	public final Betrayal game;
	public final List<PlayerCard> playerZone;
	public final List<MonsterCard> monsterZone;
	public final Socket socket;
	public int reward;
	private List<Card> allCards;
	private int currentCardTurn;
	private Character clientCharacter;

	/**
	 * Creates an empty field that utilizes a stage for its actors
	 */
	public Field(GameStateManager gsm) {
		// Initialize card zones and instance variables
		playerZone = new ArrayList<PlayerCard>();
		monsterZone = new ArrayList<MonsterCard>();
		this.gsm = gsm;
		game = gsm.game;
		res = gsm.game.res;
		socket = gsm.game.getCurrentCharacter().getRoom().getSocket();
		clientCharacter = gsm.game.getCurrentCharacter();
		if (socket != null && socket.connected()) configSocket();
		reward = 0;
		Image background = new Image(res.getTexture("map-1"));
		currentCardTurn = 0;
		allCards = new ArrayList<Card>();

		// Add things to stage
		addActor(background);
		createEventLogButton();

		turnManager = new TurnManager(this);
		animationMgr = new AnimationManager(res);
		roundManager = new RoundManager(animationMgr);
		roundManager.setSocket(socket);
	}

	private void createEventLogButton() {
		// Load event log button
		float scale = 0.5f;
		Image eventLogButton = new Image(res.getTexture("event-log-button"));
		eventLogButton.layout();
		eventLogButton.setBounds(20, Betrayal.HEIGHT - 30 - 144 * scale, 512 * scale, 144 * scale);
		eventLogButton.addListener(new InputListener(eventLogButton) {
			@Override
			public void doAction() {
				new EventLog(game, roundManager.eventHistory);
			}
		});
		addActor(eventLogButton);
	}

	public void addCard(Card card) {
		if (card instanceof PlayerCard) playerZone.add((PlayerCard) card);
		else if (card instanceof MonsterCard) monsterZone.add((MonsterCard) card);
		else throw new AssertionError("Card is neither a PlayerCard or MonsterCard");
		addActor(card.getGroup());
		card.setField(this);
	}

	public void adjustMonsterHealth() {
		for (MonsterCard card : monsterZone) {
			card.multiplyHealth(playerZone.size());
		}
	}

	/**
	 * Activates select mode on all cards
	 */
	public void beginSelectMode(int numTargets) {
		for (Card card : playerZone) {
			card.beginSelectMode(numTargets);
		}
		for (Card card : monsterZone) {
			card.beginSelectMode(numTargets);
		}
	}

	public boolean isMonsterZoneEmpty() {
		return monsterZone.size() == 0;
	}

	public boolean checkCardsSelected(int cardsSelected) {
		return cardsSelected == getCardsSelected().size();
	}

	/**
	 * Ends select mode on all cards
	 */
	public void endSelectMode() {
		for (Card card : allCards) {
			card.endSelectMode();
			card.unselect();
		}
	}

	/**
	 * @return the array of cards on the field selected
	 */
	public List<Card> getCardsSelected() {
		List<Card> selectedCards = new ArrayList<Card>();
		for (Card card : allCards) {
			if (card.isSelected()) selectedCards.add(card);
		}
		return selectedCards;
	}

	public void unselectAll() {
		for (Card card : allCards) {
			card.unselect();
		}
	}

	public void resetCards() {
		for (Card card : getAllCards()) {
			card.reset();
		}
	}

	public void setNextCardIndex() {
		refreshAllCards();
		currentCardTurn = (currentCardTurn + 1) % getAllCards().size();
	}

	public Card getCurrentCard() {
		return allCards.get(currentCardTurn);
	}

	// refreshed all cards incase a card died.
	public void refreshAllCards() {
		allCards.clear();
		allCards.addAll(playerZone);
		allCards.addAll(monsterZone);
	}

	public List<Card> getAllCards() {
		refreshAllCards();
		return allCards;
	}

	public List<Card> getAllPlayerCards() {
		return new ArrayList<Card>(playerZone);
	}

	public List<Card> getAllMonsterCards() {
		return new ArrayList<Card>(monsterZone);
	}

	/**
	 * Player card death event
	 */
	public void removePlayerCard(PlayerCard card) {
		playerZone.remove(card);
		refreshAllCards();
	}

	/**
	 * Monster card death event
	 */
	public void removeMonsterCard(MonsterCard card) {
		monsterZone.remove(card);
		refreshAllCards();
	}

	public void configSocket() {
		socket.on("newEvent", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];

				// Initializing values
				List<Integer> dstID = new ArrayList<Integer>();
				int srcID = -1;

				try {
					srcID = data.getJSONObject("effect").getInt("src");
					JSONArray destArray = data.getJSONObject("effect").getJSONArray("dest");
					for (int i = 0; i < destArray.length(); i++) {
						dstID.add(destArray.getInt(i));
					}

					// FInd Card corresponding to id
					Card src = findSrcCard(srcID);
					List<Card> dst = findDestCard(dstID);

					// Recreate event
					Class<?> clazz = Class.forName(data.getJSONObject("effect").getString("class"));
					Constructor<?> constructor = clazz.getConstructor(JSONObject.class, int.class, Card.class, List.class);
					Effect effect = (Effect) constructor.newInstance(data.getJSONObject("effect").getJSONObject("values"),
							data.getJSONObject("effect").getInt("turns"), src, dst);

					// Register recreated event with roundManager
					Event event = roundManager.addEventClient(effect, EventType.valueOf(data.getString("eventType")));
					System.out.println("RECEIVED EVENT: " + event);
					turnManager.nextTurn();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Card findSrcCard(int srcID) {
		//check if there is a src or dest card in the playerzone
		for (Card card : allCards) {
			if (card.getID() == srcID) {
				return card;
			}
		}
		return null;
	}

	private List<Card> findDestCard(List<Integer> dstID) {
		List<Card> dst = new ArrayList<Card>();
		for (Card card : allCards) {
			for (int i : dstID) {
				if (i == card.getID()) {
					dst.add(card);
				}
			}
		}
		return dst;
	}

	public void adjustPlayerCardStatsBasedOnJobs(){
		boolean hasWarrior, hasThief, hasPriest, hasKnight;
		hasWarrior = hasThief = hasPriest = hasKnight = false;

		//Determines what jobs are in the party
		for( PlayerCard card : playerZone ){
			switch (card.getJob()){

				case WARRIOR:
					hasWarrior = true;
					break;
				case THIEF:
					hasThief = true;
					break;
				case KNIGHT:
					hasKnight = true;
					break;
				case PRIEST:
					hasPriest = true;
					break;
			}
		}

		//adjusts all stats according to which Jobs are present
		for( PlayerCard card : playerZone ){
			if(hasWarrior) card.multiplyAttack();
			if(hasPriest) card.multiplyHealth();
			if(hasKnight) card.multiplyDefense();
		}

		if(hasThief) reward  *= 2;
	}

	public Character getClientCharacter() {
		return clientCharacter;
	}
}
