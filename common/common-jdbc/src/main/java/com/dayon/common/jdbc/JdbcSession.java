package com.dayon.common.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.dayon.common.base.dto.model.DataList;
import com.dayon.common.base.dto.model.DataMap;

public class JdbcSession {
	private Connection connection;
	protected boolean isTansaction = false;

	public JdbcSession(Connection connection) {
		this.connection = connection;
	}

	public boolean isAvailable() throws Exception {
		return this.connection.isValid(0);
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
			return list.iterator().next();
		} else if (list.size() == 0) {
			return null;
		} else {
			throw new Exception("查询数据不是唯一的");
		}
	}

	public DataList find(String sql, Object... params) throws Exception {
		return BaseDB.find(this.connection, sql, params);
	}

	public int update(String sql, Object... params) throws Exception {
		int rnum = BaseDB.update(this.connection, sql, params);
		this.isTansaction = rnum > 0 ? true : this.isTansaction;
		return rnum;
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
