package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.SQLUtils;

public interface DataAccess<T> {

    boolean insert(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean delete(String... primaryKeyValues) throws SQLException;

	T get(String... primaryKeyValues) throws SQLException;

	private String where(String logicOperator, String... conditions) {
		int size = conditions.length;
		if (size < 2 || size % 2 != 0) return "";
		String where = " WHERE ";
		for (int i = 0; i < size; i+=2) {
			if (conditions[i]!=null&&!conditions[i].isBlank()) {
				if (i!=0) where+=" " + logicOperator + " ";
				where += " ("
						+ conditions[i] + " LIKE BINARY '%"
						+ conditions[i+1] + "%') ";
			}
		}
		return where==" WHERE " ? "" : where;
	}
	
	default <E> E get(Class<E> c, String selectFrom, String... where) throws SQLException {
		Connection connection = SQLUtils.getConnection();
		String query = selectFrom;
		query += where("AND", where);
		ResultSet rs = connection.createStatement().executeQuery(query);
		E data = null;
		if (rs.next())
			try {
				data = c.getConstructor(ResultSet.class).newInstance(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		SQLUtils.closeConnection(connection);
		return data;
	}

	default <E> List<E> getList(Class<E> c, String selectFrom, String... where) throws SQLException {
		Connection connection = SQLUtils.getConnection();
		String query = selectFrom;
		query += where("AND", where);
		ResultSet rs = connection.createStatement().executeQuery(query);
		List<E> dataList = new ArrayList<E>();
		while(rs.next()) {
			try {
				dataList.add(c.getConstructor(ResultSet.class).newInstance(rs));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		SQLUtils.closeConnection(connection);
		return dataList;
	}
	
	default <E> List<E> search(Class<E> c, String selectFrom, String... where) throws SQLException {
		Connection connection = SQLUtils.getConnection();
		String query = selectFrom;
		query += where("OR", where);
		ResultSet rs = connection.createStatement().executeQuery(query);
		List<E> dataList = new ArrayList<E>();
		while(rs.next()) {
			try {
				dataList.add(c.getConstructor(ResultSet.class).newInstance(rs));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		SQLUtils.closeConnection(connection);
		return dataList;
	}

}