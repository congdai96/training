<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="container">
		<!-- <h1 class="form-heading">login Form</h1> -->
		<div class="navbar-container ace-save-state" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <h1>
                    <i class="fa fa-leaf"></i>
                    	<h1 class="form-control">ログイン</h1>
                </h1>
            </a>
        </div>
		<div class="login-form">
			<div class="main-div">
				<c:if test="${not empty message}">
					<div class="alert alert-${alert}">
							${message}
					</div>
				</c:if>
				<form action="<c:url value='/dang-nhap'/>" id="formLogin" method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="userId" name="userId"
							placeholder="ユーザーID">
					</div>

					<div class="form-group">
						<input type="password" class="form-control" id="password" name="password"
							placeholder="パスワード">
					</div>
					<input type="hidden" value="login" name="action"/>
					<button type="submit" class="btn btn-primary" >ログイン</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>