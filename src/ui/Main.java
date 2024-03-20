package ui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
			BorderPane root = (BorderPane) loader.load();

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);

			primaryStage.getIcons().add(new Image("/images/icon.png"));
			primaryStage.setTitle("Mine Quizz - HEHE");

			primaryStage.show();
			primaryStage.setMaximized(true);

			Screen screen = Screen.getPrimary();
			double dpi = screen.getDpi();
			double scaleFactor = dpi / 96.0;
			primaryStage.setRenderScaleX(scaleFactor);
			primaryStage.setRenderScaleY(scaleFactor);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
