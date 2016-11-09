package com.finalproject.onlineapteka.service;

import java.sql.SQLException;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface GoodsService {
	List<Drug> getAllGoods() throws DAOException, SQLException;
}
