package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import business.model.Question;
import business.model.Subject;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import utils.Notification;
import utils.OpenFileExplorer;

public class QuestionController implements Initializable {
	private Scene scene = null;

	private AnchorPane anchor;

	public static List<Subject> subjects;

	private QuestionManager quesManager = new QuestionManager();

	private static Question Question_Current;

	private ToggleGroup group_Button_Difficulty_QuestionView = new ToggleGroup();

	private ToggleGroup group_Button_Difficulty_QuestionAdd = new ToggleGroup();

	private List<Question> question_ListOfSubject;

	private ObservableList<Question> observableList_Question;

	private List<RadioButton> listRadioButton = new ArrayList<RadioButton>();

	private List<TextArea> listTextArea = new ArrayList<TextArea>();

	private List<CheckBox> listCheckBox = new ArrayList<CheckBox>();

	public static Subject subject_Current;

	private static File file_Current;
	@FXML
	private HBox hBox_Question = new HBox();
	@FXML
	private TextField chapter_Question = new TextField();
	@FXML
	private ComboBox<String> dificult_Question = new ComboBox<String>();

	@FXML
	private TextField searchQuestion = new TextField();

	@FXML
	private Button Export_Question_Disable = new Button();

	@FXML
	private Button Import_Question_Disable = new Button();

	@FXML
	private AnchorPane anchor_CreateSubject_Question = new AnchorPane();

	@FXML
	private ComboBox<Subject> choose_Subject_Question = new ComboBox<Subject>();

	@FXML
	private Button delete_Subject_Question = new Button();

	@FXML
	private Button create_Subject_Question = new Button();

	@FXML
	private TextField subject_ID_Question = new TextField();

	@FXML
	private TextField subject_Name_Question = new TextField();

	@FXML
	private TableView<Question> tableView_Question = new TableView<Question>();

	@FXML
	private TableColumn<Question, Integer> Question_Chapter_Column = new TableColumn<Question, Integer>();

	@FXML
	private TableColumn<Question, String> Question_Difficulty_Column = new TableColumn<Question, String>();

	@FXML
	private TableColumn<Question, String> Question_Content_Column = new TableColumn<Question, String>();

	@FXML
	private TableColumn<Question, String> Question_ID_Column = new TableColumn<Question, String>();
	@FXML
	private Button button_Add_Question = new Button();

	@FXML
	private Button button_Delete_Question = new Button();

	@FXML
	private Button button_View_Question = new Button();

	@FXML
	private TextArea answerA_QuestionView = new TextArea();

	@FXML
	private TextArea answerB_QuestionView = new TextArea();

	@FXML
	private TextArea answerC_QuestionView = new TextArea();

	@FXML
	private TextArea answerD_QuestionView = new TextArea();

	@FXML
	private CheckBox checkBoxA_QuestionView = new CheckBox();

	@FXML
	private CheckBox checkBoxB_QuestionView = new CheckBox();

	@FXML
	private CheckBox checkBoxC_QuestionView = new CheckBox();

	@FXML
	private CheckBox checkBoxD_QuestionView = new CheckBox();

	@FXML
	private Button buttonEdit_QuestionView = new Button();

	@FXML
	private Button buttonSave_QuestionView = new Button();

	@FXML
	private TextField chapter_QuestionView = new TextField();

	@FXML
	private TextArea content_QuestionView = new TextArea();

	@FXML
	private Label questionID_QuestionView = new Label();

	@FXML
	private RadioButton radioEasy_QuestionView = new RadioButton();

	@FXML
	private RadioButton radioHard_QuestionView = new RadioButton();

	@FXML
	private RadioButton radioMedium_QuestionView = new RadioButton();

	@FXML
	private TextArea answerA_QuestionAdd = new TextArea();

	@FXML
	private TextArea answerB_QuestionAdd = new TextArea();

	@FXML
	private TextArea answerC_QuestionAdd = new TextArea();

	@FXML
	private TextArea answerD_QuestionAdd = new TextArea();

	@FXML
	private TextField chapter_QuestionAdd = new TextField();

	@FXML
	private CheckBox checkBoxA_QuestionAdd = new CheckBox();

	@FXML
	private CheckBox checkBoxB_QuestionAdd = new CheckBox();

	@FXML
	private CheckBox checkBoxC_QuestionAdd = new CheckBox();

	@FXML
	private CheckBox checkBoxD_QuestionAdd = new CheckBox();

	@FXML
	private TextArea content_QuestionAdd = new TextArea();

	@FXML
	private RadioButton radioEasy_QuestionAdd = new RadioButton();

	@FXML
	private RadioButton radioHard_QuestionAdd = new RadioButton();

	@FXML
	private RadioButton radioMedium_QuestionAdd = new RadioButton();

	public void process_SubjectCurrent() {
		button_Add_Question.setVisible(true);
		dificult_Question.setVisible(true);
		chapter_Question.setVisible(true);
		hBox_Question.setVisible(true);
		choose_Subject_Question.setValue(subject_Current);
		question_ListOfSubject = quesManager.getQuestionsForSubject(subject_Current);
		tableView_Question.setItems(loadQuestion_tableView(question_ListOfSubject));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		buttonRadioGroup_Add();
		button_Add_Question.setVisible(false);
		if (subject_Current != null) {
			process_SubjectCurrent();
		}

		chapter_QuestionAdd.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue) {
				if (!newValue.matches("\\d*")) {
					chapter_QuestionAdd.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		chapter_QuestionView.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue) {
				if (!newValue.matches("\\d*")) {
					chapter_QuestionView.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		subjects = quesManager.getSubjects(LoginController.teacher_Current);

		for (Subject subj : subjects) {
			choose_Subject_Question.getItems().add(subj);
		}

		choose_Subject_Question.setConverter(new StringConverter<Subject>() {
			@Override
			public String toString(Subject subj) {
				return subj == null ? "" : "ID_Subject : " + subj.getSubjectID() + " -- " + subj.getSubjectName();
			}

			@Override
			public Subject fromString(String s) {
				return null;
			}
		});

		setVisibleButton_View_Del_Question(false);

		choose_Subject_Question.setOnAction(envent -> {
			setDisableEX_IM(false);
			delete_Subject_Question.setDisable(false);
			question_ListOfSubject = quesManager.getQuestionsForSubject(choose_Subject_Question.getValue());
			// Save subject current
			QuestionController.subject_Current = choose_Subject_Question.getValue();

			tableView_Question.setItems(loadQuestion_tableView(question_ListOfSubject));
			ObservableList<String> options = FXCollections.observableArrayList("Easy", "Medium", "Hard");

			dificult_Question.setItems(options);
			button_Add_Question.setVisible(true);
			dificult_Question.setVisible(true);
			chapter_Question.setVisible(true);
			hBox_Question.setVisible(true);
		});

		try {
			SelectionModel<Question> selectionModel = tableView_Question.getSelectionModel();
			selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {

				if (newValue != null) {
					QuestionController.Question_Current = newValue;
					setVisibleButton_View_Del_Question(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Question_Current != null)
			loadQuestionDetail(Question_Current);
	}

	private void buttonRadioGroup_View() {
		radioEasy_QuestionView.setToggleGroup(group_Button_Difficulty_QuestionView);
		radioHard_QuestionView.setToggleGroup(group_Button_Difficulty_QuestionView);
		radioMedium_QuestionView.setToggleGroup(group_Button_Difficulty_QuestionView);
	}

	private void buttonRadioGroup_Add() {
		radioEasy_QuestionAdd.setToggleGroup(group_Button_Difficulty_QuestionAdd);
		radioHard_QuestionAdd.setToggleGroup(group_Button_Difficulty_QuestionAdd);
		radioMedium_QuestionAdd.setToggleGroup(group_Button_Difficulty_QuestionAdd);
	}

	private void setVisibleButton_View_Del_Question(boolean b) {
		button_Delete_Question.setVisible(b);
		button_View_Question.setVisible(b);
	}

	private ObservableList<Question> loadQuestion_tableView(List<Question> list) {
		observableList_Question = FXCollections.observableArrayList(list);

		Question_ID_Column.setCellValueFactory(new PropertyValueFactory<Question, String>("questionID"));
		Question_Chapter_Column.setCellValueFactory(new PropertyValueFactory<Question, Integer>("chapter"));
		Question_Difficulty_Column.setCellValueFactory(cellData -> getSubject(cellData.getValue()));
		Question_Content_Column.setCellValueFactory(new PropertyValueFactory<Question, String>("content"));

		return observableList_Question;
	}

	private StringProperty getSubject(Question current_Question) {
		String level = "Easy";

		if (current_Question.getDifficulty() == 2)
			level = "Medium";
		else if (current_Question.getDifficulty() == 3)
			level = "Hard";

		return new SimpleStringProperty(level);

	}

	public void Question_Tranfer_QuestionView_Quizz(ActionEvent event) throws IOException {
		if (Question_Current != null) {
			AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question_view.fxml")).load();
			setAnchor(insidePane);
			scene = (Scene) ((Node) event.getSource()).getScene();
			anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
			anchor.getChildren().clear();
			anchor.getChildren().add(insidePane);
		}
	}

	public void displayDifficulty(Question current_Question) {
		for (int i = 0; i < listRadioButton.size(); i++) {
			if (i + 1 == current_Question.getDifficulty()) {
				listRadioButton.get(i).setSelected(true);
				break;
			}
		}
	}

	public void displayAnswer(Question current_Question) {
		for (int i = 0; i < current_Question.getAnswers().size(); i++)
			listTextArea.get(i).setText(current_Question.getAnswers().get(i));
	}

	public void displayCorrectAnswer(Question current_Question) {
		for (Integer i : current_Question.getCorrectAnswers()) {
			for (int j = 0; j < listCheckBox.size(); j++) {
				if (i == j + 1) {
					listCheckBox.get(j).setSelected(true);
					break;
				}
			}
		}
	}

	private void loadQuestionDetail(Question current_Question) {
		// display questionID
		questionID_QuestionView.setText(current_Question.getQuestionID());
		// display chapter
		chapter_QuestionView.setText(String.valueOf(current_Question.getChapter()));
		// add radio button to list
		listRadioButton = List.of(radioEasy_QuestionView, radioMedium_QuestionView, radioHard_QuestionView);
		// display difficulty
		displayDifficulty(current_Question);
		// displayContent
		content_QuestionView.setText(current_Question.getContent());
		// add TextArea to list
		listTextArea = List.of(answerA_QuestionView, answerB_QuestionView, answerC_QuestionView, answerD_QuestionView);
		// display answer
		displayAnswer(current_Question);
		// add checkBox to list
		listCheckBox = List.of(checkBoxA_QuestionView, checkBoxB_QuestionView, checkBoxC_QuestionView,
				checkBoxD_QuestionView);
		// display correct Answer
		displayCorrectAnswer(current_Question);
	}

	public void Question_Tranfer_QuestionAdd_Quizz(ActionEvent event) throws IOException {

		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question_add.fxml")).load();
		setAnchor(insidePane);
		scene = (Scene) ((Node) event.getSource()).getScene();
		anchor = (AnchorPane) scene.lookup("#AnchorPaneLayout");
		anchor.getChildren().clear();
		anchor.getChildren().add(insidePane);

	}

	public void Back_Question(ActionEvent event) throws IOException {
		AnchorPane insidePane = new FXMLLoader(getClass().getResource("/fxml/Question.fxml")).load();
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
	void Delete_QuestionFromQuestion(ActionEvent event) {
		Boolean isDeleteQuestion = quesManager.deleteQuestion(Question_Current.getQuestionID());
		if (!isDeleteQuestion) {
			tableView_Question.getSelectionModel().clearSelection();
			Notification.Error(Notification.Error, "Câu hỏi không thể xóa ");
		} else {
			tableView_Question.getItems().remove(Question_Current);
			Notification.Infomation(Notification.Successfully, "Câu hỏi đã được xoá");
		}

	}

	@FXML
	public void buttonEdit_QuestionView(ActionEvent event) {
		boolean edit_Status = false;
		setDisable_display_QuestionDetail(edit_Status);
		buttonRadioGroup_View();
	}

	private void setDisable_display_QuestionDetail(boolean b) {
		buttonSave_QuestionView.setVisible(true);
		chapter_QuestionView.setDisable(b);
		radioEasy_QuestionView.setDisable(b);
		radioMedium_QuestionView.setDisable(b);
		radioHard_QuestionView.setDisable(b);
		content_QuestionView.setDisable(b);
		answerA_QuestionView.setDisable(b);
		answerB_QuestionView.setDisable(b);
		answerC_QuestionView.setDisable(b);
		answerD_QuestionView.setDisable(b);
		checkBoxA_QuestionView.setDisable(b);
		checkBoxB_QuestionView.setDisable(b);
		checkBoxC_QuestionView.setDisable(b);
		checkBoxD_QuestionView.setDisable(b);
	}

	@FXML
	private void buttonSave_DataChange_QuestionView(ActionEvent event) {

		Boolean check_FillFull = checkSelectCheckBox(listCheckBox) && checkTextArea(listTextArea);
		if (!check_FillFull) {
			Notification.Error(Notification.Error, Notification.FullFill);
			return;
		}
		try {
			Boolean check_EditQuestion = quesManager
					.editQuestion(new Question(questionID_QuestionView.getText(), subject_Current,
							Integer.parseInt(chapter_QuestionView.getText()), radioSelect_Question(listRadioButton),
							content_QuestionView.getText(), listAnswer_Question(listTextArea),
							listCorrectAnswer_Question(listCheckBox)));
			if (!check_EditQuestion) {
				Notification.Error(Notification.Unsuccessfully, "Câu hỏi không được thây đổi");
				return;
			}
			Notification.Infomation(Notification.Successfully, "Câu hỏi đã được thay đổi");
		} catch (Exception e) {
			Notification.Error(Notification.Error, "Lỗi chỉnh sửa câu hỏi");
		}
	}

	private int radioSelect_Question(List<RadioButton> listRadioButton) {
		for (int i = 0; i < listRadioButton.size(); i++) {
			if (listRadioButton.get(i).isSelected()) {
				return i + 1;
			}
		}
		return 1;
	}

	private List<String> listAnswer_Question(List<TextArea> listTextArea) {
		List<String> listAnswer = new ArrayList<String>();
		for (TextArea i : listTextArea) {
			listAnswer.add(i.getText());
		}
		return listAnswer;
	}

	private List<Integer> listCorrectAnswer_Question(List<CheckBox> listCheckBox) {
		List<Integer> listCorrectAnswer = new ArrayList<Integer>();
		for (int i = 0; i < listCheckBox.size(); i++) {
			if (listCheckBox.get(i).isSelected()) {
				listCorrectAnswer.add(i + 1);
			}
		}
		return listCorrectAnswer;
	}

	private boolean checkSelectCheckBox(List<CheckBox> listCheckBox) {
		boolean check_BoxSelectedAll = false;
		for (CheckBox b : listCheckBox) {
			if (b.isSelected())
				check_BoxSelectedAll = true;
		}
		return check_BoxSelectedAll;
	}

	private boolean checkTextArea(List<TextArea> listTextArea) {
		boolean check_AreaAll = true;
		for (TextArea a : listTextArea) {
			if (a.getText() == null || a.getText() == "")
				check_AreaAll = false;
		}
		return check_AreaAll;
	}

	@FXML
	void save_DataAdd_QuestionAdd(ActionEvent event) {
		List<RadioButton> listRadioButton = List.of(radioEasy_QuestionAdd, radioMedium_QuestionAdd,
				radioHard_QuestionAdd);

		List<TextArea> listTextArea = List.of(answerA_QuestionAdd, answerB_QuestionAdd, answerC_QuestionAdd,
				answerD_QuestionAdd);

		List<CheckBox> listCheckBox = List.of(checkBoxA_QuestionAdd, checkBoxB_QuestionAdd, checkBoxC_QuestionAdd,
				checkBoxD_QuestionAdd);

		String content = content_QuestionAdd.getText();
		Boolean isFull = checkSelectCheckBox(listCheckBox) && checkTextArea(listTextArea) && content != null
				&& content != "";

		if (!isFull) {
			Notification.Error(Notification.Error, Notification.FullFill);
			return;
		}
		try {
			Boolean check_AddQuestion = quesManager
					.addQuestion(
							new Question(null, subject_Current, Integer.parseInt(chapter_QuestionAdd.getText()),
									radioSelect_Question(listRadioButton), content_QuestionAdd.getText(),
									listAnswer_Question(listTextArea), listCorrectAnswer_Question(listCheckBox)));
			if (!check_AddQuestion) {
				Notification.Error(Notification.Unsuccessfully, "Thêm câu hỏi thất bại");
				return;
			}
			Notification.Infomation(Notification.Successfully,
					"Thêm câu hỏi thành công");

		} catch (Exception e) {
			Notification.Error(Notification.Error, "Thêm câu hỏi bị lỗi");
		}

	}

	public boolean checkInfomationSubject() {
		return subject_ID_Question.getText() == null || subject_ID_Question.getText() != "" &&
				subject_Name_Question.getText() == null || subject_Name_Question.getText() == "";
	}

	@FXML
	void button_Create_Subject_Question(ActionEvent event) {

		if (checkInfomationSubject()) {
			Notification.Infomation(Notification.Default, "SubjectID hoặc tên rỗng");
			return;
		}
		try {
			Boolean check_AddSubject = quesManager
					.addSubject(new Subject(subject_ID_Question.getText(), LoginController.teacher_Current,
							subject_Name_Question.getText()));
			if (!check_AddSubject) {
				Notification.Error(Notification.Unsuccessfully, "Thêm môn thất bại");
				return;
			}
			Notification.Infomation(Notification.Successfully,
					"Thêm môn thành công");
			choose_Subject_Question.getItems().add(new Subject(subject_ID_Question.getText(),
					LoginController.teacher_Current, subject_Name_Question.getText()));
		} catch (SQLException e) {
			Notification.Error(Notification.Unsuccessfully,
					"The subject added failed because it already exists!");
		}

	}

	@FXML
	void buttonSearchQuestion(ActionEvent event) {
		String content = searchQuestion.getText();
		String chapter = chapter_Question.getText();
		String difficulty = dificult_Question.getValue();
		switch (difficulty) {
			case "Hard":
				difficulty = "3";
				break;
			case "Medium":
				difficulty = "2";
				break;
			case "Easy":
				difficulty = "1";
				break;
			default:
				difficulty = "1";
		}

		List<Question> list_SearchQuestionInSubject = quesManager.searchQuestionInSubject(subject_Current, content,
				chapter,
				difficulty);
		if (list_SearchQuestionInSubject.isEmpty() || list_SearchQuestionInSubject == null)
			Notification.Infomation(Notification.Default, "Không có dữ liệu");
		else {
			tableView_Question.getItems().clear();
			tableView_Question.setItems(loadQuestion_tableView(list_SearchQuestionInSubject));
		}
	}

	@FXML
	void button_Delete_Subject_Question(ActionEvent event) {
		Boolean check_DeleteSubject = quesManager.deleteSubject(subject_Current.getSubjectID());
		if (!check_DeleteSubject) {
			Notification.Error(Notification.Unsuccessfully, "Xóa môn thất bại");
			return;
		}
		Notification.Infomation(Notification.Successfully, "Xóa môn thành công");
		choose_Subject_Question.getItems().remove(subject_Current);

	}

	@FXML
	private boolean Import_Question(ActionEvent event) {
		file_Current = OpenFileExplorer.Open(event);
		if (file_Current != null) {
			String check_xlsx = file_Current.getPath().substring(file_Current.getPath().lastIndexOf(".") + 1);
			try {
				Boolean check_FormatExel = check_xlsx.equalsIgnoreCase("xlsx") || check_xlsx.equalsIgnoreCase("xls");
				Boolean selectFile = quesManager.importQuestions(subject_Current, file_Current.getPath());
				if (!check_FormatExel && !selectFile) {
					Notification.Error(Notification.Unsuccessfully, "Tệp không hợp lệ hoặc chưa chọn");
					return false;
				}
				tableView_Question.getItems().clear();
				Notification.Infomation(Notification.Successfully, "Nhập tệp thành công");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		question_ListOfSubject = quesManager.getQuestionsForSubject(subject_Current);

		tableView_Question.setItems(loadQuestion_tableView(question_ListOfSubject));

		return true;
	}

	@FXML
	private void Export_Question(ActionEvent event) {

		file_Current = OpenFileExplorer.Save(event);
		String fileNameExel = file_Current.getPath();
		Boolean check_ExportExel = quesManager.exportQuestions(subject_Current, fileNameExel);
		if (!check_ExportExel) {
			Notification.Infomation(Notification.Unsuccessfully, "Xuất tệp exel thất bại");
			return;
		}
		if (Notification.Comfrim(Notification.Successfully,
				"Xuất tệp thành công và mở tệp").getResult() == ButtonType.YES)
			OpenFileExel_Export(new File(fileNameExel));
	}

	private void OpenFileExel_Export(File fileOpen) {

		if (file_Current == null) {
			Notification.Error(Notification.Error, "Không đọc được tệp");
			return;
		}
		try {
			Desktop.getDesktop().open(fileOpen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setDisableEX_IM(boolean b) {
		Import_Question_Disable.setDisable(b);
		Export_Question_Disable.setDisable(b);
	}
}
