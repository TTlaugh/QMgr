package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import utils.Notification;

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

    // Data_test
    Group_card group = new Group_card("Group name", "GR0011", "2020-01-01");
    Group_card group2 = new Group_card("Group name", "GR0012", "2020-01-02");
    Group_card group3 = new Group_card("Group name", "GR0013", "2020-01-03");

    List<Group_card> group_list = new ArrayList<Group_card>();

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

    // func shared
    @FXML
    void btn_cancel_Subject(ActionEvent event) {
        this.create_NewSubject.setVisible(false);
        this.archive_NewSubject.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // LoadListGroup & Set button details & button archive
        LoadListGroup(group_list, archive_NewSubject);
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

}
