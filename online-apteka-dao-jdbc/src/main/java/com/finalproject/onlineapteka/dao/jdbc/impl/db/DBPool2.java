package com.finalproject.onlineapteka.dao.jdbc.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBPool2 {
	private static final String DS_NAME = "jdbc/online_apteka";

	private DataSource dataSource;
	private Connection connection;

	public DBPool2() throws NamingException, SQLException {
		Context context = new InitialContext();
		Context root = (Context) context.lookup("java:/comp/env");
		dataSource = (DataSource) root.lookup(DS_NAME);
		connection = dataSource.getConnection();
		System.out.println("");
	}

	public Connection getConnection() throws SQLException {
		return connection;
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
