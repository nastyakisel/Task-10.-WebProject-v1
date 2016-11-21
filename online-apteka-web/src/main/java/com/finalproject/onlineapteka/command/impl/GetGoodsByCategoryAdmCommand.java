package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetGoodsByCategoryAdmCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final Logger logger = Logger.getRootLogger();
		Integer categotyId = Integer.parseInt(request.getParameter("catId"));

		List<Drug> drugList = null;
		List<Category> categoryList = null;
		GoodsService service = (GoodsService) ServiceFactory.getInstance()
				.getGoodsService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		try {
			drugList = service.getGoodsByCategory(categotyId);
			categoryList = categoryService.getAllCategories();
		} catch (ServiceException e) {
			logger.error("The good cannot be recieved from the DB", e);
		}

		HttpSession session = request.getSession();
		session.setAttribute("categotyId", categotyId);

		request.setAttribute("drug_List", drugList);
		request.setAttribute("category_List", categoryList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("administratorPage_2.jsp");
		dispatcher.forward(request, response);
	}
}
