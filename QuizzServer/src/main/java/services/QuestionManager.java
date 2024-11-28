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
import utils.ExcelReader;
import utils.ExcelWriter;

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
				question.setSubjectId(subject.getSubjectId());
				createQuestion(question);
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
		for (Cell cell : row) {
			Object cellValue = getCellValue(cell);
			if (cellValue == null || cellValue.toString().isEmpty())
				continue;

            int colIndex = 0;
            question.setChapter(row.getCell(colIndex++).getStringCellValue());
            question.setDifficulty((int) row.getCell(colIndex++).getNumericCellValue());
            question.setContent(row.getCell(colIndex++).getStringCellValue());

            String answersString = row.getCell(colIndex++).getStringCellValue();
            List<Answer> answers = parseAnswers(answersString);

            String correctAnswersString = row.getCell(colIndex++).getStringCellValue();
            List<Integer> correctAnswers = parseCorrectAnswers(correctAnswersString, answers);	
            
            // Gắn danh sách câu trả lời và đáp án đúng vào câu hỏi
            for (int i = 0; i < answers.size(); i++) {
                Answer answer = answers.get(i);
                answer.setCorrect(correctAnswers.contains(i));
            }
            question.setAnswers(new ArrayList<>(answers));
		}
		return (T) question;
	}

    private List<Answer> parseAnswers(String answersString) {
        List<Answer> answers = new ArrayList<>();
        answersString = answersString.substring(1, answersString.length() - 1); // Bỏ [] khỏi chuỗi
        String[] parts = answersString.split("\", \"");
        for (String part : parts) {
            part = part.replace("\"", ""); // Loại bỏ dấu ngoặc kép
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