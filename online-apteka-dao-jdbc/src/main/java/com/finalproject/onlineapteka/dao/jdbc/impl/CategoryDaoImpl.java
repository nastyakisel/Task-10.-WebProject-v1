package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.dao.CategoryDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class CategoryDaoImpl implements CategoryDao{
	
	@Override
	public List<Category> loadAllCategories() throws DAOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Category> categoryList = new ArrayList<Category>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = (Statement) connection.createStatement();

			String sql = "SELECT* FROM category";
			resultSet = (ResultSet) statement.executeQuery(sql);

			while (resultSet.next()) {

				Category category = new Category();
				category.setId(resultSet.getInt(1));
				category.setCategoryName(resultSet.getString(2));

				categoryList.add(category);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return categoryList;
	}
}
