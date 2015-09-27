package com.jnv.betrayal.dungeon.phases;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

/**
 * Guides the dungeon through the different phases of the dungeon
 * Phases are: Main, Defend, Item, Skill, Attach
 */
public class PhaseManager {

	public final Field field;
	public final Pool<Label> panelPool;
	public final Pool<Button> buttonPool;
	private Phase currentPhase;

	public PhaseManager(Field field) {
		this.field = field;
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
		currentPhase = new Main(this);
		field.addActor(currentPhase.group);
	}

	public void setPhase(Phase phase) {
		field.removeActor(currentPhase.group);
		currentPhase = phase;
		field.addActor(currentPhase.group);
	}
}
