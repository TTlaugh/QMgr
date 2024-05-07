package business.services;

import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.exceptions.MysqlErrorNumbers;

import business.model.Exam;
import business.model.Teacher;
import data.ExamAccess;
import utils.SQLUtils;

public class ExamManager {
	
	public List<Exam> getExams(Teacher teacher) {
		try {
			return new ExamAccess().getAll(teacher.getTeacherID());
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	
	public boolean addExam(Exam newExam) throws SQLException {
		try {
			return new ExamAccess().insert(newExam);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
			if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new SQLException("ExamID: '"+newExam.getExamID()+"' already exists", e);
		}
		return false;
	}
	
	public boolean editExam(Exam newExam) {
		try {
			return new ExamAccess().update(newExam);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}
	
	public boolean deleteExam(String examID) {
		try {
			return new ExamAccess().delete(examID);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}
	
	public boolean getQuestions(Exam exam) {
		try {
			new ExamAccess().getQuestions(exam);
			return true;
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}
	
	public List<Exam> searchExams(String examID, String subjectID, String startDateTime, String timeLimit) {
		try {
			return new ExamAccess().searchExams(examID, subjectID, startDateTime, timeLimit);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}


}
