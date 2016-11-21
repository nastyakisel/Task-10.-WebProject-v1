package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorApteka;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class LoginCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final Logger logger = Logger.getRootLogger();
		String action = request.getParameter("action");
		if (!"login".equals(action)) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		String userName = request.getParameter("user_login");
		String password = request.getParameter("user_password");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);

		RequestDispatcher dispatcher;
		List<ErrorApteka> errors = new ArrayList<ErrorApteka>();

		if (userName.isEmpty()) {
			ErrorApteka emptyName = new ErrorApteka("loginPage.emptyName.error");
			errors.add(emptyName);
		}

		if (password.isEmpty()) {
			ErrorApteka emptyPassword = new ErrorApteka(
					"loginPage.emptyPassword.error");
			errors.add(emptyPassword);
		}

		UserService service = ServiceFactory.getInstance().getUserService();
		User receivedUser = null;

		try {
			receivedUser = service.getUser(userName);
		} catch (ServiceException e) {
			logger.error("The user cannot be recieved from the DB", e);
		}

		if (receivedUser == null) {
			ErrorApteka errorName = new ErrorApteka("loginPage.errorUser");
			errors.add(errorName);

		}

		else {
			String receivedPassword = receivedUser.getPassword();
			if (!password.equals(receivedPassword)) {
				ErrorApteka errorPassword = new ErrorApteka(
						"loginPage.errorPassword");
				errors.add(errorPassword);
			}
		}
		if (!errors.isEmpty()) {
			request.setAttribute("has_errors", errors);
			request.setAttribute("current_user", user);

			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		HttpSession session = request.getSession();
		session.setAttribute("session_user", receivedUser);
		String sessionId = session.getId();
		session.setAttribute("session_Id", sessionId);

		List<Drug> drugList = null;
		List<Category> categoryList = null;
		GoodsService goodsService = (GoodsService) ServiceFactory.getInstance()
				.getGoodsService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		try {
			drugList = goodsService.getAllGoods();
			categoryList = categoryService.getAllCategories();
		} catch (ServiceException e) {
			logger.error("The good cannot be recieved from the DB", e);
			
		}

		request.setAttribute("drug_List", drugList);
		request.setAttribute("category_List", categoryList);

		Integer roleId = receivedUser.getRoleId();

		if (roleId == 1) {
			dispatcher = request
					.getRequestDispatcher("/WEB-INF/pages/welcome.jsp");
			dispatcher.forward(request, response);
		}

		if (roleId == 2) {
			dispatcher = request.getRequestDispatcher("administratorPage.jsp");
			dispatcher.forward(request, response);
		}

		if (roleId == 3) {
			dispatcher = request
					.getRequestDispatcher("/WEB-INF/pages/administratorPage.jsp");
			dispatcher.forward(request, response);
		}

	}

}
