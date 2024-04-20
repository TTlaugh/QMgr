
package application;

//	
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.stage.Stage;
//import utils.Constant;
//import utils.KeyEventFunction;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//
//public class Main extends Application {
//
//	@Override
//	public void start(Stage primaryStage) {
//		try {
////			primaryStage.getIcons().add(new Image("/images/icon.png"));
//			primaryStage.setTitle("Mine Quizz - HEHE");
////			primaryStage.initStyle(StageStyle.TRANSPARENT);
////			primaryStage.initStyle(StageStyle.UNDECORATED);
//			primaryStage.show();
//
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Main.fxml"));
//			Parent root = (Parent) loader.load();
//			Scene scene = new Scene(root, Constant.ScreenSize.WIDTH, Constant.ScreenSize.HEIGHT);
//
//			primaryStage.setMinWidth(1300);
//			primaryStage.setMinHeight(900);
//			primaryStage.setScene(scene);
//			primaryStage.setMaximized(true);
////			primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
////			primaryStage.setFullScreen(true);
//
//			scene.setOnKeyPressed(KeyEventFunction.toggleFullScreen(primaryStage));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//}
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		ComboBox<User> combo = new ComboBox<User>();
		combo.setConverter(new StringConverter<User>() {
			@Override
			public String toString(User usr) {
				return usr == null ? "" : usr.getName();
			}

			@Override
			public User fromString(String s) {
				User usr = new User(s, "haslo");
				return usr;
			}
		});
		combo.getItems().addAll(
				new User("hehe", "123"),
				new User("huhu", "123"),
				new User("hihi", "123"),
				new User("haha", "123"));
		// combo.setCellFactory(lv -> {
		// ListCell<String> cell = new ListCell<String>() {
		// @Override
		// protected void updateItem(String item, boolean empty) {
		// super.updateItem(item, empty);
		// if (empty) {
		// setText(null);
		// } else {
		// if (item.isEmpty()) {
		// setText("Add item...");
		// } else {
		// setText(item);
		// }
		// }
		// }
		// };
		//
		// cell.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
		// if (cell.getItem().isEmpty() && ! cell.isEmpty()) {
		// TextInputDialog dialog = new TextInputDialog();
		// dialog.setContentText("Enter item");
		// dialog.showAndWait().ifPresent(text -> {
		// int index = combo.getItems().size()-1;
		// combo.getItems().add(index, text);
		// combo.getSelectionModel().select(index);
		// });
		// evt.consume();
		// }
		// });
		//
		// return cell ;
		// });

		BorderPane root = new BorderPane();
		root.setTop(combo);
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class User {
	private String name;
	private String password;

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}