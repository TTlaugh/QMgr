package services;

import java.sql.SQLException;
import java.util.List;

import data.DataAccessObj.DataAccess;
import data.DataAccessObj.GroupAccess;
import data.DataTransferObj.Group;
import utils.SQLUtils;

public class GroupManager {

	private DataAccess<Group> dataAccess = null;

	public List<Group> getGroups() {
		dataAccess = new GroupAccess();
		try {
			return dataAccess.getAll();
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}

	public boolean addGroup(Group newGroup) {
		dataAccess = new GroupAccess();
		try {
			return dataAccess.insert(newGroup);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}


}
