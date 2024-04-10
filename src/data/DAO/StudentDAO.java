package data.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import data.DTO.Student;
import utils.DataSourceFactory;

public class StudentDAO implements DAO<Student> {
	
	private Connection connection;
	
	public StudentDAO() {
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Student student) {
		try {
			String id = student.getStudentID();
			String personID = "ST" + id;
			String firstName = student.getFirstName();
			String lastName = student.getLastName();
			String email = student.getEmail();
			String phone = student.getPhone();
			String sql = "INSERT INTO Person VALUES ('"
						+ personID + "','"
						+ firstName + "','"
						+ lastName + "','"
						+ email + "','"
						+ phone + "');"
						+ "INSERT INTO Students VALUES ('"
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
	public boolean update(Student student) {
		try {
			String personID = "ST" + student.getStudentID();
			String firstName = student.getFirstName();
			String lastName = student.getLastName();
			String email = student.getEmail();
			String phone = student.getPhone();
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
					"DELETE FROM Person WHERE PersonID = 'ST" + id + "'") >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Student> get(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
