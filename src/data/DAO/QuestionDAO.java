package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import data.DTO.Question;
import utils.DataSourceFactory;

public class QuestionDAO implements DAO<Question> {

	private Connection connection;

	public QuestionDAO() {
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Question question) {
		try {
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
			if (pStatement.executeUpdate() >= 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Question question) {
		try {
			PreparedStatement pStatement = connection
					.prepareStatement(
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
					"DELETE FROM Questions WHERE QuestionID='" + id + "'") >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Question> get(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
