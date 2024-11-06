package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class workspace_controller {

    @FXML
    private Button workspace_setup;

    @FXML
    private TextField workspace_pin;

    @FXML
    private Button workspace_continue;

    @FXML
    private ComboBox<?> workspace_choose;

    @FXML
    private Button workspace_rename;

    @FXML
    private Button workspace_archive;

    @FXML
    void btn_workspace_continue(ActionEvent event) {
        Parent root;
        try {
            root = (Parent) FXMLLoader.load(getClass().getResource("/ui/exam-management.fxml"));
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in loading workspace_controller");
        }
    }

}