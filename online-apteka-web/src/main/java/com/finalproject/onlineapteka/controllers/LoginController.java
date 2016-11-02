package com.finalproject.onlineapteka.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.bean.ErrorApteka;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.UserDaoImpl;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;
import com.finalproject.onlineapteka.service.impl.UserServiceImpl;


@WebServlet("/login.html")
public class LoginController extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		if (!"login".equals(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
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
			ErrorApteka emptyName = new ErrorApteka("loginPage.emptyName.error");
			errors.add(emptyName);
		}
		
		if (password.isEmpty()) {
			ErrorApteka emptyPassword = new ErrorApteka("loginPage.emptyPassword.error");
			errors.add(emptyPassword);
		}
		
		
		UserService service = ServiceFactory.getInstance().getUserService();
		User receivedUser = null;
		
		try {
			receivedUser = service.getUser(userName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		if (receivedUser == null) {
			ErrorApteka errorName = new ErrorApteka("loginPage.errorName");
			errors.add(errorName);
			
		}
		
		String receivedPassword = receivedUser.getPassword();
		if (!password.equals(receivedPassword)) {
			ErrorApteka errorPassword = new ErrorApteka("loginPage.errorPassword");
			errors.add(errorPassword);
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("has_errors", errors);
			request.setAttribute("current_user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("session_user", receivedUser);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp");
		dispatcher.forward(request, response);
	}
}
