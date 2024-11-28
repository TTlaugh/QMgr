package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import components.Answer_card;
import components.Subject_card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Answer;
import model.Question;
import model.Subject;
import services.QuestionManager;
import services.SubjectManager;
import utils.CheckCheckBox;
import utils.Notification;
import utils.OpenFileExplorer;

public class Subject_controller implements Initializable {

    // FlowPane
    @FXML
    private FlowPane flowpane_mainbody = new FlowPane();

    // StackPane create_NewSubject
    @FXML
    private StackPane create_NewSubject;

    @FXML
    private TextField tf_SubjectName_CreateSubject;

    // StackPane archive_NewSubject
    @FXML
    private StackPane archive_NewSubject;

    @FXML
    private TextField tf_Subject_ConfirmName_Archive;

    @FXML
    private Label lb_Subject_Name_Archive;

    @FXML
    private Label lb_Subject_ID_Archive;

    @FXML
    private Label lb_SubjectConfirm_Archive_SubjectManagement;

    /* Subject Detail */
    @FXML
    private TextField tf_SearchQuestion_SubjectManagement;

    @FXML
    private TableView<Question> table_Question_SubjectManagement = new TableView<Question>();

    @FXML
    private TableColumn<Question, Integer> ID_TableColumn_subjectDetail = new TableColumn<Question, Integer>();

    @FXML
    private TableColumn<Question, String> Question_TableColumn_subjectDetail = new TableColumn<Question, String>();

    @FXML
    private Label lb_SubjectName_SubjectManagement = new Label();

    @FXML
    private Label lb_SubjectID_SubjectManagement = new Label();

    @FXML
    private Label lb_DateCreate_SubjectManagemennt = new Label();

    @FXML
    private Label lb_QuantityQuestion_SubjectManagement = new Label();

    @FXML
    private TableView<Question> table_Search_SubjectManagement = new TableView<Question>();

    @FXML
    private TableColumn<Question, Integer> ID_TableColumnSearch_subjectDetail = new TableColumn<Question, Integer>();

    @FXML
    private TableColumn<Question, String> Question_TableColumnSearch_subjectDetail = new TableColumn<Question, String>();
    // StackPane rename subject
    @FXML
    private StackPane renameSubject_SubjectManagement;

    @FXML
    private Label lb_SubjectName_Rename_SubjectManagement;

    @FXML
    private Label lb_SubjectID_Rename_SubjectManagement;

    @FXML
    private TextField tf_SubjectName_Rename_SubjectManagement;

    // StackPane add new question
    @FXML
    private StackPane addNewQuestion_SubjectManagement;

    @FXML
    private TextArea txtArea_ContentQuestion_addQuestion_SubjectManagement;

    @FXML
    private CheckBox checkbox_MultipleAnswers_addQuestion_SubjectManagement;

    @FXML
    private VBox VBox_addQuestion_SubjectManagement;

    // StackPane detail question
    @FXML
    private StackPane detailQuestion_SubjectManagement;

    @FXML
    private TextArea txtArea_ContentQuestion_detailQuestion_SubjectManagement;

    @FXML
    private CheckBox checkbox_MultipleAnswers_detailQuestion_SubjectManagement;

    @FXML
    private VBox VBox_detailQuestion_SubjectManagement;
    // Anchor Result Search
    @FXML
    private AnchorPane AnchorPane_ResultSearchQuestion_SubjectManagement;

    // Anchor Subject Detail
    @FXML
    private AnchorPane AnchorPane_SubjectDetailQuestion_SubjectManagement;

    @FXML
    private Label lb_totalQuestion_SubjectManagement = new Label();

    // Load List HBOx
    List<HBox> listHBox = new java.util.ArrayList<>();

    // Subject Manager
    public ObservableList<Question> observableList;

    private SubjectManager subjectManager = SubjectManager.getInstance();

    private QuestionManager questionManager = QuestionManager.getInstance();

    private Question questionSelected;

    private int indexSelected_Question_in_TableView;

    public static List<Question> question_list;

    private List<Subject> subject_list = subjectManager.getAllSubject(Workspace_controller.current_WorkSpaceID);

    private int CountSubjectID_Current_StudentManagement = subject_list.get(subject_list.size() - 1).getSubjectId() + 1;

    private int indexSelected_AnchorPane_in_FlowPane = 0;

    public static Subject subject_Current_SubjectManagement = new Subject();

    public static int totalQuestion = 0;

    public boolean checkChoiceAnswer = false;

    // func create new subject
    @FXML
    void btn_createSubject_NewSubject(ActionEvent event) {
        String subjectName = tf_SubjectName_CreateSubject.getText();

        if (subjectName.length() == 0 || subjectName == null || subjectName == "") {
            Notification.Error("Error", "Please enter subject name");
            return;
        }
        if (subjectName.length() > 191) {
            Notification.Error("Error", "Subject name is too long");
            return;
        }

        Subject subject_current = new Subject(CountSubjectID_Current_StudentManagement,
                Workspace_controller.current_WorkSpaceID, subjectName,
                java.time.LocalDateTime.now(), false);

        boolean isCreateSuccess = subjectManager.createSubject(subject_current);

        if (!isCreateSuccess) {
            Notification.Error("Error", "Create subject failed");
            return;
        }

        subject_list.add(subject_current);

        // Func Create New Group
        Notification.Infomation("Success", "Create new subject successfully");

        Subject_card subject_card_current = new Subject_card(subject_current);

        add_Group_FlowPane(subject_card_current);

        btn_cancel_Subject(event);
    }

    @FXML
    void btn_newSubject_Hidden(ActionEvent event) {
        this.create_NewSubject.setVisible(true);
    }

    // Func detail Group
    void btn_details_Group(ActionEvent event) {
        if (question_list != null) {
            String url = "/ui/subject-detail+search-result.fxml";
            load_Scene_AnchorPane(event, url);
        }
    }

    // Func archive subject
    @FXML
    void btn_archive_NewSubject(ActionEvent event) {
        String subjectName = tf_Subject_ConfirmName_Archive.getText();
        if (subjectName.length() == 0 || subjectName == null || subjectName == "") {
            Notification.Error("Error", "Please enter group name");
            return;
        }
        if (subjectName.length() > 191) {
            Notification.Error("Error", "Group name is too long");
            return;
        }
        if (!subjectName.equalsIgnoreCase(lb_Subject_Name_Archive.getText())) {
            Notification.Error("Error", "Group name does not match");
            return;
        }

        boolean isArchiveSuccess = subjectManager.deleteSubject(subject_Current_SubjectManagement.getSubjectId());

        if (!isArchiveSuccess) {
            Notification.Error("Error", "Archive subject failed");
            return;
        }
        // Func Archive Group

        Notification.Infomation("Success", "Archive group successfully");

        this.flowpane_mainbody.getChildren().remove(indexSelected_AnchorPane_in_FlowPane);

        subject_list.remove(subject_Current_SubjectManagement);

        btn_cancel_Subject(event);
    }

    void archiveQuestion_SubjectManagement(TableView<Question> tableView) {

        questionSelected = tableView.getSelectionModel().getSelectedItem();

        indexSelected_Question_in_TableView = tableView.getSelectionModel().getSelectedIndex();

        if (questionSelected == null) {
            Notification.Error("Error", "Please choose question");
            return;
        }

        if (!questionManager.deleteQuestion(questionSelected.getQuestionId())) {
            Notification.Error("Error", "Delete question failed");
            return;
        }
        totalQuestion--;

        lb_QuantityQuestion_SubjectManagement
                .setText(totalQuestion + " questions");
        Notification.Infomation("Success", "Delete question successfully");

        tableView.getItems().remove(indexSelected_Question_in_TableView);
    }

    @FXML
    void btn_ArchiveQuestion_SubjectManagement(ActionEvent event) {
        if (AnchorPane_SubjectDetailQuestion_SubjectManagement.isVisible()) {
            archiveQuestion_SubjectManagement(table_Question_SubjectManagement);
            return;
        }
        archiveQuestion_SubjectManagement(table_Search_SubjectManagement);
    }

    @FXML
    void btn_Archive_SubjectManagement(ActionEvent event) {
        boolean isArchiveSuccess = subjectManager.deleteSubject(subject_Current_SubjectManagement.getSubjectId());

        if (!isArchiveSuccess) {
            Notification.Error("Error", "Archive subject failed");
            return;
        }

        Notification.Infomation("Success", "Archive subject successfully");

        btn_back_SubjectManagement(event);

        setAction_Archive(new Subject_card(subject_Current_SubjectManagement));
    }

    // func shared
    @FXML
    void btn_cancel_Subject(ActionEvent event) {
        this.create_NewSubject.setVisible(false);
        this.archive_NewSubject.setVisible(false);
    }

    @FXML
    void btn_back_SubjectManagement(ActionEvent event) {
        if (!AnchorPane_SubjectDetailQuestion_SubjectManagement.isVisible()) {

            AnchorPane_ResultSearchQuestion_SubjectManagement.setVisible(false);

            AnchorPane_SubjectDetailQuestion_SubjectManagement.setVisible(true);

            table_Search_SubjectManagement.toFront();

            Subject_controller.question_list = questionManager
                    .getAllQuestionsBySubject(subject_Current_SubjectManagement.getSubjectId());

            LoadListQuestion();
            return;
        }

        String url = "/ui/subject-management.fxml";

        load_Scene_AnchorPane(event, url);

    }

    // Cancel subject management (Detail )
    @FXML
    void btn_cancel_SubjectManagement(ActionEvent event) {
        this.renameSubject_SubjectManagement.setVisible(false);
        this.addNewQuestion_SubjectManagement.setVisible(false);
        this.detailQuestion_SubjectManagement.setVisible(false);
    }

    // Func Question detail

    @FXML
    void btn_QuestionDetail_Hidden_SubjectManagement(ActionEvent event) {

        if (AnchorPane_SubjectDetailQuestion_SubjectManagement.isVisible())
            questionSelected = table_Question_SubjectManagement.getSelectionModel().getSelectedItem();
        else
            questionSelected = table_Search_SubjectManagement.getSelectionModel().getSelectedItem();

        if (questionSelected == null) {
            Notification.Error("Error", "Please choose question");
            return;
        }

        this.VBox_detailQuestion_SubjectManagement.getChildren().clear();

        txtArea_ContentQuestion_detailQuestion_SubjectManagement.setText(questionSelected.getContent());

        this.detailQuestion_SubjectManagement.setVisible(true);

        VBox_detailQuestion_SubjectManagement.getChildren().clear();

        for (Answer answer : questionSelected.getAnswers()) {

            Answer_card answer_card = new Answer_card(answer);

            setEventButtonDelete(answer_card);

            setEventCheckbox(answer_card, VBox_detailQuestion_SubjectManagement);

            VBox_detailQuestion_SubjectManagement.getChildren().add(answer_card.getHBox());
        }
    }

    @FXML
    void btn_addAnswer_detailQuestion_SubjectManagement(ActionEvent event) {

        Answer_card answer_card = new Answer_card(new Answer());

        setEventButtonDelete(answer_card);

        VBox_detailQuestion_SubjectManagement.getChildren().add(answer_card.getHBox());
    }

    public ArrayList<Answer> getAnswers(VBox vbox) {

        ArrayList<Answer> answers = new ArrayList<>();

        for (Node node : vbox.getChildren()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                Answer answer = new Answer();

                String content = ((TextField) hBox.getChildren().get(1)).getText();
                boolean isCorrect = ((CheckBox) hBox.getChildren().get(0)).isSelected();

                answer.setContent(content);
                answer.setCorrect(isCorrect);

                answers.add(answer);
            }
        }

        return answers;

    }

    @FXML
    void setOnActionMultiple_Detail(ActionEvent event) {
        CheckCheckBox.checkMultipleCheckBox(VBox_detailQuestion_SubjectManagement,
                checkbox_MultipleAnswers_detailQuestion_SubjectManagement);
    }

    @FXML
    void setOnActionMultiple_Add(ActionEvent event) {
        CheckCheckBox.checkMultipleCheckBox(VBox_addQuestion_SubjectManagement,
                checkbox_MultipleAnswers_addQuestion_SubjectManagement);
    }

    @FXML
    void btn_SaveChange_detailQuestion_SubjectManagement(ActionEvent event) {
        String contentQuestion = txtArea_ContentQuestion_detailQuestion_SubjectManagement.getText();

        if (contentQuestion.length() == 0 || contentQuestion == null || contentQuestion == "") {
            Notification.Error("Error", "Please enter content question");
            return;
        }
        if (contentQuestion.length() > 1000) {
            Notification.Error("Error", "Content question is too long");
            return;
        }

        ArrayList<Answer> answers = getAnswers(VBox_detailQuestion_SubjectManagement);

        if (answers == null) {
            Notification.Error("Error", "Please enter answers");
            return;
        }

        if (answers.size() < 2) {
            Notification.Error("Error", "Please enter at least 2 answers");
            return;
        }

        if (CheckCheckBox.checkChoiceNoOrOne(VBox_detailQuestion_SubjectManagement)) {
            Notification.Error("Error", "Please choose correct answer");
            return;
        }

        questionSelected.setContent(contentQuestion);

        questionSelected.setAnswers(getAnswers(VBox_detailQuestion_SubjectManagement));

        boolean isUpdateSuccess = questionManager.updateQuestion(questionSelected);

        if (!isUpdateSuccess) {
            Notification.Error("Error", "Update question failed");
            return;
        }

        boolean uiUse = AnchorPane_SubjectDetailQuestion_SubjectManagement.isVisible();

        if (uiUse)
            table_Question_SubjectManagement.getItems().set(indexSelected_Question_in_TableView, questionSelected);
        else
            table_Search_SubjectManagement.getItems().set(indexSelected_Question_in_TableView, questionSelected);

        Notification.Infomation("Success", "Update question successfully");

        btn_cancel_SubjectManagement(event);

    }

    @FXML
    void btn_QuestionDetail_ResultSearch_Hidden_SubjectManagement(ActionEvent event) {
        this.detailQuestion_SubjectManagement.toFront();
        this.detailQuestion_SubjectManagement.setVisible(true);

        questionSelected = table_Search_SubjectManagement.getSelectionModel().getSelectedItem();

        if (questionSelected == null) {
            Notification.Error("Error", "Please choose question");
            return;
        }

        txtArea_ContentQuestion_detailQuestion_SubjectManagement.setText(questionSelected.getContent());

        VBox_detailQuestion_SubjectManagement.getChildren().clear();

        for (Answer answer : questionSelected.getAnswers()) {
            Answer_card answer_card = new Answer_card(answer);
            VBox_detailQuestion_SubjectManagement.getChildren().add(answer_card.getHBox());
        }
    }

    // Func Rename
    @FXML
    void btn_Rename_Hidden_SubjectManagement(ActionEvent event) {
        this.renameSubject_SubjectManagement.setVisible(true);

        tf_SubjectName_Rename_SubjectManagement.clear();

        lb_SubjectName_Rename_SubjectManagement.setText(subject_Current_SubjectManagement.getSubjectName());
        lb_SubjectID_Rename_SubjectManagement.setText("ID : #"
                + (subject_Current_SubjectManagement.getSubjectId() >= 10
                        ? subject_Current_SubjectManagement.getSubjectId()
                        : "0" + subject_Current_SubjectManagement.getSubjectId()));

    }

    @FXML
    void btn_Rename_SubjectManagement(ActionEvent event) {
        String subjectName = tf_SubjectName_Rename_SubjectManagement.getText();

        if (subjectName.length() == 0 || subjectName == null || subjectName == "") {
            Notification.Error("Error", "Please enter subject name");
            return;
        }
        if (subjectName.length() > 191) {
            Notification.Error("Error", "Subject name is too long");
            return;
        }

        // set subject name
        subject_Current_SubjectManagement.setSubjectName(subjectName);

        // Func Rename Group here
        boolean isRenameSuccess = subjectManager.updateSubject(subject_Current_SubjectManagement);
        if (!isRenameSuccess) {
            Notification.Error("Error", "Rename subject failed");
            return;
        }

        lb_SubjectName_SubjectManagement.setText(subjectName);

        Notification.Infomation("Success", "Rename subject successfully");

        btn_cancel_SubjectManagement(event);

    }

    // Func Add Question
    @FXML
    void btn_AddQuestion_Hidden_SubjectManagement(ActionEvent event) {
        VBox_addQuestion_SubjectManagement.getChildren().clear();
        this.addNewQuestion_SubjectManagement.setVisible(true);
    }

    @FXML
    void btn_addQuestion_SubjectMangement(ActionEvent event) {
        String content = txtArea_ContentQuestion_addQuestion_SubjectManagement.getText();

        if (content.length() == 0 || content == null || content == "") {
            Notification.Error("Error", "Please enter content question");
            return;
        }
        if (content.length() > 1000) {
            Notification.Error("Error", "Content question is too long");
            return;
        }

        ArrayList<Answer> answers = getAnswers(VBox_addQuestion_SubjectManagement);

        if (answers == null) {
            Notification.Error("Error", "Please enter answers");
            return;
        }

        if (answers.size() < 2) {
            Notification.Error("Error", "Please enter at least 2 answers");
            return;
        }

        if (CheckCheckBox.checkChoiceNoOrOne(VBox_addQuestion_SubjectManagement)) {
            Notification.Error("Error", "Please choose correct answer");
            return;
        }

        int subjectId = subject_Current_SubjectManagement.getSubjectId();

        Question newQuestion = new Question(0, subjectId, "Chapter1", 1, content, answers, false);

        boolean isCreateSuccess = questionManager.createQuestion(newQuestion);

        if (!isCreateSuccess) {
            Notification.Error("Error", "Create question failed");
            return;
        }
        int questionid = questionManager.getAllQuestions().size();

        newQuestion = questionManager.getQuestion(questionid);

        table_Question_SubjectManagement.getItems().add(newQuestion);

        Notification.Infomation("Success", "Create question successfully");

        btn_cancel_SubjectManagement(event);
    }

    @FXML
    void btn_addAnswer_addQuestion_SubjectManagement(ActionEvent event) {

        Answer_card answer_card = new Answer_card(new Answer());

        setEventButtonDelete(answer_card);

        setEventCheckbox(answer_card, VBox_addQuestion_SubjectManagement);

        VBox_addQuestion_SubjectManagement.getChildren().add(answer_card.getHBox());

    }

    void setEventButtonDelete(Answer_card answer_card) {
        answer_card.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (addNewQuestion_SubjectManagement.isVisible()) {
                    VBox_addQuestion_SubjectManagement.getChildren().remove(answer_card.getHBox());
                    return;
                }

                VBox_detailQuestion_SubjectManagement.getChildren().remove(answer_card.getHBox());
            }
        });
    }

    void setEventCheckbox(Answer_card answer_card, VBox vbox) {
        answer_card.getCheckBox().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isMultiple;

                boolean uiUse = addNewQuestion_SubjectManagement.isVisible();

                if (uiUse)
                    isMultiple = checkbox_MultipleAnswers_addQuestion_SubjectManagement.isSelected();
                else
                    isMultiple = checkbox_MultipleAnswers_detailQuestion_SubjectManagement.isSelected();

                boolean isCheckBox = CheckCheckBox.checkTextBox(vbox);

                if (!isMultiple && !isCheckBox) {
                    answer_card.getCheckBox().setSelected(false);
                    Notification.Error("Error", "Only one answer is allowed");
                    return;
                }
            }
        });
    }

    // Func search subject
    @FXML
    void btn_SearchQuestion_SubjectManagement(ActionEvent event) {
        this.AnchorPane_SubjectDetailQuestion_SubjectManagement.setVisible(false);
        this.AnchorPane_ResultSearchQuestion_SubjectManagement.setVisible(true);

        String keyWord = tf_SearchQuestion_SubjectManagement.getText();

        if (keyWord.length() == 0 || keyWord == null || keyWord == "") {
            Notification.Error("Error", "Please enter keyword");
            return;
        }
        if (keyWord.length() > 191) {
            Notification.Error("Error", "Keyword is too long");
            return;
        }

        // Func Search Question
        ArrayList<Question> listQuestion = questionManager.searchQuestions(subject_Current_SubjectManagement
                .getSubjectId(), keyWord);

        if (listQuestion.size() == 0) {
            Notification.Error("Error", "Not found question");
            return;
        }

        lb_totalQuestion_SubjectManagement.setText(listQuestion.size() + " questions");

        table_Search_SubjectManagement.getItems().clear();

        table_Search_SubjectManagement.setItems(loadQuestion_tableViewQuestion_QuestionManagement(listQuestion));

    }

    // Export Question
    @FXML
    void btn_ExportQuestion_SubjectManagement(ActionEvent event) {
        File file_Current = OpenFileExplorer.Save(event);

        if (file_Current == null) {
            Notification.Error("Error", "Please choose file");
            return;
        }
        String fileNameExel = file_Current.getPath();
        // Boolean check_ExportExel = quesManager.exportQuestions(subject_Current,
        // fileNameExel);
        // if (!check_ExportExel) {
        // Notification.Infomation("Error", "Export file failed");
        // return;
        // }
        if (Notification.Comfrim("Confirm",
                "Do you want to open file?").getResult() == ButtonType.YES)
            OpenFileExel_Export(new File(fileNameExel));
    }

    // Import Question
    @FXML
    boolean btn_ImportQuestion_SubjectManagement(ActionEvent event) {
        File file_Current = OpenFileExplorer.Open(event);
        if (file_Current != null) {
            // String check_xlsx =
            // file_Current.getPath().substring(file_Current.getPath().lastIndexOf(".") +
            // 1);
            // try {
            // Boolean check_FormatExel = check_xlsx.equalsIgnoreCase("xlsx") ||
            // check_xlsx.equalsIgnoreCase("xls");
            // Boolean selectFile = quesManager.importQuestions(subject_Current,
            // file_Current.getPath());
            // if (!check_FormatExel && !selectFile) {
            // Notification.Error("Error", "Please choose file excel");
            // return false;
            // }
            // tableView_Question.getItems().clear();
            Notification.Infomation("Success", "Import file successfully");
            // } catch (SQLException e) {
            // e.printStackTrace();
            // }
        }
        // question_ListOfSubject = quesManager.getQuestionsForSubject(subject_Current);

        // tableView_Question.setItems(loadQuestion_tableView(question_ListOfSubject));

        return true;
    }

    // Open File
    private void OpenFileExel_Export(File fileOpen) {
        try {
            Desktop.getDesktop().open(fileOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load Scene
    public void load_Scene_AnchorPane(ActionEvent event, String url_ui) {
        AnchorPane insidePane;
        try {
            insidePane = new FXMLLoader(
                    getClass().getResource(url_ui))
                    .load();
            new Screencontainer_controller().setAnchor(insidePane);
            Scene scene = (Scene) ((Node) event.getSource()).getScene();
            AnchorPane anchor = (AnchorPane) scene.lookup("#mainbody");
            anchor.getChildren().clear();
            anchor.getChildren().add(insidePane);
        } catch (IOException e) {
            System.out.println("Error in loading subject_controller load_Scene_AnchorPane");
            e.printStackTrace();
        }

    }

    // Load List Group
    void LoadListGroup() {
        for (Subject subject : subject_list) {

            Subject_card subject_card = new Subject_card(subject);

            add_Group_FlowPane(subject_card);
        }
    }

    void setAction_Archive(Subject_card subject) {
        subject.getArchive_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                archive_NewSubject.setVisible(true);

                tf_Subject_ConfirmName_Archive.clear();

                subject_Current_SubjectManagement = subject.getSubject();

                indexSelected_AnchorPane_in_FlowPane = flowpane_mainbody.getChildren()
                        .indexOf(subject.getGroup_Instance());

                if (archive_NewSubject.isVisible()) {
                    lb_Subject_ID_Archive
                            .setText("Subject ID :" + subject_Current_SubjectManagement.getSubjectId() + "");
                    lb_Subject_Name_Archive.setText(subject_Current_SubjectManagement.getSubjectName());
                    lb_SubjectConfirm_Archive_SubjectManagement.setText(
                            "To confirm, type \"" + subject_Current_SubjectManagement.getSubjectName()
                                    + "\" in the box below");
                }

            }
        });
    }

    void setAction_Details(Subject_card subject) {
        subject.getDetails_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // System.out.println("Details");

                subject_Current_SubjectManagement = subject.getSubject();

                Subject_controller.question_list = questionManager
                        .getAllQuestionsBySubject(subject_Current_SubjectManagement.getSubjectId());

                Subject_controller.totalQuestion = question_list.size();

                btn_details_Group(event);
            }
        });
    }

    // Add group for flowpane
    public void add_Group_FlowPane(Subject_card subject) {
        Insets margin = new Insets(12, 12, 0, 0);

        FlowPane.setMargin(subject.getGroup_Instance(), margin);

        this.flowpane_mainbody.getChildren().add(subject.getGroup_Instance());

        // setAction_Archive_Details(subject);

        setAction_Archive(subject);
        setAction_Details(subject);
    }

    // Load List Question
    void LoadListQuestion() {

        // System.out.println(loadQuestion_tableViewQuestion_QuestionManagement(Subject_controller.question_list));

        table_Question_SubjectManagement.getItems().clear();

        table_Question_SubjectManagement
                .setItems(loadQuestion_tableViewQuestion_QuestionManagement(Subject_controller.question_list));

        lb_SubjectName_SubjectManagement.setText(subject_Current_SubjectManagement.getSubjectName());
        lb_SubjectID_SubjectManagement
                .setText(
                        "ID : #"
                                + (subject_Current_SubjectManagement.getSubjectId() >= 10
                                        ? subject_Current_SubjectManagement
                                                .getSubjectId()
                                        : "0" + subject_Current_SubjectManagement.getSubjectId()));

        lb_DateCreate_SubjectManagemennt.setText(subject_Current_SubjectManagement.getCreateDate()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        lb_QuantityQuestion_SubjectManagement.setText(totalQuestion + " questions");

    }

    // Load ObservableList
    public ObservableList<Question> loadQuestion_tableViewQuestion_QuestionManagement(List<Question> list) {

        observableList = FXCollections.observableArrayList(list);

        ID_TableColumn_subjectDetail.setCellValueFactory(new PropertyValueFactory<Question, Integer>("questionId"));
        Question_TableColumn_subjectDetail.setCellValueFactory(new PropertyValueFactory<Question, String>("content"));

        return observableList;

    }

    // initialize
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // LoadListGroup & Set button details & button archive

        LoadListGroup();

        if (question_list != null) {
            LoadListQuestion();
        }

    }

    /* ================================================================ */
}
