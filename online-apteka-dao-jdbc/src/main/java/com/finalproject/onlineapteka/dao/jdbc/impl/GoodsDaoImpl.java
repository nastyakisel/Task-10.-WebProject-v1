package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.GoodsDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class GoodsDaoImpl implements GoodsDao{
	
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
				drug.setPrice(resultSet.getBigDecimal(5));
				drug.setQuantity(resultSet.getFloat(6));
				drugList.add(drug);

			}
		} catch (SQLException e) {

		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return drugList;
	}
}
