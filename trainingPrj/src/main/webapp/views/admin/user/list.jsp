<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var ="NewURL" value="/admin-user"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>一覧</title>
		<script src="https://raw.githubusercontent.com/makeusabrew/bootbox/gh-pages/bootbox.js"></script>
	</head>

	<body>
		<div>
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">
									${message}
							</div>
						</c:if>
						<font size="8">一覧</font>
		<form id="formsearchSubmit" class="form-inline" style="margin-left:50px" method = "post">
			<br>
			<div class="row">
		    <label class="col-sm-1">姓:</label>
		    <input type="text" class="col-sm-2" name="familyName" id="familyName" value="${model.familyName}">
		    <label class="col-sm-1 col-sm-offset-2" >名:</label>
		    <input  type="text" class="col-sm-2" name="firstName" id="firstName" value="${model.firstName}">
		    </div>
		    <br><br>
		    <div class="row">
			  <label class="col-sm-1">役職:</label>
			  <select class="col-sm-2" name ="authorityId" id ="authorityId">
			  	<option value=""></option>
			  	<c:forEach var="item" items="${roles}">
                  <option value="${item.authorityId}" <c:if test="${item.authorityId == model.authorityId}">selected="selected"</c:if>>
                  ${item.authorityName}
                  </option>
              
                </c:forEach>
			  </select>
			<input type="hidden" value="search" name="action"/>
			<button id="report" type="button" class="btn btn-primary" style="margin-left:300px;width: 200px;border-radius: 12px;">リスト</button>
		    <button id="search" type="button" class="btn btn-primary" style="width: 200px;border-radius: 12px;">検索</button>
		    </div>
  		</form>
		</div>
		<div class="main-content">
		<form action="<c:url value='/admin-user'/>" id="formSubmit" method="get">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<c:if test="${not empty messageResponse}">
									<div class="alert alert-${alert}">
  										${messageResponse}
									</div>
								</c:if>
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th class="col-xs-1">No</th>
														<th class="col-xs-2">ユーザーID</th>
														<th class="col-xs-2">氏名</th>
														<th class="col-xs-1">性別</th>
														<th class="col-xs-1">年齢</th>
														<th class="col-xs-2">役職</th>
										
														<th class="col-xs-3"><button style="border-radius: 12px;" type="button" class="btn btn-primary btn-block" onclick="window.location.href='${pageContext.request.contextPath}/admin-user?type=add'">登録</button></th>
														
													</tr>
												</thead>
												<tbody>
													<c:set var="i" value="${1}" />
													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td>${i}</td>
															<td>${item.userId}</td>
															<td>${item.familyName} ${item.firstName}</td>
															<td>${item.mstGenderModel.genderName}</td>
															<td>
																<c:if test="${item.age==0}"></c:if>
																<c:if test="${item.age!=0}">${item.age}</c:if>
															</td>
															<td>
																<c:if test="${item.admin==1}"><span class="fa fa-star"></span></c:if>
																${item.mstRoleModel.authorityName}</td>
															<td>
																<c:url var="editURL" value="/admin-user">
																	<c:param name="type" value="edit"/>
																	<c:param name="userId" value="${item.userId}"/>
																</c:url>
																<c:url var="deleteURL" value="/admin-user">
																	<c:param name="type" value="delete"/>
																	<c:param name="userId" value="${item.userId}"/>
																</c:url>
																<button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='${editURL}'" style="width: 135px;border-radius: 12px;">更新</button>
																<button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='${deleteURL}'" style="width: 135px;border-radius: 12px;" >削除</button>
																					
															</td>
														</tr>
														<c:set var="i" value="${i+1}" />
													</c:forEach>
												</tbody>
											</table>
											<ul class="pagination" id="pagination"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</form>
		</div>
		<!-- /.main-content -->
		<script>
		
		$('#report').click(function(){
			   $('#formsearchSubmit').attr('action', '/training/admin-download');
			   document.getElementById('formsearchSubmit').submit();
			});
		
		$('#search').click(function(){
			   $('#formsearchSubmit').attr('action', '/training/admin-user');
			   document.getElementById('formsearchSubmit').submit();
			});
		

		</script>
	</body>

	</html>