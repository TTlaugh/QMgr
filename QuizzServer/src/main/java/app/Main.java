
package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import utils.Constant;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			primaryStage.setTitle("Quizz Server");
			primaryStage.show();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/workspace-management.fxml"));

			Parent root = (Parent) loader.load();

			Scene scene = new Scene(root, Constant.ScreenSize.WIDTH, Constant.ScreenSize.HEIGHT);

			primaryStage.setMinWidth(1300);
			primaryStage.setMinHeight(900);
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);

			primaryStage.setOnCloseRequest(event -> {
				Platform.exit();
				System.exit(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
