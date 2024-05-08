package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import business.model.Exam;
import business.model.Question;
import business.model.Subject;
import business.services.ExamManager;
import business.services.QuestionManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import utils.DateTime;
import utils.DisplayDialog_Notification;
import utils.uiUtils;

public class ExamController implements Initializable {
	private Scene scene = null;

	private AnchorPane anchor;

	private ExamManager examManager = new ExamManager();

	private QuestionManager quesManager = new QuestionManager();

	public static List<Question> listQuestion_Selected = new ArrayList<Question>();

	private List<Question> question_ListOfSubject;

	public static Subject subject_Current;

	public static Question question_Current;

	public static Exam exam_Current;
	
	private ObservableList<Question> selectedItems ;
	
	private ObservableList<Exam> observableList;

	private ObservableList<Question> observableList_Question;
    @FXML
    private DatePicker DatePicker_Exam=new DatePicker();
	@FXML
	private TextField textFile_Exam=new TextField()	;
	   
	@FXML
	private ComboBox<Subject> subject_Exam=new ComboBox<Subject>();
	
	@FXML
	private Button picktoTest = new Button();

	@FXML
	private CheckBox shuffe_Question=new CheckBox();
	
	@FXML
	private Label Date_ExamView = new Label();

	@FXML
	private Label Desc_ExamView = new Label();

	@FXML
	private Label Name_ExamView = new Label();

	@FXML
	private Label Score_ExamView = new Label();

	@FXML
	private Label Subject_ExamView = new Label();

	@FXML
	private Label TimeLimit_ExamView = new Label();

	@FXML
	private VBox VBox_DisplayQuestion = new VBox();

	@FXML
	private Spinner<Integer> Spinner_Hours_ExamAdd = new Spinner<Integer>();

	@FXML
	private Spinner<Integer> Spinner_Minutes_ExamAdd = new Spinner<Integer>();

	@FXML
	private Button deleteExam_Exam;

	@FXML
	private Button viewExam_Exam;

	@FXML
	public static TextField Time_Start_ExamAdd = new TextField();

	@FXML
	private Spinner<Integer> spinner_Exam = new Spinner<Integer>();

	@FXML
	private TableColumn<Exam, DateTime> DateTime_Exam_Column = new TableColumn<Exam, DateTime>();

	@FXML
	private TableColumn<Exam, DateTime> ID_Exam_Column = new TableColumn<Exam, DateTime>();

	@FXML
	private TableColumn<Exam, String> Name_Exam_Column = new TableColumn<Exam, String>();

	@FXML
	private TableColumn<Exam, String> Subject_Exam_Column = new TableColumn<Exam, String>();

	@FXML
	private TableColumn<Exam, Integer> Time_Exam_Column = new TableColumn<Exam, Integer>();

	@FXML
	private TableView<Exam> tableView_Exam = new TableView<Exam>();

	@FXML
	private TableView<Question> tableView_ExamAdd = new TableView<Question>();

	@FXML
	private TableColumn<Question, Integer> Chapter_ExamAdd;

	@FXML
	private TableColumn<Question, String> Content_ExamAdd;

	@FXML
	private Button CreateExam_ExamAdd = new Button();

	@FXML
	private DatePicker Date_Exam_Add = new DatePicker();

	@FXML
	private TextArea Desctiption_ExamAdd = new TextArea();

	@FXML
	private TableColumn<Question, Integer> Difficulty_ExamAdd;

	@FXML
	private TableColumn<Question, String> ID_ExamAdd;

	@FXML
	private TextField Name_ExamAdd = new TextField();

	@FXML
	private TextField Score_ExamAdd = new TextField();

	@FXML
	private Spinner<Integer> Time_ExamAdd = new Spinner<Integer>();

	@FXML
	private ListView<Question> listViewQuestion_Selected_ExamAdd = new ListView<Question>();

	@FXML
	private AnchorPane anchor_Infomation_Exam_ExamAdd;

	@FXML
	private ComboBox<Subject> comboBox_ChooseSubject_ExamAdd = new ComboBox<Subject>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listQuestion_Selected = new ArrayList<Question>();
		Score_ExamAdd.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	Score_ExamAdd.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		loadSpinner();
		loadExam();
		loadComboBoxInExamAdd();
		for(Subject sub: new QuestionManager().getSubjects(LoginController.teacher_Current))
		{
			subject_Exam.getItems().add(sub);
		}
		subject_Exam.setConverter(new StringConverter<Subject>() {
			@Override
			public String toString(Subject sub) {
				return sub == null ? "" : sub.getSubjectName();
			}

			@Override
			public Subject fromString(String s) {
				return null;
			}
		});
	}
	   

	private void loadComboBoxInExamAdd() {
		List<Subject> subjects = quesManager.getSubjects(LoginController.teacher_Current);

		for (Subject subj : subjects) {
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

			question_ListOfSubject = quesManager.getQuestionsForSubject(comboBox_ChooseSubject_ExamAdd.getValue());

			// Save subject current
			ExamController.subject_Current = comboBox_ChooseSubject_ExamAdd.getValue();

			tableView_ExamAdd.setItems(loadQuestion_tableView_ExamAdd(question_ListOfSubject));
		});

		// Load table view and set value
		try {
			SelectionModel<Question> selectionModel = tableView_ExamAdd.getSelectionModel();
			tableView_ExamAdd.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			selectedItems = tableView_ExamAdd.getSelectionModel().getSelectedItems();
			selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					if (selectedItems.size() == 1) 
						ExamController.question_Current = newValue;
						
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			SelectionModel<Exam> selectionModel = tableView_Exam.getSelectionModel();
			selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					ExamController.exam_Current = newValue;

					picktoTest.setVisible(true);

					setVisableView_Delete(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (exam_Current != null) {
			Load_Exam_ExamView();
		}
	}
	@FXML
    private void search_Exam(ActionEvent event) {
		String exam=textFile_Exam.getText();
		String subject = subject_Exam.getValue().getSubjectID();
		String date =DatePicker_Exam.getValue().toString();
		String pinner=String.valueOf(spinner_Exam.getValue());
		
		if(examManager.searchExams(exam, subject, date, pinner)==null)
			DisplayDialog_Notification.Dialog_Infomation("Null", "KH tim thAY", pinner);
		else {
			tableView_Exam.getItems().clear();
			List<Exam> list_Exam=examManager.searchExams(exam, subject, date, pinner);
			tableView_Exam.setItems(loadStudent_tableView(list_Exam));
		}
	}
	@FXML
	void button_PickToTest(ActionEvent event) {
		if(examManager.getQuestions(exam_Current))
		{			
			new MainController().receiveExam(exam_Current);
			new Start_TestController().receiveExam(exam_Current);
			DisplayDialog_Notification.Dialog_Infomation("Successfully",
					"Ban da chon exam nay hay nhan start test de bat dau bai kiem tra", getCurrentTime());
		}
		else {
			DisplayDialog_Notification.Dialog_Error("Null", "Khong co cau hoi", getCurrentTime());
		}
	}

	private ObservableList<Question> loadQuestion_tableView_ExamAdd(List<Question> list) {
		observableList_Question = FXCollections.observableArrayList(list);

		ID_ExamAdd.setCellValueFactory(new PropertyValueFactory<Question, String>("questionID"));
		Chapter_ExamAdd.setCellValueFactory(new PropertyValueFactory<Question, Integer>("chapter"));
		Difficulty_ExamAdd.setCellValueFactory(new PropertyValueFactory<Question, Integer>("difficulty"));
		Content_ExamAdd.setCellValueFactory(new PropertyValueFactory<Question, String>("content"));

		return observableList_Question;
	}

	private void loadExam() {
		List<Exam> examList = examManager.getExams(LoginController.teacher_Current);
		tableView_Exam.setItems(loadStudent_tableView(examList));
	}

	public StringProperty getSubjectNameStringProperty(Subject subject) {
		StringProperty name = new SimpleStringProperty(subject.getSubjectName());
		return name;
	}

	private ObservableList<Exam> loadStudent_tableView(List<Exam> list) {
		observableList = FXCollections.observableArrayList(list);

		ID_Exam_Column.setCellValueFactory(new PropertyValueFactory<Exam, DateTime>("examID"));
		Subject_Exam_Column
				.setCellValueFactory(cellData -> getSubjectNameStringProperty(cellData.getValue().getSubject()));
		DateTime_Exam_Column.setCellValueFactory(new PropertyValueFactory<Exam, DateTime>("startDateTime"));
		Time_Exam_Column.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("timeLimit"));
		Name_Exam_Column.setCellValueFactory(new PropertyValueFactory<Exam, String>("name"));

		return observableList;
	}

	private void loadSpinner() {
		SpinnerValueFactory<Integer> valueFatory = new SpinnerValueFactory.IntegerSpinnerValueFactory(15, 120);
		spinner_Exam.setValueFactory(valueFatory);
		spinner_Exam.setEditable(true);

		Time_ExamAdd.setValueFactory(valueFatory);

		SpinnerValueFactory<Integer> valueFatoryStartTime_Hours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
				23);
		SpinnerValueFactory<Integer> valueFatoryStartTime_Minutes = new SpinnerValueFactory.IntegerSpinnerValueFactory(
				0, 59);
		Spinner_Hours_ExamAdd.setValueFactory(valueFatoryStartTime_Hours);
		Spinner_Minutes_ExamAdd.setValueFactory(valueFatoryStartTime_Minutes);

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
		boolean checkList_Select=true;
		if(selectedItems.size()>1)
		{
				for(Question q: selectedItems)
				{
					if(listQuestion_Selected.contains(q))
						DisplayDialog_Notification.Dialog_Infomation("Notification", "Cau hoi da duoc chon", "cau hoi trung");					
					break;
				}		
			listQuestion_Selected.addAll(selectedItems);			
		}
		else if(selectedItems.size()==1)
		{
			if (listQuestion_Selected.contains(question_Current))
				DisplayDialog_Notification.Dialog_Infomation("Notification", "Cau hoi da duoc chon", "cau hoi trung");
			listQuestion_Selected.add(question_Current);			
		}
		else 
			checkList_Select=false;
		
		if(checkList_Select)
		{
			ObservableList<Question> observableListView_Question = FXCollections.observableArrayList(listQuestion_Selected);
			
			listViewQuestion_Selected_ExamAdd.getItems().clear();
			
			listViewQuestion_Selected_ExamAdd.setItems(observableListView_Question);
			
			listViewQuestion_Selected_ExamAdd.setCellFactory(param -> new ListCell<Question>() {
				@Override
				protected void updateItem(Question item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null && !empty) {
						setText("ID: " + item.getQuestionID() + ", " + "Chapter: " + item.getChapter() + ", "
								+ "Difficulty: " + item.getDifficulty() + ", " + "Correct Answers: "
								+ item.getCorrectAnswers().toString());
					} else {
						setText("");
					}
				}
			});
			listViewQuestion_Selected_ExamAdd.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			CreateExam_ExamAdd.setDisable(!checkListView());
			
			tableView_ExamAdd.getSelectionModel().clearSelection();
		}
		else 
			DisplayDialog_Notification.Dialog_Infomation("heheh","chon cau hoi di fen", getCurrentTime());
			
	}

	@FXML
	private void Delete_QuestionForExam_ExamAdd(ActionEvent event) {
		
		observableList_Question=listViewQuestion_Selected_ExamAdd.getItems();
		
		if(observableList_Question.size()==1)
		{
			int index = listViewQuestion_Selected_ExamAdd.getSelectionModel().getSelectedIndex();
			listViewQuestion_Selected_ExamAdd.getItems().remove(index);
			listQuestion_Selected.remove(index);
		}
		else if(observableList_Question.size()>1) {
			
			ObservableList<Integer> indices =
					listViewQuestion_Selected_ExamAdd.getSelectionModel().getSelectedIndices().sorted();

	            for (int k = indices.size() - 1; k >= 0; k--) {
	            	listViewQuestion_Selected_ExamAdd.getItems().remove(
	                        (int) indices.get(k));
	            	listQuestion_Selected.remove(k);
	            }

		}
		else {
			DisplayDialog_Notification.Dialog_Infomation("hehes", "Chon cau hoi de xoa di ba", getCurrentTime());
		}
		listViewQuestion_Selected_ExamAdd.getSelectionModel().clearSelection();
		
		CreateExam_ExamAdd.setDisable(!checkListView());
	}

	@FXML
	private void Delete_Exam_Exam(ActionEvent event) {
		if (!examManager.deleteExam(String.valueOf(exam_Current.getExamID()))) {
			DisplayDialog_Notification.Dialog_Error("Unsuccessful notification!", "Exam wasn't delete!",
					"Unsuccessful!");
		} else {
			Exam exam_Delete = tableView_Exam.getSelectionModel().getSelectedItem();
			tableView_Exam.getItems().remove(exam_Delete);
			DisplayDialog_Notification.Dialog_Infomation("Successful notification!", "Exam was deleted!",
					"Successful!");
		}
		CreateExam_ExamAdd.setDisable(!checkListView());
	}

	public static String getCurrentTime() {
		LocalDateTime now = LocalDateTime.now();

		// Define the format pattern
		String format = "yyyy-MM-dd HH:mm:ss"; // Customize the format as needed

		// Create a DateTimeFormatter object
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

		// Format the current date and time
		String formattedDateTime = now.format(formatter);
		return formattedDateTime;
	}

	@FXML
	private void buttonCreateExam(ActionEvent event) {
		try {
			int MaxScore = Integer.parseInt(Score_ExamAdd.getText());
			int timeLimit = Time_ExamAdd.getValue();
			String nameExam = Name_ExamAdd.getText();
			String desctiption = Desctiption_ExamAdd.getText();
			DateTime dateTimeID = new DateTime(getCurrentTime());
			boolean shuffed =shuffe_Question.isSelected() ? true:false;

			String year = String.valueOf(Date_Exam_Add.getValue().getYear());
			String month = String.valueOf(Date_Exam_Add.getValue().getMonthValue());
			String day = String.valueOf(Date_Exam_Add.getValue().getDayOfMonth());

			String hours = Spinner_Hours_ExamAdd.getValue() >= 10 ? String.valueOf(Spinner_Hours_ExamAdd.getValue())
					: "0" + String.valueOf(Spinner_Hours_ExamAdd.getValue());

			String minutes = Spinner_Minutes_ExamAdd.getValue() >= 10
					? String.valueOf(Spinner_Minutes_ExamAdd.getValue())
					: "0" + String.valueOf(Spinner_Minutes_ExamAdd.getValue());

			DateTime dateTimeStart_Time = new DateTime(year, month, day, hours, minutes);
			try {
				if (!examManager.addExam(new Exam(dateTimeID, subject_Current, dateTimeStart_Time, timeLimit, MaxScore,
						nameExam, desctiption, shuffed, listQuestion_Selected))) {
					DisplayDialog_Notification.Dialog_Error("Notification Error", "Exam wasn't created", "Error");
				} else
					DisplayDialog_Notification.Dialog_Infomation("Notification Successfully", "Exam was created",
							"Successful");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				DisplayDialog_Notification.Dialog_Error("Notification Error", "Exam wasn't created", "Error");
			}
		} catch (NumberFormatException e) {
			DisplayDialog_Notification.Dialog_Error("Error", "Time or timeStart or MaxScore must be number", "Error");
		}
	}

	private boolean checkListView() {
		return listQuestion_Selected == null || listQuestion_Selected.isEmpty() ? false : true;
	}

	@FXML
	private void checkDateTime_StartTest() {
		Date_Exam_Add.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isBefore(LocalDate.now())) {
				Date_Exam_Add.setValue(LocalDate.now());
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

	private void Load_Exam_ExamView() {

		String examName = exam_Current.getName();
		String SubjectName = exam_Current.getSubject().getSubjectName();
		String Date = exam_Current.getStartDateTime().toString();
		String Score = String.valueOf(exam_Current.getMaxScore());
		String timeLimit = String.valueOf(exam_Current.getTimeLimit());
		String desc = exam_Current.getDescription();

		Name_ExamView.setText(examName);
		Subject_ExamView.setText(SubjectName);
		Date_ExamView.setText(Date);
		Score_ExamView.setText(Score);
		TimeLimit_ExamView.setText(timeLimit);
		Desc_ExamView.setText(desc);

		examManager.getQuestions(exam_Current);
		if (exam_Current.getQuestions() != null) {
			List<Question> questions = exam_Current.getQuestions();
			int questions_size = questions.size();
			for (int i = 0; i < questions_size; i++) {
				Question question = questions.get(i);
				Label contentLabel = new Label();
				contentLabel.setText((i + 1) + ". " + question.getContent());
				contentLabel.setStyle("-fx-font-size:18 ; -fx-font-weight:bold");
				contentLabel.setWrapText(true);

				VBox_DisplayQuestion.getChildren().add(contentLabel);

				List<String> answers = question.getAnswers();
				int answers_size = answers.size();
				List<Integer> correctAns = question.getCorrectAnswers();
				for (int j = 0; j < answers_size; j++) {
					String answer = answers.get(j);
					Label answerLabel = new Label();
					answerLabel.setText(uiUtils.indexToLetter(j) + ". " + answer);
					answerLabel.setStyle("-fx-font-size:18");
					answerLabel.setWrapText(true);
					if (correctAns.contains(j + 1))
						answerLabel.setStyle("-fx-font-size:18;-fx-text-fill:green");
					VBox_DisplayQuestion.getChildren().add(answerLabel);
				}
			}

		}

	}

}