package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class UserDaoImpl implements UserDao {

	@Override
	public User loadUser(String userName) throws DAOException, SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = (Statement) connection.createStatement();

			String sql = "SELECT id, user_name, password, FK_ROLE_ID FROM user WHERE user_name='"
					+ userName + "'";
			resultSet = (ResultSet) statement.executeQuery(sql);

			if (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("user_name");
				String password = resultSet.getString("password");
				Integer roleId = resultSet.getInt("FK_ROLE_ID");

				User user = new User();
				user.setId(id);
				user.setUserName(name);
				user.setPassword(password);
				user.setRoleId(roleId);

				return user;
			}
		} catch (SQLException e) {

		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return null;
	}

	@Override
	public void saveUser(User user) throws DAOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = (Statement) connection.createStatement();

			String sql2 = "INSERT INTO user(user_name, password, FK_ROLE_ID) VALUES ('"
					+ user.getUserName() + "', '" + user.getPassword() + "', '" + user.getRoleId() + "')";
			System.out.println(sql2);
			statement.executeUpdate(sql2);

		} catch (SQLException e) {

		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}

	}
}
