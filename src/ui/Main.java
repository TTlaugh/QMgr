package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));

			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("/ui/application.css").toExternalForm());

			primaryStage.setScene(scene);

			primaryStage.getIcons().add(new Image("/images/icon.png"));
			primaryStage.setTitle("Mine Quizz - HEHE");

			primaryStage.show();
			primaryStage.setMaximized(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
