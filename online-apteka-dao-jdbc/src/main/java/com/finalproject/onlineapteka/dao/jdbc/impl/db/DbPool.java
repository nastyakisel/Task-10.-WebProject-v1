package com.finalproject.onlineapteka.dao.jdbc.impl.db;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class DbPool {

	private static final String DS_NAME = "jdbc/online_apteka";

	private static DbPool pool = null;


	public static DbPool getPool() {
		if (pool == null) {
			try {
				pool = new DbPool();
			} catch (NamingException e) {
				throw new RuntimeException(e);
			}
		}

		return pool;
	}

	private DataSource dataSource;

	private DbPool() throws NamingException {
		Context context = new InitialContext();
		Context root = (Context) context.lookup("java:/comp/env");
		dataSource = (DataSource) root.lookup(DS_NAME);
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void closeDbResources(Connection connection) {
		closeDbResources(connection, null);
	}

	public void closeDbResources(Connection connection, Statement statement) {
		closeDbResources(connection, statement, null);
	}

	public void closeDbResources(Connection connection, Statement statement, ResultSet resultSet) {
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
