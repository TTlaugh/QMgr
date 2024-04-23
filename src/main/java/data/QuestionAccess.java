package main.java.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import main.java.business.model.Question;
import main.java.business.model.Subject;
import main.java.business.model.Teacher;
import main.java.utils.SQLUtils;

public class QuestionAccess implements DataAccess<Question> {

	private Connection connection;

	@Override
	public boolean insert(Question question) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"INSERT INTO Questions"
						+ " (SubjectID, Chapter, Difficulty, Content, Answer1, Answer2, Answer3, Answer4, CorrectAnswers)"
						+ " VALUES (?,?,?,?,?,?,?,?,?)");
		pStatement.setString(1, question.getSubject().getSubjectID());
		pStatement.setInt(2, question.getChapter());
		pStatement.setInt(3, question.getDifficulty());
		pStatement.setString(4, question.getContent());
		pStatement.setString(5, question.getAnswers().get(0));
		pStatement.setString(6, question.getAnswers().get(1));
		pStatement.setString(7, question.getAnswers().get(2));
		pStatement.setString(8, question.getAnswers().get(3));
		pStatement.setString(9, question.getCorrectAnswers().toString());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Question question) throws SQLException {
		connection = SQLUtils.getConnection();
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
		pStatement.setInt(2, question.getChapter());
		pStatement.setInt(3, question.getDifficulty());
		pStatement.setString(4, question.getContent());
		pStatement.setString(5, question.getAnswers().get(0));
		pStatement.setString(6, question.getAnswers().get(1));
		pStatement.setString(7, question.getAnswers().get(2));
		pStatement.setString(8, question.getAnswers().get(3));
		pStatement.setString(9, question.getCorrectAnswers().toString());
		pStatement.setString(10, question.getQuestionID());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM Questions WHERE QuestionID='" + primaryKeyValues[0] + "'") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public Question get(String... primaryKeyValues) throws SQLException {
		return get(Question.class,
				"SELECT * FROM Questions"
						+ " INNER JOIN Subjects ON Questions.SubjectID = Subjects.SubjectID",
				"Questions.QuestionID", primaryKeyValues[0]);
	}

	public void getTeacher(Question question) throws SQLException {
		question.getSubject().setTeacher(get(Teacher.class,
				"SELECT Teachers.TeacherID, Person.* FROM Questions"
						+ " INNER JOIN Subjects ON Questions.SubjectID = Subjects.SubjectID"
						+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID"
						+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID",
				"Questions.QuestionID", question.getQuestionID()));
	}

	public List<Question> getQuestionsOfSubject(Subject subject) throws SQLException {
		return getList(Question.class,
				"SELECT * FROM Questions",
				"SubjectID", subject.getSubjectID());
	}
}
