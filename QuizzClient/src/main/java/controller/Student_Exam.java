package controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import business.model.Exam;
import business.model.Question;
import business.model.SelectedQuestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utils.Notification;

public class Student_Exam implements Initializable {

	@FXML
	private AnchorPane anchor_Exam;

	@FXML
	private Label label_CountDown;
	@FXML
	private TextArea answerA = new TextArea();

	@FXML
	private TextArea answerB = new TextArea();

	@FXML
	private TextArea answerC = new TextArea();

	@FXML
	private TextArea answerD = new TextArea();

	@FXML
	private TextArea textArea_Content = new TextArea();

	@FXML
	private CheckBox checkBoxA = new CheckBox();

	@FXML
	private CheckBox checkBoxB = new CheckBox();

	@FXML
	private CheckBox checkBoxC = new CheckBox();

	@FXML
	private CheckBox checkBoxD = new CheckBox();
	@FXML
	private BorderPane start_Exam = new BorderPane();

	@FXML
	private ListView<SelectedQuestion> listView_Exam = new ListView<SelectedQuestion>();

	private List<SelectedQuestion> listSelect = new ArrayList<SelectedQuestion>();

	public static SelectedQuestion selectedQues;

	public static Question question_Current = new Question();

	@FXML
	private Label score_Sub = new Label();

	public static double score;

	private Parent root;

	@FXML
	private void submit(ActionEvent event) throws IOException {
		score = Student_Start.start_Client.submit(Student_Start.start_Client.getExam().getExamID().toString(),
				Student_Start.id, listSelect);
		Notification.Infomation("Bài nộp", "Bạn đã nộp bài", null);

		root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Submiss_student.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);

		Label score_Sub = (Label) root.lookup("#score_Sub");
		score_Sub.setText(String.valueOf(score));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Exam exam = Student_Start.start_Client.getExam();

		List<Question> questions = exam.getQuestions();

		for (Question question : questions) {
			listSelect.add(new SelectedQuestion(question, new ArrayList<Integer>()));
		}

		ObservableList<SelectedQuestion> observableListView_Question = FXCollections.observableArrayList(listSelect);

		listView_Exam.setItems(observableListView_Question);

		listView_Exam.setCellFactory(param -> new ListCell<SelectedQuestion>() {
			@Override
			protected void updateItem(SelectedQuestion item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null && !empty) {
					setText(" QUESTION ");
				} else {
					setText("");
				}
			}
		});

		listView_Exam.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			List<Integer> listSelect = new ArrayList<Integer>();

			DisPlayQuestion(newValue, listSelect);
		});
	}

	@FXML

	void save_Exam(ActionEvent event) {
		List<Integer> listSelect_CheckBox = new ArrayList<Integer>();
		if (checkBoxA.isSelected())
			listSelect_CheckBox.add(1);
		if (checkBoxB.isSelected())
			listSelect_CheckBox.add(2);
		if (checkBoxC.isSelected())
			listSelect_CheckBox.add(3);
		if (checkBoxD.isSelected())
			listSelect_CheckBox.add(4);

		SelectedQuestion selectedQues = listView_Exam.getSelectionModel().getSelectedItem();
		selectedQues.setSelectedAnswers(listSelect_CheckBox);

		listView_Exam.refresh();

		Notification.Infomation("Thông báo", "Câu hỏi đã được lưu", null);

	}

	private void DisPlayQuestion(SelectedQuestion selectedQuestion, List<Integer> listSelect) {
		clearChoice();

		listSelect = selectedQuestion.getSelectedAnswers();

		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i) == 1) {
				checkBoxA.setSelected(true);
			}
			if (listSelect.get(i) == 2) {
				checkBoxB.setSelected(true);
			}
			if (listSelect.get(i) == 3) {
				checkBoxC.setSelected(true);
			}
			if (listSelect.get(i) == 4) {
				checkBoxD.setSelected(true);
			}
		}

		textArea_Content.setText(selectedQuestion.getContent());
		List<TextArea> list = List.of(answerA, answerB, answerC, answerD);

		for (int i = 0; i < selectedQuestion.getAnswers().size(); i++) {
			list.get(i).setText(selectedQuestion.getAnswers().get(i));
		}
	}

	private void clearChoice() {
		checkBoxA.setSelected(false);
		checkBoxB.setSelected(false);
		checkBoxC.setSelected(false);
		checkBoxD.setSelected(false);
	}

	@FXML
	void start(ActionEvent event) throws IOException {

		String exam_date = Student_Start.start_Client.getExam().getStartDateTime().toString();
		long timeTaken = 0;
		SimpleDateFormat smpDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;
		try {
			start = smpDF.parse(exam_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date end = new Date(System.currentTimeMillis());
		timeTaken = end.getTime() - start.getTime();

		if (timeTaken <= 0) {
			Notification.Infomation("Thông báo", exam_date, "Giờ kiểm tra");
			return;
		}
		timeTaken = end.getTime()
				- (start.getTime() + Student_Start.start_Client.getExam().getTimeLimit() * 60 * 1000);
		if (timeTaken <= 0) {
			anchor_Exam.setVisible(true);
			start_Exam.setVisible(false);
			return;
		}
		Notification.Infomation("Thông báo", "Hết giờ kiểm tra", "Giờ kiểm tra");
		root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Student_Start.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);
	}
}
