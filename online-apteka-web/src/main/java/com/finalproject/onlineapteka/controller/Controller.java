package com.finalproject.onlineapteka.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.finalproject.onlineapteka.command.Command;


@WebServlet("/controller.html")
public class Controller extends HttpServlet{
	private final CommandProvider provider = new CommandProvider();

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Logger logger = Logger.getRootLogger();
		request.setCharacterEncoding("UTF-8");
		HttpSession session;
		
		String commandName = request.getParameter("action");
		
		Command command = provider.getCommand(commandName);
		
		String requestLocale = request.getParameter("locale");
		String previousURL = request.getHeader("referer");
		
		if (requestLocale != null) {
			session = request.getSession();
			session.setAttribute("requestLocale", requestLocale);
			
			try {
				response.sendRedirect(previousURL);
			} catch (IOException e) {
				logger.error("Error", e);
			}
		}
		else {
		try {
			command.execute(request, response);
		} catch (ServletException e) {
			logger.error("Error", e);
			
		} catch (IOException e) {
			logger.error("Error", e);
		}
		}
	}
}
