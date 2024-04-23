package view.controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import services.GroupManager;

public class GroupController {
	private Scene scene = null;
	@FXML
	private TextField textField_GroupID;
	@FXML
	private TextField textField_Name;
	@FXML
	private Button button_Save;
	@FXML
	private ComboBox<String> comboBox_Group;

	private GroupManager groupManager = new GroupManager();

	public void loadComboBox_Group() {

	}

	public void addNewGroup() throws IOException {
		textField_GroupID.setDisable(false);
		textField_Name.setDisable(false);
		button_Save.setDisable(false);
		System.out.println(groupManager.getGroups());
	}

	public void Group_Tranfer_GroupAdd_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Group_add.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		StackPane myStackPane = (StackPane) scene.lookup("#StackPane_Layout");
		myStackPane.getChildren().clear();
		myStackPane.getChildren().add(loader.load());
	}

	public void Group_Tranfer_GroupView_Quizz(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Group_view.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		StackPane myStackPane = (StackPane) scene.lookup("#StackPane_Layout");
		myStackPane.getChildren().clear();
		myStackPane.getChildren().add(loader.load());
	}

	public void Back_Group(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Group.fxml"));
		scene = (Scene) ((Node) event.getSource()).getScene();
		StackPane myStackPane = (StackPane) scene.lookup("#StackPane_Layout");
		myStackPane.getChildren().clear();
		myStackPane.getChildren().add(loader.load());
	}
}
