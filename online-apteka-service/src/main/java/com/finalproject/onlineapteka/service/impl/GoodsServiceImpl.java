package com.finalproject.onlineapteka.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.GoodsDao;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactory;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class GoodsServiceImpl implements GoodsService{
	@Override
	public List<Drug> getAllGoods() throws ServiceException {
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
	
	@Override
	public List<Drug> getGoodsByCategory(Integer categoryName) throws ServiceException {
		GoodsDao goodsDao = DAOFactory.getInstance().getGoodsDao();
		List<Drug> drugList = null;
		
		try {
			drugList = goodsDao.loadGoodsByCategory(categoryName);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return drugList;
	}
	
	@Override
	public Drug getGoodsById(Integer id) throws ServiceException {
		GoodsDao goodsDao = DAOFactory.getInstance().getGoodsDao();
		Drug drug = null;
		
		try {
			drug = goodsDao.loadGoodsById(id);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return drug;
	}
	
	@Override
	public void addGoodToBb(Drug drug) throws ServiceException {
		GoodsDao goodsDao = DAOFactory.getInstance().getGoodsDao();
				
		try {
			goodsDao.saveDrug(drug);
		} catch (DAOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
