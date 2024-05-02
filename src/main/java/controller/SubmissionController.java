package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.model.Exam;
import business.model.Student;
import business.model.Submission;
import business.services.SubmissionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SubmissionController implements Initializable {
		private ObservableList<Submission> observableList ;
		
	  	@FXML
	    private TableColumn<Submission, String> ExamID_Submission;

	    @FXML
	    private TableColumn<Submission, Double> Score_Submission;

	    @FXML
	    private TableColumn<Submission, String> StudentID_Submission;

	    @FXML
	    private TableColumn<Submission, Integer> TTaken_Submission;

	    @FXML
	    private Button delete_Submission;

	    @FXML
	    private TableView<Submission> tableView_Submission;

	    private static SubmissionManager submissionManager = new SubmissionManager();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadData_Submission();
	}
	private void loadData_Submission() {
		List<Submission> listSubmission = submissionManager.getSubmissions(new LoginController().teacher_Current);
		tableView_Submission.setItems(loadStudent_tableView(listSubmission));
	}
	private ObservableList loadStudent_tableView(List<Submission> list) {
	    observableList = FXCollections.observableArrayList(list);	    

	    TTaken_Submission.setCellValueFactory(new PropertyValueFactory<Submission,Integer>("timeTaken")); 
	    Score_Submission.setCellValueFactory(new PropertyValueFactory<Submission,Double>("score"));
	    ExamID_Submission.setCellValueFactory(cellData -> cellData.getValue().getExamID());
	    StudentID_Submission.setCellValueFactory(cellData -> cellData.getValue().getStudentID());
	    
	    return observableList;
	}
    @FXML
    void button_Delete_Submission(ActionEvent event) {

    }
	
}
