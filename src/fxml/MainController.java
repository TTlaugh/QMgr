package fxml;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MainController {

	public void startServerMenu(ActionEvent event) throws IOException {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/StartServer.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);;
	}
	
	public void quit(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow() ).close();
	}

}
