/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.mechanics;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.Event;
import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.managers.AnimationManager;
import com.jnv.betrayal.dungeon.effects.actions.Attack;
import com.jnv.betrayal.dungeon.managers.RoundManager;
import com.jnv.betrayal.dungeon.managers.TurnManager;
import com.jnv.betrayal.dungeon.popup.EventLog;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.FontManager;
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
	List<Card> allCards;
	private int currentCardTurn;
	private Label timeLeft;
	private Timer timer;
	private Timer.Task task;
	private long savedTime;
	private int delay, t;
	public int reward;

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
		if (socket != null && socket.connected()) configSocket();
		reward = 0;
		Image background = new Image(res.getTexture("map-1"));
		currentCardTurn = 0;
		allCards = new ArrayList<Card>();

		// Add things to stage
		addActor(background);

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
		turnManager = new TurnManager(this);
		animationMgr = new AnimationManager(res);
		roundManager = new RoundManager(animationMgr);
		roundManager.setSocket(socket);

		timer = new Timer();
		timer.start();
		delay = 5;
		task = new Timer.Task(){
			@Override
			public void run() {
				// Do your work
				defaultEvent();
			}
		};

		timeLeft = new Label("" , FontManager.getFont60()) {
			@Override
			public void act(float delta) {
				super.act(delta);
				t = (int)(System.currentTimeMillis() - savedTime);
				t /= 1000;
				t = 5 - t;
				timeLeft.setText(Integer.toString(t));
			}
		};
	}

	public void clearTimerTask(){

		timer.clear();
	}

	public void startTimer(){
		savedTime = System.currentTimeMillis();
		Timer.schedule(task, delay);
	}

	private void defaultEvent(){
		List<Card> dst = new ArrayList<Card>();
		dst.add(monsterZone.get(0));
		Event event = new Event(new Attack(getCurrentCard(), dst), EventType.ATTACK);
		roundManager.addEvent(event);

		//monster should never call this
	}

	public void setTimerLabel(){
		timeLeft = new com.jnv.betrayal.scene2d.ui.Label("", FontManager.getFont60());
		timeLeft.setX(Betrayal.WIDTH/2 - timeLeft.getPrefWidth() / 2);
		timeLeft.setY(1000);
		addActor(timeLeft);
	}

	public void addCard(Card card) {
		if (card instanceof PlayerCard) playerZone.add((PlayerCard) card);
		else if (card instanceof MonsterCard) monsterZone.add((MonsterCard) card);
		else throw new AssertionError("Card is neither a PlayerCard or MonsterCard");
		addActor(card.getGroup());
		card.setField(this);
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
		refreshAllCards();
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
		refreshAllCards();
		for (Card card : allCards) {
			if (card.isSelected()) selectedCards.add(card);
		}
		return selectedCards;
	}

	public void unselectAll() {
		refreshAllCards();
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
		refreshAllCards();
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

	public void sendDungeonEvent(Event event) {
		System.out.println("newEvent Sent");
		socket.emit("newEvent", event.toJSON());
	}

	public void configSocket() {
		socket.on("newEvent", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				System.out.println("newEvent Received");
				// Send to Round Manager
				JSONObject data = (JSONObject) args[0];

				// Take out src and dest, recreate them into actual Cards

				//resetting values
				List<Integer> dstID = new ArrayList<Integer>();
				int srcID = -1;

				try {
					srcID = data.getJSONObject("effect").getInt("src");
					JSONArray destArray = data.getJSONObject("effect").getJSONArray("dest");
					for (int i = 0; i < destArray.length(); i++) {
						dstID.add(destArray.getInt(i));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				// FInd Card corresponding to id
				Card src = setSrcCard(srcID);
				List<Card> dst = setDstCard(dstID);

				// Create event
				Class<?> clazz;
				Constructor<?> constructor;
				Effect effect;

				try {
					clazz = Class.forName(data.getJSONObject("effect").getString("class"));
					constructor = clazz.getConstructor(JSONObject.class, int.class, Card.class, List.class);
					effect = (Effect) constructor.newInstance(data.getJSONObject("effect").getJSONObject("values"),
							data.getJSONObject("effect").getInt("turns"), src, dst);
					roundManager.addEventClient(effect, EventType.valueOf(data.getString("eventType")));
					turnManager.nextTurn();
					System.out.println("next turn");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	private Card setSrcCard(int srcID) {
		//check if there is a src or dest card in the playerzone
		for (Card card : allCards) {
			if (card.getID() == srcID) {
				return card;
			}
		}
		return null;
	}

	private List<Card> setDstCard(List<Integer> dstID) {
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


}
