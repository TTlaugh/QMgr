package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import components.Exam_card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import utils.Notification;

public class Exam_controller implements Initializable {
    // FlowPane Main
    @FXML
    private FlowPane flowpane_mainbody;

    // Stack Archive
    @FXML
    private StackPane archive_NewExam;

    @FXML
    private Label lb_ExamName_ExamManagement;

    @FXML
    private Label lb_ExamID_ExamManagement;

    @FXML
    private TextField tf_ExanName_Comfirm_ExamManagement;

    // AnchorPane Create New Exam
    @FXML
    private AnchorPane AnchorPane_newExam_ExamManagement;

    @FXML
    private TextField tf_ExamName_newExam_ExamManagement;

    @FXML
    private ComboBox<?> ComboBox_SubejctName_newExam_ExamManagement;

    // AnchorPane Detail Exam
    @FXML
    private AnchorPane AnchorPane_detailExam_ExamManagement;

    @FXML
    private AnchorPane AnchorPane_viewAllSubmission_detailExam_ExamManagement;

    @FXML
    private AnchorPane AnchorPane_editExam_detailExam_ExamManagement;

    @FXML
    private AnchorPane AnchorPane_hostExam_detailExam_ExamManagement;

    // Data test
    Exam_card exam_card = new Exam_card("Cnpm", "Cnpm", "21/12/2004");
    Exam_card exam_card2 = new Exam_card("Cnpm", "Cnpm", "21/12/2004");

    List<Exam_card> listExamCards = List.of(exam_card, exam_card2);

    // Func Create New Exam
    @FXML
    void btn_newExam_Hidden_ExamManagement(ActionEvent event) {
        this.AnchorPane_newExam_ExamManagement.setVisible(true);
    }

    @FXML
    void btn_CreateExam_newExam_ExamManagement(ActionEvent event) {
        String examName = tf_ExamName_newExam_ExamManagement.getText();

        if (examName.length() == 0 || examName == null || examName == "") {
            Notification.Error("Error", "Please enter exam name");
            return;
        }

        if (examName.length() > 191) {
            Notification.Error("Error", "Exam name is too long");
            return;
        }

        Notification.Infomation("Success", "Create new exam successfully");

        Exam_card exam_card = new Exam_card(examName, "Cnpm", "21/12/2004");

        listExamCards.add(exam_card);

        add_Exam_FlowPane(exam_card);
    }

    // Add group for flowpane
    public void add_Exam_FlowPane(Exam_card exam_card) {
        Insets margin = new Insets(12, 12, 0, 0);

        FlowPane.setMargin(exam_card.getExam_Instance(), margin);

        this.flowpane_mainbody.getChildren().add(exam_card.getExam_Instance());
        exam_card.getDetails_btn().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                btn_details_Exam(event);
            }
        });

        exam_card.click_Button_Archive(archive_NewExam);
    }

    // Func detail Exam
    void btn_details_Exam(ActionEvent event) {
        AnchorPane_detailExam_ExamManagement.setVisible(true);
    }

    // Func Archive Exam
    @FXML
    void btn_ArchiveExam_ExamManagement(ActionEvent event) {
        String examName = tf_ExanName_Comfirm_ExamManagement.getText();
        if (examName.length() == 0 || examName == null || examName == "") {
            Notification.Error("Error", "Please enter exam name");
            return;
        }
        if (!examName.equalsIgnoreCase(lb_ExamName_ExamManagement.getText())) {
            Notification.Error("Error", "Exam name does not match");
            return;
        }

        // Func Archive Exam
        Notification.Infomation("Success", "Archive exam successfully");
    }

    @FXML
    void btn_cancel_ExamManagement(ActionEvent event) {
        this.archive_NewExam.setVisible(false);
    }

    // ===============================================//
    // Exam Detail FUNCTION
    @FXML
    void btn_detailQuestion_detailExam_ExamManagement(ActionEvent event) {

    }

    @FXML
    void btn_duplicateExam_detailExam_ExamManagement(ActionEvent event) {

    }

    // Edit Exam
    @FXML
    void btn_editExam_detailExam_ExamManagement(ActionEvent event) {
        AnchorPane_editExam_detailExam_ExamManagement.setVisible(true);
    }

    @FXML
    void btn_back_editExam_ExamManagement(ActionEvent event) {
        AnchorPane_editExam_detailExam_ExamManagement.setVisible(false);
    }

    // Host Exam
    @FXML
    void btn_hostExam_detailExam_ExamManagement(ActionEvent event) {
        AnchorPane_hostExam_detailExam_ExamManagement.setVisible(true);
    }

    @FXML
    void btn_back_hostExam_ExamManagement(ActionEvent event) {
        AnchorPane_hostExam_detailExam_ExamManagement.setVisible(false);
    }

    // View All Submission
    @FXML
    void btn_viewAllSubmission_detailExam_ExamManagement(ActionEvent event) {
        AnchorPane_viewAllSubmission_detailExam_ExamManagement.setVisible(true);
    }

    @FXML
    void btn_back_allSubmission_ExamManagement(ActionEvent event) {
        AnchorPane_viewAllSubmission_detailExam_ExamManagement.setVisible(false);
    }

    // ===============================================﻿//

    // Func back

    // Back Create New Exam
    @FXML
    void btn_back_newExam_ExamManagement(ActionEvent event) {
        this.AnchorPane_newExam_ExamManagement.setVisible(false);
    }

    // Back Detail Exam
    @FXML
    void btn_back_detailExam_ExamManagement(ActionEvent event) {
        AnchorPane_detailExam_ExamManagement.setVisible(false);
    }

    public void LoadListExan(List<Exam_card> list, StackPane archive_NewExam) {
        for (Exam_card exam : list) {
            add_Exam_FlowPane(exam);
        }
    }

    /* =============================================== */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // LoadListGroup & Set button details & button archive
        LoadListExan(listExamCards, archive_NewExam);
    }

}
