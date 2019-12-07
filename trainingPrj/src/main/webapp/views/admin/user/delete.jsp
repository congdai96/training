<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>削除</title>
</head>
<body>

<div class="main-content">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">
									${message}
							</div>
						</c:if>
						<font size="8">削除</font>
						<form id="formSubmit" name="formSubmit" form action="<c:url value='/admin-user'/>" method="post">
                            <div class="row">
                                <label style="margin-left: 300px; margin-top: 70px;"><font size="6">以下のデータを削除してよろしいですか。</font></label>
                            </div>
                            	<label style="margin-left: 400px;margin-top: 20px;"><font size="5">ゆーざーID：</font></label>
                                <label style="margin-left: ２0px;"><font size="5">${model.userId}</font></label>
                                <br>
                                <label style="margin-left: 400px;margin-top: 10px;"><font size="5">氏名:</font></label>
                                <label style="margin-left: 90px;"><font size="5">${model.firstName} ${model.familyName}</font></label>
								<br><br><br><br><br>
								<input type="hidden" value="delete" name="action"/>
								<input type="hidden" value="${model.userId}" id="userId" name="userId"/>
								<button style="margin-left:350px;width:150px;border-radius: 12px;" type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/admin-user?type=list'">戻る</button>
                            	<button style="margin-left:50px;width:150px;border-radius: 12px;" id="btnDelete" type="button" class="btn btn-primary" >削除</button>
<!--                            <button style="margin-left:50px;width:150px;border-radius: 12px;" id="btnDelete" type="button" class="btn btn-primary" onClick="if(confirm('以下のデータを削除してよろしいですか。\nユーザID: ${model.userId}\n氏名:      ${model.firstName} ${model.familyName}')) window.location.href='${deleteURL}';" >削除</button> --> 	
						</form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>	

		
		$('#btnDelete').on('click', function() {
			document.getElementById("btnDelete").type = "button";
			if(confirm('削除してよろしいですか？')) 
				document.getElementById("btnDelete").type = "submit";
		});
	
	
</script>
</body>
</html>
