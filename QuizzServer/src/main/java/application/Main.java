package application;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.QuestionDAO;
import DTO.Answer;
import DTO.Question;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import utils.Constant;
import utils.SQLUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			primaryStage.getIcons().add(new Image("/imgs/icon.png"));
			primaryStage.setTitle("Quizz Server");
			primaryStage.show();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root, Constant.ScreenSize.WIDTH, Constant.ScreenSize.HEIGHT);

			primaryStage.setMinWidth(1300);
			primaryStage.setMinHeight(900);
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);

			primaryStage.setOnCloseRequest(event -> {
				Platform.exit();
				System.exit(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// SQLUtils.getConnection(); //test connection

		// Test tạo câu hỏi
		ArrayList<Answer> answerList = new ArrayList<>();
		answerList.add(new Answer(1, 11, "Trả lời 1 cho câu hỏi demo", true));
		answerList.add(new Answer(2, 11, "Trả lời 2 cho câu hỏi demo", false));
		answerList.add(new Answer(3, 11, "Trả lời 3 cho câu hỏi demo", false));
		Question question = new Question(20, 1, "Chapter 1", 1, "Câu hỏi demo 1", answerList, false);
		QuestionDAO questionDAO = new QuestionDAO();
		try {
			questionDAO.insert(question);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Đọc thử kết quả
		try {
			System.out.println(questionDAO.get("8"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		launch(args);
	}
}
