package com.finalproject.onlineapteka.dao;
import java.sql.SQLException;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.exception.DAOException;


public interface GoodsDao {
	List<Drug> loadAllGoods() throws DAOException, SQLException;
}
