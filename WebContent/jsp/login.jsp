<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/jscss.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%">
<head>
<style type="text/css">
body {
	background: url("${pageContext.request.contextPath}/img/loginbk.jpg")
		no-repeat;
	background-size: cover;
	style ="height: 100%"
}

.mainContent {
	padding-top: 20px;
}

#refresh-authcode {
	margin-top: 10px;
	font-size: 22px;
	line-height: 25px;
	height: 25px;
}

#recordAuthCode {
	display: hidden;
}

#showMsg {
	padding-left: 10px;
	font-size: 8px;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="height: 100%">
	<div class="container-fluid mainContent">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2 text">
				<h1>
					<strong>Store</strong> Login Form
				</h1>
				<div class="description">
					<p>
						This is a free responsive login form . <a
							href="${pageContext.request.contextPath }/jsp/register.jsp"><strong>
								or Regist</strong></a>, customize and use it as you like!
					</p>
					<p>${loginResult }</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>会员登陆</h3>
					</div>
					<div class="form-top-right">
						<i class="fa fa-key"></i>
					</div>
				</div>
				<div class="form-bottom">
					<form role="form" method="post" class="login-form"
						action="${pageContext.request.contextPath}/user?method=login"
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

						<div class="form-group ">
							<label class="col-md-2 control-label" for="code">验证码</label>
							<div class="col-md-4">

								<input type="text" name="code" class="form-control"
									id="form-authcode">

							</div>
							<div class="col-md-3">
								<img alt="验证码" id="authcodeimg"
									data-authpath="${pageContext.request.contextPath }/authServlet">
							</div>
							<div class="col-md-3">

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
<script src="${pageContext.request.contextPath }/js/login.js"
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

		

	})
</script>
</html>