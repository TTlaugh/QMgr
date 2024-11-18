package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Submission;

import java.lang.reflect.Type;

import utils.SQLUtils;

public class SubmissionDAO implements interfaceDAO<Submission> {
    private ArrayList<Submission> list;
    private Submission submission;
    private Gson gson = new Gson();
    Connection con;

    @Override
    public ArrayList<Submission> getAll() {
        list = null;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM Submissions";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Submission submission = new Submission();
                    submission.setHostExamId(rs.getInt("HostExamID"));
                    submission.setStudentId(rs.getInt("UID"));
                    submission.setTimeTaken(rs.getInt("TimeTaken"));
                    submission.setScore(rs.getFloat("Score"));

                    String answerJson = rs.getString("AnswerSelecteds");
                    if (answerJson != null && !answerJson.isEmpty()) {
                        Type answerMapType = new TypeToken<Map<Integer, List<Integer>>>() {
                        }.getType();
                        Map<Integer, List<Integer>> answerMap = gson.fromJson(answerJson, answerMapType);
                        submission.setAnswerSelectedMap(answerMap);
                    }

                    list.add(submission);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return list;
    }

    @Override
    public boolean create(Submission t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "INSERT INTO Submissions (HostExamID, UID, TimeTaken, Score, AnswerSelecteds) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, submission.getHostExamId());
                ps.setInt(2, submission.getStudentId());
                ps.setInt(3, submission.getTimeTaken());
                ps.setFloat(4, submission.getScore());

                String answerJson = gson.toJson(submission.getAnswerSelectedMap());
                ps.setString(5, answerJson);

                b = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

    @Override
    public Submission getByID(int t) {
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM Submissions WHERE UID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    submission = new Submission();
                    submission.setHostExamId(rs.getInt("HostExamID"));
                    submission.setStudentId(rs.getInt("UID"));
                    submission.setTimeTaken(rs.getInt("TimeTaken"));
                    submission.setScore(rs.getFloat("Score"));

                    String answerJson = rs.getString("AnswerSelecteds");
                    if (answerJson != null && !answerJson.isEmpty()) {
                        Type answerMapType = new TypeToken<Map<Integer, List<Integer>>>() {
                        }.getType();
                        Map<Integer, List<Integer>> answerMap = gson.fromJson(answerJson, answerMapType);
                        submission.setAnswerSelectedMap(answerMap);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return submission;
    }

    @Override
    public boolean update(Submission t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE Submissions SET TimeTaken = ?, Score = ?, AnswerSelecteds = ? WHERE HostExamID = ? AND UID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, submission.getTimeTaken());
                ps.setFloat(2, submission.getScore());

                // Convert answerSelectedMap to JSON
                String answerJson = gson.toJson(submission.getAnswerSelectedMap());
                ps.setString(3, answerJson);
                ps.setInt(4, submission.getHostExamId());
                ps.setInt(5, submission.getStudentId());

                b = ps.executeUpdate() > 0;
            } catch (SQLException e) {
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
                String query = "DELETE FROM Submissions WHERE UID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                b = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

}
