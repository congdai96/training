package com.dainc.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dainc.constant.SystemConstant;
import com.dainc.model.MstGenderModel;
import com.dainc.model.MstRoleModel;
import com.dainc.model.MstUserModel;
import com.dainc.service.IMstGenderService;
import com.dainc.service.IMstRoleService;
import com.dainc.service.IMstUserService;
import com.dainc.service.impl.MstUserService;
import com.dainc.utils.FormUtil;
import com.dainc.utils.MessageUtil;

@WebServlet(urlPatterns = {"/admin-user"})
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 2686801510274002166L;
	
	@Inject
	private IMstUserService mstUserService;
	
	@Inject
	private IMstRoleService mstRoleService;
	
	@Inject
	private IMstGenderService mstGenderService;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		MstUserModel mstModel = FormUtil.toModel(MstUserModel.class, request);
		String view = "";
		if (mstModel.getType().equals(SystemConstant.LIST)) {
			mstModel.setListResult(mstUserService.findAll());
			view = "/views/admin/user/list.jsp";
			
		} else if (mstModel.getType().equals(SystemConstant.EDIT)) {
			if (mstModel.getUserId() != null) {
				mstModel = mstUserService.findOne(mstModel.getUserId());
			}
			request.setAttribute("roles", mstRoleService.findAll());
			request.setAttribute("genders", mstGenderService.findAll());
//			view = "/views/admin/new/edit.jsp";
			view = "/views/admin/home.jsp";
		}
		MessageUtil.showMessage(request);
		request.setAttribute(SystemConstant.MODEL, mstModel);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
