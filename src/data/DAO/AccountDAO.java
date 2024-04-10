package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import data.DTO.Account;
import utils.DataSourceFactory;

public class AccountDAO implements DAO<Account> {
	
	private Connection connection;
	
	public AccountDAO() {
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Account account) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"INSERT INTO Accounts VALUES (?,?)");
			pStatement.setString(1, account.getPersonID());
			pStatement.setString(2, account.getPassword());
			if (pStatement.executeUpdate() >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Account account) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"UPDATE Accounts SET"
					+ "Password=?"
					+ "WHERE AccountID=?"
					);
			pStatement.setString(1, account.getPassword());
			pStatement.setString(2, account.getPersonID());
			if (pStatement.executeUpdate() >= 1)
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
					"DELETE FROM Person WHERE PersonID = '" + id + "'") >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Account> get(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
