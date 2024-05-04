package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable{
	private Parent root = null;
	
	@FXML
	private AnchorPane AnchorPaneLayout;
	
	@FXML
	private Label nameAccount;

	public void LogOut_Quizz(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
	}

	public void Start_Test(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Start_test.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
	}

	public void Back_Exam(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Exam.fxml")).load();
		setAnchor(insidePane);
		AnchorPaneLayout.getChildren().clear();
		AnchorPaneLayout.getChildren().add(insidePane);
	}

	public void Back_Submission(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Submiss.fxml")).load();
		setAnchor(insidePane);
		AnchorPaneLayout.getChildren().clear();
		AnchorPaneLayout.getChildren().add(insidePane);
	}

	public void Back_Question(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question.fxml")).load();
		setAnchor(insidePane);
		AnchorPaneLayout.getChildren().clear();
		AnchorPaneLayout.getChildren().add(insidePane);
	}

	public void Back_Group(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Group.fxml")).load();
		setAnchor(insidePane);
		AnchorPaneLayout.getChildren().clear();
		AnchorPaneLayout.getChildren().add(insidePane);
	}
	private void setAnchor(AnchorPane insidePane) {
		AnchorPane.setTopAnchor(insidePane, 0.0);
		AnchorPane.setBottomAnchor(insidePane, 0.0);
		AnchorPane.setRightAnchor(insidePane, 0.0);
		AnchorPane.setLeftAnchor(insidePane, 0.0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		if(new LoginController().teacher_Current != null)
//		nameAccount.setText(new LoginController().teacher_Current.getTeacherID());
	}
}
