package com.dayon.build.base;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.dayon.common.jdbc.SessionPool;
import com.dayon.common.model.DataList;
import com.dayon.common.model.DataMap;

public class MySqlDBPool {

	private SessionPool sp = null;
	private String databaseName = null;

	public MySqlDBPool(String ipAndPort, String user, String password, String databaseName) {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://" + ipAndPort + "/" + databaseName + "?characterEncoding=utf-8";

			this.databaseName = databaseName;

			Properties p = new Properties();
			p.setProperty("driver", driver);
			p.setProperty("url", url);
			p.setProperty("user", user);
			p.setProperty("password", password);
			this.sp = new SessionPool(p, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Collection<Table> getMySqlTablesInfo(String tableNameLikeValue) {

		try {
			String sql = "SELECT `TABLE_NAME`,`COLUMN_NAME`,`DATA_TYPE`,`COLUMN_COMMENT`, `COLUMN_KEY`='PRI' IS_PRIMARY, `COLUMN_KEY`='UNI' IS_UNIQUE,`IS_NULLABLE`='NO' IS_NULLABLE FROM information_schema.COLUMNS WHERE `TABLE_SCHEMA` = ? AND `TABLE_NAME` LIKE ? ";
			Map<String, Table> tabsMap = new HashMap<>();
			DataList dataList = sp.getSession().find(sql, databaseName,tableNameLikeValue);
			for (DataMap dataMap : dataList) {
				Table table = tabsMap.get(dataMap.get("TABLE_NAME"));
				if (table == null) {
					table = new Table();
					table.setName(dataMap.getString("TABLE_NAME"));
					tabsMap.put(dataMap.getString("TABLE_NAME"), table);
				}
				Column column = new Column();
				table.getColumns().add(column);
				column.setName(dataMap.getString("COLUMN_NAME"));
				column.setType(dataMap.getString("DATA_TYPE"));
				column.setComment(dataMap.getString("COLUMN_COMMENT"));
				column.setIsNullable(dataMap.getLong("IS_NULLABLE")!=0?true:false);
				column.setIsUnique(dataMap.getLong("IS_UNIQUE")!=0?true:false);
				column.setIsPrimary(dataMap.getLong("IS_PRIMARY")!=0?true:false);
			}

			return tabsMap.values();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sp.getSession().close();
		}

		return null;

	}

	public static Class<?> getJavaType(String mySqlType) {
		switch (mySqlType.toUpperCase()) {
		case "CHAR":
			return String.class;
		case "VARCHAR":
			return String.class;
		case "BLOB":
			return byte[].class;
		case "TEXT":
			return String.class;
		case "INTEGER":
			return Integer.class;
		case "SMALLINT":
			return Integer.class;
		case "TINYINT":
			return Integer.class;
		case "MEDIUMINT":
			return Integer.class;
		case "BIT":
			return Boolean.class;
		case "BIGINT":
			return Long.class;
		case "FLOAT":
			return Float.class;
		case "DOUBLE":
			return Double.class;
		case "DECIMAL":
			return Double.class;
		case "DATE":
			return Date.class;
		case "TIME":
			return Date.class;
		case "DATETIME":
			return Date.class;
		case "TIMESTAMP":
			return Date.class;
		default:
			System.out.println(mySqlType + "这个MySql数据类型查不到对JAVA数据类型");
			return null;
		}
	}
}
