package com.dainc.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {

	public static void showMessage(HttpServletRequest request) {
		if (request.getParameter("message") != null) {
			String messageResponse = "";
			String alert = "";
			String message = request.getParameter("message");
			if (message.equals("userid_haved")) {
				messageResponse = "ユーザIDが重複しています。";
				alert = "danger";
			} else if (message.equals("false")) {
				messageResponse = "できません。";
				alert = "danger";
			} else if (message.equals("not_haved")) {
				messageResponse = "結果がありません。";
				alert = "danger";
			} else if (message.equals("add_success")) {
				messageResponse = "登録できました。";
				alert = "success";
			} else if (message.equals("edit_success")) {
				messageResponse = "更新できました。";
				alert = "success";
			} else if (message.equals("delete_success")) {
				messageResponse = "削除できました。";
				alert = "success";
			} else if (message.equals("non_user")) {
				messageResponse = "ユーザーがありません。他の管理者は削除したかもしれません。";
				alert = "danger";
			} else if (message.equals("new_data")) {
				messageResponse = "最新データが更新されました。";
				alert = "success";
			} else if (message.equals("login_success")) {
				messageResponse = "ログインできました。";
				alert = "success";
			}
			request.setAttribute("message", messageResponse);
			request.setAttribute("alert", alert);
		}
	}
}
