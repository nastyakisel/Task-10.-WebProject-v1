package com.finalproject.onlineapteka.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.dao.CategoryDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactory;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class CategoryServiceImpl implements CategoryService{
	@Override
	public List<Category> getAllCategories() throws ServiceException {
		CategoryDao categoryDao = DAOFactory.getInstance().getCategoryDao();
		List<Category> categoryList = null;
		
		try {
			categoryList = categoryDao.loadAllCategories();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
}
