package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import business.model.Group;
import business.model.Score;
import business.model.Student;
import business.model.Teacher;
import business.services.GroupManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import utils.Notification;
import utils.OpenFileExplorer;

public class GroupController implements Initializable {

	private Scene scene = null;

	private AnchorPane anchor;

	private static File file_Current;
	@FXML
	private Button export_Group = new Button();
	@FXML
	private Button import_Group = new Button();
	@FXML
	private TextField textField_GroupID = new TextField();
	@FXML
	private TextField textField_GroupName = new TextField();
	@FXML
	private Button button_Save = new Button();
	@FXML
	private ComboBox<Group> ComboBoxGroup = new ComboBox<Group>();
	@FXML
	private TableView<Student> tableView_Group = new TableView<Student>();
	@FXML
	private TableColumn<Student, String> Student_ID_Column = new TableColumn<Student, String>();
	@FXML
	private TableColumn<Student, String> Student_FullName_Column = new TableColumn<Student, String>();
	@FXML
	private TableColumn<Student, String> Student_Phone_Column = new TableColumn<Student, String>();
	@FXML
	private TableColumn<Student, String> Student_Email_Column = new TableColumn<Student, String>();
	@FXML
	private TableView<Score> tableView_GroupView = new TableView<Score>();
	@FXML
	private TableColumn<Score, String> Exam_ID_GroupView = new TableColumn<Score, String>();

	@FXML
	private TableColumn<Score, Double> Score_GroupView = new TableColumn<Score, Double>();

	@FXML
	private TableColumn<?, ?> Subject_GroupView;
	@FXML
	private Button btnAddStudent_Group = new Button();

	@FXML
	private Button btnDeleteStudent_Group = new Button();
	@FXML
	private HBox HBox_Search = new HBox();
	@FXML
	private Button btnDeleteGroup_Group = new Button();

	@FXML
	private Button btnViewStudent_Group = new Button();
	@FXML
	private TextField searchOnnGroup = new TextField();
	@FXML
	private TextField textField_EmailAdd_GroupAdd;

	@FXML
	private TextField textField_FirstNameAdd_GroupAdd;

	@FXML
	private TextField textField_IdAdd_GroupAdd;

	@FXML
	private TextField textField_LastNameAdd_GroupAdd;

	@FXML
	private TextField textField_PhoneAdd_GroupAdd;

	@FXML
	private Button btn_Edit_Group_View;

	@FXML
	private Label label_Stu_Id_GroupView = new Label();

	@FXML
	private TextField textField_Email_GroupView = new TextField();

	@FXML
	private TextField textField_FirstName_GroupView = new TextField();

	@FXML
	private TextField textField_LastName_GroupView = new TextField();

	@FXML
	private TextField textField_Phone_GroupView = new TextField();

	@FXML
	private Button button_Save_GroupView_Display;

	public static Student student_Current;

	private ObservableList<Student> observableList;

	private ObservableList<Score> observableList_Score;

	private List<Student> studentList;

	public static Group group_Current_Layout;

	private static GroupManager gr = new GroupManager();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadData_Group();
	}

	public void loadData_Group() {
		setVisibleButton_Add_DelGroup(false);

		if (group_Current_Layout != null) {
			btnAddStudent_Group.setVisible(true);
			ComboBoxGroup.setValue(group_Current_Layout);
			studentList = gr.getStudentsFromGroup(group_Current_Layout);
			tableView_Group.setItems(loadStudent_tableView(studentList));
			HBox_Search.setVisible(true);
		}
		List<Group> groups = gr.getGroups(LoginController.teacher_Current);

		for (Group group : groups) {
			ComboBoxGroup.getItems().add(group);
		}

		ComboBoxGroup.setConverter(new StringConverter<Group>() {
			@Override
			public String toString(Group gr) {
				return gr == null ? "" : "ID_Group : " + gr.getGroupID() + " -- " + gr.getGroupName();
			}

			@Override
			public Group fromString(String s) {
				return null;
			}
		});

		ComboBoxGroup.setOnAction(envent -> {
			btnViewStudent_Group.setVisible(false);
			Group Group_Current = ComboBoxGroup.getValue();
			group_Current_Layout = Group_Current;
			studentList = gr.getStudentsFromGroup(Group_Current);
			;
			tableView_Group.setItems(loadStudent_tableView(studentList));
			HBox_Search.setVisible(true);
			export_Group.setDisable(false);
			import_Group.setDisable(false);
			setVisibleButton_Add_DelGroup(true);
		});

		try {
			SelectionModel<Student> selectionModel = tableView_Group.getSelectionModel();
			selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					student_Current = newValue;
					btnViewStudent_Group.setVisible(true);
					btnDeleteStudent_Group.setVisible(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (student_Current != null)
			load_QuestionView();

	}

	private void setVisibleButton_Add_DelGroup(boolean b) {
		btnAddStudent_Group.setVisible(b);
		btnDeleteGroup_Group.setVisible(b);
	}

	private StringProperty getExamIDStringProperty(String t) {
		StringProperty examID = new SimpleStringProperty(t);
		return examID;
	}

	private ObservableList<Score> load_tableViewGroupView(List<Score> list) {
		observableList_Score = FXCollections.observableArrayList(list);

		Exam_ID_GroupView
				.setCellValueFactory(cellData -> getExamIDStringProperty(cellData.getValue().getExamID().toString()));
		Score_GroupView.setCellValueFactory(new PropertyValueFactory<Score, Double>("score"));
		// Subject_GroupView.setCellValueFactory(new PropertyValueFactory<Score,
		// String>("subjectName"));

		return observableList_Score;
	}

	public StringProperty getFullName(Student stu) {
		StringProperty fullName = new SimpleStringProperty(stu.getFirstName() + " " + stu.getLastName());
		return fullName;
	}

	private ObservableList<Student> loadStudent_tableView(List<Student> list) {
		observableList = FXCollections.observableArrayList(list);

		Student_ID_Column.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
		Student_FullName_Column.setCellValueFactory(cellData -> getFullName(cellData.getValue()));
		Student_Phone_Column.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
		Student_Email_Column.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

		return observableList;
	}

	public void addNewGroup() throws IOException {
		textField_GroupID.setDisable(false);
		textField_GroupName.setDisable(false);
		button_Save.setDisable(false);
	}

	@FXML
	public void Group_Tranfer_GroupAdd_Quizz(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Group_add.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}

	@FXML
	public void Group_Tranfer_GroupView_Quizz(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Group_view.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);

	}

	@FXML
	public void Back_Group(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Group.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}

	public boolean check_inputInfomatonStudent() {
		return textField_IdAdd_GroupAdd.getText() != null && textField_IdAdd_GroupAdd.getText() != ""
				&& textField_FirstNameAdd_GroupAdd.getText() != null
				&& textField_FirstNameAdd_GroupAdd.getText() != ""
				&& textField_LastNameAdd_GroupAdd.getText() != null && textField_IdAdd_GroupAdd.getText() != ""
				&& textField_PhoneAdd_GroupAdd.getText() != null && textField_PhoneAdd_GroupAdd.getText() != ""
				&& textField_EmailAdd_GroupAdd.getText() != null && textField_EmailAdd_GroupAdd.getText() != "";
	}

	@FXML
	public void saveStudent_Group(ActionEvent event) {
		try {

			if (!check_inputInfomatonStudent()) {
				Notification.Error(Notification.Unsuccessfully, Notification.FullFill);
				return;
			}
			Boolean isAddedStudent = gr.addStudent(group_Current_Layout,
					new Student(textField_IdAdd_GroupAdd.getText(), textField_FirstNameAdd_GroupAdd.getText(),
							textField_LastNameAdd_GroupAdd.getText(), textField_PhoneAdd_GroupAdd.getText(),
							textField_EmailAdd_GroupAdd.getText()));

			if (!isAddedStudent) {
				Notification.Infomation(Notification.Successfully,
						Notification.Success_AddStudent);
				return;
			}

			Notification.Error(Notification.Unsuccessfully,
					Notification.Unsuccess_AddStudent);

		} catch (SQLException e) {

			// cleaned
			Boolean Confirm = Notification.Comfrim(Notification.Default,
					"Học sinh này đã có trong nhóm khasc! Bạn muốn thêm học sinh vào nhóm?")
					.getResult() == ButtonType.YES;
			if (!Confirm) {
				Notification.Infomation(Notification.Default,
						Notification.Unsuccess_AddStudent);
				return;
			}

			try {
				Boolean isAddedStudentToGroup = gr.addStudentToGroup(group_Current_Layout.getGroupID(),
						textField_IdAdd_GroupAdd.getText());
				if (!isAddedStudentToGroup) {
					Notification.Infomation(Notification.Successfully,
							Notification.Success_AddStudent);
					return;
				}
			} catch (SQLException e1) {
				Notification.Error(Notification.Unsuccessfully,
						Notification.Unsuccess_AddStudent
								+ "Bởi vì học sinh này đã có trong nhóm!");
			}

		}
	}

	private void setAnchor(AnchorPane insidePane) {
		AnchorPane.setTopAnchor(insidePane, 0.0);
		AnchorPane.setBottomAnchor(insidePane, 0.0);
		AnchorPane.setRightAnchor(insidePane, 0.0);
		AnchorPane.setLeftAnchor(insidePane, 0.0);
	}

	public void load_QuestionView() {
		label_Stu_Id_GroupView.setText(student_Current.getStudentID());
		textField_Email_GroupView.setText(student_Current.getEmail());
		textField_FirstName_GroupView.setText(student_Current.getFirstName());
		textField_LastName_GroupView.setText(student_Current.getLastName());
		textField_Phone_GroupView.setText(student_Current.getPhone());

		tableView_GroupView.setItems(load_tableViewGroupView(student_Current.getScores()));

	}

	@FXML
	void button_Edit_GroupView_Display(ActionEvent event) {
		label_Stu_Id_GroupView.setDisable(false);
		textField_Email_GroupView.setDisable(false);
		textField_FirstName_GroupView.setDisable(false);
		textField_LastName_GroupView.setDisable(false);
		textField_Phone_GroupView.setDisable(false);

		button_Save_GroupView_Display.setVisible(true);
	}

	@FXML
	void buttonSave_Data_ChangeStudent_GroupView(ActionEvent event) {
		try {
			Student student_Change = new Student(student_Current.getStudentID(),
					textField_FirstName_GroupView.getText(), textField_LastName_GroupView.getText(),
					textField_Phone_GroupView.getText(), textField_Email_GroupView.getText(),
					student_Current.getScores());
			Boolean isChange = gr.editStudent(student_Change);
			if (isChange) {
				studentList.set(studentList.indexOf(student_Current), student_Change);
				Notification.Infomation(Notification.Successfully, "The student was changed!");
			}
		} catch (Exception e) {
			Notification.Error(Notification.Unsuccessfully, "The student wasn't changed!");
		}
	}

	@FXML
	public void buttonDelete_Student_Group(ActionEvent event) {
		if (!gr.removeStudentFromGroup(group_Current_Layout, student_Current)) {
			Notification.Error(Notification.Unsuccessfully,
					Notification.Unsuccess_DeleteStudent);
			return;
		}
		Notification.Infomation(Notification.Successfully,
				Notification.Success_DeleteStudent);
		Student student_Delete = tableView_Group.getSelectionModel().getSelectedItem();
		tableView_Group.getItems().remove(student_Delete);
		studentList.remove(student_Delete);

	}

	@FXML
	void save_AddGroup_Group(ActionEvent event) {
		Teacher teacher_Group = LoginController.teacher_Current;
		if (textField_GroupID.getText() != null && textField_GroupName.getText() != null
				&& textField_GroupID.getText() != "" && textField_GroupName.getText() != "") {
			try {
				if (!gr.addGroup(
						new Group(textField_GroupID.getText(), teacher_Group, textField_GroupName.getText()))) {

				} else {
					ComboBoxGroup.getItems()
							.add(new Group(textField_GroupID.getText(), teacher_Group, textField_GroupName.getText()));
					Notification.Infomation(Notification.Successfully,
							Notification.Success_AddGroup);
				}
			} catch (SQLException e) {

				Notification.Error(Notification.Unsuccessfully,
						Notification.Unsuccess_AddGroup);
			}
		} else {
			Notification.Error(Notification.Unsuccessfully, Notification.FullFill);
		}
	}

	@FXML
	void deleteGroup_Group(ActionEvent event) {
		gr.deleteGroup(group_Current_Layout.getGroupID());
		ComboBoxGroup.getItems().remove(group_Current_Layout);
	}

	@FXML
	private boolean Import_Group(ActionEvent event) {
		file_Current = OpenFileExplorer.Open(event);
		if (file_Current != null) {
			String check_xlsx = file_Current.getPath().substring(file_Current.getPath().lastIndexOf(".") + 1);
			try {
				Boolean check_FormatExel = check_xlsx.equalsIgnoreCase("xlsx") || check_xlsx.equalsIgnoreCase("xls");

				if (!check_FormatExel) {
					Notification.Error(Notification.Error,
							Notification.SameData_NotOverride);
					return false;
				}
				tableView_Group.getItems().clear();

				Boolean is_SameData = Notification.Comfrim(Notification.Default,
						Notification.SameData_Override)
						.getResult() == ButtonType.YES;

				gr.importStudent(group_Current_Layout, file_Current.getPath(), is_SameData);

				Notification.Infomation(Notification.Successfully,
						Notification.Success_ImportFile);

				studentList = gr.getStudentsFromGroup(group_Current_Layout);

				tableView_Group.setItems(loadStudent_tableView(studentList));

			} catch (IOException e) {
				file_Current = null;
				Notification.Error(Notification.Error, Notification.Unsuccess_ImportFile);
			}
		}
		// studentList = gr.getStudentsFromGroup(group_Current_Layout);

		// tableView_Group.setItems(loadStudent_tableView(studentList));
		return file_Current == null ? false : true;

	}

	@FXML
	private void searchGroup(ActionEvent event) {
		String keyword = searchOnnGroup.getText();

		List<Student> current_List = gr.searchStudentsFromGroup(group_Current_Layout, keyword);

		if (current_List != null) {
			tableView_Group.getItems().clear();
			tableView_Group.setItems(loadStudent_tableView(current_List));
			return;
		}
		Notification.Infomation("Thông báo", "Danh sách rỗng");
	}

	@FXML
	private void Export_Group(ActionEvent event) {
		try {
			file_Current = OpenFileExplorer.Save(event);

			String fileNameExel = file_Current.getAbsolutePath();

			Boolean is_ExportSuccess = gr.exportStudent(group_Current_Layout, studentList, fileNameExel);
			if (!is_ExportSuccess) {
				Notification.Infomation(Notification.Unsuccessfully,
						Notification.Unsuccess_ExelFile);
				return;
			}

			Boolean is_Confirm = Notification.Comfrim(Notification.Successfully,
					Notification.Success_ExelFile).getResult() == ButtonType.YES;

			if (is_Confirm)
				OpenFileExeml_Export(new File(fileNameExel));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void OpenFileExeml_Export(File fileOpen) {
		if (fileOpen == null) {
			Notification.Error(Notification.Unsuccessfully,
					Notification.Unsuccess_ImportFile);
			return;
		}
		try {
			Desktop.getDesktop().open(fileOpen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
