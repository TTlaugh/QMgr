package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Start_TestController {
	private Parent root = null;

	public void Back_Test(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/view/fxml/Main.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
	}
}
