package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class QuestionController {
	private Scene scene = null;

	public void Question_Tranfer_QuestionView_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Question_view.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		StackPane myStackPane = (StackPane) scene.lookup("#StackPane_Layout");
		myStackPane.getChildren().clear();
		myStackPane.getChildren().add(loader.load());
	}

	public void Question_Tranfer_QuestionAdd_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Question_add.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		StackPane myStackPane = (StackPane) scene.lookup("#StackPane_Layout");
		myStackPane.getChildren().clear();
		myStackPane.getChildren().add(loader.load());
	}

	public void Back_Question(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Question.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		StackPane myStackPane = (StackPane) scene.lookup("#StackPane_Layout");
		myStackPane.getChildren().clear();
		myStackPane.getChildren().add(loader.load());
	}
}
