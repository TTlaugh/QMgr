package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import components.Exam_card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import model.Exam;
import model.Question;
import model.Subject;
import services.ExamManager;
import services.QuestionManager;
import services.SubjectManager;
import utils.Notification;

public class Exam_controller implements Initializable {
    // FlowPane Main
    @FXML
    private FlowPane flowpane_mainbody;

    @FXML
    private ComboBox<Subject> ComboBox_Subject_AllExam = new ComboBox<Subject>();

    @FXML
    private ComboBox<String> ComboBox_Host_ExamAll = new ComboBox<String>();

    @FXML
    private Label lb_ExamConfirm_ExamManagement;

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
    private ComboBox<Subject> ComboBox_SubejctName_newExam_ExamManagement = new ComboBox<Subject>();

    @FXML
    private TableView<Question> tableView_QuestionBank_ExamManagement = new TableView<Question>();

    @FXML
    private TableColumn<Question, Integer> ID_ColumnQuestion_ExamManagement = new TableColumn<Question, Integer>();

    @FXML
    private TableColumn<Question, String> Question_ColumnQuestion_ExamManagement = new TableColumn<Question, String>();

    @FXML
    private TableView<Question> tableView_ExamQuestion_ExamManagement = new TableView<Question>();

    @FXML
    private TableColumn<Question, Integer> ID_ColumnQuestionExam_ExamManagement = new TableColumn<Question, Integer>();

    @FXML
    private TableColumn<Question, String> Question_ColumnQuestionExam_ExamManagement = new TableColumn<Question, String>();

    @FXML
    private Label lb_totalQuestionBank_ExamManagement = new Label();

    @FXML
    private Label lb_totalExamQuestion_ExamManagement = new Label();

    @FXML
    private TextArea txtArea_Description_ExamManagement = new TextArea();

    // AnchorPane Detail Exam
    @FXML
    private AnchorPane AnchorPane_detailExam_ExamManagement;

    @FXML
    private AnchorPane AnchorPane_viewAllSubmission_detailExam_ExamManagement;

    @FXML
    private AnchorPane AnchorPane_editExam_detailExam_ExamManagement;

    @FXML
    private AnchorPane AnchorPane_hostExam_detailExam_ExamManagement;

    /// =============================

    public static List<Question> listQuestions = new ArrayList<Question>();

    ExamManager examManager = ExamManager.getInstance();

    SubjectManager subjectManager = SubjectManager.getInstance();

    QuestionManager quesManager = QuestionManager.getInstance();

    Exam exam_Current_SubjectManagement = new Exam();

    Subject subject_Current_SubjectManagement = new Subject();

    int indexSelected_AnchorPane_in_FlowPane = 0;

    public static List<Exam> listExams = new ArrayList<Exam>();

    public List<Subject> listSubjects = subjectManager.getAllSubject(Workspace_controller.current_WorkSpaceID);

    public ObservableList<Question> observableList;

    void LoadComboBox_newExam() {
        ComboBox_SubejctName_newExam_ExamManagement.getItems().addAll(listSubjects);

        ComboBox_SubejctName_newExam_ExamManagement.setConverter(new StringConverter<Subject>() {
            @Override
            public String toString(Subject subject) {
                return subject == null ? "" : subject.getSubjectName();
            }

            @Override
            public Subject fromString(String s) {
                return null;
            }
        });

        subject_Current_SubjectManagement = null;
    }

    @FXML
    void setOnAction_ComboBoxnewExam(ActionEvent event) {
        subject_Current_SubjectManagement = ComboBox_SubejctName_newExam_ExamManagement.getValue();

        if (subject_Current_SubjectManagement != null) {
            listQuestions = quesManager.getAllQuestionsBySubject(subject_Current_SubjectManagement.getSubjectId());

            tableView_QuestionBank_ExamManagement.getItems().clear();

            tableView_QuestionBank_ExamManagement
                    .setItems(loadQuestion_tableViewQuestionBank_QuestionManagement(listQuestions));

            lb_totalQuestionBank_ExamManagement.setText(listQuestions.size() + " questions");
        }
    }

    @FXML
    void setOnAction_ComboBoxAllExams(ActionEvent event) {
        subject_Current_SubjectManagement = ComboBox_Subject_AllExam.getValue();

        this.flowpane_mainbody.getChildren().clear();

        if (subject_Current_SubjectManagement != null) {
            listExams = examManager.getAllExamsBySubject(subject_Current_SubjectManagement.getSubjectId());

            for (Exam exam : listExams) {
                add_Exam_FlowPane(new Exam_card(exam, subject_Current_SubjectManagement));
            }
        }
    }

    @FXML
    void setOnAction_ComboBox_HostExamAll(ActionEvent event) {
        String hosted = ComboBox_Host_ExamAll.getValue();

        System.out.println(ComboBox_Host_ExamAll.getValue());
        System.out.println(hosted);

        ArrayList<Exam> list = hosted == "Hosted" ? examManager.getAllExams_Hosted()
                : examManager.getAllExams_UnHosted();

        this.flowpane_mainbody.getChildren().clear();

        if (list == null)
            return;

        for (Exam exam : list)
            add_Exam_FlowPane(new Exam_card(exam, subject_Current_SubjectManagement));

    }

    public ObservableList<Question> loadQuestion_tableViewQuestionBank_QuestionManagement(List<Question> list) {

        observableList = FXCollections.observableArrayList(list);

        ID_ColumnQuestion_ExamManagement.setCellValueFactory(new PropertyValueFactory<Question, Integer>("questionId"));
        Question_ColumnQuestion_ExamManagement
                .setCellValueFactory(new PropertyValueFactory<Question, String>("content"));

        return observableList;

    }

    public ObservableList<Question> loadQuestion_tableViewQuestionExam_QuestionManagement(List<Question> list) {

        observableList = FXCollections.observableArrayList(list);

        ID_ColumnQuestionExam_ExamManagement
                .setCellValueFactory(new PropertyValueFactory<Question, Integer>("questionId"));
        Question_ColumnQuestionExam_ExamManagement
                .setCellValueFactory(new PropertyValueFactory<Question, String>("content"));

        return observableList;

    }

    // Func Create New Exam
    @FXML
    void btn_newExam_Hidden_ExamManagement(ActionEvent event) {

        this.AnchorPane_newExam_ExamManagement.setVisible(true);

        LoadComboBox_newExam();

    }

    @FXML
    void btn_CreateExam_newExam_ExamManagement(ActionEvent event) {
        String examName = tf_ExamName_newExam_ExamManagement.getText();
        String desc = txtArea_Description_ExamManagement.getText();

        if (examName.length() == 0 || examName == null || examName == "") {
            Notification.Error("Error", "Please enter exam name");
            return;
        }

        if (examName.length() > 191) {
            Notification.Error("Error", "Exam name is too long");
            return;
        }
        if (subject_Current_SubjectManagement == null) {
            Notification.Error("Error", "Please choose subject");
            return;
        }
        if (tableView_ExamQuestion_ExamManagement.getItems().size() == 0) {
            Notification.Error("Error", "Please choose question");
            return;
        }

        List<Integer> listQuestionIds = new ArrayList<Integer>();

        for (Question question : tableView_ExamQuestion_ExamManagement.getItems()) {
            listQuestionIds.add(question.getQuestionId());
        }

        Exam exam_current = new Exam(0,
                subject_Current_SubjectManagement.getSubjectId(), examName, desc, listQuestionIds, false);

        boolean isCreateSuccess = examManager.createExam(exam_current);

        if (!isCreateSuccess) {
            Notification.Error("Error", "Create new exam failed");
            return;
        }

        Notification.Infomation("Success", "Create new exam successfully");

        Exam_card exam_card = new Exam_card(exam_current, subject_Current_SubjectManagement);

        add_Exam_FlowPane(exam_card);
    }

    @FXML
    void btn_ReturnExamQuesFromQuesBank(ActionEvent event) {
        ObservableList<Question> questionReturnQuestionBank = tableView_ExamQuestion_ExamManagement
                .getSelectionModel()
                .getSelectedItems();

        if (questionReturnQuestionBank == null) {
            Notification.Error("Error", "Please choose question");
            return;
        }

        tableView_ExamQuestion_ExamManagement
                .getItems().removeAll(questionReturnQuestionBank);
        int total = listQuestions.size() - questionReturnQuestionBank.size();
        lb_totalExamQuestion_ExamManagement.setText(total + " questions");
    }

    @FXML
    void btn_SelectQuestionBankToExamQues(ActionEvent event) {
        ObservableList<Question> questionSelectInQuestionBank = tableView_QuestionBank_ExamManagement
                .getSelectionModel()
                .getSelectedItems();

        if (questionSelectInQuestionBank == null) {
            Notification.Error("Error", "Please choose question");
            return;
        }

        tableView_ExamQuestion_ExamManagement
                .setItems(loadQuestion_tableViewQuestionExam_QuestionManagement(questionSelectInQuestionBank));

        lb_totalExamQuestion_ExamManagement.setText(questionSelectInQuestionBank.size() + " questions");

    }

    // Add group for flowpane
    public void add_Exam_FlowPane(Exam_card exam_card) {
        Insets margin = new Insets(12, 12, 0, 0);

        FlowPane.setMargin(exam_card.getExam_Instance(), margin);

        this.flowpane_mainbody.getChildren().add(exam_card.getExam_Instance());

        setAction_Archive(exam_card);
        setAction_Details(exam_card);

    }

    void setAction_Details(Exam_card exam) {
        exam.getDetails_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                exam_Current_SubjectManagement = exam.getExam();

                listSubjects = subjectManager.getAllSubject(Workspace_controller.current_WorkSpaceID);

                btn_detailQuestion_detailExam_ExamManagement(event);
            }
        });
    }

    void setAction_Archive(Exam_card exam) {
        exam.getArchive_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                archive_NewExam.setVisible(true);

                exam_Current_SubjectManagement = exam.getExam();

                indexSelected_AnchorPane_in_FlowPane = flowpane_mainbody.getChildren()
                        .indexOf(exam.getExam_Instance());

                if (archive_NewExam.isVisible()) {
                    lb_ExamName_ExamManagement.setText("Exam Name : " + exam_Current_SubjectManagement.getName());
                    lb_ExamID_ExamManagement.setText("ID : #"
                            + (exam_Current_SubjectManagement.getExamId() >= 10 ? exam_Current_SubjectManagement
                                    .getExamId() : "0" + exam_Current_SubjectManagement.getExamId()));
                    lb_ExamConfirm_ExamManagement
                            .setText("To confirm, type \"" + exam_Current_SubjectManagement.getName()
                                    + "\" in the box below");

                    tf_ExanName_Comfirm_ExamManagement.clear();
                }

            }
        });
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

    public void LoadListExam() {

        ArrayList<Exam> listExam_Current = new ArrayList<Exam>();
        for (Subject subject : listSubjects) {
            listExam_Current = examManager.getAllExamsBySubject(subject.getSubjectId());

            for (Exam exam : listExam_Current) {
                listExams.add(exam);

                Subject subject_current = subjectManager.getSubject(exam.getSubjectId());
                add_Exam_FlowPane(new Exam_card(exam, subject_current));
            }
        }

    }

    public void LoadComboBox_AllExams() {
        ComboBox_Subject_AllExam.getItems().addAll(listSubjects);

        ComboBox_Subject_AllExam.setConverter(new StringConverter<Subject>() {
            @Override
            public String toString(Subject subject) {
                return subject == null ? "" : subject.getSubjectName();
            }

            @Override
            public Subject fromString(String s) {
                return null;
            }
        });
    }

    public void LoadComboBox_HostExamAll() {
        ComboBox_Host_ExamAll.getItems().addAll("Un-Hosted", "Hosted");
    }

    /* =============================================== */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // LoadListGroup & Set button details & button archive
        LoadListExam();

        LoadComboBox_AllExams();

        LoadComboBox_HostExamAll();

        tableView_QuestionBank_ExamManagement.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView_ExamQuestion_ExamManagement.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}
