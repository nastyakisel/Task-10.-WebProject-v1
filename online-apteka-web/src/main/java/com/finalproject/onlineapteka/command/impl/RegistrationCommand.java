package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.finalproject.onlineapteka.bean.ErrorApteka;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class RegistrationCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final Logger logger = Logger.getRootLogger();
		String action = request.getParameter("action");
		if (!"registration".equals(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		String userName = request.getParameter("user_login");
		String password = request.getParameter("user_password");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setRoleId(1);
		
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
			logger.error("The user cannot be added to the DB", e);
		}
		
		if (receivedUser != null) {
			ErrorApteka errorName = new ErrorApteka("registrationPage.existUserError");
			errors.add(errorName);
			
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("has_errors", errors);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		UserService addService = ServiceFactory.getInstance().getUserService();
		try {
			addService.addUserToBb(user);
		} catch (ServiceException e) {
			logger.error("The user cannot be added to the DB", e);
			
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("session_user", user);
		String sessionId = session.getId();
		session.setAttribute("session_Id", sessionId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp");
		dispatcher.forward(request, response);
	}
		
}
