package utils;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Screen;

public class Constant {

	public class ScreenSize {
		public static final double WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
		public static final double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
	}

	public class KeyMap {
		public static final KeyCodeCombination FullScreenKey = new KeyCodeCombination(KeyCode.F11);
	}

}
