package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import business.model.Exam;
import business.model.Question;
import business.model.Student;
import business.model.Subject;
import business.services.ExamManager;
import business.services.QuestionManager;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import utils.DateTime;
import utils.DisplayDialog_Notification;
import utils.ObjectCell;

public class ExamController implements Initializable {
	private Scene scene = null;
	
	private AnchorPane anchor;
	
	private ExamManager examManager =new ExamManager();
	
	private QuestionManager quesManager=new QuestionManager();
	
	public static List<Question> listQuestion_Selected = new ArrayList<Question>();
	
	private List<Question> question_ListOfSubject;
	
	public static Subject subject_Current;
	
	public static Question question_Current;
	
	public static Exam exam_Current; 
	
	private ObservableList<Exam> observableList ;
	
	private ObservableList<Question> observableList_Question ;
	
	@FXML
	private Button deleteExam_Exam;
	
    @FXML
    private Button viewExam_Exam;
	 
	@FXML
	public static TextField Time_Start_ExamAdd=new TextField();
	   
	@FXML
	private Spinner<Integer> spinner_Exam=new Spinner<Integer>();
	
	@FXML
	private TableColumn<Exam, DateTime> DateTime_Exam_Column=new TableColumn<Exam, DateTime>();

	@FXML
	private TableColumn<Exam, DateTime> ID_Exam_Column=new TableColumn<Exam, DateTime>();

	@FXML
	private TableColumn<Exam, String> Name_Exam_Column=new TableColumn<Exam, String>();
	    
	@FXML
	private TableColumn<Exam, String> Subject_Exam_Column=new TableColumn<Exam, String>();

	@FXML
	private TableColumn<Exam, Integer> Time_Exam_Column=new TableColumn<Exam, Integer>();
	
    @FXML
    private TableView<Exam> tableView_Exam=new TableView<Exam>();
    
    @FXML
    private TableView<Question> tableView_ExamAdd=new TableView<Question>();
	
    @FXML
    private TableColumn<Question, Integer> Chapter_ExamAdd;

    @FXML
    private TableColumn<Question, String> Content_ExamAdd;

    @FXML
    private Button CreateExam_ExamAdd=new Button();

    @FXML
    private DatePicker Date_Exam_Add= new DatePicker();

    @FXML
    private TextArea Desctiption_ExamAdd=new TextArea();

    @FXML
    private TableColumn<Question, Integer> Difficulty_ExamAdd;

    @FXML
    private TableColumn<Question, String> ID_ExamAdd;

    @FXML
    private TextField Name_ExamAdd=new TextField();

    @FXML
    private TextField Score_ExamAdd=new TextField();

    @FXML
    private Spinner<Integer> Time_ExamAdd=new Spinner<Integer>();
    
    @FXML
    private ListView<Question> listViewQuestion_Selected_ExamAdd=new ListView<Question>();

    @FXML
    private AnchorPane anchor_Infomation_Exam_ExamAdd;

    @FXML
    private ComboBox<Subject> comboBox_ChooseSubject_ExamAdd=new ComboBox<Subject>();
	  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadSpinner();
		loadExam();
		loadComboBoxInExamAdd();
	}
	private void loadComboBoxInExamAdd() {
		List<Subject> subjects=quesManager.getSubjects(new LoginController().teacher_Current);
		
		for(Subject subj : subjects) {
			comboBox_ChooseSubject_ExamAdd.getItems().add(subj);
		}
		
		comboBox_ChooseSubject_ExamAdd.setConverter(new StringConverter<Subject>() {
	            @Override
	            public String toString(Subject subj) {
	                return subj == null ? "" : subj.getSubjectName();
	            }

	            @Override
	            public Subject fromString(String s) {
	                return null;
	            }
			});
		
		// Set default value comboBox
		comboBox_ChooseSubject_ExamAdd.setOnAction(envent -> {   	
			anchor_Infomation_Exam_ExamAdd.setDisable(false);
			
			 question_ListOfSubject=quesManager.getQuestionsForSubject(comboBox_ChooseSubject_ExamAdd.getValue());
			 
			 //Save subject current
			 new ExamController().subject_Current=comboBox_ChooseSubject_ExamAdd.getValue();
			 
			 tableView_ExamAdd.setItems(loadQuestion_tableView_ExamAdd(question_ListOfSubject));
		   });
		
		// Load table view and set value
		try {
	    	SelectionModel<Question> selectionModel = tableView_ExamAdd.getSelectionModel();
	    	selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	    		if (newValue != null) {
	    			new ExamController().question_Current=newValue;
	    		}
	    	});	    	
	    }catch (Exception e) {
		    e.printStackTrace();
	    }
		
		try {
	    	SelectionModel<Exam> selectionModel = tableView_Exam.getSelectionModel();
	    	selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	    		if (newValue != null) {
	    			new ExamController().exam_Current=newValue;
	    			setVisableView_Delete(true);
	    		}
	    	});	    	
	    }catch (Exception e) {
		    e.printStackTrace();
	    }
	}
	
	private ObservableList loadQuestion_tableView_ExamAdd(List<Question> list) {
		observableList_Question = FXCollections.observableArrayList(list);	    

		ID_ExamAdd.setCellValueFactory(new PropertyValueFactory<Question,String>("questionID")); 
		Chapter_ExamAdd.setCellValueFactory(new PropertyValueFactory<Question,Integer>("chapter"));
		Difficulty_ExamAdd.setCellValueFactory(new PropertyValueFactory<Question,Integer>("difficulty"));
		Content_ExamAdd.setCellValueFactory(new PropertyValueFactory<Question,String>("content"));
	    
	    return observableList_Question;
	}
	private void loadExam() {
		List<Exam> examList =examManager.getExams(new LoginController().teacher_Current);
		tableView_Exam.setItems(loadStudent_tableView(examList));
		
	}
	private ObservableList loadStudent_tableView(List<Exam> list) {
	    observableList = FXCollections.observableArrayList(list);	    

	    ID_Exam_Column.setCellValueFactory(new PropertyValueFactory<Exam, DateTime>("examID"));
	    Subject_Exam_Column.setCellValueFactory(cellData -> cellData.getValue().getSubject().getSubjectNameStringProperty());
	    DateTime_Exam_Column.setCellValueFactory(new PropertyValueFactory<Exam, DateTime>("startDateTime"));
	    Time_Exam_Column.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("timeLimit")); 
	    Name_Exam_Column.setCellValueFactory(new PropertyValueFactory<Exam, String>("name"));
	    
	    return observableList;
	}
	private void loadSpinner() {
		SpinnerValueFactory<Integer> valueFatory =new SpinnerValueFactory.IntegerSpinnerValueFactory(15, 120);
		spinner_Exam.setValueFactory(valueFatory);
		spinner_Exam.setEditable(true);
		
		Time_ExamAdd.setValueFactory(valueFatory);
		Time_ExamAdd.setEditable(true);
	}
	public void Exam_Tranfer_ExamAdd_Quizz(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Exam_add.fxml")).load();
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		setAnchor(insidePane);
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}

	public void Exam_Tranfer_ExamView_Quizz(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Exam_view.fxml")).load();
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		setAnchor(insidePane);
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}

	public void Back_Exam(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Exam.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}
	private void setAnchor(AnchorPane insidePane) {
		AnchorPane.setTopAnchor(insidePane, 0.0);
		AnchorPane.setBottomAnchor(insidePane, 0.0);
		AnchorPane.setRightAnchor(insidePane, 0.0);
		AnchorPane.setLeftAnchor(insidePane, 0.0);
	}

    @FXML
    private void choice_QuestionForExam_ExamAdd(ActionEvent event) {    	
    	
    	listQuestion_Selected.add(question_Current);
    	
    	ObservableList<Question> observableListView_Question = FXCollections.observableArrayList(listQuestion_Selected);

    	listViewQuestion_Selected_ExamAdd.getItems().clear();
    	
    	listViewQuestion_Selected_ExamAdd.setItems(observableListView_Question);
    	
    	listViewQuestion_Selected_ExamAdd.setCellFactory(param -> new ObjectCell<Question>());

    	//Cho phép chọn 1 dòng trong list
    	listViewQuestion_Selected_ExamAdd.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	
    	CreateExam_ExamAdd.setDisable(!checkListView());
    }

    @FXML
    private void Delete_QuestionForExam_ExamAdd(ActionEvent event) {
    	SelectionModel<Question> selectionModel = listViewQuestion_Selected_ExamAdd.getSelectionModel();
    	if (!selectionModel.isEmpty()) {
    	    int index = selectionModel.getSelectedIndex();
    	    listViewQuestion_Selected_ExamAdd.getItems().remove(index);
    	    listQuestion_Selected.remove(index);
    	}
    	CreateExam_ExamAdd.setDisable(!checkListView());
    }
    
    @FXML
    private void Delete_Exam_Exam(ActionEvent event) {
    	if(!examManager.deleteExam(String .valueOf(exam_Current.getExamID()))) {	
    		DisplayDialog_Notification.Dialog_Error( "Unsuccessful notification!", "Exam wasn't delete!","Unsuccessful!");
    		Exam exam_Delete = tableView_Exam.getSelectionModel().getSelectedItem();
    		tableView_Exam.getItems().remove(exam_Delete);
    	}
    	else {
    		DisplayDialog_Notification.Dialog_Infomation( "Successful notification!", "Exam was deleted!","Successful!");
    	}
    	CreateExam_ExamAdd.setDisable(!checkListView());
    }
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        String formattedTime = String.format("%d-%d-%d %02d:%02d:%02d", year, month, day, hour, minute, second);
        return formattedTime;
    }
    @FXML
    private void buttonCreateExam(ActionEvent event) {
//    	examManager.addExam(new Exam(
//    			new DateTime(getCurrentTime()),
//    			subject_Current,
//    			
//    			))
    }
    
    private boolean checkListView() {
    	return  listQuestion_Selected == null || listQuestion_Selected.isEmpty() ? false : true;
    }
    
    @FXML
    private void checkDateTime_StartTest() {
    	Date_Exam_Add.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isBefore(LocalDate.now())) {
            	Date_Exam_Add.setValue(null);
            	DisplayDialog_Notification.Dialog_Error("Notification", "Invalid date", "Time error");
            } else {
            	Date_Exam_Add.setValue(newValue);
            }
        });
    }
    private void setVisableView_Delete(boolean b) {
    	viewExam_Exam.setVisible(b);
    	deleteExam_Exam.setVisible(b);
    }
    @FXML
    private String checkTimeStart_ExamAdd(ActionEvent event) {
    	
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        Time_Start_ExamAdd.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
            	 String timeString = Time_Start_ExamAdd.getText();

                 try {
                     LocalDateTime dateTime = LocalDateTime.parse(timeString, formatter);
                 } catch (Exception e) {
                	 DisplayDialog_Notification.Dialog_Error("Notification","\r\n"
                	 		+ "Invalid time", "Error Time");
                 }
            }
          });
		return null;
    }
}