package controller;

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

public class MainController {
	private Parent root = null;
	
	@FXML
	private StackPane StackPane_Layout;

	public void LogOut_Quizz(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/view/fxml/Login.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
	}

	public void Start_Test(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/view/fxml/Start_test.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
	}
	public void Back_Exam(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Exam.fxml"));
		StackPane_Layout.getChildren().clear();
		StackPane_Layout.getChildren().add(loader.load());
	}

	public void Back_Submission(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Submiss.fxml"));
		StackPane_Layout.getChildren().clear();
		StackPane_Layout.getChildren().add(loader.load());
	}

	public void Back_Question(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Question.fxml"));
		StackPane_Layout.getChildren().clear();
		StackPane_Layout.getChildren().add(loader.load());
	}
	public void Back_Group(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Group.fxml"));
		StackPane_Layout.getChildren().clear();
		StackPane_Layout.getChildren().add(loader.load());
	}
}
