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
		</div>
		<div class="main-content">
		<form >
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<c:if test="${not empty messageResponse}">
									<div class="alert alert-${alert}">
  										${messageResponse}
									</div>
								</c:if>
								<h1>役職別集計</h1>
								<button type="button" style="margin-left: 1200px;" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/admin-user?type=list'">一覧</button>
								<br><br><br>
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>No</th>
														<th>役職</th>
														<th>男の人数</th>
														<th>女の人数</th>
														<th style="color: red">未登録の人数</th>
														<th>年齢別人数(0-19)</th>
														<th>年齢別人数（20以上）</th>
														<th>年齢別人数（未登録）</th>
										
									
														
													</tr>
												</thead>
												<tbody>
													<c:set var="i" value="${1}" />
													<c:forEach var="item" items="${shuukeiModel.listResult}">
														<tr>
															<td>${i}</td>
															<td>${item.roleName}</td>
															<td>${item.male}</td>
															<td>${item.female}</td>
															<td style="color: red">${item.notFull}</td>
															<td>${item.ageMax19}</td>
															<td>${item.ageMin20}</td>
															<td>${item.notAge}</td>
		
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