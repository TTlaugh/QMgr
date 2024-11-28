package DAO;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.HostExam;
import model.Question;
import utils.SQLUtils;

public class HostExamDAO implements interfaceDAO<HostExam> {
    private ArrayList<HostExam> list;
    private HostExam hostExam;
    private Gson gson = new Gson();
    Connection con;

    @Override
    public ArrayList<HostExam> getAll() {
        list = null;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM HostExams WHERE isArchived = 0"; // Assuming you have an archived field
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    hostExam = new HostExam();
                    hostExam.setHostExamId(rs.getInt("HostExamID"));
                    hostExam.setTimeLimit(rs.getInt("TimeLimit"));
                    hostExam.setMaxScore((rs.getBigDecimal("MaxScore").intValue()));
                    hostExam.setShuffle(rs.getBoolean("isShuffled"));
                    hostExam.setExamId(rs.getInt("ExamID"));
                    hostExam.setGroupId(rs.getInt("GroupID"));

                    String questionsJson = rs.getString("ExamQuestions");
                    Type questionListType = new TypeToken<ArrayList<Question>>() {
                    }.getType();
                    if (questionsJson != null && !questionsJson.isEmpty()) {
                        hostExam.setExamQuestions(gson.fromJson(questionsJson, questionListType));
                    } else
                        list.add(hostExam);
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
    public boolean create(HostExam t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "INSERT INTO HostExams (TimeLimit, MaxScore, isShuffled, ExamQuestions, ExamID, GroupID) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, hostExam.getTimeLimit());
                ps.setBigDecimal(2, new BigDecimal(hostExam.getMaxScore()));
                ps.setBoolean(3, hostExam.isShuffle());
                ps.setString(4, gson.toJson(hostExam.getExamQuestions()));
                ps.setInt(5, hostExam.getExamId());
                ps.setInt(6, hostExam.getGroupId());
                b = ps.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

    @Override
    public HostExam getByID(int t) {
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM HostExams WHERE HostExamID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    hostExam = new HostExam();
                    hostExam.setHostExamId(rs.getInt("HostExamID"));
                    hostExam.setTimeLimit(rs.getInt("TimeLimit"));
                    hostExam.setMaxScore(rs.getBigDecimal("MaxScore").intValue());
                    hostExam.setShuffle(rs.getBoolean("isShuffled"));
                    hostExam.setExamId(rs.getInt("ExamID"));
                    hostExam.setGroupId(rs.getInt("GroupID"));

                    // Deserialize the JSON array of questions
                    String questionsJson = rs.getString("ExamQuestions");
                    Type questionListType = new TypeToken<ArrayList<Question>>() {
                    }.getType();
                    if (questionsJson != null && !questionsJson.isEmpty()) {
                        hostExam.setExamQuestions(gson.fromJson(questionsJson, questionListType));
                    } else {
                        hostExam.setExamQuestions(new ArrayList<>());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return hostExam;
    }

    @Override
    public boolean update(HostExam t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE HostExams SET TimeLimit = ?, MaxScore = ?, isShuffled = ?, ExamQuestions = ?, ExamID = ?, GroupID = ? WHERE HostExamID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, hostExam.getTimeLimit());
                ps.setBigDecimal(2, new BigDecimal(hostExam.getMaxScore()));
                ps.setBoolean(3, hostExam.isShuffle());
                ps.setString(4, gson.toJson(hostExam.getExamQuestions())); // Serialize questions to JSON
                ps.setInt(5, hostExam.getExamId());
                ps.setInt(6, hostExam.getGroupId());
                ps.setInt(7, hostExam.getHostExamId());
                b = ps.executeUpdate() > 0;
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
                String query = "UPDATE HostExams SET Archived = 1 WHERE HostExamID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                b = ps.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

}
