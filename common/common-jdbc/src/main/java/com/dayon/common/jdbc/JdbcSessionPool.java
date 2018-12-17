package com.dayon.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.dayon.common.base.dto.model.DataQueue;

public class JdbcSessionPool {
	private final ThreadLocal<JdbcSession> threadLocal = new ThreadLocal<>();
	private final DataQueue<JdbcSession> jdbcSessionDataQueue = new DataQueue<>();
	private String driver;
	private String url;
	private String user;
	private String password;

	/**
	 * @param databaseInfo:
	 *            {driver,url,user,password}
	 * @param availableConnectionCount:
	 *            Suggested 10-200
	 */
	public JdbcSessionPool(Properties databaseInfo, int count) throws Exception {
		count = count <= 0 ? 1 : count;
		this.driver = databaseInfo.getProperty("driver");
		this.url = databaseInfo.getProperty("url");
		this.user = databaseInfo.getProperty("user");
		this.password = databaseInfo.getProperty("password");
		Class.forName(this.driver);
		for (int i = 0; i < count; i++) {
			this.jdbcSessionDataQueue.input(this.createSession(this.url, this.user, this.password));
		}
	}

	private JdbcSession createSession(String url, String user, String password) throws Exception {
		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);
		return new JdbcSession(connection) {
			public boolean close() {
				try {
					if (this.isTansaction) {
						this.rollback();
						this.isTansaction=false;
					}
					JdbcSessionPool.this.threadLocal.remove();
					JdbcSessionPool.this.jdbcSessionDataQueue.input(this);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
		};
	}

	public JdbcSession getSession() {
		try {
			JdbcSession jdbcSession = this.threadLocal.get();
			if (jdbcSession == null) {
				jdbcSession = this.jdbcSessionDataQueue.output();
				if (!jdbcSession.isAvailable()) {
					jdbcSession = this.createSession(this.url, this.user, this.password);
				}
				this.threadLocal.set(jdbcSession);
			}
			return jdbcSession;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
