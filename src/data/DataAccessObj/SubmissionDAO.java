package data.DataAccessObj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DataTransferObj.SelectedQuestion;
import data.DataTransferObj.Submission;
import utils.DataSourceFactory;

public class SubmissionDAO implements DataAccess<Submission> {
	
	private Connection connection;
	
	@Override
	public boolean insert(Submission submission) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement("INSERT INTO Submissions VALUES (?,?,?,?,?)");
		pStatement.setString(1, submission.getExam().getExamID().toString());
		pStatement.setString(2, submission.getStudent().getStudentID());
		pStatement.setInt   (3, submission.getTimeTaken());
		pStatement.setDouble(4, submission.getScore());
		pStatement.setString(5, submission.getAnswerSelectedsJSON());
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Submission submission) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"UPDATE Submissions SET"
						+ "TimeTaken=?,"
						+ "Score=?,"
						+ "AnswerSelecteds=?"
						+ "WHERE ExamID=? AND StudentID=?");
		pStatement.setInt   (1, submission.getTimeTaken());
		pStatement.setDouble(2, submission.getScore());
		pStatement.setString(3, submission.getAnswerSelectedsJSON());
		pStatement.setString(4, submission.getExam().getExamID().toString());
		pStatement.setString(5, submission.getStudent().getStudentID());
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"DELETE FROM Submissions WHERE ExamID=? AND StudentID=?");
		pStatement.setString(1, primaryKeyValues[0]);
		pStatement.setString(2, primaryKeyValues[1]);
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}
	
	public List<SelectedQuestion> getSelectedQuestions() {
		List<SelectedQuestion> selectedQuestions = new ArrayList<SelectedQuestion>();

		return selectedQuestions;
	}

	@Override
	public Submission getOnly(String... primaryKeyValues) throws SQLException {
		return DataAccess.get(Submission.class,
					"SELECT * FROM Submissions"
					+ " INNER JOIN Exams ON Submissions.ExamID = Exams.ExamID"
					+ " INNER JOIN Students ON Submissions.StudentID = Students.StudentID"
					+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID"
					+ " INNER JOIN Person ON Students.PersonID = Person.PersonID",
					"Submissions.ExamID", primaryKeyValues[0],
					"Submissions.StudentID", primaryKeyValues[1]);
	}

	@Override
	public List<Submission> getAll(String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Submissions"
					+ " INNER JOIN Exams ON Submissions.ExamID = Exams.ExamID"
					+ " INNER JOIN Students ON Submissions.StudentID = Students.StudentID"
					+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID"
					+ " INNER JOIN Person ON Students.PersonID = Person.PersonID";
		return DataAccess.getList(Submission.class, selectFrom, columnName_values);
	}

	@Override
	public List<Submission> getLimit(int offset, int limit, String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Submissions LIMIT " + offset + ", " + limit
					+ " INNER JOIN Exams ON Submissions.ExamID = Exams.ExamID"
					+ " INNER JOIN Students ON Submissions.StudentID = Students.StudentID"
					+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID"
					+ " INNER JOIN Person ON Students.PersonID = Person.PersonID";
		return DataAccess.getList(Submission.class, selectFrom, columnName_values);
	}

}
