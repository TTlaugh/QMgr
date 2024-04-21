package data;

import java.sql.Connection;
import java.sql.SQLException;

import business.model.Student;
import utils.SQLUtils;

public class StudentAccess implements DataAccess<Student> {
	
	private Connection connection;
	
	@Override
	public boolean insert(Student student) throws SQLException {
		connection = SQLUtils.getConnection();
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
		boolean i = connection.createStatement().executeUpdate(sql) >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Student student) throws SQLException {
		connection = SQLUtils.getConnection();
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
		boolean i = connection.createStatement().executeUpdate(sql) >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
					"DELETE FROM Person WHERE PersonID = 'ST" + primaryKeyValues[0] + "'") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public Student get(String... primaryKeyValues) throws SQLException {
		return get(Student.class,
				"SELECT Students.StudentID, Person.* FROM Students"
				+ " INNER JOIN Person ON Students.PersonID = Person.PersonID",
				"Students.StudentID", primaryKeyValues[0]);
	}

}
