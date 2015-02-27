package model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import log.Loger;
import snaq.db.ConnectionPool;

class DBInit {
	private final static Loger loger = Loger.getInstanse();
	private static String URL = "jdbc:mysql://localhost/issues_subscriptions?useUnicode=true&characterEncoding=utf8";
	private static String LOGIN = "root";
	private static String PASSWORD = "123";
	private static String POOLNAME = "subscriptions";
	private static ConnectionPool pool;

	static {	
		loger.log(Loger.Event.INFO, "DBInit", "DBInit initialization starts");	
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			pool = new ConnectionPool(POOLNAME, 1, 100, 0, 180000, URL, LOGIN, PASSWORD);
		} catch (ClassNotFoundException e) {
			loger.log(Loger.Event.FATALERROR, "DBInit", "failed DBInit initialization", e);
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			loger.log(Loger.Event.FATALERROR, "DBInit", "failed DBInit initialization", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			loger.log(Loger.Event.FATALERROR, "DBInit", "failed DBInit initialization", e);
			throw new RuntimeException(e);
		}

	}

	static synchronized Connection getConnection() throws SQLException {
		Connection result = null;
		result = pool.getConnection();
		loger.log(Loger.Event.INFO, "DBInit", "getConnection executed");
		return result;
	}

	static synchronized void poolRelease() {
		pool.release();
		loger.log(Loger.Event.INFO, "DBInit", "Connection pool closed");
	}

}
