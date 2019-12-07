<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>登録</title>
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
						<font size="8">登録</font>
                        <form id="formSubmit" name="formSubmit" class="form-inline" form action="<c:url value='/admin-user'/>" method="post" onsubmit="return validateForm()">
                           	<br/>
                           	<br/>
                            <br/>
                            <br/>
                            <div class="row">
                                <label class="col-md-1 col-md-offset-1">ユーザID：</label>
                                <input class="col-md-2"  type="text" class="form-control" name="userId" id="userId" value="${model.userId}" required>
                                <label class="col-md-1 col-md-offset-1" >パスワード：</label>
                                <input class="col-md-2"  type="text" class="form-control" id="password" name="password" value="${model.password}" required/>												
                            </div>
                            <br/>
                            <br/>
                            <div class="row">
                                <label class="col-md-1 col-md-offset-1">姓：</label>
                                <input class="col-md-2" type="text" class="form-control" id="familyName" name="familyName" value="${model.familyName}" required/>
                                <label class="col-md-1 col-md-offset-1">名：</label>
                                <input class="col-md-2" type="text" class="form-control" id="firstName" name="firstName" value="${model.firstName}" required/>                                             
                            </div>
                            <br/>
                            <br/>
                            <div class="row">
                                <label class="col-md-1 col-md-offset-1">性別：</label>
                                <select class="col-md-2" class="form-control" id="genderId" name="genderId">
                                <c:if test="${empty model.genderId}">
                                  <option value=""></option>
                                  <c:forEach var="item" items="${genders}">
                                      <option value="${item.genderId}" >${item.genderName}</option>
                                  </c:forEach>
                                </c:if>
                                <c:if test="${not empty model.genderId}">
                                  <option value=""></option>
                                  <c:forEach var="item" items="${genders}">
                                      <option value="${item.genderId}"  <c:if test="${item.genderId == model.genderId}">selected="selected"</c:if>>${item.genderName}
                                      </option>
                                  </c:forEach>
                                </c:if>
                                </select>
                                <label  class="col-md-1 col-md-offset-1">年齢：</label>
                                <input class="col-md-2" type="text" class="form-control" id="age" name="age" <c:if test="${model.age!=0}">value="${model.age}"</c:if> digits/>                                            
                            </div>
                            <br/>
                            <br/>

                            <div class="row">
                                <label class="col-md-1 col-md-offset-1">役職：</label>
                                <select class="col-md-2" class="form-control" id="authorityId" name="authorityId">
                                <c:if test="${empty model.authorityId}">
                                  <option value=""></option>
                                  <c:forEach var="item" items="${roles}">
                                      <option value="${item.authorityId}" >${item.authorityName}</option>
                                  </c:forEach>
                                </c:if>
                                <c:if test="${not empty model.authorityId}">
                                  <option value=""></option>
                                  <c:forEach var="item" items="${roles}">
                                      <option value="${item.authorityId}"  <c:if test="${item.authorityId == model.authorityId}">selected="selected"</c:if>>${item.authorityName}
                                      </option>
                                  </c:forEach>
                                </c:if>
                                </select>                      
                                <label class="col-md-1 col-md-offset-1" class="custom-control-label" for="defaultUnchecked">管理者：</label>
                                <c:if test = "${model.admin==1}">
                                  <input type="checkbox" name="admin" id="admin" checked="checked">
                                </c:if>
                                <c:if test = "${model.admin==0}">
                                  <input type="checkbox" name="admin" id="admin">
                                </c:if>
                            </div>
                            <br/>
                            <br/>
                            <br/>
                            <input type="hidden" value="add" name="action"/>
    						<button style="margin-left:300px;width:150px;border-radius: 12px;" type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/admin-user?type=list'">戻る</button>
    						<button style="margin-left:150px;width:150px;border-radius: 12px;" id="btnAdd" type="button" class="btn btn-primary" >登録</button>			                        
                        </form>

                </div>
            </div>
        </div>
    </div>
</div>
<script>	
		$(":checkbox").change(function(){
		     $(this).val($(this).is(":checked") ? 1 : 0);
		});
		
		$('#btnAdd').on('click', function() {
			document.getElementById("btnAdd").type = "button";
			if(confirm('登録してよろしいですか？')) 
				document.getElementById("btnAdd").type = "submit";
		});
	
	    $(document).ready(function() {

            $.validator.addMethod(
              "RegExName",
              function(value, element) {
                var pHiragana = "[\\u3041-\\u3096\\u309D-\\u309F]|\\uD82C\\uDC01|\\uD83C\\uDE00";
                var pKatakana = "[\\u30A1-\\u30FA\\u30FD-\\u30FF\\u31F0-\\u31FF\\u32D0-\\u32FE\\u3300-\\u3357\\uFF66-\\uFF6F\\uFF71-\\uFF9D]|\\uD82C\\uDC00";
                var pHan = "[\\u2E80-\\u2E99\\u2E9B-\\u2EF3\\u2F00-\\u2FD5\\u3005\\u3007\\u3021-\\u3029\\u3038-\\u303B\\u3400-\\u4DB5\\u4E00-\\u9FD5\\uF900-\\uFA6D\\uFA70-\\uFAD9]|[\\uD840-\\uD868\\uD86A-\\uD86C\\uD86F-\\uD872][\\uDC00-\\uDFFF]|\\uD869[\\uDC00-\\uDED6\\uDF00-\\uDFFF]|\\uD86D[\\uDC00-\\uDF34\\uDF40-\\uDFFF]|\\uD86E[\\uDC00-\\uDC1D\\uDC20-\\uDFFF]|\\uD873[\\uDC00-\\uDEA1]|\\uD87E[\\uDC00-\\uDE1D]";
                var rx = new RegExp("^([a-zA-Z0-9]|" + pHiragana + "|" + pKatakana + "|" + pHan + ")+$");
                return this.optional(element) || rx.test( value );
              },
              "Use a valid username."
            );

            $.validator.addMethod(
              "RegExUserPass",
              function(value, element) {
                return this.optional(element) || /^[a-zA-Z0-9]+$/.test( value );
              },
              "Use a valid username."
            );
            //Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
            $("#formSubmit").validate({
              rules: {
                userId: {
                  required: true,
                  maxlength: 8,
                  RegExUserPass: true
                },
                firstName: {
                  required: true,
                  maxlength: 10,
                  RegExName: true
                },

                familyName: {
                  required: true,
                  maxlength: 10,
                  RegExName: true
                },
                password: {
                  required: true,
                  maxlength: 8,
                  RegExUserPass: true
                },
                age:{
                  digits:true
                }

              },
              messages: {
                userId: {
                  required: "ユーザーIDが未入力です。",
                  maxlength: "ユーザーIDは8文字以下。",
                  RegExUserPass: "IDはalphabet文字と数字だけ。"
                },
                firstName: {
                  required: "名がが未入力です。",
                  maxlength: "名は10文字以下。",
                  RegExName: "名は文字と数字だけ。"
                },

                familyName: {
                  required: "姓がが未入力です。",
                  maxlength: "姓は10文字以下。",
                  RegExName: "姓は文字と数字だけ。"
                },
                password: {
                  required: "パスワードが未入力です。",
                  maxlength: "パスワードは8文字以下。",
                  RegExUserPass: "パスワードはalphabet文字と数字だけ。"
                },
                age:{
                  digits:"年齢は整数"
                }

              }
            });
     });
	
</script>
</body>
</html>
