<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var ="NewURL" value="/admin-new"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Danh sách bài viết</title>
	</head>

	<body>
		<div class="main-content">
		<form action="<c:url value='/admin-new'/>" id="formSubmit" method="get">
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
														<th><button type="submit" class="btn btn-primary btn-block" >登録</button></th>
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
																<c:url var="editURL" value="/admin-new">
																	<c:param name="type" value="edit"/>
																	<c:param name="userId" value="${item.userId}"/>
																</c:url>
																<button type="submit" class="btn btn-primary btn-sm" >更新</button>
																<button type="submit" class="btn btn-primary btn-sm" >削除</button>																
															</td>
														</tr>
													<c:set var="i" value="${i+1}" />
													</c:forEach>
												</tbody>
											</table>
											<ul class="pagination" id="pagination"></ul>
											<input type="hidden" value="" id="type" name="type"/>
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

		</script>
	</body>

	</html>