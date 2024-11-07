package controller;

import java.net.URL;
import java.util.ResourceBundle;

import components.Group_card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class Student_controller implements Initializable {

    @FXML
    private FlowPane flowpane_mainbody;

    @FXML
    private StackPane create_newGroup;

    @FXML
    void newGroup_btn(ActionEvent event) {

        // Group_card group_card = new Group_card("Group GR0001", "GR00001",
        // "10/10/2020");

        // AnchorPane anchor_mainbody = group_card.getGroup_Instance();

        // flowpane_mainbody.getChildren().add(anchor_mainbody);
        create_newGroup.setVisible(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
