package com.finalproject.onlineapteka.service;

import java.util.List;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface GoodsService {
	List<Drug> getAllGoods() throws ServiceException;
	List<Drug> getGoodsByCategory(Integer categoryName) throws ServiceException;
	void addGoodToBb(Drug drug) throws ServiceException;
	Drug getGoodsById(Integer id) throws ServiceException;
}
