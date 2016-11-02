package com.finalproject.onlineapteka.service.impl;

import java.sql.SQLException;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactory;
import com.finalproject.onlineapteka.dao.jdbc.impl.UserDaoImpl;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;


public class UserServiceImpl implements UserService{
	
	//@Override
	public User getUser(String userName) throws ServiceException {
		UserDao userDao = DAOFactory.getInstance().getUserDao();
		User user = null;
		
		try {
			user = userDao.loadUser(userName);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	public void addUserToBb(User user) throws ServiceException {
		UserDao userDao = DAOFactory.getInstance().getUserDao();
				
		try {
			userDao.saveUser(user);
		} catch (DAOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
}
