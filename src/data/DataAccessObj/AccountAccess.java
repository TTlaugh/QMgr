package data.DataAccessObj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import data.DataTransferObj.Account;
import utils.SQLUtils;

public class AccountAccess implements DataAccess<Account> {
	
	private Connection connection;
	
	@Override
	public boolean insert(Account account) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"INSERT INTO Accounts VALUES (?,?)");
		pStatement.setString(1, account.getPersonID());
		pStatement.setString(2, account.getPassword());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Account account) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"UPDATE Accounts SET"
						+ "Password=?"
						+ "WHERE PersonID=?");
		pStatement.setString(1, account.getPassword());
		pStatement.setString(2, account.getPersonID());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM Person WHERE PersonID = '" + primaryKeyValues[0] + "'") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public Account getOnly(String... primaryKeyValues) throws SQLException {
		return get(Account.class,
					"SELECT * FROM Accounts",
					"PersonID", primaryKeyValues[0]);
	}

	@Override
	public List<Account> getAll(String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Accounts";
		return getList(Account.class, selectFrom, columnName_values);
	}

	@Override
	public List<Account> getLimit(int offset, int limit, String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Accounts LIMIT " + offset + ", " + limit;
		return getList(Account.class, selectFrom, columnName_values);
	}

}
