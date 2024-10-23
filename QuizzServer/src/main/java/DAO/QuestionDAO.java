package DAO;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DTO.Answer;
import DTO.Question;
import utils.SQLUtils;

public class QuestionDAO implements iDataAccess<Question> {
    private Connection connection;
    private Gson gson = new Gson();

    @Override
    public boolean insert(Question question) throws SQLException {
        String answers = gson.toJson(question.getAnswers());
        connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"INSERT INTO Questions"
				+ " (QuestionID, SubjectID, Chapter, Difficulty, Content, Answers, Archived)"
				+ " VALUES (?,?,?,?,?,?,?)");
		pStatement.setInt(1, question.getQuestionId());
		pStatement.setInt(2, question.getSubjectId());
		pStatement.setString(3, question.getChapter());
		pStatement.setInt(4, question.getDifficulty());
		pStatement.setString(5, question.getContent());
		pStatement.setString(6, answers);
		pStatement.setBoolean(7, question.isArchive());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
    }

    @Override
    public boolean update(Question question) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(String... primaryKeyValues) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Question get(String... primaryKeyValues) throws SQLException {
        Type answerListType = new TypeToken<List<Answer>>(){}.getType();
        Question question = null;

        try (Connection connection = SQLUtils.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Questions WHERE Questions.QuestionID = ?")) {
            pstmt.setInt(1, Integer.parseInt(primaryKeyValues[0]));

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    question = new Question();
                    question.setQuestionId(rs.getInt(1));
                    question.setSubjectId(rs.getInt(2));
                    question.setChapter(rs.getString(3));
                    question.setDifficulty(rs.getInt(4));
                    question.setContent(rs.getString(5));
                    
                    String answersJson = rs.getString(6);
                    if (answersJson != null && !answersJson.isEmpty()) {
                        question.setAnswers(gson.fromJson(answersJson, answerListType));
                    }
                    
                    question.setArchive(rs.getBoolean(7));
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return question;
    }

    
}
