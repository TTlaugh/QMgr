package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import utils.Constant;
import utils.KeyEventFunction;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.getIcons().add(new Image("/images/icon.png"));
			primaryStage.setTitle("Mine Quizz - HEHE");
//			primaryStage.initStyle(StageStyle.TRANSPARENT);
//			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Main.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root, Constant.ScreenSize.WIDTH, Constant.ScreenSize.HEIGHT);

			primaryStage.setMinWidth(1300);
			primaryStage.setMinHeight(900);
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
//			primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
//			primaryStage.setFullScreen(true);

			scene.setOnKeyPressed(KeyEventFunction.toggleFullScreen(primaryStage));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
