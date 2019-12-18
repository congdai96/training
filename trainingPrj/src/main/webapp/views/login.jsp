<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light">
		<div class="container">
			<font size="8" class="p-4 mb-3 bg-white text-warning">ログイン</font>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
	</nav>
	<div class="login-form">
		<div class="main-div">
			<c:if test="${not empty message}">
			<div class="alert alert-${alert}">
				${message}
			</div>
			</c:if>
			<form action="<c:url value='/login'/>" id="formLogin" method="post">
				<div class="form-group row">
					<label for="email_address" class="col-md-4 col-form-label text-md-right">ユーザID:</label>
					<div class="col-md-7">
						<input type="text" id="userId" class="form-control" name="userId">
					</div>
				</div>

				<div class="form-group row">
					<label for="password" class="col-md-4 col-form-label text-md-right">パスワード:</label>
					<div class="col-md-7">
						<input type="password" id="password" class="form-control" name="password" >
					</div>
				</div>

				<input type="hidden" value="login" name="action"/>
				<button type="submit" class="btn btn-primary" style="width: 135px" >ログイン</button>
			</form>
		</div>
	</div>
</body>
</html>