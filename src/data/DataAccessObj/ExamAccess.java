package data.DataAccessObj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data.DataTransferObj.Exam;
import data.DataTransferObj.Teacher;
import utils.DataSourceFactory;
import utils.JsonUtils;

public class ExamAccess implements DataAccess<Exam> {

	private Connection connection;

	@Override
	public boolean insert(Exam exam) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement("INSERT INTO Exams VALUES (?,?,?,?,?,?,?,?,?)");
		pStatement.setString (1, exam.getExamID().toString());
		pStatement.setString (2, exam.getSubject().getSubjectID());
		pStatement.setString (3, exam.getStartDateTime().toString());
		pStatement.setInt    (4, exam.getTimeLimit());
		pStatement.setDouble (5, exam.getMaxScore());
		pStatement.setString (6, exam.getName());
		pStatement.setString (7, exam.getDescription());
		pStatement.setBoolean(8, exam.isShuffled());
		pStatement.setString (9, exam.getQuestions().toString());
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Exam exam) throws SQLException {
		connection = DataSourceFactory.getConnection();
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
						+ "WHERE ExamID=?");
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
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = DataSourceFactory.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM Exams WHERE ExamID='" + primaryKeyValues[0] + "'") >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	public static void getTeacher(Exam exam) throws SQLException {
		exam.getSubject().setTeacher(DataAccess.get(Teacher.class,
				"SELECT Teachers.TeacherID, Person.* FROM Exams"
				+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID"
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID"
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID"
				+ "Exams.ExamID", exam.getExamID().toString()));
	}
	
	public static void getQuestions(Exam exam) throws SQLException {
		Connection connection = DataSourceFactory.getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"SECLECT QuestionIDs FROM Exams WHERE ExamID='"+exam.getExamID()+"'");
		List<Integer> questionIDsList = null;
		if (rs.next())
			try { questionIDsList = JsonUtils.jsonToList(rs.getString(0), Integer.class);
			} catch (IOException e) { e.printStackTrace(); }
		QuestionAccess questionAccess = new QuestionAccess();
		for (Integer questionID : questionIDsList)
			exam.getQuestions().add(questionAccess.getOnly(questionID.toString()));
		DataSourceFactory.closeConnection(connection);
	}

	@Override
	public Exam getOnly(String... primaryKeyValues) throws SQLException {
		return DataAccess.get(Exam.class,
					"SELECT * FROM Exams"
					+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID",
					"Exams.ExamID", primaryKeyValues[0]);
	}

	@Override
	public List<Exam> getAll(String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Exams"
					+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID";
		return DataAccess.getList(Exam.class, selectFrom, columnName_values);
	}

	@Override
	public List<Exam> getLimit(int offset, int limit, String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Exams LIMIT " + offset + ", " + limit
					+ " INNER JOIN Subjects ON Exams.SubjectID = Subjects.SubjectID";
		return DataAccess.getList(Exam.class, selectFrom, columnName_values);
	}

}
