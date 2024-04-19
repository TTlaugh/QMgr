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
	private Scene scene = null;
	private Stage stage = null;
	private Parent root = null;
	@FXML
	private AnchorPane Anchor_Layout;
	@FXML
	private AnchorPane Anchor_Regester;
	@FXML
	private AnchorPane Anchor_SignIn;

	public void LogOut_Quizz(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/view/fxml/Login.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
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

	public void Back_Submission(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Submiss.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

	public void Exam_Tranfer_ExamAdd_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Exam_add.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

	public void Exam_Tranfer_ExamView_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Exam_view.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

	public void Back_Exam(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Exam.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

	public void Question_Tranfer_QuestionView_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Question_view.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

	public void Question_Tranfer_QuestionAdd_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Question_add.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

	public void Back_Question(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Question.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

	public void Group_Tranfer_GroupAdd_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Group_add.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

	public void Group_Tranfer_GroupView_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Group_view.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

	public void Back_Group(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Group.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		AnchorPane myAnchorPane = (AnchorPane) scene.lookup("#Anchor_Layout");
		myAnchorPane.getChildren().clear();
		myAnchorPane.getChildren().add(loader.load());
	}

}
