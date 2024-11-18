package controller;

import java.io.File;
import java.awt.Desktop;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import model.Student;
import utils.Notification;
import utils.OpenFileExplorer;

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

    /* Detail Group */
    // TextField Detail Group
    @FXML
    private TextField tf_SearchStudent_StudentManagement;

    // Table Detail Group
    @FXML
    private TableView<?> table_Student_StudentManagement;

    // Label Detail Group
    @FXML
    private Label lb_GroupName_StudentManagement;

    @FXML
    private Label lb_GroupID_StudentManagement;

    @FXML
    private Label lb_DateCreate_StudentManagement;

    @FXML
    private Label lb_Quantity_StudentManagement;

    // StackPane Detail Group
    // Rename Student
    @FXML
    private StackPane renameGroup_StudentManagement;

    @FXML
    private Label lb_GroupName_Rename_StudentManagement;

    @FXML
    private Label lb_GroupID_Rename_StudentManagement;

    @FXML
    private TextField tf_Rename_StudentManagement;

    // Add Student
    @FXML
    private StackPane addStudent_StudentManagement;

    @FXML
    private TextField tf_FirstName_Add_StudentManagement;

    @FXML
    private TextField tf_LastName_Add_StudentManagement;

    @FXML
    private TextField tf_StudentID_Add_StudentManagement;

    @FXML
    private TextField tf_Email_Add_StudentManagement;

    @FXML
    private TextField tf_Phone_Add_StudentManagement;

    // Detail Student
    @FXML
    private StackPane detailStudent_StudentManagement;

    @FXML
    private TextField tf_FirstName_Detail_StudentManagement;

    @FXML
    private TextField tf_LastName_Detail_StudentManagement;

    @FXML
    private TextField tf_StudentID_Detail_StudentManagement;

    @FXML
    private TextField tf_Email_Detail_StudentManagement;

    @FXML
    private TextField tf_Phone_Detail_StudentManagement;

    //
    @FXML
    private StackPane existsStudent_StudentManagement;

    @FXML
    private StackPane addStudentFail_Email_StudentManagemennt;

    @FXML
    private StackPane addStudentFail_PhoneNumber_StudentManagement;

    // AnchorPane Detail Group
    @FXML
    private AnchorPane AnchorPane_GroupDetailStudent_StudentManagement;

    @FXML
    private AnchorPane AnchorPane_ReultSearch_StudentManagement;
    /*------------------------------------------------------- */
    // Data for test
    Group_card group = new Group_card("Group name", "GR0011", "2020-01-01");
    Group_card group2 = new Group_card("Group name", "GR0012", "2020-01-02");
    Group_card group3 = new Group_card("Group name", "GR0013", "2020-01-03");

    List<Group_card> group_list = List.of(group, group2, group3);

    Student student = new Student(1, 1, "ID", "First name", "Last name", "Email", "Phone");
    Student student2 = new Student(2, 2, "ID", "First name", "Last name", "Email", "Phone");
    Student student3 = new Student(3, 3, "ID", "First name", "Last name", "Email", "Phone");

    List<Student> student_list = List.of(student, student2, student3);

    // Import Group Detail
    @FXML
    boolean btn_ImportStudent_StudentManagement(ActionEvent event) {
        File file_Current = OpenFileExplorer.Open(event);
        if (file_Current != null) {
            String check_xlsx = file_Current.getPath().substring(file_Current.getPath().lastIndexOf(".") + 1);
            // try {
            Boolean check_FormatExel = check_xlsx.equalsIgnoreCase("xlsx") || check_xlsx.equalsIgnoreCase("xls");

            if (!check_FormatExel) {
                Notification.Error("Error",
                        "Please choose file excel");
                return false;
            }

            // Boolean is_SameData = Notification.Comfrim("Error",
            // "Trung data")
            // .getResult() == ButtonType.YES;

            // gr.importStudent(group_Current_Layout, file_Current.getPath(), is_SameData);

            Notification.Infomation("Success",
                    "Import file successfully");

            // studentList = gr.getStudentsFromGroup(group_Current_Layout);

            // studentList = gr.getStudentsFromGroup2(group_Current_Layout);

            // tableView_Group.getItems().clear();

            // tableView_Group.setItems(loadStudent_tableView(studentList));

            // } catch (IOException e) {
            // file_Current = null;
            // Notification.Error("Error", "Unsuccess_ImportFile");
            // }
        }

        return file_Current == null ? false : true;
    }

    // Export Group Detail
    @FXML
    void btn_ExportStudent_StudentManagement(ActionEvent event) {
        File file_Current = null;
        try {
            file_Current = OpenFileExplorer.Save(event);

            String fileNameExel = file_Current.getAbsolutePath();

            // Boolean is_ExportSuccess = gr.exportStudent(group_Current_Layout,
            // studentList, fileNameExel);
            // if (!is_ExportSuccess) {
            // Notification.Infomation("Error",
            // "Export file failed");
            // return;
            // }

            Boolean is_Confirm = Notification.Comfrim("Confirm",
                    "Do you want to open file?").getResult() == ButtonType.YES;

            if (is_Confirm)
                OpenFileExel_Export(new File(fileNameExel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Group Detail
    @FXML
    void btn_DeleteStudent_StudentManagement(ActionEvent event) {

    }

    // Rename Group Detail
    @FXML
    void btn_Rename_Hidden_StudentManagement(ActionEvent event) {
        this.renameGroup_StudentManagement.setVisible(true);
    }

    @FXML
    void btn_SaveChange_Detail_StudentManagement(ActionEvent event) {
        String firstName = tf_FirstName_Detail_StudentManagement.getText();
        String lastName = tf_LastName_Detail_StudentManagement.getText();
        String studentID = tf_StudentID_Detail_StudentManagement.getText();
        String email = tf_Email_Detail_StudentManagement.getText();
        String phone = tf_Phone_Detail_StudentManagement.getText();

        if (firstName.length() == 0 || firstName == null || firstName == "") {
            Notification.Error("Error", "Please enter first name");
            return;
        }
        if (lastName.length() == 0 || lastName == null || lastName == "") {
            Notification.Error("Error", "Please enter last name");
            return;
        }
        if (studentID.length() == 0 || studentID == null || studentID == "") {
            Notification.Error("Error", "Please enter student ID");
            return;
        }
        if (email.length() == 0 || email == null || email == "") {
            Notification.Error("Error", "Please enter email");
            return;
        }
        if (phone.length() == 0 || phone == null || phone == "") {
            Notification.Error("Error", "Please enter phone");
            return;
        }
        // func check email here

        // func check phone here

        // Func Save Change
    }

    @FXML
    void btn_Rename_StudentManagement(ActionEvent event) {
        String groupName = tf_Rename_StudentManagement.getText();
        if (groupName.length() == 0 || groupName == null || groupName == "") {
            Notification.Error("Error", "Please enter group name");
            return;
        }
        if (groupName.length() > 191) {
            Notification.Error("Error", "Group name is too long");
            return;
        }
        // Func Rename Group here

    }

    // Detail Group Detail
    @FXML
    void btn_StudentDetail_Hidden_StudentManagement(ActionEvent event) {
        this.detailStudent_StudentManagement.setVisible(true);
    }

    @FXML
    void btn_StudentDetail_ResultSearch_Hidden_StudentManagement(ActionEvent event) {
        this.detailStudent_StudentManagement.toFront();
        this.detailStudent_StudentManagement.setVisible(true);
    }

    // AddStudent Group Detail
    @FXML
    void btn_addStudent_Hidden_StudentManagement(ActionEvent event) {
        this.addStudent_StudentManagement.setVisible(true);
    }

    @FXML
    void btn_AddStudent_Add_StudentManagement(ActionEvent event) {
        String firstName = tf_FirstName_Add_StudentManagement.getText();
        String lastName = tf_LastName_Add_StudentManagement.getText();
        String studentID = tf_StudentID_Add_StudentManagement.getText();
        String email = tf_Email_Add_StudentManagement.getText();
        String phone = tf_Phone_Add_StudentManagement.getText();

        if (firstName.length() == 0 || firstName == null || firstName == "") {
            Notification.Error("Error", "Please enter first name");
            return;
        }
        if (lastName.length() == 0 || lastName == null || lastName == "") {
            Notification.Error("Error", "Please enter last name");
            return;
        }
        if (studentID.length() == 0 || studentID == null || studentID == "") {
            Notification.Error("Error", "Please enter student ID");
            return;
        }
        if (email.length() == 0 || email == null || email == "") {
            Notification.Error("Error", "Please enter email");
            return;
        }
        if (phone.length() == 0 || phone == null || phone == "") {
            Notification.Error("Error", "Please enter phone");
            return;
        }
        // func check email here

        // func check phone here

        // Func Add Student
    }

    // Archive Group Detail
    @FXML
    void btn_Archive_Hidden_StudentManagement(ActionEvent event) {

    }

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

        Group_card group = new Group_card(groupName, "ID", "12/8/2024");

        group_list.add(group);

        add_Group_FlowPane(group);

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

    // Search Student in Group

    @FXML
    void btn_SearchStudent_StudentManagement(ActionEvent event) {
        AnchorPane_GroupDetailStudent_StudentManagement.setVisible(false);
        AnchorPane_ReultSearch_StudentManagement.setVisible(true);
    }

    // Back Group
    @FXML
    void btn_back_StudentManagement(ActionEvent event) {
        if (!AnchorPane_GroupDetailStudent_StudentManagement.isVisible()) {
            AnchorPane_ReultSearch_StudentManagement.setVisible(false);
            AnchorPane_GroupDetailStudent_StudentManagement.setVisible(true);
            return;
        }
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

    @FXML
    void btn_cancel_GroupDetail(ActionEvent event) {
        this.renameGroup_StudentManagement.setVisible(false);
        this.addStudent_StudentManagement.setVisible(false);
        this.detailStudent_StudentManagement.setVisible(false);
        this.existsStudent_StudentManagement.setVisible(false);
        this.addStudentFail_Email_StudentManagemennt.setVisible(false);
        this.addStudentFail_PhoneNumber_StudentManagement.setVisible(false);
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

    // Add group for flowpane
    public void add_Group_FlowPane(Group_card group) {
        Insets margin = new Insets(12, 12, 0, 0);

        FlowPane.setMargin(group.getGroup_Instance(), margin);

        this.flowpane_mainbody.getChildren().add(group.getGroup_Instance());
        group.getDetails_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btn_details_Group(event);
            }
        });

        group.click_Button_Archive(archive_NewGroup);
    }

    // Load List Group
    public void LoadListGroup(List<Group_card> list, StackPane archive_NewGroup) {
        for (Group_card group : list) {
            add_Group_FlowPane(group);
        }
    }

    // Load List Student
    void LoadListStudent(List<Student> list) {
    }

    // Open File
    void OpenFileExel_Export(File fileOpen) {
        if (fileOpen == null) {
            Notification.Error("Error",
                    "Please choose file");
            return;
        }
        try {
            Desktop.getDesktop().open(fileOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*-------------------------------------------------------*/
    // Initialize

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // LoadListGroup & Set button details & button archive
        LoadListGroup(group_list, archive_NewGroup);

    }

}
