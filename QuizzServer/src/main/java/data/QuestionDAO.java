package data;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.*;
import utils.SQLUtils;

public class QuestionDAO implements interfaceDAO<Question> {
    private ArrayList<Question> list;
    private Question question;
    private Gson gson = new Gson();
    Connection con;

    public ArrayList<Question> getAllBySubject(int subjectId) {
        list = new ArrayList<Question>();

        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from questions where archived = 0 and subjectid = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, subjectId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Type answerListType = new TypeToken<List<Answer>>() {
                    }.getType();
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
                    list.add(question);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return list;
    }

    @Override
    public ArrayList<Question> getAll() {
        list = new ArrayList<Question>();

        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from questions ";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Type answerListType = new TypeToken<List<Answer>>() {
                    }.getType();
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
                    list.add(question);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return list;

    }

    @Override
    public boolean create(Question question) {
        boolean b = false;
        String answers = gson.toJson(question.getAnswers());
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                PreparedStatement pStatement = con.prepareStatement(
                        "INSERT INTO Questions"
                                + " ( SubjectID, Chapter, Difficulty, Content, Answers, Archived)"
                                + " VALUES (?,?,?,?,?,?)");

                pStatement.setInt(1, question.getSubjectId());
                pStatement.setString(2, question.getChapter());
                pStatement.setInt(3, question.getDifficulty());
                pStatement.setString(4, question.getContent());
                pStatement.setString(5, answers);
                pStatement.setBoolean(6, question.isArchive());
                b = pStatement.executeUpdate() >= 1;
                SQLUtils.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

    @Override
    public Question getByID(int t) {
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from questions where questionid=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Type answerListType = new TypeToken<List<Answer>>() {
                    }.getType();
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
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return question;
    }

    @Override
    public boolean update(Question t) {

        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE questions SET Chapter = ?, Difficulty = ?, Content = ?, Answers = ? WHERE QuestionID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, t.getChapter());
                ps.setInt(2, t.getDifficulty());
                ps.setString(3, t.getContent());
                String answerJson = gson.toJson(t.getAnswers());
                ps.setString(4, answerJson);
                ps.setInt(5, t.getQuestionId());

                if (ps.executeUpdate() > 0)
                    b = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

    @Override
    public boolean delete(int t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE questions SET archived = 1 WHERE QuestionID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                if (ps.executeUpdate() > 0)
                    b = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;

    }

    public ArrayList<Question> searchQuestions(int subjectId, String keyWord) {
        ArrayList<Question> list = new ArrayList<Question>();
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from questions where archived = 0 and subjectid = ? and content like ? ";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, subjectId);
                ps.setString(2, "%" + keyWord + "%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Type answerListType = new TypeToken<List<Answer>>() {
                    }.getType();
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
                    list.add(question);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return list;
    }

}
