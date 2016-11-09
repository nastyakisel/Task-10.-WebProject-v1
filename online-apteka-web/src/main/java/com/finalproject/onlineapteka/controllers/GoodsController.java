package com.finalproject.onlineapteka.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;


@WebServlet("/goodsList.html")
public class GoodsController extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<Drug> drugList = null;
		GoodsService service = (GoodsService) ServiceFactory.getInstance().getGoodsService();
		
		try {
			drugList = service.getAllGoods();
		} catch (DAOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		request.setAttribute("drug_List", drugList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/start.jsp");
		dispatcher.forward(request, response);
	}
}
