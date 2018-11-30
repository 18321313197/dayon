package com.dayon.common.jdbc;

public class SqlPlus {
	private StringBuilder sql = new StringBuilder();

	public StringBuilder getStringBuilder() {
		return sql;
	}

	public SqlPlus on(String expression) {
		sql.append(" ON ").append(expression);
		return this;
	}

	public SqlPlus leftJoin(String table) {
		sql.append(" LEFT JOIN ").append(table);
		return this;
	}

	public SqlPlus groupBy(String... columns) {
		sql.append(" GROUP BY ");
		for (String column : columns) {
			sql.append(column).append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		return this;
	}

	public SqlPlus orderBy(String... columns) {
		sql.append(" ORDER BY ");
		for (String column : columns) {
			sql.append(column).append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		return this;
	}

	public SqlPlus orderByDesc(String... columns) {
		sql.append(" ORDER BY ");
		for (String column : columns) {
			sql.append(column).append(",");
		}
		sql.deleteCharAt(sql.length() - 1).append(" DESC");
		return this;
	}

	public SqlPlus delete(String... tables) {
		sql.append(" DELETE ");
		for (String table : tables) {
			sql.append(table).append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		return this;
	}

	public SqlPlus from(String... tables) {
		sql.append(" FROM ");
		for (String table : tables) {
			sql.append(table).append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		return this;
	}

	public SqlPlus limit(String first, String length) {
		sql.append(" LIMIT ").append(first).append(",").append(length);
		return this;
	}

	public SqlPlus limit(String length) {
		sql.append(" LIMIT ").append(length);
		return this;
	}

	public SqlPlus select(String... columns) {
		sql.append(" SELECT ");
		for (String column : columns) {
			sql.append(column).append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		return this;
	}

	public SqlPlus where(String expression) {
		sql.append(" WHERE ");

		sql.append(expression);

		return this;
	}

	public SqlPlus and(String expression) {
		sql.append(" AND ");
		sql.append(expression);
		return this;
	}

	public SqlPlus having(String expression) {
		sql.append(" HAVING ");
		sql.append(expression);
		return this;
	}

	public SqlPlus or(String expression) {
		sql.append(" OR ");
		sql.append(expression);
		return this;
	}

	public SqlPlus insertInto(String tableName, String... columns) {
		sql.append(" INSERT INTO ").append(tableName);
		if (columns.length > 0) {
			sql.append(" (");
			for (String column : columns) {
				sql.append(column).append(",");
			}
			sql.deleteCharAt(sql.length() - 1).append(")");

		}
		return this;
	}

	public SqlPlus values(int columnCount, int length) {
		sql.append(" VALUES ");
		for (int i = 0; i < length; i++) {
			sql.append("(");
			for (int j = 0; j < columnCount; j++) {
				sql.append("?,");
			}
			sql.deleteCharAt(sql.length() - 1).append("),");
		}
		sql.deleteCharAt(sql.length() - 1);
		return this;
	}

	public SqlPlus select() {

		return this;
	}

	public SqlPlus from() {

		return this;
	}

	public SqlPlus groupBy() {
		return this;
	}

	public SqlPlus orderBy() {
		return this;
	}

	public SqlPlus orderByDesc() {
		return this;
	}

	public SqlPlus clear() {
		sql.delete(0, sql.length());
		return this;
	}

	public String toString() {
		return sql.toString();
	}
}
