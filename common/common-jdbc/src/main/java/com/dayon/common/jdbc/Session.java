package com.dayon.common.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.dayon.common.model.DataList;
import com.dayon.common.model.DataMap;

public class Session {
	private Connection connection;

	public Session(Connection connection) {
		this.connection = connection;
	}

	public boolean isAvailable() throws Exception {
		return this.connection != null && this.connection.isValid(0);
	}

	public boolean isAutoCommit() throws Exception {
		return this.connection.getAutoCommit();
	}

	public void autoCommit() throws Exception {
		this.connection.setAutoCommit(true);
	}

	public boolean beginTransaction() {
		try {
			this.connection.setAutoCommit(false);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean commit() {
		try {
			this.connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean rollback() {
		try {
			this.connection.rollback();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean close() {
		try {
			this.connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public DataMap get(String sql, Object... params) throws Exception {
		DataList list = this.find(sql, params);
		if (list.size() == 1) {
			return list.get(0);
		} else if (list.size() == 0) {
			return null;
		} else {
			throw new Exception("result is not the only");
		}
	}

	public DataList find(String sql, Object... params) throws Exception {
		return BaseDB.find(this.connection, sql, params);
	}

	public int update(String sql, Object... params) throws Exception {
		return BaseDB.update(this.connection, sql, params);
	}
}

class BaseDB {

	public static DataList find(Connection connection, String sql, Object... params) throws Exception {
		DataList list = new DataList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			while (rs.next()) {
				DataMap map = new DataMap();
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					map.put(meta.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return list;
	}

	public static int update(Connection connection, String sql, Object... params) throws Exception {

		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {

				pstmt.setObject(i + 1, params[i]);
			}
			return pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}

}
