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
								<div class="widget-box table-filter">
									<div class="table-btn-controls">
										<div class="pull-right tableTools-container">
											<div class="dt-buttons btn-overlap btn-group">
												<a flag="info"
												   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
												   title='Thêm bài viết' href='<c:url value="/admin-new?type=edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
												</a>
												<button id="btnDelete" type="button"
														class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
												</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th></th>
														<th>No</th>
														<th>ユーザーID</th>
														<th>氏名</th>
														<th>性別</th>
														<th>年齢</th>
														<th>役職</th>
														<th>更新</th>
													</tr>
												</thead>
												<tbody>
													<c:set var="i" value="${1}" />
													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
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
																<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
																   title="更新" href='${editURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																</a>
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
			$("#btnDelete").click(function() {
				var data = {};
				var ids = $('tbody input[type=checkbox]:checked').map(function () {
		            return $(this).val();
		        }).get();
				data['ids'] = ids;
				deleteNew(data);
			});
			
			function deleteNew(data) {
		        $.ajax({
		            url: '${APIurl}',
		            type: 'DELETE',
		            contentType: 'application/json',
		            data: JSON.stringify(data),
		            success: function (result) {
		                window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=delete_success";
		            },
		            error: function (error) {
		            	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
		            }
		        });
		    }
		</script>
	</body>

	</html>