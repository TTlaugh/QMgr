package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import components.Group_card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import model.Student_Model;
import utils.Notification;

public class Student_controller implements Initializable {

    // FlowPane
    @FXML
    private FlowPane flowpane_mainbody = new FlowPane();

    // StackPane Create New Group
    @FXML
    private StackPane create_NewGroup = new StackPane();

    @FXML
    private TextField tf_GroupName_CreateGroup;

    // StackPane Archive Group

    @FXML
    private StackPane archive_NewGroup = new StackPane();

    @FXML
    private Label lb_Group_Name_Archive;

    @FXML
    private Label lb_Group_ID_Archive;

    @FXML
    private TextField tf_Group_ConfirmName_Archive;

    // StackPane Change Group
    @FXML
    private StackPane change_NewGroup = new StackPane();

    @FXML
    private ComboBox<?> ComboBox_NewGroup_ChangeWorkSpace;

    @FXML
    private TextField tf_Group_Name_ChangeWorkSpace;

    // Anchor Pane Group Detail
    // @FXML
    // private AnchorPane Group_DeTail_Student_Hidden;

    // Data_test
    Group_card group = new Group_card("Group name", "GR0011", "2020-01-01");
    Group_card group2 = new Group_card("Group name", "GR0012", "2020-01-02");
    Group_card group3 = new Group_card("Group name", "GR0013", "2020-01-03");

    List<Group_card> group_list = List.of(group, group2, group3);

    Student_Model student = new Student_Model();
    Student_Model student2 = new Student_Model();
    Student_Model student3 = new Student_Model();

    List<Student_Model> student_list = List.of(student, student2, student3);

    // Func Create New Group
    @FXML
    void btn_newGroup_Hidden(ActionEvent event) {
        this.create_NewGroup.setVisible(true);
    }

    @FXML
    void btn_createGroup_NewGroup(ActionEvent event) {
        String groupName = tf_GroupName_CreateGroup.getText();
        if (groupName.length() == 0 || groupName == null || groupName == "") {
            Notification.Error("Error", "Please enter group name");
            return;
        }
        if (groupName.length() > 191) {
            Notification.Error("Error", "Group name is too long");
            return;
        }
        // Func Create New Group
        Notification.Infomation("Success", "Create new group successfully");
        Group_card group = new Group_card(groupName, "ID", "Date created");
        this.flowpane_mainbody.getChildren().add(group.getGroup_Instance());

        btn_cancel_Group(event);
    }

    // Func Archive Group

    @FXML
    void btn_archive_NewGroup(ActionEvent event) {
        String groupName = tf_Group_ConfirmName_Archive.getText();
        if (groupName.length() == 0 || groupName == null || groupName == "") {
            Notification.Error("Error", "Please enter group name");
            return;
        }
        if (groupName.length() > 191) {
            Notification.Error("Error", "Group name is too long");
            return;
        }
        if (!groupName.equalsIgnoreCase(lb_Group_Name_Archive.getText())) {
            Notification.Error("Error", "Group name does not match");
            return;
        }
        // Func Archive Group

        Notification.Infomation("Success", "Archive group successfully");
        btn_cancel_Group(event);
    }

    // Func detail Group
    void btn_details_Group(ActionEvent event) {
        String url = "/ui/group-detail+search-result.fxml";
        load_Scene_AnchorPane(event, url);
    }

    @FXML
    void btn_back_StudentManagement(ActionEvent event) {
        String url = "/ui/student-management.fxml";
        load_Scene_AnchorPane(event, url);
    }

    @FXML
    void btn_ContinueWorkSpace_NewGroup(ActionEvent event) {

    }

    // Function shared
    @FXML
    void btn_cancel_Group(ActionEvent event) {
        this.create_NewGroup.setVisible(false);
        this.archive_NewGroup.setVisible(false);
        this.change_NewGroup.setVisible(false);
    }

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
            System.out.println("Error in loading student_controller load_Scene_AnchorPane");
            e.printStackTrace();
        }
    }

    // Load List Group
    void LoadListGroup(List<Group_card> list, StackPane archive_NewGroup) {
        for (Group_card group : list) {
            this.flowpane_mainbody.getChildren().add(group.getGroup_Instance());

            // Doing
            group.getDetails_btn().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    btn_details_Group(event);
                }
            });

            group.click_Button_Archive(archive_NewGroup);
        }
    }

    // Load List Student
    void LoadListStudent(List<Student_Model> list) {
    }

    /*-------------------------------------------------------*/
    // Initialize

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // LoadListGroup & Set button details & button archive
        LoadListGroup(group_list, archive_NewGroup);

    }
}
