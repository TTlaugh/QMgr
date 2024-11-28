package services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import data.QuestionDAO;
import model.Answer;
import model.Question;
import model.Subject;
import utils.CheckCheckBox;
import utils.ExcelReader;
import utils.ExcelWriter;
import utils.Notification;
import utils.StringNormalization;
import utils.StringValidate;

public class QuestionManager {
    private static QuestionManager instance = null;

    public static QuestionManager getInstance() {
        if (instance == null) {
            instance = new QuestionManager();
        }
        return instance;
    }

    public boolean createQuestion(Question question) {
        return new QuestionDAO().create(question);
    }

    public boolean updateQuestion(Question question) {
        return new QuestionDAO().update(question);
    }

    public boolean deleteQuestion(int questionId) {
        return new QuestionDAO().delete(questionId);
    }

    public Question getQuestion(int questionId) {
        return new QuestionDAO().getByID(questionId);
    }

    public ArrayList<Question> getAllQuestions() {
        return new QuestionDAO().getAll();
    }

    public ArrayList<Question> getAllQuestionsBySubject(int subjectId) {
        return new QuestionDAO().getAllBySubject(subjectId);
    }

    public ArrayList<Question> searchQuestions(int subjectId, String keyWord) {
        return new QuestionDAO().searchQuestions(subjectId, keyWord);
    }

    public boolean importQuestions(Subject subject, String excelFilePath) throws SQLException {
		try {
			List<Question> questions = new QuestionExcelReader().readExcel(excelFilePath);
			for (Question question : questions) {
                if (question != null) {
                    question.setSubjectId(subject.getSubjectId());
                    createQuestion(question);
                }
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

    public boolean exportQuestions(Subject subject, String excelFilePath) {
        int sbjID = subject.getSubjectId();
		try {
			new QuestionExcelWriter().writeExcel(
                    sbjID + " | " + subject.getSubjectName(),
					getAllQuestionsBySubject(sbjID),
					excelFilePath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}

class QuestionExcelReader extends ExcelReader {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getData(Row row) {
		Question question = new Question();
		int colIndex = 0;

        String chapter = row.getCell(colIndex++).getStringCellValue();
        chapter = StringNormalization.removeDuplicateSpaces(chapter);
        if (chapter.length() == 0 || chapter == null || chapter == "") {
            Notification.Error("Error", "Import question failed because Chapter is empty.");
            return null;
        }
        if (!StringValidate.CheckLengthInRange(chapter, 1, 255)) {
            Notification.Error("Error", "Import question failed because Chapter is too long.");
            return null;
        }


        int difficulty = (int) row.getCell(colIndex++).getNumericCellValue();
        if (difficulty > 5 || difficulty < 1) {
            Notification.Error("Error", "Import question failed because Difficulty í out of 1-5.");
            return null;
        }

        String content = row.getCell(colIndex++).getStringCellValue();
        content = StringNormalization.removeDuplicateSpaces(content);
        if (content.length() == 0 || content == null || content == "") {
            Notification.Error("Error", "Import question failed because Content is empty.");
            return null;
        }
        if (!StringValidate.CheckLengthInRange(chapter, 1, 1000)) {
            Notification.Error("Error", "Import question failed because Content is too long.");
            return null;
        }
        
        question.setChapter(chapter);
        question.setDifficulty(difficulty);
        question.setContent(content);
        
        String answersStr = row.getCell(colIndex++).getStringCellValue();
        if(!StringValidate.CheckMatchRegex(answersStr, "^\\[\"[^\"]*\"(,\\s*\"[^\"]*\")*\\]$")){
            Notification.Error("Error", "Import question failed because Answers have incorrect format.");
            return null;
        }
        List<Answer> answers = parseAnswers(answersStr);

        String correctAnswersString = row.getCell(colIndex++).getStringCellValue();
        if(!StringValidate.CheckMatchRegex(correctAnswersString, "^\\[\\d+(,\\s*\\d+)*\\]$")){
            Notification.Error("Error", "Import question failed because Correct Answers have incorrect format.");
            return null;
        }
        List<Integer> correctAnswers = parseCorrectAnswers(correctAnswersString, answers);	
        
        // Gắn danh sách câu trả lời và đáp án đúng vào câu hỏi
        for (int i = 0; i < answers.size(); i++) {
            Answer answer = answers.get(i);
            answer.setCorrect(correctAnswers.contains(i));
        }
        question.setAnswers(new ArrayList<>(answers));

		return (T) question;
	}

    private List<Answer> parseAnswers(String answersString) {
        List<Answer> answers = new ArrayList<>();
        answersString = answersString.substring(1, answersString.length() - 1); // Bỏ [] khỏi chuỗi
        String[] parts = answersString.split("\", \"");
        for (String part : parts) {
            part = part.replace("\"", ""); // Loại bỏ dấu ngoặc kép
            part = StringNormalization.removeDuplicateSpaces(part);
            Answer answer = new Answer();
            answer.setContent(part);
            answers.add(answer);
        }
        return answers;
    }

    private List<Integer> parseCorrectAnswers(String correctAnswersString, List<Answer> answers) {
        List<Integer> correctAnswers = new ArrayList<>();
        correctAnswersString = correctAnswersString.substring(1, correctAnswersString.length() - 1); // Bỏ [] khỏi chuỗi
        if (!correctAnswersString.isEmpty()) {
            String[] indices = correctAnswersString.split(", ");
            for (String index : indices) {
                correctAnswers.add(Integer.parseInt(index));
            }
        }
        return correctAnswers;
    }
}

class QuestionExcelWriter extends ExcelWriter {
	@Override
	public void writeHeader(Sheet sheet, int rowIndex) {
		CellStyle cellStyle = createStyleForHeader(sheet);
		Row row = sheet.createRow(rowIndex);
		Cell cell = null;
		cell = row.createCell(0); cell.setCellStyle(cellStyle); cell.setCellValue("Chapter");
		cell = row.createCell(1); cell.setCellStyle(cellStyle); cell.setCellValue("Difficulty");
		cell = row.createCell(2); cell.setCellStyle(cellStyle); cell.setCellValue("Question");
		cell = row.createCell(3); cell.setCellStyle(cellStyle); cell.setCellValue("Answers");
		cell = row.createCell(4); cell.setCellStyle(cellStyle); cell.setCellValue("Correct Answers");
	}

	@Override
	public <T> void writeData(T data, Row row) {
		Question question = (Question) data;
		Cell cell = null; int colIndex = 0;
		cell = row.createCell(colIndex++); cell.setCellValue(question.getChapter());
		cell = row.createCell(colIndex++); cell.setCellValue(question.getDifficulty());
		cell = row.createCell(colIndex++); cell.setCellValue(question.getContent());

        ArrayList<String> answerList = new ArrayList<String>();
        ArrayList<Integer> correctAnswerList = new ArrayList<Integer>();
        ArrayList<Answer> questionList = question.getAnswers();
        for(int i=0; i<questionList.size(); i++){
            Answer currentAnswer = questionList.get(i);
            answerList.add('"' + currentAnswer.getContent() + '"');
            if (currentAnswer.isCorrect()) correctAnswerList.add(i);
        }
		cell = row.createCell(colIndex++); cell.setCellValue(answerList.toString());
		cell = row.createCell(colIndex++); cell.setCellValue(correctAnswerList.toString());
	}
}