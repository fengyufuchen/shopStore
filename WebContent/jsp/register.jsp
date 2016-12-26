<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, com.itheima.web.domain.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/jscss.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
	text-decoration: none;
	list-style: none;
}

#cotainerBody {
	outline: 2px solid green;
}

#nav-guide {
	height: 100%;
}

#nav-guide ol {
	margin: 20px 0px;
}

#nav-guide ol li {
	display: inline-block;
	margin-left: 7px;
	cursor: pointer;
}

.searchform {
	margin-left: -20px;
}
/*主要内容区域*/
.mainContent {
	background: url("/Store18s/image/regist_bg.jpg")
}

.label-hint {
	padding: 0;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
	margin-left: 20px;
	margin-right: 6px;
	outline: 2px solid green;
}

#refresh-authcode {
	margin-top: 15px;
	font-size: 20px;
	margin-left: 5px;
}

#authcodeimg {
	margin-top: 2px;
}

#showMsg {
	padding-left: 10px;
	font-size: 8px;
}
</style>
<title>Insert title here</title>
</head>
<body>

	<%@include file="/jsp/navheader.jsp"%>


	<div class="container-fluid mainContent">


		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>会员注册</h3>
						<p>请填写注册信息:</p>
					</div>
					<div class="form-top-right">
						<i class="fa fa-key"></i>
					</div>
				</div>
				<div class="form-bottom">
					<form role="form" method="post" class="login-form"
						action="${pageContext.request.contextPath}/user?method=regist"
						class="form-horizontal">
						<div class="form-group">
							<label class="col-md-2 control-label" for="username">用户名：</label>

							<div class="col-md-10">
								<input type="text" name="username" placeholder="请输入用户名"
									class="form-username form-control" id="form-username">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="password">密码</label>

							<div class="col-md-10">
								<input type="text" name="password" placeholder="请输入密码"
									class="form-password form-control" id="form-password">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label">确认密码</label>

							<!-- 不给这个表单设置name属性，从而这个表单数据就无法提交；或者设置表单的属性为disableed -->
							<div class="col-md-10">
								<input type="text" placeholder="请输入确认密码" onpaste="return false;"
									oncontextmenu="return false" oncopy="return false;"
									oncut="return false;" class="form-ensurepassword form-control"
									id="form-ensurepassword">
							</div>
						</div>



						<div class="form-group">
							<label class="col-md-2 control-label" for="email">Email</label>

							<div class="col-md-10">
								<input type="text" name="email" placeholder="请输入邮箱"
									class="form-email form-control" id="form-email">
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-2 control-label" for="name">姓名</label>

							<div class="col-md-10">
								<input type="text" name="name" placeholder="请输入姓名"
									class="form-compellation form-control" id="form-compellation">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="sex">性别</label>

							<div class="col-md-10">
								<label> <input type="radio" name="sex" id="form-sex-man"
									value="man" checked> 男
								</label> <label> <input type="radio" name="sex"
									id="form-sex-woman" value="man"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="birthday">日期</label>

							<div class="col-md-10 input-group date form-date" data-date=""
								data-date-format="dd MM yyyy">
								<input class="form-control" size="16" type="text" readonly
									name="birthday"> <span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"> </span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
						</div>

						<div class="form-group ">
							<label class="col-md-2 control-label" for="code">验证码</label>
							<div class="col-md-3">

								<input type="text" name="code" class="form-control"
									id="form-authcode">

							</div>
							<div class="col-md-3">
								<img alt="验证码" id="authcodeimg"
									data-authpath="${pageContext.request.contextPath }/authServlet">
							</div>
							<div class="col-md-2">
								<span type="text" id="refresh-authcode"
									class=" glyphicon glyphicon-refresh"></span>
							</div>
							<div id="showMsg" class=""></div>




						</div>


						<button type="submit" class="btn" id="submitInfo">Sign
							in!</button>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 social-login">
				<h3 style="margin: 0px 0px;">...or login with:</h3>
				<div class="social-login-buttons">
					<a class="btn btn-link-1 btn-link-1-facebook" href="#"> <i
						class="fa fa-facebook"></i> Facebook
					</a> <a class="btn btn-link-1 btn-link-1-twitter" href="#"> <i
						class="fa fa-twitter"></i> Twitter
					</a> <a class="btn btn-link-1 btn-link-1-google-plus" href="#"> <i
						class="fa fa-google-plus"></i> Google Plus
					</a>
				</div>

			</div>

		</div>
</body>
<script src="${pageContext.request.contextPath }/js/regist.js"
	type="text/javascript">
	
</script>

<script type="text/javascript">
	$(function() {
		$("#form-authcode")
				.bind(
						"keydown",
						function(event) {

							var e = event || window.event
									|| arguments.callee.caller.arguments[0];

							$
									.get(
											"${pageContext.request.contextPath}/authServlet?method=checkClientAuthCode",
											{
												clientAuthCode : $(
														"#form-authcode").val()

											}, function(data, textStatus) {
												console.log("data" + data)
												var result = JSON.parse(data);
												console.log(result)
												console.log(result.authResult)
												$("#showMsg").text(
														result.authResult);

											});

							console.log("发送验证数据：" + $("#showMsg").val());

						});
		//请求分类信息
		function getCategory() {

			$
					.get(
							"${pageContext.request.contextPath}/index?method=getCategory",
							{},
							function(data, status) {

								var array = eval("[" + data + "]");
								for (var i = 0; i < array.length; i++) {

									$("#categoryTitle")
											.append(
													"<li><a href=\" ${pageContext.request.contextPath}/product?method=findByPage&cid="
															+ array[i].cid
															+ "&currPage=1\">"
															+ array[i].cname
															+ " </a></li>")

								}

							}

					)

		}

		getCategory();

	})
</script>
</html>