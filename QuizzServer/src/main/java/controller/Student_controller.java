package controller;

import java.io.File;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import components.Group_card;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import model.Group;
import model.Student;
import services.GroupManager;
import services.StudentManager;
import services.SubjectManager;
import utils.CheckStringFormat;
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
    private Label lb_GroupConfirm_Archive_StudentManagement;

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

    // Label Detail Group
    @FXML
    private Label lb_GroupName_StudentManagement = new Label();

    @FXML
    private Label lb_GroupID_StudentManagement = new Label();

    @FXML
    private Label lb_DateCreate_StudentManagement = new Label();

    @FXML
    private Label lb_Quantity_StudentManagement = new Label();

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
    private StackPane addStudentFail_Email_StudentManagemennt;

    @FXML
    private StackPane addStudentFail_PhoneNumber_StudentManagement;

    // AnchorPane Detail Group
    @FXML
    private AnchorPane AnchorPane_GroupDetailStudent_StudentManagement;

    @FXML
    private AnchorPane AnchorPane_ReultSearch_StudentManagement;

    // TableView Detail Group

    @FXML
    public TableView<Student> table_Student_StudentManagement = new TableView<Student>();

    @FXML
    private TableColumn<Student, String> ID_TableColumn_groupDetail = new TableColumn<Student, String>();

    @FXML
    private TableColumn<Student, String> Fullname_TableColumn_groupDetail = new TableColumn<Student, String>();

    @FXML
    private TableColumn<Student, String> Phone_TableColumn_groupDetail = new TableColumn<Student, String>();

    @FXML
    private TableColumn<Student, String> Email_TableColumn_groupDetail = new TableColumn<Student, String>();
    /*------------------------------------------------------- */
    private StudentManager studentManager = StudentManager.getInstance();

    public static List<Student> student_list;

    private Student studentSelected;

    private GroupManager groupManager = GroupManager.getInstance();

    private List<Group> group_list = groupManager.getAllGroupInWorkSpace(Workspace_controller.current_WorkSpaceID);

    public static Group group_Current_StudentManagement = new Group();

    private int CountGroupID_Current_StudentManagement = group_list.get(group_list.size() - 1).getGroupId() + 1;

    private int indexSelected_AnchorPane_in_FlowPane = 0;

    private int indexSelected_Student_in_TableView = 0;

    // TableView
    public ObservableList<Student> observableList;

    // StackPane Notification Add Student

    @FXML
    private StackPane existsStudent_StudentManagement;

    @FXML
    private Label lb_Notification_StudentManagement;

    @FXML
    private RadioButton RadioButton_UpdateInfo_StudentManagement;

    @FXML
    private Label lb_UpdateInfo_StudentManagement;

    @FXML
    private RadioButton RadioButton_CreateStudent_StudentManagement;

    @FXML
    private RadioButton RadioButton_DontCreate_StudentManagement;

    @FXML
    private Label lb_DonotAdd_StudentManagement;

    private ToggleGroup group_RadioButton_addStudent = new ToggleGroup();

    // TableView search
    @FXML
    private Label lb_totalStudent_Search_StudentManagement = new Label();

    @FXML
    private TableView<Student> table_Search_StudentManagement = new TableView<Student>();

    @FXML
    private TableColumn<Student, String> ID_Column_searchDetail = new TableColumn<Student, String>();

    @FXML
    private TableColumn<Student, String> Fullname_Column_searchDetail = new TableColumn<Student, String>();

    @FXML
    private TableColumn<Student, String> Phone_Column_searchDetail = new TableColumn<Student, String>();

    @FXML
    private TableColumn<Student, String> Email_Column_searchDetail = new TableColumn<Student, String>();

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
    void deleteStudent_TableViewGroupDetail(TableView<Student> tableView) {
        studentSelected = tableView.getSelectionModel().getSelectedItem();

        indexSelected_Student_in_TableView = tableView.getSelectionModel().getSelectedIndex();

        if (studentSelected == null) {
            Notification.Error("Error", "Please choose student");
            return;
        }

        if (!studentManager.deleteStudent(studentSelected)) {
            Notification.Error("Error", "Delete student failed");
            return;
        }
        Notification.Infomation("Success", "Delete student successfully");

        tableView.getItems().remove(indexSelected_Student_in_TableView);
    }

    @FXML
    void btn_DeleteStudent_StudentManagement(ActionEvent event) {

        if (AnchorPane_ReultSearch_StudentManagement.isVisible()) {
            deleteStudent_TableViewGroupDetail(table_Student_StudentManagement);
            return;
        }
        deleteStudent_TableViewGroupDetail(table_Search_StudentManagement);

    }

    // Rename Group Detail
    @FXML
    void btn_Rename_Hidden_StudentManagement(ActionEvent event) {
        this.renameGroup_StudentManagement.setVisible(true);

        lb_GroupName_Rename_StudentManagement.setText(group_Current_StudentManagement.getGroupName());
        lb_GroupID_Rename_StudentManagement.setText("ID : #"
                + (group_Current_StudentManagement.getGroupId() >= 10 ? group_Current_StudentManagement.getGroupId()
                        : "0" + group_Current_StudentManagement.getGroupId()));
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

        studentSelected.setFirstName(firstName);
        studentSelected.setLastName(lastName);
        studentSelected.setPhone(phone);
        studentSelected.setEmail(email);

        if (!studentManager.updateStudent(studentSelected)) {
            Notification.Error("Error", "Save change student failed");
            return;
        }

        Notification.Infomation("Success", "Save change studennt successfully");

        table_Student_StudentManagement.getItems().set(indexSelected_Student_in_TableView, studentSelected);

        btn_cancel_GroupDetail(event);

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

        group_Current_StudentManagement.setGroupName(groupName);

        if (!groupManager.updateGroup(group_Current_StudentManagement)) {
            Notification.Error("Error", "Rename group failed");
            return;
        }

        Notification.Infomation("Success", "Rename group success");

        lb_GroupName_StudentManagement.setText(groupName);

        btn_cancel_GroupDetail(event);
    }

    // Detail Group Detail
    @FXML
    void btn_StudentDetail_Hidden_StudentManagement(ActionEvent event) {

        studentSelected = table_Student_StudentManagement.getSelectionModel().getSelectedItem();

        indexSelected_Student_in_TableView = table_Student_StudentManagement.getSelectionModel().getSelectedIndex();

        if (studentSelected == null) {
            Notification.Error("Error", "Please choose student");
            return;
        }

        this.detailStudent_StudentManagement.setVisible(true);

        tf_FirstName_Detail_StudentManagement.setText(studentSelected.getFirstName());
        tf_LastName_Detail_StudentManagement.setText(studentSelected.getLastName());
        tf_StudentID_Detail_StudentManagement.setText(studentSelected.getStudentId());
        tf_Email_Detail_StudentManagement.setText(studentSelected.getEmail());
        tf_Phone_Detail_StudentManagement.setText(studentSelected.getPhone());

    }

    @FXML
    void btn_StudentDetail_ResultSearch_Hidden_StudentManagement(ActionEvent event) {
        studentSelected = table_Search_StudentManagement.getSelectionModel().getSelectedItem();

        indexSelected_Student_in_TableView = table_Search_StudentManagement.getSelectionModel().getSelectedIndex();

        if (studentSelected == null) {
            Notification.Error("Error", "Please choose student");
            return;
        }
        this.detailStudent_StudentManagement.toFront();
        this.detailStudent_StudentManagement.setVisible(true);

        tf_FirstName_Detail_StudentManagement.setText(studentSelected.getFirstName());
        tf_LastName_Detail_StudentManagement.setText(studentSelected.getLastName());
        tf_StudentID_Detail_StudentManagement.setText(studentSelected.getStudentId());
        tf_Email_Detail_StudentManagement.setText(studentSelected.getEmail());
        tf_Phone_Detail_StudentManagement.setText(studentSelected.getPhone());

    }

    // AddStudent Group Detail
    @FXML
    void btn_addStudent_Hidden_StudentManagement(ActionEvent event) {
        clearAll_tf_AddStudent();
        this.addStudent_StudentManagement.setVisible(true);

    }

    void clearAll_tf_AddStudent() {
        tf_Email_Add_StudentManagement.clear();
        tf_FirstName_Add_StudentManagement.clear();
        tf_LastName_Add_StudentManagement.clear();
        tf_Phone_Add_StudentManagement.clear();
        tf_StudentID_Add_StudentManagement.clear();
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
        if (!CheckStringFormat.isCorrectFormat(studentID)) {
            Notification.Error("Error", "Student ID is not correct format");
            return;
        }

        Student student_inGroup = studentManager.getStudentbyIdfromGroup(studentID,
                group_Current_StudentManagement.getGroupId());

        if (student_inGroup != null) {
            Notification.Error("Error", "Student ID #" + studentID + " does not exist in the group, please try again");
            return;
        }
        studentSelected = new Student(0,
                group_Current_StudentManagement.getGroupId(), studentID, firstName, lastName,
                phone, email);

        if (studentManager.createStudent(studentSelected)) {
            Notification.Infomation("Success", "Add student successfully");
            clearAll_tf_AddStudent();
            this.addStudent_StudentManagement.setVisible(false);
            table_Student_StudentManagement.getItems().add(studentSelected);
        }

    }

    void setDataforExistsStudent(String studentID) {
        lb_Notification_StudentManagement.setText("Student ID #" + studentID + " already exists");

        RadioButton_UpdateInfo_StudentManagement.setText("Update #" + studentID + " info");
        lb_UpdateInfo_StudentManagement
                .setText("Information of student with StudentID #" + studentID + " in the system will be updated.");

        lb_DonotAdd_StudentManagement.setText("Do not add student #" + studentID + " to the system.");
    }

    void setToggleforRadioButtonAddStudent() {
        RadioButton_UpdateInfo_StudentManagement.setToggleGroup(group_RadioButton_addStudent);

        RadioButton_CreateStudent_StudentManagement.setToggleGroup(group_RadioButton_addStudent);

        RadioButton_DontCreate_StudentManagement.setToggleGroup(group_RadioButton_addStudent);
    }

    @FXML
    void btn_cancel_Noti_Add_StudentManagement(ActionEvent event) {
        this.existsStudent_StudentManagement.setVisible(false);
    }

    public int findIndexByUid(String studentId, TableView<Student> tableViewStudent) {
        ObservableList<Student> items = tableViewStudent.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1; // Không tìm thấy
    }

    void Update_Info() {
        if (!studentManager.updateStudent(studentSelected)) {
            Notification.Error("Error", "Update student failed");
            return;
        }

        Student isUpdate = studentManager.getStudentbyId(studentSelected.getStudentId());

        int index = findIndexByUid(isUpdate.getStudentId(), table_Student_StudentManagement);

        table_Student_StudentManagement.getItems().set(index, isUpdate);

        Notification.Infomation("Success", "Update student successfully");

        existsStudent_StudentManagement.setVisible(false);
    }

    void Create_newStudent() {
        int sizeAllStudent = studentManager.getAllStudentInGroup(group_Current_StudentManagement.getGroupId()).size();

        String idStudent;

        if (sizeAllStudent > 99) {
            idStudent = "ST" + (sizeAllStudent);
        } else {
            idStudent = (sizeAllStudent) < 10 ? "ST00" + (sizeAllStudent) : "ST0" + (sizeAllStudent);
        }

        studentSelected.setStudentId(idStudent);

        if (!studentManager.createStudent(studentSelected)) {
            Notification.Error("Error", "Create student failed");
            return;
        }
        Student isSuccessAdd = studentManager.getStudentbyId(idStudent);

        table_Student_StudentManagement.getItems().add(isSuccessAdd);

        Notification.Infomation("Success", "Create student successfully");

        existsStudent_StudentManagement.setVisible(false);
    }

    void DontCreateStudent() {

        Notification.Infomation("Unsuccess", "Student is not added to the system");

        existsStudent_StudentManagement.setVisible(false);
    }

    @FXML
    void btn_Continue_StudentManagement(ActionEvent event) {
        if (RadioButton_UpdateInfo_StudentManagement.isSelected())
            Update_Info();
        else if (RadioButton_CreateStudent_StudentManagement.isSelected())
            Create_newStudent();
        else if (RadioButton_DontCreate_StudentManagement.isSelected())
            DontCreateStudent();
    }

    // Archive Group Detail
    @FXML
    void btn_Archive_Hidden_StudentManagement(ActionEvent event) {

    }

    // Func Create New Group
    @FXML
    void btn_newGroup_Hidden(ActionEvent event) {
        tf_GroupName_CreateGroup.clear();
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

        Group group_current = new Group(CountGroupID_Current_StudentManagement, 1, groupName, LocalDateTime.now(),
                false);

        // Func Create New Group
        boolean is_CreateSuccess = groupManager.createGroup(group_current);

        if (!is_CreateSuccess) {
            Notification.Error("Error", "Create group failed");
            return;
        }

        group_list.add(group_current);

        CountGroupID_Current_StudentManagement++;

        Notification.Infomation("Success", "Create new group successfully");

        Group_card group_card_current = new Group_card(group_current);

        add_Group_FlowPane(group_card_current);

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
        if (!(groupName.trim()).equalsIgnoreCase(lb_Group_Name_Archive.getText())) {
            Notification.Error("Error", "Group name does not match");
            return;
        }

        // Func Archive Group
        if (!groupManager.deleteGroup(group_Current_StudentManagement.getGroupId())) {
            Notification.Error("Error", "Archive group failed");
            return;
        }
        Notification.Infomation("Success", "Archive group successfully");

        this.flowpane_mainbody.getChildren().remove(indexSelected_AnchorPane_in_FlowPane);

        group_list.remove(group_Current_StudentManagement);

        btn_cancel_Group(event);
    }

    // Func detail Group
    void btn_details_Group(ActionEvent event) {
        if (student_list != null) {
            String url = "/ui/group-detail+search-result.fxml";
            load_Scene_AnchorPane(event, url);
        }

    }

    // Search Student in Group

    @FXML
    void btn_SearchStudent_StudentManagement(ActionEvent event) {
        AnchorPane_GroupDetailStudent_StudentManagement.setVisible(false);
        AnchorPane_ReultSearch_StudentManagement.setVisible(true);

        String keyword = tf_SearchStudent_StudentManagement.getText();

        if (keyword.length() == 0 || keyword == null || keyword == "") {
            Notification.Error("Error", "Please enter keyword");
            return;
        }
        if (keyword.length() > 191) {
            Notification.Error("Error", "Keyword is too long");
            return;
        }

        // Func Search Student
        ArrayList<Student> listStudent = studentManager.searchStudent(group_Current_StudentManagement.getGroupId(),
                keyword);

        if (listStudent == null || listStudent.size() == 0) {
            Notification.Error("Error", "No student found");
            return;
        }

        lb_totalStudent_Search_StudentManagement.setText(listStudent.size() + " students");

        table_Search_StudentManagement.getItems().clear();
        table_Search_StudentManagement.setItems(loadStudent_tableViewSearch_StudentManagement(listStudent));
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

    void setAction_Archive_Details(Group_card group) {
        group.getArchive_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                archive_NewGroup.setVisible(true);

                tf_Group_ConfirmName_Archive.clear();

                group_Current_StudentManagement = group.getGroup();

                indexSelected_AnchorPane_in_FlowPane = flowpane_mainbody.getChildren()
                        .indexOf(group.getGroup_Instance());

                if (archive_NewGroup.isVisible()) {
                    lb_Group_ID_Archive.setText("Group ID :" + group_Current_StudentManagement.getGroupId() + "");
                    lb_Group_Name_Archive.setText(group_Current_StudentManagement.getGroupName());
                    lb_GroupConfirm_Archive_StudentManagement.setText(
                            "To confirm, type \"" + group_Current_StudentManagement.getGroupName()
                                    + "\" in the box below");
                }
            }
        });

        group.getDetails_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                group_Current_StudentManagement = group.getGroup();

                Student_controller.student_list = studentManager
                        .getAllStudentInGroup(group_Current_StudentManagement.getGroupId());

                btn_details_Group(event);

            }
        });
    }

    // Add group for flowpane
    public void add_Group_FlowPane(Group_card group) {
        Insets margin = new Insets(12, 12, 0, 0);

        FlowPane.setMargin(group.getGroup_Instance(), margin);

        this.flowpane_mainbody.getChildren().add(group.getGroup_Instance());

        setAction_Archive_Details(group);
    }

    // Load List Student
    public void LoadListStudent() {
        table_Student_StudentManagement
                .setItems(loadStudent_tableViewStudent_StudentManagement(Student_controller.student_list));

        lb_GroupName_StudentManagement.setText(group_Current_StudentManagement.getGroupName());
        lb_GroupID_StudentManagement.setText("ID : #"
                + (group_Current_StudentManagement.getGroupId() >= 10 ? group_Current_StudentManagement.getGroupId()
                        : "0" + group_Current_StudentManagement.getGroupId()));
        lb_DateCreate_StudentManagement.setText(
                group_Current_StudentManagement.getCreateDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        lb_Quantity_StudentManagement.setText(student_list.size() + " students");
    }

    // Load List Group
    public void LoadListGroup() {
        for (Group group : group_list) {

            Group_card group_card = new Group_card(group);

            add_Group_FlowPane(group_card);
        }

    }

    public ObservableList<Student> loadStudent_tableViewStudent_StudentManagement(List<Student> list) {

        observableList = FXCollections.observableArrayList(list);

        ID_TableColumn_groupDetail.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
        Fullname_TableColumn_groupDetail.setCellValueFactory(cellData -> getFullName(cellData.getValue()));
        Phone_TableColumn_groupDetail.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        Email_TableColumn_groupDetail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

        return observableList;
    }

    public ObservableList<Student> loadStudent_tableViewSearch_StudentManagement(List<Student> list) {

        observableList = FXCollections.observableArrayList(list);

        ID_Column_searchDetail.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
        Fullname_Column_searchDetail.setCellValueFactory(cellData -> getFullName(cellData.getValue()));
        Phone_Column_searchDetail.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        Email_Column_searchDetail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

        return observableList;
    }

    // Get Full Name
    public StringProperty getFullName(Student stu) {
        StringProperty fullName = new SimpleStringProperty(stu.getFirstName() + " " + stu.getLastName());
        return fullName;
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
        LoadListGroup();

        if (student_list != null) {
            LoadListStudent();
        }
    }

}
