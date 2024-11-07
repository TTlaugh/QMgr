package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Screencontainer_controller {

    private static Parent root = null;

    @FXML
    private Button student_screen_id;

    @FXML
    private Button subject_screen_id;

    @FXML
    private Button exam_screen_id;

    @FXML
    private Button lockworkspace_screen_id;

    @FXML
    private AnchorPane mainbody;

    private String hover_btn = "-fx-background-color: #dbe8ff; -fx-text-fill:#2970ff";

    private String unhover_btn = "-fx-background-color: #ffffff; -fx-text-fill:#667085";

    void setBackGround_Button(Button button_isHover, Button... button_notHover) {
        button_isHover.setStyle(hover_btn);
        button_notHover[0].setStyle(unhover_btn);
        button_notHover[1].setStyle(unhover_btn);
    }

    @FXML
    void btn_exam_screen(ActionEvent event) {
        setBackGround_Button(exam_screen_id, subject_screen_id, student_screen_id);

        AnchorPane insidePane;
        try {
            insidePane = new FXMLLoader(getClass().getResource("/ui/exam-management.fxml")).load();
            setAnchor(insidePane);
            mainbody.getChildren().clear();
            mainbody.getChildren().add(insidePane);
        } catch (IOException e) {
            System.out.println("Error in loading screencontainer_controller btn_exam_screen");
            e.printStackTrace();
        }

    }

    @FXML
    void btn_student_screen(ActionEvent event) {
        setBackGround_Button(student_screen_id, subject_screen_id, exam_screen_id);

        AnchorPane insidePane;
        try {
            insidePane = new FXMLLoader(getClass().getResource("/ui/student-management.fxml")).load();
            setAnchor(insidePane);
            mainbody.getChildren().clear();
            mainbody.getChildren().add(insidePane);
        } catch (IOException e) {
            System.out.println("Error in loading screencontainer_controller btn_student_screen");
            e.printStackTrace();
        }
    }

    @FXML
    void btn_subject_screen(ActionEvent event) {
        setBackGround_Button(subject_screen_id, exam_screen_id, student_screen_id);
        AnchorPane insidePane;
        try {
            insidePane = new FXMLLoader(getClass().getResource("/ui/subject-management.fxml")).load();
            setAnchor(insidePane);
            mainbody.getChildren().clear();
            mainbody.getChildren().add(insidePane);
        } catch (IOException e) {
            System.out.println("Error in loading screencontainer_controller btn_subject_screen");
            e.printStackTrace();
        }
    }

    @FXML
    void btn_lockworkspace_screen(ActionEvent event) {
        try {
            root = (Parent) FXMLLoader.load(getClass().getResource("/ui/workspace-management.fxml"));
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println("Error in loading screencontainer_controller btn_lockworkspace_screen");
            e.printStackTrace();
        }
    }

    private void setAnchor(AnchorPane insidePane) {
        AnchorPane.setTopAnchor(insidePane, 0.0);
        AnchorPane.setBottomAnchor(insidePane, 0.0);
        AnchorPane.setRightAnchor(insidePane, 0.0);
        AnchorPane.setLeftAnchor(insidePane, 0.0);
    }

}
