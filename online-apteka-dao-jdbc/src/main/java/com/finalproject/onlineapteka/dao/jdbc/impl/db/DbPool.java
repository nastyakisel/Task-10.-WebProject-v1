package com.finalproject.onlineapteka.dao.jdbc.impl.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;



public class DbPool {

	private static final String DS_NAME = "jdbc/online_apteka";

	private static DbPool pool = null;

	/*
	 * static { if (pool == null) { try { pool = new DbPool(); } catch
	 * (NamingException e) { // throw new //
	 * RuntimeException("Some errors occurred during DB initialization!", // e);
	 * } } }
	 */

	public static DbPool getPool() {
		if (pool == null) {
			try {
				pool = new DbPool();
				System.out.println("///");
			} catch (NamingException e) {
				// throw new RuntimeException(e);
			}

		}
		return pool;
	}

	/*
	 * public static DbPool getPool() { return pool; }
	 */

	private DataSource dataSource;

	public DbPool() throws NamingException {
		Context context = new InitialContext();
		Context root = (Context) context.lookup("java:/comp/env");
		dataSource = (DataSource) root.lookup(DS_NAME);
		System.out.println("///");
		
		/*dataSource = new MysqlDataSource(); 
		((MysqlDataSource) dataSource).setURL("jdbc:mysql://localhost:3306/online_apteka");
		((MysqlDataSource) dataSource).setUser("root"); 
		((MysqlDataSource) dataSource).setPassword("root");*/
		
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void closeDbResources(Connection connection, Statement statement,
			ResultSet resultSet) {
		closeResultSet(resultSet);
		closeStatement(statement);
		closeConnection(connection);
	}

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}

	public void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
	}

	public void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
			}
		}
	}
}
