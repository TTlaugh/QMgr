package data;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Exam;
import utils.SQLUtils;

public class ExamDAO implements interfaceDAO<Exam> {
    private ArrayList<Exam> list;
    private Exam exam;
    Connection con;
    private Gson gson = new Gson();

    public ArrayList<Exam> getAllBySubject(int subjectId) {
        list = new ArrayList<Exam>();
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from exams where archived=0 and subjectid = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, subjectId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Type questionListType = new TypeToken<List<Integer>>() {
                    }.getType();
                    exam = new Exam();
                    exam.setExamId(rs.getInt(1));
                    exam.setSubjectId(rs.getInt(2));
                    exam.setName(rs.getString(3));
                    exam.setDesc(rs.getString(4));
                    String questionJson = rs.getString(5);
                    if (questionJson != null && !questionJson.isEmpty()) {
                        exam.setQuestionsIds(gson.fromJson(questionJson, questionListType));
                    }
                    exam.setArchive(rs.getBoolean(6));
                    list.add(exam);
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
    public ArrayList<Exam> getAll() {
        list = new ArrayList<Exam>();
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from exams";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Type questionListType = new TypeToken<List<Integer>>() {
                    }.getType();
                    exam = new Exam();
                    exam.setExamId(rs.getInt(1));
                    exam.setSubjectId(rs.getInt(2));
                    exam.setName(rs.getString(3));
                    exam.setDesc(rs.getString(4));
                    String questionJson = rs.getString(5);
                    if (questionJson != null && !questionJson.isEmpty()) {
                        exam.setQuestionsIds(gson.fromJson(questionJson, questionListType));
                    }
                    exam.setArchive(rs.getBoolean(6));
                    list.add(exam);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return list;
    }

    // asd
    @Override
    public boolean create(Exam exam) {
        boolean b = false;
        String questionID = gson.toJson(exam.getQuestionsIds());
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                PreparedStatement pStatement = con.prepareStatement(
                        "INSERT INTO Exams"
                                + " (SubjectID, Name,Description,QuestionIDS, Archived)"
                                + " VALUES (?,?,?,?,?)");
                pStatement.setInt(1, exam.getSubjectId());
                pStatement.setString(2, exam.getName());
                pStatement.setString(3, exam.getDesc());
                pStatement.setString(4, questionID);
                pStatement.setBoolean(5, false);
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
    public Exam getByID(int t) {
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from exams where examId=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Type questionListType = new TypeToken<List<Integer>>() {
                    }.getType();
                    exam = new Exam();
                    exam.setExamId(rs.getInt(1));
                    exam.setSubjectId(rs.getInt(2));
                    exam.setName(rs.getString(3));
                    exam.setDesc(rs.getString(4));
                    String questionJson = rs.getString(5);
                    if (questionJson != null && !questionJson.isEmpty()) {
                        exam.setQuestionsIds(gson.fromJson(questionJson, questionListType));
                    }
                    exam.setArchive(rs.getBoolean(6));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return exam;
    }

    @Override
    public boolean update(Exam t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE exams SET  SubjectId = ?, Name = ?, Description = ?, QuestionIDS=? WHERE ExamID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t.getSubjectId());
                ps.setString(2, t.getName());
                ps.setString(3, t.getDesc());
                String quesionIDJson = gson.toJson(t.getQuestionsIds());
                ps.setString(4, quesionIDJson);
                ps.setInt(5, t.getExamId());

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
                String query = "UPDATE exams SET archived = 1 WHERE ExamID = ?";
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

    public ArrayList<Exam> getAllExamsUnHosted() {
        list = new ArrayList<Exam>();
        con = SQLUtils.getConnection();
        if (con != null) {
            try {

                String query = "SELECT  Exams.* " + "FROM Exams "
                        + "LEFT JOIN HostExams ON Exams.ExamID = HostExams.ExamID"
                        + " WHERE HostExams.ExamID IS NULL AND Exams.Archived =0";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Type questionListType = new TypeToken<List<Integer>>() {
                    }.getType();
                    exam = new Exam();
                    exam.setExamId(rs.getInt(1));
                    exam.setSubjectId(rs.getInt(2));
                    exam.setName(rs.getString(3));
                    exam.setDesc(rs.getString(4));
                    String questionJson = rs.getString(5);
                    if (questionJson != null && !questionJson.isEmpty()) {
                        exam.setQuestionsIds(gson.fromJson(questionJson, questionListType));
                    }
                    exam.setArchive(rs.getBoolean(6));
                    list.add(exam);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return list;
    }

    public ArrayList<Exam> getAllExamsHosted() {
        list = new ArrayList<Exam>();
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT distinct Exams.*" + "FROM Exams "
                        + " JOIN HostExams  ON Exams.ExamID = HostExams.ExamID"
                        + " join Subjects ON Exams.SubjectID = Subjects.SubjectID"
                        + " WHERE Exams.Archived =0 ";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Type questionListType = new TypeToken<List<Integer>>() {
                    }.getType();
                    exam = new Exam();
                    exam.setExamId(rs.getInt(1));
                    exam.setSubjectId(rs.getInt(2));
                    exam.setName(rs.getString(3));
                    exam.setDesc(rs.getString(4));
                    String questionJson = rs.getString(5);
                    if (questionJson != null && !questionJson.isEmpty()) {
                        exam.setQuestionsIds(gson.fromJson(questionJson, questionListType));
                    }
                    exam.setArchive(rs.getBoolean(6));

                    list.add(exam);
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
