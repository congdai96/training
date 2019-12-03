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
import com.dainc.utils.MessageUtil;
import com.dainc.utils.SessionUtil;

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
		request.setCharacterEncoding("UTF-8");
		MstUserModel mstModel = FormUtil.toModel(MstUserModel.class, request);		//クライアントの情報を取る
		String view = "";
		if (mstModel.getType().equals(SystemConstant.LIST)) {			//一覧画面のすべてデータ
			mstModel.setListResult(mstUserService.findAll());			//すべてデータを取る
			request.setAttribute("roles", mstRoleService.findAll());	//すべて役職を取る
			view = "/views/admin/user/list.jsp";

		}

		else if (mstModel.getType().equals(SystemConstant.EDIT)) {		//一覧画面で更新ボタンを押すと
			mstModel = mstUserService.findOne(mstModel.getUserId());	//クライアントの更新したいユーザーの情報を取る
			request.setAttribute("roles", mstRoleService.findAll());
			request.setAttribute("genders", mstGenderService.findAll());//すべて性別を取る
			view = "/views/admin/user/edit.jsp";

		}

		else if (mstModel.getType().equals(SystemConstant.ADD)) {		//一覧画面で登録ボタンを押すと
			request.setAttribute("roles", mstRoleService.findAll());
			request.setAttribute("genders", mstGenderService.findAll());
			view = "/views/admin/user/add.jsp";
		}

		else if (mstModel.getType().equals(SystemConstant.DELETE)) {	//一覧画面で削除ボタンを押すと
			mstUserService.delete(mstModel.getUserId());				//ユーザーを消す
			response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=delete_success&alert=success");
			return;
		}
		MessageUtil.showMessage(request);								//アナウンス
		request.setAttribute(SystemConstant.MODEL, mstModel);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MstUserModel mstModel = FormUtil.toModel(MstUserModel.class, request);		//クライアントの情報を取る
		String view = "";
		String action = request.getParameter("action");
		if (action != null && action.equals("edit")) {			//更新画面で更新ボタンを押すと
			mstModel.setModifiedBy(((MstUserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserId());	//更新者を追加する
			if (mstUserService.update(mstModel)) {	//更新してみる
				response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=edit_success&alert=success");	//更新できた
			}
			else response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=false&alert=danger");	//更新できない
			return;
		}

		else if (action != null && action.equals("add")) {		//登録画面で登録ボタンを押すと
			mstModel.setCreatedBy(((MstUserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserId());	//登録者を追加する
			if(mstUserService.save(mstModel)) {		//登録してみる
				response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=add_success&alert=success");	//登録できた
				return;
			}
			else {	//登録できない、クライアントに入力した情報を帰す
				request.setAttribute("genders", mstGenderService.findAll());
				request.setAttribute("message", resourceBundle.getString("userid_haved"));
				request.setAttribute("alert", "danger");
				view = "/views/admin/user/add.jsp";
			}
		}

		else if (action != null && action.equals("search")) {		//一覧画面で検索ボタンを押すと
			List<MstUserModel> result = mstUserService.search(mstModel);	//取った情報に応じて検索する
			mstModel.setListResult(result);
			view = "/views/admin/user/list.jsp";
			if (result == null) {	//結果がないとき、アナウンスと帰す
				request.setAttribute("message", resourceBundle.getString("not_haved"));
				request.setAttribute("alert", "danger");
			}
		}
		request.setAttribute(SystemConstant.MODEL, mstModel);
		request.setAttribute("roles", mstRoleService.findAll());
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
