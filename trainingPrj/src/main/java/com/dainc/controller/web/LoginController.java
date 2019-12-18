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

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {



	@Inject
	private IMstUserService mstUserService;

	private static final long serialVersionUID = 2686801510274002166L;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {										//クライアントにログイン画面を送る
			String alert = request.getParameter("alert");									//アナウンスを取る
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}
		else if (action != null && action.equals("logout")) {								//ログアウトすると
			SessionUtil.getInstance().removeValue(request, "USERMODEL");					//Sessionにログインしているユーザーを消す
			response.sendRedirect(request.getContextPath()+"/login?action=login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {										//クライアントでログインボタンを押すと
			MstUserModel mstModel = FormUtil.toModel(MstUserModel.class, request);			//ログインの情報を取る
			mstModel = mstUserService.findByUserNameAndPassword(mstModel.getUserId(), mstModel.getPassword());	//情報を検査する
			if (mstModel != null) {															//正しいとき
				SessionUtil.getInstance().putValue(request, "USERMODEL", mstModel);			//Sessionにログインしているユーザーを入れる
				response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=login_success");
			}
			else {																			//間違うとき
				response.sendRedirect(request.getContextPath()+"/login?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
}
