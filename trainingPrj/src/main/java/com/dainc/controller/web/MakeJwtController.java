package com.dainc.controller.web;

import java.io.IOException;

import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dainc.model.MstUserModel;
import com.dainc.service.IMstUserService;
import com.dainc.utils.FormUtil;
import com.dainc.utils.SessionUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.dainc.utils.JwTokenHelper;
import com.dainc.utils.HttpUtil;

@WebServlet(urlPatterns = {"/jwt"})
public class MakeJwtController extends HttpServlet {
	
	@Inject
	private IMstUserService mstUserService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		MstUserModel userModel =  FormUtil.toModel(MstUserModel.class, request);
		userModel = mstUserService.findByUserNameAndPassword(userModel.getUserId(), userModel.getPassword());
		if (userModel != null) {
			String token = JwTokenHelper.createJWT(userModel);
			((ObjectNode) rootNode).put("status", "login_success");
			((ObjectNode) rootNode).put("token", token);
			((ObjectNode) rootNode).put("userName", userModel.getFamilyName());
			mapper.writeValue(response.getOutputStream(),rootNode);
		}
		else {
			((ObjectNode) rootNode).put("status", "login_false");
			mapper.writeValue(response.getOutputStream(),rootNode);
		}
	}
	
	
}
