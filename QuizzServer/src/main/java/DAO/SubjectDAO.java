package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import DTO.Subject;
import utils.SQLUtils;

public class SubjectDAO implements interfaceDAO<Subject> {
    private ArrayList<Subject> list;
    private Subject subject;
    Connection con;

    @Override
    public ArrayList<Subject> getAll() {
        list = null;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM Subjects WHERE Archived = 0";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("SubjectID"));
                    subject.setWorkspaceId(rs.getInt("WorkspaceID"));
                    subject.setSubjectName(rs.getString("SubjectName"));
                    subject.setCreateDate(rs.getTimestamp("DateCreated").toLocalDateTime());
                    subject.setArchive(rs.getBoolean("Archived"));
                    list.add(subject);
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
    public boolean create(Subject t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "INSERT INTO Subjects (WorkspaceID, SubjectName, DateCreated, Archived) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, subject.getWorkspaceId());
                ps.setString(2, subject.getSubjectName());
                ps.setTimestamp(3, Timestamp.valueOf(subject.getCreateDate()));
                ps.setBoolean(4, subject.isArchive());
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
    public Subject getByID(int t) {
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM Subjects WHERE SubjectID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    subject = new Subject();
                    subject.setSubjectId(rs.getInt("SubjectID"));
                    subject.setWorkspaceId(rs.getInt("WorkspaceID"));
                    subject.setSubjectName(rs.getString("SubjectName"));
                    subject.setCreateDate(rs.getTimestamp("DateCreated").toLocalDateTime());
                    subject.setArchive(rs.getBoolean("Archived"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return subject;
    }

    @Override
    public boolean update(Subject t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE Subjects SET WorkspaceID = ?, SubjectName = ?, DateCreated = ?, Archived = ? WHERE SubjectID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, subject.getWorkspaceId());
                ps.setString(2, subject.getSubjectName());
                ps.setTimestamp(3, Timestamp.valueOf(subject.getCreateDate()));
                ps.setBoolean(4, subject.isArchive());
                ps.setInt(5, subject.getSubjectId());
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
                String query = "UPDATE Subjects SET Archived = 1 WHERE SubjectID = ?";
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
