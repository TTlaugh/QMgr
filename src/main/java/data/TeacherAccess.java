package data;

import java.sql.Connection;
import java.sql.SQLException;

import business.model.Teacher;
import utils.SQLUtils;

public class TeacherAccess implements DataAccess<Teacher> {

	private Connection connection;

	@Override
	public boolean insert(Teacher teacher) throws SQLException {
		connection = SQLUtils.getConnection();
		String id = teacher.getTeacherID();
		String personID = "TC" + id;
		String firstName = teacher.getFirstName();
		String lastName = teacher.getLastName();
		String phone = teacher.getPhone();
		String email = teacher.getEmail();
		String sql = "INSERT INTO Person VALUES ('"
				+ personID + "','"
				+ firstName + "','"
				+ lastName + "','"
				+ phone + "','"
				+ email + "');"
				+ "INSERT INTO Teachers VALUES ('"
				+ id + "','"
				+ personID + "');";
		boolean i = connection.createStatement().executeUpdate(sql) >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Teacher teacher) throws SQLException {
		connection = SQLUtils.getConnection();
		String personID = "TC" + teacher.getTeacherID();
		String firstName = teacher.getFirstName();
		String lastName = teacher.getLastName();
		String phone = teacher.getPhone();
		String email = teacher.getEmail();
		String sql = "UPDATE Person SET "
				+ "FirstName = '" + firstName + "', "
				+ "LastName = '" + lastName + "', "
				+ "Phone = '" + phone + "', "
				+ "Email = '" + email + "' "
				+ "WHERE PersonID = '" + personID + "'";
		boolean i = connection.createStatement().executeUpdate(sql) >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM Teachers WHERE TeacherID = 'TC" + primaryKeyValues[0] + "'") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public Teacher get(String... primaryKeyValues) throws SQLException {
		return get(Teacher.class,
				"SELECT Teachers.TeacherID, Person.* FROM Teachers"
						+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID",
				"Teachers.TeacherID", primaryKeyValues[0]);
	}

}
