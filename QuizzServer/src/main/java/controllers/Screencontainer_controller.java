package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Screencontainer_controller {

    private Parent root = null;

    @FXML
    private Button student_screen_id = new Button();

    @FXML
    private Button subject_screen_id = new Button();

    @FXML
    private Button exam_screen_id = new Button();

    @FXML
    private Button lockworkspace_screen_id = new Button();

    @FXML
    public AnchorPane mainbody = new AnchorPane();

    private String hover_btn = "-fx-background-color: #dbe8ff; -fx-text-fill:#2970ff";

    private String unhover_btn = "-fx-background-color: #ffffff; -fx-text-fill:#667085";

    public AnchorPane getMainbody() {
        return mainbody;
    }

    void setBackGround_Button(Button button_isHover, Button... button_notHover) {
        button_isHover.setStyle(hover_btn);
        button_notHover[0].setStyle(unhover_btn);
        button_notHover[1].setStyle(unhover_btn);
    }

    @FXML
    void btn_exam_screen(ActionEvent event) {
        setBackGround_Button(exam_screen_id, subject_screen_id, student_screen_id);
        String url_ui = "/ui/exam-management.fxml";
        load_Scene_AnchorPane(url_ui);
    }

    @FXML
    void btn_student_screen(ActionEvent event) {
        setBackGround_Button(student_screen_id, subject_screen_id, exam_screen_id);
        String url_ui = "/ui/student-management.fxml";
        load_Scene_AnchorPane(url_ui);
    }

    @FXML
    void btn_subject_screen(ActionEvent event) {
        setBackGround_Button(subject_screen_id, exam_screen_id, student_screen_id);
        String url_ui = "/ui/subject-management.fxml";
        load_Scene_AnchorPane(url_ui);
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

    public void setAnchor(AnchorPane insidePane) {
        AnchorPane.setTopAnchor(insidePane, 0.0);
        AnchorPane.setBottomAnchor(insidePane, 0.0);
        AnchorPane.setRightAnchor(insidePane, 0.0);
        AnchorPane.setLeftAnchor(insidePane, 0.0);
    }

    public void load_Scene_AnchorPane(String url_ui) {
        AnchorPane insidePane;
        try {
            insidePane = new FXMLLoader(getClass().getResource(url_ui)).load();
            setAnchor(insidePane);
            mainbody.getChildren().clear();
            mainbody.getChildren().add(insidePane);
        } catch (IOException e) {
            System.out.println("Error in loading screencontainer_controller load_Scene_AnchorPane");
            e.printStackTrace();
        }
    }

}
