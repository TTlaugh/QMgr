package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import business.model.Teacher;
import utils.SQLUtils;

public class TeacherAccess implements DataAccess<Teacher> {

	private Connection connection;

	@Override
	public boolean insert(Teacher teacher) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement1 = connection.prepareStatement(
				"INSERT INTO Person VALUES (?,?,?,?,?)");
		pStatement1.setString(1, teacher.getPersonID());
		pStatement1.setString(2, teacher.getFirstName());
		pStatement1.setString(3, teacher.getLastName());
		pStatement1.setString(4, teacher.getPhone());
		pStatement1.setString(5, teacher.getEmail());
		boolean a = pStatement1.executeUpdate() >= 1;
		PreparedStatement pStatement2 = connection.prepareStatement(
				"INSERT INTO Teachers VALUES (?,?)");
		pStatement2.setString(1, teacher.getTeacherID());
		pStatement2.setString(2, teacher.getPersonID());
		boolean b = pStatement2.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return a && b;
	}

	@Override
	public boolean update(Teacher teacher) throws SQLException {
		connection = SQLUtils.getConnection();
		String personID = teacher.getPersonID();
		String firstName = teacher.getFirstName();
		String lastName = teacher.getLastName();
		String phone = teacher.getPhone();
		String email = teacher.getEmail();
		String sql = "UPDATE Person SET"
				+ " FirstName = '" + firstName + "',"
				+ " LastName = '" + lastName + "',"
				+ " Phone = '" + phone + "',"
				+ " Email = '" + email + "'"
				+ " WHERE PersonID = '" + personID + "'";
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
