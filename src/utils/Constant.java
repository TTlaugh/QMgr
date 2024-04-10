package utils;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Screen;

public class Constant {
	
	public class MySQLProperties {
		public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
		public static final String URL = "jdbc:mysql://localhost:3306/QuizzServer";
		public static final String USERNAME = "root";
		public static final String PASSWORD = "tt";
	}

	public class ScreenSize {
		public static final double WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
		public static final double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
	}

	public class KeyMap {
		public static final KeyCodeCombination FullScreenKey = new KeyCodeCombination(KeyCode.F11);
	}

}
