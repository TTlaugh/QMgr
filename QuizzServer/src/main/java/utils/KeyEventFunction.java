package utils;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KeyEventFunction {
	public static EventHandler<KeyEvent> toggleFullScreen(Stage stage) {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (Constant.KeyMap.FullScreenKey.match(e)) {
					stage.setFullScreen(!stage.isFullScreen());
					stage.setMaximized(!stage.isFullScreen());
				}
			}
		};
	}

}
