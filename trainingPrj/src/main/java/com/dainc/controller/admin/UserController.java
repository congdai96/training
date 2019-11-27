package com.dainc.controller.admin;

import java.io.IOException;

import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dainc.constant.SystemConstant;
import com.dainc.model.MstUserModel;
import com.dainc.service.IMstGenderService;
import com.dainc.service.IMstRoleService;
import com.dainc.service.IMstUserService;
import com.dainc.utils.FormUtil;
import com.dainc.utils.SessionUtil;
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
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		MstUserModel mstModel = FormUtil.toModel(MstUserModel.class, request);		//requestのparamを取る
		String view = "";
		if (mstModel.getType().equals(SystemConstant.LIST)) {			
			mstModel.setListResult(mstUserService.findAll());			//すべてのデータを取る
			request.setAttribute("roles", mstRoleService.findAll());
			view = "/views/admin/user/list.jsp";
			
		} 
		
		else if (mstModel.getType().equals(SystemConstant.EDIT)) {		//一覧画面で更新ボタンを押したあと
			mstModel = mstUserService.findOne(mstModel.getUserId());
			request.setAttribute("roles", mstRoleService.findAll());
			request.setAttribute("genders", mstGenderService.findAll());
			view = "/views/admin/user/edit.jsp";
			
		}
		
		else if (mstModel.getType().equals(SystemConstant.ADD)) {		//一覧画面で登録ボタンを押したあと
			request.setAttribute("roles", mstRoleService.findAll());
			request.setAttribute("genders", mstGenderService.findAll());
			view = "/views/admin/user/add.jsp";
		}
		
		else if (mstModel.getType().equals(SystemConstant.DELETE)) {	//一覧画面で削除ボタンを押したあと
			mstUserService.delete(mstModel.getUserId());
			response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=delete_success&alert=success");
			return;
		}
		MessageUtil.showMessage(request);								//アナウンスのところ
		request.setAttribute(SystemConstant.MODEL, mstModel);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MstUserModel mstModel = FormUtil.toModel(MstUserModel.class, request);
		String action = request.getParameter("action");
		if (action != null && action.equals("edit")) {			//更新画面で更新ボタンを押したあと
			mstModel.setModifiedBy(((MstUserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserId());
			if (mstUserService.update(mstModel)) {
				response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=edit_success&alert=success");
			}
			else response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=false&alert=danger");
		}
		
		else if (action != null && action.equals("add")) {		//登録画面で登録ボタンを押したあと
			mstModel.setCreatedBy(((MstUserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserId());
			if(mstUserService.save(mstModel)) {
				response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=add_success&alert=success");
			}
			else response.sendRedirect(request.getContextPath()+"/admin-user?type=add&message=userid_haved&alert=danger");
		}
		
		else if (action != null && action.equals("search")) {	//一覧画面で検索ボタンを押したあと
			List<MstUserModel> result = mstUserService.search(mstModel);	//データを取る
			if (result !=null) {
				mstModel.setListResult(result);
				String view = "";
				view = "/views/admin/user/list.jsp";
				request.setAttribute(SystemConstant.MODEL, mstModel);
				request.setAttribute("roles", mstRoleService.findAll());
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
			else response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=not_haved&alert=danger");
		}
		
	}
}
