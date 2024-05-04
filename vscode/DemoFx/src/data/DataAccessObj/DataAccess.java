package data.DataAccessObj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DataSourceFactory;
 
public interface DataAccess<T> {
 
    boolean insert(T t) throws SQLException;
 
    boolean update(T t) throws SQLException;
 
    boolean delete(String... primaryKeyValues) throws SQLException;

	T getOnly(String... primaryKeyValues) throws SQLException;

	List<T> getAll(String... columnName_values) throws SQLException;

	List<T> getLimit(int offset, int limit, String... columnName_values) throws SQLException;

	private static String where(String... conditions) {
		int size = conditions.length;
		if (size < 2 || size % 2 != 0) return "";
		String where = " WHERE ";
		for (int i = 0; i < size; i+=2) {
			if (conditions[i]!=null&&!conditions[i].isBlank()) {
				if (i!=0) where+=" AND ";
				where += " ("
						+ conditions[i] + " LIKE '%"
						+ conditions[i+1] + "%') ";
			}
		}
		return where==" WHERE " ? "" : where;
	}
	
	static <T> T get(Class<T> c, String selectFrom, String... where) throws SQLException {
		Connection connection = DataSourceFactory.getConnection();
		String query = selectFrom;
		query += where(where);
		ResultSet rs = connection.createStatement().executeQuery(query);
		T t = null;
		if (rs.next())
			try {
				t = c.getConstructor(ResultSet.class).newInstance(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		DataSourceFactory.closeConnection(connection);
		return t;
	}

	static <T> List<T> getList(Class<T> c, String selectFrom, String... where) throws SQLException {
		Connection connection = DataSourceFactory.getConnection();
		String query = selectFrom;
		query += where(where);
		ResultSet rs = connection.createStatement().executeQuery(query);
		List<T> dataList = new ArrayList<T>();
		while(rs.next()) {
			try {
				dataList.add(c.getConstructor(ResultSet.class).newInstance(rs));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		DataSourceFactory.closeConnection(connection);
		return dataList;
	}

}