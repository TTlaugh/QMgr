package business.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mysql.cj.exceptions.MysqlErrorNumbers;

import business.model.Question;
import business.model.Subject;
import business.model.Teacher;
import data.QuestionAccess;
import data.SubjectAccess;
import utils.ExcelReader;
import utils.ExcelWriter;
import utils.SQLUtils;

public class QuestionManager {
	
	public List<Subject> getSubjects(Teacher teacher) {
		try {
			return new SubjectAccess().getAll(teacher.getTeacherID());
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	
	public boolean addSubject(Subject newSubject) throws SQLException {
		try {
			return new SubjectAccess().insert(newSubject);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
			if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new SQLException("SubjectID: '"+newSubject.getSubjectID()+"' already exists", e);
		}
		return false;
	}

	public boolean editSubject(Subject newSubject) {
		try {
			return new SubjectAccess().update(newSubject);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

	public boolean deleteSubject(String subjectID) {
		try {
			return new SubjectAccess().delete(subjectID);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

	public List<Question> getQuestionsForSubject(Subject subject) {
		try {
			return new QuestionAccess().getQuestionsOfSubject(subject);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	
	public List<Question> searchQuestionInSubject(Subject subject, String content, String chapter, String difficulty) {
		try {
			return new QuestionAccess().searchQuestionsFromSubject(subject, content, chapter, difficulty);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	
	public boolean addQuestion(Question newQuestion) throws SQLException {
		try {
			return new QuestionAccess().insert(newQuestion);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
			if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new SQLException("QuestionID: '"+newQuestion.getQuestionID()+"' already exists", e);
		}
		return false;
	}
	
	public boolean editQuestion(Question newQuestion) {
		try {
			return new QuestionAccess().update(newQuestion);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}
	
	public boolean deleteQuestion(String questionID) {
		try {
			return new QuestionAccess().delete(questionID);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}
	
	public boolean importQuestions(Subject subject, String excelFilePath) throws SQLException {
		try {
			List<Question> questions = new QuestionExcelReader().readExcel(excelFilePath);
			for (Question question : questions) {
				question.setSubject(subject);
				addQuestion(question);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean exportQuestions(Subject subject, String excelFilePath) {
		try {
			new QuestionExcelWriter().writeExcel(
					subject.getSubjectID() + " | " + subject.getSubjectName(),
					getQuestionsForSubject(subject),
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
			int columnIndex = cell.getColumnIndex();
			switch (columnIndex) {
				case 0:
					question.setChapter(new BigDecimal((double) cellValue).intValue());
					break;
				case 1:
					question.setDifficulty(new BigDecimal((double) cellValue).intValue());
					break;
				case 2:
					question.setContent((String) getCellValue(cell));
					break;
				case 3:
				case 4:
				case 5:
				case 6:
					question.getAnswers().add((String) getCellValue(cell));
					break;
				case 7:
					String strValue = ((String) getCellValue(cell));
					strValue = strValue.substring(1, strValue.length() - 1);
					List<Integer> correctAnswers =
							Arrays.stream(strValue.split("[\\s,]+"))
							.map(Integer::valueOf)
							.collect(Collectors.toList());
					question.setCorrectAnswers(correctAnswers);
					break;
				default:
					break;
			}
		}
		return (T) question;
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
		cell = row.createCell(2); cell.setCellStyle(cellStyle); cell.setCellValue("Content");
		cell = row.createCell(3); cell.setCellStyle(cellStyle); cell.setCellValue("Answer1");
		cell = row.createCell(4); cell.setCellStyle(cellStyle); cell.setCellValue("Answer2");
		cell = row.createCell(5); cell.setCellStyle(cellStyle); cell.setCellValue("Answer3");
		cell = row.createCell(6); cell.setCellStyle(cellStyle); cell.setCellValue("Answer4");
		cell = row.createCell(7); cell.setCellStyle(cellStyle); cell.setCellValue("Correct Answer");
	}

	@Override
	public <T> void writeData(T data, Row row) {
		Question question = (Question) data;
		Cell cell = null; int colIndex = 0;
		cell = row.createCell(colIndex++); cell.setCellValue(question.getChapter());
		cell = row.createCell(colIndex++); cell.setCellValue(question.getDifficulty());
		cell = row.createCell(colIndex++); cell.setCellValue(question.getContent());
		cell = row.createCell(colIndex++); cell.setCellValue(question.getAnswers().get(0));
		cell = row.createCell(colIndex++); cell.setCellValue(question.getAnswers().get(1));
		cell = row.createCell(colIndex++); cell.setCellValue(question.getAnswers().get(2));
		cell = row.createCell(colIndex++); cell.setCellValue(question.getAnswers().get(3));
		cell = row.createCell(colIndex++); cell.setCellValue(question.getCorrectAnswers().toString());
	}
}
