package com.jnv.betrayal.scene2d.ui;

public final class LabelUtils {

	public static void splitLabelString(String string,
										com.badlogic.gdx.scenes.scene2d.ui.Label testLabel,
										com.badlogic.gdx.scenes.scene2d.ui.Label label,
										float width) {
		String[] splitted = string.split("\\s+");

		testLabel.setText("");
		for (int i = 0; i < splitted.length; i++) {
			testLabel.setText(label.getText() + " " + splitted[i]);
			if (testLabel.getPrefWidth() > width) {
				label.setText(label.getText() + "\n" + " " + splitted[i]);
				testLabel.setText("");
			} else
				label.setText(label.getText() + " " + splitted[i]);
		}
	}
}
