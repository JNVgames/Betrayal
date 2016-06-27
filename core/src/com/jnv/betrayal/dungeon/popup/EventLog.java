package com.jnv.betrayal.dungeon.popup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class EventLog extends Popup {

    private Image okayButton, noButton, background;
    private Label title;
    private ScrollPane scrollPane;
    private Table table;

    public EventLog(Betrayal game) {
        super(game);
        loadButtons();

        table = new Table();
        table.add(new Image(res.getTexture("confirmation-background")));
        table.layout();
        table.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
        scrollPane = new ScrollPane(table);
        scrollPane.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
        scrollPane.layout();
        scrollPane.setZIndex(0);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, false);
        popup.addActor(scrollPane);

    }

    private void loadButtons() {
        loadAnswer();
        loadTitle();
    }

    private void loadTitle() {
        Label.LabelStyle font = FontManager.getFont(40);
        font.fontColor = Color.WHITE;
        title = new Label("Event Log", font);
        title.layout();
        title.setBounds((Betrayal.WIDTH - background.getWidth()) / 2,
                Betrayal.HEIGHT - 100 - 75, table.getWidth(),
                title.getPrefHeight());
        title.setAlignment(Align.center);
        popup.addActor(title);
    }

    private void loadAnswer() {
        okayButton = new Image(res.getTexture("ok"));
        okayButton.layout();
        okayButton.setBounds(110, Betrayal.HEIGHT / 2 - 100, 150, 75);
        okayButton.addListener(new InputListener(okayButton) {
            @Override
            public void doAction() {
                remove();
            }
        });
        popup.addActor(okayButton);

    }

    public void doAction() {
    }
}
