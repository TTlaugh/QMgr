package view.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {

	private Parent root = null;
	@FXML
	private AnchorPane Anchor_Layout;
	@FXML
	private AnchorPane Anchor_Regester;
	@FXML
	private AnchorPane Anchor_SignIn;

	public void LogOut_Quizz(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/view/fxml/Login.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	public void Sign_In(ActionEvent event) throws IOException {
		Back_Test(event);
	}

	public void Sign_Up_Tranfer(ActionEvent event) throws IOException {
		(Anchor_SignIn).setVisible(false);
		(Anchor_Regester).setVisible(true);
	}

	public void Sign_In_Tranfer(ActionEvent event) throws IOException {
		(Anchor_Regester).setVisible(false);
		(Anchor_SignIn).setVisible(true);
	}

	public void Start_Test(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/view/fxml/Start_test.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
	}

	public void Back_Test(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/view/fxml/Main.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
	}

	public void Exam_Quizz(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Exam.fxml"));
		Node content = loader.load();
		(Anchor_Layout).getChildren().clear();
		(Anchor_Layout).getChildren().add(content);
		Parent parentNode = root;
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		
	
	}

	public void Submission_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Submiss.fxml"));
		Node content = loader.load();
		(Anchor_Layout).getChildren().clear();
		(Anchor_Layout).getChildren().add(content);
		Parent parentNode = root;
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	public void Group_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Group.fxml"));
		Node content = loader.load();
		(Anchor_Layout).getChildren().clear();
		(Anchor_Layout).getChildren().add(content);
		Parent parentNode = root;
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	public void Question_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Question.fxml"));
		Node content = loader.load();
		(Anchor_Layout).getChildren().clear();
		(Anchor_Layout).getChildren().add(content);
		Parent parentNode = root;
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	public void Exam_Tranfer_ExamAdd_Quizz(ActionEvent event) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Exam_add.fxml"));
			Node content = loader.load();
			(Anchor_Layout).getChildren().clear();
			(Anchor_Layout).getChildren().add(content);
			Parent parentNode = root;
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
	}
}
