package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import business.model.Exam;
import business.model.Group;
import business.model.SelectedQuestion;
import business.model.Student;
import business.model.Subject;
import business.model.Submission;
import business.services.QuestionManager;
import business.services.SubmissionManager;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import utils.DisplayDialog_Notification;

public class SubmissionController implements Initializable {
	private Scene scene = null;

	private AnchorPane anchor;

	private ObservableList<Submission> observableList;
	
	private List<Submission> listSubmission;
	@FXML
	private VBox vBox_Submissview = new VBox();

	@FXML
	private TableColumn<Submission, String> ExamID_Submission = new TableColumn<Submission, String>();

	@FXML
	private TableColumn<Submission, Double> Score_Submission = new TableColumn<Submission, Double>();

	@FXML
	private TableColumn<Submission, String> StudentID_Submission = new TableColumn<Submission, String>();

	@FXML
	private TableColumn<Submission, Integer> TTaken_Submission = new TableColumn<Submission, Integer>();

    @FXML
    private TableColumn<Submission, String> Subjects_Submission=new TableColumn<Submission,String>();

	@FXML
	private Button view_Submission = new Button();
	
	@FXML
	private Button delete_Submission = new Button();

	@FXML
	private TableView<Submission> tableView_Submission = new TableView<Submission>();
	@FXML
	private Label ExamID_SubmissView = new Label();

	@FXML
	private Label score_SubmissView = new Label();

	@FXML
	private Label studentID_SubmissView = new Label();

    @FXML
    private TextField examID_textFiled=new TextField();

    @FXML
    private Button search_Submiss=new Button();

    @FXML
    private TextField student_ID_textField=new TextField();

    @FXML
    private ComboBox<Subject> subjectt_ComboBox=new ComboBox<Subject>();

	private static SubmissionManager submissionManager = new SubmissionManager();

	public static Submission submission_Current;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if (submission_Current != null && listSubmission!=null) {
			
			delete_Submission.setVisible(true);
			view_Submission.setVisible(true);
		}
		for(Subject sub:new QuestionManager().getSubjects(LoginController.teacher_Current)) {
			subjectt_ComboBox.getItems().add(sub);
		}
		subjectt_ComboBox.setConverter(new StringConverter<Subject>() {
			@Override
			public String toString(Subject sub) {
				return sub == null ? "" : sub.getSubjectName();
			}

			@Override
			public Subject fromString(String s) {
				return null;
			}
		});
		try {
			SelectionModel<Submission> selectionModel = tableView_Submission.getSelectionModel();
			selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					submission_Current = newValue;
					delete_Submission.setVisible(true);
					view_Submission.setVisible(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loadData_Submission();
		if(submission_Current!=null)
		load_SubmissView();
	}
    @FXML
    void button_Search_Submiss(ActionEvent event) {
    	String subject_ID=String.valueOf(subjectt_ComboBox.getValue().getSubjectID()); 
    	String exam_ID =examID_textFiled.getText() ;
    	String student_ID=	student_ID_textField.getText();
    	
    	if(submissionManager.searchSubmissions(subject_ID, exam_ID, student_ID)==null)
    		DisplayDialog_Notification.Dialog_Infomation("null", "Hong tim thay", student_ID);
    	else {
    		tableView_Submission.getItems().clear();
    		List<Submission> list=submissionManager.searchSubmissions(subject_ID, exam_ID, student_ID);
    		tableView_Submission.setItems(loadStudent_tableView(list));
    	}
    }

	private void load_SubmissView() {
		studentID_SubmissView.setText(submission_Current.getStudent().getStudentID());
		ExamID_SubmissView.setText(submission_Current.getExam().getExamID().toString());
		score_SubmissView.setText(String.valueOf(submission_Current.getScore()));
		List<SelectedQuestion> list = submissionManager.getSelectedQuestions(submission_Current);
		
		for (SelectedQuestion submissSelect : list) {
			Separator separator = new Separator();
			Label labelQues = new Label(submissSelect.getContent());
			Label labelChosen = new Label();
			Label labelCorrect = new Label();
			String chosen = "";
			String correct = "";
			for (int i : submissSelect.getSelectedAnswers()) {
				if (i == 0)
					chosen += "A ";
				else if (i == 1)
					chosen += "B ";
				else if (i == 2)
					chosen += "C ";
				else if (i == 3)
					chosen += "D ";
			}
			labelChosen.setText("Chosen : " + chosen);
			for (int i : submissSelect.getCorrectAnswers()) {
				if (i == 0)
					correct += "A ";
				else if (i == 1)
					correct += "B ";
				else if (i == 2)
					correct += "C ";
				else if (i == 3)
					correct += "D ";
			}
			labelCorrect.setText("Corect : " + correct);
			labelChosen.setStyle("-fx-font-size: 18px");
			labelCorrect.setStyle("-fx-font-size: 18px");
			labelQues.setWrapText(true);
			labelQues.setStyle(chosen.equalsIgnoreCase(correct) ? "-fx-text-fill:green;-fx-font-size: 18px"
					: "-fx-text-fill:red;-fx-font-size: 18px");

			vBox_Submissview.getChildren().addAll(separator, labelQues, labelChosen, labelCorrect);
		}

	}

	private void loadData_Submission() {
		listSubmission = submissionManager.getSubmissions(LoginController.teacher_Current);
		tableView_Submission.setItems(loadStudent_tableView(listSubmission));
	}
	
	public StringProperty getStudentID(Student student) {
		StringProperty id = new SimpleStringProperty(student.getStudentID());
		return id;
	}
	
	public StringProperty getExamID(Exam exam) {
		StringProperty exam_ID = new SimpleStringProperty(exam.getExamID().toString());
		return exam_ID;
	}
	public StringProperty getSubjectName(Subject subject) {
		StringProperty subject_Name = new SimpleStringProperty(subject.getSubjectName());
		return subject_Name;
	}

	private ObservableList<Submission> loadStudent_tableView(List<Submission> list) {
		observableList = FXCollections.observableArrayList(list);

		TTaken_Submission.setCellValueFactory(new PropertyValueFactory<Submission, Integer>("timeTaken"));
		Score_Submission.setCellValueFactory(new PropertyValueFactory<Submission, Double>("score"));
		ExamID_Submission.setCellValueFactory(cellData -> getExamID(cellData.getValue().getExam()));
		StudentID_Submission.setCellValueFactory(cellData -> getStudentID(cellData.getValue().getStudent()));
		Subjects_Submission.setCellValueFactory(cellData -> getSubjectName(cellData.getValue().getExam().getSubject()));
		
		return observableList;
	}

	@FXML
	void button_Delete_Submission(ActionEvent event) {
		Submission sub_Delete = tableView_Submission.getSelectionModel().getSelectedItem();
		if (!submissionManager.deleteSubmission(sub_Delete)) {
			DisplayDialog_Notification.Dialog_Error("Unsuccessful notification!", "Submission wasn't delete!",
					"Unsuccessful!");
		} else {
			tableView_Submission.getItems().remove(sub_Delete);
			DisplayDialog_Notification.Dialog_Infomation("Successful notification!", "Submission was deleted!",
					"Successful!");
		}
	}

	@FXML
	void buttonViewSubmission(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Submiss_view.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);
	}

	@FXML
	void back_Submission(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Submiss.fxml")).load();
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

}
