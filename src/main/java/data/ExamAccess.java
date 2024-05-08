package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import business.model.Exam;
import business.model.Teacher;
import utils.JsonUtils;
import utils.SQLUtils;

public class ExamAccess implements DataAccess<Exam> {

	private Connection connection;

	@Override
	public boolean insert(Exam exam) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement("INSERT INTO Exams VALUES (?,?,?,?,?,?,?,?,?)");
		pStatement.setString (1, exam.getExamID().toString());
		pStatement.setString (2, exam.getSubject().getSubjectID());
		pStatement.setString (3, exam.getStartDateTime().toString());
		pStatement.setInt    (4, exam.getTimeLimit());
		pStatement.setDouble (5, exam.getMaxScore());
		pStatement.setString (6, exam.getName());
		pStatement.setString (7, exam.getDescription());
		pStatement.setBoolean(8, exam.isShuffled());
		pStatement.setString (9, exam.getQuestionIDs());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Exam exam) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"UPDATE Exams SET"
						+ " SubjectID=?,"
						+ " StartDateTime=?,"
						+ " TimeLimit=?,"
						+ " MaxScore=?,"
						+ " Name=?,"
						+ " Description=?,"
						+ " IsShuffle=?,"
						+ " Questions=?"
						+ " WHERE ExamID=?");
		pStatement.setString (1, exam.getSubject().getSubjectID());
		pStatement.setString (2, exam.getStartDateTime().toString());
		pStatement.setInt    (3, exam.getTimeLimit());
		pStatement.setDouble (4, exam.getMaxScore());
		pStatement.setString (5, exam.getName());
		pStatement.setString (6, exam.getDescription());
		pStatement.setBoolean(7, exam.isShuffled());
		pStatement.setString (8, exam.getQuestions().toString());
		pStatement.setString (9, exam.getExamID().toString());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM Exams WHERE ExamID='" + primaryKeyValues[0] + "'") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	public void getTeacher(Exam exam) throws SQLException {
		exam.getSubject().setTeacher(get(Teacher.class,
				"SELECT Teachers.TeacherID, Person.* FROM Exams"
				+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID"
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID"
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID"
				+ "Exams.ExamID", exam.getExamID().toString()));
	}

	public void getQuestions(Exam exam) throws SQLException {
		Connection connection = SQLUtils.getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"SELECT QuestionIDs FROM Exams WHERE ExamID='"+exam.getExamID()+"'");
		List<Integer> questionIDsList = null;
		if (rs.next())
			try { questionIDsList = JsonUtils.jsonToList(rs.getString("QuestionIDs"), Integer.class);
			} catch (IOException e) { e.printStackTrace(); }
		QuestionAccess questionAccess = new QuestionAccess();
		for (Integer questionID : questionIDsList)
			exam.getQuestions().add(questionAccess.get(questionID.toString()));
		SQLUtils.closeConnection(connection);
	}

	@Override
	public Exam get(String... primaryKeyValues) throws SQLException {
		return get(Exam.class,
					"SELECT * FROM Exams"
					+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID",
					"Exams.ExamID", primaryKeyValues[0]);
	}

	public List<Exam> getAll(String teacherID) throws SQLException {
		return getList(Exam.class,
				"SELECT * FROM Exams"
				+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID",
				"Subjects.TeacherID", teacherID);
	}
	
	public List<Exam> searchExams(String examID, String subjectID, String startDateTime, String timeLimit) throws SQLException {
		return getList(Exam.class,
				"SELECT * FROM Exams"
				+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID",
				"Exams.ExamID", examID,
				"Exams.SubjectID", subjectID,
				"Exams.StartDateTime", startDateTime,
				"Exams.TimeLimit", timeLimit);
	}

}
