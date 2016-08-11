package com.jnv.betrayal.dungeon.popup;

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
import java.util.List;

public class EventLog extends Popup {

	private Image okayButton;
	private ScrollPane scrollPane;
	private VerticalGroup verticalGroup;
	private Deque<Event> eventHistory;

	public EventLog(Betrayal game, Deque<Event> eventHistory) {
		super(game);
		loadBackground();

		Image border = new Image(res.getTexture("eventlog-border"));
		border.setX(100);
		border.setY(250);
		border.setWidth(Betrayal.WIDTH - 200);
		border.setHeight(Betrayal.HEIGHT - 350);
		popup.addActor(border);


		verticalGroup = new VerticalGroup();
		verticalGroup.layout();
		verticalGroup.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		verticalGroup.align(Align.left);
		scrollPane = new ScrollPane(verticalGroup);
		scrollPane.setX(border.getX()+10);
		scrollPane.setY(border.getY()+10);
		scrollPane.setWidth(border.getWidth() - 20);
		scrollPane.setHeight(border.getHeight() - 20);
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
		okayButton.setBounds(Betrayal.WIDTH / 2 - 75, 140, 150, 75);

		okayButton.addListener(new InputListener(okayButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(okayButton);
	}

	private void loadHistory() {
		int index;
		String eventString, backupString;
		Label testLabel = new Label("", FontManager.getFont50());
		for (Event event : eventHistory) {
			verticalGroup.addActor(new Label(
					"------------------------------------------",
					FontManager.getFont40()));
			eventString = event.toEventLogString();
			Label label = new Label("", FontManager.getFont50());

			String[] splited = eventString.split("\\s+");

			testLabel.setText("");
			for (int i = 0; i < splited.length; i++){
				testLabel.setText(label.getText()+ " " + splited[i]);
				if (testLabel.getPrefWidth() > Betrayal.WIDTH - 220){
					label.setText(label.getText() + "\n" + " " + splited[i]);
					testLabel.setText("");
				}else
					label.setText(label.getText() +" " + splited[i]);
			}
			verticalGroup.addActor(label);
		}
	}
}
