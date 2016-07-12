package com.jnv.betrayal.dungeon.popup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.dungeon.effects.Event;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

import java.util.Deque;

public class EventLog extends Popup {

	private Image okayButton;
	private ScrollPane scrollPane;
	private VerticalGroup verticalGroup;
	private Deque<Event> eventHistory;

	public EventLog(Betrayal game, Deque<Event> eventHistory) {
		super(game);
		loadBackground();

		verticalGroup = new VerticalGroup();
		verticalGroup.layout();
		verticalGroup.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		verticalGroup.align(Align.left);
		scrollPane = new ScrollPane(verticalGroup);
		scrollPane.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		scrollPane.layout();
		scrollPane.setZIndex(0);
		scrollPane.setScrollingDisabled(true, false);
		scrollPane.setOverscroll(false, false);
		popup.addActor(scrollPane);

		this.eventHistory = eventHistory;
		loadButtons();
		loadHistory();
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("confirmation-background"));
		background.layout();
		background.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		popup.addActor(background);
	}

	private void loadButtons() {
		loadAnswer();
	}

	private void loadAnswer() {
		okayButton = new Image(res.getTexture("ok"));
		okayButton.layout();
		okayButton.setBounds(Betrayal.WIDTH / 2 - 75, 110, 150, 75);

		okayButton.addListener(new InputListener(okayButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(okayButton);
	}

	private void loadHistory() {
		for (Event event : eventHistory) {
			Label newEvent = new Label(event.toString(), FontManager.getFont(40));
			Label separate = new Label(
					 "------------------------------------------------------------"
					+"------------------------------------------------------------"
					+"------------------------------------------------------------"
					, FontManager.getFont(12));
			separate.setColor(Color.WHITE);
			verticalGroup.addActor(newEvent);
			verticalGroup.addActor(separate);


		}
	}
}
