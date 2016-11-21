package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetAllGoodsCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final Logger logger = Logger.getRootLogger();
		List<Drug> drugList = null;
		List<Category> categoryList = null;
		GoodsService goodsService = (GoodsService) ServiceFactory.getInstance()
				.getGoodsService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		try {
			drugList = goodsService.getAllGoods();
			categoryList = categoryService.getAllCategories();
			logger.debug("The good recieved from the DB");
		} catch (ServiceException e) {
			logger.error("The good cannot be recieved from the DB", e);
		}

		request.setAttribute("drug_List", drugList);
		request.setAttribute("category_List", categoryList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("start.jsp");
		dispatcher.forward(request, response);
	}
}
