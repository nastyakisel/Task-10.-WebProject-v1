package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DBPool2;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;



public class UserDaoImpl implements UserDao{
	
	private static final String SELECT_USER_BY_USER_NAME = "SELECT id, user_name, password FROM user WHERE user_name=?";
	
	private static final String SAVE_USER = "INSERT INTO user(user_name, password) VALUES (?, ?)";
	
	//@Override
	public User loadUser(String userName) throws DAOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection(); 
			statement = connection.createStatement(); 
			
			String sql = "SELECT id, user_name, password FROM user WHERE user_name='"
					+ userName + "'";
			resultSet = (ResultSet) statement.executeQuery(sql);

			if (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("user_name");
				String password = resultSet.getString("password");

				User user = new User();
				user.setId(id);
				user.setUserName(name);
				user.setPassword(password);

				return user;
			}
		} catch (SQLException e) {
			
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}

		return null;
	}
	
	public void saveUser(User user) throws DAOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection(); 
			statement = connection.createStatement(); 
			
			String sql2 = "INSERT INTO user(user_name, password) VALUES ('"
					+ user.getUserName()+ "', '" + user.getPassword() + "'";
			statement.executeUpdate(sql2);

			
		} catch (SQLException e) {
			
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}

		
	}
}
