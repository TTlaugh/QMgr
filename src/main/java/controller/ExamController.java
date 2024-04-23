package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class ExamController {

	private Scene scene = null;

	public void Exam_Tranfer_ExamAdd_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Exam_add.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		StackPane myStackPane = (StackPane) scene.lookup("#StackPane_Layout");
		myStackPane.getChildren().clear();
		myStackPane.getChildren().add(loader.load());
	}

	public void Exam_Tranfer_ExamView_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Exam_view.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		StackPane myStackPane = (StackPane) scene.lookup("#StackPane_Layout");
		myStackPane.getChildren().clear();
		myStackPane.getChildren().add(loader.load());
	}

	public void Back_Exam(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Exam.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		StackPane myStackPane = (StackPane) scene.lookup("#StackPane_Layout");
		myStackPane.getChildren().clear();
		myStackPane.getChildren().add(loader.load());
	}

}