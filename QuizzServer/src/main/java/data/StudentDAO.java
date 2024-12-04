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

    public int getMaxUid() {
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT uid FROM students ORDER BY uid DESC LIMIT 1";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("uid");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return 0;
    }

    @Override
    public ArrayList<Student> getAll() {
        list = new ArrayList<Student>();
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from Students";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    student = new Student();
                    student.setUid(rs.getInt(1));
                    student.setStudentId(rs.getString(2));
                    student.setFirstName(rs.getString(3));
                    student.setLastName(rs.getString(4));
                    student.setPhone(rs.getString(5));
                    student.setEmail(rs.getString(6));
                    student.setGroupId(rs.getInt(7));
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

    public ArrayList<Student> getAllByGroupId(int groupId) {
        list = new ArrayList<Student>();
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from Students where GroupID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, groupId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    student = new Student();
                    student.setUid(rs.getInt(1));
                    student.setStudentId(rs.getString(2));
                    student.setFirstName(rs.getString(3));
                    student.setLastName(rs.getString(4));
                    student.setPhone(rs.getString(5));
                    student.setEmail(rs.getString(6));
                    student.setGroupId(rs.getInt(7));
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
    public boolean create(Student student) {
        boolean b = false;
        Connection con = SQLUtils.getConnection();

        if (con != null) {
            try {
                PreparedStatement pStatement = con.prepareStatement(
                        "INSERT INTO students (StudentID, FirstName, LastName, Phone, Email, GroupID)"
                                + " VALUES (?, ?, ?, ?, ?, ?)");
                pStatement.setString(1, student.getStudentId());
                pStatement.setString(2, student.getFirstName());
                pStatement.setString(3, student.getLastName());
                pStatement.setString(4, student.getPhone());
                pStatement.setString(5, student.getEmail());
                pStatement.setInt(6, student.getGroupId());

                b = pStatement.executeUpdate() >= 1;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

    public Student getByStudentIdfromGroup(String studentId, int groupId) {
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM students WHERE StudentID = ? and GroupID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, studentId);
                ps.setInt(2, groupId);
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

    public Student getByStudentId(String studentId) {
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM students WHERE StudentID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, studentId);
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
    public boolean update(Student student) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE students SET StudentID = ?, FirstName = ?, LastName = ?, Phone = ?, Email = ?, GroupID = ? WHERE StudentID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, student.getStudentId());
                ps.setString(2, student.getFirstName());
                ps.setString(3, student.getLastName());
                ps.setString(4, student.getPhone());
                ps.setString(5, student.getEmail());
                ps.setInt(6, student.getGroupId());
                ps.setString(7, student.getStudentId());

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
        return b;
    }

    public ArrayList<Student> searchStudent(int groupId, String keyword) {
        list = new ArrayList<Student>();
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "Select * from Students where GroupID = ? and (StudentID like ? or FirstName like ? or LastName like ? or phone like ? or email like ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, groupId);
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                ps.setString(5, "%" + keyword + "%");
                ps.setString(6, "%" + keyword + "%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    student = new Student();
                    student.setUid(rs.getInt(1));
                    student.setStudentId(rs.getString(2));
                    student.setFirstName(rs.getString(3));
                    student.setLastName(rs.getString(4));
                    student.setPhone(rs.getString(5));
                    student.setEmail(rs.getString(6));
                    student.setGroupId(rs.getInt(7));
                    list.add(student);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return list;
    }
}
