package com.finalproject.onlineapteka.dao;

import java.sql.SQLException;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface UserDao {
	User loadUser(String userName) throws DAOException, SQLException;
	void saveUser(User user) throws DAOException, SQLException;
	
}
