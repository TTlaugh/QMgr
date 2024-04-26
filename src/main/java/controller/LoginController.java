package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	private Parent root = null;
	@FXML
	private AnchorPane Anchor_Regester;
	@FXML
	private AnchorPane Anchor_SignIn;

	public void Sign_Up_Tranfer(ActionEvent event) throws IOException {
		(Anchor_SignIn).setVisible(false);
		(Anchor_Regester).setVisible(true);
	}

	public void Sign_In_Tranfer(ActionEvent event) throws IOException {
		(Anchor_Regester).setVisible(false);
		(Anchor_SignIn).setVisible(true);
	}

	public void Sign_In(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
	}

}
