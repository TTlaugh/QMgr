package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import business.model.Group;
import business.model.Student;
import business.model.Teacher;
import business.services.GroupManager;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import utils.DisplayDialog_Notification;
import utils.OpenFileExplorer;



public class GroupController implements Initializable{
	
	private Scene scene = null;
	
	private AnchorPane anchor;
	
	private static File file_Current; 
    @FXML
    private Button export_Group=new Button();
    @FXML
    private Button import_Group=new Button();
	@FXML
	private TextField textField_GroupID=new TextField();
	@FXML
	private TextField textField_GroupName=new TextField();
	@FXML
	private Button button_Save=new Button();
	@FXML
	private ComboBox<Group> ComboBoxGroup = new ComboBox<Group>();
	@FXML 
	private TableView<Student> tableView_Group=new TableView<Student>(); 
	@FXML 
	private TableColumn<Student, String> Student_ID_Column;
	@FXML 
	private TableColumn<Student, String> Student_FullName_Column;
	@FXML 
	private TableColumn<Student, String> Student_Phone_Column;
	@FXML 
	private TableColumn<Student, String> Student_Email_Column;
    @FXML
    private ListView<String> listView_Group=new ListView<String>();
    @FXML
    private Button btnAddStudent_Group=new Button();

    @FXML
    private Button btnDeleteStudent_Group=new Button();

    @FXML
    private Button btnDeleteGroup_Group=new Button();

    @FXML
    private Button btnViewStudent_Group=new Button();
    
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
    private Label label_Stu_Id_GroupView=new Label();

    @FXML
    private TextField textField_Email_GroupView=new TextField();

    @FXML
    private TextField textField_FirstName_GroupView=new TextField();

    @FXML
    private TextField textField_LastName_GroupView=new TextField();

    @FXML
    private TextField textField_Phone_GroupView=new TextField();
    
    @FXML
    private Button button_Save_GroupView_Display;

    public static Student student_Current;
    
	private ObservableList<Student> observableList ;
	
	private List<Student> studentList;
	
	private static Group group_Current_Layout;
	
	private static GroupManager gr = new GroupManager();

	 @Override
	public void initialize(URL url, ResourceBundle rb) {
	    loadData_Group();
	 }
	public void loadData_Group() {
		List<Group> groups = gr.getGroups(new LoginController().teacher_Current);
		
		for (Group group : groups) {
			ComboBoxGroup.getItems().add(group);
		}
		
	    ComboBoxGroup.setConverter(new StringConverter<Group>() {
            @Override
            public String toString(Group gr) {
                return gr == null ? "" :"ID_Group : "+ gr.getGroupID() +" -- "+gr.getGroupName();
            }

            @Override
            public Group fromString(String s) {
                return null;
            }
		});
	    setVisibleButton_Add_DelGroup(false);
	    
	    ComboBoxGroup.setOnAction(envent -> {   	
	    	btnViewStudent_Group.setVisible(false);
		    Group Group_Current=ComboBoxGroup.getValue();
	    	gr.getStudentsForGroup(Group_Current);
	    	group_Current_Layout=Group_Current;
	    	studentList = Group_Current.getStudents();
	    	tableView_Group.setItems(loadStudent_tableView(studentList));
	    	export_Group.setDisable(false);
	    	import_Group.setDisable(false);
	    	setVisibleButton_Add_DelGroup(true);
	    });
	    
	    try {
	    	SelectionModel<Student> selectionModel = tableView_Group.getSelectionModel();
	    	selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	    		if (newValue != null) {
	    			student_Current=newValue;
	    			showInfo_Score_Student(newValue);
	    			btnViewStudent_Group.setVisible(true);	
	    			btnDeleteStudent_Group.setVisible(true);
	    		}
	    	});	    	
	    }catch (Exception e) {
		    e.printStackTrace();
	    }
	    if(student_Current!=null)
	    	load_QuestionView() ;
	   
	}
	private void setVisibleButton_Add_DelGroup(boolean b) {
		btnAddStudent_Group.setVisible(b);
    	btnDeleteGroup_Group.setVisible(b);
	}
	private ObservableList loadStudent_tableView(List<Student> list) {
	    observableList = FXCollections.observableArrayList(list);	    

	    Student_ID_Column.setCellValueFactory(new PropertyValueFactory<Student,String>("studentID"));
	    Student_FullName_Column.setCellValueFactory(cellData -> cellData.getValue().getFullName());
	    Student_Phone_Column.setCellValueFactory(new PropertyValueFactory<Student,String>("phone"));
	    Student_Email_Column.setCellValueFactory(new PropertyValueFactory<Student,String>("email")); 
	    
	    return observableList;
	}
	
	public void showInfo_Score_Student(Student student) {
		listView_Group.getItems().clear();
		listView_Group.getItems().add(String.valueOf(student.getScores()));
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
    @FXML
    void saveStudent_Group(ActionEvent event) {
    	try {
    		if(textField_IdAdd_GroupAdd.getText() != null &&
    			textField_IdAdd_GroupAdd.getText() != "" && 
    			textField_FirstNameAdd_GroupAdd.getText() !=null && 
    			textField_FirstNameAdd_GroupAdd.getText() != "" && 
    			textField_LastNameAdd_GroupAdd.getText()!=null &&
    			textField_IdAdd_GroupAdd.getText() != "" &&
				textField_PhoneAdd_GroupAdd.getText()!=null &&
				textField_PhoneAdd_GroupAdd.getText() != "" &&
				textField_EmailAdd_GroupAdd.getText()!=null &&
				textField_EmailAdd_GroupAdd.getText() != "")
    		{	
    			if(!gr.addStudent(group_Current_Layout,
    			new Student(textField_IdAdd_GroupAdd.getText(),
    					null,textField_FirstNameAdd_GroupAdd.getText(),
    					textField_LastNameAdd_GroupAdd.getText(),
    					textField_PhoneAdd_GroupAdd.getText(),
    					textField_EmailAdd_GroupAdd.getText())))
    			{
    				DisplayDialog_Notification.Dialog_Infomation("Successful notification!", "The student was added group!", "Successful!");
    			}
    		}
    		else {    			
    			DisplayDialog_Notification.Dialog_Error("Unsuccessful notification!", "Fill full the information!", "Error!");
    		}
    		
		} catch (SQLException e) {		
			if (DisplayDialog_Notification.Dialog_Comfrim(
					"Notification", "This student already has the data you want to get. Does this student have the data?", "Your choice")
					.getResult()==ButtonType.YES) {
				try {
					if(!gr.addStudentToGroup(group_Current_Layout.getGroupID(),textField_IdAdd_GroupAdd.getText()))
					{
						DisplayDialog_Notification.Dialog_Infomation("Successful notification!", "The student was added group!","Successful!");
					}
				} catch (SQLException e1) {
					DisplayDialog_Notification.Dialog_Error("Unsuccessful notification!", "The student wasn't added \r\n"
							+ "because the student is already in the group!", "Unuccessful!");
				}			    
			}
			else {
				DisplayDialog_Notification.Dialog_Infomation("Notification!","The student wasn't added group!", "Unsuccessful!");
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
    		if(gr.editStudent(new Student(
        			student_Current.getStudentID(),
        			student_Current.getPersonID(),
        			textField_FirstName_GroupView.getText(),
        			textField_LastName_GroupView.getText(),
        			textField_Phone_GroupView.getText(),
        			textField_Email_GroupView.getText(),student_Current.getScores()))){
        		DisplayDialog_Notification.Dialog_Infomation("Successful notification!","The student was changed!", "Successful!");
        	}
    	}
    	catch (Exception e) {
        		DisplayDialog_Notification.Dialog_Error( "Unsuccessful notification!", "The student wasn't changed!","Unsuccessful!");
    	}
    }
    
    @FXML
    void buttonDelete_Student_Group(ActionEvent event) {
    	if(!gr.removeStudentFromGroup(group_Current_Layout,student_Current)) {	
    		DisplayDialog_Notification.Dialog_Infomation( "Successful notification!", "The student was deleted!","Successful!");
    		Student student_Delete = tableView_Group.getSelectionModel().getSelectedItem();
			tableView_Group.getItems().remove(student_Delete);
    	}
    	else {
    		DisplayDialog_Notification.Dialog_Error( "Unsuccessful notification!", "The student wasn't delete!","Unsuccessful!");
    	}
    }
    
    @FXML
    void save_AddGroup_Group(ActionEvent event) {
    	Teacher teacher_Group=new LoginController().teacher_Current;
    	if(		textField_GroupID.getText()!=null && 
    			textField_GroupName.getText()!=null && 
				textField_GroupID.getText()!="" &&
				textField_GroupName.getText()!="") {
    		try {
    			if(!gr.addGroup(new Group(textField_GroupID.getText(),teacher_Group,textField_GroupName.getText()))){
    				
    			}
    			else {	    			
    				ComboBoxGroup.getItems().add(new Group(textField_GroupID.getText(),teacher_Group,textField_GroupName.getText()));		
    				DisplayDialog_Notification.Dialog_Infomation("Successful notification!","The group added successfully!", "Successful!");
    			}
    		} catch (SQLException e) {
//				e.printStackTrace();
    			DisplayDialog_Notification.Dialog_Error( "Unsuccessful notification!", "The group wasn't added because it already exists!","Unsuccessful!");
    		}			
		}
    	else {
    		DisplayDialog_Notification.Dialog_Error( "Unsuccessful notification!", "Fill full the information!","Error!");
    	}
    }
    @FXML
    void deleteGroup_Group(ActionEvent event) {
    	gr.deleteGroup(group_Current_Layout.getGroupID());
    	ComboBoxGroup.getItems().remove(group_Current_Layout);
	}
    @FXML
    private boolean Import_Group(ActionEvent event)  {
    	file_Current= OpenFileExplorer.Open(event);
    	if(file_Current!=null) {
    		String check_xlsx = file_Current.getPath().substring(file_Current.getPath().lastIndexOf(".")+1);
    		try {
    			
				if( (check_xlsx.equalsIgnoreCase("xlsx") || check_xlsx.equalsIgnoreCase("xls") ) ) {
					
			    	tableView_Group.getItems().clear();
			    	
			    	if((DisplayDialog_Notification.Dialog_Comfrim("Notification!", 
			    			"File data co kha nang trung voi he thong ban co muon dung thong tin he thong co san neu gap truong hop nay ", 
			    			"Yes/cancel").getResult()==ButtonType.YES)) 
			    		gr.importStudent(group_Current_Layout, file_Current.getPath(), true);
				    else 
				    	gr.importStudent(group_Current_Layout, file_Current.getPath(), false);
			   
			    	DisplayDialog_Notification.Dialog_Infomation("Successful notification", "Import Successful", "Successful");
				}
				else {
					DisplayDialog_Notification.Dialog_Error("Notification Error", "\r\n"
							+ "Excel word list cannot be imported completely due to detection of duplicate student ID with data system, please check and supplement these student tools", "Error Student Some students are already in the group ");
				}
			} catch (IOException e) {
				file_Current = null;	
				DisplayDialog_Notification.Dialog_Error("Notification Error", "You have not selected a file exel", "Error");
			}
    	} 	
    	gr.getStudentsForGroup(group_Current_Layout);
    	
    	studentList = group_Current_Layout.getStudents();
    	
    	tableView_Group.setItems(loadStudent_tableView(studentList));
    	
    	
    	return file_Current==null ? false : true ;
    }
    @FXML
   private void Export_Group(ActionEvent event) {
    	file_Current= OpenFileExplorer.Save(event);
    	
    	String fileNameExel=file_Current.getPath();
    	
    	
    
    	if(!gr.exportStudent(group_Current_Layout,fileNameExel)) {
			DisplayDialog_Notification.Dialog_Infomation("Unsuccessful notification", "Creat file exel failed", "Error");
		}
    	else {
    		if(DisplayDialog_Notification.Dialog_Comfrim("Successful notification",
    				"Creat file exel successfully and open file?", 
    				"Successful").getResult()==ButtonType.YES) {
    			 OpenFileExeml_Export(new File(fileNameExel));
    		}		
    	}
    }    
    
    void OpenFileExeml_Export(File fileOpen) {
    	if(fileOpen!=null) {
    		try {
    			Desktop.getDesktop().open(fileOpen);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}    		
    	}
    	else {
    		DisplayDialog_Notification.Dialog_Error("Notification Error", "\r\n"
    				+ "You have not imported the file", "Error");
    	}
    }
}
