package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import business.model.Group;
import business.model.Student;
import business.services.GroupManager;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;



public class GroupController implements Initializable{
	
	private Scene scene = null;
	
	private AnchorPane anchor;
	
	@FXML
	private TextField textField_GroupID;
	@FXML
	private TextField textField_Name;
	@FXML
	private Button button_Save;
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
    private TextField textFile_EmailAdd_Group;

    @FXML
    private TextField textFile_FirstNameAdd_Group;

    @FXML
    private TextField textFile_IdAdd_Group;

    @FXML
    private TextField textFile_LastNameAdd_Group;

    @FXML
    private TextField textFile_PhoneAdd_Group;
    
	ObservableList<Student> observableList ;
	
	private List<Student> studentList;
	
	private static Group group_Current_Layout;
	
	private static GroupManager gr = new GroupManager();

	 @Override
	public void initialize(URL url, ResourceBundle rb) {
	    loadData_Group();
	 }
	public void loadData_Group() {
		List<Group> groups = gr.getGroups();
		
		for (Group group : groups) {
			ComboBoxGroup.getItems().add(group);
		}
		
	    ComboBoxGroup.setConverter(new StringConverter<Group>() {
            @Override
            public String toString(Group gr) {
                return gr == null ? "" : gr.getGroupID();
            }

            @Override
            public Group fromString(String s) {
                return null;
            }
		});
	    
	    setVisibleButton_Add_Del_View(false);
	    ComboBoxGroup.setOnAction(envent -> {   	
		    Group Group_Current=ComboBoxGroup.getValue();
	    	gr.getStudentsForGroup(Group_Current);
	    	studentList = Group_Current.getStudents();
	    	tableView_Group.setItems(loadStudent_tableView(studentList));
	    	setVisibleButton_Add_Del_View(true);
	    });
	    
	    try {
	    	SelectionModel<Student> selectionModel = tableView_Group.getSelectionModel();
	    	selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	    		if (newValue != null) {
	    			// Handle selected item: newValue
	    			showInfo_Score_Student(newValue);
	    		}
	    	});	    	
	    }catch (Exception e) {
		    e.printStackTrace();
	    }
	   
	}
	private void setVisibleButton_Add_Del_View(boolean b) {
		btnAddStudent_Group.setVisible(b);
    	btnDeleteStudent_Group.setVisible(b);
    	btnViewStudent_Group.setVisible(b);
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
		textField_Name.setDisable(false);
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
    		if( textFile_IdAdd_Group.getText() != null && textFile_IdAdd_Group.getText() != "" && 
    			textFile_FirstNameAdd_Group.getText() !=null && textFile_FirstNameAdd_Group.getText() != "" && 
    			textFile_LastNameAdd_Group.getText()!=null &&textFile_IdAdd_Group.getText() != "") 
    		{	
    			System.out.println(textFile_IdAdd_Group.getText());
    			if(!gr.addStudentToGroup(group_Current_Layout,new Student(
	    					null,
	    					textFile_FirstNameAdd_Group.getText(),
	    					textFile_LastNameAdd_Group.getText(),
	    					textFile_PhoneAdd_Group.getText(),
	    					textFile_EmailAdd_Group.getText(),
	    					textFile_IdAdd_Group.getText(),
	    					null))) 
    			{
    				System.out.println("Them That bai \n");
				}
    			else {
    				System.out.println("Them thanh cong \n");
    			}
    		}
    		else {    			
    			System.out.println("Dien day du thong tin \n");
    		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	private void setAnchor(AnchorPane insidePane) {
		AnchorPane.setTopAnchor(insidePane, 0.0);
		AnchorPane.setBottomAnchor(insidePane, 0.0);
		AnchorPane.setRightAnchor(insidePane, 0.0);
		AnchorPane.setLeftAnchor(insidePane, 0.0);
	}

	

}
