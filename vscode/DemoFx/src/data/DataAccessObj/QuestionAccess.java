package data.DataAccessObj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import data.DataTransferObj.Question;
import data.DataTransferObj.Teacher;
import utils.DataSourceFactory;

public class QuestionAccess implements DataAccess<Question> {

	private Connection connection;

	@Override
	public boolean insert(Question question) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"INSERT INTO Questions VALUES (?,?,?,?,?,?,?,?,?,?)");
		pStatement.setString(1, question.getQuestionID());
		pStatement.setString(2, question.getSubject().getSubjectID());
		pStatement.setInt   (3, question.getChapter());
		pStatement.setInt   (4, question.getDifficulty());
		pStatement.setString(5, question.getContent());
		pStatement.setString(6, question.getAnswers()[0]);
		pStatement.setString(7, question.getAnswers()[2]);
		pStatement.setString(8, question.getAnswers()[3]);
		pStatement.setString(9, question.getAnswers()[4]);
		pStatement.setString(10,question.getCorrectAnswers().toString());
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Question question) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"UPDATE Questions SET"
						+ "SubjectID=?,"
						+ "Chapter=?,"
						+ "Difficulty=?,"
						+ "Content=?,"
						+ "Answer1=?,"
						+ "Answer2=?,"
						+ "Answer3=?,"
						+ "Answer4=?,"
						+ "CorrectAnswers=?"
						+ "WHERE QuestionID=?");
		pStatement.setString(1, question.getSubject().getSubjectID());
		pStatement.setInt   (2, question.getChapter());
		pStatement.setInt   (3, question.getDifficulty());
		pStatement.setString(4, question.getContent());
		pStatement.setString(5, question.getAnswers()[0]);
		pStatement.setString(6, question.getAnswers()[2]);
		pStatement.setString(7, question.getAnswers()[3]);
		pStatement.setString(8, question.getAnswers()[4]);
		pStatement.setString(9, question.getCorrectAnswers().toString());
		pStatement.setString(10, question.getQuestionID());
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = DataSourceFactory.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM Questions WHERE QuestionID='" + primaryKeyValues[0] + "'") >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	public static void getTeacher(Question question) throws SQLException {
		question.getSubject().setTeacher(DataAccess.get(Teacher.class,
				"SELECT Teachers.TeacherID, Person.* FROM Questions"
				+ " INNER JOIN Subjects ON Questions.SubjectID = Subjects.SubjectID"
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID"
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID",
				"Questions.QuestionID", question.getQuestionID()));
	}

	@Override
	public Question getOnly(String... primaryKeyValues) throws SQLException {
		return DataAccess.get(Question.class,
				"SELECT * FROM Questions"
				+ " INNER JOIN Subjects ON Questions.SubjectID = Subjects.SubjectID",
				"Questions.QuestionID", primaryKeyValues[0]);
	}

	@Override
	public List<Question> getAll(String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Questions"
				+ " INNER JOIN Subjects ON Questions.SubjectID = Subjects.SubjectID";
		return DataAccess.getList(Question.class, selectFrom, columnName_values);
	}

	@Override
	public List<Question> getLimit(int offset, int limit, String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Questions LIMIT " + offset + ", " + limit
				+ " INNER JOIN Subjects ON Questions.SubjectID = Subjects.SubjectID";
		return DataAccess.getList(Question.class, selectFrom, columnName_values);
	}

}
