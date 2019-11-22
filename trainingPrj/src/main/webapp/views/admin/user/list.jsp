<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var ="NewURL" value="/admin-user"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>training</title>
		<script src="https://raw.githubusercontent.com/makeusabrew/bootbox/gh-pages/bootbox.js"></script>
	</head>

	<body>
		<div>
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">
									${message}
							</div>
						</c:if>
		<form id="formsearchSubmit" class="form-inline" style="margin-left:50px" action="<c:url value='/admin-user'/>" method = "post">
			<br>
		    <label >姓:</label>
		    <input type="text" class="form-control" name="familyName" id="familyName">
		    <label style="margin-left:200px">名:</label>
		    <input type="text" class="form-control" name="firstName" id="firstName">
		    <br><br>
		    <div class="form-group">
			  <label for="author">役職:</label>
			  <select class="form-control" name ="authorityId" id ="authorityId">
			  	<option value=""></option>
			  	<c:forEach var="item" items="${roles}">
                  <option value="${item.authorityId}" >${item.authorityName}</option>
                </c:forEach>
			  </select>
			</div>
			<input type="hidden" value="search" name="action"/>
			<button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/admin-user?type=list'" style="margin-left:200px;width: 135px">リスト</button>
		    <button type="submit" class="btn btn-primary" style="width: 135px">検索</button>
		    
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
														<th>No</th>
														<th>ユーザーID</th>
														<th>氏名</th>
														<th>性別</th>
														<th>年齢</th>
														<th>役職</th>
										
														<th style="width: 300px"><button type="button" class="btn btn-primary btn-block" onclick="window.location.href='${pageContext.request.contextPath}/admin-user?type=add'">登録</button></th>
														
													</tr>
												</thead>
												<tbody>
													<c:set var="i" value="${1}" />
													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td>${i}</td>
															<td>${item.userId}</td>
															<td>${item.firstName} ${item.familyName}</td>
															<td>${item.mstGenderModel.genderName}</td>
															<td>
																<c:if test="${item.age==0}"></c:if>
																<c:if test="${item.age!=0}">${item.age}</c:if>
															</td>
															<td>
																<c:if test="${item.admin==1}">*</c:if>
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
																<button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='${editURL}'" style="width: 135px">更新</button>
																<button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='${deleteURL}'" style="width: 135px" >削除</button>
																					
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


		    $("#btnDelete").on("click", ".remove", function(e) {
		        bootbox.confirm("Are you sure you want to delete?", function(result) {
		            if(result){
		              console.log('write code of remove item.');
		            }
		        }); 
		    });

		</script>
	</body>

	</html>