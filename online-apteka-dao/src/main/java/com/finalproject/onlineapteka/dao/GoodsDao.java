package com.finalproject.onlineapteka.dao;
import java.sql.SQLException;
import java.util.List;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.exception.DAOException;


public interface GoodsDao {
	List<Drug> loadAllGoods() throws DAOException, SQLException;
	List<Drug> loadGoodsByCategory(Integer categoryId) throws DAOException, SQLException;
	void saveDrug(Drug drug) throws DAOException, SQLException;
	Drug loadGoodsById(Integer id) throws DAOException, SQLException;
}
