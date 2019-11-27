package com.dainc.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
	
	public static void showMessage(HttpServletRequest request) {
		if (request.getParameter("message") != null) {
			String messageResponse = "";
			String alert = "";
			String message = request.getParameter("message");
			if (message.equals("userid_haved")) {
				messageResponse = "ユーザーIDがありました。";
				alert = "danger";
			} else if (message.equals("false")) {
				messageResponse = "失敗しました。";
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
			} 
			request.setAttribute("message", messageResponse);
			request.setAttribute("alert", alert);
		}
	}
}
