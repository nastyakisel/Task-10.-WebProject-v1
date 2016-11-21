package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.finalproject.onlineapteka.command.Command;

public class LocaleCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Logger logger = Logger.getRootLogger();
		String requestLocale = request.getParameter("locale");
		String previousURL = request.getHeader("referer");
		HttpSession session = request.getSession();
		session.setAttribute("requestLocale", requestLocale);
		
		try {
			response.sendRedirect(previousURL);
		} catch (IOException e) {
			logger.error("Error", e);
		}
		
	}
}
