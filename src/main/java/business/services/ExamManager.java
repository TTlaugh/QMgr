package main.java.business.services;

import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.exceptions.MysqlErrorNumbers;

import main.java.business.model.Exam;
import main.java.data.ExamAccess;
import main.java.utils.SQLUtils;

public class ExamManager {

	public List<Exam> getExams() {
		try {
			return new ExamAccess().getAll();
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
				throw new SQLException("ExamID: '" + newExam.getExamID() + "' already exists", e);
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
