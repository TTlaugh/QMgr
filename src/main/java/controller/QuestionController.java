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

public class QuestionController {
	private Scene scene = null;
	private AnchorPane anchor;
	public void Question_Tranfer_QuestionView_Quizz(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question_view.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}

	public void Question_Tranfer_QuestionAdd_Quizz(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question_add.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}

	public void Back_Question(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}
	private void setAnchor(AnchorPane insidePane) {
		AnchorPane.setTopAnchor(insidePane, 0.0);
		AnchorPane.setBottomAnchor(insidePane, 0.0);
		AnchorPane.setRightAnchor(insidePane, 0.0);
		AnchorPane.setLeftAnchor(insidePane, 0.0);
	}
}
