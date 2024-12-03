package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import components.Answer_card;
import components.Button_Question;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Answer;
import model.Answer_Select;
import model.HostExam;
import model.Question;

public class TestHostExam implements Initializable {

    @FXML
    private FlowPane FlowPane_Question;

    @FXML
    private StackPane StackPane_DoHostExam;

    @FXML
    private StackPane StackPane_ResultHostExam;

    @FXML
    private Label lb_Question = new Label();

    @FXML
    private Label lb_ScoreStudent = new Label();

    @FXML
    private VBox VBox_Answer = new VBox();

    public static HostExam hostExam = Connect_Server.client.getHostExam();

    public static ArrayList<Question> listQuestions = hostExam.getExamQuestions();

    ArrayList<Answer_Select> answer_selects = new ArrayList<Answer_Select>();

    public static Button button_prev = null;

    Date start;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        start = new Date();

        this.FlowPane_Question.getChildren().clear();

        for (Question question : listQuestions) {

            ArrayList<Answer> answers = question.getAnswers();

            for (Answer answer : answers) {
                Boolean isCorrect = answer.isCorrect();
                answer.setCorrect(false);
                answer_selects.add(new Answer_Select(answer, isCorrect));
            }
        }

        Question questionFirst = listQuestions.get(0);

        lb_Question.setText(questionFirst.getContent());

        for (int i = 0; i < questionFirst.getAnswers().size(); i++) {

            addAnswerVbox(questionFirst.getAnswers().get(i));

        }

        for (int i = 0; i < listQuestions.size(); i++) {

            Button_Question button_Question = new Button_Question(String.valueOf(i + 1));

            Insets margin = new Insets(0, 6, 6, 0);

            FlowPane.setMargin(button_Question.getButton(), margin);

            this.FlowPane_Question.getChildren().addAll(button_Question.getButton());

            setOnActionButton(button_Question);
        }
        this.FlowPane_Question.getChildren().get(0).setStyle("-fx-background-color:#ffffff");

    }

    void setOnActionButton(Button_Question button_Question) {
        button_Question.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FlowPane_Question.getChildren().get(0).setStyle("-fx-background-color:#DBE8FF");

                button_Question.getButton()
                        .setStyle("-fx-background-color:#ffffff");

                if (button_prev != null) {
                    button_prev.setStyle("-fx-background-color:#DBE8FF");
                }

                int index = Integer.parseInt(button_Question.getButton().getText()) - 1;

                VBox_Answer.getChildren().clear();

                lb_Question.setText(listQuestions.get(index).getContent());

                ArrayList<Answer> answers = listQuestions.get(index).getAnswers();

                for (int i = 0; i < answers.size(); i++) {

                    addAnswerVbox(answers.get(i));
                }

                button_prev = button_Question.getButton();

            }
        });
    }

    void addAnswerVbox(Answer answer) {

        Answer_card answer_card = new Answer_card(answer.getContent(), answer.isCorrect());

        answer_card.getCheckBox().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer.setCorrect(answer_card.getCheckBox().isSelected());
            }
        });

        VBox_Answer.getChildren().addAll(answer_card.getHbox());
    }

    @FXML
    void btn_Submit(ActionEvent event) {

        Double score = Connect_Server.client.submit(listQuestions, answer_selects, start);

        StackPane_DoHostExam.setVisible(false);

        StackPane_ResultHostExam.setVisible(true);

        lb_ScoreStudent.setText(String.valueOf(score));

    }

    @FXML
    void btn_ExportSubmission(ActionEvent event) {
        Platform.exit();
    }

}
