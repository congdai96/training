package com.dainc.controller.admin;

import java.io.IOException;


import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dainc.model.ShuukeiModel;
import com.dainc.service.IShuukeiService;
import com.dainc.utils.MessageUtil;

@WebServlet(urlPatterns = {"/admin-shuukei"})
public class ShuukeiController extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Inject
	private IShuukeiService shuukeiService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShuukeiModel shuukeiModel = new ShuukeiModel();
		shuukeiModel.setListResult(shuukeiService.roleShuukei());	//集計データを取る
		request.setAttribute("shuukeiModel", shuukeiModel);
		String view = "/views/admin/shuukei.jsp";
		MessageUtil.showMessage(request);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);


	}

}
