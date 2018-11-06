package com.dayon.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SessionPool {
	private final ThreadLocal<Session> sessionLocal = new ThreadLocal<Session>();
	private final List<Session> sessions = new LinkedList<Session>();
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
	public SessionPool(Properties databaseInfo, int sessionCount) throws Exception {
		if (sessionCount <= 0) {
			throw new Exception("param 'sessionCount' must be greater than 0");
		}
		this.driver = databaseInfo.getProperty("driver");
		this.url = databaseInfo.getProperty("url");
		this.user = databaseInfo.getProperty("user");
		this.password = databaseInfo.getProperty("password");
		Class.forName(this.driver);
		for (int i = 0; i < sessionCount; i++) {
			this.sessions.add(this.createSession(this.url, this.user, this.password));
		}
	}

	private Session createSession(String url, String user, String password) throws Exception {
		Connection connection = DriverManager.getConnection(url, user, password);
		return new Session(connection) {
			public boolean close() {
				try {
					if (!this.isAutoCommit()) {
						this.rollback();
						this.autoCommit();
					}
					SessionPool.this.sessionLocal.remove();
					SessionPool.this.inputSession(this);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
		};
	}

	private synchronized void inputSession(Session session) {
		this.sessions.add(session);
		if (this.sessions.size() == 1) {
			this.notify();
		}
	}

	private synchronized Session outSession() throws Exception {
		if (this.sessions.size() == 0) {
			this.wait();
		}
		Session session = this.sessions.remove(0);
		if (!session.isAvailable()) {
			session = this.createSession(this.url, this.user, this.password);
		}
		return session;
	}

	public Session getSession() {
		try {
			Session session = this.sessionLocal.get();
			if (session == null) {
				session = this.outSession();
				this.sessionLocal.set(session);
			}
			return session;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
