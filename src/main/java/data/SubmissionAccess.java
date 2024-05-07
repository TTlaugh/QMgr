package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.model.SelectedQuestion;
import business.model.Submission;
import utils.SQLUtils;

public class SubmissionAccess implements DataAccess<Submission> {

	private Connection connection;

	@Override
	public boolean insert(Submission submission) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement("INSERT INTO Submissions VALUES (?,?,?,?,?)");
		pStatement.setString(1, submission.getExam().getExamID().toString());
		pStatement.setString(2, submission.getStudent().getStudentID());
		pStatement.setInt   (3, submission.getTimeTaken());
		pStatement.setDouble(4, submission.getScore());
		pStatement.setString(5, submission.getAnswerSelectedsJSON());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Submission submission) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"UPDATE Submissions SET"
						+ " TimeTaken=?,"
						+ " Score=?,"
						+ " AnswerSelecteds=?"
						+ " WHERE ExamID=? AND StudentID=?");
		pStatement.setInt   (1, submission.getTimeTaken());
		pStatement.setDouble(2, submission.getScore());
		pStatement.setString(3, submission.getAnswerSelectedsJSON());
		pStatement.setString(4, submission.getExam().getExamID().toString());
		pStatement.setString(5, submission.getStudent().getStudentID());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"DELETE FROM Submissions WHERE ExamID=? AND StudentID=?");
		pStatement.setString(1, primaryKeyValues[0]);
		pStatement.setString(2, primaryKeyValues[1]);
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public Submission get(String... primaryKeyValues) throws SQLException {
		return get(Submission.class,
					"SELECT * FROM Submissions"
					+ " INNER JOIN Exams ON Submissions.ExamID = Exams.ExamID"
					+ " INNER JOIN Students ON Submissions.StudentID = Students.StudentID"
					+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID"
					+ " INNER JOIN Person ON Students.PersonID = Person.PersonID",
					"Submissions.ExamID", primaryKeyValues[0],
					"Submissions.StudentID", primaryKeyValues[1]);
	}

	public List<Submission> getAll(String teacherID) throws SQLException {
		return getList(Submission.class,
				"SELECT * FROM Submissions"
				+ " INNER JOIN Exams ON Submissions.ExamID = Exams.ExamID"
				+ " INNER JOIN Students ON Submissions.StudentID = Students.StudentID"
				+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID"
				+ " INNER JOIN Person ON Students.PersonID = Person.PersonID",
				"Subjects.TeacherID", teacherID);
	}

	public List<SelectedQuestion> getSelectedQuestions(Submission submission) throws SQLException {
		List<SelectedQuestion> selectedQuestions = new ArrayList<SelectedQuestion>();
		Map<String, List<Integer>> answerSelectedsMap = submission.getAnswerSelectedsMap();
		QuestionAccess questionAccess = new QuestionAccess();
		for (Map.Entry<String, List<Integer>> entry : answerSelectedsMap.entrySet())
			selectedQuestions.add(new SelectedQuestion(
							questionAccess.get(entry.getKey()),
							entry.getValue()));
		return selectedQuestions;
	}
	
	public List<Submission> searchSubmissions(String subjectID, String examID, String studentID) throws SQLException {
		return getList(Submission.class,
				"SELECT * FROM Submissions"
				+ " INNER JOIN Exams ON Submissions.ExamID = Exams.ExamID"
				+ " INNER JOIN Students ON Submissions.StudentID = Students.StudentID"
				+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID"
				+ " INNER JOIN Person ON Students.PersonID = Person.PersonID",
				"Subjects.SubjectID", subjectID,
				"Exams.ExamID", examID,
				"Students.StudentID", studentID);
	}

}
