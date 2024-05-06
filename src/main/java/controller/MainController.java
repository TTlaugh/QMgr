package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import business.model.Exam;
import business.services.WelcomeFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utils.DisplayDialog_Notification;

public class MainController implements Initializable {
    @FXML
    private Label Account_Teacher=new Label();

	private static Parent root = null;

	@FXML
	private AnchorPane AnchorPaneLayout;

	@FXML
	private Button start_Test_Main = new Button();

	public static Exam exam_StartTest;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Account_Teacher.setText(LoginController.teacher_Current.getTeacherID());
	}

	public void LogOut_Quizz(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
		
		GroupController.group_Current_Layout=null;
		LoginController.teacher_Current=null;
		
	}

	public void Start_Test(ActionEvent event) throws IOException {
		if (exam_StartTest != null) {
			root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Start_test.fxml"));
			((Node) event.getSource()).getScene().setRoot(root);
		} else {
			DisplayDialog_Notification.Dialog_Infomation("Notification", "ban chua chon exam ", "exam ");
			Back_Exam(event);
		}
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

	public void receiveExam(Exam exam) {
		exam_StartTest = exam;
	}

}
