package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Student;
import utils.SQLUtils;

public class StudentDAO implements interfaceDAO<Student> {
    private ArrayList<Student> list;
    private Student student;
    Connection con;

    @Override
    public ArrayList<Student> getAll() {
        list = null;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from Students";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    student = new Student();
                    student.setUid(rs.getInt(1));
                    student.setGroupId(rs.getInt(2));
                    student.setStudentId(rs.getString(3));
                    student.setFirstName(rs.getString(4));
                    student.setLastName(rs.getString(5));
                    student.setEmail(rs.getString(6));
                    student.setPhone(rs.getString(7));
                    list.add(student);
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
    public boolean create(Student t) {
        boolean b = false;
        Connection con = SQLUtils.getConnection();

        if (con != null) {
            try {
                PreparedStatement pStatement = con.prepareStatement(
                        "INSERT INTO students (StudentID, FirstName, LastName, Phone, Email, GroupID)"
                                + " VALUES (?, ?, ?, ?, ?, ?)");
                pStatement.setString(1, student.getStudentId());
                pStatement.setString(1, student.getFirstName());
                pStatement.setString(2, student.getLastName());
                pStatement.setString(3, student.getPhone());
                pStatement.setString(4, student.getEmail());
                pStatement.setInt(5, student.getGroupId());

                b = pStatement.executeUpdate() >= 1;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

    @Override
    public Student getByID(int t) {
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM students WHERE UID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    student = new Student();
                    student.setUid(rs.getInt("UID"));
                    student.setStudentId(rs.getString("StudentID"));
                    student.setFirstName(rs.getString("FirstName"));
                    student.setLastName(rs.getString("LastName"));
                    student.setPhone(rs.getString("Phone"));
                    student.setEmail(rs.getString("Email"));
                    student.setGroupId(rs.getInt("GroupID"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return student;
    }

    @Override
    public boolean update(Student t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE students SET StudentID = ?, FirstName = ?, LastName = ?, Phone = ?, Email = ?, GroupID = ? WHERE UID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, student.getStudentId());
                ps.setString(2, student.getFirstName());
                ps.setString(3, student.getLastName());
                ps.setString(4, student.getPhone());
                ps.setString(5, student.getEmail());
                ps.setInt(6, student.getGroupId());
                ps.setInt(7, student.getUid());

                if (ps.executeUpdate() > 0) {
                    b = true;
                }
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
                String query = "Delete from students where uid=?";
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
        return true;
    }

}
