
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.util.ResourceBundle.Control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Hello");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1300, 900);
        scene.getStylesheets().add(getClass().getResource("/view/css/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
