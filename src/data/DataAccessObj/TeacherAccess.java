package data.DataAccessObj;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import data.DataTransferObj.Teacher;
import utils.DataSourceFactory;

public class TeacherAccess implements DataAccess<Teacher> {

	private Connection connection;

	@Override
	public boolean insert(Teacher teacher) throws SQLException {
		connection = DataSourceFactory.getConnection();
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
		boolean i = connection.createStatement().executeUpdate(sql) >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Teacher teacher) throws SQLException {
		connection = DataSourceFactory.getConnection();
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
		boolean i = connection.createStatement().executeUpdate(sql) >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = DataSourceFactory.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM Teachers WHERE TeacherID = 'TC" + primaryKeyValues[0] + "'") >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public Teacher getOnly(String... primaryKeyValues) throws SQLException {
		return DataAccess.get(Teacher.class,
				"SELECT Teachers.TeacherID, Person.* FROM Teachers"
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID",
				"Teachers.TeacherID", primaryKeyValues[0]);
	}

	@Override
	public List<Teacher> getAll(String... columnName_values) throws SQLException {
		String selectFrom = "SELECT Teachers.TeacherID, Person.* FROM Teachers"
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID";
		return DataAccess.getList(Teacher.class, selectFrom, columnName_values);
	}

	@Override
	public List<Teacher> getLimit(int offset, int limit, String... columnName_values) throws SQLException {
		String selectFrom = "SELECT Teachers.TeacherID, Person.* FROM Teachers LIMIT " + offset + ", " + limit
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID";
		return DataAccess.getList(Teacher.class, selectFrom, columnName_values);
	}

}
