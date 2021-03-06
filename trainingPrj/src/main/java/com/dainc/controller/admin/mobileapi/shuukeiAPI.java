package com.dainc.controller.admin.mobileapi;

import java.io.IOException;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dainc.model.MstUserModel;
import com.dainc.service.IMstUserService;
import com.dainc.service.IShuukeiService;
import com.dainc.utils.FormUtil;
import com.dainc.utils.HttpUtil;
import com.dainc.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-shuukei"})
public class shuukeiAPI extends HttpServlet {
	
	@Inject
	private IShuukeiService shuukeiService;

	private static final long serialVersionUID = -915988021506484384L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		mapper.writeValue(response.getOutputStream(), shuukeiService.roleShuukei());
	}
}
