package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import data.DTO.Exam;
import utils.DataSourceFactory;

public class ExamDAO implements DAO<Exam> {

	private Connection connection;

	public ExamDAO() {
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Exam exam) {
		try {
			PreparedStatement pStatement = connection.prepareStatement("INSERT INTO Exams VALUES (?,?,?,?,?,?,?,?,?)");
			pStatement.setString (1, exam.getExamID().toString());
			pStatement.setString (2, exam.getSubject().getSubjectID());
			pStatement.setString (3, exam.getStartDateTime().toString());
			pStatement.setInt    (4, exam.getTimeLimit());
			pStatement.setDouble (5, exam.getMaxScore());
			pStatement.setString (6, exam.getName());
			pStatement.setString (7, exam.getDescription());
			pStatement.setBoolean(8, exam.isShuffle());
			pStatement.setString (9, exam.getQuestions().toString());
			if (pStatement.executeUpdate() >= 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Exam exam) {
			try {
			PreparedStatement pStatement = connection.prepareStatement(
						"UPDATE Exams SET"
						+ "SubjectID=?,"
						+ "StartDateTime=?,"
						+ "TimeLimit=?,"
						+ "MaxScore=?,"
						+ "Name=?,"
						+ "Description=?,"
						+ "IsShuffle=?,"
						+ "Questions=?"
						+ "WHERE ExamID=?"
						);
				pStatement.setString (1, exam.getSubject().getSubjectID());
				pStatement.setString (2, exam.getStartDateTime().toString());
				pStatement.setInt    (3, exam.getTimeLimit());
				pStatement.setDouble (4, exam.getMaxScore());
				pStatement.setString (5, exam.getName());
				pStatement.setString (6, exam.getDescription());
				pStatement.setBoolean(7, exam.isShuffle());
				pStatement.setString (8, exam.getQuestions().toString());
				pStatement.setString (9, exam.getExamID().toString());
				if (pStatement.executeUpdate() >= 1) return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}

	@Override
	public boolean delete(String id) {
		try {
			if (connection.createStatement().executeUpdate(
					"DELETE FROM Exams WHERE ExamID='" + id + "'") >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Exam> getAll() {
		return null;
	}

	@Override
	public Optional<Exam> get(String id) {
		return Optional.empty();
	}

}
