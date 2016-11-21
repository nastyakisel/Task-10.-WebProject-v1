package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class EditGoodCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final Logger logger = Logger.getRootLogger();
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
		
		String goodName = request.getParameter("good_name");
		String description = request.getParameter("good_descr");
		String goodDosage = request.getParameter("dosage");
		String instruction = request.getParameter("instruction");
		String goodPrice = request.getParameter("price");
		String goodQuantity = request.getParameter("quantity");
		String goodRecipe =  request.getParameter("recipe");
		String imagePath = request.getParameter("image");
		String goodCategoryId = request.getParameter("cat_id");
			
		List<ErrorApteka> errors = new ArrayList<ErrorApteka>();
		
		if (goodName.isEmpty()) {
			ErrorApteka emptyName = new ErrorApteka("addGood.emptyName.error");
			errors.add(emptyName);
		}
		
		if (description.isEmpty()) {
			ErrorApteka emptyDescription = new ErrorApteka("addGood.emptyDescription.error");
			errors.add(emptyDescription);
		}
		
		if (goodDosage.isEmpty()) {
			ErrorApteka emptyDosage = new ErrorApteka("addGood.emptyDosage.error");
			errors.add(emptyDosage);
		}
		
		if (instruction.isEmpty()) {
			ErrorApteka emptyInstruction = new ErrorApteka("addGood.emptyInstruction.error");
			errors.add(emptyInstruction);
		}
		if (goodPrice.isEmpty()) {
			ErrorApteka emptyGoodPrice = new ErrorApteka("addGood.emptyGoodPrice.error");
			errors.add(emptyGoodPrice);
		}
		
		if (goodQuantity.isEmpty()) {
			ErrorApteka emptyGoodQuantity = new ErrorApteka("addGood.emptyGoodQuantity.error");
			errors.add(emptyGoodQuantity);
		}
		if (goodRecipe.isEmpty()) {
			ErrorApteka emptyGoodRecipe = new ErrorApteka("addGood.emptyGoodRecipe.error");
			errors.add(emptyGoodRecipe);
		}
		
		if (imagePath.isEmpty()) {
			ErrorApteka emptyPassword = new ErrorApteka("addGood.emptyGoodRecipe.error");
			errors.add(emptyPassword);
		}
		if (goodCategoryId.isEmpty()) {
			ErrorApteka emptyGoodCategoryId = new ErrorApteka("addGood.emptyGoodCategoryId.error");
			errors.add(emptyGoodCategoryId);
		}
		
		if (!errors.isEmpty()) {
			dispatcher = request.getRequestDispatcher("addGood.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
		
		Drug drug = new Drug();
		drug.setDrugName(goodName);
		drug.setDescription(description);
		drug.setDosage(goodDosage);
		drug.setInstruction(instruction);
		drug.setPrice(BigDecimal.valueOf(Double.valueOf(goodPrice)));
		drug.setQuantity(Float.valueOf(goodQuantity));
		drug.setNeedRecipe(Integer.parseInt(goodRecipe));
		drug.setImagePath(imagePath);
		drug.setCategoryId(Integer.parseInt(goodCategoryId));
		
		GoodsService service = (GoodsService) ServiceFactory.getInstance().getGoodsService();
			
			try {
				service.addGoodToBb(drug);
			} catch (ServiceException e) {
				logger.error("The good cannot be added to the DB", e);
			}
			
		String previousURL = request.getParameter("previousURI");
		response.sendRedirect(previousURL);
	}
	}
}
