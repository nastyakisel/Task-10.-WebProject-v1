package com.finalproject.onlineapteka.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalproject.onlineapteka.bean.ErrorApteka;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

@WebServlet("/registration.html")
public class RegistrationController {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		if (!"registration".equals(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/registration.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		String userName = request.getParameter("user_login");
		String password = request.getParameter("user_password");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		
		List<ErrorApteka> errors = new ArrayList<ErrorApteka>();
		

		if (userName.isEmpty()) {
			ErrorApteka emptyName = new ErrorApteka("registrationPage.emptyName.error");
			errors.add(emptyName);
		}
		
		if (password.isEmpty()) {
			ErrorApteka emptyPassword = new ErrorApteka("registrationPage.emptyPassword.error");
			errors.add(emptyPassword);
		}
		
		
		UserService getService = ServiceFactory.getInstance().getUserService();
		User receivedUser = null;
		
		try {
			receivedUser = getService.getUser(userName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		if (receivedUser != null) {
			ErrorApteka errorName = new ErrorApteka("registrationPage.existUserError");
			errors.add(errorName);
			
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("has_errors", errors);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/registration.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		UserService addService = ServiceFactory.getInstance().getUserService();
		try {
			addService.addUserToBb(user);
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp");
		dispatcher.forward(request, response);
	}
}
