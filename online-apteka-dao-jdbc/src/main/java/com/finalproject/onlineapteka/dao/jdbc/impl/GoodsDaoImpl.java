package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.GoodsDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class GoodsDaoImpl implements GoodsDao {

	@Override
	public List<Drug> loadAllGoods() throws DAOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Drug> drugList = new ArrayList<Drug>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = (Statement) connection.createStatement();

			String sql = "SELECT* FROM drug";
			resultSet = (ResultSet) statement.executeQuery(sql);

			while (resultSet.next()) {

				Drug drug = new Drug();
				drug.setId(resultSet.getInt(1));
				drug.setDrugName(resultSet.getString(2));
				drug.setDescription(resultSet.getString(3));
				drug.setDosage(resultSet.getString(4));
				drug.setInstruction(resultSet.getString(5));
				drug.setPrice(resultSet.getBigDecimal(6));
				drug.setQuantity(resultSet.getFloat(7));
				drug.setNeedRecipe(resultSet.getInt(8));
				drug.setImagePath(resultSet.getString(9));
				drug.setCategoryId(resultSet.getInt(10));
				drugList.add(drug);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return drugList;
	}

	@Override
	public List<Drug> loadGoodsByCategory(Integer categoryId)
			throws DAOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Drug> drugList = new ArrayList<Drug>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = (Statement) connection.createStatement();

			String sql = "SELECT* FROM drug WHERE FK_CATEGORY_ID= '"
					+ categoryId + "'";
			resultSet = (ResultSet) statement.executeQuery(sql);

			while (resultSet.next()) {

				Drug drug = new Drug();
				drug.setId(resultSet.getInt(1));
				drug.setDrugName(resultSet.getString(2));
				drug.setDescription(resultSet.getString(3));
				drug.setDosage(resultSet.getString(4));
				drug.setInstruction(resultSet.getString(5));
				drug.setPrice(resultSet.getBigDecimal(6));
				drug.setQuantity(resultSet.getFloat(7));
				drug.setNeedRecipe(resultSet.getInt(8));
				drug.setImagePath(resultSet.getString(9));
				drug.setCategoryId(resultSet.getInt(10));
				drugList.add(drug);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return drugList;
	}

	@Override
	public Drug loadGoodsById(Integer id)
			throws DAOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Drug drug = new Drug();

		try {
			connection = DbPool.getPool().getConnection();
			statement = (Statement) connection.createStatement();

			String sql = "SELECT* FROM drug WHERE id= '"
					+ id + "'";
			resultSet = (ResultSet) statement.executeQuery(sql);

			while (resultSet.next()) {
				drug.setId(resultSet.getInt(1));
				drug.setDrugName(resultSet.getString(2));
				drug.setDescription(resultSet.getString(3));
				drug.setDosage(resultSet.getString(4));
				drug.setInstruction(resultSet.getString(5));
				drug.setPrice(resultSet.getBigDecimal(6));
				drug.setQuantity(resultSet.getFloat(7));
				drug.setNeedRecipe(resultSet.getInt(8));
				drug.setImagePath(resultSet.getString(9));
				drug.setCategoryId(resultSet.getInt(10));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return drug;
	}
	
	@Override
	public void saveDrug(Drug drug) throws DAOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = (Statement) connection.createStatement();

			String sql2 = "INSERT INTO drug(drugname, description, dosage, instruction, price,"
					+ "quantity, need_recipe, image_path, FK_CATEGORY_ID) VALUES ('"
					+ drug.getDrugName() + "', '" + drug.getDescription() + "', '"
					+ drug.getDosage() + "', '" + drug.getInstruction() + "', '"
					+ drug.getPrice() + "', '" + drug.getQuantity() + "', '"
					+ drug.getNeedRecipe() + "', '" + drug.getImagePath() + "', '"
					+ drug.getCategoryId()
					+ "')";
			System.out.println(sql2);
			statement.executeUpdate(sql2);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}

	}

}
