package data.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import data.DTO.Teacher;
import utils.DataSourceFactory;

public class TeacherDAO implements DAO<Teacher> {

	private Connection connection;

	public TeacherDAO() {
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Teacher teacher) {
		try {
			String id = teacher.getTeacherID();
			String personID = "TC" + id;
			String firstName = teacher.getFirstName();
			String lastName = teacher.getLastName();
			String email = teacher.getEmail();
			String phone = teacher.getPhone();
			String sql = "INSERT INTO Person VALUES ('"
						+ personID + "','"
						+ firstName + "','"
						+ lastName + "','"
						+ email + "','"
						+ phone + "');"
						+ "INSERT INTO Teachers VALUES ('"
						+ id + "','"
						+ personID + "');";
			if (connection.createStatement().executeUpdate(sql) >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Teacher teacher) {
		try {
			String personID = "TC" + teacher.getTeacherID();
			String firstName = teacher.getFirstName();
			String lastName = teacher.getLastName();
			String email = teacher.getEmail();
			String phone = teacher.getPhone();
			String sql = "UPDATE Person SET "
						+ "FirstName = '" + firstName + "', "
						+ "LastName = '" + lastName + "', "
						+ "Email = '" + email + "', "
						+ "Phone = '" + phone + "' "
						+ "WHERE PersonID = '" + personID + "'";
			if (connection.createStatement().executeUpdate(sql) >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		try {
			if (connection.createStatement().executeUpdate(
					"DELETE FROM Teachers WHERE TeacherID = 'TC" + id + "'") >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Teacher> get(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
