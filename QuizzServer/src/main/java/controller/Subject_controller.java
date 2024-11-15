package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import components.Group_card;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
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

    /* Subject Detail */
    @FXML
    private TextField tf_SearchQuestion_SubjectManagement;

    @FXML
    private TableView<?> table_Question_SubjectManagement;

    @FXML
    private Label lb_SubjectName_SubjectManagement;

    @FXML
    private Label lb_SubjectID_SubjectManagement;

    @FXML
    private Label lb_DateCreate_SubjectManagemennt;

    @FXML
    private Label lb_QuantityQuestion_SubjectManagement;

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

    // StackPane detail question
    @FXML
    private StackPane detailQuestion_SubjectManagement;

    @FXML
    private TextArea txtArea_ContentQuestion_detailQuestion_SubjectManagement;

    @FXML
    private CheckBox checkbox_MultipleAnswers_detailQuestion_SubjectManagement;

    // Anchor Result Search
    @FXML
    private AnchorPane AnchorPane_ResultSearchQuestion_SubjectManagement;

    // Anchor Subject Detail
    @FXML
    private AnchorPane AnchorPane_SubjectDetailQuestion_SubjectManagement;

    /* ================================================================ */

    // Data_test
    Group_card group = new Group_card("Group name", "GR0011", "2020-01-01");
    Group_card group2 = new Group_card("Group name", "GR0012", "2020-01-02");
    Group_card group3 = new Group_card("Group name", "GR0013", "2020-01-03");

    List<Group_card> group_list = List.of(group, group2, group3);

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
        // Func Create New Group
        Notification.Infomation("Success", "Create new subject successfully");
        Group_card group = new Group_card(subjectName, "ID", "Date created");

        add_Group_FlowPane(group);

        btn_cancel_Subject(event);
    }

    @FXML
    void btn_newSubject_Hidden(ActionEvent event) {
        this.create_NewSubject.setVisible(true);
    }

    // Func detail Group
    void btn_details_Group(ActionEvent event) {
        String url = "/ui/subject-detail+search-result.fxml";
        load_Scene_AnchorPane(event, url);
    }

    // Func archive subject
    @FXML
    void btn_archive_NewSubject(ActionEvent event) {
        String groupName = tf_Subject_ConfirmName_Archive.getText();
        if (groupName.length() == 0 || groupName == null || groupName == "") {
            Notification.Error("Error", "Please enter group name");
            return;
        }
        if (groupName.length() > 191) {
            Notification.Error("Error", "Group name is too long");
            return;
        }
        if (!groupName.equalsIgnoreCase(lb_Subject_Name_Archive.getText())) {
            Notification.Error("Error", "Group name does not match");
            return;
        }
        // Func Archive Group

        Notification.Infomation("Success", "Archive group successfully");

        btn_cancel_Subject(event);
    }

    @FXML
    void btn_ArchiveQuestion_SubjectManagement(ActionEvent event) {

    }

    @FXML
    void btn_Archive_SubjectManagement(ActionEvent event) {

    }

    // func shared
    @FXML
    void btn_cancel_Subject(ActionEvent event) {
        this.create_NewSubject.setVisible(false);
        this.archive_NewSubject.setVisible(false);
    }

    @FXML
    void btn_back_SubjectManagement(ActionEvent event) {
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
        this.detailQuestion_SubjectManagement.setVisible(true);
    }

    @FXML
    void btn_addAnswer_detailQuestion_SubjectManagement(ActionEvent event) {

    }

    @FXML
    void btn_SaveChange_detailQuestion_SubjectManagement(ActionEvent event) {

    }

    // Func Rename
    @FXML
    void btn_Rename_Hidden_SubjectManagement(ActionEvent event) {
        this.renameSubject_SubjectManagement.setVisible(true);
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
        if (!subjectName.equalsIgnoreCase(lb_SubjectName_Rename_SubjectManagement.getText())) {
            Notification.Error("Error", "Subject name does not match");
            return;
        }
        // Func Rename Group here

        Notification.Infomation("Success", "Rename subject successfully");
        btn_cancel_SubjectManagement(event);
    }

    // Func Add Question
    @FXML
    void btn_AddQuestion_Hidden_SubjectManagement(ActionEvent event) {
        this.addNewQuestion_SubjectManagement.setVisible(true);
    }

    @FXML
    void btn_addQuestion_SubjectMangement(ActionEvent event) {

    }

    @FXML
    void btn_addAnswer_addQuestion_SubjectManagement(ActionEvent event) {

    }

    // Func search subject
    @FXML
    void btn_SearchQuestion_SubjectManagement(ActionEvent event) {
        this.AnchorPane_SubjectDetailQuestion_SubjectManagement.setVisible(false);
        this.AnchorPane_ResultSearchQuestion_SubjectManagement.setVisible(true);
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
    void LoadListGroup(List<Group_card> list, StackPane archive_NewGroup) {
        for (Group_card group : list) {
            add_Group_FlowPane(group);
        }
    }

    // Add group for flowpane
    public void add_Group_FlowPane(Group_card group) {
        Insets margin = new Insets(12, 12, 0, 0);

        FlowPane.setMargin(group.getGroup_Instance(), margin);

        this.flowpane_mainbody.getChildren().add(group.getGroup_Instance());

        // Doing
        group.getDetails_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btn_details_Group(event);
            }
        });

        group.click_Button_Archive(archive_NewSubject);
    }

    // initialize
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // LoadListGroup & Set button details & button archive
        LoadListGroup(group_list, archive_NewSubject);
    }

    /* ================================================================ */
}
