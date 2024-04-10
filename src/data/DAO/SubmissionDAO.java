package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import data.DTO.Submission;
import utils.DataSourceFactory;

public class SubmissionDAO implements DAO<Submission> {
	
	private Connection connection;
	
	public SubmissionDAO() {
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Submission submission) {
		try {
			PreparedStatement pStatement = connection.prepareStatement("INSERT INTO Submissions VALUES (?,?,?,?,?,?)");
			pStatement.setString(1, submission.getSubmissionID());
			pStatement.setString(2, submission.getExam().getExamID().toString());
			pStatement.setString(3, submission.getStudent().getStudentID());
			pStatement.setInt   (4, submission.getTimeTaken());
			pStatement.setDouble(5, submission.getScore());
			pStatement.setString(6, submission.getAnswerSelecteds().toString());
			if (pStatement.executeUpdate() >= 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Submission submission) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"UPDATE Submissions SET"
					+ "ExamID=?,"
					+ "StudentID=?,"
					+ "TimeTaken=?,"
					+ "Score=?,"
					+ "AnswerSelecteds=?"
					+ "WHERE SubmissionID=?"
					);
			pStatement.setString(1, submission.getExam().getExamID().toString());
			pStatement.setString(2, submission.getStudent().getStudentID());
			pStatement.setInt   (3, submission.getTimeTaken());
			pStatement.setDouble(4, submission.getScore());
			pStatement.setString(5, submission.getAnswerSelecteds().toString());
			pStatement.setString(6, submission.getSubmissionID());
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
					"DELETE FROM Submissions WHERE SubmissionID=?") >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Submission> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Submission> get(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
