package business.services;

import java.sql.SQLException;
import java.util.List;

import business.model.Exam;
import data.ExamAccess;
import utils.SQLUtils;

public class ExamManager {
	
	public List<Exam> getExams() {
		try {
			return new ExamAccess().getAll();
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	
	public boolean addExam(Exam newExam) {
		try {
			return new ExamAccess().insert(newExam);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
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

}
