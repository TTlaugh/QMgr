package fxml;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class StartServerController {

	public void backToMain(ActionEvent event) throws IOException {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);;
	}
}
