package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import data.DTO.Clazz;
import data.DTO.Student;
import utils.DataSourceFactory;

public class ClassDAO implements DAO<Clazz> {

	private Connection connection;

	public ClassDAO() {
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addStudents(String classID, ArrayList<Student> students) throws SQLException {
		if (!students.isEmpty()) {
			DAO<Student> studentDAO = new StudentDAO();
			for (Student student : students) {
				studentDAO.insert(student);
				connection.createStatement().executeUpdate(
						"INSERT INTO ClassStudents VALUES ('"
						+ classID + "','"
						+ student.getStudentID() + "')");
			}
		}
	}

	@Override
	public boolean insert(Clazz clazz) {
		try {
			PreparedStatement pStatement = connection.prepareStatement("INSERT INTO Classes VALUES (?,?,?)");
			pStatement.setString(1, clazz.getClassID());
			pStatement.setString(2, clazz.getTeacher().getTeacherID());
			pStatement.setString(3, clazz.getClassName());
			if (pStatement.executeUpdate() >= 1) {
				addStudents(clazz.getClassID(), clazz.getStudents());
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Clazz clazz) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"UPDATE Classes SET"
					+ "ClassID=?,"
					+ "TeacherID=?,"
					+ "ClassName=?"
					+ "WHERE ClassID=?"
					);
			pStatement.setString(1, clazz.getTeacher().getTeacherID());
			pStatement.setString(2, clazz.getClassName());
			pStatement.setString(3, clazz.getClassID());
			if (pStatement.executeUpdate() >= 1) {
				addStudents(clazz.getClassID(), clazz.getStudents());
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		try {
			if (connection.createStatement().executeUpdate(
					"DELETE FROM Classes WHERE ClassID = '" + id + "'") >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Clazz> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Clazz> get(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
