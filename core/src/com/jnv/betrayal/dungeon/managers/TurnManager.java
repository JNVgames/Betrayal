package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.dungeon.turns.MonsterTurn;
import com.jnv.betrayal.dungeon.turns.TeamMemberTurn;
import com.jnv.betrayal.dungeon.turns.Turn;
import com.jnv.betrayal.dungeon.turns.YourTurn;
import com.jnv.betrayal.dungeon.utils.Turns;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

/**
 * Keeps track of user/party member/monster's turns.
 */
public class TurnManager {

	public final Group panels = new Group();
	private Field field;
	private GameStateManager gsm;
	private Pool<Label> panelPool;
	private Pool<Button> buttonPool;
	private BetrayalAssetManager res;
	private Turn currentTurn;

	public TurnManager(Field field) {
		this.field = field;
		gsm = field.gsm;
		res = field.res;
		buttonPool = new Pool<Button>() {
			public Button obtain() {
				Button tmp = super.obtain();
				ClickListener clickListener = tmp.getClickListener();
				// Remove all listeners except for the one needed for button to work
				for (EventListener listener : tmp.getListeners())
					if (listener != clickListener) tmp.removeListener(listener);
				return tmp;
			}

			protected Button newObject() {
				return new Button();
			}
		};
		panelPool = new Pool<Label>() {
			public Label obtain() {
				Label tmp = super.obtain();
				tmp.clearListeners();
				return tmp;
			}

			protected Label newObject() {
				return new Label(null, new Label.LabelStyle(FontManager.getFont(70)));
			}
		};
		field.addActor(panels);
	}

//	public ArrayList<Card> selectNextPlayerTurn() {
//
//	}

	public void nextTurn() {
		field.setNextCardTurn();
		if (field.getCurrentCard() instanceof PlayerCard) {
			currentTurn.doAtStartOfTurn();
		}
		draw();
	}

	public void draw() {
		Card currentCard = field.getCurrentCard();

		// Team member turn
		if (currentCard instanceof PlayerCard
				&& currentCard.getID() != field.game.getCurrentCharacter().getId()) {
			currentTurn = getTurn(Turns.TEAM_MEMBER_TURN);
		}
		// Monster turn
		else if (currentCard instanceof MonsterCard) {
			currentTurn = getTurn(Turns.MONSTER_TURN);
		}
		// Your turn
		else {
			currentTurn = getTurn(Turns.YOUR_TURN);
		}
		currentTurn.draw();
	}

	private Turn getTurn(Turns turnType) {
		switch (turnType) {
			case YOUR_TURN:
				return new YourTurn(field, panelPool, buttonPool, panels, field.game);
			case TEAM_MEMBER_TURN:
				return new TeamMemberTurn(field, panelPool, buttonPool, panels, field.game);
			case MONSTER_TURN:
				return new MonsterTurn(field, panelPool, buttonPool, panels, field.game);
			default:
				throw new AssertionError("TurnManager: Turns turntype does not exist");
		}
	}
}
