package com.finalproject.onlineapteka.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.GoodsDao;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactory;
import com.finalproject.onlineapteka.service.GoodsService;

public class GoodsServiceImpl implements GoodsService{
	public List<Drug> getAllGoods() throws DAOException, SQLException {
		GoodsDao goodsDao = DAOFactory.getInstance().getGoodsDao();
		List<Drug> drugList = null;
		
		try {
			drugList = goodsDao.loadAllGoods();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return drugList;
	}
	
}
